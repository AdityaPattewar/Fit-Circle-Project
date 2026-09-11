package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.user.UserNotification;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class UserNotificationDAO {

    private Firestore db;

    public UserNotificationDAO() {
        db = FirebaseConfig.getFirebaseConfig();
    }

    // =========================================================
    // SAVE NOTIFICATION
    // =========================================================

    public void saveNotification(UserNotification notification) {

        try {

            DocumentReference document =
                    db.collection("UserNotification")
                      .document(notification.getNotificationId());

            document.set(notification).get();

            System.out.println(
                    "User Notification Data Inserted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // GET ONE NOTIFICATION
    // =========================================================

    public UserNotification getNotification(
            String notificationId) {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserNotification")
                      .document(notificationId)
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        UserNotification.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE NOTIFICATION
    // =========================================================

    public void updateNotification(
            UserNotification notification) {

        try {

            db.collection("UserNotification")
              .document(notification.getNotificationId())
              .set(notification)
              .get();

            System.out.println(
                    "User Notification Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // DELETE ONE NOTIFICATION
    // =========================================================

    public void deleteNotification(
            String notificationId) {

        try {

            db.collection("UserNotification")
              .document(notificationId)
              .delete()
              .get();

            System.out.println(
                    "User Notification Data Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // GET ALL NOTIFICATIONS
    // =========================================================

    public List<UserNotification> getNotifications() {

        List<UserNotification> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserNotification")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                UserNotification notification =
                        document.toObject(
                                UserNotification.class
                        );

                if (notification != null) {

                    list.add(notification);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET NOTIFICATIONS FOR PARTICULAR USER
    // =========================================================

    public List<UserNotification> getNotificationsByUser(
            String userId) {

        List<UserNotification> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserNotification")
                      .whereEqualTo("userId", userId)
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                UserNotification notification =
                        document.toObject(
                                UserNotification.class
                        );

                if (notification != null) {

                    list.add(notification);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}