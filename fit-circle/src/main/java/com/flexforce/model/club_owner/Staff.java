package com.flexforce.model.club_owner;



public class Staff {

    private String staffId;
    private String clubId;
    private String name;
    private String role;
    private String email;
    private String phone;
    private String status;

    // =========================================
    // DEFAULT CONSTRUCTOR
    // =========================================

    public Staff() {
    }

    // =========================================
    // PARAMETERIZED CONSTRUCTOR
    // =========================================

    public Staff(
            String staffId,
            String clubId,
            String name,
            String role,
            String email,
            String phone,
            String status
    ) {
        this.staffId = staffId;
        this.clubId = clubId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    // =========================================
    // GETTERS
    // =========================================

    public String getStaffId() {
        return staffId;
    }

    public String getClubId() {
        return clubId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =========================================
    // TO STRING
    // =========================================

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", clubId='" + clubId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
