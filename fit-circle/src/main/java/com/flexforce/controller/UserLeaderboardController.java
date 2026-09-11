
package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.UserLeaderboardDAO;
import com.flexforce.model.user.UserLeaderboard;

public class UserLeaderboardController {

    // ============================================================
    // DAO
    // ============================================================

    private UserLeaderboardDAO leaderboardDAO;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public UserLeaderboardController() {

        // IMPORTANT:
        // this.leaderboardDAO म्हणजे class मधला field
        this.leaderboardDAO = new UserLeaderboardDAO();

        System.out.println(
                "UserLeaderboardDAO initialized successfully"
        );
    }

    // ============================================================
    // CREATE
    // ============================================================

    public void addUserLeaderboard(
            UserLeaderboard leaderboard) {

        if (leaderboard == null) {
            System.out.println(
                    "Leaderboard data is null"
            );
            return;
        }

        leaderboardDAO.saveUserLeaderboard(
                leaderboard
        );
    }

    // ============================================================
    // READ ONE
    // ============================================================

    public UserLeaderboard getUserLeaderboard(
            String userId) {

        if (userId == null ||
                userId.trim().isEmpty()) {

            System.out.println(
                    "User ID is null or empty"
            );

            return null;
        }

        return leaderboardDAO.getUserLeaderboard(
                userId
        );
    }

    // ============================================================
    // UPDATE
    // ============================================================

    public void updateUserLeaderboard(
            UserLeaderboard leaderboard) {

        if (leaderboard == null) {
            System.out.println(
                    "Leaderboard data is null"
            );
            return;
        }

        leaderboardDAO.updateUserLeaderboard(
                leaderboard
        );
    }

    // ============================================================
    // DELETE
    // ============================================================

    public void deleteUserLeaderboard(
            String userId) {

        if (userId == null ||
                userId.trim().isEmpty()) {

            System.out.println(
                    "User ID is null or empty"
            );

            return;
        }

        leaderboardDAO.deleteUserLeaderboard(
                userId
        );
    }

    // ============================================================
    // READ ALL
    // ============================================================

    public List<UserLeaderboard> getAllUserLeaderboards() {

        return leaderboardDAO
                .getUserLeaderboards();
    }
}

