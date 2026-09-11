/*package com.flexforce.view.club_owner;

import java.util.List;

import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;

public class UserClubMembershipController {

    private final UserClubMembershipDAO membershipDAO;

    public UserClubMembershipController() {

        membershipDAO =
                new UserClubMembershipDAO();
    }

    // =========================================================
    // SAVE MEMBERSHIP
    // =========================================================

    public boolean saveMembership(
            UserClubMembership membership) {

        if (membership == null) {

            System.out.println(
                    "Membership data cannot be null."
            );

            return false;
        }

        if (membership.getUserId() == null ||
                membership.getUserId().trim().isEmpty()) {

            System.out.println(
                    "User ID cannot be empty."
            );

            return false;
        }

        if (membership.getClubId() == null ||
                membership.getClubId().trim().isEmpty()) {

            System.out.println(
                    "Club ID cannot be empty."
            );

            return false;
        }

        return membershipDAO.saveMembership(
                membership
        );
    }

    // =========================================================
    // GET ONE
    // =========================================================

    public UserClubMembership getMembership(
            String userId,
            String clubId) {

        if (userId == null ||
                userId.trim().isEmpty()) {

            return null;
        }

        if (clubId == null ||
                clubId.trim().isEmpty()) {

            return null;
        }

        return membershipDAO.getMembership(
                userId,
                clubId
        );
    }

    // =========================================================
    // GET MEMBERS OF CURRENT CLUB
    // =========================================================

    public List<UserClubMembership>
    getMembershipsByClub(String clubId) {

        if (clubId == null ||
                clubId.trim().isEmpty()) {

            return List.of();
        }

        return membershipDAO
                .getMembershipsByClub(clubId);
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<UserClubMembership>
    getAllMemberships() {

        return membershipDAO.getMemberships();
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateMembership(
            UserClubMembership membership) {

        if (membership == null) {

            return false;
        }

        return membershipDAO.updateMembership(
                membership
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteMembership(
            String userId,
            String clubId) {

        if (userId == null ||
                userId.trim().isEmpty()) {

            return false;
        }

        if (clubId == null ||
                clubId.trim().isEmpty()) {

            return false;
        }

        return membershipDAO.deleteMembership(
                userId,
                clubId
        );
    }
}*/