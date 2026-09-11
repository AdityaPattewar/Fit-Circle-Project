package com.flexforce.model.club_owner;

public class MemberActivity {

    private String activityId;
    private String memberId;
    private String clubId;
    private String activityType; // e.g., "Gym Check-in", "Yoga Class", "Cardio Workout"
    private String date; // Format: "dd MMM yyyy" or "yyyy-MM-dd"
    private String status; // e.g., "Completed", "Missed"

    public MemberActivity() {
        // Required for Firestore
    }

    public MemberActivity(String activityId, String memberId, String clubId, String activityType, String date, String status) {
        this.activityId = activityId;
        this.memberId = memberId;
        this.clubId = clubId;
        this.activityType = activityType;
        this.date = date;
        this.status = status;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
