package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.UserChallengeDAO;
import com.flexforce.model.common_for_user_clubowner.UserChallenge;

public class UserChallengeController {

    private final UserChallengeDAO challengeDAO;


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public UserChallengeController() {

        challengeDAO =
                new UserChallengeDAO();
    }


    // ============================================================
    // SAVE
    // ============================================================

    public void saveChallenge(
            UserChallenge challenge) {

        if (challenge == null) {

            System.out.println(
                    "Challenge data cannot be null."
            );

            return;
        }

        challengeDAO.saveChallenge(
                challenge
        );
    }


    // ============================================================
    // GET ONE
    // ============================================================

    public UserChallenge getChallenge(
            String challengeId) {

        if (challengeId == null ||
                challengeId.trim().isEmpty()) {

            System.out.println(
                    "Challenge ID cannot be empty."
            );

            return null;
        }

        return challengeDAO.getChallenge(
                challengeId
        );
    }


    // ============================================================
    // UPDATE
    // ============================================================

    public void updateChallenge(
            UserChallenge challenge) {

        if (challenge == null) {
            System.out.println("Challenge data cannot be null.");
            return;
        }

        challengeDAO.updateChallenge(
                challenge
        );
    }


    // ============================================================
    // DELETE
    // ============================================================

    public void deleteChallenge(
            String challengeId) {

        if (challengeId == null ||
                challengeId.trim().isEmpty()) {

            System.out.println(
                    "Challenge ID cannot be empty."
            );

            return;
        }

        challengeDAO.deleteChallenge(
                challengeId
        );
    }


    // ============================================================
    // GET ALL
    // ============================================================

    public List<UserChallenge> getAllChallenges() {

        return challengeDAO.getChallenges();
    }
}