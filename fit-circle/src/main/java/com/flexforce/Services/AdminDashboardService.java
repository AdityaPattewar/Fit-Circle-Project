package com.flexforce.Services;


import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.controller.ClubownerActivityController;
import com.flexforce.model.admin.AdminDashboardStats;
import com.flexforce.model.club_owner.ClubOwnerActivity;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class AdminDashboardService {
    
    private Firestore firestore;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public AdminDashboardService() {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "ADMIN DASHBOARD SERVICE STARTED"
        );

        System.out.println(
                "=========================================="
        );

        try {

            firestore =
                    FirebaseConfig.getFirebaseConfig();

            if (firestore == null) {

                System.out.println(
                        ">>> FIRESTORE IS NULL"
                );

            } else {

                System.out.println(
                        ">>> FIRESTORE OBJECT CREATED"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    ">>> ADMIN SERVICE ERROR"
            );

            e.printStackTrace();

            firestore = null;
        }
    }

    // ============================================================
    // FIREBASE CONNECTION CHECK
    // ============================================================

    public boolean isFirebaseConnected() {

        return firestore != null;
    }

    // ============================================================
    // GET DASHBOARD STATISTICS
    // ============================================================

    public AdminDashboardStats getDashboardStats() {

        System.out.println(
                ">>> getDashboardStats() called"
        );

        if (firestore == null) {

            System.out.println(
                    ">>> Firestore is null"
            );

            return new AdminDashboardStats(
                    0,
                    0,
                    0,
                    0
            );
        }

        int totalClubs =
                getTotalClubs();

        int activeUsers =
                getActiveUsers();

        /*
         * Booking collection is not implemented
         * in the current project.
         */
        int totalBookings = 0;

        /*
         * Payment collection is not implemented
         * in the current project.
         */
        double totalRevenue = 0;

        System.out.println(
                ">>> Dashboard Stats:"
        );

        System.out.println(
                "Total Clubs = " + totalClubs
        );

        System.out.println(
                "Active Users = " + activeUsers
        );

        System.out.println(
                "Total Bookings = " + totalBookings
        );

        System.out.println(
                "Revenue = " + totalRevenue
        );

        return new AdminDashboardStats(
                totalClubs,
                activeUsers,
                totalBookings,
                totalRevenue
        );
    }

    // ============================================================
    // GET TOTAL CLUBS
    // ============================================================

    private int getTotalClubs() {

        if (firestore == null) {
            return 0;
        }

        try {

            System.out.println(
                    ">>> Reading ClubOwners collection..."
            );

            int count =
                    firestore
                            .collection("ClubOwners")
                            .get()
                            .get()
                            .getDocuments()
                            .size();

            System.out.println(
                    ">>> ClubOwners count = "
                            + count
            );

            return count;

        } catch (Exception e) {

            System.out.println(
                    ">>> ERROR READING ClubOwners"
            );

            e.printStackTrace();

            return 0;
        }
    }

    // ============================================================
    // GET ACTIVE USERS
    // ============================================================

    private int getActiveUsers() {

        if (firestore == null) {
            return 0;
        }

        try {

            System.out.println(
                    ">>> Reading UserInfo collection..."
            );

            int count =
                    firestore
                            .collection("UserInfo")
                            .whereEqualTo(
                                    "role",
                                    "MEMBER"
                            )
                            .get()
                            .get()
                            .getDocuments()
                            .size();

            System.out.println(
                    ">>> Active users count = "
                            + count
            );

            return count;

        } catch (Exception e) {

            System.out.println(
                    ">>> ERROR READING UserInfo"
            );

            e.printStackTrace();

            return 0;
        }
    }

    // ============================================================
    // GET RECENT ACTIVITIES
    // ============================================================

    public List<ClubOwnerActivity> getRecentActivities() {

        List<ClubOwnerActivity> activities =
                new ArrayList<>();

        if (firestore == null) {

            System.out.println(
                    ">>> Cannot get activities - Firestore is null"
            );

            return activities;
        }

        try {

            ClubownerActivityController controller =
                    new ClubownerActivityController();

            activities =
                    controller.getActivities();

            if (activities == null) {

                activities =
                        new ArrayList<>();
            }

            System.out.println(
                    ">>> Activities found = "
                            + activities.size()
            );

        } catch (Exception e) {

            System.out.println(
                    ">>> ERROR READING ACTIVITIES"
            );

            e.printStackTrace();
        }

        return activities;
    }

    // ============================================================
    // FIREBASE TEST
    // ============================================================

    public void testFirebaseConnection() {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "TESTING FIREBASE CONNECTION"
        );

        System.out.println(
                "=========================================="
        );

        if (firestore == null) {

            System.out.println(
                    ">>> FIRESTORE IS NULL"
            );

            return;
        }

        try {

            int clubCount =
                    firestore
                            .collection("ClubOwners")
                            .get()
                            .get()
                            .getDocuments()
                            .size();

            System.out.println(
                    ">>> FIREBASE CONNECTION SUCCESSFUL"
            );

            System.out.println(
                    ">>> ClubOwners documents = "
                            + clubCount
            );

        } catch (Exception e) {

            System.out.println(
                    ">>> FIREBASE QUERY FAILED"
            );

            e.printStackTrace();
        }
    }
}