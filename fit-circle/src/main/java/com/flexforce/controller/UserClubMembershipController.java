package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;

public class UserClubMembershipController {

    private final UserClubMembershipDAO membershipDAO;

    public UserClubMembershipController() {
        membershipDAO = new UserClubMembershipDAO();
    }

    // Save membership data
    public void saveMembership(UserClubMembership membership) {

        if (membership == null) {
            System.out.println("Membership data cannot be null.");
            return;
        }

        if (membership.getUserId() == null ||
            membership.getUserId().isEmpty()) {

            System.out.println("User ID cannot be empty.");
            return;
        }

        if (membership.getClubId() == null ||
            membership.getClubId().isEmpty()) {

            System.out.println("Club ID cannot be empty.");
            return;
        }

        membershipDAO.saveMembership(membership);
    }

    // Get membership of one user from one club
    public UserClubMembership getMembership(
            String userId,
            String clubId) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return null;
        }

        if (clubId == null || clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return null;
        }

        return membershipDAO.getMembership(userId, clubId);
    }

    // Update membership data
    public void updateMembership(
            UserClubMembership membership) {

        if (membership == null) {
            System.out.println("Membership data cannot be null.");
            return;
        }

        if (membership.getUserId() == null ||
            membership.getUserId().isEmpty()) {

            System.out.println("User ID cannot be empty.");
            return;
        }

        if (membership.getClubId() == null ||
            membership.getClubId().isEmpty()) {

            System.out.println("Club ID cannot be empty.");
            return;
        }

        membershipDAO.updateMembership(membership);
    }

    // Delete membership
    public void deleteMembership(
            String userId,
            String clubId) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID cannot be empty.");
            return;
        }

        if (clubId == null || clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return;
        }

        membershipDAO.deleteMembership(userId, clubId);
    }

    // Get all memberships
    public List<UserClubMembership> getAllMemberships() {

        return membershipDAO.getMemberships();
    }
}