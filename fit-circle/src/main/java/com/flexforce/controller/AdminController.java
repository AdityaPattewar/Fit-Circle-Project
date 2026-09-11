package com.flexforce.controller;

import com.flexforce.dao.AdminDAO;
import com.flexforce.model.admin.Admin;
import com.flexforce.view.admin.AdminDashboard;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AdminController {

    private AdminDAO adminDAO;

    public AdminController() {

       AdminDAO adminDAO = new AdminDAO();
    }

    // ============================================================
    // ADMIN LOGIN
    // ============================================================

    public void login(String email, String password, Stage stage) {

        try {

            // ----------------------------------------------------
            // 1. Check Email
            // ----------------------------------------------------

            if (email == null || email.trim().isEmpty()) {

                showAlert(
                        "Login Error",
                        "Please enter your email."
                );

                return;
            }


            // ----------------------------------------------------
            // 2. Check Password
            // ----------------------------------------------------

            if (password == null || password.trim().isEmpty()) {

                showAlert(
                        "Login Error",
                        "Please enter your password."
                );

                return;
            }


            // ----------------------------------------------------
            // 3. Firebase Authentication
            // ----------------------------------------------------

            boolean authenticated =
                    AuthControllerAdmin.login(
                            email.trim(),
                            password
                    );


            if (!authenticated) {

                showAlert(
                        "Login Failed",
                        "Invalid email or password."
                );

                return;
            }


            // ----------------------------------------------------
            // 4. Get Admin from Firestore
            // ----------------------------------------------------

            Admin admin = adminDAO.getAdmin(email.trim());


            // ----------------------------------------------------
            // 5. Check Admin Exists
            // ----------------------------------------------------

            if (admin == null) {

                showAlert(
                        "Access Denied",
                        "Admin account not found."
                );

                return;
            }


            // ----------------------------------------------------
            // 6. Check Admin Role
            // ----------------------------------------------------

            if (admin.getRole() == null ||
                    !admin.getRole().equalsIgnoreCase("admin")) {

                showAlert(
                        "Access Denied",
                        "You are not authorized as an admin."
                );

                return;
            }


            // ----------------------------------------------------
            // 7. Check Admin Status
            // ----------------------------------------------------

            if (admin.getStatus() == null ||
                    !admin.getStatus().equalsIgnoreCase("active")) {

                showAlert(
                        "Access Denied",
                        "Your admin account is not active."
                );

                return;
            }


            // ----------------------------------------------------
            // 8. Login Successful
            // ----------------------------------------------------

            System.out.println("Admin Login Successful");
            System.out.println( "Admin ID: " + admin.getAdminId());

            System.out.println("Admin Name: " + admin.getName());
            System.out.println("Admin Email: " + admin.getEmail());


            // ----------------------------------------------------
            // 9. Open Admin Dashboard
            // ----------------------------------------------------

            AdminDashboard dashboard = new AdminDashboard();
            dashboard.start(stage, () -> {
                try {

                    new com.flexforce.view.admin.Admin_login()
                            .start(stage);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            });


        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    "Error",
                    "Something went wrong during admin login."
            );
        }
    }


    // ============================================================
    // ALERT
    // ============================================================

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}