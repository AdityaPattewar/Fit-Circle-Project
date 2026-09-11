package com.flexforce.model.admin;

public class Admin {
    private String adminId;
    private String name;
    private String email;
    private String profileImage;
    private String role;
    private String status;

    public Admin() {
    }

    public Admin(String adminId, String name, String email, String profileImage, String role, String status){
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.role = role;
        this.status = status;

    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

