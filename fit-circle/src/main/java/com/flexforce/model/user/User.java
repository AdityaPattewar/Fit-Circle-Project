package com.flexforce.model.user;

public class User {

    private String userId;
    private String fullName;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private String address;
    private String workoutDays;
    private String height;
    private String weight;
    private String goal;
    private String plan;
    private String status;
    private String joiningDate;

    // ============================================================
    // EMPTY CONSTRUCTOR
    // ============================================================

    public User() {
    }

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public User(
            String userId,
            String fullName,
            String email,
            String phone,
            String dob,
            String gender,
            String address,
            String workoutDays,
            String height,
            String weight,
            String goal,
            String plan,
            String status,
            String joiningDate) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.workoutDays = workoutDays;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.plan = plan;
        this.status = status;
        this.joiningDate = joiningDate;
    }

    // ============================================================
    // GETTERS / SETTERS
    // ============================================================

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getWorkoutDays() {
        return workoutDays;
    }

    public void setWorkoutDays(String workoutDays) {
        this.workoutDays = workoutDays;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    // ============================================================
    // TO STRING
    // ============================================================

    @Override
    public String toString() {

        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", workoutDays='" + workoutDays + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", goal='" + goal + '\'' +
                ", plan='" + plan + '\'' +
                ", status='" + status + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }
}