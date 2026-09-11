package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.common_for_user_clubowner.UserChallenge;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class UserChallengeDAO {

    private Firestore db =
            FirebaseConfig.getFirebaseConfig();


    // ============================================================
    // SAVE CHALLENGE
    // ============================================================

    public void saveChallenge(UserChallenge challenge) {

        try {

            if (challenge == null) {
                System.out.println(
                        "Challenge cannot be null."
                );
                return;
            }

            if (challenge.getChallengeId() == null ||
                    challenge.getChallengeId().trim().isEmpty()) {

                System.out.println(
                        "Challenge ID cannot be empty."
                );
                return;
            }

            db.collection("UserChallenges")
                    .document(challenge.getChallengeId())
                    .create(challenge)
                    .get();

            System.out.println(
                    "Challenge Data Inserted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // GET ONE CHALLENGE
    // ============================================================

    public UserChallenge getChallenge(
            String challengeId) {

        try {

            if (challengeId == null ||
                    challengeId.trim().isEmpty()) {

                return null;
            }

            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserChallenges")
                            .document(challengeId)
                            .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        UserChallenge.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ============================================================
    // GET ALL CHALLENGES
    // ============================================================

    public List<UserChallenge> getChallenges() {

        List<UserChallenge> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserChallenges")
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                UserChallenge challenge =
                        doc.toObject(
                                UserChallenge.class
                        );

                if (challenge != null) {

                    /*
                     * If ChallengeId is missing in Firebase
                     * document field, use document ID.
                     */
                    if (challenge.getChallengeId() == null ||
                            challenge.getChallengeId()
                                    .trim()
                                    .isEmpty()) {

                        challenge.setChallengeId(
                                doc.getId()
                        );
                    }

                    list.add(challenge);
                }
            }

            System.out.println(
                    "Total Challenges Loaded = "
                            + list.size()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // UPDATE CHALLENGE
    // ============================================================

    public void updateChallenge(
            UserChallenge challenge) {

        try {

            if (challenge == null) {
                return;
            }

            if (challenge.getChallengeId() == null ||
                    challenge.getChallengeId()
                            .trim()
                            .isEmpty()) {

                return;
            }

            db.collection("UserChallenges")
                    .document(
                            challenge.getChallengeId()
                    )
                    .update(
                            "title",
                            challenge.getTitle(),

                            "description",
                            challenge.getDescription(),

                            "target",
                            challenge.getTarget(),

                            "points",
                            challenge.getPoints(),

                            "startDate",
                            challenge.getStartDate(),

                            "endDate",
                            challenge.getEndDate()
                    )
                    .get();

            System.out.println(
                    "Challenge Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // DELETE CHALLENGE
    // ============================================================

    public void deleteChallenge(
            String challengeId) {

        try {

            if (challengeId == null ||
                    challengeId.trim().isEmpty()) {

                return;
            }

            db.collection("UserChallenges")
                    .document(challengeId)
                    .delete()
                    .get();

            System.out.println(
                    "Challenge Data Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}