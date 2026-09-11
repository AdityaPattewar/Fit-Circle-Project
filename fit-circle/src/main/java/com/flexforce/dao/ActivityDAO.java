package com.flexforce.dao;

import com.flexforce.config.FirebaseConfig;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActivityDAO {

    private final Firestore db;

    public ActivityDAO() {

        db =
                FirebaseConfig.getFirebaseConfig();
    }

    // ============================================================
    // GET RECENT ACTIVITIES
    // ============================================================

    public List<DocumentSnapshot> getRecentActivities(
            String userId,
            int limit) {

        List<DocumentSnapshot> result =
                new ArrayList<>();

        try {

            if (userId == null ||
                    userId.trim().isEmpty()) {

                return result;
            }

            Query query =
                    db.collection("Activities")
                            .whereEqualTo(
                                    "userId",
                                    userId
                            )
                            .limit(limit);

            ApiFuture<
                    com.google.cloud.firestore.QuerySnapshot
                    > future =
                    query.get();

            com.google.cloud.firestore.QuerySnapshot snapshot =
                    future.get(
                            10,
                            TimeUnit.SECONDS
                    );

            result.addAll(
                    snapshot.getDocuments()
            );

            return result;

        } catch (Exception e) {

            System.out.println(
                    "Error loading recent activities:"
            );

            e.printStackTrace();

            return result;
        }
    }

    // ============================================================
    // GET ALL ACTIVITIES
    // ============================================================

    public List<DocumentSnapshot> getAllActivities(
            String userId) {

        List<DocumentSnapshot> result =
                new ArrayList<>();

        try {

            if (userId == null ||
                    userId.trim().isEmpty()) {

                return result;
            }

            Query query =
                    db.collection("Activities")
                            .whereEqualTo(
                                    "userId",
                                    userId
                            );

            ApiFuture<
                    com.google.cloud.firestore.QuerySnapshot
                    > future =
                    query.get();

            com.google.cloud.firestore.QuerySnapshot snapshot =
                    future.get(
                            10,
                            TimeUnit.SECONDS
                    );

            result.addAll(
                    snapshot.getDocuments()
            );

            return result;

        } catch (Exception e) {

            System.out.println(
                    "Error loading all activities:"
            );

            e.printStackTrace();

            return result;
        }
    }
}
