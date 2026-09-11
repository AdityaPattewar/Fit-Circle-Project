package com.flexforce.view.admin;

import com.flexforce.controller.AdminController;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Admin_login extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // =====================================================
        // LOGO
        // =====================================================

        Label dumbbell = new Label("✕");

        dumbbell.setTextFill(
                Color.web("#bfff00")
        );

        dumbbell.setStyle(
                "-fx-text-fill: #bfff00;" +
                "-fx-font-size: 35px;" +
                "-fx-font-weight: bold"
        );

        // -----------------------------------------------------

        Label logoText = new Label("FitCircle");

        logoText.setTextFill(
                Color.WHITE
        );

        logoText.setStyle(
                "-fx-text-fill: #18b35e;" +
                "-fx-font-size: 35px;" +
                "-fx-font-weight: bold"
        );

        // -----------------------------------------------------

        HBox logo = new HBox(
                6,
                dumbbell,
                logoText
        );

        logo.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // ADMIN PORTAL
        // =====================================================

        Label adminPortal =
                new Label("ADMIN PORTAL");

        adminPortal.setStyle(
                "-fx-text-fill: #bfff00;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15px"
        );

        // =====================================================
        // SPACE
        // =====================================================

        Region space = new Region();

        space.setMinHeight(20);
        space.setPrefHeight(20);
        space.setMaxHeight(20);

        // =====================================================
        // WELCOME
        // =====================================================

        Label welcome =
                new Label("Welcome Back, Admin");

        welcome.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 25px"
        );

        // -----------------------------------------------------

        Label subtitle =
                new Label("Sign in to manage FitCircle");

        subtitle.setStyle(
                "-fx-text-fill: #999999;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 18px;"
        );

        // -----------------------------------------------------

        Region spacing = new Region();

        spacing.setMinHeight(50);
        spacing.setPrefHeight(50);
        spacing.setMaxHeight(50);

        // =====================================================
        // WELCOME SECTION
        // =====================================================

        VBox welcomeSection = new VBox(
                5,
                logo,
                adminPortal,
                space,
                welcome,
                subtitle,
                spacing
        );

        welcomeSection.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                new Label("Email Address");

        emailLabel.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 11px;"
        );

        // -----------------------------------------------------

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        emailField.setPrefHeight(36);

        emailField.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 6;" +
                "-fx-border-radius: 6;" +
                "-fx-border-color: #cccccc;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 10px;"
        );

        // -----------------------------------------------------

        Region spacemail = new Region();

        spacemail.setMinHeight(20);
        spacemail.setPrefHeight(20);
        spacemail.setMaxHeight(20);

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 11px;"
        );

        // -----------------------------------------------------

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefHeight(36);

        passwordField.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 6;" +
                "-fx-border-radius: 6;" +
                "-fx-border-color: #cccccc;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 10px"
        );

        // -----------------------------------------------------

        Region spacepass = new Region();

        spacepass.setMinHeight(20);
        spacepass.setPrefHeight(20);
        spacepass.setMaxHeight(20);

        // =====================================================
        // REMEMBER ME
        // =====================================================

        CheckBox checkbox =
                new CheckBox("Remember me");

        checkbox.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10px;"
        );

        // =====================================================
        // PASSWORD BOX
        // =====================================================

        VBox passwordBox = new VBox(
                6,
                emailLabel,
                emailField,
                spacemail,
                passwordLabel,
                passwordField,
                spacepass,
                checkbox
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button("Login as Admin");

        loginButton.setPrefWidth(276);
        loginButton.setPrefHeight(35);

        loginButton.setStyle(
                "-fx-background-color: #B6FF00;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand"
        );

        // =====================================================
        // LOADER
        // =====================================================

        ProgressIndicator loader =
                new ProgressIndicator();

        loader.setPrefSize(
                20,
                20
        );

        loader.setVisible(false);
        loader.setManaged(false);

        // =====================================================
        // LOGIN BUTTON + LOADER
        // =====================================================

        HBox loginBox = new HBox(
                10,
                loader,
                loginButton
        );

        loginBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // LOGIN ACTION
        // =====================================================

        loginButton.setOnAction(e -> {

            String email =
                    emailField.getText().trim();

            String password =
                    passwordField.getText();

            // =================================================
            // VALIDATION
            // =================================================

            if (email.isEmpty()) {

                System.out.println(
                        "Please enter email."
                );

                return;
            }

            if (password.isEmpty()) {

                System.out.println(
                        "Please enter password."
                );

                return;
            }

            // =================================================
            // SHOW LOADER
            // =================================================

            loginButton.setDisable(true);

            loginButton.setText(
                    "Logging in..."
            );

            loader.setVisible(true);
            loader.setManaged(true);

            // =================================================
            // BACKGROUND TASK
            // =================================================

            Task<Boolean> loginTask =
                    new Task<Boolean>() {

                @Override
                protected Boolean call()
                        throws Exception {

                    System.out.println(
                            "ADMIN LOGIN STARTED"
                    );

                    AdminController controller =
                            new AdminController();

                    /*
                     * Your existing AdminController login
                     * method is used here.
                     *
                     * IMPORTANT:
                     * AdminController.login() currently
                     * receives the Stage.
                     */

                    controller.login(
                            email,
                            password,
                            stage
                    );

                    /*
                     * Since your current controller.login()
                     * handles navigation internally, we return
                     * true after the call completes.
                     */

                    return true;
                }
            };

            // =================================================
            // LOGIN SUCCESS
            // =================================================

            loginTask.setOnSucceeded(event -> {

                loader.setVisible(false);
                loader.setManaged(false);

                loginButton.setDisable(false);

                loginButton.setText(
                        "Login as Admin"
                );

                System.out.println(
                        "ADMIN LOGIN COMPLETED"
                );
            });

            // =================================================
            // LOGIN FAILED
            // =================================================

            loginTask.setOnFailed(event -> {

                loader.setVisible(false);
                loader.setManaged(false);

                loginButton.setDisable(false);

                loginButton.setText(
                        "Login as Admin"
                );

                Throwable error =
                        loginTask.getException();

                if (error != null) {

                    error.printStackTrace();
                }

                System.out.println(
                        "Admin login failed."
                );
            });

            // =================================================
            // START TASK
            // =================================================

            Thread loginThread =
                    new Thread(loginTask);

            loginThread.setDaemon(true);

            loginThread.start();
        });

        // =====================================================
        // FULL FORM
        // =====================================================

        VBox fullvb = new VBox(
                20,
                welcomeSection,
                passwordBox,
                loginBox
        );

        fullvb.setAlignment(
                Pos.TOP_CENTER
        );

        fullvb.setPadding(
                new Insets(
                        32,
                        34,
                        36,
                        34
                )
        );

        fullvb.setStyle(
                "-fx-background-color: black"
        );

        // =====================================================
        // IMAGE
        // =====================================================

        Image img =
                new Image(
                        "assets\\images\\coders.jpeg"
                );

        ImageView imageView =
                new ImageView(img);

        imageView.setFitWidth(400);
        imageView.setFitHeight(600);

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        VBox rightvb =
                new VBox(imageView);

        rightvb.setPrefWidth(425);

        // =====================================================
        // ROOT
        // =====================================================

        HBox root = new HBox(
                rightvb,
                fullvb
        );

        root.setAlignment(
                Pos.CENTER
        );

        root.setStyle(
                "-fx-background-color: #050505"
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene sc =
                new Scene(
                        root,
                        850,
                        600
                );

        stage.setScene(sc);

        stage.show();
    }
}