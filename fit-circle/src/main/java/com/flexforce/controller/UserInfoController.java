
package com.flexforce.controller;

import com.flexforce.dao.UserInfoDAO;
import com.flexforce.model.user.UserInfo;

import javafx.scene.control.TextField;

public class UserInfoController {

    private final UserInfoDAO userInfoDAO;

    public UserInfoController() {
        userInfoDAO = new UserInfoDAO();
    }

    // =====================================================
    // GET CURRENT LOGGED-IN USER ID
    // =====================================================

    public String getCurrentUserId() {

        return AuthControllerlogin.getCurrentUserId();
    }


    // =====================================================
    // LOAD CURRENT USER INFORMATION
    // =====================================================

    public UserInfo loadUserInfo() {

        String userId = getCurrentUserId();

        if (userId == null || userId.isEmpty()) {

            System.out.println("No user is currently logged in.");

            return null;
        }

        return userInfoDAO.getUserInfo(userId);
    }


    // =====================================================
    // LOAD USER INFORMATION INTO UI
    // =====================================================

    public boolean loadUserInfoIntoFields(

            TextField fullNameField,
            TextField emailField,
            TextField phoneField,
            TextField dobField,
            TextField genderField,
            TextField addressField,
            TextField heightField,
            TextField weightField,
            TextField goalField,
            TextField planField,
            TextField statusField,
            TextField joiningDateField,
            TextField workoutDaysField) {

        UserInfo userInfo = loadUserInfo();

        if (userInfo == null) {

            System.out.println(
                    "User information not found."
            );

            return false;
        }

        fullNameField.setText(
                safe(userInfo.getName())
        );

        emailField.setText(
                safe(userInfo.getEmail())
        );

        phoneField.setText(
                safe(userInfo.getPhone())
        );

        dobField.setText(
                safe(userInfo.getDateOfBirth())
        );

        genderField.setText(
                safe(userInfo.getGender())
        );

        addressField.setText(
                safe(userInfo.getAddress())
        );

        heightField.setText(
                String.valueOf(
                        userInfo.getHeight()
                )
        );

        weightField.setText(
                String.valueOf(
                        userInfo.getWeight()
                )
        );

        goalField.setText(
                safe(userInfo.getGoal())
        );

        planField.setText(
                safe(userInfo.getPlan())
        );

        statusField.setText(
                safe(userInfo.getStatus())
        );

        joiningDateField.setText(
                safe(
                        userInfo.getmembershipStartDate()
                )
        );

        workoutDaysField.setText(
                String.valueOf(
                        userInfo.getWorkoutDays()
                )
        );

        System.out.println(
                "User information loaded successfully."
        );

        return true;
    }


    // =====================================================
    // UPDATE USER INFORMATION
    // =====================================================

    public boolean updateUserInfo(

            TextField fullNameField,
            TextField emailField,
            TextField phoneField,
            TextField dobField,
            TextField genderField,
            TextField addressField,
            TextField heightField,
            TextField weightField,
            TextField goalField,
            TextField planField,
            TextField statusField,
            TextField joiningDateField,
            TextField workoutDaysField) {

        String userId = getCurrentUserId();

        if (userId == null || userId.isEmpty()) {

            System.out.println(
                    "No logged-in user found."
            );

            return false;
        }

        try {

            double height =
                    Double.parseDouble(
                            heightField.getText()
                    );

            double weight =
                    Double.parseDouble(
                            weightField.getText()
                    );

            int workoutDays =
                    Integer.parseInt(
                            workoutDaysField.getText()
                    );

            UserInfo userInfo =
                    new UserInfo();

            userInfo.setUserId(userId);

            userInfo.setName(
                    fullNameField.getText()
            );

            userInfo.setEmail(
                    emailField.getText()
            );

            userInfo.setPhone(
                    phoneField.getText()
            );

            userInfo.setDateOfBirth(
                    dobField.getText()
            );

            userInfo.setGender(
                    genderField.getText()
            );

            userInfo.setAddress(
                    addressField.getText()
            );

            userInfo.setHeight(height);

            userInfo.setWeight(weight);

            userInfo.setGoal(
                    goalField.getText()
            );

            userInfo.setWorkoutDays(
                    workoutDays
            );

            userInfo.setPlan(
                    planField.getText()
            );

            userInfo.setStatus(
                    statusField.getText()
            );

            userInfo.setmembershipStartDate(
                    joiningDateField.getText()
            );

            userInfoDAO.updateUserInfo(
                    userInfo
            );

            System.out.println(
                    "User profile updated successfully."
            );

            return true;

        } catch (NumberFormatException e) {

            System.out.println(
                    "Height, weight and workout days " +
                    "must contain valid numbers."
            );

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // SAVE NEW USER INFORMATION
    // =====================================================

    public boolean saveUserInfo(UserInfo userInfo) {

        if (userInfo == null) {
            return false;
        }

        String userId =
                getCurrentUserId();

        if (userId == null || userId.isEmpty()) {

            System.out.println(
                    "No logged-in user found."
            );

            return false;
        }

        userInfo.setUserId(userId);

        userInfoDAO.saveUserInfo(
                userInfo
        );

        return true;
    }


    // =====================================================
    // DELETE USER INFORMATION
    // =====================================================

    public boolean deleteUserInfo() {

        String userId =
                getCurrentUserId();

        if (userId == null || userId.isEmpty()) {

            System.out.println(
                    "No logged-in user found."
            );

            return false;
        }

        userInfoDAO.deleteUserInfo(
                userId
        );

        return true;
    }


    // =====================================================
    // HELPER METHOD
    // =====================================================

    private String safe(String value) {

        return value == null
                ? ""
                : value;
    }
}