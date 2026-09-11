

package com.flexforce.view.club_owner;

import java.time.LocalDate;
import java.util.UUID;

import com.flexforce.controller.ClubownerActivityController;
import com.flexforce.model.club_owner.ClubOwnerActivity;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Club_CreateActivity {

    // =========================================================
    // CONTROLLER
    // =========================================================

    private final ClubownerActivityController activityController;

    // =========================================================
    // CLUB / OWNER INFORMATION
    // =========================================================

    private String clubId = "";
    private String createdBy = "";

    // =========================================================
    // CALLBACK
    // =========================================================

    private Runnable onActivityCreated;

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private TextField activityNameField;
    private ComboBox<String> activityTypeCombo;
    private ComboBox<String> difficultyCombo;

    private DatePicker datePicker;

    private TextField startTimeField;
    private TextField endTimeField;

    private TextField locationField;
    private TextField trainerField;

    private TextField maximumParticipantsField;

    private TextArea descriptionArea;

    private Button createActivityButton;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Club_CreateActivity() {

        activityController =
                new ClubownerActivityController();
    }

    // =========================================================
    // SET CLUB ID
    // =========================================================

    public void setClubId(String clubId) {

        this.clubId =
                clubId == null
                        ? ""
                        : clubId.trim();
    }

    // =========================================================
    // SET OWNER ID
    // =========================================================

    public void setCreatedBy(String createdBy) {

        this.createdBy =
                createdBy == null
                        ? ""
                        : createdBy.trim();
    }

    // =========================================================
    // CALLBACK
    // =========================================================

    public void setOnActivityCreated(
            Runnable callback) {

        this.onActivityCreated = callback;
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    public VBox createActivityUI() {

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(
                        25,
                        30,
                        30,
                        30
                )
        );

        main.setMaxWidth(
                Double.MAX_VALUE
        );

        main.setStyle(
                "-fx-background-color:#0b0f13;"
        );

        // =====================================================
        // HEADER
        // =====================================================

        Label title =
                new Label(
                        "Create Activity"
                );

        title.setStyle(
                "-fx-text-fill:#b8ff00;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Create a new activity for your club members"
                );

        subtitle.setStyle(
                "-fx-text-fill:#b8c0ca;" +
                "-fx-font-size:13px;"
        );

        VBox headingBox =
                new VBox(
                        5,
                        title,
                        subtitle
                );

        // =====================================================
        // FORM CARD
        // =====================================================

        VBox formCard =
                new VBox(20);

        formCard.setPadding(
                new Insets(25)
        );

        formCard.setMaxWidth(
                Double.MAX_VALUE
        );

        formCard.setStyle(
                "-fx-background-color:#151c24;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:10px;" +
                "-fx-background-radius:10px;"
        );

        // =====================================================
        // SECTION TITLE
        // =====================================================

        Label sectionTitle =
                new Label(
                        "Activity Information"
                );

        sectionTitle.setStyle(
                "-fx-text-fill:#ffffff;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // GRID
        // =====================================================

        GridPane grid =
                new GridPane();

        grid.setHgap(18);
        grid.setVgap(15);

        ColumnConstraints c1 =
                new ColumnConstraints();

        ColumnConstraints c2 =
                new ColumnConstraints();

        ColumnConstraints c3 =
                new ColumnConstraints();

        c1.setPercentWidth(33.33);
        c2.setPercentWidth(33.33);
        c3.setPercentWidth(33.34);

        c1.setHgrow(Priority.ALWAYS);
        c2.setHgrow(Priority.ALWAYS);
        c3.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints()
                .addAll(
                        c1,
                        c2,
                        c3
                );

        // =====================================================
        // ACTIVITY NAME
        // =====================================================

        Label activityNameLabel =
                createFieldLabel(
                        "Activity Name"
                );

        activityNameField =
                new TextField();

        activityNameField.setPromptText(
                "Enter activity name"
        );

        styleTextField(
                activityNameField
        );

        // =====================================================
        // ACTIVITY TYPE
        // =====================================================

        Label activityTypeLabel =
                createFieldLabel(
                        "Activity Type"
                );

        activityTypeCombo =
                new ComboBox<>();

        activityTypeCombo.getItems().addAll(
                "Workout",
                "Yoga",
                "Cardio",
                "Strength Training",
                "Zumba",
                "Running",
                "Cycling",
                "Meditation",
                "Sports",
                "Other"
        );

        activityTypeCombo.setPromptText(
                "Select activity type"
        );

        styleComboBox(
                activityTypeCombo
        );

        // =====================================================
        // DIFFICULTY
        // =====================================================

        Label difficultyLabel =
                createFieldLabel(
                        "Difficulty"
                );

        difficultyCombo =
                new ComboBox<>();

        difficultyCombo.getItems().addAll(
                "Beginner",
                "Intermediate",
                "Advanced"
        );

        difficultyCombo.setPromptText(
                "Select difficulty"
        );

        styleComboBox(
                difficultyCombo
        );

        // =====================================================
        // ROW 1
        // =====================================================

        grid.add(
                activityNameLabel,
                0,
                0
        );

        grid.add(
                activityNameField,
                0,
                1
        );

        grid.add(
                activityTypeLabel,
                1,
                0
        );

        grid.add(
                activityTypeCombo,
                1,
                1
        );

        grid.add(
                difficultyLabel,
                2,
                0
        );

        grid.add(
                difficultyCombo,
                2,
                1
        );

        // =====================================================
        // DATE
        // =====================================================

        Label dateLabel =
                createFieldLabel(
                        "Date"
                );

        datePicker =
                new DatePicker();

        datePicker.setValue(
                LocalDate.now()
        );

        datePicker.setPrefHeight(
                40
        );

        datePicker.setMaxWidth(
                Double.MAX_VALUE
        );

        datePicker.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );

        // =====================================================
        // START TIME
        // =====================================================

        Label startTimeLabel =
                createFieldLabel(
                        "Start Time"
                );

        startTimeField =
                new TextField();

        startTimeField.setPromptText(
                "e.g. 10:00 AM"
        );

        styleTextField(
                startTimeField
        );

        // =====================================================
        // END TIME
        // =====================================================

        Label endTimeLabel =
                createFieldLabel(
                        "End Time"
                );

        endTimeField =
                new TextField();

        endTimeField.setPromptText(
                "e.g. 11:00 AM"
        );

        styleTextField(
                endTimeField
        );

        // =====================================================
        // ROW 2
        // =====================================================

        grid.add(
                dateLabel,
                0,
                2
        );

        grid.add(
                datePicker,
                0,
                3
        );

        grid.add(
                startTimeLabel,
                1,
                2
        );

        grid.add(
                startTimeField,
                1,
                3
        );

        grid.add(
                endTimeLabel,
                2,
                2
        );

        grid.add(
                endTimeField,
                2,
                3
        );

        // =====================================================
        // LOCATION
        // =====================================================

        Label locationLabel =
                createFieldLabel(
                        "Location"
                );

        locationField =
                new TextField();

        locationField.setPromptText(
                "Enter activity location"
        );

        styleTextField(
                locationField
        );

        // =====================================================
        // TRAINER
        // =====================================================

        Label trainerLabel =
                createFieldLabel(
                        "Trainer"
                );

        trainerField =
                new TextField();

        trainerField.setPromptText(
                "Enter trainer name"
        );

        styleTextField(
                trainerField
        );

        // =====================================================
        // MAX PARTICIPANTS
        // =====================================================

        Label maximumParticipantsLabel =
                createFieldLabel(
                        "Maximum Participants"
                );

        maximumParticipantsField =
                new TextField();

        maximumParticipantsField.setPromptText(
                "Enter maximum participants"
        );

        styleTextField(
                maximumParticipantsField
        );

        // =====================================================
        // ROW 3
        // =====================================================

        grid.add(
                locationLabel,
                0,
                4
        );

        grid.add(
                locationField,
                0,
                5
        );

        grid.add(
                trainerLabel,
                1,
                4
        );

        grid.add(
                trainerField,
                1,
                5
        );

        grid.add(
                maximumParticipantsLabel,
                2,
                4
        );

        grid.add(
                maximumParticipantsField,
                2,
                5
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label descriptionLabel =
                createFieldLabel(
                        "Description"
                );

        descriptionArea =
                new TextArea();

        descriptionArea.setPromptText(
                "Enter activity description..."
        );

        descriptionArea.setWrapText(
                true
        );

        descriptionArea.setPrefRowCount(
                5
        );

        descriptionArea.setStyle(
                "-fx-control-inner-background:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#77808a;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;"
        );

        VBox descriptionBox =
                new VBox(
                        7,
                        descriptionLabel,
                        descriptionArea
                );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button cancelButton =
                new Button(
                        "Cancel"
                );

        cancelButton.setPrefWidth(
                100
        );

        cancelButton.setPrefHeight(
                40
        );

        cancelButton.setStyle(
                "-fx-background-color:#202a34;" +
                "-fx-text-fill:#d5dde5;" +
                "-fx-border-color:#35414d;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;" +
                "-fx-cursor:hand;"
        );

        createActivityButton =
                new Button(
                        "Create Activity"
                );

        createActivityButton.setPrefWidth(
                145
        );

        createActivityButton.setPrefHeight(
                40
        );

        createActivityButton.setStyle(
                "-fx-background-color:#b8ff00;" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );

        HBox buttonBox =
                new HBox(
                        10,
                        cancelButton,
                        createActivityButton
                );

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        createActivityButton.setOnAction(
                e -> createActivity()
        );

        cancelButton.setOnAction(
                e -> clearForm()
        );

        // =====================================================
        // FORM CARD
        // =====================================================

        formCard.getChildren().addAll(
                sectionTitle,
                grid,
                descriptionBox,
                buttonBox
        );

        // =====================================================
        // MAIN
        // =====================================================

        main.getChildren().addAll(
                headingBox,
                formCard
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(main);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setStyle(
                "-fx-background:#0b0f13;" +
                "-fx-background-color:#0b0f13;"
        );

        VBox container =
                new VBox(
                        scrollPane
                );

        container.setMaxWidth(
                Double.MAX_VALUE
        );

        container.setMaxHeight(
                Double.MAX_VALUE
        );

        container.setStyle(
                "-fx-background-color:#0b0f13;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        return container;
    }

    // =========================================================
    // CREATE ACTIVITY
    // =========================================================

    private void createActivity() {

        // =====================================================
        // GET VALUES
        // =====================================================

        String activityName =
                activityNameField
                        .getText()
                        .trim();

        String activityType =
                activityTypeCombo.getValue();

        String difficulty =
                difficultyCombo.getValue();

        LocalDate selectedDate =
                datePicker.getValue();

        String startTime =
                startTimeField
                        .getText()
                        .trim();

        String endTime =
                endTimeField
                        .getText()
                        .trim();

        String location =
                locationField
                        .getText()
                        .trim();

        String trainer =
                trainerField
                        .getText()
                        .trim();

        String maxParticipantsText =
                maximumParticipantsField
                        .getText()
                        .trim();

        String description =
                descriptionArea
                        .getText()
                        .trim();

        // =====================================================
        // VALIDATION
        // =====================================================

        if (activityName.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter activity name."
            );

           

            return;
        }

        if (activityType == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select activity type."
            );

            return;
        }

        if (difficulty == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select difficulty."
            );

            return;
        }

        if (selectedDate == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select activity date."
            );

            return;
        }

        if (startTime.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter start time."
            );

            return;
        }

        if (endTime.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter end time."
            );

            return;
        }

        if (location.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter location."
            );

            return;
        }

        if (trainer.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter trainer name."
            );

            return;
        }

        if (maxParticipantsText.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter maximum participants."
            );

            return;
        }

        // =====================================================
        // MAX PARTICIPANTS
        // =====================================================

        int maximumParticipants;

        try {

            maximumParticipants =
                    Integer.parseInt(
                            maxParticipantsText
                    );

            if (maximumParticipants <= 0) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Validation Error",
                        "Maximum participants must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Maximum participants must be a valid number."
            );

            return;
        }

        // =====================================================
        // OWNER VALIDATION
        // =====================================================

        if (createdBy.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Owner Error",
                    "Club Owner ID is missing.\n"
                            + "Please login again."
            );

            return;
        }

        // =====================================================
        // AUTOMATIC VALUES
        // =====================================================

        String activityId =
                UUID.randomUUID().toString();

        String date =
                selectedDate.toString();

        String status =
                "Active";

        long createdAt =
                System.currentTimeMillis();

        // =====================================================
        // CREATE MODEL
        // =====================================================

        ClubOwnerActivity activity =
                new ClubOwnerActivity(

                        activityId,

                        clubId,

                        createdBy,

                        activityName,

                        activityType,

                        difficulty,

                        date,

                        startTime,

                        endTime,

                        location,

                        trainer,

                        maximumParticipants,

                        description,

                        status,

                        createdAt
                );

        // =====================================================
        // DISABLE BUTTON
        // =====================================================

        createActivityButton.setDisable(
                true
        );

        createActivityButton.setText(
                "Saving..."
        );

        // =====================================================
        // FIRESTORE TASK
        // =====================================================

        Task<Void> task =
                new Task<>() {

                    @Override
                    protected Void call()
                            throws Exception {

                        activityController
                                .addActivity(
                                        activity
                                );

                        return null;
                    }
                };

        // =====================================================
        // SUCCESS
        // =====================================================

        task.setOnSucceeded(
                e -> {

                    createActivityButton
                            .setDisable(false);

                    createActivityButton
                            .setText(
                                    "Create Activity"
                            );

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Success",
                            "Activity created successfully."
                    );

                    clearForm();

                    // =================================================
                    // REFRESH DASHBOARD
                    // =================================================

                    if (
                            onActivityCreated != null
                    ) {

                        onActivityCreated.run();
                    }
                }
        );

        // =====================================================
        // ERROR
        // =====================================================

        task.setOnFailed(
                e -> {

                    createActivityButton
                            .setDisable(false);

                    createActivityButton
                            .setText(
                                    "Create Activity"
                            );

                    Throwable exception =
                            task.getException();

                    exception.printStackTrace();

                    String errorMessage =
                            exception == null
                                    ? "Unknown error."
                                    : exception.getMessage();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Error",
                            "Unable to create activity.\n\n"
                                    + errorMessage
                    );
                }
        );

        // =====================================================
        // START THREAD
        // =====================================================

        Thread thread =
                new Thread(task);

        thread.setDaemon(
                true
        );

        thread.start();
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        activityNameField.clear();

        activityTypeCombo
                .setValue(null);

        difficultyCombo
                .setValue(null);

        datePicker.setValue(
                LocalDate.now()
        );

        startTimeField.clear();

        endTimeField.clear();

        locationField.clear();

        trainerField.clear();

        maximumParticipantsField.clear();

        descriptionArea.clear();
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================

    private Label createFieldLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-text-fill:#cbd3db;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField field
    ) {

        field.setPrefHeight(
                40
        );

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#77808a;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;"
        );
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            ComboBox<String> combo
    ) {

        combo.setPrefHeight(
                40
        );

        combo.setMaxWidth(
                Double.MAX_VALUE
        );

        combo.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;"
        );
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Platform.runLater(() -> {

            Alert alert =
                    new Alert(type);



            alert.setTitle(
                    title
            );

            alert.setHeaderText(
                    null
            );

            alert.setContentText(
                    message
            );

            com.flexforce.view.components.DialogUtils.applyTheme(alert);

            alert.getDialogPane().lookup(".content.label").setStyle(
            "-fx-text-fill: white;"
            );

            alert.showAndWait();
        });
    }
}

