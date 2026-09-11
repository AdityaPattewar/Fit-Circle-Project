package com.flexforce.model.common_for_user_clubowner;



public class UserClubMembership {

    private String userId;
    private String clubId;
    private String joinedDate;
    private String status;

    public UserClubMembership() {
    }

    public UserClubMembership(String userId, String clubId, String joinedDate, String status) {
        this.userId = userId;
        this.clubId = clubId;
        this.joinedDate = joinedDate;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}