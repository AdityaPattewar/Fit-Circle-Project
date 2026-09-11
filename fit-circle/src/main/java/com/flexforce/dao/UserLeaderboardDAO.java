package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.user.UserLeaderboard;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class UserLeaderboardDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();

    

    public void saveUserLeaderboard(UserLeaderboard leaderboard) {
        try {
            db.collection("UserLeaderboard")
              .document(String.valueOf(leaderboard.getUserId()))
              .create(leaderboard);

            System.out.println("User Leaderboard Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    public UserLeaderboard getUserLeaderboard(String userId) {
        try {
            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserLeaderboard")
                      .document(String.valueOf(userId))
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(UserLeaderboard.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public void updateUserLeaderboard(UserLeaderboard leaderboard) {
        try {
            db.collection("UserLeaderboard")
              .document(String.valueOf(leaderboard.getUserId()))
              .update(
                  "userName", leaderboard.getUserName(),
                  "rank", leaderboard.getRank(),
                  "points", leaderboard.getPoints(),
                  "streak", leaderboard.getStreak(),
                  "level", leaderboard.getLevel(),
                  "timeframe", leaderboard.getTimeframe()
              );

            System.out.println("User Leaderboard Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    public void deleteUserLeaderboard(String userId) {
        try {
            db.collection("UserLeaderboard")
              .document(String.valueOf(userId))
              .delete();

            System.out.println("User Leaderboard Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

    public List<UserLeaderboard> getUserLeaderboards() {

        List<UserLeaderboard> list = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> future =
                    db.collection("UserLeaderboard").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserLeaderboard leaderboard =
                        doc.toObject(UserLeaderboard.class);

                list.add(leaderboard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
