package com.flexforce.controller;

import java.util.List;
import com.flexforce.dao.ClubOwnerDashboardDAO;
import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.dao.UserEventDAO;
import com.flexforce.model.club_owner.ClubOwnerDashboard;


public class ClubOwnerDashboardController {

     private final ClubOwnerDashboardDAO dashboardDAO;
     private final UserClubMembershipDAO membershipDAO;
     private final UserEventDAO eventDAO;

    public ClubOwnerDashboardController() {
         dashboardDAO = new ClubOwnerDashboardDAO();
         membershipDAO = new UserClubMembershipDAO();
         eventDAO = new UserEventDAO();
    }

    // Save dashboard data
    public void saveDashboard(String ownerId, ClubOwnerDashboard dashboard) {

        if (ownerId == null || ownerId.isEmpty()) {
            System.out.println("Owner ID cannot be empty.");
            return;
        }

        if (dashboard == null) {
            System.out.println("Dashboard data cannot be null.");
            return;
        }

        dashboardDAO.saveDashboard(ownerId, dashboard);
    }

    // Get dashboard data of one owner
    public ClubOwnerDashboard getDashboard(String ownerId) {

        if (ownerId == null || ownerId.isEmpty()) {
            System.out.println("Owner ID cannot be empty.");
            return null;
        }

        return dashboardDAO.getDashboard(ownerId);
    }

    // Update dashboard data
    public void updateDashboard(String ownerId, ClubOwnerDashboard dashboard) {

        if (ownerId == null || ownerId.isEmpty()) {
            System.out.println("Owner ID cannot be empty.");
            return;
        }

        if (dashboard == null) {
            System.out.println("Dashboard data cannot be null.");
            return;
        }

        dashboardDAO.updateDashboard(ownerId, dashboard);
    }

    // Delete dashboard
    public void deleteDashboard(String ownerId) {

        if (ownerId == null || ownerId.isEmpty()) {
            System.out.println("Owner ID cannot be empty.");
            return;
        }

        dashboardDAO.deleteDashboard(ownerId);
    }

    // Get all dashboards
    public List<ClubOwnerDashboard> getAllDashboards() {

        return dashboardDAO.getDashboards();
    }

    // =========================================================
    // GET TOTAL MEMBERS BY OWNER
    // =========================================================

    public int getTotalMembers(String ownerId) {

        if (ownerId == null || ownerId.trim().isEmpty()) {
            return 0;
        }

        String clubId = eventDAO.getClubIdByOwner(ownerId);

        if (clubId == null || clubId.trim().isEmpty()) {
            return 0;
        }

        return membershipDAO
                .getMembershipsByClub(clubId)
                .size();
}
}
    

