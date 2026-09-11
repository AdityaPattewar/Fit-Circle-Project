package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.ClubOwnerActivityDAO;
import com.flexforce.model.club_owner.ClubOwnerActivity;

public class ClubownerActivityController {

    private final ClubOwnerActivityDAO activityDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ClubownerActivityController() {

        activityDAO =
                new ClubOwnerActivityDAO();
    }

    // =========================================================
    // ADD ACTIVITY
    // =========================================================

    public void addActivity(
            ClubOwnerActivity activity) {

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

        activityDAO.saveActivity(activity);
    }

    // =========================================================
    // GET ACTIVITY
    // =========================================================

    public ClubOwnerActivity getActivity(
            String activityId) {

        return activityDAO.getActivity(
                activityId
        );
    }

    // =========================================================
    // GET ALL ACTIVITIES
    // =========================================================

    public List<ClubOwnerActivity> getActivities() {

        return activityDAO.getActivities();
    }

    // =========================================================
    // GET ACTIVITIES BY CLUB
    // =========================================================

    public List<ClubOwnerActivity>
    getActivitiesByClubId(String clubId) {

        return activityDAO
                .getActivitiesByClubId(
                        clubId
                );
    }

    // =========================================================
    // GET ACTIVITIES BY OWNER
    // =========================================================

    public List<ClubOwnerActivity>
    getActivitiesByCreatedBy(
            String createdBy) {

        return activityDAO
                .getActivitiesByCreatedBy(
                        createdBy
                );
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public void updateActivity(
            ClubOwnerActivity activity) {

        if (activity == null) {

            throw new IllegalArgumentException(
                    "Activity cannot be null."
            );
        }

        activityDAO.updateActivity(
                activity
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deleteActivity(
            String activityId) {

        activityDAO.deleteActivity(
                activityId
        );
    }
}