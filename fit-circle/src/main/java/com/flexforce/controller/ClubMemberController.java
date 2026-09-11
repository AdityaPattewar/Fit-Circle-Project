package com.flexforce.controller;



import java.util.List;

import com.flexforce.dao.ClubMemberDAO;
import com.flexforce.model.club_owner.ClubMember;

public class ClubMemberController {

    private final ClubMemberDAO memberDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ClubMemberController() {

        memberDAO =
                new ClubMemberDAO();
    }


    // =========================================================
    // CREATE
    // =========================================================

    public void addMember(
            ClubMember member) {

        memberDAO.saveMember(member);
    }


    // =========================================================
    // GET ONE
    // =========================================================

    public ClubMember getMember(
            String memberId) {

        return memberDAO.getMember(
                memberId
        );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    public List<ClubMember> getAllMembers() {

        return memberDAO.getMembers();
    }


    // =========================================================
    // GET BY CLUB
    // =========================================================

    public List<ClubMember> getMembersByClubId(
            String clubId) {

        return memberDAO.getMembersByClubId(
                clubId
        );
    }


    // =========================================================
    // UPDATE
    // =========================================================

    public void updateMember(
            ClubMember member) {

        memberDAO.updateMember(
                member
        );
    }


    // =========================================================
    // DELETE
    // =========================================================

    public void deleteMember(
            String memberId) {

        memberDAO.deleteMember(
                memberId
        );
    }
}
