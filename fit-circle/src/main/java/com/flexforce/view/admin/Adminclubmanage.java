package com.flexforce.view.admin;

import com.flexforce.config.FirebaseConfig;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.scene.layout.RowConstraints;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Adminclubmanage {

   // =========================================================
    // FIREBASE
    // =========================================================

    private Firestore db;

    private final List<DocumentSnapshot> allClubs =
            new ArrayList<>();


    // =========================================================
    // CREATE CLUB PAGE
    // =========================================================

    public VBox createClubPage() {

        // =========================================================
        // FIREBASE INITIALIZATION
        // =========================================================

        initializeFirestore();


        // =========================================================
        // MAIN PAGE
        // =========================================================

        VBox main = new VBox();

        main.setFillWidth(true);

        main.setStyle(
                "-fx-background-color:#080B09;"
        );


        // =========================================================
        // TOP BAR
        // =========================================================

        TextField search = new TextField();

        search.setPromptText(
                "Search clubs, owners..."
        );

        search.setPrefHeight(42);

        search.setPrefWidth(300);

        search.setStyle(
                "-fx-background-color:#0D120F;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#7F8982;" +
                "-fx-background-radius:9px;" +
                "-fx-border-color:#263029;" +
                "-fx-border-radius:9px;" +
                "-fx-border-width:1px;" +
                "-fx-padding:0 15px;" +
                "-fx-font-size:14px;"
        );


        Label notification =
                new Label("●");

        notification.setTextFill(
                Color.web("#B7FF00")
        );

        notification.setStyle(
                "-fx-font-size:12px;" +
                "-fx-background-color:#172014;" +
                "-fx-background-radius:20px;" +
                "-fx-padding:13px;"
        );


        Label question =
                new Label("?");

        question.setTextFill(
                Color.WHITE
        );

        question.setAlignment(
                Pos.CENTER
        );

        question.setStyle(
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:#667169;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:50%;" +
                "-fx-background-radius:50%;" +
                "-fx-padding:7px 11px;"
        );


        Label profile =
                new Label("A");

        profile.setTextFill(
                Color.BLACK
        );

        profile.setAlignment(
                Pos.CENTER
        );

        profile.setStyle(
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-color:#B7FF00;" +
                "-fx-background-radius:50%;" +
                "-fx-padding:10px 13px;"
        );


        HBox topRight =
                new HBox(
                        18,
                        notification,
                        question,
                        profile
                );

        topRight.setAlignment(
                Pos.CENTER_RIGHT
        );


        Region topSpace =
                new Region();

        HBox.setHgrow(
                topSpace,
                Priority.ALWAYS
        );


        HBox topBar =
                new HBox(
                        search,
                        topSpace,
                        topRight
                );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(
                        15,
                        30,
                        15,
                        30
                )
        );

        topBar.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#252E27;" +
                "-fx-border-width:0 0 1px 0;"
        );


        // =========================================================
        // PAGE HEADING
        // =========================================================

        Label title =
                new Label("Manage Clubs");

        title.setTextFill(
                Color.WHITE
        );

        title.setStyle(
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        Label subtitle =
                new Label(
                        "View, monitor and manage all fitness clubs registered on FitCircle."
                );

        subtitle.setTextFill(
                Color.web("#89938C")
        );

        subtitle.setStyle(
                "-fx-font-size:14px;"
        );


        VBox headingText =
                new VBox(
                        7,
                        title,
                        subtitle
                );


       /*  Button addClub =
                new Button("+   Add Club");

        addClub.setPrefHeight(44);

        addClub.setPrefWidth(135);

        addClub.setStyle(
                "-fx-background-color:#B7FF00;" +
                "-fx-text-fill:#070A05;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );
*/

        Region headingSpace =
                new Region();

        HBox.setHgrow(
                headingSpace,
                Priority.ALWAYS
        );


        HBox heading =
                new HBox(
                        headingText,
                        headingSpace
                        //addClub
                );

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        heading.setPadding(
                new Insets(
                        35,
                        35,
                        25,
                        35
                )
        );


        // =========================================================
        // STATISTICS CARDS
        // =========================================================

        GridPane cards =
                new GridPane();

        cards.setHgap(18);

        cards.setVgap(18);

        cards.setPadding(
                new Insets(
                        0,
                        35,
                        30,
                        35
                )
        );


        ColumnConstraints cardColumn1 =
                new ColumnConstraints();

        ColumnConstraints cardColumn2 =
                new ColumnConstraints();

        ColumnConstraints cardColumn3 =
                new ColumnConstraints();

        ColumnConstraints cardColumn4 =
                new ColumnConstraints();


        cardColumn1.setPercentWidth(25);

        cardColumn2.setPercentWidth(25);

        cardColumn3.setPercentWidth(25);

        cardColumn4.setPercentWidth(25);


        cards.getColumnConstraints().addAll(
                cardColumn1,
                cardColumn2,
                cardColumn3,
                cardColumn4
        );


        // =========================================================
        // TOTAL CLUBS
        // =========================================================

        Label totalIcon =
                new Label("▦");

        totalIcon.setTextFill(
                Color.web("#B7FF00")
        );

        totalIcon.setStyle(
                "-fx-font-size:24px;" +
                "-fx-background-color:#1C271E;" +
                "-fx-background-radius:50%;" +
                "-fx-padding:12px;"
        );


        Label totalText =
                new Label("TOTAL CLUBS");

        totalText.setTextFill(
                Color.web("#89938C")
        );

        totalText.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );


        Label totalNumber =
                new Label("0");

        totalNumber.setTextFill(
                Color.WHITE
        );

        totalNumber.setStyle(
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        VBox totalInfo =
                new VBox(
                        5,
                        totalText,
                        totalNumber
                );


        HBox totalCard =
                new HBox(
                        18,
                        totalIcon,
                        totalInfo
                );

        totalCard.setAlignment(
                Pos.CENTER_LEFT
        );

        totalCard.setPrefHeight(125);

        totalCard.setMaxWidth(
                Double.MAX_VALUE
        );

        totalCard.setPadding(
                new Insets(22)
        );

        totalCard.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );


        // =========================================================
        // ACTIVE CLUBS
        // =========================================================

        Label activeIcon =
                new Label("✓");

        activeIcon.setTextFill(
                Color.web("#B7FF00")
        );

        activeIcon.setStyle(
                "-fx-font-size:23px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-color:#1D3300;" +
                "-fx-background-radius:50%;" +
                "-fx-padding:12px 14px;"
        );


        Label activeText =
                new Label("ACTIVE CLUBS");

        activeText.setTextFill(
                Color.web("#89938C")
        );

        activeText.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );


        Label activeNumber =
                new Label("0");

        activeNumber.setTextFill(
                Color.WHITE
        );

        activeNumber.setStyle(
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        VBox activeInfo =
                new VBox(
                        5,
                        activeText,
                        activeNumber
                );


        HBox activeCard =
                new HBox(
                        18,
                        activeIcon,
                        activeInfo
                );

        activeCard.setAlignment(
                Pos.CENTER_LEFT
        );

        activeCard.setPrefHeight(125);

        activeCard.setMaxWidth(
                Double.MAX_VALUE
        );

        activeCard.setPadding(
                new Insets(22)
        );

        activeCard.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );


        // =========================================================
        // PENDING CLUBS
        // =========================================================

        Label pendingIcon =
                new Label("...");

        pendingIcon.setTextFill(
                Color.web("#B7FF00")
        );

        pendingIcon.setStyle(
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-color:#293800;" +
                "-fx-background-radius:50%;" +
                "-fx-padding:14px 11px;"
        );


        Label pendingText =
                new Label(
                        "PENDING APPROVAL"
                );

        pendingText.setTextFill(
                Color.web("#89938C")
        );

        pendingText.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );


        Label pendingNumber =
                new Label("0");

        pendingNumber.setTextFill(
                Color.WHITE
        );

        pendingNumber.setStyle(
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        VBox pendingInfo =
                new VBox(
                        5,
                        pendingText,
                        pendingNumber
                );


        HBox pendingCard =
                new HBox(
                        18,
                        pendingIcon,
                        pendingInfo
                );

        pendingCard.setAlignment(
                Pos.CENTER_LEFT
        );

        pendingCard.setPrefHeight(125);

        pendingCard.setMaxWidth(
                Double.MAX_VALUE
        );

        pendingCard.setPadding(
                new Insets(22)
        );

        pendingCard.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );


        // =========================================================
        // INACTIVE CLUBS
        // =========================================================

        Label inactiveIcon =
                new Label("○");

        inactiveIcon.setTextFill(
                Color.web("#89938C")
        );

        inactiveIcon.setStyle(
                "-fx-font-size:24px;" +
                "-fx-background-color:#283129;" +
                "-fx-background-radius:50%;" +
                "-fx-padding:11px 13px;"
        );


        Label inactiveText =
                new Label(
                        "INACTIVE CLUBS"
                );

        inactiveText.setTextFill(
                Color.web("#89938C")
        );

        inactiveText.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );


        Label inactiveNumber =
                new Label("0");

        inactiveNumber.setTextFill(
                Color.WHITE
        );

        inactiveNumber.setStyle(
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        VBox inactiveInfo =
                new VBox(
                        5,
                        inactiveText,
                        inactiveNumber
                );


        HBox inactiveCard =
                new HBox(
                        18,
                        inactiveIcon,
                        inactiveInfo
                );

        inactiveCard.setAlignment(
                Pos.CENTER_LEFT
        );

        inactiveCard.setPrefHeight(125);

        inactiveCard.setMaxWidth(
                Double.MAX_VALUE
        );

        inactiveCard.setPadding(
                new Insets(22)
        );

        inactiveCard.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );


        cards.add(
                totalCard,
                0,
                0
        );

        cards.add(
                activeCard,
                1,
                0
        );

        cards.add(
                pendingCard,
                2,
                0
        );

        cards.add(
                inactiveCard,
                3,
                0
        );


        // =========================================================
        // SEARCH AND FILTER SECTION
        // =========================================================

        TextField clubSearch =
                new TextField();

        clubSearch.setPromptText(
                "Search clubs..."
        );

        clubSearch.setPrefHeight(42);

        clubSearch.setStyle(
                "-fx-background-color:#0D120F;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#7F8982;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:0 15px;" +
                "-fx-font-size:13px;"
        );


        Button locationButton =
                new Button(
                        "Location  •  All"
                );

        locationButton.setPrefHeight(42);

        locationButton.setStyle(
                "-fx-background-color:#141A16;" +
                "-fx-text-fill:#D5DDD7;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:0 18px;"
        );


        Button statusButton =
                new Button(
                        "Status  •  All"
                );

        statusButton.setPrefHeight(42);

        statusButton.setStyle(
                "-fx-background-color:#141A16;" +
                "-fx-text-fill:#D5DDD7;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:0 18px;"
        );


        Button sortButton =
                new Button(
                        "Newest First  ▾"
                );

        sortButton.setPrefHeight(42);

        sortButton.setStyle(
                "-fx-background-color:#141A16;" +
                "-fx-text-fill:#D5DDD7;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:0 18px;"
        );


        HBox filters =
                new HBox(
                        12,
                        clubSearch,
                        locationButton,
                        statusButton,
                        sortButton
                );

        filters.setAlignment(
                Pos.CENTER_LEFT
        );


        HBox filterBox =
                new HBox(
                        filters
                );

        filterBox.setPadding(
                new Insets(
                        20,
                        25,
                        20,
                        25
                )
        );

        filterBox.setStyle(
                "-fx-background-color:#101511;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );


        VBox filterSection =
                new VBox(
                        filterBox
                );

        filterSection.setPadding(
                new Insets(
                        0,
                        35,
                        25,
                        35
                )
        );


        // =========================================================
        // TABLE
        // =========================================================

        GridPane table =
                new GridPane();

        table.setMaxWidth(
                Double.MAX_VALUE
        );

        table.setStyle(
                "-fx-background-color:#0D120F;" +
                "-fx-border-color:#283129;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );


        // =========================================================
        // COLUMN WIDTHS
        // =========================================================

        ColumnConstraints c1 =
                new ColumnConstraints();

        ColumnConstraints c2 =
                new ColumnConstraints();

        ColumnConstraints c3 =
                new ColumnConstraints();

        ColumnConstraints c4 =
                new ColumnConstraints();

        ColumnConstraints c5 =
                new ColumnConstraints();

        ColumnConstraints c6 =
                new ColumnConstraints();

        ColumnConstraints c7 =
                new ColumnConstraints();


        c1.setPercentWidth(20);
        c2.setPercentWidth(15);
        c3.setPercentWidth(15);
        c4.setPercentWidth(10);
        c5.setPercentWidth(13);
        c6.setPercentWidth(12);
        c7.setPercentWidth(15);


        table.getColumnConstraints().addAll(
                c1,
                c2,
                c3,
                c4,
                c5,
                c6,
                c7
        );


        // =========================================================
        // TABLE HEADER
        // =========================================================

        Label clubHeader =
                new Label("CLUB");

        Label ownerHeader =
                new Label("OWNER");

        Label locationHeader =
                new Label("LOCATION");

        Label membersHeader =
                new Label("MEMBERS");

        Label joinedHeader =
                new Label("JOINED DATE");

        Label statusHeader =
                new Label("STATUS");

        Label actionHeader =
                new Label("ACTIONS");


        Label[] headers = {
                clubHeader,
                ownerHeader,
                locationHeader,
                membersHeader,
                joinedHeader,
                statusHeader,
                actionHeader
        };


        for (Label header : headers) {

            header.setTextFill(
                    Color.web("#B7FF00")
            );

            header.setStyle(
                    "-fx-font-size:12px;" +
                    "-fx-font-weight:bold;"
            );

            header.setPadding(
                    new Insets(
                            20,
                            15,
                            20,
                            15
                    )
            );
        }


        table.add(
                clubHeader,
                0,
                0
        );

        table.add(
                ownerHeader,
                1,
                0
        );

        table.add(
                locationHeader,
                2,
                0
        );

        table.add(
                membersHeader,
                3,
                0
        );

        table.add(
                joinedHeader,
                4,
                0
        );

        table.add(
                statusHeader,
                5,
                0
        );

        table.add(
                actionHeader,
                6,
                0
        );


        table.getRowConstraints().add(
                new RowConstraints(70)
        );


        // =========================================================
        // TABLE CONTAINER
        // =========================================================

        VBox tableSection =
                new VBox(
                        table
                );

        tableSection.setPadding(
                new Insets(
                        0,
                        35,
                        35,
                        35
                )
        );


        // =========================================================
        // SCROLLABLE CONTENT
        // =========================================================

        VBox content =
                new VBox(
                        heading,
                        cards,
                        filterSection,
                        tableSection
                );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color:#080B09;"
        );


        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color:#080B09;" +
                "-fx-border-color:transparent;"
        );


        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );


        main.getChildren().addAll(
                topBar,
                scrollPane
        );


        // =========================================================
        // LOAD CLUBS FROM FIREBASE
        // =========================================================

        loadClubsFromFirebase(
                table,
                totalNumber,
                activeNumber,
                pendingNumber,
                inactiveNumber
        );


        // =========================================================
        // SEARCH
        // =========================================================

        clubSearch.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    filterClubs(
                            table,
                            newValue
                    );
                }
        );


        search.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    filterClubs(
                            table,
                            newValue
                    );
                }
        );


        return main;
    }


    // =========================================================
    // INITIALIZE FIREBASE
    // =========================================================

    private void initializeFirestore() {

        try {

            db =
                    FirebaseConfig
                            .getFirebaseConfig();

            if (db == null) {

                System.out.println(
                        ">>> Club Management Firebase = NULL"
                );

                return;
            }

            System.out.println(
                    ">>> Club Management Firebase Connected"
            );

        } catch (Exception e) {

            System.out.println(
                    ">>> Club Management Firebase FAILED"
            );

            e.printStackTrace();

            db = null;
        }
    }


    // =========================================================
    // LOAD CLUBS FROM FIREBASE
    // =========================================================

    private void loadClubsFromFirebase(
            GridPane table,
            Label totalNumber,
            Label activeNumber,
            Label pendingNumber,
            Label inactiveNumber) {

        try {

            System.out.println(
                    ">>> Loading clubs from Firebase..."
            );


            if (db == null) {

                initializeFirestore();
            }


            if (db == null) {

                System.out.println(
                        ">>> Firestore is NULL"
                );

                return;
            }


            QuerySnapshot snapshot =
                    db.collection(
                            "ClubOwners"
                    )
                    .get()
                    .get();


            allClubs.clear();


            allClubs.addAll(
                    snapshot.getDocuments()
            );


            int total =
                    allClubs.size();

            int active = 0;

            int pending = 0;

            int inactive = 0;


            for (DocumentSnapshot doc :
                    allClubs) {

                String status =
                        getString(
                                doc,
                                "status",
                                "ACTIVE"
                        )
                        .trim()
                        .toUpperCase(
                                Locale.ROOT
                        );


                if (status.equals("ACTIVE")) {

                    active++;

                } else if (
                        status.equals("PENDING")) {

                    pending++;

                } else if (
                        status.equals("INACTIVE")) {

                    inactive++;
                }
            }


            totalNumber.setText(
                    String.valueOf(total)
            );

            activeNumber.setText(
                    String.valueOf(active)
            );

            pendingNumber.setText(
                    String.valueOf(pending)
            );

            inactiveNumber.setText(
                    String.valueOf(inactive)
            );


            refreshTable(
                    table,
                    allClubs
            );


            System.out.println(
                    ">>> TOTAL CLUBS = "
                            + total
            );

            System.out.println(
                    ">>> ACTIVE CLUBS = "
                            + active
            );

            System.out.println(
                    ">>> PENDING CLUBS = "
                            + pending
            );

            System.out.println(
                    ">>> INACTIVE CLUBS = "
                            + inactive
            );

        } catch (Exception e) {

            System.out.println(
                    ">>> ERROR LOADING CLUBS"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // REFRESH TABLE
    // =========================================================

    private void refreshTable(
            GridPane table,
            List<DocumentSnapshot> clubs) {


        // Remove old rows
        table.getChildren().removeIf(
                node -> {

                    Integer row =
                            GridPane.getRowIndex(
                                    node
                            );

                    return row != null
                            && row > 0;
                }
        );


        // Remove old row constraints
        while (
                table.getRowConstraints()
                        .size() > 1
        ) {

            table.getRowConstraints()
                    .remove(
                            table.getRowConstraints()
                                    .size() - 1
                    );
        }


        int row = 1;


        for (DocumentSnapshot doc :
                clubs) {


            String clubName =
                    getString(
                            doc,
                            "clubName",
                            getString(
                                    doc,
                                    "name",
                                    "Unknown Club"
                            )
                    );


            String owner =
                    getString(
                            doc,
                            "ownerName",
                            getString(
                                    doc,
                                    "ownerId",
                                    "Unknown"
                            )
                    );


            String location =
                    getString(
                            doc,
                            "address",
                            "Not available"
                    );


            String members =
                    getString(
                            doc,
                            "members",
                            getString(
                                    doc,
                                    "activeTrainers",
                                    "0"
                            )
                    );


            String joinedDate =
                    getString(
                            doc,
                            "createdAt",
                            ""
                    );


            joinedDate =
                    formatDate(
                            joinedDate
                    );


            String status =
                    getString(
                            doc,
                            "status",
                            "ACTIVE"
                    )
                    .trim()
                    .toUpperCase(
                            Locale.ROOT
                    );


            // =====================================================
            // CLUB
            // =====================================================

            Label club =
                    new Label(
                            clubName
                    );

            club.setTextFill(
                    Color.WHITE
            );

            club.setStyle(
                    "-fx-font-size:14px;" +
                    "-fx-font-weight:bold;"
            );
            club.setPadding(
        new Insets(0, 0, 0, 15)
    );


            // =====================================================
            // OWNER
            // =====================================================

            Label ownerLabel =
                    new Label(
                            owner
                    );


            // =====================================================
            // LOCATION
            // =====================================================

            Label locationLabel =
                    new Label(
                            location
                    );


            // =====================================================
            // MEMBERS
            // =====================================================

            Label membersLabel =
                    new Label(
                            members
                    );


            // =====================================================
            // JOINED DATE
            // =====================================================

            Label joinedLabel =
                    new Label(
                            joinedDate
                    );


            // =====================================================
            // STATUS
            // =====================================================

            Label statusLabel =
                    new Label(
                            "  "
                                    + status
                                    + "  "
                    );


            if (
                    status.equals("ACTIVE")
            ) {

                statusLabel.setTextFill(
                        Color.web("#B7FF00")
                );

                statusLabel.setStyle(
                        "-fx-background-color:#1D3300;" +
                        "-fx-background-radius:15px;" +
                        "-fx-padding:6px 12px;" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
                );

            } else if (
                    status.equals("PENDING")
            ) {

                statusLabel.setTextFill(
                        Color.web("#B7FF00")
                );

                statusLabel.setStyle(
                        "-fx-background-color:#293800;" +
                        "-fx-background-radius:15px;" +
                        "-fx-padding:6px 12px;" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
                );

            } else {

                statusLabel.setTextFill(
                        Color.web("#89938C")
                );

                statusLabel.setStyle(
                        "-fx-background-color:#252D27;" +
                        "-fx-background-radius:15px;" +
                        "-fx-padding:6px 12px;" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
                );
            }


            // =====================================================
            // ACTION
            // =====================================================

            Label action =
                    new Label("⋮");

            action.setTextFill(
                    Color.web("#7F8982")
            );

            action.setStyle(
                    "-fx-font-size:24px;"
            );


            // =====================================================
            // NORMAL LABEL STYLE
            // =====================================================

            Label[] rowLabels = {
                    ownerLabel,
                    locationLabel,
                    membersLabel,
                    joinedLabel
            };


            for (
                    Label label :
                    rowLabels
            ) {

                label.setTextFill(
                        Color.web("#C2CCC4")
                );

                label.setStyle(
                        "-fx-font-size:13px;"
                );
            }


            // =====================================================
            // ADD TO TABLE
            // =====================================================

            table.add(
                    club,
                    0,
                    row
            );

            table.add(
                    ownerLabel,
                    1,
                    row
            );

            table.add(
                    locationLabel,
                    2,
                    row
            );

            table.add(
                    membersLabel,
                    3,
                    row
            );

            table.add(
                    joinedLabel,
                    4,
                    row
            );

            table.add(
                    statusLabel,
                    5,
                    row
            );

            table.add(
                    action,
                    6,
                    row
            );


            table.getRowConstraints().add(
                    new RowConstraints(70)
            );


            row++;
        }


        // =====================================================
        // NO CLUBS
        // =====================================================

        if (clubs.isEmpty()) {

            Label empty =
                    new Label(
                            "No clubs found."
                    );

            empty.setTextFill(
                    Color.web("#89938C")
            );

            empty.setStyle(
                    "-fx-font-size:14px;"
            );


            table.add(
                    empty,
                    0,
                    1
            );


            GridPane.setColumnSpan(
                    empty,
                    7
            );


            table.getRowConstraints().add(
                    new RowConstraints(70)
            );
        }
    }


    // =========================================================
    // SEARCH CLUBS
    // =========================================================

    private void filterClubs(
            GridPane table,
            String searchText) {


        if (
                searchText == null
                || searchText.trim().isEmpty()
        ) {

            refreshTable(
                    table,
                    allClubs
            );

            return;
        }


        String search =
                searchText
                        .trim()
                        .toLowerCase(
                                Locale.ROOT
                        );


        List<DocumentSnapshot> filtered =
                new ArrayList<>();


        for (
                DocumentSnapshot doc :
                allClubs
        ) {


            String clubName =
                    getString(
                            doc,
                            "clubName",
                            getString(
                                    doc,
                                    "name",
                                    ""
                            )
                    );


            String owner =
                    getString(
                            doc,
                            "ownerName",
                            getString(
                                    doc,
                                    "ownerId",
                                    ""
                            )
                    );


            String address =
                    getString(
                            doc,
                            "address",
                            ""
                    );


            String email =
                    getString(
                            doc,
                            "email",
                            ""
                    );


            if (
                    clubName
                            .toLowerCase(
                                    Locale.ROOT
                            )
                            .contains(search)

                    ||

                    owner
                            .toLowerCase(
                                    Locale.ROOT
                            )
                            .contains(search)

                    ||

                    address
                            .toLowerCase(
                                    Locale.ROOT
                            )
                            .contains(search)

                    ||

                    email
                            .toLowerCase(
                                    Locale.ROOT
                            )
                            .contains(search)
            ) {

                filtered.add(doc);
            }
        }


        refreshTable(
                table,
                filtered
        );
    }


    // =========================================================
    // GET STRING FROM FIREBASE
    // =========================================================

    private String getString(
            DocumentSnapshot doc,
            String field,
            String defaultValue) {


        Object value =
                doc.get(field);


        if (value == null) {

            return defaultValue;
        }


        return String.valueOf(
                value
        );
    }


    // =========================================================
    // FORMAT DATE
    // =========================================================

    private String formatDate(
            String value) {


        if (
                value == null
                || value.trim().isEmpty()
        ) {

            return "—";
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
}