package com.flexforce.model.user;

public class UserDashboard {
    private int currentStreak;
    private int totalPoints;
    private String currentRank;
    private int level;
    private int weeklyPoints;

    public UserDashboard(){

    }
    public UserDashboard(int currentStreak,int totalPoints, String currentRank, int level,int weeklyPoints){
        this.currentStreak= currentStreak;
        this.totalPoints = totalPoints;
        this.currentRank = currentRank;
        this.level = level;
        this.weeklyPoints = weeklyPoints;
    }
    public int getCurrentStreak() {
        return currentStreak;
    }
    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }
    public int getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    public String getCurrentRank() {
        return currentRank;
    }
    public void setCurrentRank(String currentRank) {
        this.currentRank = currentRank;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getWeeklyPoints() {
        return weeklyPoints;
    }
    public void setWeeklyPoints(int weeklyPoints) {
        this.weeklyPoints = weeklyPoints;
    }

}
