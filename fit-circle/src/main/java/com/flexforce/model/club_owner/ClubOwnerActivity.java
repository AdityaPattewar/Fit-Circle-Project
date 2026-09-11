
package com.flexforce.model.club_owner;

public class ClubOwnerActivity {

    private String activityId;
    private String clubId;
    private String createdBy;

    private String activityName;
    private String activityType;
    private String difficulty;

    private String date;
    private String startTime;
    private String endTime;

    private String location;
    private String trainer;

    private int maximumParticipants;

    private String description;

    private String status;
    private long createdAt;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // Required for Firebase Firestore
    // =========================================================

    public ClubOwnerActivity() {
    }

    // =========================================================
    // PARAMETERIZED CONSTRUCTOR
    // =========================================================

    public ClubOwnerActivity(
            String activityId,
            String clubId,
            String createdBy,
            String activityName,
            String activityType,
            String difficulty,
            String date,
            String startTime,
            String endTime,
            String location,
            String trainer,
            int maximumParticipants,
            String description,
            String status,
            long createdAt) {

        this.activityId = activityId;
        this.clubId = clubId;
        this.createdBy = createdBy;

        this.activityName = activityName;
        this.activityType = activityType;
        this.difficulty = difficulty;

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

        this.location = location;
        this.trainer = trainer;

        this.maximumParticipants = maximumParticipants;

        this.description = description;

        this.status = status;
        this.createdAt = createdAt;
    }

    // =========================================================
    // GETTERS AND SETTERS
    // =========================================================

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getMaximumParticipants() {
        return maximumParticipants;
    }

    public void setMaximumParticipants(int maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public long getCreatedAt() {
    return createdAt;
    }

    public void setCreatedAt(long createdAt) { 
    this.createdAt = createdAt;
    }
}

