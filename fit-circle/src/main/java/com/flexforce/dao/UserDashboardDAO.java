package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.user.UserDashboard;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class UserDashboardDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();


    public void saveDashboard(String userId, UserDashboard dashboard) {
        try {
            db.collection("UserDashboard")
              .document(userId)
              .create(dashboard);

            System.out.println("Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public UserDashboard getDashboard(String userId) {
        try {
            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserDashboard")
                      .document(userId)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(UserDashboard.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public void updateDashboard(String userId, UserDashboard dashboard) {
        try {
            db.collection("UserDashboard")
              .document(userId)
              .update(
                  "currentStreak", dashboard.getCurrentStreak(),
                  "totalPoints", dashboard.getTotalPoints(),
                  "currentRank", dashboard.getCurrentRank(),
                  "level", dashboard.getLevel(),
                  "weeklyPoints", dashboard.getWeeklyPoints()
              );

            System.out.println("Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteDashboard(String userId) {
        try {
            db.collection("UserDashboard")
              .document(userId)
              .delete();

            System.out.println("Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<UserDashboard> getDashboards() {

        List<UserDashboard> list = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> future =
                    db.collection("UserDashboard").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserDashboard dashboard =
                        doc.toObject(UserDashboard.class);

                list.add(dashboard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
