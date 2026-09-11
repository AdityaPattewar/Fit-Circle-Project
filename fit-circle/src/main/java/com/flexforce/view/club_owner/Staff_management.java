package com.flexforce.view.club_owner;



import java.util.ArrayList;
import java.util.List;

import com.flexforce.controller.StaffController;
import com.flexforce.model.club_owner.Staff;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Staff_management {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BACKGROUND = "#080B09";
    private static final String CARD = "#101511";
    private static final String CARD_DARK = "#141C17";
    private static final String BORDER = "#283129";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#FFFFFF";
    private static final String GRAY = "#8F9A91";
    private static final String TEXT = "#D7DED9";

    // =========================================================
    // CONTROLLER
    // =========================================================

    private static final StaffController controller =
            new StaffController();

    // =========================================================
    // DATA
    // =========================================================

    private static final ObservableList<Staff> staffList =
            FXCollections.observableArrayList();

    private static List<Staff> allStaff =
            new ArrayList<>();

    // =========================================================
    // UI REFERENCES
    // =========================================================

    private static VBox staffContainer;

    private static Label totalValue;
    private static Label activeValue;
    private static Label leaveValue;
    private static Label resultLabel;

    private static TextField searchField;
    private static ComboBox<String> roleFilter;

    // =========================================================
    // CLUB ID
    // =========================================================

    private static String currentClubId = "";

    // =========================================================
    // CREATE PAGE - NO CLUB ID
    // =========================================================

    public static Node createPage() {

        return createPage("");
    }

    // =========================================================
    // CREATE PAGE - WITH CLUB ID
    // =========================================================

    public static Node createPage(String clubId) {

        currentClubId =
                clubId == null
                        ? ""
                        : clubId.trim();

        // =====================================================
        // MAIN
        // =====================================================

        VBox main = new VBox(22);

        main.setPadding(
                new Insets(35)
        );

        main.setStyle(
                "-fx-background-color: "
                        + BACKGROUND
                        + ";"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label title =
                new Label("Staff Management");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(LIME)
        );

        Label subtitle =
                new Label(
                        "Manage your club staff members and their roles"
                );

        subtitle.setFont(
                Font.font("Arial", 15)
        );

        subtitle.setTextFill(
                Color.web(GRAY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region headerSpacer =
                new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        Button addStaffButton =
                new Button("+  Add Staff");

        addStaffButton.setPrefWidth(145);
        addStaffButton.setPrefHeight(42);

        setPrimaryButtonStyle(
                addStaffButton
        );

        addStaffButton.setOnMouseEntered(e ->
                setPrimaryHoverStyle(
                        addStaffButton
                )
        );

        addStaffButton.setOnMouseExited(e ->
                setPrimaryButtonStyle(
                        addStaffButton
                )
        );

        addStaffButton.setOnAction(e ->
                showAddStaffDialog()
        );

        header.getChildren().addAll(
                titleBox,
                headerSpacer,
                addStaffButton
        );

        // =====================================================
        // STATISTICS
        // =====================================================

        HBox stats =
                new HBox(18);

        VBox totalCard =
                createStatCard(
                        "TOTAL STAFF",
                        "0",
                        "👥"
                );

        VBox activeCard =
                createStatCard(
                        "ACTIVE STAFF",
                        "0",
                        "✓"
                );

        VBox leaveCard =
                createStatCard(
                        "ON LEAVE",
                        "0",
                        "◷"
                );

        totalValue =
                (Label) totalCard.getProperties()
                        .get("valueLabel");

        activeValue =
                (Label) activeCard.getProperties()
                        .get("valueLabel");

        leaveValue =
                (Label) leaveCard.getProperties()
                        .get("valueLabel");

        HBox.setHgrow(
                totalCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                leaveCard,
                Priority.ALWAYS
        );

        stats.getChildren().addAll(
                totalCard,
                activeCard,
                leaveCard
        );

        // =====================================================
        // SEARCH + FILTER
        // =====================================================

        HBox searchSection =
                new HBox(12);

        searchSection.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search staff member..."
        );

        searchField.setPrefHeight(42);
        searchField.setPrefWidth(350);

        setTextFieldStyle(
                searchField
        );

        roleFilter =
                new ComboBox<>();

        roleFilter.getItems().addAll(
                "All Roles",
                "Trainer",
                "Receptionist",
                "Manager",
                "Coach",
                "Fitness Coach",
                "Senior Trainer"
        );

        roleFilter.setValue(
                "All Roles"
        );

        roleFilter.setPrefHeight(42);
        roleFilter.setPrefWidth(170);

        roleFilter.setStyle(
                "-fx-background-color: "
                        + CARD_DARK + ";"
                        + "-fx-border-color: "
                        + BORDER + ";"
                        + "-fx-border-radius: 7px;"
                        + "-fx-background-radius: 7px;"
                        + "-fx-text-fill: "
                        + WHITE + ";"
        );

        Region searchSpacer =
                new Region();

        HBox.setHgrow(
                searchSpacer,
                Priority.ALWAYS
        );

        resultLabel =
                new Label("0 Staff Members");

        resultLabel.setTextFill(
                Color.web(GRAY)
        );

        resultLabel.setFont(
                Font.font("Arial", 13)
        );

        searchSection.getChildren().addAll(
                searchField,
                roleFilter,
                searchSpacer,
                resultLabel
        );

        // =====================================================
        // SEARCH EVENT
        // =====================================================

        searchField.textProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                filterStaff()
                );

        roleFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                filterStaff()
                );

        // =====================================================
        // STAFF LIST
        // =====================================================

        staffContainer =
                new VBox(12);

        Label listTitle =
                new Label("Staff Members");

        listTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        listTitle.setTextFill(
                Color.web(WHITE)
        );

        staffContainer.getChildren().add(
                listTitle
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        staffContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-background: transparent;"
                        + "-fx-border-color: transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                header,
                stats,
                searchSection,
                scrollPane
        );

        // =====================================================
        // LOAD FIREBASE DATA
        // =====================================================

        loadStaff();

        return main;
    }

    // =========================================================
    // LOAD STAFF
    // =========================================================

    private static void loadStaff() {

        new Thread(() -> {

            try {

                List<Staff> data =
                        controller.getStaff(
                                currentClubId
                        );

                Platform.runLater(() -> {

                    allStaff =
                            data == null
                                    ? new ArrayList<>()
                                    : new ArrayList<>(data);

                    staffList.clear();

                    staffList.addAll(
                            allStaff
                    );

                    updateStatistics();

                    displayStaff(
                            allStaff
                    );
                });

            } catch (Exception e) {

                e.printStackTrace();

                Platform.runLater(() -> {

                    showError(
                            "Unable to load staff data.\n"
                                    + e.getMessage()
                    );

                    allStaff =
                            new ArrayList<>();

                    updateStatistics();

                    displayStaff(
                            allStaff
                    );
                });

            }

        }).start();
    }

    // =========================================================
    // FILTER STAFF
    // =========================================================

    private static void filterStaff() {

        String search =
                searchField == null
                        ? ""
                        : searchField
                                .getText()
                                .trim()
                                .toLowerCase();

        String selectedRole =
                roleFilter == null
                        ? "All Roles"
                        : roleFilter.getValue();

        List<Staff> filtered =
                new ArrayList<>();

        for (Staff staff : allStaff) {

            if (staff == null) {
                continue;
            }

            String name =
                    safe(staff.getName())
                            .toLowerCase();

            String role =
                    safe(staff.getRole())
                            .toLowerCase();

            String email =
                    safe(staff.getEmail())
                            .toLowerCase();

            boolean matchesSearch =
                    search.isEmpty()
                            || name.contains(search)
                            || role.contains(search)
                            || email.contains(search);

            boolean matchesRole =
                    selectedRole == null
                            || selectedRole.equals(
                                    "All Roles"
                            )
                            || role.equalsIgnoreCase(
                                    selectedRole
                            )
                            || (
                                selectedRole.equalsIgnoreCase(
                                        "Trainer"
                                )
                                &&
                                role.contains("trainer")
                            )
                            || (
                                selectedRole.equalsIgnoreCase(
                                        "Coach"
                                )
                                &&
                                role.contains("coach")
                            );

            if (matchesSearch &&
                    matchesRole) {

                filtered.add(staff);
            }
        }

        displayStaff(
                filtered
        );
    }

    // =========================================================
    // DISPLAY STAFF
    // =========================================================

    private static void displayStaff(
            List<Staff> list
    ) {

        if (staffContainer == null) {
            return;
        }

        staffContainer.getChildren().clear();

        Label listTitle =
                new Label("Staff Members");

        listTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        listTitle.setTextFill(
                Color.web(WHITE)
        );

        staffContainer.getChildren().add(
                listTitle
        );

        if (list == null ||
                list.isEmpty()) {

            Label empty =
                    new Label(
                            "No staff members found."
                    );

            empty.setFont(
                    Font.font(
                            "Arial",
                            15
                    )
            );

            empty.setTextFill(
                    Color.web(GRAY)
            );

            VBox emptyBox =
                    new VBox(empty);

            emptyBox.setAlignment(
                    Pos.CENTER
            );

            emptyBox.setPadding(
                    new Insets(40)
            );

            staffContainer.getChildren().add(
                    emptyBox
            );

        } else {

            for (Staff staff : list) {

                staffContainer.getChildren().add(
                        createStaffCard(
                                staff
                        )
                );
            }
        }

        if (resultLabel != null) {

            resultLabel.setText(
                    (list == null
                            ? 0
                            : list.size())
                            + " Staff Members"
            );
        }
    }

    // =========================================================
    // UPDATE STATISTICS
    // =========================================================

    private static void updateStatistics() {

        int total = 0;
        int active = 0;
        int leave = 0;

        for (Staff staff : allStaff) {

            if (staff == null) {
                continue;
            }

            total++;

            if ("Active".equalsIgnoreCase(
                    safe(staff.getStatus())
            )) {

                active++;
            }

            if ("On Leave".equalsIgnoreCase(
                    safe(staff.getStatus())
            )) {

                leave++;
            }
        }

        if (totalValue != null) {

            totalValue.setText(
                    String.valueOf(total)
            );
        }

        if (activeValue != null) {

            activeValue.setText(
                    String.valueOf(active)
            );
        }

        if (leaveValue != null) {

            leaveValue.setText(
                    String.valueOf(leave)
            );
        }
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private static VBox createStatCard(
            String title,
            String value,
            String icon
    ) {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(125);

        card.setStyle(
                "-fx-background-color: "
                        + CARD + ";"
                        + "-fx-border-color: "
                        + BORDER + ";"
                        + "-fx-border-radius: 10px;"
                        + "-fx-background-radius: 10px;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        iconLabel.setTextFill(
                Color.web(LIME)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        titleLabel.setTextFill(
                Color.web(GRAY)
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        valueLabel.setTextFill(
                Color.web(WHITE)
        );

        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                valueLabel
        );

        card.getProperties().put(
                "valueLabel",
                valueLabel
        );

        return card;
    }

    // =========================================================
    // STAFF CARD
    // =========================================================

    private static HBox createStaffCard(
            Staff staff
    ) {

        HBox card =
                new HBox(18);

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPadding(
                new Insets(
                        16,
                        20,
                        16,
                        20
                )
        );

        card.setStyle(
                "-fx-background-color: "
                        + CARD + ";"
                        + "-fx-border-color: "
                        + BORDER + ";"
                        + "-fx-border-radius: 9px;"
                        + "-fx-background-radius: 9px;"
        );

        // =====================================================
        // NAME
        // =====================================================

        String name =
                safe(staff.getName());

        String role =
                safe(staff.getRole());

        String email =
                safe(staff.getEmail());

        String phone =
                safe(staff.getPhone());

        String status =
                safe(staff.getStatus());

        String initial =
                name.isEmpty()
                        ? "?"
                        : name.substring(
                                0,
                                1
                        ).toUpperCase();

        // =====================================================
        // PROFILE
        // =====================================================

        StackPane profile =
                new StackPane();

        profile.setPrefSize(
                48,
                48
        );

        profile.setMinSize(
                48,
                48
        );

        profile.setMaxSize(
                48,
                48
        );

        profile.setStyle(
                "-fx-background-color: #1D3300;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-color: "
                        + LIME + ";"
                        + "-fx-border-radius: 50px;"
        );

        Label initialLabel =
                new Label(initial);

        initialLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        initialLabel.setTextFill(
                Color.web(LIME)
        );

        profile.getChildren().add(
                initialLabel
        );

        // =====================================================
        // STAFF INFORMATION
        // =====================================================

        VBox info =
                new VBox(4);

        info.setPrefWidth(180);

        Label nameLabel =
                new Label(
                        name.isEmpty()
                                ? "Unknown Staff"
                                : name
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        nameLabel.setTextFill(
                Color.web(WHITE)
        );

        Label roleLabel =
                new Label(
                        role.isEmpty()
                                ? "No Role"
                                : role
                );

        roleLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        roleLabel.setTextFill(
                Color.web(LIME)
        );

        info.getChildren().addAll(
                nameLabel,
                roleLabel
        );

        // =====================================================
        // CONTACT
        // =====================================================

        VBox contact =
                new VBox(4);

        Label emailLabel =
                new Label(
                        email.isEmpty()
                                ? "No email"
                                : email
                );

        emailLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        emailLabel.setTextFill(
                Color.web(GRAY)
        );

        Label phoneLabel =
                new Label(
                        phone.isEmpty()
                                ? "No phone"
                                : phone
                );

        phoneLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        phoneLabel.setTextFill(
                Color.web(GRAY)
        );

        contact.getChildren().addAll(
                emailLabel,
                phoneLabel
        );

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =====================================================
        // STATUS
        // =====================================================

        Label statusLabel =
                new Label(
                        status.isEmpty()
                                ? "Unknown"
                                : status
                );

        statusLabel.setPadding(
                new Insets(
                        6,
                        12,
                        6,
                        12
                )
        );

        if ("Active".equalsIgnoreCase(
                status
        )) {

            statusLabel.setStyle(
                    "-fx-background-color: #1D3300;"
                            + "-fx-text-fill: "
                            + LIME + ";"
                            + "-fx-background-radius: 20px;"
                            + "-fx-font-weight: bold;"
                            + "-fx-font-size: 12px;"
            );

        } else {

            statusLabel.setStyle(
                    "-fx-background-color: #252A20;"
                            + "-fx-text-fill: #C8D66A;"
                            + "-fx-background-radius: 20px;"
                            + "-fx-font-weight: bold;"
                            + "-fx-font-size: 12px;"
            );
        }

        // =====================================================
        // EDIT BUTTON
        // =====================================================

        Button editButton =
                new Button("Edit");

        editButton.setPrefWidth(70);
        editButton.setPrefHeight(34);

        setEditButtonStyle(
                editButton
        );

        editButton.setOnMouseEntered(e ->
                setEditHoverStyle(
                        editButton
                )
        );

        editButton.setOnMouseExited(e ->
                setEditButtonStyle(
                        editButton
                )
        );

        editButton.setOnAction(e ->
                showEditStaffDialog(
                        staff
                )
        );

        // =====================================================
        // REMOVE BUTTON
        // =====================================================

        Button removeButton =
                new Button("Remove");

        removeButton.setPrefWidth(80);
        removeButton.setPrefHeight(34);

        setRemoveButtonStyle(
                removeButton
        );

        removeButton.setOnAction(e ->
                removeStaff(
                        staff
                )
        );

        // =====================================================
        // ADD
        // =====================================================

        card.getChildren().addAll(
                profile,
                info,
                contact,
                spacer,
                statusLabel,
                editButton,
                removeButton
        );

        return card;
    }

    // =========================================================
    // ADD STAFF DIALOG
    // =========================================================

    private static void showAddStaffDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Staff"
        );

        dialog.setHeaderText(
                "Add New Staff Member"
        );

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(20)
        );

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Staff Name"
        );

        TextField roleField =
                new TextField();

        roleField.setPromptText(
                "Role"
        );

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Email"
        );

        TextField phoneField =
                new TextField();

        phoneField.setPromptText(
                "Phone"
        );

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Active",
                "On Leave"
        );

        statusBox.setValue(
                "Active"
        );

        box.getChildren().addAll(
                nameField,
                roleField,
                emailField,
                phoneField,
                statusBox
        );

        dialog.getDialogPane()
                .setContent(box);

        Button addButton =
                new Button("Add");

        Button cancelButton =
                new Button("Cancel");

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        new javafx.scene.control.ButtonType(
                                "Add",
                                javafx.scene.control.ButtonBar.ButtonData.OK_DONE
                        ),
                        new javafx.scene.control.ButtonType(
                                "Cancel",
                                javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE
                        )
                );

        dialog.showAndWait()
                .ifPresent(result -> {

                    if (result.getButtonData()
                            ==
                            javafx.scene.control.ButtonBar.ButtonData.OK_DONE) {

                        try {

                            String name =
                                    nameField
                                            .getText()
                                            .trim();

                            String role =
                                    roleField
                                            .getText()
                                            .trim();

                            String email =
                                    emailField
                                            .getText()
                                            .trim();

                            String phone =
                                    phoneField
                                            .getText()
                                            .trim();

                            String status =
                                    statusBox
                                            .getValue();

                            controller.addStaff(
                                    currentClubId,
                                    name,
                                    role,
                                    email,
                                    phone,
                                    status
                            );

                            showInfo(
                                    "Staff added successfully."
                            );

                            loadStaff();

                        } catch (Exception ex) {

                            ex.printStackTrace();

                            showError(
                                    "Unable to add staff.\n"
                                            + ex.getMessage()
                            );
                        }
                    }
                });
    }

    // =========================================================
    // EDIT STAFF
    // =========================================================

    private static void showEditStaffDialog(
            Staff staff
    ) {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Staff"
        );

        dialog.setHeaderText(
                "Edit Staff Member"
        );

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(20)
        );

        TextField nameField =
                new TextField(
                        safe(staff.getName())
                );

        TextField roleField =
                new TextField(
                        safe(staff.getRole())
                );

        TextField emailField =
                new TextField(
                        safe(staff.getEmail())
                );

        TextField phoneField =
                new TextField(
                        safe(staff.getPhone())
                );

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Active",
                "On Leave"
        );

        statusBox.setValue(
                safe(staff.getStatus()).isEmpty()
                        ? "Active"
                        : staff.getStatus()
        );

        box.getChildren().addAll(
                nameField,
                roleField,
                emailField,
                phoneField,
                statusBox
        );

        dialog.getDialogPane()
                .setContent(box);

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        new javafx.scene.control.ButtonType(
                                "Update",
                                javafx.scene.control.ButtonBar.ButtonData.OK_DONE
                        ),
                        new javafx.scene.control.ButtonType(
                                "Cancel",
                                javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE
                        )
                );

        dialog.showAndWait()
                .ifPresent(result -> {

                    if (result.getButtonData()
                            ==
                            javafx.scene.control.ButtonBar.ButtonData.OK_DONE) {

                        try {

                            staff.setName(
                                    nameField.getText().trim()
                            );

                            staff.setRole(
                                    roleField.getText().trim()
                            );

                            staff.setEmail(
                                    emailField.getText().trim()
                            );

                            staff.setPhone(
                                    phoneField.getText().trim()
                            );

                            staff.setStatus(
                                    statusBox.getValue()
                            );

                            controller.updateStaff(
                                    staff
                            );

                            showInfo(
                                    "Staff updated successfully."
                            );

                            loadStaff();

                        } catch (Exception ex) {

                            ex.printStackTrace();

                            showError(
                                    "Unable to update staff.\n"
                                            + ex.getMessage()
                            );
                        }
                    }
                });
    }

    // =========================================================
    // REMOVE STAFF
    // =========================================================

    private static void removeStaff(
            Staff staff
    ) {

        Alert confirm =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirm.setTitle(
                "Remove Staff"
        );

        confirm.setHeaderText(
                "Remove "
                        + safe(staff.getName())
                        + "?"
        );

        confirm.setContentText(
                "This staff member will be removed from Firebase."
        );

        com.flexforce.view.components.DialogUtils.applyTheme(confirm);
        confirm.showAndWait()
                .ifPresent(result -> {

                    if (result.getButtonData()
                            ==
                            javafx.scene.control.ButtonBar.ButtonData.OK_DONE) {

                        try {

                            controller.deleteStaff(
                                    staff.getStaffId()
                            );

                            showInfo(
                                    "Staff removed successfully."
                            );

                            loadStaff();

                        } catch (Exception ex) {

                            ex.printStackTrace();

                            showError(
                                    "Unable to remove staff.\n"
                                            + ex.getMessage()
                            );
                        }
                    }
                });
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private static void setTextFieldStyle(
            TextField field
    ) {

        field.setStyle(
                "-fx-background-color: "
                        + CARD_DARK + ";"
                        + "-fx-text-fill: "
                        + WHITE + ";"
                        + "-fx-prompt-text-fill: "
                        + GRAY + ";"
                        + "-fx-border-color: "
                        + BORDER + ";"
                        + "-fx-border-radius: 7px;"
                        + "-fx-background-radius: 7px;"
                        + "-fx-padding: 0 14px;"
                        + "-fx-font-size: 14px;"
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static void setPrimaryButtonStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + LIME + ";"
                        + "-fx-text-fill: #050705;"
                        + "-fx-font-size: 14px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void setPrimaryHoverStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: #D0FF4D;"
                        + "-fx-text-fill: #050705;"
                        + "-fx-font-size: 14px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // EDIT BUTTON STYLE
    // =========================================================

    private static void setEditButtonStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: "
                        + BORDER + ";"
                        + "-fx-text-fill: "
                        + WHITE + ";"
                        + "-fx-border-radius: 6px;"
                        + "-fx-background-radius: 6px;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void setEditHoverStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + LIME + ";"
                        + "-fx-text-fill: #050705;"
                        + "-fx-border-color: "
                        + LIME + ";"
                        + "-fx-border-radius: 6px;"
                        + "-fx-background-radius: 6px;"
                        + "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // REMOVE BUTTON STYLE
    // =========================================================

    private static void setRemoveButtonStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: #21191A;"
                        + "-fx-text-fill: #FF7777;"
                        + "-fx-border-color: #493033;"
                        + "-fx-border-radius: 6px;"
                        + "-fx-background-radius: 6px;"
                        + "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // SAFE
    // =========================================================

    private static String safe(
            String value
    ) {

        return value == null
                ? ""
                : value.trim();
    }

    // =========================================================
    // INFO ALERT
    // =========================================================

    private static void showInfo(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Staff Management"
        );

        alert.setHeaderText(null);

        alert.setContentText(
                message
        );

        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }

    // =========================================================
    // ERROR ALERT
    // =========================================================

    private static void showError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Error"
        );

        alert.setHeaderText(
                "Staff Management Error"
        );

        alert.setContentText(
                message
        );

        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }
}

