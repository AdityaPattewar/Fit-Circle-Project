package com.flexforce.view.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.flexforce.Services.AdminDashboardService;
import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.admin.AdminDashboardStats;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AdminDashboard extends Application {

       private static Stage mainStage;

    // =====================================================
    // COLORS
    // =====================================================

    private final String BACKGROUND = "#080B09";
    private final String SIDEBAR = "#0D120F";

    private final String SIDEBAR_CARD = "#141C17";
    private final String SIDEBAR_BORDER = "#283129";

    private final String CARD = "#101511";
    private final String CARD_BORDER = "#283129";

    private final String GREEN = "#B7FF00";

    private final String TEXT_GRAY = "#8F9A91";

    // =====================================================
    // FIRESTORE
    // =====================================================

    private Firestore db;

    // =====================================================
    // START
    // =====================================================

    @Override
    public void start(Stage stage) {

        mainStage = stage;

        initializeFirestore();

        stage.setTitle("FitCircle - Admin Dashboard");

        stage.setScene(
                mainScene(null)
        );

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.show();
    }

    // =====================================================
    // START WITH CALLBACK
    // =====================================================

    public void start(
            Stage stage,
            Runnable callbackAction) {

        mainStage = stage;

        initializeFirestore();

        stage.setTitle("FitCircle - Admin Dashboard");

        stage.setScene(
                mainScene(callbackAction)
        );

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.show();
    }

    // =====================================================
    // FIREBASE INITIALIZATION
    // =====================================================

   /*  private void initializeFirestore() {

        try {

            db = FirebaseConfig.getFirebaseConfig();

            if (db == null) {
                System.out.println(
                        ">>> Admin Dashboard Firebase = NULL"
                );
                return;
            }

            System.out.println(
                    ">>> Admin Dashboard Firebase Connected"
            );

        } catch (Exception e) {

            System.out.println(
                    ">>> Admin Dashboard Firebase FAILED"
            );
            e.printStackTrace();

            db = null;
        }
    }*/

       private void initializeFirestore() {

    try {

        System.out.println(
                ">>> Initializing Admin Dashboard Firebase..."
        );

        /*
         * IMPORTANT:
         * AdminDashboardService is already able to initialize
         * Firebase because the dashboard statistics are being
         * loaded successfully.
         *
         * So we first force that initialization.
         */
        AdminDashboardService dashboardService =
                new AdminDashboardService();

        dashboardService.getDashboardStats();

        /*
         * Firebase is now initialized.
         *
         * Get the SAME Firestore instance directly from
         * FirestoreClient.
         */
        db = FirestoreClient.getFirestore();

        if (db != null) {

            System.out.println(
                    ">>> Admin Dashboard Firebase Connected"
            );

        } else {

            System.out.println(
                    ">>> Admin Dashboard Firebase = NULL"
            );
        }

    } catch (Exception e) {

        System.out.println(
                ">>> Admin Dashboard Firebase FAILED"
        );

        e.printStackTrace();

        db = null;
    }
}

    // =====================================================
    // MAIN SCENE
    // =====================================================

    public Scene mainScene(
            Runnable callbackAction) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        VBox sidebar =
                createSidebar(
                        root,
                        callbackAction
                );

        root.setLeft(sidebar);

        root.setCenter(
                createDashboard()
        );

        return new Scene(
                root,
                1550,
                800
        );
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

    private VBox createSidebar(
            BorderPane root,
            Runnable callbackAction) {

        VBox sidebar =
                new VBox(12);

        sidebar.setPrefWidth(245);

        sidebar.setPadding(
                new Insets(
                        25,
                        12,
                        20,
                        12
                )
        );

        sidebar.setStyle(
                "-fx-background-color:" +
                        SIDEBAR + ";" +
                        "-fx-border-color:#202A23;" +
                        "-fx-border-width:0 1 0 0;"
        );

        Label logo =
                new Label(
                        "FITCIRCLE"
                );

        logo.setStyle(
                "-fx-font-size:25px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-padding:0 0 0 15;"
        );

        Label tagline =
                new Label(
                        "ADMIN PORTAL"
                );

        tagline.setStyle(
                "-fx-font-size:10px;" +
                        "-fx-text-fill:#78857C;" +
                        "-fx-padding:0 0 18 15;"
        );

        VBox menu =
                new VBox(10);

        String[] menuItems = {

                "🏠   Dashboard",
                "🏢   Clubs",
                "👥   Approval",
                "📊   Reports",
                "🖼   Carousel",
                "🚪   Logout"
        };

        for (String item : menuItems) {

            Button menuButton =
                    new Button(item);

            menuButton.setMaxWidth(
                    Double.MAX_VALUE
            );

            menuButton.setPrefHeight(46);

            menuButton.setAlignment(
                    Pos.CENTER_LEFT
            );

            menuButton.setPadding(
                    new Insets(
                            0,
                            12,
                            0,
                            15
                    )
            );

            setMenuButtonNormalStyle(
                    menuButton
            );

            menuButton.setOnMouseEntered(e -> {

                menuButton.setStyle(
                        "-fx-background-color:#1D3300;" +
                                "-fx-text-fill:" +
                                GREEN + ";" +
                                "-fx-font-size:14px;" +
                                "-fx-font-weight:bold;" +
                                "-fx-background-radius:11px;" +
                                "-fx-border-color:" +
                                GREEN + ";" +
                                "-fx-border-radius:11px;" +
                                "-fx-cursor:hand;"
                );
            });

            menuButton.setOnMouseExited(e -> {

                setMenuButtonNormalStyle(
                        menuButton
                );
            });

            menuButton.setOnAction(e -> {

                String pageName =
                        item
                                .replace("🏠   ", "")
                                .replace("🏢   ", "")
                                .replace("👥   ", "")
                                .replace("📊   ", "")
                                .replace("🖼   ", "")
                                .replace("🚪   ", "");

                if (pageName.equals("Dashboard")) {

                    root.setCenter(
                            createDashboard()
                    );
                }

                else if (pageName.equals("Clubs")) {

                    Adminclubmanage clubPage =
                            new Adminclubmanage();

                    root.setCenter(
                            clubPage.createClubPage()
                    );
                }

                else if (pageName.equals("Approval")) {

                    AdminApproval approvalPage =
                            new AdminApproval();

                    root.setCenter(
                            approvalPage.createClubApprovalView()
                    );
                }

                else if (pageName.equals("Reports")) {

                    Admin_Report reportPage =
                            new Admin_Report();

                    root.setCenter(
                            reportPage.createClubReportPage()
                    );
                }

                else if (pageName.equals("Carousel")) {

                    AdminCarouselManage carouselManage =
                            new AdminCarouselManage();

                    root.setCenter(
                            carouselManage.createContent(mainStage)
                    );
                }

                else if (pageName.equals("Logout")) {

                    if (callbackAction != null) {

                        callbackAction.run();
                    }
                }
            });

            menu.getChildren().add(
                    menuButton
            );
        }

        sidebar.getChildren().addAll(
                logo,
                tagline,
                menu
        );

        VBox.setVgrow(
                menu,
                Priority.ALWAYS
        );

        return sidebar;
    }

    // =====================================================
    // SIDEBAR NORMAL STYLE
    // =====================================================

    private void setMenuButtonNormalStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color:" +
                        SIDEBAR_CARD + ";" +
                        "-fx-text-fill:#C5CEC7;" +
                        "-fx-font-size:14px;" +
                        "-fx-background-radius:11px;" +
                        "-fx-border-color:" +
                        SIDEBAR_BORDER + ";" +
                        "-fx-border-radius:11px;" +
                        "-fx-cursor:hand;"
        );
    }

    // =====================================================
    // DASHBOARD
    // =====================================================

    private ScrollPane createDashboard() {

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(
                        35,
                        30,
                        30,
                        45
                )
        );

        main.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        // =================================================
        // HEADER
        // =================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label heading =
                new Label(
                        "Admin Dashboard"
                );

        heading.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-size:34px;" +
                        "-fx-font-weight:bold;"
        );

        Region headerSpace =
                new Region();

        HBox.setHgrow(
                headerSpace,
                Priority.ALWAYS
        );

        Label date =
                new Label(
                        "▦ Today - " +
                                LocalDate.now()
                                        .format(
                                                DateTimeFormatter.ofPattern(
                                                        "dd MMM yyyy"
                                                )
                                        )
                );

        date.setStyle(
                "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;"
        );

        Label admin =
                new Label(
                        "Admin"
                );

        admin.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;"
        );

        HBox headerRight =
                new HBox(
                        15,
                        date,
                        admin
                );

        headerRight.setAlignment(
                Pos.CENTER_RIGHT
        );

        header.getChildren().addAll(
                heading,
                headerSpace,
                headerRight
        );

        // =================================================
        // STAT CARDS - LOAD THROUGH ADMIN DASHBOARD SERVICE
        // =================================================

        GridPane cards =
                new GridPane();

        cards.setHgap(22);

        AdminDashboardStats stats;

        try {

            AdminDashboardService dashboardService =
                    new AdminDashboardService();

            stats = dashboardService.getDashboardStats();

            System.out.println(
                    ">>> Total Clubs = " +
                    stats.getTotalClubs()
            );

            System.out.println(
                    ">>> Active Users = " +
                    stats.getActiveUsers()
            );

            System.out.println(
                    ">>> Total Bookings = " +
                    stats.getTotalBookings()
            );

            System.out.println(
                    ">>> Revenue = " +
                    stats.getTotalRevenue()
            );

        } catch (Exception e) {

            System.out.println(
                    ">>> Dashboard statistics could not be loaded"
            );
            e.printStackTrace();

            stats = new AdminDashboardStats(
                    0,
                    0,
                    0,
                    0
            );
        }

        VBox card1 =
                createStatCard(
                        "●",
                        "Total Clubs",
                        String.valueOf(
                                stats.getTotalClubs()
                        )
                );

        VBox card2 =
                createStatCard(
                        "♟",
                        "Active Users",
                        String.valueOf(
                                stats.getActiveUsers()
                        )
                );

        VBox card3 =
                createStatCard(
                        "▦",
                        "Total Bookings",
                        String.valueOf(
                                stats.getTotalBookings()
                        )
                );

        VBox card4 =
                createStatCard(
                        "₹",
                        "Revenue",
                        formatRevenue(
                                stats.getTotalRevenue()
                        )
                );

        cards.add(
                card1,
                0,
                0
        );

        cards.add(
                card2,
                1,
                0
        );

        cards.add(
                card3,
                2,
                0
        );

        cards.add(
                card4,
                3,
                0
        );

        // =================================================
        // ANALYTICS
        // =================================================

        VBox analytics =
                new VBox(15);

        analytics.setPadding(
                new Insets(25)
        );

        analytics.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:12;" +
                        "-fx-background-radius:12;"
        );

        Label analyticsTitle =
                new Label(
                        "Bookings Analytics"
                );

        analyticsTitle.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-size:17px;" +
                        "-fx-font-weight:bold;"
        );

        HBox chart =
                new HBox(28);

        chart.setAlignment(
                Pos.BOTTOM_CENTER
        );

        chart.setPadding(
                new Insets(
                        10,
                        20,
                        15,
                        20
                )
        );

        chart.setPrefHeight(170);

        chart.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-background-radius:8;"
        );

        addAnalyticsBars(chart);

        analytics.getChildren().addAll(
                analyticsTitle,
                chart
        );

        // =================================================
        // RECENT ACTIVITY
        // =================================================

        VBox activity =
                new VBox(15);

        activity.setPadding(
                new Insets(25)
        );

        activity.setPrefWidth(350);

        activity.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:12;" +
                        "-fx-background-radius:12;"
        );

        Label activityTitle =
                new Label(
                        "Recent Activity"
                );

        activityTitle.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-size:17px;" +
                        "-fx-font-weight:bold;"
        );

        VBox activityList =
                new VBox(10);

        loadRecentActivities(
                activityList
        );

        ScrollPane activityScroll =
                new ScrollPane(
                        activityList
                );

        activityScroll.setFitToWidth(
                true
        );

        activityScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        activityScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        activityScroll.setPrefHeight(
                180
        );

        activityScroll.setStyle(
                "-fx-background:" +
                        BACKGROUND + ";" +
                        "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        activity.getChildren().addAll(
                activityTitle,
                activityScroll
        );

        // =================================================
        // MIDDLE
        // =================================================

        HBox middle =
                new HBox(20);

        HBox.setHgrow(
                analytics,
                Priority.ALWAYS
        );

        middle.getChildren().addAll(
                analytics,
                activity
        );

        // =================================================
        // MANAGEMENT
        // =================================================

        HBox management =
                new HBox(20);

        VBox clubManagement =
                createManagementBox(
                        "Club Management",
                        "Add New Club",
                        "Edit Club",
                        "Delete Club"
                );

        VBox userManagement =
                createManagementBox(
                        "User Management",
                        "View Users",
                        "Send Notification",
                        "Block User"
                );

        VBox bookingManagement =
                createManagementBox(
                        "Booking Management",
                        "View Bookings",
                        "Cancel Booking",
                        "Issue Refund"
                );

        management.getChildren().addAll(
                clubManagement,
                userManagement,
                bookingManagement
        );

        // =================================================
        // CONTENT
        // =================================================

        main.getChildren().addAll(
                header,
                cards,
                middle,
                management
        );

        // =================================================
        // MAIN SCROLL
        // =================================================

        ScrollPane mainScroll =
                new ScrollPane(main);

        mainScroll.setFitToWidth(
                true
        );

        mainScroll.setStyle(
                "-fx-background:" +
                        BACKGROUND + ";" +
                        "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        return mainScroll;
    }

    // =====================================================
    // ANALYTICS BARS
    // =====================================================

    private void addAnalyticsBars(
            HBox chart) {

        String bookingCollection =
                findBookingCollection();

        if (bookingCollection == null) {

            chart.getChildren().addAll(

                    createBar(0, "Jan"),
                    createBar(0, "Feb"),
                    createBar(0, "Mar"),
                    createBar(0, "Apr"),
                    createBar(0, "May"),
                    createBar(0, "Jun")
            );

            return;
        }

        int[] monthlyCounts =
                new int[6];

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            bookingCollection
                    )
                    .get()
                    .get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Object dateValue =
                        doc.get("date");

                if (dateValue == null) {
                    continue;
                }

                String date =
                        String.valueOf(
                                dateValue
                        );

                int month =
                        getMonthFromDate(
                                date
                        );

                if (month >= 1 &&
                        month <= 6) {

                    monthlyCounts[
                            month - 1
                    ]++;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        int max = 1;

        for (int count :
                monthlyCounts) {

            if (count > max) {
                max = count;
            }
        }

        String[] months = {
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun"
        };

        for (int i = 0;
             i < months.length;
             i++) {

            double height =
                    monthlyCounts[i] == 0
                            ? 0
                            : (130.0 *
                            monthlyCounts[i] /
                            max);

            chart.getChildren().add(
                    createBar(
                            height,
                            months[i]
                    )
            );
        }
    }

    // =====================================================
    // MONTH FROM DATE
    // =====================================================

    private int getMonthFromDate(
            String date) {

        try {

            if (date.length() >= 7) {

                String month =
                        date.substring(
                                5,
                                7
                        );

                return Integer.parseInt(
                        month
                );
            }

        } catch (Exception e) {

            // Ignore invalid date
        }

        return 0;
    }

    // =====================================================
    // STAT CARD
    // =====================================================

    private VBox createStatCard(
            String icon,
            String name,
            String value) {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(25)
        );

        card.setPrefWidth(250);

        card.setPrefHeight(170);

        card.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:12;" +
                        "-fx-background-radius:12;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-size:25px;"
        );

        Label nameLabel =
                new Label(name);

        nameLabel.setStyle(
                "-fx-text-fill:" +
                        TEXT_GRAY + ";" +
                        "-fx-font-size:14px;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-size:27px;" +
                        "-fx-font-weight:bold;"
        );

        card.getChildren().addAll(
                iconLabel,
                nameLabel,
                valueLabel
        );

        return card;
    }

    // =====================================================
    // BAR
    // =====================================================

    private VBox createBar(
            double height,
            String month) {

        Rectangle bar =
                new Rectangle(
                        45,
                        height
                );

        bar.setFill(
                Color.web(GREEN)
        );

        Label monthLabel =
                new Label(month);

        monthLabel.setStyle(
                "-fx-text-fill:#78857C;" +
                        "-fx-font-size:12px;"
        );

        VBox box =
                new VBox(7);

        box.setAlignment(
                Pos.BOTTOM_CENTER
        );

        box.getChildren().addAll(
                bar,
                monthLabel
        );

        return box;
    }

    // =====================================================
    // LOAD RECENT ACTIVITIES
    // =====================================================

   /*  private void loadRecentActivities(
            VBox activityList) {

        if (db == null) {

            activityList.getChildren().add(
                    createActivity(
                            "Firebase",
                            "Not connected",
                            ""
                    )
            );

            return;
        }

       try {
        ApiFuture<QuerySnapshot> future =
                db.collection("ClubOwnerActivities").get();

        QuerySnapshot snapshot = future.get();

        List<QueryDocumentSnapshot> documents =
                snapshot.getDocuments();

        int count = 0;

        for (QueryDocumentSnapshot doc : documents) {

                count++;

                String activityName = doc.getString("activityName");
                String activityType = doc.getString("activityType");
                String difficulty = doc.getString("difficulty");
                String date = doc.getString("date");

                if (activityName == null) {
                activityName = "Activity";
                }

                if (activityType == null) {
                activityType = "";
                }

                if (difficulty == null) {
                difficulty = "";
                }

                if (date == null) {
                date = "";
                }

                activityList.getChildren().add(
                        createActivity(
                                activityName,
                                activityType + " • " + difficulty,
                                date
                        )
                );

                if (count >= 10) {
                break;
                }
        }

        } catch (Exception e) {

            e.printStackTrace();

            activityList.getChildren().add(
                    createActivity(
                            "Firebase",
                            "Unable to load activities",
                            ""
                    )
            );
        }
}*/
     // =====================================================
// LOAD RECENT ACTIVITIES
// =====================================================
private void loadRecentActivities(
        VBox activityList) {

    try {

        System.out.println(
                ">>> Loading Recent Activities..."
        );

        // -------------------------------------------------
        // GET FIRESTORE DIRECTLY FROM FIREBASE CONFIG
        // -------------------------------------------------
        Firestore firestore =
                FirebaseConfig.getFirebaseConfig();

        if (firestore == null) {

            System.out.println(
                    ">>> FirebaseConfig returned NULL"
            );

            activityList.getChildren().add(
                    createActivity(
                            "Firebase",
                            "Not connected",
                            ""
                    )
            );

            return;
        }

        System.out.println(
                ">>> FIRESTORE OBJECT AVAILABLE"
        );

        // -------------------------------------------------
        // READ CLUB OWNER ACTIVITIES
        // -------------------------------------------------
        QuerySnapshot snapshot =
                firestore
                        .collection(
                                "ClubOwnerActivities"
                        )
                        .get()
                        .get();

        System.out.println(
                ">>> ClubOwnerActivities READ SUCCESSFULLY"
        );

        List<QueryDocumentSnapshot> documents =
                snapshot.getDocuments();

        System.out.println(
                ">>> TOTAL ACTIVITIES = "
                        + documents.size()
        );

        // -------------------------------------------------
        // EMPTY COLLECTION
        // -------------------------------------------------
        if (documents.isEmpty()) {

            activityList.getChildren().add(
                    createActivity(
                            "No Activity",
                            "No records found",
                            ""
                    )
            );

            return;
        }

        // -------------------------------------------------
        // SHOW ACTIVITIES
        // -------------------------------------------------
        int count = 0;

        for (QueryDocumentSnapshot doc :
                documents) {

            if (count >= 10) {
                break;
            }

            try {

                System.out.println(
                        ">>> Reading activity document: "
                                + doc.getId()
                );

                // -------------------------------------------------
                // READ FIELDS
                // -------------------------------------------------
                Object nameObject =
                        doc.get("activityName");

                Object typeObject =
                        doc.get("activityType");

                Object difficultyObject =
                        doc.get("difficulty");

                Object dateObject =
                        doc.get("date");

                String activityName =
                        nameObject == null
                                ? "Activity"
                                : String.valueOf(
                                        nameObject
                                );

                String activityType =
                        typeObject == null
                                ? ""
                                : String.valueOf(
                                        typeObject
                                );

                String difficulty =
                        difficultyObject == null
                                ? ""
                                : String.valueOf(
                                        difficultyObject
                                );

                String date =
                        dateObject == null
                                ? ""
                                : String.valueOf(
                                        dateObject
                                );

                // -------------------------------------------------
                // CREATE DISPLAY VALUE
                // -------------------------------------------------
                String value =
                        activityType;

                if (!difficulty.isEmpty()) {

                    if (!value.isEmpty()) {
                        value += " • ";
                    }

                    value += difficulty;
                }

                // -------------------------------------------------
                // ADD ACTIVITY TO UI
                // -------------------------------------------------
                activityList.getChildren().add(
                        createActivity(
                                activityName,
                                value,
                                date
                        )
                );

                count++;

            } catch (Exception documentError) {

                System.out.println(
                        ">>> ERROR READING DOCUMENT: "
                                + doc.getId()
                );

                documentError.printStackTrace();
            }
        }

        // -------------------------------------------------
        // NO VALID ACTIVITIES
        // -------------------------------------------------
        if (count == 0) {

            activityList.getChildren().add(
                    createActivity(
                            "No Activity",
                            "No valid records found",
                            ""
                    )
            );
        }

        System.out.println(
                ">>> ACTIVITIES DISPLAYED = "
                        + count
        );

    } catch (Exception e) {

        System.out.println(
                ">>> RECENT ACTIVITY ERROR"
        );

        e.printStackTrace();

        activityList.getChildren().add(
                createActivity(
                        "Error",
                        "Unable to load activities",
                        ""
                )
        );
    }
}
    //-----------------------------------------------------------------------------------------------------------------------------------------    
    // =====================================================
    // ACTIVITY UI
    // =====================================================

    private HBox createActivity(
            String title,
            String value,
            String time) {

        HBox box =
                new HBox(10);

        box.setPadding(
                new Insets(12)
        );

        box.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-background-radius:7;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-weight:bold;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill:" +
                        GREEN + ";"
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setStyle(
                "-fx-text-fill:#718078;"
        );

        HBox.setHgrow(
                titleLabel,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                titleLabel,
                valueLabel,
                timeLabel
        );

        return box;
    }

    // =====================================================
    // MANAGEMENT BOX
    // =====================================================

    private VBox createManagementBox(
            String title,
            String button1Text,
            String button2Text,
            String button3Text) {

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(22)
        );

        box.setPrefWidth(330);

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:12;" +
                        "-fx-background-radius:12;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;"
        );

        Button button1 =
                createManagementButton(
                        button1Text
                );

        Button button2 =
                createManagementButton(
                        button2Text
                );

        Button button3 =
                createManagementButton(
                        button3Text
                );

        button1.setOnAction(
                e -> handleManagementAction(
                        button1Text
                )
        );

        button2.setOnAction(
                e -> handleManagementAction(
                        button2Text
                )
        );

        button3.setOnAction(
                e -> handleManagementAction(
                        button3Text
                )
        );

        box.getChildren().addAll(
                titleLabel,
                button1,
                button2,
                button3
        );

        return box;
    }

    // =====================================================
    // MANAGEMENT BUTTON
    // =====================================================

    private Button createManagementButton(
            String text) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color:#171E19;" +
                        "-fx-text-fill:#FFFFFF;" +
                        "-fx-border-color:#2B352E;" +
                        "-fx-border-radius:7;" +
                        "-fx-background-radius:7;" +
                        "-fx-cursor:hand;"
        );

        button.setOnMouseEntered(e -> {

            button.setStyle(
                    "-fx-background-color:" +
                            GREEN + ";" +
                            "-fx-text-fill:#050705;" +
                            "-fx-border-radius:7;" +
                            "-fx-background-radius:7;" +
                            "-fx-cursor:hand;"
            );
        });

        button.setOnMouseExited(e -> {

            button.setStyle(
                    "-fx-background-color:#171E19;" +
                            "-fx-text-fill:#FFFFFF;" +
                            "-fx-border-color:#2B352E;" +
                            "-fx-border-radius:7;" +
                            "-fx-background-radius:7;" +
                            "-fx-cursor:hand;"
            );
        });

        return button;
    }

    // =====================================================
    // MANAGEMENT ACTION HANDLER
    // =====================================================

    private void handleManagementAction(
            String action) {

        switch (action) {

            case "Add New Club":
                showAddClubDialog();
                break;

            case "Edit Club":
                showEditClubDialog();
                break;

            case "Delete Club":
                showDeleteClubDialog();
                break;

            case "View Users":
                showUsersDialog();
                break;

            case "Send Notification":
                showNotificationDialog();
                break;

            case "Block User":
                showBlockUserDialog();
                break;

            case "View Bookings":
                showBookingsDialog();
                break;

            case "Cancel Booking":
                showCancelBookingDialog();
                break;

            case "Issue Refund":
                showRefundDialog();
                break;

            default:
                break;
        }
    }

    // =====================================================
    // ADD CLUB
    // =====================================================

    /*private void showAddClubDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add New Club"
        );

        dialog.setHeaderText(
                "Create a new club"
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(10);
        grid.setVgap(12);

        grid.setPadding(
                new Insets(20)
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "Club name"
        );

        TextField email =
                new TextField();

        email.setPromptText(
                "Email"
        );

        TextField phone =
                new TextField();

        phone.setPromptText(
                "Phone"
        );

        TextField category =
                new TextField();

        category.setPromptText(
                "Category"
        );

        TextField address =
                new TextField();

        address.setPromptText(
                "Address"
        );

        TextField ownerId =
                new TextField();

        ownerId.setPromptText(
                "Owner ID"
        );

        grid.add(
                new Label("Club Name"),
                0,
                0
        );

        grid.add(
                name,
                1,
                0
        );

        grid.add(
                new Label("Email"),
                0,
                1
        );

        grid.add(
                email,
                1,
                1
        );

        grid.add(
                new Label("Phone"),
                0,
                2
        );

        grid.add(
                phone,
                1,
                2
        );

        grid.add(
                new Label("Category"),
                0,
                3
        );

        grid.add(
                category,
                1,
                3
        );

        grid.add(
                new Label("Address"),
                0,
                4
        );

        grid.add(
                address,
                1,
                4
        );

        grid.add(
                new Label("Owner ID"),
                0,
                5
        );

        grid.add(
                ownerId,
                1,
                5
        );

        dialog.getDialogPane()
                .setContent(grid);

        ButtonType saveButton =
                new ButtonType(
                        "Save",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        saveButton,
                        ButtonType.CANCEL
                );

        Optional<ButtonType> result =
                dialog.showAndWait();

        if (!result.isPresent()
                || result.get() != saveButton) {

            return;
        }

        if (name.getText()
                .trim()
                .isEmpty()) {

            showError(
                    "Validation Error",
                    "Club name is required."
            );

            return;
        }

        try {

            String documentId =
                    db.collection(
                            "ClubOwners"
                    )
                    .document()
                    .getId();

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "clubName",
                    name.getText().trim()
            );

            data.put(
                    "name",
                    name.getText().trim()
            );

            data.put(
                    "email",
                    email.getText().trim()
            );

            data.put(
                    "phone",
                    phone.getText().trim()
            );

            data.put(
                    "category",
                    category.getText().trim()
            );

            data.put(
                    "address",
                    address.getText().trim()
            );

            data.put(
                    "ownerId",
                    ownerId.getText().trim()
            );

            data.put(
                    "activeTrainers",
                    0
            );

            data.put(
                    "maxCapacity",
                    0
            );

            data.put(
                    "status",
                    "ACTIVE"
            );

            data.put(
                    "createdAt",
                    LocalDateTime.now().toString()
            );

            db.collection(
                    "ClubOwners"
            )
            .document(documentId)
            .set(data)
            .get();

            showInfo(
                    "Success",
                    "Club added successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to add club."
            );
        }
    }*/

        private void showAddClubDialog() {

    // =========================================================
    // GET FIRESTORE DIRECTLY FROM FIREBASE CONFIG
    // =========================================================

    Firestore database;

    try {

        System.out.println("======================================");
        System.out.println(">>> ADD CLUB BUTTON CLICKED");
        System.out.println(">>> Getting Firestore from FirebaseConfig...");
        System.out.println("======================================");

        database = FirebaseConfig.getFirebaseConfig();

        if (database == null) {

            System.out.println(">>> ADD CLUB: FIRESTORE IS NULL");

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        System.out.println(">>> ADD CLUB: FIRESTORE CONNECTED");

    } catch (Exception e) {

        System.out.println(">>> ADD CLUB: FIRESTORE CONNECTION FAILED");

        e.printStackTrace();

        showError(
                "Firebase Error",
                "Unable to connect to Firebase."
        );

        return;
    }


    // =========================================================
    // CREATE DIALOG
    // =========================================================

    Dialog<ButtonType> dialog =
            new Dialog<>();

    dialog.setTitle(
            "Add New Club"
    );

    dialog.setHeaderText(
            "Create a new club"
    );


    // =========================================================
    // FORM GRID
    // =========================================================

    GridPane grid =
            new GridPane();

    grid.setHgap(10);
    grid.setVgap(12);

    grid.setPadding(
            new Insets(20)
    );


    // =========================================================
    // INPUT FIELDS
    // =========================================================

    TextField name =
            new TextField();

    name.setPromptText(
            "Club name"
    );


    TextField email =
            new TextField();

    email.setPromptText(
            "Email"
    );


    TextField phone =
            new TextField();

    phone.setPromptText(
            "Phone"
    );


    TextField category =
            new TextField();

    category.setPromptText(
            "Category"
    );


    TextField address =
            new TextField();

    address.setPromptText(
            "Address"
    );


    TextField ownerId =
            new TextField();

    ownerId.setPromptText(
            "Owner ID"
    );


    // =========================================================
    // ADD FIELDS TO GRID
    // =========================================================

    grid.add(
            new Label("Club Name"),
            0,
            0
    );

    grid.add(
            name,
            1,
            0
    );


    grid.add(
            new Label("Email"),
            0,
            1
    );

    grid.add(
            email,
            1,
            1
    );


    grid.add(
            new Label("Phone"),
            0,
            2
    );

    grid.add(
            phone,
            1,
            2
    );


    grid.add(
            new Label("Category"),
            0,
            3
    );

    grid.add(
            category,
            1,
            3
    );


    grid.add(
            new Label("Address"),
            0,
            4
    );

    grid.add(
            address,
            1,
            4
    );


    grid.add(
            new Label("Owner ID"),
            0,
            5
    );

    grid.add(
            ownerId,
            1,
            5
    );


    // =========================================================
    // SET DIALOG CONTENT
    // =========================================================

    dialog.getDialogPane()
            .setContent(grid);


    // =========================================================
    // BUTTONS
    // =========================================================

    ButtonType saveButton =
            new ButtonType(
                    "Save",
                    ButtonBar.ButtonData.OK_DONE
            );

    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                    saveButton,
                    ButtonType.CANCEL
            );


    // =========================================================
    // SHOW DIALOG
    // =========================================================

    Optional<ButtonType> result =
            dialog.showAndWait();


    if (!result.isPresent()
            || result.get() != saveButton) {

        return;
    }


    // =========================================================
    // VALIDATION
    // =========================================================

    if (name.getText()
            .trim()
            .isEmpty()) {

        showError(
                "Validation Error",
                "Club name is required."
        );

        return;
    }


    // =========================================================
    // SAVE CLUB TO FIRESTORE
    // =========================================================

    try {

        System.out.println(">>> Creating new ClubOwners document...");


        String documentId =
                database
                        .collection("ClubOwners")
                        .document()
                        .getId();


        System.out.println(
                ">>> New Club ID = "
                        + documentId
        );


        Map<String, Object> data =
                new HashMap<>();


        data.put(
                "clubName",
                name.getText().trim()
        );


        data.put(
                "name",
                name.getText().trim()
        );


        data.put(
                "email",
                email.getText().trim()
        );


        data.put(
                "phone",
                phone.getText().trim()
        );


        data.put(
                "category",
                category.getText().trim()
        );


        data.put(
                "address",
                address.getText().trim()
        );


        data.put(
                "ownerId",
                ownerId.getText().trim()
        );


        data.put(
                "activeTrainers",
                0
        );


        data.put(
                "maxCapacity",
                0
        );


        data.put(
                "status",
                "ACTIVE"
        );


        data.put(
                "createdAt",
                LocalDateTime.now().toString()
        );


        System.out.println(
                ">>> Saving club to ClubOwners..."
        );


        database
                .collection("ClubOwners")
                .document(documentId)
                .set(data)
                .get();


        System.out.println(
                ">>> CLUB SAVED SUCCESSFULLY"
        );


        showInfo(
                "Success",
                "Club added successfully."
        );


    } catch (Exception e) {

        System.out.println(
                ">>> ERROR WHILE ADDING CLUB"
        );

        e.printStackTrace();


        showError(
                "Error",
                "Unable to add club.\n\n"
                        + e.getMessage()
        );
    }
}

    // =====================================================
    // EDIT CLUB
    // =====================================================

    /*private void showEditClubDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Club"
        );

        dialog.setHeaderText(
                "Enter Club document ID"
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(10);
        grid.setVgap(12);

        grid.setPadding(
                new Insets(20)
        );

        TextField id =
                new TextField();

        id.setPromptText(
                "Club document ID"
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "New club name"
        );

        TextField email =
                new TextField();

        email.setPromptText(
                "New email"
        );

        TextField phone =
                new TextField();

        phone.setPromptText(
                "New phone"
        );

        TextField category =
                new TextField();

        category.setPromptText(
                "New category"
        );

        TextField address =
                new TextField();

        address.setPromptText(
                "New address"
        );

        grid.add(
                new Label("Document ID"),
                0,
                0
        );

        grid.add(
                id,
                1,
                0
        );

        grid.add(
                new Label("Club Name"),
                0,
                1
        );

        grid.add(
                name,
                1,
                1
        );

        grid.add(
                new Label("Email"),
                0,
                2
        );

        grid.add(
                email,
                1,
                2
        );

        grid.add(
                new Label("Phone"),
                0,
                3
        );

        grid.add(
                phone,
                1,
                3
        );

        grid.add(
                new Label("Category"),
                0,
                4
        );

        grid.add(
                category,
                1,
                4
        );

        grid.add(
                new Label("Address"),
                0,
                5
        );

        grid.add(
                address,
                1,
                5
        );

        dialog.getDialogPane()
                .setContent(grid);

        ButtonType updateButton =
                new ButtonType(
                        "Update",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        updateButton,
                        ButtonType.CANCEL
                );

        Optional<ButtonType> result =
                dialog.showAndWait();

        if (!result.isPresent()
                || result.get() != updateButton) {

            return;
        }

        if (id.getText()
                .trim()
                .isEmpty()) {

            showError(
                    "Validation Error",
                    "Document ID is required."
            );

            return;
        }

        try {

            DocumentReference ref =
                    db.collection(
                            "ClubOwners"
                    )
                    .document(
                            id.getText().trim()
                    );

            DocumentSnapshot doc =
                    ref.get().get();

            if (!doc.exists()) {

                showError(
                        "Not Found",
                        "Club document was not found."
                );

                return;
            }

            Map<String, Object> updates =
                    new HashMap<>();

            if (!name.getText()
                    .trim()
                    .isEmpty()) {

                updates.put(
                        "clubName",
                        name.getText().trim()
                );

                updates.put(
                        "name",
                        name.getText().trim()
                );
            }

            if (!email.getText()
                    .trim()
                    .isEmpty()) {

                updates.put(
                        "email",
                        email.getText().trim()
                );
            }

            if (!phone.getText()
                    .trim()
                    .isEmpty()) {

                updates.put(
                        "phone",
                        phone.getText().trim()
                );
            }

            if (!category.getText()
                    .trim()
                    .isEmpty()) {

                updates.put(
                        "category",
                        category.getText().trim()
                );
            }

            if (!address.getText()
                    .trim()
                    .isEmpty()) {

                updates.put(
                        "address",
                        address.getText().trim()
                );
            }

            updates.put(
                    "updatedAt",
                    LocalDateTime.now()
                            .toString()
            );

            if (!updates.isEmpty()) {

                ref.update(updates).get();
            }

            showInfo(
                    "Success",
                    "Club updated successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to update club."
            );
        }
    }*/

       private void showEditClubDialog() {

    // ---------------------------------------------------------
    // GET FIRESTORE DIRECTLY
    // ---------------------------------------------------------
    Firestore firestore;

    try {

        firestore = FirebaseConfig.getFirebaseConfig();

        if (firestore == null) {

            showError(
                    "Firebase Error",
                    "Unable to connect to Firebase."
            );

            return;
        }

    } catch (Exception e) {

        e.printStackTrace();

        showError(
                "Firebase Error",
                "Unable to initialize Firebase."
        );

        return;
    }


    // ---------------------------------------------------------
    // CREATE DIALOG
    // ---------------------------------------------------------

    Dialog<ButtonType> dialog = new Dialog<>();

    dialog.setTitle("Edit Club");

    dialog.setHeaderText(
            "Enter Club Document ID and new details"
    );


    GridPane grid = new GridPane();

    grid.setHgap(10);
    grid.setVgap(12);

    grid.setPadding(
            new Insets(20)
    );


    // ---------------------------------------------------------
    // TEXT FIELDS
    // ---------------------------------------------------------

    TextField id = new TextField();

    id.setPromptText(
            "Club document ID"
    );


    TextField name = new TextField();

    name.setPromptText(
            "New club name"
    );


    TextField email = new TextField();

    email.setPromptText(
            "New email"
    );


    TextField phone = new TextField();

    phone.setPromptText(
            "New phone"
    );


    TextField category = new TextField();

    category.setPromptText(
            "New category"
    );


    TextField address = new TextField();

    address.setPromptText(
            "New address"
    );


    // ---------------------------------------------------------
    // ADD FIELDS TO GRID
    // ---------------------------------------------------------

    grid.add(
            new Label("Document ID"),
            0,
            0
    );

    grid.add(
            id,
            1,
            0
    );


    grid.add(
            new Label("Club Name"),
            0,
            1
    );

    grid.add(
            name,
            1,
            1
    );


    grid.add(
            new Label("Email"),
            0,
            2
    );

    grid.add(
            email,
            1,
            2
    );


    grid.add(
            new Label("Phone"),
            0,
            3
    );

    grid.add(
            phone,
            1,
            3
    );


    grid.add(
            new Label("Category"),
            0,
            4
    );

    grid.add(
            category,
            1,
            4
    );


    grid.add(
            new Label("Address"),
            0,
            5
    );

    grid.add(
            address,
            1,
            5
    );


    dialog.getDialogPane()
            .setContent(grid);


    // ---------------------------------------------------------
    // BUTTONS
    // ---------------------------------------------------------

    ButtonType updateButton =
            new ButtonType(
                    "Update",
                    ButtonBar.ButtonData.OK_DONE
            );


    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                    updateButton,
                    ButtonType.CANCEL
            );


    // ---------------------------------------------------------
    // SHOW DIALOG
    // ---------------------------------------------------------

    Optional<ButtonType> result =
            dialog.showAndWait();


    if (!result.isPresent()
            || result.get() != updateButton) {

        return;
    }


    // ---------------------------------------------------------
    // VALIDATE DOCUMENT ID
    // ---------------------------------------------------------

    String documentId =
            id.getText()
                    .trim();


    if (documentId.isEmpty()) {

        showError(
                "Validation Error",
                "Document ID is required."
        );

        return;
    }


    // ---------------------------------------------------------
    // UPDATE FIRESTORE
    // ---------------------------------------------------------

    try {

        System.out.println(
                ">>> EDIT CLUB"
        );

        System.out.println(
                ">>> Document ID = "
                        + documentId
        );


        DocumentReference ref =
                firestore
                        .collection("ClubOwners")
                        .document(documentId);


        // -----------------------------------------------------
        // CHECK WHETHER DOCUMENT EXISTS
        // -----------------------------------------------------

        DocumentSnapshot doc =
                ref.get().get();


        if (!doc.exists()) {

            showError(
                    "Club Not Found",
                    "No club found with Document ID:\n"
                            + documentId
            );

            return;
        }


        // -----------------------------------------------------
        // CREATE UPDATE MAP
        // -----------------------------------------------------

        Map<String, Object> updates =
                new HashMap<>();


        String clubName =
                name.getText().trim();


        String clubEmail =
                email.getText().trim();


        String clubPhone =
                phone.getText().trim();


        String clubCategory =
                category.getText().trim();


        String clubAddress =
                address.getText().trim();


        // -----------------------------------------------------
        // UPDATE ONLY ENTERED VALUES
        // -----------------------------------------------------

        if (!clubName.isEmpty()) {

            updates.put(
                    "clubName",
                    clubName
            );

            // Keep name field synchronized
            updates.put(
                    "name",
                    clubName
            );
        }


        if (!clubEmail.isEmpty()) {

            updates.put(
                    "email",
                    clubEmail
            );
        }


        if (!clubPhone.isEmpty()) {

            updates.put(
                    "phone",
                    clubPhone
            );
        }


        if (!clubCategory.isEmpty()) {

            updates.put(
                    "category",
                    clubCategory
            );
        }


        if (!clubAddress.isEmpty()) {

            updates.put(
                    "address",
                    clubAddress
            );
        }


        // -----------------------------------------------------
        // CHECK WHETHER THERE IS ANYTHING TO UPDATE
        // -----------------------------------------------------

        if (updates.isEmpty()) {

            showError(
                    "No Changes",
                    "Please enter at least one value to update."
            );

            return;
        }


        // -----------------------------------------------------
        // UPDATED TIME
        // -----------------------------------------------------

        updates.put(
                "updatedAt",
                LocalDateTime.now().toString()
        );


        // -----------------------------------------------------
        // UPDATE FIRESTORE
        // -----------------------------------------------------

        ref.update(updates).get();


        System.out.println(
                ">>> CLUB UPDATED SUCCESSFULLY"
        );


        // -----------------------------------------------------
        // SUCCESS MESSAGE
        // -----------------------------------------------------

        showInfo(
                "Success",
                "Club updated successfully."
        );


        // -----------------------------------------------------
        // REFRESH DASHBOARD
        // -----------------------------------------------------

        try {

            AdminDashboardService service =
                    new AdminDashboardService();

            AdminDashboardStats stats =
                    service.getDashboardStats();

            System.out.println(
                    ">>> Dashboard refreshed"
            );

        } catch (Exception refreshError) {

            refreshError.printStackTrace();
        }


    } catch (Exception e) {

        e.printStackTrace();

        showError(
                "Update Error",
                "Unable to update club.\n\n"
                        + e.getMessage()
        );
    }
}
    // =====================================================
    // DELETE CLUB
    // =====================================================

   /*  private void showDeleteClubDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setTitle(
                "Delete Club"
        );

        dialog.setHeaderText(
                "Delete Club"
        );

        dialog.setContentText(
                "Enter Club document ID:"
        );

        Optional<String> result =
                dialog.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        String id =
                result.get().trim();

        if (id.isEmpty()) {

            showError(
                    "Validation Error",
                    "Document ID is required."
            );

            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Confirm Delete"
        );

        confirmation.setHeaderText(
                "Delete this club?"
        );

        confirmation.setContentText(
                "Document ID: " + id
        );

        Optional<ButtonType> confirm =
                confirmation.showAndWait();

        if (!confirm.isPresent()
                || confirm.get() != ButtonType.OK) {

            return;
        }

        try {

            DocumentReference ref =
                    db.collection(
                            "ClubOwners"
                    )
                    .document(id);

            DocumentSnapshot doc =
                    ref.get().get();

            if (!doc.exists()) {

                showError(
                        "Not Found",
                        "Club document was not found."
                );

                return;
            }

            ref.delete().get();

            showInfo(
                    "Success",
                    "Club deleted successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to delete club."
            );
        }
    }*/

      private void showDeleteClubDialog() {

    // ---------------------------------------------------------
    // GET FIRESTORE DIRECTLY
    // ---------------------------------------------------------

    Firestore firestore;

    try {

        firestore =
                FirebaseConfig.getFirebaseConfig();


        if (firestore == null) {

            showError(
                    "Firebase Error",
                    "Unable to connect to Firebase."
            );

            return;
        }

    } catch (Exception e) {

        e.printStackTrace();

        showError(
                "Firebase Error",
                "Unable to initialize Firebase."
        );

        return;
    }


    // ---------------------------------------------------------
    // ASK FOR DOCUMENT ID
    // ---------------------------------------------------------

    TextInputDialog dialog =
            new TextInputDialog();


    dialog.setTitle(
            "Delete Club"
    );


    dialog.setHeaderText(
            "Delete Club"
    );


    dialog.setContentText(
            "Enter Club Document ID:"
    );


    com.flexforce.view.components.DialogUtils.applyTheme(dialog);
    Optional<String> result =
            dialog.showAndWait();


    if (!result.isPresent()) {

        return;
    }


    String documentId =
            result.get().trim();


    // ---------------------------------------------------------
    // VALIDATE ID
    // ---------------------------------------------------------

    if (documentId.isEmpty()) {

        showError(
                "Validation Error",
                "Document ID is required."
        );

        return;
    }


    // ---------------------------------------------------------
    // GET CLUB FIRST
    // ---------------------------------------------------------

    try {

        System.out.println(
                ">>> DELETE CLUB"
        );

        System.out.println(
                ">>> Document ID = "
                        + documentId
        );


        DocumentReference ref =
                firestore
                        .collection("ClubOwners")
                        .document(documentId);


        DocumentSnapshot doc =
                ref.get().get();


        // -----------------------------------------------------
        // CHECK DOCUMENT
        // -----------------------------------------------------

        if (!doc.exists()) {

            showError(
                    "Club Not Found",
                    "No club found with Document ID:\n"
                            + documentId
            );

            return;
        }


        // -----------------------------------------------------
        // GET CLUB NAME
        // -----------------------------------------------------

        String clubName =
                doc.getString("clubName");


        if (clubName == null
                || clubName.isEmpty()) {

            clubName =
                    doc.getString("name");
        }


        if (clubName == null) {

            clubName =
                    "Unknown Club";
        }


        // -----------------------------------------------------
        // CONFIRMATION
        // -----------------------------------------------------

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );


        confirmation.setTitle(
                "Confirm Delete"
        );


        confirmation.setHeaderText(
                "Delete this club?"
        );


        confirmation.setContentText(
                "Club: "
                        + clubName
                        + "\n\nDocument ID: "
                        + documentId
                        + "\n\nThis action cannot be undone."
        );


        com.flexforce.view.components.DialogUtils.applyTheme(confirmation);
        Optional<ButtonType> confirm =
                confirmation.showAndWait();


        if (!confirm.isPresent()
                || confirm.get() != ButtonType.OK) {

            return;
        }


        // -----------------------------------------------------
        // DELETE DOCUMENT
        // -----------------------------------------------------

        ref.delete().get();


        System.out.println(
                ">>> CLUB DELETED SUCCESSFULLY"
        );


        // -----------------------------------------------------
        // SUCCESS
        // -----------------------------------------------------

        showInfo(
                "Success",
                "Club deleted successfully."
        );


        // -----------------------------------------------------
        // REFRESH DASHBOARD
        // -----------------------------------------------------

        try {

            AdminDashboardService service =
                    new AdminDashboardService();

            AdminDashboardStats stats =
                    service.getDashboardStats();


            System.out.println(
                    ">>> Dashboard refreshed"
            );

        } catch (Exception refreshError) {

            refreshError.printStackTrace();
        }


    } catch (Exception e) {

        e.printStackTrace();


        showError(
                "Delete Error",
                "Unable to delete club.\n\n"
                        + e.getMessage()
        );
    }
}

    // =====================================================
    // VIEW USERS
    // =====================================================

   /* private void showUsersDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Users"
        );

        dialog.setHeaderText(
                "Users from UserInfo"
        );

        VBox container =
                new VBox(10);

        container.setPadding(
                new Insets(15)
        );

        container.setPrefWidth(800);

        ScrollPane scroll =
                new ScrollPane(
                        container
                );

        scroll.setFitToWidth(true);

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "UserInfo"
                    )
                    .get()
                    .get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                String name =
                        getString(
                                doc,
                                "name",
                                "Unknown"
                        );

                String email =
                        getString(
                                doc,
                                "email",
                                ""
                        );

                String role =
                        getString(
                                doc,
                                "role",
                                ""
                        );

                String phone =
                        getString(
                                doc,
                                "phone",
                                ""
                        );

                String status =
                        getString(
                                doc,
                                "status",
                                "ACTIVE"
                        );

                HBox row =
                        new HBox(20);

                row.setPadding(
                        new Insets(12)
                );

                row.setStyle(
                        "-fx-background-color:#141C17;" +
                                "-fx-background-radius:8;"
                );

                Label user =
                        new Label(
                                name +
                                        " | " +
                                        email +
                                        " | " +
                                        phone +
                                        " | " +
                                        role +
                                        " | " +
                                        status
                        );

                user.setStyle(
                        "-fx-text-fill:#FFFFFF;"
                );

                row.getChildren().add(
                        user
                );

                container.getChildren().add(
                        row
                );
            }

            if (container.getChildren()
                    .isEmpty()) {

                Label empty =
                        new Label(
                                "No users found."
                        );

                empty.setStyle(
                        "-fx-text-fill:#FFFFFF;"
                );

                container.getChildren().add(
                        empty
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load users."
                    );

            error.setStyle(
                    "-fx-text-fill:#FFFFFF;"
            );

            container.getChildren().add(
                    error
            );
        }

        dialog.getDialogPane()
                .setContent(scroll);

        dialog.getDialogPane()
                .getButtonTypes()
                .add(
                        ButtonType.CLOSE
                );

        dialog.showAndWait();
    }*/

        private void showUsersDialog() {

    // =========================================================
    // GET FIRESTORE DIRECTLY
    // =========================================================

    Firestore firestore;

    try {

        firestore =
                FirebaseConfig.getFirebaseConfig();

        if (firestore == null) {

            showError(
                    "Firebase Error",
                    "Unable to connect to Firebase."
            );

            return;
        }

    } catch (Exception e) {

        e.printStackTrace();

        showError(
                "Firebase Error",
                "Unable to initialize Firebase."
        );

        return;
    }


    // =========================================================
    // CREATE DIALOG
    // =========================================================

    Dialog<ButtonType> dialog =
            new Dialog<>();

    dialog.setTitle(
            "Users"
    );

    dialog.setHeaderText(
            "Users from UserInfo"
    );


    // =========================================================
    // CONTAINER
    // =========================================================

    VBox container =
            new VBox(10);

    container.setPadding(
            new Insets(15)
    );

    container.setPrefWidth(
            850
    );


    ScrollPane scroll =
            new ScrollPane(
                    container
            );

    scroll.setFitToWidth(
            true
    );

    scroll.setPrefHeight(
            500
    );


    // =========================================================
    // READ USERS FROM FIRESTORE
    // =========================================================

    try {

        System.out.println(
                "========================================"
        );

        System.out.println(
                ">>> VIEW USERS"
        );

        System.out.println(
                ">>> Reading UserInfo collection..."
        );


        QuerySnapshot snapshot =
                firestore
                        .collection("UserInfo")
                        .get()
                        .get();


        System.out.println(
                ">>> Users found = "
                        + snapshot.size()
        );


        // =====================================================
        // CHECK EMPTY COLLECTION
        // =====================================================

        if (snapshot.isEmpty()) {

            Label empty =
                    new Label(
                            "No users found."
                    );

            empty.setStyle(
                    "-fx-text-fill:#FFFFFF;" +
                    "-fx-font-size:16px;"
            );

            container.getChildren().add(
                    empty
            );

        } else {


            // =================================================
            // LOOP THROUGH USERS
            // =================================================

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {


                // ---------------------------------------------
                // DOCUMENT ID
                // ---------------------------------------------

                String userId =
                        doc.getId();


                // ---------------------------------------------
                // USER NAME
                // ---------------------------------------------

                String name =
                        getString(
                                doc,
                                "name",
                                "Unknown"
                        );


                // ---------------------------------------------
                // EMAIL
                // ---------------------------------------------

                String email =
                        getString(
                                doc,
                                "email",
                                "Not available"
                        );


                // ---------------------------------------------
                // PHONE
                // ---------------------------------------------

                String phone =
                        getString(
                                doc,
                                "phone",
                                "Not available"
                        );


                // ---------------------------------------------
                // ROLE
                // ---------------------------------------------

                String role =
                        getString(
                                doc,
                                "role",
                                "USER"
                        );


                // ---------------------------------------------
                // STATUS
                // ---------------------------------------------

                String status =
                        getString(
                                doc,
                                "status",
                                "ACTIVE"
                        );


                // =================================================
                // CREATE USER ROW
                // =================================================

                VBox userBox =
                        new VBox(6);

                userBox.setPadding(
                        new Insets(12)
                );

                userBox.setStyle(
                        "-fx-background-color:#141C17;" +
                        "-fx-background-radius:8;" +
                        "-fx-border-color:#26352D;" +
                        "-fx-border-radius:8;"
                );


                // =================================================
                // USER ID
                // =================================================

                Label idLabel =
                        new Label(
                                "User ID: "
                                        + userId
                        );

                idLabel.setStyle(
                        "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:12px;"
                );


                // =================================================
                // NAME
                // =================================================

                Label nameLabel =
                        new Label(
                                "Name: "
                                        + name
                        );

                nameLabel.setStyle(
                        "-fx-text-fill:#FFFFFF;" +
                        "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;"
                );


                // =================================================
                // EMAIL
                // =================================================

                Label emailLabel =
                        new Label(
                                "Email: "
                                        + email
                        );

                emailLabel.setStyle(
                        "-fx-text-fill:#FFFFFF;"
                );


                // =================================================
                // PHONE
                // =================================================

                Label phoneLabel =
                        new Label(
                                "Phone: "
                                        + phone
                        );

                phoneLabel.setStyle(
                        "-fx-text-fill:#FFFFFF;"
                );


                // =================================================
                // ROLE
                // =================================================

                Label roleLabel =
                        new Label(
                                "Role: "
                                        + role
                        );

                roleLabel.setStyle(
                        "-fx-text-fill:#FFFFFF;"
                );


                // =================================================
                // STATUS
                // =================================================

                Label statusLabel =
                        new Label(
                                "Status: "
                                        + status
                        );

                statusLabel.setStyle(
                        "-fx-text-fill:#B6FF00;" +
                        "-fx-font-weight:bold;"
                );


                // =================================================
                // ADD EVERYTHING TO USER BOX
                // =================================================

                userBox.getChildren().addAll(
                        idLabel,
                        nameLabel,
                        emailLabel,
                        phoneLabel,
                        roleLabel,
                        statusLabel
                );


                // =================================================
                // ADD USER TO MAIN CONTAINER
                // =================================================

                container.getChildren().add(
                        userBox
                );
            }
        }


        System.out.println(
                ">>> Users loaded successfully"
        );


    } catch (Exception e) {

        e.printStackTrace();


        System.out.println(
                ">>> ERROR WHILE READING USERS"
        );


        Label error =
                new Label(
                        "Unable to load users.\n"
                                + e.getMessage()
                );

        error.setStyle(
                "-fx-text-fill:#FF5555;" +
                "-fx-font-size:14px;"
        );


        container.getChildren().add(
                error
        );
    }


    // =========================================================
    // SET DIALOG CONTENT
    // =========================================================

    dialog.getDialogPane()
            .setContent(
                    scroll
            );


    // =========================================================
    // CLOSE BUTTON
    // =========================================================

    dialog.getDialogPane()
            .getButtonTypes()
            .add(
                    ButtonType.CLOSE
            );


    // =========================================================
    // SHOW DIALOG
    // =========================================================

    dialog.showAndWait();
}
     //------------------------------------------------------------------------------------------------------------------------------------------

    // =====================================================
    // SEND NOTIFICATION
    // =====================================================

   /*  private void showNotificationDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Send Notification"
        );

        dialog.setHeaderText(
                "Create Admin Notification"
        );

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(20)
        );

        TextField title =
                new TextField();

        title.setPromptText(
                "Notification title"
        );

        TextArea message =
                new TextArea();

        message.setPromptText(
                "Notification message"
        );

        message.setPrefRowCount(5);

        box.getChildren().addAll(
                new Label("Title"),
                title,
                new Label("Message"),
                message
        );

        dialog.getDialogPane()
                .setContent(box);

        ButtonType sendButton =
                new ButtonType(
                        "Send",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        sendButton,
                        ButtonType.CANCEL
                );

        Optional<ButtonType> result =
                dialog.showAndWait();

        if (!result.isPresent()
                || result.get() != sendButton) {

            return;
        }

        if (title.getText()
                .trim()
                .isEmpty()
                ||
                message.getText()
                        .trim()
                        .isEmpty()) {

            showError(
                    "Validation Error",
                    "Title and message are required."
            );

            return;
        }

        try {

            String id =
                    db.collection(
                            "AdminNotifications"
                    )
                    .document()
                    .getId();

            Map<String, Object> notification =
                    new HashMap<>();

            notification.put(
                    "title",
                    title.getText().trim()
            );

            notification.put(
                    "message",
                    message.getText().trim()
            );

            notification.put(
                    "createdAt",
                    LocalDateTime.now()
                            .toString()
            );

            notification.put(
                    "status",
                    "SENT"
            );

            db.collection(
                    "AdminNotifications"
            )
            .document(id)
            .set(notification)
            .get();

            showInfo(
                    "Success",
                    "Notification saved successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to save notification."
            );
        }
    }*/

        private void showNotificationDialog() {

    // =========================================================
    // GET FIRESTORE CONNECTION
    // =========================================================

    Firestore firestore;

    try {

        firestore =
                FirebaseConfig.getFirebaseConfig();

        if (firestore == null) {

            showError(
                    "Firebase Error",
                    "Unable to connect to Firebase."
            );

            return;
        }

    } catch (Exception e) {

        e.printStackTrace();

        showError(
                "Firebase Error",
                "Unable to initialize Firebase."
        );

        return;
    }


    // =========================================================
    // CREATE DIALOG
    // =========================================================

    Dialog<ButtonType> dialog =
            new Dialog<>();

    dialog.setTitle(
            "Send Notification"
    );

    dialog.setHeaderText(
            "Create Admin Notification"
    );


    // =========================================================
    // MAIN BOX
    // =========================================================

    VBox box =
            new VBox(12);

    box.setPadding(
            new Insets(20)
    );

    box.setPrefWidth(
            500
    );


    // =========================================================
    // TITLE
    // =========================================================

    Label titleLabel =
            new Label(
                    "Notification Title"
            );

    titleLabel.setStyle(
            "-fx-font-weight:bold;"
    );


    TextField title =
            new TextField();

    title.setPromptText(
            "Enter notification title"
    );


    // =========================================================
    // MESSAGE
    // =========================================================

    Label messageLabel =
            new Label(
                    "Notification Message"
            );

    messageLabel.setStyle(
            "-fx-font-weight:bold;"
    );


    TextArea message =
            new TextArea();

    message.setPromptText(
            "Enter notification message"
    );

    message.setPrefRowCount(
            6
    );

    message.setWrapText(
            true
    );


    // =========================================================
    // INFORMATION LABEL
    // =========================================================

    Label info =
            new Label(
                    "This notification will be saved for users."
            );

    info.setStyle(
            "-fx-text-fill:#777777;" +
            "-fx-font-size:12px;"
    );


    // =========================================================
    // ADD CONTROLS
    // =========================================================

    box.getChildren().addAll(

            titleLabel,

            title,

            messageLabel,

            message,

            info
    );


    // =========================================================
    // SET CONTENT
    // =========================================================

    dialog.getDialogPane()
            .setContent(
                    box
            );


    // =========================================================
    // BUTTONS
    // =========================================================

    ButtonType sendButton =
            new ButtonType(
                    "Send",
                    ButtonBar.ButtonData.OK_DONE
            );


    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(

                    sendButton,

                    ButtonType.CANCEL
            );


    // =========================================================
    // SHOW DIALOG
    // =========================================================

    Optional<ButtonType> result =
            dialog.showAndWait();


    // =========================================================
    // CHECK CANCEL
    // =========================================================

    if (!result.isPresent()
            || result.get() != sendButton) {

        return;
    }


    // =========================================================
    // GET VALUES
    // =========================================================

    String notificationTitle =
            title.getText()
                    .trim();


    String notificationMessage =
            message.getText()
                    .trim();


    // =========================================================
    // VALIDATION
    // =========================================================

    if (notificationTitle.isEmpty()) {

        showError(
                "Validation Error",
                "Notification title is required."
        );

        return;
    }


    if (notificationMessage.isEmpty()) {

        showError(
                "Validation Error",
                "Notification message is required."
        );

        return;
    }


    // =========================================================
    // SAVE NOTIFICATION
    // =========================================================

    try {

        System.out.println(
                "========================================"
        );

        System.out.println(
                ">>> SEND NOTIFICATION"
        );


        // -----------------------------------------------------
        // CREATE DOCUMENT ID
        // -----------------------------------------------------

        String notificationId =
                firestore
                        .collection(
                                "AdminNotifications"
                        )
                        .document()
                        .getId();


        System.out.println(
                ">>> Notification ID = "
                        + notificationId
        );


        // -----------------------------------------------------
        // CREATE DATA MAP
        // -----------------------------------------------------

        Map<String, Object> notification =
                new HashMap<>();


        notification.put(
                "notificationId",
                notificationId
        );


        notification.put(
                "title",
                notificationTitle
        );


        notification.put(
                "message",
                notificationMessage
        );


        notification.put(
                "createdAt",
                LocalDateTime.now()
                        .toString()
        );


        notification.put(
                "status",
                "SENT"
        );


        notification.put(
                "sender",
                "ADMIN"
        );


        // -----------------------------------------------------
        // SAVE TO FIRESTORE
        // -----------------------------------------------------

        firestore
                .collection(
                        "AdminNotifications"
                )
                .document(
                        notificationId
                )
                .set(
                        notification
                )
                .get();


        System.out.println(
                ">>> Notification saved successfully"
        );


        System.out.println(
                ">>> Title = "
                        + notificationTitle
        );


        System.out.println(
                ">>> Message = "
                        + notificationMessage
        );


        System.out.println(
                "========================================"
        );


        // =====================================================
        // SUCCESS MESSAGE
        // =====================================================

        showInfo(
                "Notification Sent",
                "Notification saved successfully."
        );


    } catch (Exception e) {

        e.printStackTrace();


        System.out.println(
                ">>> Notification save failed"
        );


        showError(
                "Notification Error",
                "Unable to save notification.\n\n"
                        + e.getMessage()
        );
    }
}
//------------------------------------------------------------------------------------------------------------------------------------
    // =====================================================
    // BLOCK USER
    // =====================================================

  /*   private void showBlockUserDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setTitle(
                "Block User"
        );

        dialog.setHeaderText(
                "Block User"
        );

        dialog.setContentText(
                "Enter UserInfo document ID:"
        );

        Optional<String> result =
                dialog.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        String id =
                result.get().trim();

        if (id.isEmpty()) {

            showError(
                    "Validation Error",
                    "User document ID is required."
            );

            return;
        }

        try {

            DocumentReference ref =
                    db.collection(
                            "UserInfo"
                    )
                    .document(id);

            DocumentSnapshot doc =
                    ref.get().get();

            if (!doc.exists()) {

                showError(
                        "Not Found",
                        "User was not found."
                );

                return;
            }

            ref.update(
                    "status",
                    "BLOCKED"
            ).get();

            showInfo(
                    "Success",
                    "User has been blocked."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to block user."
            );
        }
    }*/

        private void showBlockUserDialog() {

    // =========================================================
    // GET FIRESTORE DIRECTLY
    // =========================================================

    Firestore firestore;

    try {

        firestore =
                FirebaseConfig.getFirebaseConfig();

        if (firestore == null) {

            showError(
                    "Firebase Error",
                    "Unable to connect to Firebase."
            );

            return;
        }

    } catch (Exception e) {

        e.printStackTrace();

        showError(
                "Firebase Error",
                "Unable to initialize Firebase."
        );

        return;
    }


    // =========================================================
    // ASK FOR USER DOCUMENT ID
    // =========================================================

    TextInputDialog dialog =
            new TextInputDialog();

    dialog.setTitle(
            "Block User"
    );

    dialog.setHeaderText(
            "Block User"
    );

    dialog.setContentText(
            "Enter UserInfo document ID:"
    );


    com.flexforce.view.components.DialogUtils.applyTheme(dialog);
    Optional<String> result =
            dialog.showAndWait();


    if (!result.isPresent()) {

        return;
    }


    // =========================================================
    // GET DOCUMENT ID
    // =========================================================

    String userId =
            result.get().trim();


    if (userId.isEmpty()) {

        showError(
                "Validation Error",
                "User document ID is required."
        );

        return;
    }


    // =========================================================
    // FIRESTORE OPERATION
    // =========================================================

    try {

        System.out.println(
                "========================================"
        );

        System.out.println(
                ">>> BLOCK USER"
        );

        System.out.println(
                ">>> User Document ID = "
                        + userId
        );


        // -----------------------------------------------------
        // GET USER DOCUMENT
        // -----------------------------------------------------

        DocumentReference ref =
                firestore
                        .collection("UserInfo")
                        .document(userId);


        DocumentSnapshot doc =
                ref.get().get();


        // -----------------------------------------------------
        // CHECK USER EXISTS
        // -----------------------------------------------------

        if (!doc.exists()) {

            showError(
                    "User Not Found",
                    "No user was found with Document ID:\n"
                            + userId
            );

            return;
        }


        // -----------------------------------------------------
        // GET USER NAME
        // -----------------------------------------------------

        String userName =
                getString(
                        doc,
                        "name",
                        "Unknown User"
                );


        // -----------------------------------------------------
        // GET CURRENT STATUS
        // -----------------------------------------------------

        String currentStatus =
                getString(
                        doc,
                        "status",
                        "ACTIVE"
                );


        // -----------------------------------------------------
        // CHECK IF ALREADY BLOCKED
        // -----------------------------------------------------

        if (currentStatus.equalsIgnoreCase(
                "BLOCKED"
        )) {

            showInfo(
                    "Already Blocked",
                    "User "
                            + userName
                            + " is already blocked."
            );

            return;
        }


        // =====================================================
        // CONFIRMATION DIALOG
        // =====================================================

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );


        confirmation.setTitle(
                "Confirm Block"
        );


        confirmation.setHeaderText(
                "Block this user?"
        );


        confirmation.setContentText(
                "User: "
                        + userName
                        + "\n\nUser ID: "
                        + userId
                        + "\n\nThe user's status will be changed to BLOCKED."
        );


        com.flexforce.view.components.DialogUtils.applyTheme(confirmation);
        Optional<ButtonType> confirm =
                confirmation.showAndWait();


        if (!confirm.isPresent()
                || confirm.get() != ButtonType.OK) {

            return;
        }


        // =====================================================
        // UPDATE USER STATUS
        // =====================================================

        ref.update(
                "status",
                "BLOCKED"
        ).get();


        System.out.println(
                ">>> USER BLOCKED SUCCESSFULLY"
        );


        // =====================================================
        // SUCCESS MESSAGE
        // =====================================================

        showInfo(
                "Success",
                "User "
                        + userName
                        + " has been blocked successfully."
        );


        System.out.println(
                "========================================"
        );


    } catch (Exception e) {

        e.printStackTrace();


        showError(
                "Block User Error",
                "Unable to block user.\n\n"
                        + e.getMessage()
        );
    }
}

    // =====================================================
    // FIND BOOKING COLLECTION
    // =====================================================

    private String findBookingCollection() {

        if (db == null) {
            return null;
        }

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
                        name.contains("reservation")) {

                    return collection.getId();
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
     //------------------------------------------------------------------------------------------------------------------------------------------
    // =====================================================
    // VIEW BOOKINGS
    // =====================================================

    private void showBookingsDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        String bookingCollection =
                findBookingCollection();

        if (bookingCollection == null) {

            showInfo(
                    "Booking Collection Not Found",
                    "No booking or reservation collection " +
                            "was found in Firestore."
            );

            return;
        }

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Bookings"
        );

        dialog.setHeaderText(
                "Collection: " +
                        bookingCollection
        );

        VBox container =
                new VBox(10);

        container.setPadding(
                new Insets(15)
        );

        container.setPrefWidth(800);

        ScrollPane scroll =
                new ScrollPane(
                        container
                );

        scroll.setFitToWidth(true);

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            bookingCollection
                    )
                    .get()
                    .get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Label booking =
                        new Label(
                                "ID: " +
                                        doc.getId() +
                                        "\n" +
                                        String.valueOf(
                                                doc.getData()
                                        )
                        );

                booking.setWrapText(true);

                booking.setStyle(
                        "-fx-text-fill:#FFFFFF;" +
                                "-fx-padding:10;"
                );

                container.getChildren().add(
                        booking
                );
            }

            if (container.getChildren()
                    .isEmpty()) {

                Label empty =
                        new Label(
                                "No bookings found."
                        );

                empty.setStyle(
                        "-fx-text-fill:#FFFFFF;"
                );

                container.getChildren().add(
                        empty
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load bookings."
                    );

            error.setStyle(
                    "-fx-text-fill:#FFFFFF;"
            );

            container.getChildren().add(
                    error
            );
        }

        dialog.getDialogPane()
                .setContent(scroll);

        dialog.getDialogPane()
                .getButtonTypes()
                .add(
                        ButtonType.CLOSE
                );

        dialog.showAndWait();
    }

    // =====================================================
    // CANCEL BOOKING
    // =====================================================

    private void showCancelBookingDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        String collection =
                findBookingCollection();

        if (collection == null) {

            showInfo(
                    "Booking Collection Not Found",
                    "Your Firestore currently has no " +
                            "booking/reservation collection."
            );

            return;
        }

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setTitle(
                "Cancel Booking"
        );

        dialog.setHeaderText(
                "Collection: " + collection
        );

        dialog.setContentText(
                "Enter booking document ID:"
        );

        com.flexforce.view.components.DialogUtils.applyTheme(dialog);
        Optional<String> result =
                dialog.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        String id =
                result.get().trim();

        if (id.isEmpty()) {
            return;
        }

        try {

            DocumentReference ref =
                    db.collection(
                            collection
                    )
                    .document(id);

            DocumentSnapshot doc =
                    ref.get().get();

            if (!doc.exists()) {

                showError(
                        "Not Found",
                        "Booking was not found."
                );

                return;
            }

            ref.update(
                    "status",
                    "CANCELLED"
            ).get();

            showInfo(
                    "Success",
                    "Booking cancelled successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to cancel booking."
            );
        }
    }

    // =====================================================
    // REFUND
    // =====================================================

    private void showRefundDialog() {

        if (db == null) {

            showError(
                    "Firebase Error",
                    "Firebase is not connected."
            );

            return;
        }

        String collection =
                findBookingCollection();

        if (collection == null) {

            showInfo(
                    "Booking Collection Not Found",
                    "No booking/reservation collection " +
                            "was found."
            );

            return;
        }

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setTitle(
                "Issue Refund"
        );

        dialog.setHeaderText(
                "Booking Collection: " +
                        collection
        );

        dialog.setContentText(
                "Enter booking document ID:"
        );

        com.flexforce.view.components.DialogUtils.applyTheme(dialog);
        Optional<String> result =
                dialog.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        String bookingId =
                result.get().trim();

        if (bookingId.isEmpty()) {
            return;
        }

        try {

            DocumentReference bookingRef =
                    db.collection(
                            collection
                    )
                    .document(
                            bookingId
                    );

            DocumentSnapshot booking =
                    bookingRef.get().get();

            if (!booking.exists()) {

                showError(
                        "Not Found",
                        "Booking was not found."
                );

                return;
            }

            Map<String, Object> refund =
                    new HashMap<>();

            refund.put(
                    "bookingId",
                    bookingId
            );

            refund.put(
                    "status",
                    "REQUESTED"
            );

            refund.put(
                    "requestedAt",
                    LocalDateTime.now()
                            .toString()
            );

            db.collection(
                    "RefundRequests"
            )
            .document()
            .set(refund)
            .get();

            bookingRef.update(
                    "refundStatus",
                    "REQUESTED"
            ).get();

            showInfo(
                    "Refund Request Created",
                    "Refund request has been recorded."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Error",
                    "Unable to create refund request."
            );
        }
    }

    // =====================================================
    // COLLECTION COUNT
    // =====================================================

    private String getCollectionCount(
            String collectionName) {

        if (db == null) {
            return "0";
        }

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            collectionName
                    )
                    .get()
                    .get();

            return String.valueOf(
                    snapshot.size()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return "0";
        }
    }

    // =====================================================
    // ACTIVE USERS
    // =====================================================

    private String getActiveUsersCount() {

        if (db == null) {
            return "0";
        }

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "UserInfo"
                    )
                    .whereEqualTo(
                            "role",
                            "MEMBER"
                    )
                    .get()
                    .get();

            return String.valueOf(
                    snapshot.size()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return "0";
        }
    }

    // =====================================================
    // BOOKING COUNT
    // =====================================================

    private String getBookingCount() {

        String collection =
                findBookingCollection();

        if (collection == null) {
            return "0";
        }

        return getCollectionCount(
                collection
        );
    }

    // =====================================================
    // REVENUE
    // =====================================================

    private String getRevenue() {

        String collection =
                findBookingCollection();

        if (collection == null) {
            return "₹0";
        }

        double total = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            collection
                    )
                    .get()
                    .get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Object amount =
                        doc.get("amount");

                if (amount instanceof Number) {

                    total +=
                            ((Number) amount)
                                    .doubleValue();
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "₹" +
                String.format(
                        Locale.US,
                        "%.0f",
                        total
                );
    }

    // =====================================================
    // GET STRING
    // =====================================================

    private String getString(
            DocumentSnapshot doc,
            String field,
            String defaultValue) {

        Object value =
                doc.get(field);

        if (value == null) {
            return defaultValue;
        }

        return String.valueOf(value);
    }

    // =====================================================
    // INFO
    // =====================================================

    private void showInfo(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }

    // =====================================================
    // ERROR
    // =====================================================

    private void showError(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }

    // =====================================================
    // SIMPLE PAGE
    // =====================================================

    private VBox createSimplePage(
            String pageName,
            String description) {

        Label title =
                new Label(pageName);

        title.setStyle(
                "-fx-font-size:30px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#FFFFFF;"
        );

        Label text =
                new Label(description);

        text.setStyle(
                "-fx-font-size:18px;" +
                        "-fx-text-fill:#9AA69D;"
        );

        Button back =
                new Button(
                        "← Back to Dashboard"
                );

        back.setStyle(
                "-fx-background-color:" +
                        GREEN + ";" +
                        "-fx-text-fill:#050705;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:10px 20px;" +
                        "-fx-background-radius:8px;" +
                        "-fx-cursor:hand;"
        );

        VBox page =
                new VBox(
                        15,
                        title,
                        text,
                        back
                );

        page.setAlignment(
                Pos.CENTER
        );

        page.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        back.setOnAction(e -> {

            if (page.getParent()
                    instanceof BorderPane) {

                BorderPane parent =
                        (BorderPane)
                                page.getParent();

                parent.setCenter(
                        createDashboard()
                );
            }
        });

        return page;
    }

        private String formatRevenue(double revenue) {
        if (revenue == 0) {
            return "₹0";
        }

        return String.format("₹%.0f", revenue);
    }

}
