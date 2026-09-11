package com.flexforce.view.User;

import com.flexforce.controller.UserProfileController;
import com.flexforce.dao.ActivityDAO;
import com.google.cloud.firestore.DocumentSnapshot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;

import java.util.List;

public class User_Profile {

     // ============================================================
    // CONTROLLER
    // ============================================================

    private final UserProfileController userProfileController =
            new UserProfileController();

    private final ActivityDAO activityDAO =
            new ActivityDAO();

    // ============================================================
    // TEXT FIELDS
    // ============================================================

    private TextField fullNameField;
    private TextField emailField;
    private TextField phoneField;
    private TextField dobField;

    private ComboBox<String> genderField;

    private TextField addressField;
    private TextField workoutDaysField;
    private TextField heightField;
    private TextField weightField;
    private TextField goalField;
    private TextField planField;
    private TextField statusField;
    private TextField joiningDateField;

    // ============================================================
    // LABELS
    // ============================================================

    private Label profileLetter;
    private Label nameLabel;
    private Label emailHeaderLabel;

    private TextField membershipDaysField;

    // added by Aditya--------------------------------------------------------------------------------------------------------------------

    private Label weightStatValue;
    private Label heightStatValue;
    private Label workoutStatValue;
//-----------------------------------------------------------------------------------------------------------------------------------------
    // ============================================================
    // COLORS
    // ============================================================

    private final String BACKGROUND = "#0B0F14";
    private final String CARD = "#151C22";
    private final String CARD_BORDER = "#2A3541";
    private final String GREEN = "#c6ff00";
    private final String WHITE = "#F5F7FA";
    private final String GRAY = "#B8C1CC";
    private final String FIELD = "#1A232D";

    // ============================================================
    // CREATE PROFILE PAGE
    // ============================================================

    public ScrollPane createProfilePage() {

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(
                        28,
                        38,
                        35,
                        38
                )
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        // ========================================================
        // TOP BAR
        // ========================================================

        HBox topBar =
                new HBox(15);

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        Label pageTitle =
                new Label("My Profile");

        pageTitle.setStyle(
                "-fx-font-size:30px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        WHITE + ";"
        );

        Button editButton =
                new Button("✎  Edit Profile");

        Button saveButton =
                new Button("✓  Save Changes");

        editButton.setPrefHeight(40);
        saveButton.setPrefHeight(40);

        String buttonNormalStyle =
                "-fx-background-color:" + GREEN + ";" +
                        "-fx-text-fill:#000000;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8;" +
                        "-fx-padding:0 18 0 18;" +
                        "-fx-cursor:hand;";

        String buttonHoverStyle =
                "-fx-background-color:#d9ff55;" +
                        "-fx-text-fill:#000000;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8;" +
                        "-fx-padding:0 18 0 18;" +
                        "-fx-cursor:hand;";

        editButton.setStyle(
                buttonNormalStyle
        );

        saveButton.setStyle(
                buttonNormalStyle
        );

        editButton.setOnMouseEntered(e ->
                editButton.setStyle(
                        buttonHoverStyle
                )
        );

        editButton.setOnMouseExited(e ->
                editButton.setStyle(
                        buttonNormalStyle
                )
        );

        saveButton.setOnMouseEntered(e ->
                saveButton.setStyle(
                        buttonHoverStyle
                )
        );

        saveButton.setOnMouseExited(e ->
                saveButton.setStyle(
                        buttonNormalStyle
                )
        );

        HBox.setHgrow(
                pageTitle,
                Priority.ALWAYS
        );

        topBar.getChildren().addAll(
                pageTitle,
                editButton,
                saveButton
        );

        // ========================================================
        // PROFILE HEADER
        // ========================================================

        VBox profileHeader =
                new VBox(15);

        profileHeader.setPadding(
                new Insets(22)
        );

        profileHeader.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"
        );

        HBox profileDetails =
                new HBox(20);

        profileDetails.setAlignment(
                Pos.CENTER_LEFT
        );

        // ========================================================
        // PROFILE INITIAL
        // ========================================================

        VBox profileIcon =
                new VBox();

        profileIcon.setAlignment(
                Pos.CENTER
        );

        profileIcon.setPrefWidth(85);
        profileIcon.setPrefHeight(85);

        profileIcon.setMinWidth(85);
        profileIcon.setMinHeight(85);

        profileIcon.setStyle(
                "-fx-background-color:" +
                        GREEN + ";" +
                        "-fx-background-radius:50;"
        );

        profileLetter =
                new Label(
                        userProfileController.getInitial()
                );

        profileLetter.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        profileLetter.setTextFill(
                Color.BLACK
        );

        profileIcon.getChildren().add(
                profileLetter
        );

        // ========================================================
        // NAME / EMAIL
        // ========================================================

        VBox nameBox =
                new VBox(7);

        nameLabel =
                new Label("Loading...");

        nameLabel.setStyle(
                "-fx-font-size:24px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        WHITE + ";"
        );

        Label memberLabel =
                new Label("Fitness Member");

        memberLabel.setStyle(
                "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GREEN + ";"
        );

        emailHeaderLabel =
                new Label("Loading...");

        emailHeaderLabel.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        nameBox.getChildren().addAll(
                nameLabel,
                memberLabel,
                emailHeaderLabel
        );

        profileDetails.getChildren().addAll(
                profileIcon,
                nameBox
        );

        profileHeader.getChildren().add(
                profileDetails
        );

        // ========================================================
        // CREATE FIELDS
        // ========================================================

        fullNameField =
                createTextField(
                        "Enter full name"
                );

        emailField =
                createTextField(
                        "Enter email"
                );

        phoneField =
                createTextField(
                        "Enter phone number"
                );

        dobField =
                createTextField(
                        "DD/MM/YYYY"
                );

        genderField =
                new ComboBox<>();

        genderField.getItems().addAll(
                "Male",
                "Female",
                "Other"
        );

        styleComboBox(
                genderField
        );

        addressField =
                createTextField(
                        "Enter address"
                );

        heightField =
                createTextField(
                        "Enter height"
                );

        weightField =
                createTextField(
                        "Enter weight"
                );

        goalField =
                createTextField(
                        "Enter fitness goal"
                );

        planField =
                createTextField(
                        "Enter membership plan"
                );

        statusField =
                createTextField(
                        "Enter status"
                );

        joiningDateField =
                createTextField(
                        "DD/MM/YYYY"
                );

        workoutDaysField =
                createTextField(
                        "Enter workout days"
                );
//---------------------------------------------------------------------------------------------------------------------------

        // ========================================================
        // UPDATE TOP STATISTICS WHEN FIREBASE DATA LOADS
        // ========================================================

        heightField.textProperty().addListener(
                (obs, oldValue, newValue) -> updateStatValues()
        );

        weightField.textProperty().addListener(
                (obs, oldValue, newValue) -> updateStatValues()
        );

        workoutDaysField.textProperty().addListener(
                (obs, oldValue, newValue) -> updateStatValues()
        );
//----------------------------------------------------------------------------------------------------------------------------------






        // ========================================================
        // LOAD FIREBASE DATA
        // ========================================================

        userProfileController.loadUserProfile(

                fullNameField,
                emailField,
                phoneField,
                dobField,
                genderField,
                addressField,
                heightField,
                weightField,
                goalField,
                planField,
                statusField,
                joiningDateField,
                workoutDaysField
        );

// Calculate membership days after Firebase data is loaded.
updateMembershipDays();
//--------------------------------------------------------------------------------------------------------------------------------------
       
//-------------------------------------------------------------------------------------------------------------------------------------
        // ========================================================
        // NAME / EMAIL UPDATE
        // ========================================================

        updateHeader();

        // ========================================================
        // STATS
        // ========================================================
GridPane statsGrid = new GridPane();

statsGrid.setHgap(15);
statsGrid.setVgap(15);

weightStatValue = new Label("—");
heightStatValue = new Label("—");
workoutStatValue = new Label("—");

weightStatValue.setStyle(
        "-fx-font-size:16px;" +
        "-fx-font-weight:bold;" +
        "-fx-text-fill:" + WHITE + ";"
);

heightStatValue.setStyle(
        "-fx-font-size:16px;" +
        "-fx-font-weight:bold;" +
        "-fx-text-fill:" + WHITE + ";"
);

workoutStatValue.setStyle(
        "-fx-font-size:16px;" +
        "-fx-font-weight:bold;" +
        "-fx-text-fill:" + WHITE + ";"
);

VBox weightBox =
        createStatBox(
                "Current Weight",
                weightStatValue,
                "kg"
        );

VBox heightBox =
        createStatBox(
                "Height",
                heightStatValue,
                "cm"
        );

VBox workoutBox =
        createStatBox(
                "Workout Days",
                workoutStatValue,
                "days/week"
        );

membershipDaysField =
        createTextField("0 days");

// Membership days are calculated automatically.
membershipDaysField.setEditable(false);

// Recalculate when the joining date changes.
joiningDateField.textProperty().addListener(
        (obs, oldValue, newValue) -> updateMembershipDays()
);

VBox membershipBox =
        createStatInputBox(
                "Membership Days",
                membershipDaysField
        );

statsGrid.add(weightBox, 0, 0);
statsGrid.add(heightBox, 1, 0);
statsGrid.add(workoutBox, 2, 0);
statsGrid.add(membershipBox, 3, 0);

setEqualColumns(statsGrid, 4);

// Initial synchronization-----------------------------------------------------
updateStatValues();
//----------------------------------------------------------------------------------


statsGrid.setMaxWidth(Double.MAX_VALUE);
statsGrid.setMinHeight(105);
//--------------------------------------------------------------------------------------------------------------------------------------
        // ========================================================
        // INFORMATION AREA
        // ========================================================

        HBox informationArea =
                new HBox(15);

        informationArea.setFillHeight(
                true
        );

        // ========================================================
        // PERSONAL INFORMATION
        // ========================================================

        VBox personalBox =
                createSectionBox();

        Label personalTitle =
                createSectionTitle(
                        "Personal Information"
                );

        GridPane personalGrid =
                new GridPane();

        personalGrid.setHgap(15);
        personalGrid.setVgap(15);

        addField(
                personalGrid,
                "Full Name",
                fullNameField,
                0
        );

        addField(
                personalGrid,
                "Email",
                emailField,
                1
        );

        addField(
                personalGrid,
                "Phone",
                phoneField,
                2
        );

        addField(
                personalGrid,
                "Date of Birth",
                dobField,
                3
        );

        addComboField(
                personalGrid,
                "Gender",
                genderField,
                4
        );

        addField(
                personalGrid,
                "Address",
                addressField,
                5
        );

        ColumnConstraints personalLabelColumn =
                new ColumnConstraints();

        ColumnConstraints personalFieldColumn =
                new ColumnConstraints();

        personalLabelColumn.setPercentWidth(30);
        personalFieldColumn.setPercentWidth(70);

        personalGrid.getColumnConstraints()
                .addAll(
                        personalLabelColumn,
                        personalFieldColumn
                );

        personalBox.getChildren().addAll(
                personalTitle,
                personalGrid
        );

        // ========================================================
        // FITNESS INFORMATION
        // ========================================================

        VBox fitnessBox =
                createSectionBox();

        Label fitnessTitle =
                createSectionTitle(
                        "Fitness Information"
                );

        GridPane fitnessGrid =
                new GridPane();

        fitnessGrid.setHgap(15);
        fitnessGrid.setVgap(15);

        addFieldWithUnit(
                fitnessGrid,
                "Height",
                heightField,
                "cm",
                0
        );

        addFieldWithUnit(
                fitnessGrid,
                "Weight",
                weightField,
                "kg",
                1
        );

        addField(
                fitnessGrid,
                "Goal",
                goalField,
                2
        );

        addField(
                fitnessGrid,
                "Plan",
                planField,
                3
        );

        addField(
                fitnessGrid,
                "Status",
                statusField,
                4
        );

        addField(
                fitnessGrid,
                "Joining Date",
                joiningDateField,
                5
        );

        addField(
                fitnessGrid,
                "Workout Days",
                workoutDaysField,
                6
        );

        ColumnConstraints fitnessLabelColumn =
                new ColumnConstraints();

        ColumnConstraints fitnessFieldColumn =
                new ColumnConstraints();

        fitnessLabelColumn.setPercentWidth(30);
        fitnessFieldColumn.setPercentWidth(70);

        fitnessGrid.getColumnConstraints()
                .addAll(
                        fitnessLabelColumn,
                        fitnessFieldColumn
                );

        fitnessBox.getChildren().addAll(
                fitnessTitle,
                fitnessGrid
        );

        HBox.setHgrow(
                personalBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                fitnessBox,
                Priority.ALWAYS
        );

        personalBox.setMaxWidth(
                Double.MAX_VALUE
        );

        fitnessBox.setMaxWidth(
                Double.MAX_VALUE
        );

        informationArea.getChildren().addAll(
                personalBox,
                fitnessBox
        );

        // ========================================================
        // RECENT ACTIVITY
        // ========================================================

        VBox activityBox =
                createSectionBox();

        HBox activityHeader =
                new HBox();

        activityHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        Label activityTitle =
                createSectionTitle(
                        "Recent Activity"
                );

        Button viewAllButton =
                new Button("View All");

        viewAllButton.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-cursor:hand;"
        );

        HBox.setHgrow(
                activityTitle,
                Priority.ALWAYS
        );

        activityHeader.getChildren().addAll(
                activityTitle,
                viewAllButton
        );

        VBox activityList =
                new VBox(8);

        loadRecentActivities(
                activityList
        );

        activityBox.getChildren().addAll(
                activityHeader,
                activityList
        );

        // ========================================================
        // VIEW ALL ACTION
        // ========================================================

        viewAllButton.setOnAction(e -> {

            openAllActivitiesPage();
        });

        // ========================================================
        // EDIT BUTTON
        // ========================================================

        editButton.setOnAction(e -> {

            setFieldsEditable(true);

            System.out.println(
                    "Profile editing enabled."
            );
        });

        // ========================================================
        // SAVE BUTTON
        // ========================================================

        saveButton.setOnAction(e -> {

            boolean updated =
                    userProfileController
                            .updateUserProfile(

                                    fullNameField,
                                    emailField,
                                    phoneField,
                                    dobField,
                                    genderField,
                                    addressField,
                                    heightField,
                                    weightField,
                                    goalField,
                                    planField,
                                    statusField,
                                    joiningDateField,
                                    workoutDaysField
                            );

            if (updated) {

                System.out.println(
                        "Profile saved successfully."
                );

                setFieldsEditable(false);

                updateHeader();

                // Recalculate instead of reading a stored/static value.
                updateMembershipDays();
//-----------------------------------------------------------------------------------------------
                    updateStatValues();
//-------------------------------------------------------------------------------------------------------
            } else {

                System.out.println(
                        "Profile save failed."
                );

                setFieldsEditable(true);
            }
        });

        // ========================================================
        // INITIAL EDIT MODE
        // ========================================================

        setFieldsEditable(false);

        // ========================================================
        // CONTENT
        // ========================================================

        content.getChildren().addAll(
                topBar,
                profileHeader,
                statsGrid,
                informationArea,
                activityBox
        );

        // ========================================================
        // SCROLL PANE
        // ========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-background:" +
                        BACKGROUND + ";"
        );

        return scrollPane;
    }

    // ============================================================
    // UPDATE HEADER
    // ============================================================

    private void updateHeader() {

        if (nameLabel != null) {

            String name =
                    fullNameField.getText();

            if (name == null ||
                    name.trim().isEmpty()) {

                nameLabel.setText(
                        "User"
                );

            } else {

                nameLabel.setText(
                        name
                );
            }
        }

        if (emailHeaderLabel != null) {

            emailHeaderLabel.setText(
                    emailField.getText()
            );
        }

        if (profileLetter != null) {

            String name =
                    fullNameField.getText();

            if (name != null &&
                    !name.trim().isEmpty()) {

                profileLetter.setText(
                        name.trim()
                                .substring(0, 1)
                                .toUpperCase()
                );

            } else {

                profileLetter.setText(
                        "U"
                );
            }
        }
    }

    // ============================================================
    // EDIT MODE
    // ============================================================

    private void setFieldsEditable(
            boolean editable) {

        if (fullNameField != null) {
            fullNameField.setEditable(
                    editable
            );
        }

        if (emailField != null) {
            emailField.setEditable(
                    editable
            );
        }

        if (phoneField != null) {
            phoneField.setEditable(
                    editable
            );
        }

        if (dobField != null) {
            dobField.setEditable(
                    editable
            );
        }

        if (genderField != null) {
            genderField.setDisable(
                    !editable
            );
        }

        if (addressField != null) {
            addressField.setEditable(
                    editable
            );
        }

        if (workoutDaysField != null) {
            workoutDaysField.setEditable(
                    editable
            );
        }

        if (heightField != null) {
            heightField.setEditable(
                    editable
            );
        }

        if (weightField != null) {
            weightField.setEditable(
                    editable
            );
        }

        if (goalField != null) {
            goalField.setEditable(
                    editable
            );
        }

        /*
         * Plan आणि Status पण edit करता येतील.
         */
        if (planField != null) {
            planField.setEditable(
                    editable
            );
        }

        if (statusField != null) {
            statusField.setEditable(
                    editable
            );
        }

        if (joiningDateField != null) {
            joiningDateField.setEditable(
                    editable
            );
        }
    }

    // ============================================================
    // TEXT FIELD
    // ============================================================

    private TextField createTextField(
            String prompt) {

        TextField field =
                new TextField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(38);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        applyNormalFieldStyle(
                field
        );

        field.focusedProperty()
                .addListener(
                        (obs,
                         oldValue,
                         newValue) -> {

                            if (newValue) {

                                applyFocusFieldStyle(
                                        field
                                );

                            } else {

                                applyNormalFieldStyle(
                                        field
                                );
                            }
                        }
                );

        return field;
    }

    // ============================================================
    // NORMAL FIELD STYLE
    // ============================================================

    private void applyNormalFieldStyle(
            TextField field) {

        field.setStyle(
                "-fx-background-color:" +
                        FIELD + ";" +
                        "-fx-text-fill:" +
                        WHITE + ";" +
                        "-fx-prompt-text-fill:#71808F;" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:7;" +
                        "-fx-background-radius:7;" +
                        "-fx-padding:8 10 8 10;"
        );
    }

    // ============================================================
    // FOCUS FIELD STYLE
    // ============================================================

    private void applyFocusFieldStyle(
            TextField field) {

        field.setStyle(
                "-fx-background-color:" +
                        FIELD + ";" +
                        "-fx-text-fill:" +
                        WHITE + ";" +
                        "-fx-prompt-text-fill:#71808F;" +
                        "-fx-border-color:" +
                        GREEN + ";" +
                        "-fx-border-radius:7;" +
                        "-fx-background-radius:7;" +
                        "-fx-padding:8 10 8 10;"
        );
    }

    // ============================================================
    // COMBO BOX STYLE
    // ============================================================

   /*  private void styleComboBox(
            ComboBox<String> comboBox) {

        comboBox.setPrefHeight(38);

        comboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        comboBox.setStyle(
                "-fx-background-color:" +
                        FIELD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:7;" +
                        "-fx-background-radius:7;" +
                        "-fx-text-fill:" +
                        WHITE + ";"
        );
    }*/

        private void styleComboBox(
        ComboBox<String> comboBox) {

    comboBox.setPrefHeight(38);

    comboBox.setMaxWidth(
            Double.MAX_VALUE
    );

    comboBox.setStyle(
            "-fx-background-color:" + FIELD + ";" +
            "-fx-border-color:" + CARD_BORDER + ";" +
            "-fx-border-radius:7;" +
            "-fx-background-radius:7;" +
            "-fx-text-fill:" + WHITE + ";" +
            "-fx-opacity:1;"
    );

    // Selected value displayed inside ComboBox
    comboBox.setButtonCell(
            new javafx.scene.control.ListCell<String>() {

        @Override
        protected void updateItem(
                String item,
                boolean empty) {

            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item);
                setTextFill(Color.WHITE);
            }

            setStyle(
                    "-fx-background-color:" + FIELD + ";" +
                    "-fx-text-fill:" + WHITE + ";"
            );
        }
    });

    // Dropdown options
    comboBox.setCellFactory(listView ->
            new javafx.scene.control.ListCell<String>() {

        @Override
        protected void updateItem(
                String item,
                boolean empty) {

            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item);
                setTextFill(Color.BLACK);
            }
        }
    });
}

    // ============================================================
    // SECTION BOX
    // ============================================================

    private VBox createSectionBox() {

        VBox box =
                new VBox(18);

        box.setPadding(
                new Insets(20)
        );

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        return box;
    }

    // ============================================================
    // SECTION TITLE
    // ============================================================

    private Label createSectionTitle(
            String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size:17px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        WHITE + ";"
        );

        return label;
    }

    // ============================================================
    // ADD FIELD
    // ============================================================

    private void addField(
            GridPane grid,
            String title,
            TextField field,
            int row) {

        Label label =
                new Label(title);

        label.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        grid.add(
                label,
                0,
                row
        );

        grid.add(
                field,
                1,
                row
        );

        GridPane.setHgrow(
                field,
                Priority.ALWAYS
        );
    }

    // ============================================================
    // ADD COMBO FIELD
    // ============================================================

    private void addComboField(
            GridPane grid,
            String title,
            ComboBox<String> field,
            int row) {

        Label label =
                new Label(title);

        label.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        grid.add(
                label,
                0,
                row
        );

        grid.add(
                field,
                1,
                row
        );

        GridPane.setHgrow(
                field,
                Priority.ALWAYS
        );
    }

    // ============================================================
    // ADD FIELD WITH UNIT
    // ============================================================

    private void addFieldWithUnit(
            GridPane grid,
            String title,
            TextField field,
            String unit,
            int row) {

        Label label =
                new Label(title);

        label.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        HBox fieldBox =
                new HBox(8);

        fieldBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Label unitLabel =
                new Label(unit);

        unitLabel.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GREEN + ";"
        );

        fieldBox.getChildren().addAll(
                field,
                unitLabel
        );

        HBox.setHgrow(
                field,
                Priority.ALWAYS
        );

        grid.add(
                label,
                0,
                row
        );

        grid.add(
                fieldBox,
                1,
                row
        );
    }

    // ============================================================
    // STAT BOX
    // ============================================================

    private VBox createStatBox(
            String title,
            Label field,
            String unit) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        Label unitLabel =
                new Label(unit);

        unitLabel.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-weight:bold;"
        );

        HBox inputBox =
                new HBox(7);

        inputBox.setAlignment(
                Pos.CENTER_LEFT
        );

        inputBox.getChildren().addAll(
                field,
                unitLabel
        );

        HBox.setHgrow(
                field,
                Priority.ALWAYS
        );

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(15)
        );

        box.setMinHeight(105);

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"
        );

        box.getChildren().addAll(
                titleLabel,
                inputBox
        );

        return box;
    }

    // ============================================================
    // MEMBERSHIP STAT BOX
    // ============================================================

    private VBox createStatInputBox(
            String title,
            TextField field) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(15)
        );

        box.setMinHeight(105);

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"
        );

        box.getChildren().addAll(
                titleLabel,
                field
        );

        return box;
    }

    // ============================================================
    // RECENT ACTIVITIES
    // ============================================================

    private void loadRecentActivities(
            VBox activityList) {

        activityList.getChildren().clear();

        try {

            String userId =
                    userProfileController
                            .getCurrentUserId();

            List<DocumentSnapshot> activities =
                    activityDAO.getRecentActivities(
                            userId,
                            3
                    );

            if (activities == null ||
                    activities.isEmpty()) {

                Label empty =
                        createActivity(
                                "No recent activities found."
                        );

                activityList.getChildren()
                        .add(empty);

                return;
            }

            for (
                    DocumentSnapshot document :
                    activities
            ) {

                String activityText =
                        getActivityText(
                                document
                        );

                Label activity =
                        createActivity(
                                activityText
                        );

                activityList.getChildren()
                        .add(activity);
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    createActivity(
                            "Unable to load activities."
                    );

            activityList.getChildren()
                    .add(error);
        }
    }

    // ============================================================
    // GET ACTIVITY TEXT
    // ============================================================

    private String getActivityText(
            DocumentSnapshot document) {

        String text =
                document.getString(
                        "activity"
                );

        if (text == null ||
                text.trim().isEmpty()) {

            text =
                    document.getString(
                            "description"
                    );
        }

        if (text == null ||
                text.trim().isEmpty()) {

            text =
                    document.getString(
                            "title"
                    );
        }

        if (text == null ||
                text.trim().isEmpty()) {

            text =
                    document.getString(
                            "message"
                    );
        }

        if (text == null ||
                text.trim().isEmpty()) {

            text =
                    "Activity recorded";
        }

        return text;
    }

    // ============================================================
    // ACTIVITY LABEL
    // ============================================================

    private Label createActivity(
            String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-text-fill:" +
                        GRAY + ";" +
                        "-fx-padding:5 0 5 0;"
        );

        return label;
    }

    // ============================================================
    // VIEW ALL ACTIVITIES PAGE
    // ============================================================

    private void openAllActivitiesPage() {

        VBox root =
                new VBox(15);

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        // ========================================================
        // HEADER
        // ========================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "All Activities"
                );

        title.setStyle(
                "-fx-font-size:26px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        WHITE + ";"
        );

        Button closeButton =
                new Button("Close");

        closeButton.setStyle(
                "-fx-background-color:" +
                        GREEN + ";" +
                        "-fx-text-fill:#000000;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:7;" +
                        "-fx-cursor:hand;"
        );

        HBox.setHgrow(
                title,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                title,
                closeButton
        );

        // ========================================================
        // ACTIVITY LIST
        // ========================================================

        VBox activityList =
                new VBox(12);

        ScrollPane scrollPane =
                new ScrollPane(
                        activityList
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-background:" +
                        BACKGROUND + ";"
        );

        loadAllActivities(
                activityList
        );

        root.getChildren().addAll(
                header,
                scrollPane
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        Stage stage =
                new Stage();

        stage.setTitle(
                "FitCircle - All Activities"
        );

        Scene scene =
                new Scene(
                        root,
                        750,
                        600
                );

        stage.setScene(scene);

        closeButton.setOnAction(
                e -> stage.close()
        );

        stage.show();
    }

    // ============================================================
    // LOAD ALL ACTIVITIES
    // ============================================================

    private void loadAllActivities(
            VBox activityList) {

        activityList.getChildren().clear();

        try {

            String userId =
                    userProfileController
                            .getCurrentUserId();

            List<DocumentSnapshot> activities =
                    activityDAO.getAllActivities(
                            userId
                    );

            if (activities == null ||
                    activities.isEmpty()) {

                Label empty =
                        new Label(
                                "No activities found."
                        );

                empty.setStyle(
                        "-fx-font-size:15px;" +
                                "-fx-text-fill:" +
                                GRAY + ";"
                );

                activityList.getChildren()
                        .add(empty);

                return;
            }

            for (
                    DocumentSnapshot document :
                    activities
            ) {

                VBox activityCard =
                        createActivityCard(
                                document
                        );

                activityList.getChildren()
                        .add(activityCard);
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load activities."
                    );

            error.setStyle(
                    "-fx-text-fill:#F87171;" +
                            "-fx-font-size:14px;"
            );

            activityList.getChildren()
                    .add(error);
        }
    }

    // ============================================================
    // ACTIVITY CARD
    // ============================================================

    private VBox createActivityCard(
            DocumentSnapshot document) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(15)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"
        );

        String activityText =
                getActivityText(
                        document
                );

        Label activity =
                new Label(
                        activityText
                );

        activity.setStyle(
                "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        WHITE + ";"
        );

        String dateText =
                getDateText(
                        document
                );

        Label date =
                new Label(
                        dateText
                );

        date.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:" +
                        GRAY + ";"
        );

        card.getChildren().addAll(
                activity,
                date
        );

        return card;
    }

    // ============================================================
    // GET DATE
    // ============================================================

    private String getDateText(
            DocumentSnapshot document) {

        String date =
                document.getString(
                        "date"
                );

        if (date == null ||
                date.trim().isEmpty()) {

            date =
                    document.getString(
                            "activityDate"
                    );
        }

        if (date == null ||
                date.trim().isEmpty()) {

            date =
                    document.getString(
                            "createdAt"
                    );
        }

        if (date == null ||
                date.trim().isEmpty()) {

            return "";
        }

        return date;
    }

    // ============================================================
    // EQUAL COLUMNS
    // ============================================================

    private void setEqualColumns(
            GridPane grid,
            int numberOfColumns) {

        double width =
                100.0 /
                        numberOfColumns;

        for (
                int i = 0;
                i < numberOfColumns;
                i++
        ) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(
                    width
            );

            grid.getColumnConstraints()
                    .add(column);
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------
   
    //----------------------------------------------------------------------------------------------------------------------------------
    // ============================================================
        // UPDATE TOP STATISTICS
        // ============================================================

        // ============================================================
        // CALCULATE MEMBERSHIP DAYS FROM JOINING DATE
        // ============================================================
        private void updateMembershipDays() {

            if (membershipDaysField == null ||
                    joiningDateField == null) {
                return;
            }

            String joiningDate = joiningDateField.getText();

            if (joiningDate == null || joiningDate.trim().isEmpty()) {
                membershipDaysField.setText("0 days");
                return;
            }

            String value = joiningDate.trim();
            LocalDate startDate = null;

            try {
                // Supports DD/MM/YYYY, e.g. 12/10/2025
                if (value.contains("/")) {
                    startDate = LocalDate.parse(
                            value,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );
                } else {
                    // Also supports DDMMYYYY, e.g. 12102025
                    startDate = LocalDate.parse(
                            value,
                            DateTimeFormatter.ofPattern("ddMMyyyy")
                    );
                }

                LocalDate today = LocalDate.now();

                long days = ChronoUnit.DAYS.between(
                        startDate,
                        today
                );

                if (days < 0) {
                    days = 0;
                }

                membershipDaysField.setText(
                        days + " days"
                );

            } catch (Exception e) {
                membershipDaysField.setText("0 days");
                System.out.println(
                        "Invalid joining date: " + value
                );
            }
        }

        private void updateStatValues() {

        if (weightStatValue != null) {
                String value = weightField.getText();

                weightStatValue.setText(
                        value == null || value.trim().isEmpty()
                                ? "—"
                                : value.trim()
                );
        }

        if (heightStatValue != null) {
                String value = heightField.getText();

                heightStatValue.setText(
                        value == null || value.trim().isEmpty()
                                ? "—"
                                : value.trim()
                );
        }

        if (workoutStatValue != null) {
                String value = workoutDaysField.getText();

                workoutStatValue.setText(
                        value == null || value.trim().isEmpty()
                                ? "—"
                                : value.trim()
                );
        }
        }
}
