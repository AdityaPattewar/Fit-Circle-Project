package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.UserDashboardDAO;
import com.flexforce.model.user.UserDashboard;

public class UserDashboardController {

    private final UserDashboardDAO dashboardDAO;

    public UserDashboardController() {
        dashboardDAO = new UserDashboardDAO();
    }

    // Save dashboard data
    public void saveDashboard(String userId, UserDashboard dashboard) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return;
        }

        if (dashboard == null) {
            System.out.println("Dashboard data cannot be null.");
            return;
        }

        dashboardDAO.saveDashboard(userId, dashboard);
    }

    // Get dashboard data of one user
    public UserDashboard getDashboard(String userId) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return null;
        }

        return dashboardDAO.getDashboard(userId);
    }

    // Update dashboard data
    public void updateDashboard(
            String userId,
            UserDashboard dashboard) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return;
        }

        if (dashboard == null) {
            System.out.println("Dashboard data cannot be null.");
            return;
        }

        dashboardDAO.updateDashboard(userId, dashboard);
    }

    // Delete dashboard
    public void deleteDashboard(String userId) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return;
        }

        dashboardDAO.deleteDashboard(userId);
    }

    // Get all dashboards
    public List<UserDashboard> getAllDashboards() {

        return dashboardDAO.getDashboards();
    }
}