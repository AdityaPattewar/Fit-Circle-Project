package com.flexforce.view.club_owner;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.flexforce.controller.AuthControllerClub;
import com.flexforce.controller.ClubownerAnnouncementController;
import com.flexforce.model.club_owner.ClubOwnerAnnouncement;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Club_Announcement {

    // =========================================================
    // MAIN ANNOUNCEMENT UI
    // =========================================================

    public static VBox createAnnouncementUI() {

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox main = new VBox(18);

        main.setPadding(
                new Insets(25, 30, 25, 30)
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

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingBox = new VBox(5);

        Label title = new Label(
                "Announcement"
        );

        title.setStyle(
                "-fx-text-fill:#b8ff00;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle = new Label(
                "Create and manage announcements for your club members"
        );

        subtitle.setStyle(
                "-fx-text-fill:#b8c0ca;" +
                "-fx-font-size:13px;"
        );

        headingBox.getChildren().addAll(
                title,
                subtitle
        );

        VBox headerSpacer = new VBox();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        Button createButton = new Button(
                "+   Create Announcement"
        );

        createButton.setPrefHeight(42);

        createButton.setPrefWidth(175);

        createButton.setStyle(
                "-fx-background-color:#b8ff00;" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );

        header.getChildren().addAll(
                headingBox,
                headerSpacer,
                createButton
        );

        // =====================================================
        // STATISTICS LABELS
        // =====================================================

        Label totalValue =
                new Label("0");

        Label activeValue =
                new Label("0");

        Label scheduledValue =
                new Label("0");

        Label draftValue =
                new Label("0");

        // =====================================================
        // STATISTICS CARDS
        // =====================================================

        VBox totalCard =
                createStatCard(
                        "TOTAL ANNOUNCEMENTS",
                        totalValue
                );

        VBox activeCard =
                createStatCard(
                        "ACTIVE",
                        activeValue
                );

        VBox scheduledCard =
                createStatCard(
                        "SCHEDULED",
                        scheduledValue
                );

        VBox draftCard =
                createStatCard(
                        "DRAFTS",
                        draftValue
                );

        HBox statistics =
                new HBox(15);

        statistics.setAlignment(
                Pos.CENTER_LEFT
        );

        statistics.getChildren().addAll(
                totalCard,
                activeCard,
                scheduledCard,
                draftCard
        );

        // =====================================================
        // SEARCH AND FILTER
        // =====================================================

        HBox tools =
                new HBox(10);

        tools.setAlignment(
                Pos.CENTER_LEFT
        );

        TextField search =
                new TextField();

        search.setPromptText(
                "⌕  Search announcements..."
        );

        search.setPrefHeight(38);

        search.setPrefWidth(300);

        search.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#77808a;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;"
        );

        Button allButton =
                new Button("All");

        Button activeButton =
                new Button("Active");

        Button scheduledButton =
                new Button("Scheduled");

        Button draftButton =
                new Button("Draft");

        styleFilterButton(
                allButton,
                true
        );

        styleFilterButton(
                activeButton,
                false
        );

        styleFilterButton(
                scheduledButton,
                false
        );

        styleFilterButton(
                draftButton,
                false
        );

        tools.getChildren().addAll(
                search,
                allButton,
                activeButton,
                scheduledButton,
                draftButton
        );

        // =====================================================
        // ANNOUNCEMENT LIST
        // =====================================================

        VBox announcementList =
                new VBox(12);

        announcementList.setMaxWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // GET CURRENT OWNER ID
        // =====================================================

        String currentOwnerId =
                AuthControllerClub
                        .getCurrentOwnerId();

        // =====================================================
        // CONTROLLER
        // =====================================================

        ClubownerAnnouncementController
                announcementController =
                new ClubownerAnnouncementController();

        // =====================================================
        // FETCH ANNOUNCEMENTS
        // =====================================================

        List<ClubOwnerAnnouncement>
                announcements =
                announcementController
                        .getAnnouncementsByOwner(
                                currentOwnerId
                        );

        // =====================================================
        // DYNAMIC STATISTICS
        // =====================================================

        updateStatistics(
                announcements,
                totalValue,
                activeValue,
                scheduledValue,
                draftValue
        );

        // =====================================================
        // CURRENT FILTER
        // =====================================================

        final String[] selectedStatus =
                {"All"};

        // =====================================================
        // INITIAL ANNOUNCEMENT LIST
        // =====================================================

        refreshAnnouncementList(
                announcementList,
                announcements,
                "",
                selectedStatus[0]
        );

        // =====================================================
        // CREATE ANNOUNCEMENT BUTTON
        // =====================================================

        createButton.setOnAction(e -> {

            showCreateAnnouncementDialog(
                    announcementController,
                    announcements,
                    announcementList,
                    totalValue,
                    activeValue,
                    scheduledValue,
                    draftValue,
                    search,
                    selectedStatus
            );

        });

        // =====================================================
        // SEARCH
        // =====================================================

        search.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    refreshAnnouncementList(
                            announcementList,
                            announcements,
                            newValue,
                            selectedStatus[0]
                    );
                }
        );

        // =====================================================
        // ALL BUTTON
        // =====================================================

        allButton.setOnAction(e -> {

            selectedStatus[0] =
                    "All";

            styleSelectedFilter(
                    allButton,
                    activeButton,
                    scheduledButton,
                    draftButton
            );

            refreshAnnouncementList(
                    announcementList,
                    announcements,
                    search.getText(),
                    selectedStatus[0]
            );
        });

        // =====================================================
        // ACTIVE BUTTON
        // =====================================================

        activeButton.setOnAction(e -> {

            selectedStatus[0] =
                    "Active";

            styleSelectedFilter(
                    activeButton,
                    allButton,
                    scheduledButton,
                    draftButton
            );

            refreshAnnouncementList(
                    announcementList,
                    announcements,
                    search.getText(),
                    selectedStatus[0]
            );
        });

        // =====================================================
        // SCHEDULED BUTTON
        // =====================================================

        scheduledButton.setOnAction(e -> {

            selectedStatus[0] =
                    "Scheduled";

            styleSelectedFilter(
                    scheduledButton,
                    allButton,
                    activeButton,
                    draftButton
            );

            refreshAnnouncementList(
                    announcementList,
                    announcements,
                    search.getText(),
                    selectedStatus[0]
            );
        });

        // =====================================================
        // DRAFT BUTTON
        // =====================================================

        draftButton.setOnAction(e -> {

            selectedStatus[0] =
                    "Draft";

            styleSelectedFilter(
                    draftButton,
                    allButton,
                    activeButton,
                    scheduledButton
            );

            refreshAnnouncementList(
                    announcementList,
                    announcements,
                    search.getText(),
                    selectedStatus[0]
            );
        });

        // =====================================================
        // PAGINATION
        // =====================================================

        HBox pagination =
                new HBox(7);

        pagination.setAlignment(
                Pos.CENTER
        );

        Button previous =
                new Button("Previous");

        Button page1 =
                new Button("1");

        Button page2 =
                new Button("2");

        Button page3 =
                new Button("3");

        Button next =
                new Button("Next");

        stylePageButton(previous);

        stylePageButton(page2);

        stylePageButton(page3);

        stylePageButton(next);

        page1.setStyle(
                "-fx-background-color:#b8ff00;" +
                "-fx-text-fill:#101510;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:5px;" +
                "-fx-cursor:hand;"
        );

        pagination.getChildren().addAll(
                previous,
                page1,
                page2,
                page3,
                next
        );

        // =====================================================
        // ADD EVERYTHING TO MAIN
        // =====================================================

        main.getChildren().addAll(
                header,
                statistics,
                tools,
                announcementList,
                pagination
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(main);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setFitToHeight(
                false
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

        // =====================================================
        // CONTAINER
        // =====================================================

        VBox container =
                new VBox(scrollPane);

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
    // CREATE ANNOUNCEMENT DIALOG
    // =========================================================

    private static void showCreateAnnouncementDialog(
            ClubownerAnnouncementController announcementController,
            List<ClubOwnerAnnouncement> announcements,
            VBox announcementList,
            Label totalValue,
            Label activeValue,
            Label scheduledValue,
            Label draftValue,
            TextField search,
            String[] selectedStatus) {

        // =====================================================
        // CURRENT OWNER
        // =====================================================

        String currentOwnerId =
                AuthControllerClub.getCurrentOwnerId();

        if (currentOwnerId == null ||
                currentOwnerId.isEmpty()) {

            showError(
                    "Club Owner is not logged in."
            );

            return;
        }

        // =====================================================
        // DIALOG
        // =====================================================

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Create Announcement"
        );

        dialog.setHeaderText(
                "Create a new announcement"
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label titleLabel =
                new Label("Title");

        TextField titleField =
                new TextField();

        titleField.setPromptText(
                "Enter announcement title"
        );

        titleField.setPrefWidth(350);

        titleField.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#77808a;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:6px;" +
                "-fx-background-radius:6px;"
        );

        // =====================================================
        // MESSAGE
        // =====================================================

        Label messageLabel =
                new Label("Message");

        TextArea messageField =
                new TextArea();

        messageField.setPromptText(
                "Enter announcement message"
        );

        messageField.setPrefRowCount(5);

        messageField.setWrapText(
                true
        );

        messageField.setPrefWidth(350);

        messageField.setStyle(
                "-fx-control-inner-background:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#77808a;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:6px;" +
                "-fx-background-radius:6px;"
        );

        // =====================================================
        // DATE
        // =====================================================

        Label dateLabel =
                new Label("Date");

        TextField dateField =
                new TextField();

        dateField.setText(
                LocalDate.now().toString()
        );

        dateField.setPromptText(
                "YYYY-MM-DD"
        );

        dateField.setPrefWidth(350);

        dateField.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#77808a;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:6px;" +
                "-fx-background-radius:6px;"
        );

        // =====================================================
        // STATUS
        // =====================================================

        Label statusLabel =
                new Label("Status");

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Active",
                "Scheduled",
                "Draft"
        );

        statusBox.setValue(
                "Draft"
        );

        statusBox.setPrefWidth(
                350
        );

        // =====================================================
        // FORM
        // =====================================================

        VBox form =
                new VBox(8);

        form.setPadding(
                new Insets(10)
        );

        form.getChildren().addAll(
                titleLabel,
                titleField,

                messageLabel,
                messageField,

                dateLabel,
                dateField,

                statusLabel,
                statusBox
        );

        dialog.getDialogPane()
                .setContent(form);

        // =====================================================
        // BUTTONS
        // =====================================================

        ButtonType saveButton =
                new ButtonType(
                        "Save",
                        ButtonType.OK.getButtonData()
                );

        ButtonType cancelButton =
                new ButtonType(
                        "Cancel",
                        ButtonType.CANCEL.getButtonData()
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        saveButton,
                        cancelButton
                );

        // =====================================================
        // SAVE BUTTON
        // =====================================================

        dialog.setResultConverter(
                button -> {

                    if (button == saveButton) {

                        String title =
                                titleField.getText()
                                        .trim();

                        String message =
                                messageField.getText()
                                        .trim();

                        String date =
                                dateField.getText()
                                        .trim();

                        String status =
                                statusBox.getValue();

                        // =====================================
                        // VALIDATION
                        // =====================================

                        if (title.isEmpty()) {

                            showError(
                                    "Please enter announcement title."
                            );

                            return null;
                        }

                        if (message.isEmpty()) {

                            showError(
                                    "Please enter announcement message."
                            );

                            return null;
                        }

                        if (date.isEmpty()) {

                            showError(
                                    "Please enter announcement date."
                            );

                            return null;
                        }

                        if (status == null ||
                                status.isEmpty()) {

                            showError(
                                    "Please select announcement status."
                            );

                            return null;
                        }

                        // =====================================
                        // CREATE ANNOUNCEMENT ID
                        // =====================================

                        String announcementId =
                                UUID.randomUUID()
                                        .toString();

                        // =====================================
                        // CREATE MODEL
                        // =====================================

                        ClubOwnerAnnouncement announcement =
                                new ClubOwnerAnnouncement();

                        announcement.setAnnouncementId(
                                announcementId
                        );

                        // Current Firebase Owner UID
                        announcement.setCreatedBy(
                                currentOwnerId
                        );

                        // Club ID not currently available
                        announcement.setClubId(
                                ""
                        );

                        announcement.setTitle(
                                title
                        );

                        announcement.setMessage(
                                message
                        );

                        announcement.setDate(
                                date
                        );

                        announcement.setStatus(
                                status
                        );

                        // =====================================
                        // SAVE TO FIREBASE
                        // =====================================

                        boolean saved =
                                announcementController
                                        .saveAnnouncement(
                                                announcement
                                        );

                        // =====================================
                        // SUCCESS
                        // =====================================

                        if (saved) {

                            // Add to current list
                            announcements.add(
                                    announcement
                            );

                            // Update statistics
                            updateStatistics(
                                    announcements,
                                    totalValue,
                                    activeValue,
                                    scheduledValue,
                                    draftValue
                            );

                            // Refresh announcement cards
                            refreshAnnouncementList(
                                    announcementList,
                                    announcements,
                                    search.getText(),
                                    selectedStatus[0]
                            );

                            showSuccess(
                                    "Announcement created successfully."
                            );

                        } else {

                            showError(
                                    "Failed to save announcement to Firebase."
                            );
                        }

                        return null;
                    }

                    return button;
                }
        );

        com.flexforce.view.components.DialogUtils.applyTheme(dialog);
        dialog.showAndWait();
    }

    // =========================================================
    // SUCCESS ALERT
    // =========================================================

    private static void showSuccess(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Success"
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
    }

    // =========================================================
    // ERROR ALERT
    // =========================================================

    private static void showError(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Error"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }

    // =========================================================
    // UPDATE STATISTICS
    // =========================================================

    private static void updateStatistics(
            List<ClubOwnerAnnouncement> announcements,
            Label totalValue,
            Label activeValue,
            Label scheduledValue,
            Label draftValue) {

        int total =
                announcements.size();

        int active = 0;

        int scheduled = 0;

        int drafts = 0;

        for (ClubOwnerAnnouncement announcement :
                announcements) {

            if (announcement == null) {
                continue;
            }

            String status =
                    announcement.getStatus();

            if (status == null) {
                continue;
            }

            if (status.equalsIgnoreCase(
                    "Active")) {

                active++;

            } else if (
                    status.equalsIgnoreCase(
                            "Scheduled")) {

                scheduled++;

            } else if (
                    status.equalsIgnoreCase(
                            "Draft")) {

                drafts++;
            }
        }

        totalValue.setText(
                String.valueOf(total)
        );

        activeValue.setText(
                String.valueOf(active)
        );

        scheduledValue.setText(
                String.valueOf(scheduled)
        );

        draftValue.setText(
                String.valueOf(drafts)
        );
    }

    // =========================================================
    // REFRESH ANNOUNCEMENT LIST
    // =========================================================

    private static void refreshAnnouncementList(
            VBox announcementList,
            List<ClubOwnerAnnouncement> announcements,
            String searchText,
            String selectedStatus) {

        announcementList
                .getChildren()
                .clear();

        if (announcements == null ||
                announcements.isEmpty()) {

            Label emptyLabel =
                    new Label(
                            "No announcements found."
                    );

            emptyLabel.setStyle(
                    "-fx-text-fill:#9da6b0;" +
                    "-fx-font-size:13px;"
            );

            announcementList
                    .getChildren()
                    .add(emptyLabel);

            return;
        }

        String search =
                searchText == null
                        ? ""
                        : searchText
                                .toLowerCase()
                                .trim();

        for (ClubOwnerAnnouncement announcement :
                announcements) {

            if (announcement == null) {
                continue;
            }

            String title =
                    announcement.getTitle() == null
                            ? ""
                            : announcement.getTitle();

            String message =
                    announcement.getMessage() == null
                            ? ""
                            : announcement.getMessage();

            String status =
                    announcement.getStatus() == null
                            ? ""
                            : announcement.getStatus();

            // =================================================
            // SEARCH MATCH
            // =================================================

            boolean matchesSearch =
                    title.toLowerCase()
                            .contains(search)
                    ||
                    message.toLowerCase()
                            .contains(search);

            // =================================================
            // STATUS MATCH
            // =================================================

            boolean matchesStatus =
                    selectedStatus.equalsIgnoreCase(
                            "All"
                    )
                    ||
                    status.equalsIgnoreCase(
                            selectedStatus
                    );

            // =================================================
            // ADD CARD
            // =================================================

            if (matchesSearch &&
                    matchesStatus) {

                VBox card =
                        createAnnouncementCard(
                                title,
                                message,
                                status,
                                "Normal",
                                "All Members",
                                announcement.getDate()
                        );

                announcementList
                        .getChildren()
                        .add(card);
            }
        }

        // =====================================================
        // NO RESULT AFTER FILTER / SEARCH
        // =====================================================

        if (announcementList
                .getChildren()
                .isEmpty()) {

            Label noResultLabel =
                    new Label(
                            "No announcements found."
                    );

            noResultLabel.setStyle(
                    "-fx-text-fill:#9da6b0;" +
                    "-fx-font-size:13px;"
            );

            announcementList
                    .getChildren()
                    .add(noResultLabel);
        }
    }

    // =========================================================
    // STATISTICS CARD
    // =========================================================

    private static VBox createStatCard(
            String heading,
            Label value) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(15)
        );

        card.setPrefWidth(
                205
        );

        card.setMinHeight(
                90
        );

        card.setStyle(
                "-fx-background-color:#151c24;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;"
        );

        Label title =
                new Label(heading);

        title.setStyle(
                "-fx-text-fill:#8f99a4;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        value.setStyle(
                "-fx-text-fill:#b8ff00;" +
                "-fx-font-size:25px;" +
                "-fx-font-weight:bold;"
        );

        card.getChildren().addAll(
                title,
                value
        );

        return card;
    }

    // =========================================================
    // ANNOUNCEMENT CARD
    // =========================================================

    private static VBox createAnnouncementCard(
            String titleText,
            String description,
            String status,
            String priority,
            String audience,
            String date) {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(16)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setMinHeight(
                140
        );

        card.setStyle(
                "-fx-background-color:#151c24;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;"
        );

        // =====================================================
        // TOP ROW
        // =====================================================

        HBox topRow =
                new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox =
                new VBox(5);

        Label title =
                new Label(titleText);

        title.setStyle(
                "-fx-text-fill:#ffffff;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        Label announcementText =
                new Label(description);

        announcementText.setWrapText(
                true
        );

        announcementText.setMaxWidth(
                900
        );

        announcementText.setStyle(
                "-fx-text-fill:#9da6b0;" +
                "-fx-font-size:11px;"
        );

        titleBox.getChildren().addAll(
                title,
                announcementText
        );

        VBox spacer =
                new VBox();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =====================================================
        // STATUS
        // =====================================================

        Label statusLabel =
                new Label(status);

        statusLabel.setPadding(
                new Insets(5, 10, 5, 10)
        );

        if (status.equalsIgnoreCase(
                "Active")) {

            statusLabel.setStyle(
                    "-fx-background-color:#19351f;" +
                    "-fx-text-fill:#48e878;" +
                    "-fx-background-radius:12px;" +
                    "-fx-font-size:10px;" +
                    "-fx-font-weight:bold;"
            );

        } else if (
                status.equalsIgnoreCase(
                        "Scheduled")) {

            statusLabel.setStyle(
                    "-fx-background-color:#252d3b;" +
                    "-fx-text-fill:#8bb9ff;" +
                    "-fx-background-radius:12px;" +
                    "-fx-font-size:10px;" +
                    "-fx-font-weight:bold;"
            );

        } else {

            statusLabel.setStyle(
                    "-fx-background-color:#302b1f;" +
                    "-fx-text-fill:#d8c36a;" +
                    "-fx-background-radius:12px;" +
                    "-fx-font-size:10px;" +
                    "-fx-font-weight:bold;"
            );
        }

        topRow.getChildren().addAll(
                titleBox,
                spacer,
                statusLabel
        );

        // =====================================================
        // INFORMATION ROW
        // =====================================================

        HBox infoRow =
                new HBox(22);

        infoRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label priorityLabel =
                new Label(
                        "●  " + priority
                );

        priorityLabel.setStyle(
                "-fx-text-fill:#b8ff00;" +
                "-fx-font-size:10px;"
        );

        Label audienceLabel =
                new Label(
                        "♟  " + audience
                );

        audienceLabel.setStyle(
                "-fx-text-fill:#9da6b0;" +
                "-fx-font-size:10px;"
        );

        Label dateLabel =
                new Label(
                        "◷  " +
                        (date == null
                                ? ""
                                : date)
                );

        dateLabel.setStyle(
                "-fx-text-fill:#9da6b0;" +
                "-fx-font-size:10px;"
        );

        infoRow.getChildren().addAll(
                priorityLabel,
                audienceLabel,
                dateLabel
        );

        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        HBox actionRow =
                new HBox(8);

        actionRow.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button viewButton =
                new Button("View");

        Button editButton =
                new Button("Edit");

        styleActionButton(
                viewButton
        );

        styleActionButton(
                editButton
        );

        actionRow.getChildren().addAll(
                viewButton,
                editButton
        );

        // =====================================================
        // ADD CARD CONTENT
        // =====================================================

        card.getChildren().addAll(
                topRow,
                infoRow,
                actionRow
        );

        return card;
    }

    // =========================================================
    // FILTER BUTTON STYLE
    // =========================================================

    private static void styleFilterButton(
            Button button,
            boolean selected) {

        button.setPrefHeight(
                36
        );

        if (selected) {

            button.setStyle(
                    "-fx-background-color:#b8ff00;" +
                    "-fx-text-fill:#101510;" +
                    "-fx-font-size:11px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:6px;" +
                    "-fx-cursor:hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color:#171e26;" +
                    "-fx-text-fill:#c4ccd4;" +
                    "-fx-font-size:11px;" +
                    "-fx-border-color:#29333e;" +
                    "-fx-border-radius:6px;" +
                    "-fx-background-radius:6px;" +
                    "-fx-cursor:hand;"
            );
        }
    }

    // =========================================================
    // SELECTED FILTER STYLE
    // =========================================================

    private static void styleSelectedFilter(
            Button selectedButton,
            Button button1,
            Button button2,
            Button button3) {

        styleFilterButton(
                selectedButton,
                true
        );

        styleFilterButton(
                button1,
                false
        );

        styleFilterButton(
                button2,
                false
        );

        styleFilterButton(
                button3,
                false
        );
    }

    // =========================================================
    // ACTION BUTTON STYLE
    // =========================================================

    private static void styleActionButton(
            Button button) {

        button.setPrefWidth(
                65
        );

        button.setPrefHeight(
                28
        );

        button.setStyle(
                "-fx-background-color:#202a34;" +
                "-fx-text-fill:#d5dde5;" +
                "-fx-border-color:#35414d;" +
                "-fx-border-radius:5px;" +
                "-fx-background-radius:5px;" +
                "-fx-font-size:10px;" +
                "-fx-cursor:hand;"
        );
    }

    // =========================================================
    // PAGINATION BUTTON STYLE
    // =========================================================

    private static void stylePageButton(
            Button button) {

        button.setStyle(
                "-fx-background-color:#171e26;" +
                "-fx-text-fill:#c5ccd3;" +
                "-fx-border-color:#29333e;" +
                "-fx-border-radius:5px;" +
                "-fx-background-radius:5px;" +
                "-fx-cursor:hand;"
        );
    }
}