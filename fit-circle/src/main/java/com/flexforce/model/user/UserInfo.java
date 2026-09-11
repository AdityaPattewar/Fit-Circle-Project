package com.flexforce.model.user;

public class UserInfo {
    private String userId;

    private String name;
    private String email;
    private String phone;
    private String profileImage;
    private String dateOfBirth;
    private String gender;
    private String address;

    private double height;
    private double weight;
    private String goal;
    private int workoutDays;

    private String role;
    private String status;

    private String plan;
    private String membershipStartDate;

    public UserInfo() {
    }

    public UserInfo(String userId, String name, String email, String phone, String profileImage, String dateofBirth, String gender,
            String address, double height, double weight, String goal, int workoutDays,
            String role, String status, String plan, String memebershipStartDate) {

                this.userId = userId;
                this.name = name;
                this.email = email;
                this.phone = phone;
                this.profileImage = profileImage;
                this.dateOfBirth = dateofBirth;
                this.gender = gender;
                this.address = address;
                this.height = height;
                this.weight = weight;
                this.goal = goal;
                this.workoutDays = workoutDays;
                this.role = role;
                this.status = status;
                this.plan = plan;
                this.membershipStartDate = membershipStartDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
    return address;
    }

    public void setAddress(String address) {
    this.address = address;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getWorkoutDays() {
        return workoutDays;
    }

    public void setWorkoutDays(int workoutDays) {
        this.workoutDays = workoutDays;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getmembershipStartDate() {
        return membershipStartDate;
    }

    public void setmembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

}

