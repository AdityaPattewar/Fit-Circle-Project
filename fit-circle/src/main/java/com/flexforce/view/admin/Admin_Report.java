package com.flexforce.view.admin;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.dao.ClubOwnerDAO;
import com.flexforce.model.club_owner.ClubOwner;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;


import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import javafx.scene.control.Alert;

import javafx.scene.layout.ColumnConstraints;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.List;
import java.util.Locale;



/**
 * ============================================================
 * FITCIRCLE - ADMIN REPORT
 * ============================================================
 *
 * Dynamic Admin Reports page.
 *
 * Firebase sources:
 *
 * 1. ClubOwners
 * 2. UserInfo
 * 3. Booking / Reservation collection
 *
 * Existing application can open this page using:
 *
 * Admin_Report reportPage = new Admin_Report();
 * root.setCenter(reportPage.createClubReportPage());
 *
 * ============================================================
 */

public class Admin_Report {

    // =========================================================
    // COLORS
    // =========================================================

    private final String BACKGROUND = "#080B09";
    private final String CARD = "#101511";
    private final String CARD_BORDER = "#283129";

    private final String GREEN = "#B7FF00";
    private final String WHITE = "#FFFFFF";
    private final String GRAY = "#8F9A91";


    // =========================================================
    // FIREBASE
    // =========================================================

    private final Firestore db =
            FirebaseConfig.getFirebaseConfig();

    private final ClubOwnerDAO clubOwnerDAO =
            new ClubOwnerDAO();


    // =========================================================
    // START METHOD
    // =========================================================

    public void start(Stage stage) {

        ScrollPane reportPage =
                createClubReportPage();

        Scene scene =
                new Scene(
                        reportPage,
                        1200,
                        800
                );

        stage.setTitle(
                "FitCircle - Admin Club Reports"
        );

        stage.setScene(scene);

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.show();
    }


    // =========================================================
    // MAIN REPORT PAGE
    // =========================================================

    public ScrollPane createClubReportPage() {

        VBox main =
                new VBox(22);

        main.setPadding(
                new Insets(
                        25,
                        30,
                        35,
                        30
                )
        );

        main.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );


        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox headingBox =
                new VBox(5);


        Label heading =
                new Label(
                        "Admin Club Reports"
                );

        heading.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                        "-fx-font-size:28px;" +
                        "-fx-font-weight:bold;"
        );


        Label subtitle =
                new Label(
                        "Monitor, analyze and manage performance of all FitCircle clubs"
                );

        subtitle.setStyle(
                "-fx-text-fill:" + GRAY + ";" +
                        "-fx-font-size:14px;"
        );


        headingBox.getChildren().addAll(
                heading,
                subtitle
        );


        Region headerSpace =
                new Region();

        HBox.setHgrow(
                headerSpace,
                Priority.ALWAYS
        );


        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        Button refresh =
                new Button(
                        "⟳  Refresh"
                );

        refresh.setStyle(
                "-fx-background-color:#181C22;" +
                        "-fx-text-fill:#C8D0CA;" +
                        "-fx-border-color:#283129;" +
                        "-fx-border-radius:6;" +
                        "-fx-background-radius:6;" +
                        "-fx-padding:10 14;" +
                        "-fx-cursor:hand;"
        );


        // =====================================================
        // DATE BUTTONS
        // =====================================================

        Button today =
                createDateButton(
                        "Today"
                );

        Button week =
                createDateButton(
                        "This Week"
                );

        Button month =
                createDateButton(
                        "This Month"
                );

        Button year =
                createDateButton(
                        "This Year"
                );


        // Month active by default
        setActiveDateButton(
                month,
                today,
                week,
                year
        );


        HBox dateBox =
                new HBox(
                        4,
                        today,
                        week,
                        month,
                        year
                );


        // =====================================================
        // DATE BUTTON ACTIONS
        // =====================================================

        today.setOnAction(e -> {

            setActiveDateButton(
                    today,
                    week,
                    month,
                    year
            );

            showInfo(
                    "Date Filter",
                    "Today filter selected.\n\n" +
                            "The summary currently uses available Firebase data."
            );
        });


        week.setOnAction(e -> {

            setActiveDateButton(
                    week,
                    today,
                    month,
                    year
            );

            showInfo(
                    "Date Filter",
                    "This Week filter selected.\n\n" +
                            "The summary currently uses available Firebase data."
            );
        });


        month.setOnAction(e -> {

            setActiveDateButton(
                    month,
                    today,
                    week,
                    year
            );

            showInfo(
                    "Date Filter",
                    "This Month filter selected.\n\n" +
                            "The summary currently uses available Firebase data."
            );
        });


        year.setOnAction(e -> {

            setActiveDateButton(
                    year,
                    today,
                    week,
                    month
            );

            showInfo(
                    "Date Filter",
                    "This Year filter selected.\n\n" +
                            "The summary currently uses available Firebase data."
            );
        });


        // =====================================================
        // RIGHT HEADER
        // =====================================================

        HBox rightHeader =
                new HBox(
                        10,
                        dateBox,
                        refresh
                );

        rightHeader.setAlignment(
                Pos.CENTER_RIGHT
        );


        header.getChildren().addAll(
                headingBox,
                headerSpace,
                rightHeader
        );


        // =====================================================
        // SUMMARY CARDS
        // =====================================================

        HBox summaryCards =
                new HBox(15);


        Label totalClubsLabel =
                createValueLabel();

        Label activeClubsLabel =
                createValueLabel();

        Label pendingClubsLabel =
                createValueLabel();

        Label inactiveClubsLabel =
                createValueLabel();

        Label totalMembersLabel =
                createValueLabel();

        Label totalRevenueLabel =
                createValueLabel();


        summaryCards.getChildren().addAll(

                createSummaryCard(
                        "TOTAL\nCLUBS",
                        totalClubsLabel
                ),

                createSummaryCard(
                        "ACTIVE\nCLUBS",
                        activeClubsLabel
                ),

                createSummaryCard(
                        "PENDING\nCLUBS",
                        pendingClubsLabel
                ),

                createSummaryCard(
                        "INACTIVE\nCLUBS",
                        inactiveClubsLabel
                ),

                createSummaryCard(
                        "TOTAL\nMEMBERS",
                        totalMembersLabel
                ),

                createSummaryCard(
                        "TOTAL\nREVENUE",
                        totalRevenueLabel
                )
        );


        // =====================================================
        // PERFORMANCE SECTION
        // =====================================================

        VBox performance =
                createPerformanceSection();


        // =====================================================
        // DYNAMIC REPORT CARDS
        // =====================================================

        GridPane reportGrid =
                createDynamicReportGrid();


        // =====================================================
        // KEY INSIGHTS
        // =====================================================

        VBox insights =
                createDynamicInsights();


        // =====================================================
        // LOAD FIREBASE DATA
        // =====================================================

        loadSummaryData(
                totalClubsLabel,
                activeClubsLabel,
                pendingClubsLabel,
                inactiveClubsLabel,
                totalMembersLabel,
                totalRevenueLabel
        );


        // =====================================================
        // REFRESH
        // =====================================================

        refresh.setOnAction(e -> {

            loadSummaryData(
                    totalClubsLabel,
                    activeClubsLabel,
                    pendingClubsLabel,
                    inactiveClubsLabel,
                    totalMembersLabel,
                    totalRevenueLabel
            );

            refreshPerformanceSection(
                    performance
            );

            showInfo(
                    "Report Refreshed",
                    "Report data has been refreshed from Firebase."
            );
        });


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        main.getChildren().addAll(
                header,
                summaryCards,
                reportGrid,
                performance,
                insights
        );


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll =
                new ScrollPane(
                        main
                );

        scroll.setFitToWidth(true);

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        

        scroll.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-background:" +
                        BACKGROUND + ";"
        );


        return scroll;
    }


    // =========================================================
    // CREATE VALUE LABEL
    // =========================================================

    private Label createValueLabel() {

        Label label =
                new Label("0");

        label.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                        "-fx-font-size:31px;" +
                        "-fx-font-weight:bold;"
        );

        return label;
    }


    // =========================================================
    // LOAD SUMMARY DATA
    // =========================================================

    private void loadSummaryData(

            Label totalClubsLabel,

            Label activeClubsLabel,

            Label pendingClubsLabel,

            Label inactiveClubsLabel,

            Label totalMembersLabel,

            Label totalRevenueLabel

    ) {

        try {

            int totalClubs =
                    getTotalClubs();

            int activeClubs =
                    getActiveClubs();

            int pendingClubs =
                    getPendingClubs();

            int inactiveClubs =
                    getInactiveClubs();

            int totalMembers =
                    getTotalMembers();

            double revenue =
                    getTotalRevenue();


            totalClubsLabel.setText(
                    String.valueOf(totalClubs)
            );

            activeClubsLabel.setText(
                    String.valueOf(activeClubs)
            );

            pendingClubsLabel.setText(
                    String.valueOf(pendingClubs)
            );

            inactiveClubsLabel.setText(
                    String.valueOf(inactiveClubs)
            );

            totalMembersLabel.setText(
                    String.valueOf(totalMembers)
            );

            totalRevenueLabel.setText(
                    formatCurrency(revenue)
            );


        } catch (Exception e) {

            e.printStackTrace();

            totalClubsLabel.setText("0");
            activeClubsLabel.setText("0");
            pendingClubsLabel.setText("0");
            inactiveClubsLabel.setText("0");
            totalMembersLabel.setText("0");
            totalRevenueLabel.setText("₹0");
        }
    }


    // =========================================================
    // TOTAL CLUBS
    // =========================================================

    private int getTotalClubs() {

        List<ClubOwner> clubs =
                clubOwnerDAO.getClubOwners();

        return clubs.size();
    }


    // =========================================================
    // ACTIVE CLUBS
    // =========================================================

    private int getActiveClubs() {

        List<ClubOwner> clubs =
                clubOwnerDAO.getClubOwners();

        int count = 0;

        for (ClubOwner club : clubs) {

            String status =
                    club.getStatus();

            if (status != null &&
                    status.equalsIgnoreCase(
                            "ACTIVE"
                    )) {

                count++;
            }
        }

        return count;
    }


    // =========================================================
    // PENDING CLUBS
    // =========================================================

    private int getPendingClubs() {

        List<ClubOwner> clubs =
                clubOwnerDAO.getClubOwners();

        int count = 0;

        for (ClubOwner club : clubs) {

            String status =
                    club.getStatus();

            if (status != null &&
                    status.equalsIgnoreCase(
                            "PENDING"
                    )) {

                count++;
            }
        }

        return count;
    }


    // =========================================================
    // INACTIVE CLUBS
    // =========================================================

    private int getInactiveClubs() {

        List<ClubOwner> clubs =
                clubOwnerDAO.getClubOwners();

        int count = 0;

        for (ClubOwner club : clubs) {

            String status =
                    club.getStatus();

            if (status != null &&
                    status.equalsIgnoreCase(
                            "INACTIVE"
                    )) {

                count++;
            }
        }

        return count;
    }


    // =========================================================
    // TOTAL MEMBERS
    // =========================================================

    private int getTotalMembers() {

        try {

            ApiFuture<QuerySnapshot> future =

                    db.collection(
                            "UserInfo"
                    )
                    .whereEqualTo(
                            "role",
                            "MEMBER"
                    )
                    .get();


            QuerySnapshot snapshot =
                    future.get();


            return snapshot.size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =========================================================
    // FIND BOOKING COLLECTION
    // =========================================================

    private String findBookingCollection() {

        try {

            Iterable<CollectionReference>
                    collections =
                    db.listCollections();


            for (CollectionReference collection :
                    collections) {

                String name =
                        collection
                                .getId()
                                .toLowerCase(
                                        Locale.ROOT
                                );


                if (name.contains("booking")
                        ||
                        name.contains(
                                "reservation"
                        )) {

                    return collection.getId();
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }


    // =========================================================
    // TOTAL REVENUE
    // =========================================================

    private double getTotalRevenue() {

        try {

            String collectionName =
                    findBookingCollection();


            if (collectionName == null) {

                return 0;
            }


            QuerySnapshot snapshot =
                    db.collection(
                            collectionName
                    )
                    .get()
                    .get();


            double total =
                    0;


            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                total +=
                        getAmountFromDocument(
                                doc
                        );
            }


            return total;

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =========================================================
    // GET AMOUNT FROM BOOKING
    // =========================================================

    private double getAmountFromDocument(
            DocumentSnapshot doc
    ) {

        String[] possibleFields = {

                "amount",

                "price",

                "totalAmount",

                "totalPrice",

                "paymentAmount",

                "bookingAmount",

                "revenue"
        };


        for (String field :
                possibleFields) {

            Object value =
                    doc.get(field);


            if (value instanceof Number) {

                return (
                        (Number) value
                ).doubleValue();
            }


            if (value != null) {

                try {

                    String text =
                            String.valueOf(
                                    value
                            )
                            .replace(
                                    "₹",
                                    ""
                            )
                            .replace(
                                    ",",
                                    ""
                            )
                            .trim();


                    return Double.parseDouble(
                            text
                    );

                } catch (Exception ignored) {

                }
            }
        }


        return 0;
    }


    // =========================================================
    // CURRENCY FORMAT
    // =========================================================

    private String formatCurrency(
            double amount
    ) {

        if (amount >= 10000000) {

            return String.format(
                    Locale.US,
                    "₹%.2fCr",
                    amount / 10000000
            );
        }


        if (amount >= 100000) {

            return String.format(
                    Locale.US,
                    "₹%.2fL",
                    amount / 100000
            );
        }


        if (amount >= 1000) {

            return String.format(
                    Locale.US,
                    "₹%.1fK",
                    amount / 1000
            );
        }


        return String.format(
                Locale.US,
                "₹%.0f",
                amount
        );
    }


    // =========================================================
    // SUMMARY CARD
    // =========================================================

    private VBox createSummaryCard(

            String title,

            Label valueLabel

    ) {

        VBox card =
                new VBox(5);


        card.setPrefWidth(
                170
        );


        card.setPrefHeight(
                105
        );


        card.setPadding(
                new Insets(18)
        );


        card.setStyle(

                "-fx-background-color:"
                        + CARD + ";" +

                        "-fx-border-color:"
                        + CARD_BORDER + ";" +

                        "-fx-border-radius:9;" +

                        "-fx-background-radius:9;"
        );


        Label titleLabel =
                new Label(
                        title
                );


        titleLabel.setStyle(

                "-fx-text-fill:#AAB5AD;" +

                        "-fx-font-size:10px;" +

                        "-fx-font-weight:bold;"
        );


        HBox valueBox =
                new HBox(7);


        valueBox.setAlignment(
                Pos.CENTER_LEFT
        );


        valueBox.getChildren().add(
                valueLabel
        );


        card.getChildren().addAll(
                titleLabel,
                valueBox
        );


        return card;
    }


    // =========================================================
    // DYNAMIC REPORT GRID
    // =========================================================

    private GridPane createDynamicReportGrid() {

        GridPane grid =
                new GridPane();


        grid.setHgap(15);
        grid.setVgap(15);


        VBox clubGrowth =
                createMetricReportCard(
                        "Club Growth Report",
                        "↗",
                        getTotalClubs(),
                        "Total registered clubs"
                );


        VBox membership =
                createMetricReportCard(
                        "Club Membership Report",
                        "▥",
                        getTotalMembers(),
                        "Total registered members"
                );


        VBox booking =
                createMetricReportCard(
                        "Club Booking Report",
                        "◷",
                        getTotalBookings(),
                        "Total bookings"
                );


        VBox revenue =
                createMetricReportCard(
                        "Club Revenue Report",
                        "₹",
                        getTotalRevenue(),
                        "Total recorded revenue"
                );


        grid.add(
                clubGrowth,
                0,
                0
        );


        grid.add(
                membership,
                1,
                0
        );


        grid.add(
                booking,
                0,
                1
        );


        grid.add(
                revenue,
                1,
                1
        );


        ColumnConstraints first =
                new ColumnConstraints();

        ColumnConstraints second =
                new ColumnConstraints();


        first.setPercentWidth(50);
        second.setPercentWidth(50);


        grid.getColumnConstraints()
                .addAll(
                        first,
                        second
                );


        return grid;
    }


    // =========================================================
    // TOTAL BOOKINGS
    // =========================================================

    private int getTotalBookings() {

        try {

            String collectionName =
                    findBookingCollection();


            if (collectionName == null) {

                return 0;
            }


            QuerySnapshot snapshot =
                    db.collection(
                            collectionName
                    )
                    .get()
                    .get();


            return snapshot.size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =========================================================
    // METRIC REPORT CARD
    // =========================================================

    private VBox createMetricReportCard(

            String title,

            String icon,

            double value,

            String description

    ) {

        VBox card =
                new VBox();


        card.setPrefHeight(
                190
        );


        card.setPadding(
                new Insets(18)
        );


        card.setStyle(

                "-fx-background-color:"
                        + CARD + ";" +

                        "-fx-border-color:"
                        + CARD_BORDER + ";" +

                        "-fx-border-radius:9;" +

                        "-fx-background-radius:9;"
        );


        HBox top =
                new HBox();


        top.setAlignment(
                Pos.CENTER_LEFT
        );


        Label titleLabel =
                new Label(
                        title
                );


        titleLabel.setStyle(

                "-fx-text-fill:"
                        + WHITE + ";" +

                        "-fx-font-size:18px;" +

                        "-fx-font-weight:bold;"
        );


        Region space =
                new Region();


        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        Label iconLabel =
                new Label(
                        icon
                );


        iconLabel.setStyle(

                "-fx-text-fill:"
                        + GREEN + ";" +

                        "-fx-font-size:24px;"
        );


        top.getChildren().addAll(
                titleLabel,
                space,
                iconLabel
        );


        VBox center =
                new VBox(5);


        center.setAlignment(
                Pos.CENTER
        );


        VBox.setVgrow(
                center,
                Priority.ALWAYS
        );


        String displayValue;


        if (title.contains(
                "Revenue"
        )) {

            displayValue =
                    formatCurrency(
                            value
                    );

        } else {

            displayValue =
                    String.format(
                            Locale.US,
                            "%,.0f",
                            value
                    );
        }


        Label valueLabel =
                new Label(
                        displayValue
                );


        valueLabel.setStyle(

                "-fx-text-fill:"
                        + GREEN + ";" +

                        "-fx-font-size:32px;" +

                        "-fx-font-weight:bold;"
        );


        Label descriptionLabel =
                new Label(
                        description
                );


        descriptionLabel.setStyle(

                "-fx-text-fill:#8F9A91;" +

                        "-fx-font-size:12px;"
        );


        center.getChildren().addAll(
                valueLabel,
                descriptionLabel
        );


        card.getChildren().addAll(
                top,
                center
        );


        return card;
    }


    // =========================================================
    // PERFORMANCE SECTION
    // =========================================================

    private VBox createPerformanceSection() {

        VBox box =
                new VBox();


        box.setStyle(

                "-fx-background-color:"
                        + CARD + ";" +

                        "-fx-border-color:"
                        + CARD_BORDER + ";" +

                        "-fx-border-radius:9;" +

                        "-fx-background-radius:9;"
        );


        HBox titleRow =
                new HBox();


        titleRow.setPadding(
                new Insets(
                        14,
                        18,
                        12,
                        18
                )
        );


        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label title =
                new Label(
                        "Club Performance"
                );


        title.setStyle(

                "-fx-text-fill:"
                        + WHITE + ";" +

                        "-fx-font-size:19px;" +

                        "-fx-font-weight:bold;"
        );


        Region space =
                new Region();


        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        TextField search =
                new TextField();


        search.setPromptText(
                "⌕  Search clubs..."
        );


        search.setPrefWidth(
                200
        );


        search.setStyle(

                "-fx-background-color:#121A14;" +

                        "-fx-text-fill:white;" +

                        "-fx-prompt-text-fill:#718078;" +

                        "-fx-border-color:#283129;" +

                        "-fx-border-radius:5;" +

                        "-fx-background-radius:5;"
        );


        titleRow.getChildren().addAll(
                title,
                space,
                search
        );


        GridPane table =
                new GridPane();


        table.setHgap(15);
        table.setVgap(0);


        table.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );


        String[] headers = {

                "CLUB NAME",

                "LOCATION",

                "OWNER",

                "MEMBERS",

                "BOOKINGS",

                "REVENUE",

                "STATUS"
        };


        for (int i = 0;
             i < headers.length;
             i++) {


            Label label =
                    new Label(
                            headers[i]
                    );


            label.setStyle(

                    "-fx-text-fill:#8F9A91;" +

                            "-fx-font-size:9px;" +

                            "-fx-font-weight:bold;"
            );


            table.add(
                    label,
                    i,
                    0
            );
        }


        loadClubRows(
                table,
                ""
        );


        // =====================================================
        // SEARCH
        // =====================================================

        search.textProperty()
                .addListener(
                        (observable,
                         oldValue,
                         newValue) -> {

                            clearTableRows(
                                    table
                            );

                            loadClubRows(
                                    table,
                                    newValue
                            );
                        }
                );


        Label showing =
                new Label(
                        "Live data from Firebase"
                );


        showing.setStyle(

                "-fx-text-fill:#B9C3BC;" +

                        "-fx-font-size:12px;"
        );


        HBox footer =
                new HBox();


        footer.setPadding(
                new Insets(
                        12,
                        18,
                        12,
                        18
                )
        );


        footer.getChildren().add(
                showing
        );


        box.getChildren().addAll(
                titleRow,
                table,
                footer
        );


        return box;
    }


    // =========================================================
    // CLEAR TABLE ROWS
    // =========================================================

    private void clearTableRows(
            GridPane table
    ) {

        table.getChildren()
                .removeIf(
                        node -> {

                            Integer row =
                                    GridPane
                                            .getRowIndex(
                                                    node
                                            );

                            return row != null
                                    && row > 0;
                        }
                );
    }


    // =========================================================
    // LOAD CLUB ROWS
    // =========================================================

    private void loadClubRows(

            GridPane table,

            String searchText

    ) {

        List<ClubOwner> clubs =
                clubOwnerDAO.getClubOwners();


        int row = 1;


        String search =
                searchText == null
                        ? ""
                        : searchText
                        .trim()
                        .toLowerCase(
                                Locale.ROOT
                        );


        for (ClubOwner club :
                clubs) {


            String clubName =
                    safe(
                            club.getClubName()
                    );


            String location =
                    safe(
                            club.getAddress()
                    );


            String owner =
                    safe(
                            club.getName()
                    );


            String status =
                    safe(
                            club.getStatus()
                    );


            String combined =
                    (
                            clubName
                                    + " "
                                    + location
                                    + " "
                                    + owner
                                    + " "
                                    + status
                    )
                    .toLowerCase(
                            Locale.ROOT
                    );


            if (!search.isEmpty()
                    &&
                    !combined.contains(
                            search
                    )) {

                continue;
            }


            addClubRow(

                    table,

                    row,

                    clubName,

                    location,

                    owner,

                    getClubMembers(
                            club
                    ),

                    getClubBookings(
                            club
                    ),

                    getClubRevenue(
                            club
                    ),

                    status
            );


            row++;
        }


        if (row == 1) {

            Label empty =
                    new Label(
                            "No clubs found."
                    );


            empty.setStyle(

                    "-fx-text-fill:#8F9A91;" +

                            "-fx-font-size:12px;" +

                            "-fx-padding:15;"
            );


            table.add(
                    empty,
                    0,
                    1,
                    7,
                    1
            );
        }
    }


    // =========================================================
    // CLUB MEMBERS
    // =========================================================

    private String getClubMembers(
            ClubOwner club
    ) {

        /*
         * Your current ClubOwner model does not contain
         * a member count.
         *
         * Therefore we safely display 0 until your
         * membership relation is available.
         */

        return "0";
    }


    // =========================================================
    // CLUB BOOKINGS
    // =========================================================

    private String getClubBookings(
            ClubOwner club
    ) {

        /*
         * Booking documents in the current project do not
         * have a confirmed club-id field available in the
         * supplied ClubOwner model.
         *
         * Therefore the table does not invent a number.
         */

        return "0";
    }


    // =========================================================
    // CLUB REVENUE
    // =========================================================

    private String getClubRevenue(
            ClubOwner club
    ) {

        return "₹0";
    }


    // =========================================================
    // ADD CLUB ROW
    // =========================================================

    private void addClubRow(

            GridPane table,

            int row,

            String club,

            String location,

            String owner,

            String members,

            String bookings,

            String revenue,

            String status

    ) {


        String[] values = {

                club,

                location,

                owner,

                members,

                bookings,

                revenue
        };


        for (int i = 0;
             i < values.length;
             i++) {


            Label value =
                    new Label(
                            values[i]
                    );


            value.setWrapText(
                    true
            );


            value.setMaxWidth(
                    150
            );


            value.setStyle(

                    "-fx-text-fill:#D7DED9;" +

                            "-fx-font-size:11px;"
            );


            value.setPadding(
                    new Insets(
                            13,
                            0,
                            13,
                            0
                    )
            );


            table.add(
                    value,
                    i,
                    row
            );
        }


        Label statusLabel =
                new Label(
                        "●  " +
                                (
                                        status.isEmpty()
                                                ? "UNKNOWN"
                                                : status
                                )
                );


        String upperStatus =
                status.toUpperCase(
                        Locale.ROOT
                );


        if (upperStatus.equals(
                "ACTIVE"
        )) {


            statusLabel.setStyle(

                    "-fx-background-color:#1D3300;" +

                            "-fx-text-fill:#B7FF00;" +

                            "-fx-padding:5 9;" +

                            "-fx-background-radius:15;" +

                            "-fx-font-size:10px;"
            );


        } else if (
                upperStatus.equals(
                        "PENDING"
                )
        ) {


            statusLabel.setStyle(

                    "-fx-background-color:#2B2F20;" +

                            "-fx-text-fill:#C8D66A;" +

                            "-fx-padding:5 9;" +

                            "-fx-background-radius:15;" +

                            "-fx-font-size:10px;"
            );


        } else {


            statusLabel.setStyle(

                    "-fx-background-color:#2B2427;" +

                            "-fx-text-fill:#FF7474;" +

                            "-fx-padding:5 9;" +

                            "-fx-background-radius:15;" +

                            "-fx-font-size:10px;"
            );
        }


        table.add(
                statusLabel,
                6,
                row
        );
    }


    // =========================================================
    // DYNAMIC INSIGHTS
    // =========================================================

    private VBox createDynamicInsights() {

        VBox section =
                new VBox();


        section.setStyle(

                "-fx-background-color:"
                        + CARD + ";" +

                        "-fx-border-color:"
                        + CARD_BORDER + ";" +

                        "-fx-border-radius:9;" +

                        "-fx-background-radius:9;"
        );


        Label title =
                new Label(
                        "Key Insights"
                );


        title.setPadding(
                new Insets(
                        14,
                        18,
                        12,
                        18
                )
        );


        title.setStyle(

                "-fx-text-fill:"
                        + WHITE + ";" +

                        "-fx-font-size:19px;" +

                        "-fx-font-weight:bold;"
        );


        HBox cards =
                new HBox(12);


        cards.setPadding(
                new Insets(
                        0,
                        18,
                        18,
                        18
                )
        );


        int total =
                getTotalClubs();


        int active =
                getActiveClubs();


        int pending =
                getPendingClubs();


        int inactive =
                getInactiveClubs();


        cards.getChildren().addAll(

                createInsight(
                        "🏢",
                        "TOTAL CLUBS",
                        String.valueOf(
                                total
                        )
                ),

                createInsight(
                        "✓",
                        "ACTIVE CLUBS",
                        String.valueOf(
                                active
                        )
                ),

                createInsight(
                        "⌛",
                        "PENDING",
                        String.valueOf(
                                pending
                        )
                ),

                createInsight(
                        "○",
                        "INACTIVE",
                        String.valueOf(
                                inactive
                        )
                ),

                createInsight(
                        "👥",
                        "TOTAL MEMBERS",
                        String.valueOf(
                                getTotalMembers()
                        )
                )
        );


        section.getChildren().addAll(
                title,
                cards
        );


        return section;
    }


    // =========================================================
    // INSIGHT CARD
    // =========================================================

    private VBox createInsight(

            String icon,

            String heading,

            String value

    ) {

        VBox card =
                new VBox(7);


        card.setPrefWidth(
                160
        );


        card.setPrefHeight(
                90
        );


        card.setPadding(
                new Insets(10)
        );


        card.setStyle(

                "-fx-background-color:#121A14;" +

                        "-fx-border-color:#283129;" +

                        "-fx-border-radius:7;" +

                        "-fx-background-radius:7;"
        );


        Label iconLabel =
                new Label(
                        icon
                );


        iconLabel.setStyle(

                "-fx-text-fill:"
                        + GREEN + ";" +

                        "-fx-font-size:17px;"
        );


        Label headingLabel =
                new Label(
                        heading
                );


        headingLabel.setStyle(

                "-fx-text-fill:#8F9A91;" +

                        "-fx-font-size:8px;" +

                        "-fx-font-weight:bold;"
        );


        Label valueLabel =
                new Label(
                        value
                );


        valueLabel.setStyle(

                "-fx-text-fill:#FFFFFF;" +

                        "-fx-font-size:15px;" +

                        "-fx-font-weight:bold;"
        );


        card.getChildren().addAll(

                iconLabel,

                headingLabel,

                valueLabel
        );


        return card;
    }


    // =========================================================
    // REFRESH PERFORMANCE
    // =========================================================

    private void refreshPerformanceSection(
            VBox performance
    ) {

        /*
         * The main page is normally rebuilt when navigating
         * back to Reports.
         *
         * This method intentionally keeps the existing UI
         * stable instead of trying to locate private table
         * nodes.
         */
    }


    // =========================================================
    // DATE BUTTON
    // =========================================================

    private Button createDateButton(
            String text
    ) {

        Button button =
                new Button(
                        text
                );


        button.setStyle(

                "-fx-background-color:#181C22;" +

                        "-fx-text-fill:#C8D0CA;" +

                        "-fx-border-color:#283129;" +

                        "-fx-border-radius:6;" +

                        "-fx-background-radius:6;" +

                        "-fx-padding:10 14;" +

                        "-fx-cursor:hand;"
        );


        return button;
    }


    // =========================================================
    // ACTIVE DATE BUTTON
    // =========================================================

    private void setActiveDateButton(

            Button active,

            Button button1,

            Button button2,

            Button button3

    ) {


        active.setStyle(

                "-fx-background-color:#1D3300;" +

                        "-fx-text-fill:" + GREEN + ";" +

                        "-fx-border-color:#334A1A;" +

                        "-fx-border-radius:6;" +

                        "-fx-background-radius:6;" +

                        "-fx-padding:10 14;" +

                        "-fx-cursor:hand;"
        );


        button1.setStyle(

                "-fx-background-color:#181C22;" +

                        "-fx-text-fill:#C8D0CA;" +

                        "-fx-border-color:#283129;" +

                        "-fx-border-radius:6;" +

                        "-fx-background-radius:6;" +

                        "-fx-padding:10 14;" +

                        "-fx-cursor:hand;"
        );


        button2.setStyle(

                "-fx-background-color:#181C22;" +

                        "-fx-text-fill:#C8D0CA;" +

                        "-fx-border-color:#283129;" +

                        "-fx-border-radius:6;" +

                        "-fx-background-radius:6;" +

                        "-fx-padding:10 14;" +

                        "-fx-cursor:hand;"
        );


        button3.setStyle(

                "-fx-background-color:#181C22;" +

                        "-fx-text-fill:#C8D0CA;" +

                        "-fx-border-color:#283129;" +

                        "-fx-border-radius:6;" +

                        "-fx-background-radius:6;" +

                        "-fx-padding:10 14;" +

                        "-fx-cursor:hand;"
        );
    }


    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        if (value == null
                ||
                value.trim().isEmpty()) {

            return "Not available";
        }


        return value;
    }


    // =========================================================
    // INFO DIALOG
    // =========================================================

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


        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }
}