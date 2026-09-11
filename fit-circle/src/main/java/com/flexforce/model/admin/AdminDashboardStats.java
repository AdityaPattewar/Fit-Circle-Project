package com.flexforce.model.admin;

public class AdminDashboardStats {
   
   
    private int totalClubs;

    private int activeUsers;

    private int totalBookings;

    private double totalRevenue;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminDashboardStats(
            int totalClubs,
            int activeUsers,
            int totalBookings,
            double totalRevenue) {

        this.totalClubs =
                totalClubs;

        this.activeUsers =
                activeUsers;

        this.totalBookings =
                totalBookings;

        this.totalRevenue =
                totalRevenue;
    }


    // =====================================================
    // GET TOTAL CLUBS
    // =====================================================

    public int getTotalClubs() {

        return totalClubs;
    }


    // =====================================================
    // GET ACTIVE USERS
    // =====================================================

    public int getActiveUsers() {

        return activeUsers;
    }


    // =====================================================
    // GET TOTAL BOOKINGS
    // =====================================================

    public int getTotalBookings() {

        return totalBookings;
    }


    // =====================================================
    // GET TOTAL REVENUE
    // =====================================================

    public double getTotalRevenue() {

        return totalRevenue;
    }
}