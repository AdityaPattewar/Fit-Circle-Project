package com.flexforce.model.club_owner;



public class ClubOwnerDashboard {

    private int totalMembers;
    private int upcomingEvents;
    private int activeActivities;
    private double monthlyEngagement;

    public ClubOwnerDashboard() {
    }

    public ClubOwnerDashboard(int totalMembers, int upcomingEvents,
                              int activeActivities, double monthlyEngagement) {
        this.totalMembers = totalMembers;
        this.upcomingEvents = upcomingEvents;
        this.activeActivities = activeActivities;
        this.monthlyEngagement = monthlyEngagement;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getUpcomingEvents() {
        return upcomingEvents;
    }

    public void setUpcomingEvents(int upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
    }

    public int getActiveActivities() {
        return activeActivities;
    }

    public void setActiveActivities(int activeActivities) {
        this.activeActivities = activeActivities;
    }

    public double getMonthlyEngagement() {
        return monthlyEngagement;
    }

    public void setMonthlyEngagement(double monthlyEngagement) {
        this.monthlyEngagement = monthlyEngagement;
    }
}
