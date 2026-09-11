package com.flexforce.view.login;

import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.dao.ClubOwnerDAO;
import com.flexforce.model.club_owner.ClubOwner;
import com.flexforce.controller.AuthControllerClub;
import com.flexforce.view.Registration;
import com.flexforce.view.User.UserDashboard;
import com.flexforce.view.admin.AdminDashboard;
import com.flexforce.view.club_owner.ClubOwnerDashboard;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Login extends Application {

    private static Stage mainStage;
    private static Scene mainScene;

    private String selectedRole = "MEMBER";


    @Override
    public void start(Stage stage) {

        mainStage = stage;


        // =====================================================
        // LEFT SIDE
        // =====================================================

        Image image = new Image(
                "assets\\images\\Fit-Circle.png"
        );

        ImageView logoImage = new ImageView(image);

        logoImage.setFitWidth(140);
        logoImage.setFitHeight(140);
        logoImage.setPreserveRatio(true);


        Label logo = new Label("FITCIRCLE");

        logo.setStyle(
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );


        Label leftTitle = new Label(
                "Connect. Compete. Grow Together."
        );

        leftTitle.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );


        Label description = new Label(
                "Your community for fitness,\n" +
                "challenges, events and healthy\n" +
                "competition."
        );

        description.setStyle(
                "-fx-text-fill:#9A9A9A;" +
                "-fx-font-size:14px;"
        );

        description.setAlignment(Pos.CENTER);


        VBox vbleft = new VBox(
                14,
                logoImage,
                logo,
                leftTitle,
                description
        );

        vbleft.setAlignment(Pos.CENTER);

        vbleft.setPrefWidth(500);
        vbleft.setPrefHeight(650);

        vbleft.setPadding(
                new Insets(25, 20, 25, 20)
        );

        vbleft.setStyle(
                "-fx-background-color:#0B0F0D;" +
                "-fx-border-color:#202820;"
        );


        // =====================================================
        // RIGHT SIDE
        // =====================================================

        Label formTitle = new Label(
                "Welcome Back"
        );

        formTitle.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );


        Label formDescription = new Label(
                "Sign in to continue your FitCircle journey"
        );

        formDescription.setStyle(
                "-fx-text-fill:#8F968F;" +
                "-fx-font-size:12px;"
        );


        // =====================================================
        // ROLE
        // =====================================================

        Label roleLabel = new Label(
                "SELECT YOUR ROLE"
        );

        roleLabel.setStyle(
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );


        Button memberButton = new Button("MEMBER");
        Button ownerButton = new Button("CLUB OWNER");
        Button adminButton = new Button("ADMIN");

        memberButton.setPrefHeight(38);
        ownerButton.setPrefHeight(38);
        adminButton.setPrefHeight(38);

        memberButton.setMaxWidth(Double.MAX_VALUE);
        ownerButton.setMaxWidth(Double.MAX_VALUE);
        adminButton.setMaxWidth(Double.MAX_VALUE);


        HBox roleBox = new HBox(
                8,
                memberButton,
                ownerButton,
                adminButton
        );

        roleBox.setAlignment(Pos.CENTER);


        // =====================================================
        // ROLE STYLE
        // =====================================================

        String selectedStyle =
                "-fx-background-color:#B6FF00;" +
                "-fx-text-fill:black;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;";


        String normalStyle =
                "-fx-background-color:#171B18;" +
                "-fx-text-fill:#A9AFA9;" +
                "-fx-border-color:#303830;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;";


        memberButton.setStyle(selectedStyle);
        ownerButton.setStyle(normalStyle);
        adminButton.setStyle(normalStyle);


        // =====================================================
        // MEMBER BUTTON
        // =====================================================

        memberButton.setOnAction(e -> {

            selectedRole = "MEMBER";

            System.out.println("ROLE = MEMBER");

            memberButton.setStyle(selectedStyle);
            ownerButton.setStyle(normalStyle);
            adminButton.setStyle(normalStyle);
        });


        // =====================================================
        // CLUB OWNER BUTTON
        // =====================================================

        ownerButton.setOnAction(e -> {

            selectedRole = "CLUB_OWNER";

            System.out.println("ROLE = CLUB_OWNER");

            memberButton.setStyle(normalStyle);
            ownerButton.setStyle(selectedStyle);
            adminButton.setStyle(normalStyle);
        });


        // =====================================================
        // ADMIN BUTTON
        // =====================================================

        adminButton.setOnAction(e -> {

            selectedRole = "ADMIN";

            System.out.println("ROLE = ADMIN");

            memberButton.setStyle(normalStyle);
            ownerButton.setStyle(normalStyle);
            adminButton.setStyle(selectedStyle);
        });


        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel = new Label(
                "EMAIL ADDRESS"
        );

        emailLabel.setStyle(
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );


        TextField emailField = new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        emailField.setPrefHeight(42);

        emailField.setStyle(
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#6F756F;" +
                "-fx-background-color:#171B18;" +
                "-fx-border-color:#303830;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );


        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel = new Label(
                "PASSWORD"
        );

        passwordLabel.setStyle(
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );


        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefHeight(42);

        passwordField.setStyle(
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#6F756F;" +
                "-fx-background-color:#171B18;" +
                "-fx-border-color:#303830;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );


        // =====================================================
        // REMEMBER ME
        // =====================================================

        CheckBox checkbox =
                new CheckBox("Remember me");

        checkbox.setStyle(
                "-fx-text-fill:#9A9A9A;" +
                "-fx-font-size:12px;"
        );


        // =====================================================
        // ERROR LABEL
        // =====================================================

        Label errorLabel =
                new Label();

        errorLabel.setStyle(
                "-fx-text-fill:#ff5555;" +
                "-fx-font-size:11px;"
        );


        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button("LOGIN  →");

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setPrefHeight(44);

        loginButton.setStyle(
                "-fx-background-color:#B6FF00;" +
                "-fx-text-fill:black;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // LOADER
        // =====================================================

        ProgressIndicator loader =
                new ProgressIndicator();

        loader.setPrefSize(25, 25);

        loader.setVisible(false);


        // =====================================================
        // LOGIN ACTION
        // =====================================================

        loginButton.setOnAction(e -> {

            String email =
                    emailField.getText().trim();

            String password =
                    passwordField.getText().trim();


            // =================================================
            // EMPTY FIELD
            // =================================================

            if (email.isEmpty() ||
                    password.isEmpty()) {

                errorLabel.setText(
                        "Please enter email and password."
                );

                return;
            }


            errorLabel.setText("");


            // =================================================
            // START LOADER
            // =================================================

            loginButton.setDisable(true);

            memberButton.setDisable(true);
            ownerButton.setDisable(true);
            adminButton.setDisable(true);

            loader.setVisible(true);

            loginButton.setText(
                    "LOGGING IN..."
            );


            // =================================================
            // BACKGROUND LOGIN TASK
            // =================================================

            Task<Boolean> loginTask =
                    new Task<>() {

                        @Override
                        protected Boolean call()
                                throws Exception {

                            // =================================
                            // MEMBER
                            // =================================

                            if (selectedRole.equals("MEMBER")) {

                                System.out.println(
                                        "MEMBER LOGIN STARTED"
                                );

                                AuthControllerlogin auth =
                                        new AuthControllerlogin();

                                return auth.signIn(
                                        email,
                                        password
                                );
                            }


                            // =================================
                            // CLUB OWNER
                            // =================================

                            if (selectedRole.equals("CLUB_OWNER")) {

                                System.out.println(
                                        "CLUB OWNER LOGIN STARTED"
                                );

                                AuthControllerClub auth =
                                        new AuthControllerClub();

                                return auth.signIn(
                                        email,
                                        password
                                );
                            }


                            // =================================
                            // ADMIN
                            // =================================

                            if (selectedRole.equals("ADMIN")) {

                                System.out.println(
                                        "ADMIN LOGIN STARTED"
                                );

                                AuthControllerlogin auth =
                                        new AuthControllerlogin();

                                return auth.signIn(
                                        email,
                                        password
                                );
                            }


                            return false;
                        }
                    };


            // =================================================
            // LOGIN SUCCESS
            // =================================================

            loginTask.setOnSucceeded(event -> {

                boolean success =
                        loginTask.getValue();


                // Stop loader
                loader.setVisible(false);

                loginButton.setDisable(false);

                memberButton.setDisable(false);
                ownerButton.setDisable(false);
                adminButton.setDisable(false);

                loginButton.setText(
                        "LOGIN  →"
                );


                // =================================================
                // LOGIN FAILED
                // =================================================

                if (!success) {

                    if (selectedRole.equals("MEMBER")) {

                        errorLabel.setText(
                                "Invalid member email or password."
                        );

                    }
                    else if (
                            selectedRole.equals("CLUB_OWNER")) {

                        errorLabel.setText(
                                "Invalid club owner email or password."
                        );

                    }
                    else {

                        errorLabel.setText(
                                "Invalid admin email or password."
                        );
                    }

                    return;
                }


                // =================================================
                // MEMBER DASHBOARD
                // =================================================

                if (selectedRole.equals("MEMBER")) {

                    try {

                        System.out.println(
                                "MEMBER LOGIN SUCCESS"
                        );


                        UserDashboard dashboard =
                                new UserDashboard();


                        Runnable logoutAction = () -> {

                            System.out.println(
                                    "USER LOGOUT"
                            );

                            mainStage.setScene(
                                    mainScene
                            );

                            mainStage.setTitle(
                                    "FitCircle - Login"
                            );

                            mainStage.show();
                        };


                        Scene dashboardScene =
                                dashboard.mainScene(
                                        logoutAction
                                );


                        if (dashboardScene == null) {

                            errorLabel.setText(
                                    "User Dashboard Scene is null."
                            );

                            return;
                        }


                        mainStage.setScene(
                                dashboardScene
                        );

                        mainStage.setTitle(
                                "FitCircle - User Dashboard"
                        );

                        mainStage.setMinWidth(1000);
                        mainStage.setMinHeight(650);

                        mainStage.show();


                        System.out.println(
                                "USER DASHBOARD OPENED"
                        );


                    } catch (Exception ex) {

                        ex.printStackTrace();

                        errorLabel.setText(
                                "User Dashboard open failed."
                        );
                    }

                    return;
                }


                // =================================================
                // CLUB OWNER DASHBOARD
                // =================================================

               // =================================================
// CLUB OWNER LOGIN + APPROVAL CHECK
// =================================================

if (selectedRole.equals("CLUB_OWNER")) {

    try {

        System.out.println(
                "CLUB OWNER LOGIN SUCCESS"
        );

        // =============================================
        // GET CURRENT CLUB OWNER ID
        // =============================================

        String ownerId =
                AuthControllerClub.getCurrentOwnerId();

        if (ownerId == null ||
                ownerId.trim().isEmpty()) {

            errorLabel.setText(
                    "Club owner account ID not found."
            );

            return;
        }

        System.out.println(
                "Club Owner ID = " + ownerId
        );

        // =============================================
        // GET CLUB OWNER FROM FIRESTORE
        // =============================================

        ClubOwnerDAO clubOwnerDAO =
                new ClubOwnerDAO();

        ClubOwner owner =
                clubOwnerDAO.getClubOwner(ownerId);

        // =============================================
        // OWNER RECORD NOT FOUND
        // =============================================

        if (owner == null) {

            errorLabel.setText(
                    "Club owner profile not found."
            );

            return;
        }

        // =============================================
        // GET STATUS
        // =============================================

        String status =
                owner.getStatus();

        if (status == null) {
            status = "";
        }

        status = status.trim().toUpperCase();

        System.out.println(
                "CLUB OWNER STATUS = " + status
        );

        // =============================================
        // PENDING APPROVAL
        // =============================================

        if (status.equals("PENDING")) {

            errorLabel.setText(
                    "Your club registration is waiting for admin approval."
            );

            System.out.println(
                    "CLUB OWNER LOGIN BLOCKED - PENDING APPROVAL"
            );

            return;
        }

        // =============================================
        // REJECTED
        // =============================================

        if (status.equals("REJECTED")) {

            errorLabel.setText(
                    "Your club registration was rejected by admin."
            );

            System.out.println(
                    "CLUB OWNER LOGIN BLOCKED - REJECTED"
            );

            return;
        }

        // =============================================
        // ONLY ACTIVE OWNER CAN ENTER DASHBOARD
        // =============================================

        if (!status.equals("ACTIVE")) {

            errorLabel.setText(
                    "Your club account is not active."
            );

            System.out.println(
                    "CLUB OWNER LOGIN BLOCKED - STATUS = "
                            + status
            );

            return;
        }

        // =============================================
        // APPROVED / ACTIVE
        // OPEN DASHBOARD
        // =============================================

        System.out.println(
                "CLUB OWNER APPROVED"
        );

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
                "Unable to verify club owner approval."
        );
    }

    return;
}
                // =================================================
                // ADMIN DASHBOARD
                // =================================================

                if (selectedRole.equals("ADMIN")) {

                    try {

                        System.out.println(
                                "ADMIN LOGIN SUCCESS"
                        );


                        AdminDashboard dashboard =
                                new AdminDashboard();


                        Runnable logoutAction =
                                () -> backToLoginPage();


                        Scene adminScene =
                                dashboard.mainScene(
                                        logoutAction
                                );


                        if (adminScene == null) {

                            errorLabel.setText(
                                    "Admin Dashboard Scene is null."
                            );

                            return;
                        }


                        mainStage.setScene(
                                adminScene
                        );

                        mainStage.setTitle(
                                "FitCircle - Admin Dashboard"
                        );

                        mainStage.show();


                        System.out.println(
                                "ADMIN DASHBOARD OPENED"
                        );


                    } catch (Exception ex) {

                        ex.printStackTrace();

                        errorLabel.setText(
                                "Admin Dashboard open failed."
                        );
                    }
                }

            });


            // =================================================
            // LOGIN ERROR
            // =================================================

            loginTask.setOnFailed(event -> {

                loader.setVisible(false);

                loginButton.setDisable(false);

                memberButton.setDisable(false);
                ownerButton.setDisable(false);
                adminButton.setDisable(false);

                loginButton.setText(
                        "LOGIN  →"
                );


                errorLabel.setText(
                        "Login failed. Please try again."
                );


                if (loginTask.getException() != null) {

                    loginTask.getException()
                            .printStackTrace();
                }
            });


            // =================================================
            // START LOGIN THREAD
            // =================================================

            Thread loginThread =
                    new Thread(loginTask);

            loginThread.setDaemon(true);

            loginThread.start();

        });


        // =====================================================
        // SIGN UP
        // =====================================================

        Label signupText =
                new Label(
                        "Don't have an account?"
                );

        signupText.setStyle(
                "-fx-text-fill:#8F968F;" +
                "-fx-font-size:12px;"
        );


        Button signupButton =
                new Button("Sign Up");

        signupButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0;" +
                "-fx-cursor:hand;"
        );


        signupButton.setOnAction(e -> {

            try {

                Registration registration =
                        new Registration();

                registration.start(
                        mainStage
                );


            } catch (Exception ex) {

                ex.printStackTrace();

                errorLabel.setText(
                        "Unable to open Registration."
                );
            }
        });


        HBox signupBox =
                new HBox(
                        5,
                        signupText,
                        signupButton
                );

        signupBox.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // RIGHT BOX
        // =====================================================

        VBox vbright =
                new VBox(
                        13,
                        formTitle,
                        formDescription,
                        roleLabel,
                        roleBox,
                        emailLabel,
                        emailField,
                        passwordLabel,
                        passwordField,
                        checkbox,
                        errorLabel,
                        loginButton,
                        loader,
                        signupBox
                );


        vbright.setPadding(
                new Insets(30)
        );

        vbright.setAlignment(
                Pos.CENTER
        );

        vbright.setPrefWidth(450);
        vbright.setPrefHeight(650);

        vbright.setStyle(
                "-fx-background-color:#101411;" +
                "-fx-border-color:#293029;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;"
        );


        // =====================================================
        // MAIN
        // =====================================================

        HBox main =
                new HBox(
                        35,
                        vbleft,
                        vbright
                );

        main.setAlignment(
                Pos.CENTER
        );

        main.setPadding(
                new Insets(30)
        );

        main.setStyle(
                "-fx-background-color:#080A09;"
        );


        // =====================================================
        // SCENE
        // =====================================================

        mainScene =
                new Scene(
                        main,
                        1550,
                        800
                );


        mainStage.setScene(
                mainScene
        );

        mainStage.setTitle(
                "FitCircle - Login"
        );

        mainStage.show();
    }


    // =========================================================
    // BACK TO LOGIN
    // =========================================================

    public void backToLoginPage() {

        mainStage.setScene(
                mainScene
        );

        mainStage.setTitle(
                "FitCircle - Login"
        );

        mainStage.show();
    }
}