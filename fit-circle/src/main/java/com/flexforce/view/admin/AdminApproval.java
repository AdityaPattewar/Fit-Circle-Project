package com.flexforce.view.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.io.InputStream;

import com.flexforce.config.FirebaseConfig;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


// =====================================================
// ADMIN APPROVAL
// =====================================================

public class AdminApproval {

    private Firestore db;

    private VBox registrationList;

    private VBox detailsPane;

    private TextField searchField;

    private DocumentSnapshot selectedDocument;

    private List<DocumentSnapshot> allPendingOwners =
            new ArrayList<>();


    // =====================================================
    // MAIN APPROVAL VIEW
    // =====================================================

    public ScrollPane createClubApprovalView() {

        try {

            db = FirebaseConfig.getFirebaseConfig();

        } catch (Exception e) {

            e.printStackTrace();

            db = null;
        }


        VBox mainContainer =
                new VBox(20);

        mainContainer.setPadding(
                new Insets(
                        20,
                        40,
                        20,
                        40
                )
        );

        mainContainer.setStyle(
                "-fx-background-color:#080B09;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                createHeader();


        // =====================================================
        // STATISTICS
        // =====================================================

        HBox statsRow =
                new HBox(20);


        int pendingCount =
                getPendingCount();

        int approvedThisWeek =
                getApprovedThisWeekCount();

        int rejectedCount =
                getRejectedCount();


        VBox pendingCard =
                createStatCard(
                        "PENDING APPROVAL",
                        String.valueOf(
                                pendingCount
                        ),
                        "📋",
                        "#B7FF00"
                );


        VBox approvedCard =
                createStatCard(
                        "APPROVED THIS WEEK",
                        String.valueOf(
                                approvedThisWeek
                        ),
                        "✅",
                        "#B7FF00"
                );


        VBox rejectedCard =
                createStatCard(
                        "REJECTED",
                        String.valueOf(
                                rejectedCount
                        ),
                        "❌",
                        "#A9B2AA"
                );


        statsRow.getChildren().addAll(
                pendingCard,
                approvedCard,
                rejectedCard
        );


        for (Node node :
                statsRow.getChildren()) {

            HBox.setHgrow(
                    node,
                    Priority.ALWAYS
            );
        }


        // =====================================================
        // CONTENT AREA
        // =====================================================

        HBox contentArea =
                new HBox(20);


        // =====================================================
        // LEFT COLUMN
        // =====================================================

        VBox leftColumn =
                new VBox(15);

        leftColumn.setPrefWidth(
                600
        );

        leftColumn.setMinWidth(
                500
        );


        Label newRegistration =
                new Label(
                        "New Registrations"
                );

        newRegistration.setStyle(
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#B7FF00;"
        );


        registrationList =
                new VBox(15);


        ScrollPane registrationScroll =
                new ScrollPane(
                        registrationList
                );

        registrationScroll.setFitToWidth(
                true
        );

        registrationScroll.setPrefHeight(
                550
        );

        registrationScroll.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background:transparent;"
        );


        VBox.setVgrow(
                registrationScroll,
                Priority.ALWAYS
        );


        leftColumn.getChildren().addAll(
                newRegistration,
                registrationScroll
        );


        // =====================================================
        // RIGHT COLUMN
        // =====================================================

        detailsPane =
                createEmptyDetailsPane();


        HBox.setHgrow(
                detailsPane,
                Priority.ALWAYS
        );


        contentArea.getChildren().addAll(
                leftColumn,
                detailsPane
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        mainContainer.getChildren().addAll(
                header,
                statsRow,
                contentArea
        );


        // =====================================================
        // LOAD PENDING OWNERS
        // =====================================================

        loadPendingOwners();


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContainer
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background:#080B09;"
        );


        return scrollPane;
    }


    // =====================================================
    // HEADER
    // =====================================================

    private HBox createHeader() {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox titleBox =
                new VBox(5);


        Label title =
                new Label(
                        "Club Approval"
                );

        title.setStyle(
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#B7FF00;"
        );


        Label subtitle =
                new Label(
                        "Review and approve club owner registrations."
                );

        subtitle.setStyle(
                "-fx-font-size:14px;" +
                "-fx-text-fill:#8F9A91;"
        );


        titleBox.getChildren().addAll(
                title,
                subtitle
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        // =====================================================
        // SEARCH
        // =====================================================

        searchField =
                new TextField();

        searchField.setPromptText(
                "🔍 Search clubs or owners..."
        );

        searchField.setPrefWidth(
                250
        );

        searchField.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#2A332C;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-padding:8;" +
                "-fx-text-fill:#D7DED9;"
        );


        searchField.textProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            filterPendingOwners(
                                    newValue
                            );
                        }
                );


        header.getChildren().addAll(
                titleBox,
                spacer,
                searchField
        );


        return header;
    }


    // =====================================================
    // STAT CARD
    // =====================================================

    private VBox createStatCard(
            String title,
            String value,
            String icon,
            String iconColor
    ) {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(
                        15,
                        20,
                        15,
                        20
                )
        );

        card.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );


        HBox topRow =
                new HBox();


        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#C8D0CA;"
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-text-fill:" +
                iconColor +
                ";" +
                "-fx-font-size:16px;"
        );


        topRow.getChildren().addAll(
                titleLabel,
                spacer,
                iconLabel
        );


        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FFFFFF;"
        );


        card.getChildren().addAll(
                topRow,
                valueLabel
        );


        return card;
    }


    // =====================================================
    // LOAD PENDING OWNERS
    // =====================================================

    private void loadPendingOwners() {

        registrationList
                .getChildren()
                .clear();


        if (db == null) {

            registrationList
                    .getChildren()
                    .add(
                            createMessageCard(
                                    "Firebase",
                                    "Firebase is not connected."
                            )
                    );

            return;
        }


        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "ClubOwners"
                    )
                    .get()
                    .get();


            allPendingOwners =
                    new ArrayList<>();


            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                String role =
                        getString(
                                doc,
                                "role"
                        );

                String status =
                        getString(
                                doc,
                                "status"
                        );


                if (
                        "CLUB_OWNER"
                                .equalsIgnoreCase(
                                        role
                                )
                        &&
                        "PENDING"
                                .equalsIgnoreCase(
                                        status
                                )
                ) {

                    allPendingOwners.add(
                            doc
                    );
                }
            }


            displayPendingOwners(
                    allPendingOwners
            );


        } catch (Exception e) {

            e.printStackTrace();


            registrationList
                    .getChildren()
                    .add(
                            createMessageCard(
                                    "Error",
                                    "Unable to load registrations."
                            )
                    );
        }
    }


    // =====================================================
    // DISPLAY PENDING OWNERS
    // =====================================================

    private void displayPendingOwners(
            List<DocumentSnapshot> owners
    ) {

        registrationList
                .getChildren()
                .clear();


        if (
                owners == null
                        ||
                owners.isEmpty()
        ) {

            registrationList
                    .getChildren()
                    .add(
                            createMessageCard(
                                    "No Pending Registrations",
                                    "There are no club owner registrations waiting for approval."
                            )
                    );

            detailsPane =
                    createEmptyDetailsPane();

            return;
        }


        for (
                DocumentSnapshot doc :
                owners
        ) {

            registrationList
                    .getChildren()
                    .add(
                            createRegistrationCard(
                                    doc
                            )
                    );
        }


        // Automatically select first registration

        if (
                selectedDocument == null
                        ||
                !containsDocument(
                        owners,
                        selectedDocument.getId()
                )
        ) {

            selectOwner(
                    owners.get(0)
            );
        }
    }


    // =====================================================
    // REGISTRATION CARD
    // =====================================================

    private VBox createRegistrationCard(
            DocumentSnapshot doc
    ) {

        String clubName =
                getClubName(doc);


        String ownerName =
                getString(
                        doc,
                        "name"
                );


        String category =
                getString(
                        doc,
                        "category"
                );


        String address =
                getAddress(doc);


        if (ownerName.isEmpty()) {
            ownerName = "Unknown Owner";
        }


        if (category.isEmpty()) {
            category = "Fitness";
        }


        if (address.isEmpty()) {
            address = "Location not provided";
        }


        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(15)
        );


        card.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox(12);

        header.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox titleBox =
                new VBox(4);


        Label nameLabel =
                new Label(
                        clubName
                );

        nameLabel.setStyle(
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FFFFFF;"
        );


        Label infoLabel =
                new Label(
                        category +
                        " • " +
                        address
                );

        infoLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#D7DED9;"
        );


        titleBox.getChildren().addAll(
                nameLabel,
                infoLabel
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        Label pending =
                new Label(
                        "⏳ Pending"
                );

        pending.setStyle(
                "-fx-background-color:#243500;" +
                "-fx-text-fill:#B7FF00;" +
                "-fx-padding:4 8;" +
                "-fx-background-radius:12;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );


        header.getChildren().addAll(
                titleBox,
                spacer,
                pending
        );


        // =====================================================
        // OWNER
        // =====================================================

        HBox infoRow =
                new HBox(40);


        VBox ownerBox =
                new VBox(
                        3,
                        createSubLabel(
                                "Owner"
                        ),
                        createMainLabel(
                                ownerName
                        )
                );


        String phone =
                getString(
                        doc,
                        "phone"
                );


        VBox phoneBox =
                new VBox(
                        3,
                        createSubLabel(
                                "Phone"
                        ),
                        createMainLabel(
                                phone.isEmpty()
                                        ? "Not provided"
                                        : phone
                        )
                );


        infoRow.getChildren().addAll(
                ownerBox,
                phoneBox
        );


        // =====================================================
        // CLICK EVENT
        // =====================================================

        card.setOnMouseClicked(
                e -> {

                    selectOwner(
                            doc
                    );
                }
        );


        card.setOnMouseEntered(
                e -> {

                    card.setStyle(
                            "-fx-background-color:#151B16;" +
                            "-fx-border-color:#B7FF00;" +
                            "-fx-border-width:1;" +
                            "-fx-border-radius:8;" +
                            "-fx-background-radius:8;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        card.setOnMouseExited(
                e -> {

                    card.setStyle(
                            "-fx-background-color:#101511;" +
                            "-fx-border-color:#283129;" +
                            "-fx-border-width:1;" +
                            "-fx-border-radius:8;" +
                            "-fx-background-radius:8;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        card.getChildren().addAll(
                header,
                new Separator(),
                infoRow
        );


        return card;
    }


    // =====================================================
    // SELECT OWNER
    // =====================================================

    private void selectOwner(
            DocumentSnapshot doc
    ) {

        selectedDocument =
                doc;


        VBox newDetails =
                createDetailsPane(
                        doc
                );


        int index =
                0;


        if (
                detailsPane != null
                        &&
                detailsPane.getParent()
                        instanceof HBox
        ) {

            HBox parent =
                    (HBox)
                            detailsPane
                                    .getParent();

            index =
                    parent
                            .getChildren()
                            .indexOf(
                                    detailsPane
                            );


            parent.getChildren()
                    .set(
                            index,
                            newDetails
                    );


            HBox.setHgrow(
                    newDetails,
                    Priority.ALWAYS
            );


            detailsPane =
                    newDetails;
        }
    }


    // =====================================================
    // DETAILS PANE
    // =====================================================

    private VBox createDetailsPane(
            DocumentSnapshot doc
    ) {

        VBox pane =
                new VBox(15);

        pane.setPadding(
                new Insets(20)
        );

        pane.setPrefWidth(
                450
        );

        pane.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );


        String clubName =
                getClubName(doc);


        String ownerName =
                getString(
                        doc,
                        "name"
                );


        String email =
                getString(
                        doc,
                        "email"
                );


        String phone =
                getString(
                        doc,
                        "phone"
                );


        String category =
                getString(
                        doc,
                        "category"
                );


        String address =
                getAddress(doc);


        String description =
                getString(
                        doc,
                        "description"
                );


        String capacity =
                getCapacity(doc);


        String createdAt =
                getString(
                        doc,
                        "createdAt"
                );


        // =====================================================
        // STATUS
        // =====================================================

        Label status =
                new Label(
                        "⏳ Awaiting Review"
                );

        status.setStyle(
                "-fx-background-color:#243500;" +
                "-fx-text-fill:#B7FF00;" +
                "-fx-padding:5 10;" +
                "-fx-background-radius:12;" +
                "-fx-font-size:12px;"
        );


        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        clubName
                );

        title.setWrapText(
                true
        );

        title.setStyle(
                "-fx-font-size:22px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FFFFFF;"
        );


        // =====================================================
        // DATE
        // =====================================================

        Label date =
                new Label(
                        "Registered: " +
                        formatDate(
                                createdAt
                        )
                );

        date.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8F9A91;"
        );


        // =====================================================
        // GRID
        // =====================================================

        GridPane grid =
                new GridPane();

        grid.setHgap(
                40
        );

        grid.setVgap(
                15
        );


        grid.add(
                createInfoBox(
                        "Location",
                        address.isEmpty()
                                ? "Not provided"
                                : address
                ),
                0,
                0
        );


        grid.add(
                createInfoBox(
                        "Applicant Name",
                        ownerName.isEmpty()
                                ? "Unknown"
                                : ownerName
                ),
                1,
                0
        );


        grid.add(
                createInfoBox(
                        "Email",
                        email.isEmpty()
                                ? "Not provided"
                                : email
                ),
                0,
                1
        );


        grid.add(
                createInfoBox(
                        "Phone",
                        phone.isEmpty()
                                ? "Not provided"
                                : phone
                ),
                1,
                1
        );


        grid.add(
                createInfoBox(
                        "Category",
                        category.isEmpty()
                                ? "Not provided"
                                : category
                ),
                0,
                2
        );


        grid.add(
                createInfoBox(
                        "Capacity",
                        capacity
                ),
                1,
                2
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label pitchTitle =
                createSubLabel(
                        "Club Description"
                );


        Label pitch =
                createMainLabel(
                        description.isEmpty()
                                ? "No description provided."
                                : description
                );


        pitch.setWrapText(
                true
        );

        pitch.setPadding(
                new Insets(10)
        );

        pitch.setStyle(
                "-fx-background-color:#171D18;" +
                "-fx-background-radius:6;" +
                "-fx-text-fill:#8F9A91;" +
                "-fx-font-size:13px;"
        );


        // =====================================================
        // APPROVE BUTTON
        // =====================================================

        Button approve =
                new Button(
                        "✅ Approve Club"
                );

        approve.setMaxWidth(
                Double.MAX_VALUE
        );

        approve.setStyle(
                "-fx-background-color:#B7FF00;" +
                "-fx-text-fill:#080B09;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:10;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // REJECT BUTTON
        // =====================================================

        Button reject =
                new Button(
                        "❌ Reject Registration"
                );

        reject.setMaxWidth(
                Double.MAX_VALUE
        );

        reject.setStyle(
                "-fx-background-color:#A9B2AA;" +
                "-fx-text-fill:#080B09;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:10;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // APPROVE ACTION
        // =====================================================

        approve.setOnAction(
                e -> {

                    approveOwner(
                            doc.getId()
                    );
                }
        );


        // =====================================================
        // REJECT ACTION
        // =====================================================

        reject.setOnAction(
                e -> {

                    rejectOwner(
                            doc.getId()
                    );
                }
        );


        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        pane.getChildren().addAll(
                status,
                title,
                date,
                new Separator(),
                grid,
                new Separator(),
                pitchTitle,
                pitch,
                approve,
                reject
        );


        return pane;
    }


    // =====================================================
    // EMPTY DETAILS PANE
    // =====================================================

    private VBox createEmptyDetailsPane() {

        VBox pane =
                new VBox(15);

        pane.setPadding(
                new Insets(20)
        );

        pane.setPrefWidth(
                450
        );

        pane.setAlignment(
                Pos.CENTER
        );

        pane.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );


        Label title =
                new Label(
                        "No Registration Selected"
                );

        title.setStyle(
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FFFFFF;"
        );


        Label message =
                new Label(
                        "Select a pending club registration to review its details."
                );

        message.setWrapText(
                true
        );

        message.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8F9A91;"
        );


        pane.getChildren().addAll(
                title,
                message
        );


        return pane;
    }


    // =====================================================
    // APPROVE OWNER
    // =====================================================

    private void approveOwner(
            String documentId
    ) {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }


        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Approve Club"
        );

        confirmation.setHeaderText(
                "Approve this club?"
        );

        confirmation.setContentText(
                "The club owner will become active."
        );


        confirmation
                .showAndWait()
                .ifPresent(
                        result -> {

                            if (
                                    result ==
                                    ButtonType.OK
                            ) {

                                try {

                                    db.collection(
                                            "ClubOwners"
                                    )
                                    .document(
                                            documentId
                                    )
                                    .update(
                                            "status",
                                            "ACTIVE",
                                            "approvedAt",
                                            LocalDateTime.now()
                                                    .toString()
                                    )
                                    .get();


                                    showInfo(
                                            "Approved",
                                            "Club has been approved successfully."
                                    );


                                    selectedDocument =
                                            null;


                                    refreshPage();


                                } catch (Exception e) {

                                    e.printStackTrace();

                                    showError(
                                            "Error",
                                            "Unable to approve club."
                                    );
                                }
                            }
                        }
                );
    }


    // =====================================================
    // REJECT OWNER
    // =====================================================

    private void rejectOwner(
            String documentId
    ) {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }


        TextInputDialogHelper dialog =
                new TextInputDialogHelper();

        String reason =
                dialog.show(
                        "Reject Registration",
                        "Enter rejection reason:"
                );


        if (reason == null) {
            return;
        }


        if (
                reason.trim().isEmpty()
        ) {

            showError(
                    "Validation Error",
                    "Rejection reason is required."
            );

            return;
        }


        try {

            db.collection(
                    "ClubOwners"
            )
            .document(
                    documentId
            )
            .update(
                    "status",
                    "REJECTED",
                    "rejectionReason",
                    reason.trim(),
                    "rejectedAt",
                    LocalDateTime.now()
                            .toString()
            )
            .get();


            showInfo(
                    "Rejected",
                    "Club registration has been rejected."
            );


            selectedDocument =
                    null;


            refreshPage();


        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to reject club."
            );
        }
    }


    // =====================================================
    // REFRESH
    // =====================================================

    private void refreshPage() {

        loadPendingOwners();

    }


    // =====================================================
    // SEARCH
    // =====================================================

    private void filterPendingOwners(
            String search
    ) {

        if (
                search == null
                        ||
                search.trim().isEmpty()
        ) {

            displayPendingOwners(
                    allPendingOwners
            );

            return;
        }


        String text =
                search
                        .trim()
                        .toLowerCase(
                                Locale.ROOT
                        );


        List<DocumentSnapshot> filtered =
                new ArrayList<>();


        for (
                DocumentSnapshot doc :
                allPendingOwners
        ) {

            String clubName =
                    getClubName(doc)
                            .toLowerCase(
                                    Locale.ROOT
                            );


            String ownerName =
                    getString(
                            doc,
                            "name"
                    )
                    .toLowerCase(
                            Locale.ROOT
                    );


            String email =
                    getString(
                            doc,
                            "email"
                    )
                    .toLowerCase(
                            Locale.ROOT
                    );


            if (
                    clubName.contains(text)
                            ||
                    ownerName.contains(text)
                            ||
                    email.contains(text)
            ) {

                filtered.add(
                        doc
                );
            }
        }


        displayPendingOwners(
                filtered
        );
    }


    // =====================================================
    // PENDING COUNT
    // =====================================================

    private int getPendingCount() {

        if (db == null) {
            return 0;
        }


        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "ClubOwners"
                    )
                    .get()
                    .get();


            int count = 0;


            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                if (
                        "CLUB_OWNER"
                                .equalsIgnoreCase(
                                        getString(
                                                doc,
                                                "role"
                                        )
                                )
                        &&
                        "PENDING"
                                .equalsIgnoreCase(
                                        getString(
                                                doc,
                                                "status"
                                        )
                                )
                ) {

                    count++;
                }
            }


            return count;


        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =====================================================
    // APPROVED THIS WEEK
    // =====================================================

    private int getApprovedThisWeekCount() {

        if (db == null) {
            return 0;
        }


        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "ClubOwners"
                    )
                    .get()
                    .get();


            LocalDate today =
                    LocalDate.now();


            LocalDate startOfWeek =
                    today.minusDays(
                            today.getDayOfWeek()
                                    .getValue() - 1
                    );


            int count = 0;


            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                if (
                        !"CLUB_OWNER"
                                .equalsIgnoreCase(
                                        getString(
                                                doc,
                                                "role"
                                        )
                                )
                ) {

                    continue;
                }


                String approvedAt =
                        getString(
                                doc,
                                "approvedAt"
                        );


                if (
                        approvedAt.isEmpty()
                ) {

                    continue;
                }


                try {

                    LocalDate approvedDate =
                            LocalDateTime
                                    .parse(
                                            approvedAt
                                    )
                                    .toLocalDate();


                    if (
                            !approvedDate
                                    .isBefore(
                                            startOfWeek
                                    )
                            &&
                            !approvedDate
                                    .isAfter(
                                            today
                                    )
                    ) {

                        count++;
                    }

                } catch (Exception ignored) {
                }
            }


            return count;


        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =====================================================
    // REJECTED COUNT
    // =====================================================

    private int getRejectedCount() {

        if (db == null) {
            return 0;
        }


        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "ClubOwners"
                    )
                    .get()
                    .get();


            int count = 0;


            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                if (
                        "CLUB_OWNER"
                                .equalsIgnoreCase(
                                        getString(
                                                doc,
                                                "role"
                                        )
                                )
                        &&
                        "REJECTED"
                                .equalsIgnoreCase(
                                        getString(
                                                doc,
                                                "status"
                                        )
                                )
                ) {

                    count++;
                }
            }


            return count;


        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =====================================================
    // CLUB NAME
    // =====================================================

    private String getClubName(
            DocumentSnapshot doc
    ) {

        String clubName =
                getString(
                        doc,
                        "clubName"
                );


        if (
                clubName.isEmpty()
        ) {

            clubName =
                    getString(
                            doc,
                            "name"
                    );
        }


        if (
                clubName.isEmpty()
        ) {

            return "Club Name Not Provided";
        }


        return clubName;
    }


    // =====================================================
    // ADDRESS
    // =====================================================

    private String getAddress(
            DocumentSnapshot doc
    ) {

        String address =
                getString(
                        doc,
                        "clubAddress"
                );


        if (
                address.isEmpty()
        ) {

            address =
                    getString(
                            doc,
                            "address"
                    );
        }


        if (
                address.isEmpty()
        ) {

            return "";
        }


        return address;
    }


    // =====================================================
    // CAPACITY
    // =====================================================

    private String getCapacity(
            DocumentSnapshot doc
    ) {

        Object value =
                doc.get("capacity");


        if (value == null) {

            value =
                    doc.get(
                            "maxCapacity"
                    );
        }


        if (
                value == null
        ) {

            return "Not provided";
        }


        return String.valueOf(
                value
        );
    }


    // =====================================================
    // STRING HELPER
    // =====================================================

    private String getString(
            DocumentSnapshot doc,
            String field
    ) {

        Object value =
                doc.get(field);


        if (value == null) {
            return "";
        }


        return String.valueOf(
                value
        );
    }


    // =====================================================
    // DATE FORMAT
    // =====================================================

    private String formatDate(
            String value
    ) {

        if (
                value == null
                        ||
                value.trim().isEmpty()
        ) {

            return "Not available";
        }


        try {

            LocalDateTime date =
                    LocalDateTime.parse(
                            value
                    );


            return date.format(
                    DateTimeFormatter.ofPattern(
                            "dd MMM yyyy"
                    )
            );

        } catch (Exception e) {

            return value;
        }
    }


    // =====================================================
    // DOCUMENT CHECK
    // =====================================================

    private boolean containsDocument(
            List<DocumentSnapshot> list,
            String id
    ) {

        for (
                DocumentSnapshot doc :
                list
        ) {

            if (
                    doc.getId()
                            .equals(id)
            ) {

                return true;
            }
        }


        return false;
    }


    // =====================================================
    // INFO BOX
    // =====================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        return new VBox(
                3,
                createSubLabel(
                        title
                ),
                createMainLabel(
                        value
                )
        );
    }


    // =====================================================
    // SUB LABEL
    // =====================================================

    private Label createSubLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#B7FF00;"
        );


        return label;
    }


    // =====================================================
    // MAIN LABEL
    // =====================================================

    private Label createMainLabel(
            String text
    ) {

        Label label =
                new Label(
                        text == null
                                ? ""
                                : text
                );


        label.setWrapText(
                true
        );


        label.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#C7D0C8;"
        );


        return label;
    }


    // =====================================================
    // MESSAGE CARD
    // =====================================================

    private VBox createMessageCard(
            String title,
            String message
    ) {

        VBox box =
                new VBox(8);

        box.setPadding(
                new Insets(20)
        );

        box.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );


        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FFFFFF;"
        );


        Label messageLabel =
                new Label(message);

        messageLabel.setWrapText(
                true
        );

        messageLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8F9A91;"
        );


        box.getChildren().addAll(
                titleLabel,
                messageLabel
        );


        return box;
    }


    // =====================================================
    // INFO ALERT
    // =====================================================

    private void showInfo(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // =====================================================
    // ERROR ALERT
    // =====================================================

    private void showError(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // =====================================================
    // TEXT INPUT DIALOG HELPER
    // =====================================================

    private static class TextInputDialogHelper {

        public String show(
                String title,
                String message
        ) {

            Dialog<String> dialog =
                    new Dialog<>();


            dialog.setTitle(
                    title
            );

            dialog.setHeaderText(
                    message
            );


            TextArea textArea =
                    new TextArea();

            textArea.setPromptText(
                    "Reason for rejection..."
            );

            textArea.setPrefRowCount(
                    4
            );

            textArea.setWrapText(
                    true
            );


            dialog.getDialogPane()
                    .setContent(
                            textArea
                    );


            ButtonType submit =
                    new ButtonType(
                            "Reject",
                            ButtonBar.ButtonData.OK_DONE
                    );


            dialog.getDialogPane()
                    .getButtonTypes()
                    .addAll(
                            submit,
                            ButtonType.CANCEL
                    );


            dialog.setResultConverter(
                    button -> {

                        if (
                                button == submit
                        ) {

                            return textArea
                                    .getText();
                        }

                        return null;
                    }
            );


            return dialog
                    .showAndWait()
                    .orElse(null);
        }
    }
}