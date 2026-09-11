package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class UserClubMembershipDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();

    public void saveMembership(UserClubMembership membership) {
        try {
            db.collection("UserClubMembership")
                    .document(membership.getUserId() + "_" + membership.getClubId())
                    .create(membership);

            System.out.println("Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserClubMembership getMembership(String userId, String clubId) {
        try {
            ApiFuture<DocumentSnapshot> future = db.collection("UserClubMembership")
                    .document(userId + "_" + clubId)
                    .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(UserClubMembership.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateMembership(UserClubMembership membership) {
        try {
            db.collection("UserClubMembership")
                    .document(membership.getUserId() + "_" + membership.getClubId())
                    .update(
                            "joinedDate", membership.getJoinedDate(),
                            "status", membership.getStatus());

            System.out.println("Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMembership(String userId, String clubId) {
        try {
            db.collection("UserClubMembership")
                    .document(userId + "_" + clubId)
                    .delete();

            System.out.println("Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserClubMembership> getMembershipsByUser(String userId) {

        List<UserClubMembership> list = new ArrayList<>();

        if (userId == null || userId.trim().isEmpty()) {
            return list;
        }

        try {

            ApiFuture<QuerySnapshot> future = db.collection("UserClubMembership")
                    .whereEqualTo("userId", userId)
                    .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserClubMembership membership = doc.toObject(UserClubMembership.class);

                if (membership != null) {
                    list.add(membership);
                }
            }

            System.out.println(
                    "Memberships found for user: " + list.size());

        } catch (Exception e) {

            System.out.println(
                    "Error getting memberships for user.");

            e.printStackTrace();
        }

        return list;
    }

    public List<UserClubMembership> getMemberships() {

        List<UserClubMembership> list = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> future = db.collection("UserClubMembership").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserClubMembership membership = doc.toObject(UserClubMembership.class);

                list.add(membership);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    // =========================================================
    // GET MEMBERSHIPS BY CLUB
    // =========================================================

    public List<UserClubMembership> getMembershipsByClub(String clubId) {

        List<UserClubMembership> list = new ArrayList<>();

        if (clubId == null || clubId.trim().isEmpty()) {
            return list;
        }

        try {

            ApiFuture<QuerySnapshot> future = db.collection("UserClubMembership")
                    .whereEqualTo("clubId", clubId)
                    .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserClubMembership membership = doc.toObject(UserClubMembership.class);

                if (membership != null) {
                    list.add(membership);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting club memberships.");

            e.printStackTrace();
        }

        return list;
    }
}
