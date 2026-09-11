package com.flexforce.model.common_for_user_clubowner;

public class UserChallengeProgress {

    private String userId;
    private String challengeId;
    private int progress;
    private boolean completed;
    private String completedDate;

    public UserChallengeProgress() {
    }

    public UserChallengeProgress(String userId, String challengeId, int progress,
                             boolean completed, String completedDate) {
        this.userId = userId;
        this.challengeId = challengeId;
        this.progress = progress;
        this.completed = completed;
        this.completedDate = completedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }
}
