package com.flexforce.dao;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.user.User;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

import java.util.concurrent.TimeUnit;

public class UserProfileDAO {

    private final Firestore db;

    public UserProfileDAO() {

        db = FirebaseConfig.getFirebaseConfig();
    }

    // ============================================================
    // SAVE PROFILE
    // ============================================================

    public boolean saveUserProfile(User user) {

        try {

            if (user == null ||
                    user.getUserId() == null ||
                    user.getUserId().trim().isEmpty()) {

                System.out.println(
                        "Invalid user data."
                );

                return false;
            }

            DocumentReference document =
                    db.collection("Users")
                            .document(
                                    user.getUserId()
                            );

            ApiFuture<WriteResult> future =
                    document.set(user);

            future.get(
                    10,
                    TimeUnit.SECONDS
            );

            System.out.println(
                    "User profile saved successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error while saving profile:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // ============================================================
    // GET PROFILE
    // ============================================================

    public User getUserProfile(String userId) {

        try {

            if (userId == null ||
                    userId.trim().isEmpty()) {

                return null;
            }

            DocumentReference document =
                    db.collection("Users")
                            .document(
                                    userId.trim()
                            );

            ApiFuture<DocumentSnapshot> future =
                    document.get();

            DocumentSnapshot snapshot =
                    future.get(
                            10,
                            TimeUnit.SECONDS
                    );

            if (snapshot.exists()) {

                User user =
                        snapshot.toObject(
                                User.class
                        );

                if (user != null) {

                    user.setUserId(
                            userId
                    );
                }

                return user;
            }

            System.out.println(
                    "No User document found: " +
                            userId
            );

            return null;

        } catch (Exception e) {

            System.out.println(
                    "Error while loading profile:"
            );

            e.printStackTrace();

            return null;
        }
    }

    // ============================================================
    // UPDATE PROFILE
    // ============================================================

    public boolean updateUserProfile(User user) {

        try {

            if (user == null ||
                    user.getUserId() == null ||
                    user.getUserId().trim().isEmpty()) {

                System.out.println(
                        "Invalid user data for update."
                );

                return false;
            }

            DocumentReference document =
                    db.collection("Users")
                            .document(
                                    user.getUserId()
                            );

            ApiFuture<WriteResult> future =
                    document.set(
                            user,
                            SetOptions.merge()
                    );

            WriteResult result =
                    future.get(
                            10,
                            TimeUnit.SECONDS
                    );

            System.out.println(
                    "User profile updated successfully."
            );

            System.out.println(
                    "Update time: " +
                            result.getUpdateTime()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error while updating profile:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // ============================================================
    // DELETE PROFILE
    // ============================================================

    public boolean deleteUserProfile(
            String userId) {

        try {

            if (userId == null ||
                    userId.trim().isEmpty()) {

                return false;
            }

            DocumentReference document =
                    db.collection("Users")
                            .document(
                                    userId
                            );

            ApiFuture<WriteResult> future =
                    document.delete();

            future.get(
                    10,
                    TimeUnit.SECONDS
            );

            System.out.println(
                    "User profile deleted."
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}