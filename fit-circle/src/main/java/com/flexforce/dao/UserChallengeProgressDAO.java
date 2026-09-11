package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.common_for_user_clubowner.UserChallengeProgress;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class UserChallengeProgressDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();

    public void saveProgress(UserChallengeProgress progress) {
        try {
            String documentId = progress.getUserId() + "_" + progress.getChallengeId();

            db.collection("UserChallengeProgress")
              .document(documentId)
              .create(progress);

            System.out.println("User Challenge Progress Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserChallengeProgress getProgress(String userId, String challengeId) {
        try {
            String documentId = userId + "_" + challengeId;

            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserChallengeProgress")
                      .document(documentId)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(UserChallengeProgress.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateProgress(UserChallengeProgress progress) {
        try {
            String documentId = progress.getUserId() + "_" + progress.getChallengeId();

            db.collection("UserChallengeProgress")
              .document(documentId)
              .update(
                  "progress", progress.getProgress(),
                  "completed", progress.isCompleted(),
                  "completedDate", progress.getCompletedDate()
              );

            System.out.println("User Challenge Progress Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProgress(String userId, String challengeId) {
        try {
            String documentId = userId + "_" + challengeId;

            db.collection("UserChallengeProgress")
              .document(documentId)
              .delete();

            System.out.println("User Challenge Progress Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserChallengeProgress> getProgressList() {

        List<UserChallengeProgress> list = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> future =
                    db.collection("UserChallengeProgress").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserChallengeProgress progress =
                        doc.toObject(UserChallengeProgress.class);

                list.add(progress);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}