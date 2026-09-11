package com.flexforce.controller;

import java.util.List;
import com.flexforce.dao.UserChallengeProgressDAO;
import com.flexforce.model.common_for_user_clubowner.UserChallengeProgress;

public class UserChallengeProgressController {

      private final UserChallengeProgressDAO progressDAO;

    public UserChallengeProgressController() {
        progressDAO = new UserChallengeProgressDAO();
    }

    // Save user challenge progress
    public void saveProgress(UserChallengeProgress progress) {

        if (progress == null) {
            System.out.println("Progress data cannot be null.");
            return;
        }

        progressDAO.saveProgress(progress);
    }

    // Get progress of a particular user for a particular challenge
    public UserChallengeProgress getProgress(
            String userId,
            String challengeId) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return null;
        }

        if (challengeId == null || challengeId.isEmpty()) {
            System.out.println("Challenge ID cannot be empty.");
            return null;
        }

        return progressDAO.getProgress(userId, challengeId);
    }

    // Update progress
    public void updateProgress(UserChallengeProgress progress) {

        if (progress == null) {
            System.out.println("Progress data cannot be null.");
            return;
        }

        progressDAO.updateProgress(progress);
    }

    // Delete progress
    public void deleteProgress(
            String userId,
            String challengeId) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return;
        }

        if (challengeId == null || challengeId.isEmpty()) {
            System.out.println("Challenge ID cannot be empty.");
            return;
        }

        progressDAO.deleteProgress(userId, challengeId);
    }

    // Get all challenge progress records
    public List<UserChallengeProgress> getAllProgress() {

        return progressDAO.getProgressList();
    }
}
    

