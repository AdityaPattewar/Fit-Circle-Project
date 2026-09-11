package com.flexforce.view;

import com.flexforce.dao.UserInfoDAO;
import com.flexforce.controller.AuthControllerClub;
import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.controller.UserInfoController;
import com.flexforce.dao.ClubOwnerDAO;
import com.flexforce.model.club_owner.ClubOwner;
import com.flexforce.model.user.UserInfo;
import com.flexforce.view.User.UserDashboard;
import com.flexforce.view.admin.Admin_login;
import com.flexforce.view.club_owner.ClubOwnerDashboard;
import com.flexforce.view.login.Login;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Registration extends Application {

   private UserInfoDAO userInfoDAO =
            new UserInfoDAO();

    private static Stage mainStage;

    private String selectedRole = "MEMBER";

    private UserInfoController userInfoController =
            new UserInfoController();

    private ClubOwnerDAO clubOwnerDAO =
            new ClubOwnerDAO();


    @Override
    public void start(Stage stage) {

        mainStage = stage;

        // =====================================================
        // LEFT SIDE
        // =====================================================

        Label logo = new Label("FITCIRCLE");

        logo.setStyle(
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );


        Label leftTitle = new Label(
                "Connect. Compete.\nGrow Together."
        );

        leftTitle.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );


        Label description = new Label(
                "Join a community that motivates you to stay active,\n" +
                "achieve your goals and grow together."
        );

        description.setStyle(
                "-fx-text-fill:#9A9A9A;" +
                "-fx-font-size:13px;"
        );


        Region space = new Region();

        space.setPrefHeight(100);


        Label challenge = new Label(
                "🏆   Fitness Challenges"
        );

        Label competition = new Label(
                "⚡   Healthy Competition"
        );

        Label communities = new Label(
                "👥   Fitness Communities"
        );

        Label ai = new Label(
                "◉   AI Guidance"
        );


        challenge.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:13px;"
        );

        competition.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:13px;"
        );

        communities.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:13px;"
        );

        ai.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:13px;"
        );


        VBox vbleft = new VBox(
                18,
                logo,
                leftTitle,
                description,
                space,
                challenge,
                competition,
                communities,
                ai
        );

        vbleft.setPrefWidth(420);
        vbleft.setPrefHeight(700);

        vbleft.setPadding(
                new Insets(45, 35, 40, 45)
        );

        vbleft.setStyle(
                "-fx-background-color:#0B0F0D;" +
                "-fx-border-color:#202820;"
        );


        // =====================================================
        // FORM TITLE
        // =====================================================

        Label formTitle = new Label(
                "Create Your Account"
        );

        formTitle.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );


        Label formDescription = new Label(
                "Choose your role and start your FitCircle journey."
        );

        formDescription.setStyle(
                "-fx-text-fill:#8F968F;" +
                "-fx-font-size:13px;"
        );


        // =====================================================
        // ROLE
        // =====================================================

        Label roleLabel = new Label(
                "SELECT YOUR ROLE"
        );

        roleLabel.setStyle(
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );


        Button memberButton = new Button(
                "👤  MEMBER"
        );

        Button ownerButton = new Button(
                "🏢  CLUB OWNER"
        );


        memberButton.setPrefWidth(150);
        ownerButton.setPrefWidth(150);

        memberButton.setPrefHeight(42);
        ownerButton.setPrefHeight(42);


        HBox roleBox = new HBox(
                10,
                memberButton,
                ownerButton
        );


        // =====================================================
        // ROLE STYLE
        // =====================================================

        String selectedStyle =
                "-fx-background-color:#B6FF00;" +
                "-fx-text-fill:black;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;";


        String normalStyle =
                "-fx-background-color:#171B18;" +
                "-fx-text-fill:#9A9A9A;" +
                "-fx-border-color:#303830;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";


        memberButton.setStyle(selectedStyle);
        ownerButton.setStyle(normalStyle);


        // =====================================================
        // COMMON FIELDS
        // =====================================================

        Label nameLabel =
                new Label("FULL NAME");

        Label emailLabel =
                new Label("EMAIL ADDRESS");

        Label mobileLabel =
                new Label("MOBILE NUMBER");

        Label addressLabel =
                new Label("ADDRESS");

        Label dobLabel =
                new Label("DATE OF BIRTH");


        nameLabel.setStyle(labelStyle());
        emailLabel.setStyle(labelStyle());
        mobileLabel.setStyle(labelStyle());
        addressLabel.setStyle(labelStyle());
        dobLabel.setStyle(labelStyle());


        TextField nameField =
                new TextField();

        TextField emailField =
                new TextField();

        TextField mobileField =
                new TextField();

        TextField addressField =
                new TextField();

        TextField dobField =
                new TextField();


        nameField.setPromptText(
                "Enter your full name"
        );

        emailField.setPromptText(
                "name@example.com"
        );

        mobileField.setPromptText(
                "Enter mobile number"
        );

        addressField.setPromptText(
                "Enter your address"
        );

        dobField.setPromptText(
                "DD / MM / YYYY"
        );


        setFieldStyle(nameField);
        setFieldStyle(emailField);
        setFieldStyle(mobileField);
        setFieldStyle(addressField);
        setFieldStyle(dobField);


        // =====================================================
        // CLUB NAME
        // =====================================================

        Label clubNameLabel =
                new Label("CLUB NAME");

        clubNameLabel.setStyle(
                labelStyle()
        );


        TextField clubNameField =
                new TextField();

        clubNameField.setPromptText(
                "Enter your club name"
        );

        setFieldStyle(clubNameField);


        clubNameLabel.setVisible(false);
        clubNameLabel.setManaged(false);

        clubNameField.setVisible(false);
        clubNameField.setManaged(false);


        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                new Label("PASSWORD");

        passwordLabel.setStyle(
                labelStyle()
        );


        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Create a strong password"
        );

        setFieldStyle(passwordField);


        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        Label confirmLabel =
                new Label("CONFIRM PASSWORD");

        confirmLabel.setStyle(
                labelStyle()
        );


        PasswordField confirmField =
                new PasswordField();

        confirmField.setPromptText(
                "Confirm your password"
        );

        setFieldStyle(confirmField);


        // =====================================================
        // INPUT BOXES
        // =====================================================

        VBox nameBox = new VBox(
                6,
                nameLabel,
                nameField
        );


        VBox emailBox = new VBox(
                6,
                emailLabel,
                emailField
        );


        VBox mobileBox = new VBox(
                6,
                mobileLabel,
                mobileField
        );


        VBox addressBox = new VBox(
                6,
                addressLabel,
                addressField
        );


        VBox dobBox = new VBox(
                6,
                dobLabel,
                dobField
        );


        VBox clubBox = new VBox(
                6,
                clubNameLabel,
                clubNameField
        );


        VBox passwordBox = new VBox(
                6,
                passwordLabel,
                passwordField
        );


        VBox confirmBox = new VBox(
                6,
                confirmLabel,
                confirmField
        );


        // =====================================================
        // ROWS
        // =====================================================

        HBox row1 = new HBox(
                20,
                nameBox,
                emailBox
        );


        HBox row2 = new HBox(
                20,
                mobileBox,
                addressBox
        );


        HBox row3 = new HBox(
                20,
                dobBox,
                passwordBox
        );


        // =====================================================
        // WIDTH
        // =====================================================

        nameBox.setPrefWidth(320);
        emailBox.setPrefWidth(320);

        mobileBox.setPrefWidth(320);
        addressBox.setPrefWidth(320);

        dobBox.setPrefWidth(320);
        passwordBox.setPrefWidth(320);

        clubBox.setPrefWidth(320);
        confirmBox.setPrefWidth(320);


        nameField.setMaxWidth(
                Double.MAX_VALUE
        );

        emailField.setMaxWidth(
                Double.MAX_VALUE
        );

        mobileField.setMaxWidth(
                Double.MAX_VALUE
        );

        addressField.setMaxWidth(
                Double.MAX_VALUE
        );

        dobField.setMaxWidth(
                Double.MAX_VALUE
        );

        passwordField.setMaxWidth(
                Double.MAX_VALUE
        );

        clubNameField.setMaxWidth(
                Double.MAX_VALUE
        );

        confirmField.setMaxWidth(
                Double.MAX_VALUE
        );


        // =====================================================
        // ERROR / SUCCESS MESSAGE
        // =====================================================

        Label messageLabel =
                new Label();

        messageLabel.setStyle(
                "-fx-text-fill:#ff5555;" +
                "-fx-font-size:12px;"
        );


        // =====================================================
        // LOADER
        // =====================================================

        ProgressIndicator loader =
                new ProgressIndicator();

        loader.setPrefSize(30, 30);

        loader.setVisible(false);
        loader.setManaged(false);


        // =====================================================
        // ROLE SELECTION - MEMBER
        // =====================================================

        memberButton.setOnAction(e -> {

            selectedRole = "MEMBER";


            memberButton.setStyle(
                    selectedStyle
            );

            ownerButton.setStyle(
                    normalStyle
            );


            clubNameLabel.setVisible(false);
            clubNameLabel.setManaged(false);

            clubNameField.setVisible(false);
            clubNameField.setManaged(false);


            if (row3.getChildren().contains(clubBox)) {

                row3.getChildren().remove(
                        clubBox
                );
            }


            messageLabel.setText("");
        });


        // =====================================================
        // ROLE SELECTION - CLUB OWNER
        // =====================================================

        ownerButton.setOnAction(e -> {

            selectedRole = "CLUB_OWNER";


            memberButton.setStyle(
                    normalStyle
            );

            ownerButton.setStyle(
                    selectedStyle
            );


            clubNameLabel.setVisible(true);
            clubNameLabel.setManaged(true);

            clubNameField.setVisible(true);
            clubNameField.setManaged(true);


            if (!row3.getChildren().contains(clubBox)) {

                row3.getChildren().add(
                        0,
                        clubBox
                );
            }


            messageLabel.setText("");
        });


        // =====================================================
        // CREATE ACCOUNT BUTTON
        // =====================================================

        Button createButton =
                new Button(
                        "CREATE ACCOUNT  →"
                );


        createButton.setMaxWidth(
                Double.MAX_VALUE
        );

        createButton.setPrefHeight(45);


        createButton.setStyle(
                "-fx-background-color:#B6FF00;" +
                "-fx-text-fill:black;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // CREATE ACCOUNT ACTION
        // =====================================================

        createButton.setOnAction(e -> {

            messageLabel.setText("");


            // =================================================
            // CHECK COMMON FIELDS
            // =================================================

            if (
                    nameField.getText().trim().isEmpty() ||
                    emailField.getText().trim().isEmpty() ||
                    mobileField.getText().trim().isEmpty() ||
                    addressField.getText().trim().isEmpty() ||
                    dobField.getText().trim().isEmpty() ||
                    passwordField.getText().trim().isEmpty() ||
                    confirmField.getText().trim().isEmpty()
            ) {

                messageLabel.setStyle(
                        "-fx-text-fill:#ff5555;" +
                        "-fx-font-size:12px;"
                );


                messageLabel.setText(
                        "Please fill all required fields."
                );

                return;
            }


            // =================================================
            // PASSWORD CHECK
            // =================================================

            if (
                    !passwordField.getText().equals(
                            confirmField.getText()
                    )
            ) {

                messageLabel.setStyle(
                        "-fx-text-fill:#ff5555;" +
                        "-fx-font-size:12px;"
                );


                messageLabel.setText(
                        "Passwords do not match."
                );

                return;
            }


            // =================================================
            // CLUB OWNER CHECK
            // =================================================

            if (
                    selectedRole.equals("CLUB_OWNER") &&
                    clubNameField.getText().trim().isEmpty()
            ) {

                messageLabel.setStyle(
                        "-fx-text-fill:#ff5555;" +
                        "-fx-font-size:12px;"
                );


                messageLabel.setText(
                        "Please enter club name."
                );

                return;
            }


            // =================================================
            // STORE VALUES BEFORE BACKGROUND THREAD
            // =================================================

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String mobile =
                    mobileField.getText().trim();

            String address =
                    addressField.getText().trim();

            String dob =
                    dobField.getText().trim();

            String password =
                    passwordField.getText();

            String role =
                    selectedRole;

            String clubName =
                    clubNameField.getText().trim();


            // =================================================
            // SHOW LOADER
            // =================================================

            loader.setVisible(true);
            loader.setManaged(true);


            createButton.setDisable(true);

            memberButton.setDisable(true);
            ownerButton.setDisable(true);


            messageLabel.setStyle(
                    "-fx-text-fill:#B6FF00;" +
                    "-fx-font-size:12px;"
            );


            messageLabel.setText(
                    "Creating account, please wait..."
            );


            // =================================================
            // BACKGROUND TASK
            // =================================================

            Task<Boolean> registrationTask =
                    new Task<Boolean>() {

                @Override
                protected Boolean call()
                        throws Exception {


                    // =========================================
                    // MEMBER REGISTRATION
                    // =========================================

                    if (role.equals("MEMBER")) {

                        System.out.println(
                                "Member registration started"
                        );


                        boolean success =
                                AuthControllerlogin.signUp(
                                        email,
                                        password
                                );


                        if (!success) {

                            return false;
                        }


                        System.out.println(
                                "MEMBER registration successful"
                        );


                        // =====================================
                        // SAVE USER INFORMATION
                        // =====================================

                        UserInfo userInfo =
                                new UserInfo();


                        String currentUserId =
                                AuthControllerlogin
                                        .getCurrentUserId();


                        if (
                                currentUserId == null ||
                                currentUserId.trim().isEmpty()
                        ) {

                            throw new Exception(
                                    "Account created but user ID could not be found."
                            );
                        }


                        userInfo.setUserId(
                                currentUserId
                        );


                        userInfo.setName(
                                name
                        );


                        userInfo.setEmail(
                                email
                        );


                        userInfo.setPhone(
                                mobile
                        );


                        userInfo.setAddress(
                                address
                        );


                        userInfo.setDateOfBirth(
                                dob
                        );


                        userInfo.setRole(
                                "MEMBER"
                        );


                        userInfo.setStatus(
                                "ACTIVE"
                        );


                        boolean userInfoSaved =
                                userInfoController.saveUserInfo(
                                        userInfo
                                );


                        if (!userInfoSaved) {

                            throw new Exception(
                                    "Account created but user information could not be saved."
                            );
                        }


                        System.out.println(
                                "User information saved successfully."
                        );


                        return true;
                    }


                    // =========================================
                    // CLUB OWNER REGISTRATION
                    // =========================================

                    if (role.equals("CLUB_OWNER")) {

                        System.out.println(
                                "Club Owner registration started"
                        );


                        boolean clubOwnerSuccess =
                                AuthControllerClub.signUp(
                                        email,
                                        password
                                );


                        if (!clubOwnerSuccess) {

                            return false;
                        }


                        System.out.println(
                                "CLUB OWNER registration successful"
                        );


                        // =====================================
                        // SAVE CLUB OWNER INFORMATION
                        // =====================================

                        ClubOwner owner =
                                new ClubOwner();


                        String currentOwnerId =
                                AuthControllerClub
                                        .getCurrentOwnerId();


                        if (
                                currentOwnerId == null ||
                                currentOwnerId.trim().isEmpty()
                        ) {

                            throw new Exception(
                                    "Account created but club owner ID could not be found."
                            );
                        }


                        owner.setOwnerId(
                                currentOwnerId
                        );


                        owner.setName(
                                name
                        );


                        owner.setEmail(
                                email
                        );


                        owner.setPhone(
                                mobile
                        );


                        // SAVE CLUB NAME
                        owner.setClubName(
                                clubName
                        );


                        // SAVE LOCATION / ADDRESS
                        owner.setAddress(
                                address
                        );


                        // ROLE
                        owner.setRole(
                                "CLUB_OWNER"
                        );


                        // IMPORTANT:
                        // NEW CLUB OWNER MUST WAIT FOR ADMIN
                        owner.setStatus(
                                "PENDING"
                        );


                        boolean ownerSaved =
                                clubOwnerDAO.saveClubOwner(
                                        owner
                                );


                        if (!ownerSaved) {

                            throw new Exception(
                                    "Account created but club owner information could not be saved."
                            );
                        }


                        System.out.println(
                                "Club Owner information saved successfully."
                        );


                        return true;
                    }


                    return false;
                }
            };


            // =================================================
            // SUCCESS
            // =================================================

            registrationTask.setOnSucceeded(
                    successEvent -> {

                loader.setVisible(false);
                loader.setManaged(false);


                createButton.setDisable(false);

                memberButton.setDisable(false);
                ownerButton.setDisable(false);


                boolean success =
                        registrationTask.getValue();


                // =================================================
                // REGISTRATION FAILED
                // =================================================

                if (!success) {

                    messageLabel.setStyle(
                            "-fx-text-fill:#ff5555;" +
                            "-fx-font-size:12px;"
                    );


                    messageLabel.setText(
                            "Registration failed. Please try again."
                    );


                    return;
                }


                // =================================================
                // MEMBER REGISTRATION SUCCESS
                // =================================================

                if (role.equals("MEMBER")) {

                    messageLabel.setStyle(
                            "-fx-text-fill:#B6FF00;" +
                            "-fx-font-size:12px;"
                    );


                    messageLabel.setText(
                            "Account created successfully!"
                    );


                    try {

                        UserDashboard dashboard =
                                new UserDashboard();


                        mainStage.setScene(
                                dashboard.mainScene(
                                        () -> {

                                            Login loginPage =
                                                    new Login();

                                            loginPage.start(
                                                    mainStage
                                            );
                                        }
                                )
                        );


                        mainStage.setTitle(
                                "FitCircle - User Dashboard"
                        );


                        mainStage.show();


                    } catch (Exception ex) {

                        ex.printStackTrace();


                        messageLabel.setStyle(
                                "-fx-text-fill:#ff5555;" +
                                "-fx-font-size:12px;"
                        );


                        messageLabel.setText(
                                "Account created, but unable to open User Dashboard."
                        );
                    }


                    return;
                }


                // =================================================
                // CLUB OWNER REGISTRATION SUCCESS
                // =================================================

                if (role.equals("CLUB_OWNER")) {

                    messageLabel.setStyle(
                            "-fx-text-fill:#B6FF00;" +
                            "-fx-font-size:12px;"
                    );


                    messageLabel.setText(
                            "Registration submitted! " +
                            "Your club is waiting for admin approval."
                    );


                    System.out.println(
                            "CLUB OWNER REGISTRATION COMPLETED - " +
                            "WAITING FOR ADMIN APPROVAL"
                    );


                    // =============================================
                    // RETURN TO LOGIN PAGE
                    // =============================================

                    try {

                        Login loginPage =
                                new Login();


                        loginPage.start(
                                mainStage
                        );


                        mainStage.setTitle(
                                "FitCircle - Login"
                        );


                        mainStage.show();


                    } catch (Exception ex) {

                        ex.printStackTrace();


                        messageLabel.setStyle(
                                "-fx-text-fill:#ff5555;" +
                                "-fx-font-size:12px;"
                        );


                        messageLabel.setText(
                                "Registration completed, but unable to open Login page."
                        );
                    }


                    return;
                }
            });


            // =================================================
            // FAILED
            // =================================================

            registrationTask.setOnFailed(
                    failedEvent -> {

                loader.setVisible(false);
                loader.setManaged(false);


                createButton.setDisable(false);

                memberButton.setDisable(false);
                ownerButton.setDisable(false);


                messageLabel.setStyle(
                        "-fx-text-fill:#ff5555;" +
                        "-fx-font-size:12px;"
                );


                Throwable error =
                        registrationTask.getException();


                if (error != null) {

                    error.printStackTrace();


                    String errorMessage =
                            error.getMessage();


                    if (
                            errorMessage != null &&
                            !errorMessage.trim().isEmpty()
                    ) {

                        messageLabel.setText(
                                errorMessage
                        );

                    } else {

                        messageLabel.setText(
                                "Registration failed. Please try again."
                        );
                    }

                } else {

                    messageLabel.setText(
                            "Registration failed. Please try again."
                    );
                }
            });


            // =================================================
            // START BACKGROUND THREAD
            // =================================================

            Thread registrationThread =
                    new Thread(registrationTask);


            registrationThread.setDaemon(true);


            registrationThread.start();
        });


        // =====================================================
        // LOGIN LINK
        // =====================================================

        Label loginText =
                new Label(
                        "Already have an account?"
                );


        loginText.setStyle(
                "-fx-text-fill:#8F968F;" +
                "-fx-font-size:12px;"
        );


        Button loginButton =
                new Button(
                        "Login"
                );


        loginButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0;" +
                "-fx-cursor:hand;"
        );


        loginButton.setOnAction(
                loginEvent -> {

            Login loginPage =
                    new Login();


            loginPage.start(
                    mainStage
            );
        });


        HBox loginBox =
                new HBox(
                        5,
                        loginText,
                        loginButton
                );


        loginBox.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // FORM
        // =====================================================

        VBox form =
                new VBox(
                        10,
                        formTitle,
                        formDescription,
                        roleLabel,
                        roleBox,
                        row1,
                        row2,
                        row3,
                        confirmBox,
                        messageLabel,
                        loader,
                        createButton
                );


        form.setPadding(
                new Insets(
                        25,
                        35,
                        25,
                        35
                )
        );


        form.setPrefWidth(760);

        form.setMaxWidth(760);


        form.setStyle(
                "-fx-background-color:#101411;" +
                "-fx-border-color:#293029;" +
                "-fx-border-radius:10px;" +
                "-fx-background-radius:10px;"
        );


        // =====================================================
        // RIGHT SIDE
        // =====================================================

        VBox rightSide =
                new VBox(
                        15,
                        form,
                        loginBox
                );


        rightSide.setAlignment(
                Pos.CENTER
        );


        rightSide.setPadding(
                new Insets(25)
        );


        rightSide.setStyle(
                "-fx-background-color:#080A09;"
        );


        // =====================================================
        // MAIN
        // =====================================================

        HBox main =
                new HBox(
                        vbleft,
                        rightSide
                );


        main.setAlignment(
                Pos.CENTER
        );


        main.setStyle(
                "-fx-background-color:#080A09;"
        );


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        main,
                        1550,
                        800
                );


        stage.setScene(scene);


        stage.setTitle(
                "FitCircle - Registration"
        );


        stage.setMinWidth(1100);

        stage.setMinHeight(700);


        stage.show();
    }


    // =========================================================
    // LABEL STYLE
    // =========================================================

    private String labelStyle() {

        return
                "-fx-text-fill:#B6FF00;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;";
    }


    // =========================================================
    // FIELD STYLE
    // =========================================================

    private void setFieldStyle(
            TextField field) {

        field.setPrefHeight(40);


        field.setStyle(
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#6F756F;" +
                "-fx-background-color:#171B18;" +
                "-fx-border-color:#303830;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );
    }
}