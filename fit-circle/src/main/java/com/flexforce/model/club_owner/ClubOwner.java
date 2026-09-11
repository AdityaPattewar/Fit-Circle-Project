package com.flexforce.model.club_owner;
import java.util.List;



public class ClubOwner {
  // =========================================================
    // BASIC ACCOUNT INFORMATION
    // =========================================================

    private String ownerId;

    private String name;
    private String email;
    private String phone;
    private String profileImage;

    private String role;
    private String status;


    // =========================================================
    // CLUB PROFILE INFORMATION
    // =========================================================

    private String clubName;
    private String category;
    private String description;
    private String address;
    private String website;


    // =========================================================
    // CLUB OPERATIONAL INFORMATION
    // =========================================================

    private String operatingHours;
    private String price;


    // =========================================================
    // CLUB STATISTICS
    // =========================================================

    private int maxCapacity;
    private int activeTrainers;
    private double rating;


    // =========================================================
    // AMENITIES
    // =========================================================

    private List<String> amenities;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // REQUIRED BY FIRESTORE
    // =========================================================

    public ClubOwner() {
    }


    // =========================================================
    // EXISTING CONSTRUCTOR
    // KEEPING THIS SO REGISTRATION CODE DOES NOT BREAK
    // =========================================================

    public ClubOwner(
            String ownerId,
            String name,
            String email,
            String phone,
            String profileImage,
            String role,
            String status) {

        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
        this.role = role;
        this.status = status;
    }


    // =========================================================
    // OWNER ID
    // =========================================================

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }


    // =========================================================
    // NAME
    // =========================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // =========================================================
    // EMAIL
    // =========================================================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // =========================================================
    // PHONE
    // =========================================================

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // =========================================================
    // PROFILE IMAGE
    // =========================================================

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    // =========================================================
    // ROLE
    // =========================================================

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // =========================================================
    // STATUS
    // =========================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // =========================================================
    // CLUB NAME
    // =========================================================

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    // =========================================================
    // CATEGORY
    // =========================================================

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    // =========================================================
    // DESCRIPTION
    // =========================================================

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // =========================================================
    // ADDRESS
    // =========================================================

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // =========================================================
    // WEBSITE
    // =========================================================

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    // =========================================================
    // OPERATING HOURS
    // =========================================================

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }


    // =========================================================
    // PRICE
    // =========================================================

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    // =========================================================
    // MAX CAPACITY
    // =========================================================

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }


    // =========================================================
    // ACTIVE TRAINERS
    // =========================================================

    public int getActiveTrainers() {
        return activeTrainers;
    }

    public void setActiveTrainers(int activeTrainers) {
        this.activeTrainers = activeTrainers;
    }


    // =========================================================
    // RATING
    // =========================================================

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    // =========================================================
    // AMENITIES
    // =========================================================

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}