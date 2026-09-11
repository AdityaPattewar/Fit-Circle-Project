package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.common_for_user_clubowner.UserClub;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class UserClubDao {

    private Firestore db = FirebaseConfig.getFirebaseConfig();

    public void saveClub(UserClub club) {
        try {
            db.collection("UserClubs")
                    .document(String.valueOf(club.getClubId()))
                    .create(club);

            System.out.println("User Club Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserClub getClub(String clubId) {
        try {
            ApiFuture<DocumentSnapshot> future = db.collection("UserClubs")
                    .document(String.valueOf(clubId))
                    .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(UserClub.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserClub getClubFromOwners(String clubId) {
        try {
            ApiFuture<DocumentSnapshot> future = db.collection("ClubOwners")
                    .document(String.valueOf(clubId))
                    .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                UserClub club = document.toObject(UserClub.class);
                if (club != null) {
                    if (club.getClubId() == null || club.getClubId().trim().isEmpty()) {
                        club.setClubId(document.getId());
                    }
                    if (club.getClubAddress() == null || club.getClubAddress().trim().isEmpty()) {
                        Object addr = document.get("address");
                        if (addr != null) {
                            club.setClubAddress(String.valueOf(addr));
                        }
                    }
                    if (club.getStartingPrice() == null || club.getStartingPrice().trim().isEmpty()) {
                        Object priceObj = document.get("price");
                        if (priceObj != null) {
                            club.setStartingPrice(String.valueOf(priceObj));
                        }
                    }
                    if (club.getImage() == null || club.getImage().trim().isEmpty()) {
                        Object profileImg = document.get("profileImage");
                        if (profileImg != null) {
                            club.setImage(String.valueOf(profileImg));
                        }
                    }
                    return club;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateClub(UserClub club) {
        try {
            db.collection("UserClubs")
                    .document(String.valueOf(club.getClubId()))
                    .update(
                            "clubName", club.getClubName(),
                            "description", club.getDescription(),
                            "image", club.getImage(),
                            "ownerId", club.getOwnerId(),
                            "status", club.getStatus());

            System.out.println("User Club Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClub(String clubId) {
        try {
            db.collection("UserClubs")
                    .document(String.valueOf(clubId))
                    .delete();

            System.out.println("User Club Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserClub> getClubs() {

        List<UserClub> list = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future = db.collection("ClubOwners")
                    .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserClub club = doc.toObject(
                        UserClub.class);

                if (club == null) {
                    continue;
                }

                // =================================================
                // ONLY ADD ACTUAL CLUB DOCUMENTS
                // =================================================

                if (club.getClubName() == null ||
                        club.getClubName().trim().isEmpty()) {

                    System.out.println(
                            "Skipping document because clubName is missing: "
                                    + doc.getId());

                    continue;
                }
                
                // Fix address mismatch
                if (club.getClubAddress() == null || club.getClubAddress().trim().isEmpty()) {
                    Object addr = doc.get("address");
                    if (addr != null) {
                        club.setClubAddress(String.valueOf(addr));
                    }
                }

                // Fix image mismatch
                if (club.getImage() == null || club.getImage().trim().isEmpty()) {
                    Object profileImg = doc.get("profileImage");
                    if (profileImg != null) {
                        club.setImage(String.valueOf(profileImg));
                    }
                }

                // =================================================
                // USE FIREBASE DOCUMENT ID
                // =================================================

                if (club.getClubId() == null ||
                        club.getClubId().trim().isEmpty()) {

                    club.setClubId(
                            doc.getId());
                }

                // =================================================
                // ADD CLUB
                // =================================================

                list.add(club);
            }

            System.out.println(
                    "Actual clubs fetched: " +
                            list.size());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}