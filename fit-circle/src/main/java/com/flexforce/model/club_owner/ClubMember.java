package com.flexforce.model.club_owner;

public class ClubMember {

    private String memberId;
    private String name;
    private String email;
    private String phone;
    private String membership;
    private String joinDate;
    private String status;
    private String clubId;

    public ClubMember() {
    }

    public ClubMember(
            String memberId,
            String name,
            String email,
            String phone,
            String membership,
            String joinDate,
            String status,
            String clubId) {

        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membership = membership;
        this.joinDate = joinDate;
        this.status = status;
        this.clubId = clubId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}