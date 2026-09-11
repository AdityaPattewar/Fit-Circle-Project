package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.ClubOwnerDashboard;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class ClubOwnerDashboardDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();

   

    public void saveDashboard(String ownerId, ClubOwnerDashboard dashboard) {
        try {
            db.collection("ClubOwnerDashboard")
              .document(ownerId)
              .create(dashboard);

            System.out.println("Club Owner Dashboard Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ClubOwnerDashboard getDashboard(String ownerId) {
        try {
            ApiFuture<DocumentSnapshot> future =
                    db.collection("ClubOwnerDashboard")
                      .document(ownerId)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(ClubOwnerDashboard.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    

    public void updateDashboard(String ownerId, ClubOwnerDashboard dashboard) {
        try {
            db.collection("ClubOwnerDashboard")
              .document(ownerId)
              .update(
                  "totalMembers", dashboard.getTotalMembers(),
                  "upcomingEvents", dashboard.getUpcomingEvents(),
                  "activeActivities", dashboard.getActiveActivities(),
                  "monthlyEngagement", dashboard.getMonthlyEngagement()
              );

            System.out.println("Club Owner Dashboard Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void deleteDashboard(String ownerId) {
        try {
            db.collection("ClubOwnerDashboard")
              .document(ownerId)
              .delete();

            System.out.println("Club Owner Dashboard Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<ClubOwnerDashboard> getDashboards() {

        List<ClubOwnerDashboard> list = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwnerDashboard").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                ClubOwnerDashboard dashboard =
                        doc.toObject(ClubOwnerDashboard.class);

                list.add(dashboard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
