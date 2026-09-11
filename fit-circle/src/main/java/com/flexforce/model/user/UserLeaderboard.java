package com.flexforce.model.user;

public class UserLeaderboard {

    private String userId;
    private String userName;
    private int rank;
    private int points;
    private int streak;
    private int level;
    private String timeframe;

    public UserLeaderboard() {
    }

    public UserLeaderboard(String userId, String userName, int rank,
                           int points, int streak, int level, String timeframe) {
        this.userId = userId;
        this.userName = userName;
        this.rank = rank;
        this.points = points;
        this.streak = streak;
        this.level = level;
        this.timeframe = timeframe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }
}