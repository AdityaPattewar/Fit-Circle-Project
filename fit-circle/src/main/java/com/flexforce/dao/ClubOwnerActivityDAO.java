
package com.flexforce.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.ClubOwnerActivity;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class ClubOwnerActivityDAO {

    private final Firestore db =
            FirebaseConfig.getFirebaseConfig();

    // ============================================================
    // SAVE ACTIVITY
    // ============================================================

    public void saveActivity(ClubOwnerActivity activity) {

        try {

            if (activity == null) {
                throw new IllegalArgumentException(
                        "Activity cannot be null."
                );
            }

            if (activity.getActivityId() == null ||
                    activity.getActivityId().trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Activity ID cannot be empty."
                );
            }

            ApiFuture<?> future =
                    db.collection("ClubOwnerActivities")
                      .document(activity.getActivityId())
                      .set(activity);

            // IMPORTANT:
            // Wait until Firestore actually saves data.
            future.get();

            System.out.println(
                    "Activity Data Inserted Successfully"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while saving activity."
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to save activity: "
                            + e.getMessage(),
                    e
            );
        }
    }

    // ============================================================
    // GET ACTIVITY BY ID
    // ============================================================

    public ClubOwnerActivity getActivity(
            String activityId) {

        try {

            if (activityId == null ||
                    activityId.trim().isEmpty()) {

                return null;
            }

            ApiFuture<DocumentSnapshot> future =
                    db.collection("ClubOwnerActivities")
                      .document(activityId)
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        ClubOwnerActivity.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ============================================================
    // GET ACTIVITIES BY CLUB ID
    // ============================================================

    public List<ClubOwnerActivity> getActivitiesByClubId(
            String clubId) {

        List<ClubOwnerActivity> list =
                new ArrayList<>();

        try {

            if (clubId == null ||
                    clubId.trim().isEmpty()) {

                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwnerActivities")
                      .whereEqualTo("clubId", clubId)
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwnerActivity activity =
                        doc.toObject(
                                ClubOwnerActivity.class
                        );

                if (activity != null) {
                    list.add(activity);
                }
            }

            sortNewestFirst(list);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // ============================================================
    // GET ACTIVITIES BY OWNER
    // ============================================================

    public List<ClubOwnerActivity> getActivitiesByCreatedBy(
            String createdBy) {

        List<ClubOwnerActivity> list =
                new ArrayList<>();

        try {

            if (createdBy == null ||
                    createdBy.trim().isEmpty()) {

                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwnerActivities")
                      .whereEqualTo(
                              "createdBy",
                              createdBy
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwnerActivity activity =
                        doc.toObject(
                                ClubOwnerActivity.class
                        );

                if (activity != null) {

                    list.add(activity);
                }
            }

            // Newest activity first
            sortNewestFirst(list);

        } catch (Exception e) {

            System.out.println(
                    "Error loading owner activities."
            );

            e.printStackTrace();
        }

        return list;
    }

    // ============================================================
    // SORT NEWEST FIRST
    // ============================================================

    private void sortNewestFirst(
            List<ClubOwnerActivity> list) {

        list.sort(
                Comparator.comparingLong(
                        ClubOwnerActivity::getCreatedAt
                ).reversed()
        );
    }

    // ============================================================
    // UPDATE ACTIVITY
    // ============================================================

    public void updateActivity(
            ClubOwnerActivity activity) {

        try {

            if (activity == null) {
                throw new IllegalArgumentException(
                        "Activity cannot be null."
                );
            }

            if (activity.getActivityId() == null ||
                    activity.getActivityId().trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Activity ID is required."
                );
            }

            ApiFuture<?> future =
                    db.collection("ClubOwnerActivities")
                      .document(activity.getActivityId())
                      .set(activity);

            future.get();

            System.out.println(
                    "Activity Data Updated Successfully"
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to update activity.",
                    e
            );
        }
    }

    // ============================================================
    // DELETE ACTIVITY
    // ============================================================

    public void deleteActivity(
            String activityId) {

        try {

            if (activityId == null ||
                    activityId.trim().isEmpty()) {

                return;
            }

            ApiFuture<?> future =
                    db.collection("ClubOwnerActivities")
                      .document(activityId)
                      .delete();

            future.get();

            System.out.println(
                    "Activity Data Deleted Successfully"
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to delete activity.",
                    e
            );
        }
    }

    // ============================================================
    // GET ALL ACTIVITIES
    // ============================================================

    public List<ClubOwnerActivity> getActivities() {

        List<ClubOwnerActivity> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwnerActivities")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwnerActivity activity =
                        doc.toObject(
                                ClubOwnerActivity.class
                        );

                if (activity != null) {

                    list.add(activity);
                }
            }

            sortNewestFirst(list);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}