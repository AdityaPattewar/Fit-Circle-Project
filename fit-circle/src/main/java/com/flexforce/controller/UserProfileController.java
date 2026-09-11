package com.flexforce.controller;

import com.flexforce.dao.UserInfoDAO;
import com.flexforce.model.user.User;
import com.flexforce.model.user.UserInfo;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UserProfileController {

    private final UserInfoDAO userInfoDAO;

    private String currentUserId;

    public UserProfileController() {

        userInfoDAO = new UserInfoDAO();

        /*
         * Temporary default ID.
         *
         * Login झाल्यावर actual Firebase User ID इथे set करा:
         *
         * controller.setCurrentUserId(actualUserId);
         */
        currentUserId = AuthControllerlogin.getCurrentUserId();
    }

    // ============================================================
    // CURRENT USER ID
    // ============================================================

    public void setCurrentUserId(String userId) {

        if (userId != null && !userId.trim().isEmpty()) {
            currentUserId = userId.trim();
        }
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    // ============================================================
    // GET CURRENT USER
    // ============================================================

    public User getCurrentUser() {

        if (currentUserId == null ||
                currentUserId.trim().isEmpty()) {

            return null;
        }

        UserInfo userInfo = userInfoDAO.getUserInfo(currentUserId);
        if (userInfo == null) {
            // Fallback to old UserProfileDAO for testing or legacy accounts
            com.flexforce.dao.UserProfileDAO oldDao = new com.flexforce.dao.UserProfileDAO();
            User oldUser = oldDao.getUserProfile(currentUserId);
            if (oldUser != null) {
                return oldUser;
            }
            return null;
        }

        User user = new User();
        user.setUserId(userInfo.getUserId());
        user.setFullName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setPhone(userInfo.getPhone());
        user.setDob(userInfo.getDateOfBirth());
        user.setGender(userInfo.getGender());
        user.setAddress(userInfo.getAddress());
        user.setHeight(String.valueOf(userInfo.getHeight()));
        user.setWeight(String.valueOf(userInfo.getWeight()));
        user.setGoal(userInfo.getGoal());
        user.setPlan(userInfo.getPlan());
        user.setStatus(userInfo.getStatus());
        user.setJoiningDate(userInfo.getmembershipStartDate());
        user.setWorkoutDays(String.valueOf(userInfo.getWorkoutDays()));

        return user;
    }

    // ============================================================
    // GET INITIAL
    // ============================================================

    public String getInitial() {

        User user = getCurrentUser();

        if (user == null ||
                user.getFullName() == null ||
                user.getFullName().trim().isEmpty()) {

            return "U";
        }

        return user.getFullName()
                .trim()
                .substring(0, 1)
                .toUpperCase();
    }

    // ============================================================
    // GET MEMBERSHIP DAYS
    // ============================================================

    public String getMembershipDays() {

        User user = getCurrentUser();

        if (user == null ||
                user.getJoiningDate() == null ||
                user.getJoiningDate().trim().isEmpty()) {

            return "0 days";
        }

        String joiningDate =
                user.getJoiningDate().trim();

        try {

            LocalDate date;

            if (joiningDate.contains("/")) {

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy"
                        );

                date = LocalDate.parse(
                        joiningDate,
                        formatter
                );

            } else {

                date = LocalDate.parse(
                        joiningDate
                );
            }

            long days =
                    ChronoUnit.DAYS.between(
                            date,
                            LocalDate.now()
                    );

            if (days < 0) {
                days = 0;
            }

            return days + " days";

        } catch (Exception e) {

            System.out.println(
                    "Invalid joining date: " +
                            joiningDate
            );

            return "0 days";
        }
    }

    // ============================================================
    // LOAD USER PROFILE
    // ============================================================

    public void loadUserProfile(

            TextField fullNameField,
            TextField emailField,
            TextField phoneField,
            TextField dobField,
            ComboBox<String> genderField,
            TextField addressField,
            TextField heightField,
            TextField weightField,
            TextField goalField,
            TextField planField,
            TextField statusField,
            TextField joiningDateField,
            TextField workoutDaysField) {

        if (currentUserId == null ||
                currentUserId.trim().isEmpty()) {

            System.out.println(
                    "Current User ID is empty."
            );

            return;
        }

        User user = getCurrentUser();

        if (user == null) {

            System.out.println(
                    "User profile not found for ID: " +
                            currentUserId
            );

            return;
        }

        fullNameField.setText(
                safe(user.getFullName())
        );

        emailField.setText(
                safe(user.getEmail())
        );

        phoneField.setText(
                safe(user.getPhone())
        );

        dobField.setText(
                safe(user.getDob())
        );

        String gender =
                safe(user.getGender());

        if (!gender.isEmpty()) {

            if (!genderField.getItems().contains(gender)) {
                genderField.getItems().add(gender);
            }

            genderField.setValue(gender);
        }

        addressField.setText(
                safe(user.getAddress())
        );

        heightField.setText(
                safe(user.getHeight())
        );

        weightField.setText(
                safe(user.getWeight())
        );

        goalField.setText(
                safe(user.getGoal())
        );

        planField.setText(
                safe(user.getPlan())
        );

        statusField.setText(
                safe(user.getStatus())
        );

        joiningDateField.setText(
                safe(user.getJoiningDate())
        );

        workoutDaysField.setText(
                safe(user.getWorkoutDays())
        );

        System.out.println(
                "User profile loaded successfully."
        );
    }

    // ============================================================
    // UPDATE USER PROFILE
    // ============================================================

    public boolean updateUserProfile(

            TextField fullNameField,
            TextField emailField,
            TextField phoneField,
            TextField dobField,
            ComboBox<String> genderField,
            TextField addressField,
            TextField heightField,
            TextField weightField,
            TextField goalField,
            TextField planField,
            TextField statusField,
            TextField joiningDateField,
            TextField workoutDaysField) {

        try {

            if (currentUserId == null ||
                    currentUserId.trim().isEmpty()) {

                System.out.println(
                        "User ID not available."
                );

                return false;
            }

            UserInfo userInfo = userInfoDAO.getUserInfo(currentUserId);
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setUserId(currentUserId);
            }

            userInfo.setName(safe(fullNameField.getText()));
            userInfo.setEmail(safe(emailField.getText()));
            userInfo.setPhone(safe(phoneField.getText()));
            userInfo.setDateOfBirth(safe(dobField.getText()));

            String gender = "";
            if (genderField.getValue() != null) {
                gender = genderField.getValue();
            }
            userInfo.setGender(safe(gender));
            userInfo.setAddress(safe(addressField.getText()));

            try {
                if (!safe(heightField.getText()).isEmpty()) {
                    userInfo.setHeight(Double.parseDouble(safe(heightField.getText())));
                }
            } catch (Exception e) {}

            try {
                if (!safe(weightField.getText()).isEmpty()) {
                    userInfo.setWeight(Double.parseDouble(safe(weightField.getText())));
                }
            } catch (Exception e) {}

            userInfo.setGoal(safe(goalField.getText()));
            userInfo.setPlan(safe(planField.getText()));
            userInfo.setStatus(safe(statusField.getText()));
            userInfo.setmembershipStartDate(safe(joiningDateField.getText()));

            try {
                if (!safe(workoutDaysField.getText()).isEmpty()) {
                    userInfo.setWorkoutDays(Integer.parseInt(safe(workoutDaysField.getText())));
                }
            } catch (Exception e) {}

            userInfoDAO.updateUserInfo(userInfo);

            boolean result = true;

            if (result) {

                System.out.println(
                        "User profile updated successfully."
                );

            } else {

                System.out.println(
                        "User profile update failed."
                );
            }

            return result;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ============================================================
    // SAFE
    // ============================================================

    private String safe(String value) {

        return value == null
                ? ""
                : value.trim();
    }
}