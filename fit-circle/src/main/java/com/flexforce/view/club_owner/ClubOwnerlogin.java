package com.flexforce.view.club_owner;

import com.flexforce.controller.AuthControllerClub;
import com.flexforce.view.Registration;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClubOwnerlogin extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) {

        mainStage = stage;

        // =====================================================
        // LEFT SIDE
        // =====================================================

        Image image = new Image(
                "assets\\images\\image.png"
        );

        ImageView imageView = new ImageView(image);

        imageView.setPreserveRatio(false);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        Label lab = new Label(
                "Welcome Back Club Owner"
        );

        lab.setTextFill(Color.BLUEVIOLET);

        lab.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-color:#151515;" +
                "-fx-border-color:#82d000;" +
                "-fx-border-width:2px;" +
                "-fx-border-radius:20px;" +
                "-fx-background-radius:20px;" +
                "-fx-padding:10px 25px;"
        );

        Label lab1 = new Label(
                "Lead the community. Inspire the change."
        );

        lab1.setTextFill(Color.WHITE);

        lab1.setStyle(
                "-fx-font-size:20px;"
        );

        Label lab2 = new Label(
                "Your club. Your Community. Your impact."
        );

        lab2.setTextFill(Color.WHITE);

        lab2.setStyle(
                "-fx-font-size:20px;"
        );

        VBox leftSide = new VBox(
                30,
                lab,
                lab1,
                imageView,
                lab2
        );

        leftSide.setAlignment(
                Pos.TOP_CENTER
        );

        leftSide.setStyle(
                "-fx-background-color:#151515;"
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        Label logo = new Label(
                "〽 FitCircle"
        );

        logo.setTextFill(Color.WHITE);

        logo.setStyle(
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        Label portal = new Label(
                "⚙ CLUB OWNER PORTAL"
        );

        portal.setTextFill(
                Color.LIGHTGRAY
        );

        portal.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-color:#222222;" +
                "-fx-padding:6px 12px;" +
                "-fx-background-radius:15px;"
        );

        Label welcome = new Label(
                "Welcome Back"
        );

        welcome.setTextFill(
                Color.WHITE
        );

        welcome.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle = new Label(
                "Sign in to manage your fitness club operations"
        );

        subtitle.setTextFill(
                Color.LIGHTGRAY
        );

        subtitle.setStyle(
                "-fx-font-size:13px;"
        );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel = new Label(
                "Email Address"
        );

        emailLabel.setTextFill(
                Color.WHITE
        );

        emailLabel.setStyle(
                "-fx-font-size:12px;"
        );

        TextField email = new TextField();

        email.setPromptText(
                "Enter your email"
        );

        email.setPrefHeight(40);

        email.setStyle(
                "-fx-background-color:#090909;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#292929;" +
                "-fx-border-radius:5px;" +
                "-fx-background-radius:5px;" +
                "-fx-padding:10px;"
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel = new Label(
                "Password"
        );

        passwordLabel.setTextFill(
                Color.WHITE
        );

        passwordLabel.setStyle(
                "-fx-font-size:12px;"
        );

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter your password"
        );

        password.setPrefHeight(40);

        password.setStyle(
                "-fx-background-color:#090909;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#292929;" +
                "-fx-border-radius:5px;" +
                "-fx-background-radius:5px;" +
                "-fx-padding:10px;"
        );

        // =====================================================
        // REMEMBER ME
        // =====================================================

        CheckBox remember =
                new CheckBox("Remember me");

        remember.setTextFill(
                Color.WHITE
        );

        remember.setStyle(
                "-fx-font-size:12px;"
        );

        // =====================================================
        // ERROR LABEL
        // =====================================================

        Label errorLabel =
                new Label();

        errorLabel.setTextFill(
                Color.RED
        );

        errorLabel.setStyle(
                "-fx-font-size:12px;"
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button login =
                new Button(
                        "Login as Club Owner  →"
                );

        login.setMaxWidth(
                Double.MAX_VALUE
        );

        login.setPrefHeight(45);

        login.setStyle(
                "-fx-background-color:#82d000;" +
                "-fx-text-fill:black;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:6px;" +
                "-fx-cursor:hand;"
        );

        // =====================================================
        // LOGIN ACTION
        // =====================================================

        login.setOnAction(e -> {

            String emailText =
                    email.getText().trim();

            String passwordText =
                    password.getText().trim();

            // =================================================
            // EMPTY EMAIL
            // =================================================

            if (emailText.isEmpty()) {

                errorLabel.setText(
                        "Please enter email."
                );

                return;
            }

            // =================================================
            // EMPTY PASSWORD
            // =================================================

            if (passwordText.isEmpty()) {

                errorLabel.setText(
                        "Please enter password."
                );

                return;
            }

            errorLabel.setText("");

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "CLUB OWNER LOGIN STARTED"
            );

            System.out.println(
                    "Email : " + emailText
            );

            // =================================================
            // FIREBASE LOGIN
            // =================================================

            try {

                AuthControllerClub auth =
                        new AuthControllerClub();

                boolean success =
                        auth.signIn(
                                emailText,
                                passwordText
                        );

                // =================================================
                // LOGIN SUCCESS
                // =================================================

                if (success) {

                    System.out.println(
                            "CLUB OWNER LOGIN SUCCESSFUL"
                    );

                    // =============================================
                    // OPEN CLUB OWNER DASHBOARD
                    // =============================================

                    try {

                        ClubOwnerDashboard dashboard =
                                new ClubOwnerDashboard();

                        dashboard.start(
                                mainStage
                        );

                        System.out.println(
                                "CLUB OWNER DASHBOARD OPENED"
                        );

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        errorLabel.setText(
                                "Dashboard open failed."
                        );
                    }

                }

                // =================================================
                // LOGIN FAILED
                // =================================================

                else {

                    System.out.println(
                            "CLUB OWNER LOGIN FAILED"
                    );

                    errorLabel.setText(
                            "Invalid email or password."
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                errorLabel.setText(
                        "Login error occurred."
                );
            }

            System.out.println(
                    "================================"
            );
        });

        // =====================================================
        // REGISTER TEXT
        // =====================================================

        Label registerText =
                new Label(
                        "Don't have a club account?"
                );

        registerText.setTextFill(
                Color.LIGHTGRAY
        );

        registerText.setStyle(
                "-fx-font-size:11px;"
        );

        // =====================================================
        // CREATE ACCOUNT BUTTON
        // =====================================================

        Button createAccount =
                new Button(
                        "Create Account"
                );

        createAccount.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#82d000;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );

        // =====================================================
        // CREATE ACCOUNT NAVIGATION
        // =====================================================

        createAccount.setOnAction(e -> {

            System.out.println(
                    "Create Account Clicked"
            );

            try {

                Registration registration =
                        new Registration();

                registration.start(
                        mainStage
                );

                System.out.println(
                        "Registration Page Opened"
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                errorLabel.setText(
                        "Unable to open Registration."
                );
            }
        });

        // =====================================================
        // REGISTER BOX
        // =====================================================

        HBox registerBox =
                new HBox(
                        5,
                        registerText,
                        createAccount
                );

        registerBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        VBox rightSide =
                new VBox(
                        15,
                        logo,
                        portal,
                        welcome,
                        subtitle,
                        emailLabel,
                        email,
                        passwordLabel,
                        password,
                        remember,
                        errorLabel,
                        login,
                        registerBox
                );

        rightSide.setPadding(
                new Insets(
                        40,
                        70,
                        30,
                        70
                )
        );

        rightSide.setAlignment(
                Pos.TOP_CENTER
        );

        rightSide.setStyle(
                "-fx-background-color:#151515;" +
                "-fx-border-color:#292929;" +
                "-fx-border-width:0 0 0 1;"
        );

        // =====================================================
        // ROOT
        // =====================================================

        HBox root =
                new HBox(
                        leftSide,
                        rightSide
                );

        root.setStyle(
                "-fx-background-color:#151515;"
        );

        leftSide.prefWidthProperty().bind(
                root.widthProperty().divide(2)
        );

        rightSide.prefWidthProperty().bind(
                root.widthProperty().divide(2)
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        850,
                        600
                );

        stage.setTitle(
                "FitCircle - Club Owner"
        );

        stage.setScene(
                scene
        );

        stage.show();
    }
}