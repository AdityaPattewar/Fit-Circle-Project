
package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.MemberParticipation;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class MemberParticipationDAO {

    private final Firestore db;

    public MemberParticipationDAO() {
        db = FirebaseConfig.getFirebaseConfig();
    }

    // =========================================================
    // SAVE
    // =========================================================

    public void saveParticipation(MemberParticipation participation) {

        if (participation == null) {
            System.out.println("Participation is null.");
            return;
        }

        if (participation.getParticipationId() == null ||
                participation.getParticipationId().trim().isEmpty()) {

            System.out.println("Participation ID is missing.");
            return;
        }

        try {

            db.collection("MemberParticipation")
                    .document(participation.getParticipationId())
                    .set(participation)
                    .get();

            System.out.println(
                    "Member Participation Data Inserted");

        } catch (Exception e) {

            System.out.println(
                    "Error inserting participation.");

            e.printStackTrace();
        }
    }

    // =========================================================
    // GET BY ID
    // =========================================================

    public MemberParticipation getParticipation(
            String participationId) {

        if (participationId == null ||
                participationId.trim().isEmpty()) {

            return null;
        }

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("MemberParticipation")
                            .document(participationId)
                            .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {

                return document.toObject(
                        MemberParticipation.class);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting participation.");

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public void updateParticipation(
            MemberParticipation participation) {

        if (participation == null ||
                participation.getParticipationId() == null) {

            System.out.println(
                    "Invalid participation.");

            return;
        }

        try {

            db.collection("MemberParticipation")
                    .document(
                            participation.getParticipationId())
                    .update(
                            "clubId",
                            participation.getClubId(),

                            "day",
                            participation.getDay(),

                            "participants",
                            participation.getParticipants()
                    )
                    .get();

            System.out.println(
                    "Member Participation Data Updated");

        } catch (Exception e) {

            System.out.println(
                    "Error updating participation.");

            e.printStackTrace();
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deleteParticipation(
            String participationId) {

        if (participationId == null ||
                participationId.trim().isEmpty()) {

            return;
        }

        try {

            db.collection("MemberParticipation")
                    .document(participationId)
                    .delete()
                    .get();

            System.out.println(
                    "Member Participation Data Deleted");

        } catch (Exception e) {

            System.out.println(
                    "Error deleting participation.");

            e.printStackTrace();
        }
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<MemberParticipation>
            getParticipations() {

        List<MemberParticipation> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("MemberParticipation")
                            .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                MemberParticipation participation =
                        document.toObject(
                                MemberParticipation.class);

                if (participation != null) {
                    list.add(participation);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting participation list.");

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET BY CLUB
    // =========================================================

    public List<MemberParticipation>
            getParticipationsByClub(String clubId) {

        List<MemberParticipation> list =
                new ArrayList<>();

        if (clubId == null ||
                clubId.trim().isEmpty()) {

            return list;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("MemberParticipation")
                            .whereEqualTo("clubId", clubId)
                            .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                MemberParticipation participation =
                        document.toObject(
                                MemberParticipation.class);

                if (participation != null) {
                    list.add(participation);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting club participation.");

            e.printStackTrace();
        }

        return list;
    }
}