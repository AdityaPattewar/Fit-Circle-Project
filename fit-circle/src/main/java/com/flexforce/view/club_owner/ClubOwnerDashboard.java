package com.flexforce.view.club_owner;


import com.flexforce.controller.AuthControllerClub;
import com.flexforce.controller.ClubOwnerDashboardController;
import com.flexforce.dao.MemberParticipationDAO;
import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.dao.UserEventDAO;
import com.flexforce.model.club_owner.MemberParticipation;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;
import com.flexforce.model.common_for_user_clubowner.UserEvent;
import com.flexforce.view.login.Login;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.flexforce.config.FirebaseConfig;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


/**
 * Club Owner Dashboard
 *
 * Dynamic data:
 * 1. Dashboard statistics
 * 2. Upcoming Events
 * 3. Member Participation Chart
 */
public class ClubOwnerDashboard extends Application {
// =========================================================
    // STAGE
    // =========================================================

    private static Stage mainStage;

    // =========================================================
    // FIREBASE
    // =========================================================

    private static final Firestore db =
            FirebaseConfig.getFirebaseConfig();

    // =========================================================
    // CONTROLLERS / DAO
    // =========================================================

    private static final ClubOwnerDashboardController dashboardController =
            new ClubOwnerDashboardController();

    private static final UserEventDAO eventDAO =
            new UserEventDAO();

    private static final MemberParticipationDAO participationDAO =
            new MemberParticipationDAO();

    private static final UserClubMembershipDAO membershipDAO =
            new UserClubMembershipDAO();

    // =========================================================
    // DATA
    // =========================================================

    private static String ownerId;

    private static List<UserEvent> ownerEvents =
            new ArrayList<>();

    private static List<MemberParticipation> participationList =
            new ArrayList<>();

    private static List<UserClubMembership> membershipList =
            new ArrayList<>();

    // =========================================================
    // DYNAMIC COUNTS
    // =========================================================

    private static int totalMembers = 0;

    private static int upcomingEvents = 0;

    private static int activeActivities = 0;

    private static double monthlyEngagement = 0;

    // =========================================================
    // COLORS
    // =========================================================

    private static final String BACKGROUND = "#0B0F14";

    private static final String SIDEBAR = "#111820";

    private static final String SIDEBAR_CARD = "#1A232D";

    private static final String SIDEBAR_BORDER = "#2A3541";

    private static final String DASHBOARD_CARD = "#151C24";

    private static final String INNER_CARD = "#1C252E";

    private static final String BORDER = "#2A3541";

    // FITCIRCLE LIME GREEN
    private static final String GREEN = "#C6FF00";

    private static final String WHITE = "#FFFFFF";

    private static final String SECONDARY_TEXT = "#F5F7F9";

    private static final String TEXT_WHITE = "#F5F7FA";

    private static final String TEXT_GRAY = "#B8C1CC";


    // =========================================================
    // START
    // =========================================================

    @Override
    public void start(Stage stage) {

        mainStage = stage;

        ownerId =
                AuthControllerClub.getCurrentOwnerId();

        System.out.println(
                "================================"
        );

        System.out.println(
                "CLUB OWNER DASHBOARD"
        );

        System.out.println(
                "Owner ID : " + ownerId
        );

        System.out.println(
                "================================"
        );

        showDashboard();

        mainStage.setTitle(
                "FitCircle - Club Owner Dashboard"
        );

        mainStage.setWidth(1550);

        mainStage.setHeight(800);

        mainStage.show();
    }


    // =========================================================
    // SHOW DASHBOARD
    // =========================================================

    private static void showDashboard() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        root.setLeft(
                createSidebar(root)
        );

        root.setCenter(
                createDashboard()
        );

        Scene scene =
                new Scene(
                        root,
                        1550,
                        800
                );

        mainStage.setScene(scene);
    }


    // =========================================================
    // SIDEBAR
    // =========================================================

    private static VBox createSidebar(
            BorderPane root) {

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
                        "-fx-border-color:#202934;" +
                        "-fx-border-width:0 1 0 0;"
        );


        // =====================================================
        // LOGO
        // =====================================================

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


        // =====================================================
        // TAGLINE
        // =====================================================

        Label tagline =
                new Label(
                        "Fitness • Community • Challenge"
                );

        tagline.setStyle(
                "-fx-font-size:10px;" +
                        "-fx-text-fill:#7F8A97;" +
                        "-fx-padding:0 0 18 15;"
        );


        // =====================================================
        // MENU
        // =====================================================

        VBox menu =
                new VBox(10);

        String[] menuItems = {

                "🏠   Dashboard",

                "👤   Club Owner Details",

                "📢   Announcement",

                "➕   Create Activity",

               // "👥   Member Management",

                "👤   Staff Management",

                "🚪   Logout"
        };


        // =====================================================
        // CREATE MENU BUTTONS
        // =====================================================

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

            setNormalButtonStyle(
                    menuButton
            );


            // =================================================
            // HOVER
            // =================================================

            menuButton.setOnMouseEntered(e -> {

                menuButton.setStyle(

                        "-fx-background-color:#26352F;" +

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

                setNormalButtonStyle(
                        menuButton
                );

            });


            // =================================================
            // CLICK
            // =================================================

            menuButton.setOnAction(e -> {

                String pageName =
                        getPageName(item);


                // =============================================
                // DASHBOARD
                // =============================================

                if (
                        pageName.equals(
                                "Dashboard"
                        )
                ) {

                    root.setCenter(
                            createDashboard()
                    );

                }


                // =============================================
                // CLUB OWNER DETAILS
                // =============================================

                else if (
                        pageName.equals(
                                "Club Owner Details"
                        )
                ) {

                    Club_Owner_Detail detailPage =
                            new Club_Owner_Detail();

                    root.setCenter(
                            detailPage
                                    .createDetailsPage()
                    );

                }


                // =============================================
                // ANNOUNCEMENT
                // =============================================

                else if (
                        pageName.equals(
                                "Announcement"
                        )
                ) {

                    root.setCenter(
                            Club_Announcement
                                    .createAnnouncementUI()
                    );

                }


                // =============================================
                // CREATE ACTIVITY
                // =============================================

                else if (
                        pageName.equals(
                                "Create Activity"
                        )
                ) {

                    Club_CreateActivity activityView =
                            new Club_CreateActivity();

                    // IMPORTANT:
                    // Give the activity the current owner ID.

                    activityView.setCreatedBy(
                            ownerId
                    );

                    // Use owner ID as fallback club ID
                    // because current authentication
                    // provides owner ID.

                    activityView.setClubId(
                            ownerId
                    );

                    // After activity creation,
                    // refresh dashboard data.

                    activityView.setOnActivityCreated(
                            () -> {

                                System.out.println(
                                        "Activity created - dashboard refresh requested."
                                );

                            }
                    );

                    root.setCenter(
                            activityView
                                    .createActivityUI()
                    );

                }


                // =============================================
                // MEMBER MANAGEMENT
                // =============================================

                else if (
                        pageName.equals(
                                "Member Management"
                        )
                ) {

                    Member_management memberView =
                            new Member_management();

                    root.setCenter(
                            memberView
                                    .createMemberManagementUI()
                    );

                }


                // =============================================
                // STAFF MANAGEMENT
                // =============================================

                else if (
                        pageName.equals(
                                "Staff Management"
                        )
                ) {

                    root.setCenter(
                            Staff_management
                                    .createPage()
                    );

                }


                // =============================================
                // LOGOUT
                // =============================================

                else if (
                        pageName.equals(
                                "Logout"
                        )
                ) {

                    Login login =
                            new Login();

                    login.start(
                            mainStage
                    );

                }

            });

            menu.getChildren().add(
                    menuButton
            );
        }


        // =====================================================
        // ADD SIDEBAR
        // =====================================================

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


    // =========================================================
    // NORMAL SIDEBAR BUTTON STYLE
    // =========================================================

    private static void setNormalButtonStyle(
            Button button) {

        button.setStyle(

                "-fx-background-color:" +
                        SIDEBAR_CARD + ";" +

                        "-fx-text-fill:#C5CDD7;" +

                        "-fx-font-size:14px;" +

                        "-fx-background-radius:11px;" +

                        "-fx-border-color:" +
                        SIDEBAR_BORDER + ";" +

                        "-fx-border-radius:11px;" +

                        "-fx-cursor:hand;"
        );
    }


    // =========================================================
    // GET PAGE NAME
    // =========================================================

    private static String getPageName(
            String item) {

        return item

                .replace(
                        "🏠   ",
                        ""
                )

                .replace(
                        "👤   ",
                        ""
                )

                .replace(
                        "📢   ",
                        ""
                )

                .replace(
                        "➕   ",
                        ""
                )

                .replace(
                        "👥   ",
                        ""
                )

                .replace(
                        "🚪   ",
                        ""
                );
    }


    // =========================================================
    // LOAD DASHBOARD DATA
    // =========================================================

    private static void loadDashboardData() {

        if (
                ownerId == null ||
                        ownerId.trim().isEmpty()
        ) {

            System.out.println(
                    "Club Owner ID is missing."
            );

            return;
        }


        try {

            // =================================================
            // EVENTS
            // =================================================

            ownerEvents =
                    eventDAO
                            .getEventsByOwner(
                                    ownerId
                            );

            if (ownerEvents == null) {

                ownerEvents =
                        new ArrayList<>();

            }


            upcomingEvents =
                    ownerEvents.size();


            // =================================================
            // FIND CLUB ID
            // =================================================

            String clubId =
                    findClubId();


            // =================================================
            // MEMBERS
            // =================================================

            if (
                    clubId != null &&
                            !clubId.trim().isEmpty()
            ) {

                membershipList =
                        getMembershipsByClub(
                                clubId
                        );

                totalMembers =
                        membershipList.size();

                participationList =
                        participationDAO
                                .getParticipationsByClub(
                                        clubId
                                );

            } else {

                membershipList =
                        new ArrayList<>();

                participationList =
                        new ArrayList<>();

                totalMembers = 0;
            }


            // =================================================
            // ACTIVE ACTIVITIES
            // =================================================

            activeActivities =
                    getActiveActivityCount();


            // =================================================
            // ENGAGEMENT
            // =================================================

            monthlyEngagement =
                    calculateEngagement();


            // =================================================
            // DEBUG
            // =================================================

            System.out.println(
                    "--------------------------------"
            );

            System.out.println(
                    "Dashboard loaded."
            );

            System.out.println(
                    "Owner ID : " +
                            ownerId
            );

            System.out.println(
                    "Total Members : " +
                            totalMembers
            );

            System.out.println(
                    "Upcoming Events : " +
                            upcomingEvents
            );

            System.out.println(
                    "Active Activities : " +
                            activeActivities
            );

            System.out.println(
                    "Monthly Engagement : " +
                            monthlyEngagement
            );

            System.out.println(
                    "--------------------------------"
            );


        } catch (Exception e) {

            System.out.println(
                    "Error loading dashboard data."
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // FIND CLUB ID
    // =========================================================

    private static String findClubId() {

        // First try UserEvent

        if (
                ownerEvents != null &&
                        !ownerEvents.isEmpty()
        ) {

            for (UserEvent event :
                    ownerEvents) {

                if (event == null) {
                    continue;
                }

                String clubId =
                        event.getClubId();

                if (
                        clubId != null &&
                                !clubId.trim().isEmpty()
                ) {

                    return clubId;
                }
            }
        }


        // Then try ClubOwnerActivities

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(
                            "ClubOwnerActivities"
                    )
                            .whereEqualTo(
                                    "createdBy",
                                    ownerId
                            )
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                            snapshot.getDocuments()
            ) {

                String clubId =
                        doc.getString(
                                "clubId"
                        );

                if (
                        clubId != null &&
                                !clubId.trim().isEmpty()
                ) {

                    return clubId;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Unable to find club ID."
            );
        }


        return "";
    }


    // =========================================================
    // GET MEMBERS BY CLUB
    // =========================================================

    private static List<UserClubMembership>
    getMembershipsByClub(
            String clubId) {

        List<UserClubMembership> list =
                new ArrayList<>();

        if (
                clubId == null ||
                        clubId.trim().isEmpty()
        ) {

            return list;
        }


        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(
                            "UserClubMembership"
                    )
                            .whereEqualTo(
                                    "clubId",
                                    clubId
                            )
                            .get();

            QuerySnapshot snapshot =
                    future.get();


            for (
                    DocumentSnapshot doc :
                            snapshot.getDocuments()
            ) {

                UserClubMembership membership =
                        doc.toObject(
                                UserClubMembership.class
                        );

                if (
                        membership != null
                ) {

                    list.add(
                            membership
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting club members."
            );

            e.printStackTrace();
        }


        return list;
    }


    // =========================================================
    // ACTIVE ACTIVITY COUNT
    // =========================================================

    private static int getActiveActivityCount() {

        int count = 0;


        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(
                            "ClubOwnerActivities"
                    )
                            .whereEqualTo(
                                    "createdBy",
                                    ownerId
                            )
                            .whereEqualTo(
                                    "status",
                                    "Active"
                            )
                            .get();


            QuerySnapshot snapshot =
                    future.get();


            count =
                    snapshot
                            .getDocuments()
                            .size();


        } catch (Exception e) {

            System.out.println(
                    "Error getting active activities."
            );

            e.printStackTrace();
        }


        return count;
    }


    // =========================================================
    // ENGAGEMENT CALCULATION
    // =========================================================

    private static double calculateEngagement() {

        if (
                participationList == null ||
                        participationList.isEmpty()
        ) {

            return 0;
        }


        int totalParticipants = 0;


        for (
                MemberParticipation participation :
                participationList
        ) {

            if (participation != null) {

                totalParticipants +=
                        participation
                                .getParticipants();
            }
        }


        if (totalMembers <= 0) {

            return 0;
        }


        double percentage =
                (
                        (double) totalParticipants /
                                (totalMembers * 7.0)
                )
                        * 100.0;


        if (percentage > 100) {

            percentage = 100;
        }


        if (percentage < 0) {

            percentage = 0;
        }


        return percentage;
    }


    // =========================================================
    // DASHBOARD
    // =========================================================

    private static ScrollPane createDashboard() {

        loadDashboardData();


        // =====================================================
        // TITLE
        // =====================================================

        Label welcome =
                new Label(
                        "Welcome back, Club Owner!"
                );

        welcome.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        welcome.setTextFill(
                Color.WHITE
        );


        Label subtitle =
                new Label(
                        "Here's what's happening with your club today."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        subtitle.setTextFill(
                Color.web(
                        SECONDARY_TEXT
                )
        );


        VBox titleBox =
                new VBox(
                        5,
                        welcome,
                        subtitle
                );


        // =====================================================
        // STATISTICS
        // =====================================================

        HBox statistics =
                new HBox(12);


        statistics.getChildren().addAll(

                createStatCard(

                        "TOTAL MEMBERS",

                        String.valueOf(
                                totalMembers
                        ),

                        "Club members",

                        "👥",

                        GREEN
                ),


                createStatCard(

                        "UPCOMING EVENTS",

                        String.valueOf(
                                upcomingEvents
                        ),

                        "Upcoming events",

                        "▣",

                        GREEN
                ),


                createStatCard(

                        "ACTIVE ACTIVITIES",

                        String.valueOf(
                                activeActivities
                        ),

                        "Currently active",

                        "🏃",

                        GREEN
                ),


                createStatCard(

                        "MONTHLY ENGAGEMENT",

                        String.format(
                                "%.0f%%",
                                monthlyEngagement
                        ),

                        "This month",

                        "📊",

                        GREEN
                )
        );


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        VBox quickActions =
                createQuickActions();


        // =====================================================
        // UPCOMING EVENTS
        // =====================================================

        VBox eventsBox =
                createUpcomingEvents();


        HBox topContent =
                new HBox(12);

        HBox.setHgrow(
                quickActions,
                Priority.ALWAYS
        );

        topContent.getChildren().addAll(
                quickActions,
                eventsBox
        );


        // =====================================================
        // PARTICIPATION CHART
        // =====================================================

        VBox chartBox =
                createParticipationChart();


        // =====================================================
        // RECENT ACTIVITIES
        // =====================================================

        VBox recentActivities =
                createRecentActivities();


        HBox bottomContent =
                new HBox(12);

        HBox.setHgrow(
                chartBox,
                Priority.ALWAYS
        );

        bottomContent.getChildren().addAll(
                chartBox,
                recentActivities
        );


        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(14);

        content.setPadding(
                new Insets(18)
        );

        content.setMinWidth(
                900
        );

        content.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );


        com.flexforce.view.components.CarouselSlider carouselSlider = new com.flexforce.view.components.CarouselSlider();

        content.getChildren().addAll(

                titleBox,

                carouselSlider,

                statistics,

                topContent,

                bottomContent
        );


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll =
                new ScrollPane(
                        content
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setFitToHeight(
                false
        );

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setPannable(
                true
        );

        scroll.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-border-color:transparent;"
        );


        return scroll;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private static VBox createStatCard(

            String title,

            String number,

            String bottom,

            String icon,

            String iconColor
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(

                "-fx-text-fill:" +
                        SECONDARY_TEXT + ";" +

                        "-fx-font-size:13px;" +

                        "-fx-font-weight:bold;"
        );


        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(25)
        );

        iconLabel.setTextFill(
                Color.web(
                        iconColor
                )
        );

        iconLabel.setMinSize(
                38,
                38
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setStyle(

                "-fx-background-color:#202B35;" +

                        "-fx-background-radius:10;"
        );


        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        HBox top =
                new HBox(
                        titleLabel,
                        space,
                        iconLabel
                );


        Label numberLabel =
                new Label(number);

        numberLabel.setStyle(

                "-fx-text-fill:" +
                        WHITE + ";" +

                        "-fx-font-size:27px;" +

                        "-fx-font-weight:bold;"
        );


        Label bottomLabel =
                new Label(bottom);

        bottomLabel.setWrapText(
                true
        );

        bottomLabel.setStyle(

                "-fx-text-fill:" +
                        GREEN + ";" +

                        "-fx-font-size:11px;"
        );


        VBox card =
                new VBox(9);

        card.setPrefWidth(
                210
        );

        card.setMinWidth(
                210
        );

        card.setMinHeight(
                115
        );

        card.setPadding(
                new Insets(13)
        );

        card.setStyle(

                "-fx-background-color:" +
                        DASHBOARD_CARD + ";" +

                        "-fx-background-radius:10;" +

                        "-fx-border-color:" +
                        BORDER + ";" +

                        "-fx-border-radius:10;"
        );


        card.getChildren().addAll(

                top,

                numberLabel,

                bottomLabel
        );


        return card;
    }


    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    private static VBox createQuickActions() {

        Label title =
                sectionTitle(
                        "Quick Actions"
                );


        Button createEvent =
                actionButton(
                        "⊕  Create Event",
                        true
                );


        Button announcement =
                actionButton(
                        "⚑  Post Announcement",
                        false
                );


        Button members =
                actionButton(
                        "♙  Manage Members",
                        false
                );


        Button activity =
                actionButton(
                        "⚒  Create Activity",
                        false
                );


        // =====================================================
        // CREATE EVENT
        // =====================================================

        createEvent.setOnAction(
                e -> {

                    // Your project currently does not provide
                    // a confirmed Club Create Event page here.
                    // Therefore we open Announcement instead of
                    // creating a fake page.

                    rootToPage(
                            Club_Announcement
                                    .createAnnouncementUI()
                    );
                }
        );


        // =====================================================
        // ANNOUNCEMENT
        // =====================================================

        announcement.setOnAction(
                e -> {

                    rootToPage(
                            Club_Announcement
                                    .createAnnouncementUI()
                    );
                }
        );


        // =====================================================
        // MEMBERS
        // =====================================================

        members.setOnAction(
                e -> {

                    Member_management memberView =
                            new Member_management();

                    rootToPage(
                            memberView
                                    .createMemberManagementUI()
                    );
                }
        );


        // =====================================================
        // ACTIVITY
        // =====================================================

        activity.setOnAction(
                e -> {

                    Club_CreateActivity activityView =
                            new Club_CreateActivity();

                    activityView.setCreatedBy(
                            ownerId
                    );

                    activityView.setClubId(
                            ownerId
                    );

                    activityView.setOnActivityCreated(
                            () -> {

                                System.out.println(
                                        "Activity created successfully."
                                );

                            }
                    );

                    rootToPage(
                            activityView
                                    .createActivityUI()
                    );
                }
        );


        HBox row1 =
                new HBox(
                        10,
                        createEvent,
                        announcement
                );


        HBox row2 =
                new HBox(
                        10,
                        members,
                        activity
                );


        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(13)
        );

        box.setMinWidth(
                450
        );

        box.setStyle(
                cardStyle()
        );


        box.getChildren().addAll(

                title,

                row1,

                row2
        );


        HBox.setHgrow(
                box,
                Priority.ALWAYS
        );


        return box;
    }


    // =========================================================
    // PAGE HELPER
    // =========================================================

    private static void rootToPage(
            Node page) {

        if (
                mainStage == null ||
                        mainStage.getScene() == null
        ) {

            return;
        }


        BorderPane root =
                (BorderPane)
                        mainStage
                                .getScene()
                                .getRoot();


        root.setCenter(page);
    }


    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private static Button actionButton(

            String text,

            boolean primary
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(
                35
        );

        button.setPrefWidth(
                140
        );


        if (primary) {

            button.setStyle(

                    "-fx-background-color:" +
                            GREEN + ";" +

                            "-fx-text-fill:#000000;" +

                            "-fx-font-size:11px;" +

                            "-fx-font-weight:bold;" +

                            "-fx-background-radius:6;"
            );

        } else {

            button.setStyle(

                    "-fx-background-color:" +
                            INNER_CARD + ";" +

                            "-fx-text-fill:" +
                            WHITE + ";" +

                            "-fx-font-size:11px;" +

                            "-fx-border-color:" +
                            BORDER + ";" +

                            "-fx-border-radius:6;" +

                            "-fx-background-radius:6;"
            );
        }


        return button;
    }


    // =========================================================
    // UPCOMING EVENTS
    // =========================================================

    private static VBox createUpcomingEvents() {

        Label title =
                sectionTitle(
                        "Upcoming Events"
                );


        Button viewAll =
                new Button(
                        "View All"
                );

        viewAll.setStyle(

                "-fx-text-fill:" +
                        GREEN + ";" +

                        "-fx-background-color:transparent;"
        );


        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        HBox heading =
                new HBox(
                        title,
                        space,
                        viewAll
                );


        heading.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox box =
                new VBox(9);

        box.setPrefWidth(
                360
        );

        box.setMinWidth(
                360
        );

        box.setPadding(
                new Insets(13)
        );

        box.setStyle(
                cardStyle()
        );


        box.getChildren().add(
                heading
        );


        if (
                ownerEvents == null ||
                        ownerEvents.isEmpty()
        ) {

            Label empty =
                    new Label(
                            "No upcoming events."
                    );

            empty.setStyle(

                    "-fx-text-fill:" +
                            SECONDARY_TEXT + ";" +

                            "-fx-font-size:13px;"
            );


            box.getChildren().add(
                    empty
            );


            return box;
        }


        int count =
                Math.min(
                        3,
                        ownerEvents.size()
                );


        for (
                int i = 0;
                i < count;
                i++
        ) {

            UserEvent event =
                    ownerEvents.get(i);


            if (event == null) {
                continue;
            }


            String dateTime =
                    safe(
                            event.getDate()
                    )
                            + "  "
                            + safe(
                            event.getTime()
                    );


            VBox item =
                    eventItem(

                            safe(
                                    event.getTitle()
                            ),

                            dateTime,

                            "Event",

                            event.getImage()
                    );


            box.getChildren().add(
                    item
            );
        }


        return box;
    }


    // =========================================================
    // EVENT ITEM
    // =========================================================

    private static VBox eventItem(

            String name,

            String date,

            String info,

            String imagePath
    ) {

        ImageView image = null;


        if (
                imagePath != null &&
                        !imagePath.trim().isEmpty()
        ) {

            image =
                    createImage(
                            imagePath,
                            65,
                            65
                    );
        }


        Node imageNode;


        if (image != null) {

            imageNode = image;

        } else {

            Label placeholder =
                    new Label(
                            "EVENT"
                    );

            placeholder.setPrefSize(
                    65,
                    65
            );

            placeholder.setAlignment(
                    Pos.CENTER
            );

            placeholder.setStyle(

                    "-fx-background-color:" +
                            INNER_CARD + ";" +

                            "-fx-background-radius:8;" +

                            "-fx-text-fill:" +
                            SECONDARY_TEXT + ";"
            );

            imageNode =
                    placeholder;
        }


        Label eventName =
                new Label(
                        name.isEmpty()
                                ? "Untitled Event"
                                : name
                );

        eventName.setStyle(

                "-fx-text-fill:" +
                        WHITE + ";" +

                        "-fx-font-size:11px;" +

                        "-fx-font-weight:bold;"
        );


        Label dateLabel =
                new Label(date);

        dateLabel.setStyle(

                "-fx-text-fill:" +
                        SECONDARY_TEXT + ";" +

                        "-fx-font-size:13px;"
        );


        Label infoLabel =
                new Label(info);

        infoLabel.setStyle(

                "-fx-text-fill:" +
                        GREEN + ";" +

                        "-fx-font-size:13px;"
        );


        VBox details =
                new VBox(

                        2,

                        eventName,

                        dateLabel,

                        infoLabel
                );


        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        Button view =
                new Button(
                        "View"
                );

        view.setStyle(

                "-fx-background-color:transparent;" +

                        "-fx-text-fill:" +
                        GREEN + ";" +

                        "-fx-font-weight:bold;"
        );


        HBox row =
                new HBox(

                        10,

                        imageNode,

                        details,

                        space,

                        view
                );


        row.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox item =
                new VBox(row);

        item.setPadding(
                new Insets(8)
        );

        item.setStyle(

                "-fx-background-color:" +
                        INNER_CARD + ";" +

                        "-fx-background-radius:7;"
        );


        return item;
    }


    // =========================================================
    // PARTICIPATION CHART
    // =========================================================

    private static VBox createParticipationChart() {

        Label title =
                sectionTitle(
                        "Member Participation (7 Days)"
                );


        CategoryAxis xAxis =
                new CategoryAxis();


        NumberAxis yAxis =
                new NumberAxis();


        xAxis.setLabel(
                "Day"
        );


        yAxis.setLabel(
                "Participants"
        );


        BarChart<String, Number> chart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );


        chart.setLegendVisible(
                false
        );


        chart.setAnimated(
                false
        );


        chart.setCategoryGap(
                8
        );


        chart.setBarGap(
                3
        );


        chart.setPrefHeight(
                290
        );


        XYChart.Series<String, Number> series =
                new XYChart.Series<>();


        int mon = 0;

        int tue = 0;

        int wed = 0;

        int thu = 0;

        int fri = 0;

        int sat = 0;

        int sun = 0;


        if (
                participationList != null
        ) {

            for (
                    MemberParticipation participation :
                    participationList
            ) {

                if (participation == null) {
                    continue;
                }


                int participants =
                        participation
                                .getParticipants();


                String day =
                        safe(
                                participation.getDay()
                        )
                                .toLowerCase();


                switch (day) {

                    case "mon":

                    case "monday":

                        mon += participants;

                        break;


                    case "tue":

                    case "tuesday":

                        tue += participants;

                        break;


                    case "wed":

                    case "wednesday":

                        wed += participants;

                        break;


                    case "thu":

                    case "thursday":

                        thu += participants;

                        break;


                    case "fri":

                    case "friday":

                        fri += participants;

                        break;


                    case "sat":

                    case "saturday":

                        sat += participants;

                        break;


                    case "sun":

                    case "sunday":

                        sun += participants;

                        break;
                }
            }
        }


        series.getData().add(
                new XYChart.Data<>(
                        "Mon",
                        mon
                )
        );


        series.getData().add(
                new XYChart.Data<>(
                        "Tue",
                        tue
                )
        );


        series.getData().add(
                new XYChart.Data<>(
                        "Wed",
                        wed
                )
        );


        series.getData().add(
                new XYChart.Data<>(
                        "Thu",
                        thu
                )
        );


        series.getData().add(
                new XYChart.Data<>(
                        "Fri",
                        fri
                )
        );


        series.getData().add(
                new XYChart.Data<>(
                        "Sat",
                        sat
                )
        );


        series.getData().add(
                new XYChart.Data<>(
                        "Sun",
                        sun
                )
        );


        chart.getData().add(
                series
        );


        VBox box =
                new VBox(

                        8,

                        title,

                        chart
                );


        box.setPadding(
                new Insets(13)
        );


        box.setMinWidth(
                600
        );


        box.setStyle(
                cardStyle()
        );


        HBox.setHgrow(
                box,
                Priority.ALWAYS
        );


        return box;
    }


    // =========================================================
    // RECENT ACTIVITIES
    // =========================================================

    private static VBox createRecentActivities() {

        Label title =
                sectionTitle(
                        "Recent Activities"
                );


        VBox box =
                new VBox(10);


        box.setPrefWidth(
                300
        );


        box.setMinWidth(
                300
        );


        box.setPadding(
                new Insets(13)
        );


        box.setStyle(
                cardStyle()
        );


        box.getChildren().add(
                title
        );


        List<DocumentSnapshot> activities =
                getRecentActivities();


        if (
                activities.isEmpty()
        ) {

            box.getChildren().add(

                    activityItem(

                            "ℹ",

                            "No recent activities.",

                            "No data"
                    )
            );


            return box;
        }


        int count =
                Math.min(
                        5,
                        activities.size()
                );


        for (
                int i = 0;
                i < count;
                i++
        ) {

            DocumentSnapshot doc =
                    activities.get(i);


            String name =
                    getString(
                            doc,
                            "activityName",
                            "Activity"
                    );


            String type =
                    getString(
                            doc,
                            "activityType",
                            ""
                    );


            String difficulty =
                    getString(
                            doc,
                            "difficulty",
                            ""
                    );


            String date =
                    getString(
                            doc,
                            "date",
                            ""
                    );


            String value =
                    type;


            if (
                    !difficulty.isEmpty()
            ) {

                if (
                        !value.isEmpty()
                ) {

                    value +=
                            " • ";
                }


                value +=
                        difficulty;
            }


            box.getChildren().add(

                    activityItem(

                            "🏃",

                            "Activity created: " +
                                    name,

                            date
                    )
            );
        }


        return box;
    }


    // =========================================================
    // GET RECENT ACTIVITIES
    // =========================================================

    private static List<DocumentSnapshot>
    getRecentActivities() {

        List<DocumentSnapshot> list =
                new ArrayList<>();


        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(
                            "ClubOwnerActivities"
                    )
                            .whereEqualTo(
                                    "createdBy",
                                    ownerId
                            )
                            .get();


            QuerySnapshot snapshot =
                    future.get();


            list.addAll(
                    snapshot.getDocuments()
            );


        } catch (Exception e) {

            System.out.println(
                    "Error loading recent activities."
            );

            e.printStackTrace();
        }


        return list;
    }


    // =========================================================
    // GET STRING
    // =========================================================

    private static String getString(

            DocumentSnapshot document,

            String field,

            String defaultValue
    ) {

        String value =
                document.getString(field);


        if (
                value == null ||
                        value.trim().isEmpty()
        ) {

            return defaultValue;
        }


        return value;
    }


    // =========================================================
    // ACTIVITY ITEM
    // =========================================================

    private static VBox activityItem(

            String icon,

            String text,

            String time
    ) {

        Label iconLabel =
                new Label(icon);


        iconLabel.setFont(
                Font.font(18)
        );


        iconLabel.setTextFill(
                Color.web(
                        GREEN
                )
        );


        Label activityText =
                new Label(text);


        activityText.setWrapText(
                true
        );


        activityText.setStyle(

                "-fx-text-fill:" +
                        WHITE + ";" +

                        "-fx-font-size:14px;"
        );


        Label timeLabel =
                new Label(time);


        timeLabel.setStyle(

                "-fx-text-fill:" +
                        GREEN + ";" +

                        "-fx-font-size:12px;"
        );


        VBox textBox =
                new VBox(

                        2,

                        activityText,

                        timeLabel
                );


        HBox row =
                new HBox(

                        8,

                        iconLabel,

                        textBox
                );


        return new VBox(row);
    }


    // =========================================================
    // SECTION TITLE
    // =========================================================

    private static Label sectionTitle(
            String text) {

        Label label =
                new Label(text);


        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );


        label.setTextFill(
                Color.WHITE
        );


        return label;
    }


    // =========================================================
    // CARD STYLE
    // =========================================================

    private static String cardStyle() {

        return

                "-fx-background-color:" +
                        DASHBOARD_CARD + ";" +

                        "-fx-background-radius:10;" +

                        "-fx-border-color:" +
                        BORDER + ";" +

                        "-fx-border-radius:10;";
    }


    // =========================================================
    // IMAGE
    // =========================================================

    private static ImageView createImage(

            String imagePath,

            double width,

            double height
    ) {

        try {

            String path =
                    "/" +
                            imagePath.replace(
                                    "\\",
                                    "/"
                            );


            var resource =
                    ClubOwnerDashboard.class
                            .getResource(path);


            if (
                    resource == null
            ) {

                return null;
            }


            Image image =
                    new Image(
                            resource
                                    .toExternalForm()
                    );


            ImageView imageView =
                    new ImageView(
                            image
                    );


            imageView.setFitWidth(
                    width
            );


            imageView.setFitHeight(
                    height
            );


            imageView.setPreserveRatio(
                    false
            );


            return imageView;


        } catch (Exception e) {

            return null;
        }
    }


    // =========================================================
    // SAFE STRING
    // =========================================================

    private static String safe(
            String value) {

        if (
                value == null ||
                        value.trim().isEmpty()
        ) {

            return "";
        }


        return value;
    }

    
}