package com.flexforce.model.common_for_user_clubowner;

public class UserChallenge {

    private String ChallengeId;
    private String title;
    private String description;
    private int target;
    private int points;
    private String startDate;
    private String endDate;

    public UserChallenge() {
    }

    public UserChallenge(
            String ChallengeId,
            String title,
            String description,
            int target,
            int points,
            String startDate,
            String endDate) {

        this.ChallengeId = ChallengeId;
        this.title = title;
        this.description = description;
        this.target = target;
        this.points = points;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getChallengeId() {
        return ChallengeId;
    }

    public void setChallengeId(String challengeId) {
        this.ChallengeId = challengeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}