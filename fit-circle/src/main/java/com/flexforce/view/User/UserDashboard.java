package com.flexforce.view.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.controller.UserLeaderboardController;
import com.flexforce.dao.UserInfoDAO;
import com.flexforce.model.user.UserInfo;
import com.flexforce.model.user.UserLeaderboard;
import com.flexforce.view.About_Us;
import com.flexforce.view.ExplorerClubPage;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserDashboard {

    // ============================================================
    // DAO / CONTROLLER
    // ============================================================

    private final UserInfoDAO userInfoDAO =
            new UserInfoDAO();

    private final UserLeaderboardController leaderboardController =
            new UserLeaderboardController();

    // ============================================================
    // USER DATA
    // ============================================================

    private String currentUserName = "User";
    private String loggedInUserId;

    // ============================================================
    // DYNAMIC STAT LABELS
    // ============================================================

    private Label streakValue;
    private Label pointsValue;
    private Label rankValue;
    private Label levelValue;

    private Label streakDescription;
    private Label pointsDescription;
    private Label rankDescription;
    private Label levelDescription;

    // ============================================================
    // COLORS
    // ============================================================

    private final String BACKGROUND = "#0B0F14";
    private final String SIDEBAR = "#111820";

    private final String SIDEBAR_CARD = "#1A232D";
    private final String SIDEBAR_BORDER = "#2A3541";

    private final String CARD = "#252E38";
    private final String CARD_BORDER = "#394552";

    private final String GREEN = "#c6ff00";

    private final String TEXT_WHITE = "#F5F7FA";
    private final String TEXT_GRAY = "#B8C1CC";

    // ============================================================
    // MAIN SCENE
    // ============================================================

    public Scene mainScene(Runnable callbackAction) {

        // --------------------------------------------------------
        // CURRENT USER ID
        // --------------------------------------------------------

        loggedInUserId =
                AuthControllerlogin.getCurrentUserId();

        System.out.println(
                "Dashboard Current User ID = "
                        + loggedInUserId
        );

        // --------------------------------------------------------
        // GET USER NAME
        // --------------------------------------------------------

        if (loggedInUserId != null
                && !loggedInUserId.trim().isEmpty()) {

            UserInfo userInfo =
                    userInfoDAO.getUserInfo(loggedInUserId);

            if (userInfo != null
                    && userInfo.getName() != null
                    && !userInfo.getName().trim().isEmpty()) {

                currentUserName =
                        userInfo.getName().trim();
            }
        }

        // --------------------------------------------------------
        // BORDER PANE
        // --------------------------------------------------------

        BorderPane borderPane =
                new BorderPane();

        borderPane.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        // --------------------------------------------------------
        // SIDEBAR
        // --------------------------------------------------------

        VBox sidebar =
                createSidebar(
                        borderPane,
                        callbackAction
                );

        borderPane.setLeft(sidebar);

        // --------------------------------------------------------
        // DASHBOARD
        // --------------------------------------------------------

        borderPane.setCenter(
                createDashboard(borderPane)
        );

        // --------------------------------------------------------
        // SCENE
        // --------------------------------------------------------

        Scene scene =
                new Scene(
                        borderPane,
                        1550,
                        800
                );

        return scene;
    }

    // ============================================================
    // SIDEBAR
    // ============================================================

    private VBox createSidebar(
            BorderPane borderPane,
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
                        "-fx-border-color:#202934;" +
                        "-fx-border-width:0 1 0 0;"
        );

        // --------------------------------------------------------
        // LOGO
        // --------------------------------------------------------

        Label logo =
                new Label("FITCIRCLE");

        logo.setStyle(
                "-fx-font-size:25px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-padding:0 0 0 15;"
        );

        Label tagline =
                new Label(
                        "Fitness • Community • Challenge"
                );

        tagline.setStyle(
                "-fx-font-size:10px;" +
                        "-fx-text-fill:#7F8A97;" +
                        "-fx-padding:0 0 18 15;"
        );

        // --------------------------------------------------------
        // MENU
        // --------------------------------------------------------

        VBox menu =
                new VBox(10);

        String[] menuItems = {

                "🏠   Dashboard",

                "🔍   Explore Clubs",

                "👥   My Clubs",

                "🔥   Challenges",

                "💬   Community",

              //  "🏆   Leaderboard",

                "👤   Profile",

                "ℹ️   About Us",

               // "🔔   Notifications",

                "🚪   Logout"
        };

        // --------------------------------------------------------
        // MENU BUTTONS
        // --------------------------------------------------------

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

            menuButton.setStyle(
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

            // ----------------------------------------------------
            // HOVER
            // ----------------------------------------------------

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

                menuButton.setStyle(
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
            });

            // ----------------------------------------------------
            // CLICK
            // ----------------------------------------------------

            menuButton.setOnAction(e -> {

                String pageName =
                        item
                                .replace("🏠   ", "")
                                .replace("🔍   ", "")
                                .replace("👥   ", "")
                                .replace("🔥   ", "")
                                .replace("💬   ", "")
                                .replace("🏆   ", "")
                                .replace("👤   ", "")

                                .replace("ℹ️   ", "")
                                .replace("🔔   ", "")
                                .replace("🚪   ", "");

                // ------------------------------------------------
                // DASHBOARD
                // ------------------------------------------------

                if (pageName.equals("Dashboard")) {

                    borderPane.setCenter(
                            createDashboard(borderPane)
                    );
                }

                // ------------------------------------------------
                // EXPLORE CLUBS
                // ------------------------------------------------

                else if (
                        pageName.equals("Explore Clubs")) {

                    ExplorerClubPage explorer =
                            new ExplorerClubPage();

                    explorer.setOnClubSelected(club -> {
                        borderPane.setCenter(
                                com.flexforce.view.User.Userclubdet.createPage(club, () -> {
                                    borderPane.setCenter(explorer.createScrollableContent());
                                })
                        );
                    });

                    borderPane.setCenter(
                            explorer.createScrollableContent()
                    );
                }

                // ------------------------------------------------
                // MY CLUBS
                // ------------------------------------------------

                else if (
                        pageName.equals("My Clubs")) {

                    MyClubs myClubs =
                            new MyClubs();

                    myClubs.setOnClubSelected(club -> {
                        borderPane.setCenter(
                                com.flexforce.view.User.Userclubdet.createPage(club, () -> {
                                    ScrollPane backPane = new ScrollPane(myClubs.createContent());
                                    backPane.setFitToWidth(true);
                                    backPane.setFitToHeight(false);
                                    backPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                                    backPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                                    backPane.setPannable(true);
                                    backPane.setStyle("-fx-background-color:#0f1010;-fx-background:#0f1010;");
                                    borderPane.setCenter(backPane);
                                })
                        );
                    });

                    myClubs.setOnBrowseMore(() -> {
                        ExplorerClubPage explorer = new ExplorerClubPage();
                        explorer.setOnClubSelected(club -> {
                            borderPane.setCenter(
                                    com.flexforce.view.User.Userclubdet.createPage(club, () -> {
                                        borderPane.setCenter(explorer.createScrollableContent());
                                    })
                            );
                        });
                        borderPane.setCenter(explorer.createScrollableContent());
                    });

                    ScrollPane scrollPane =
                            new ScrollPane(
                                    myClubs.createContent()
                            );

                    scrollPane.setFitToWidth(true);
                    scrollPane.setFitToHeight(false);

                    scrollPane.setHbarPolicy(
                            ScrollPane.ScrollBarPolicy.NEVER
                    );

                    scrollPane.setVbarPolicy(
                            ScrollPane.ScrollBarPolicy.NEVER
                    );

                    scrollPane.setPannable(true);

                    scrollPane.setStyle(
                            "-fx-background-color:#0f1010;" +
                                    "-fx-background:#0f1010;"
                    );

                    borderPane.setCenter(
                            scrollPane
                    );
                }

                // ------------------------------------------------
                // CHALLENGES
                // ------------------------------------------------

                else if (
                        pageName.equals("Challenges")) {

                    ChallengesPage challenges =
                            new ChallengesPage();

                    borderPane.setCenter(
                            challenges.createContent()
                    );
                }

                // ------------------------------------------------
                // COMMUNITY
                // ------------------------------------------------

                else if (
                        pageName.equals("Community")) {

                    CommunityPage community =
                            new CommunityPage();

                    community.setOnDiscoverMore(() -> {
                        ExplorerClubPage explorer = new ExplorerClubPage();
                        explorer.setOnClubSelected(club -> {
                            borderPane.setCenter(
                                    com.flexforce.view.User.Userclubdet.createPage(club, () -> {
                                        borderPane.setCenter(explorer.createScrollableContent());
                                    })
                            );
                        });
                        borderPane.setCenter(explorer.createScrollableContent());
                    });

                    ScrollPane scrollPane =
                            new ScrollPane();

                    scrollPane.setContent(
                            community.createContent()
                    );

                    scrollPane.setFitToWidth(true);

                    scrollPane.setHbarPolicy(
                            ScrollPane.ScrollBarPolicy.NEVER
                    );

                    scrollPane.setVbarPolicy(
                            ScrollPane.ScrollBarPolicy.NEVER
                    );

                    scrollPane.setStyle(
                            "-fx-background:#101111;" +
                                    "-fx-background-color:#101111;"
                    );

                    borderPane.setCenter(
                            scrollPane
                    );
                }

                // ------------------------------------------------
                // LEADERBOARD
                // ------------------------------------------------

                else if (
                        pageName.equals("Leaderboard")) {

                    Leaderboard leaderboard =
                            new Leaderboard();

                    borderPane.setCenter(
                            leaderboard.createContent()
                    );
                }

                // ------------------------------------------------
                // PROFILE
                // ------------------------------------------------

                else if (
                        pageName.equals("Profile")) {

                    borderPane.setCenter(
                            new User_Profile()
                                    .createProfilePage()
                    );
                }

                // ------------------------------------------------
                // NOTIFICATIONS
                // ------------------------------------------------

                else if (
                        pageName.equals("Notifications")) {

                    User_Notification notification =
                            new User_Notification();

                    borderPane.setCenter(
                            notification.createNotificationPage()
                    );
                }

                // ------------------------------------------------
                // ABOUT US
                // ------------------------------------------------
                else if (
                        pageName.equals("About Us")) {

                About_Us aboutUsPage =
                        new About_Us();

                borderPane.setCenter(
                        aboutUsPage.createContent()
                );
                }

                // ------------------------------------------------
                // LOGOUT
                // ------------------------------------------------

                else if (
                        pageName.equals("Logout")) {

                    if (callbackAction != null) {
                        callbackAction.run();
                    }
                }
            });

            menu.getChildren().add(
                    menuButton
            );
        }

        // --------------------------------------------------------
        // SIDEBAR ADD
        // --------------------------------------------------------

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

    // ============================================================
    // DASHBOARD
    // ============================================================

    private ScrollPane createDashboard(
            BorderPane borderPane) {

        VBox dashboard =
                new VBox(22);

        dashboard.setPadding(
                new Insets(
                        28,
                        38,
                        35,
                        38
                )
        );

        dashboard.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        // ========================================================
        // HEADER
        // ========================================================

        Label title =
                new Label(
                        "Welcome, "
                                + currentUserName
                                + " 👋"
                );

        title.setStyle(
                "-fx-font-size:30px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        TEXT_WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Stay consistent, stay active, "
                                + "and keep moving forward."
                );

        subtitle.setStyle(
                "-fx-font-size:14px;" +
                        "-fx-text-fill:" +
                        TEXT_GRAY + ";"
        );

        VBox heading =
                new VBox(
                        5,
                        title,
                        subtitle
                );

        // ========================================================
        // NOTIFICATION BUTTON
        // ========================================================

        Button notification =
                new Button("🔔");

        notification.setPrefSize(
                45,
                40
        );

        notification.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-text-fill:white;" +
                        "-fx-background-radius:10px;" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:10px;" +
                        "-fx-font-size:16px;" +
                        "-fx-cursor:hand;"
        );

        // ========================================================
        // 🔔 NOTIFICATION CLICK
        // ========================================================

        notification.setOnAction(e -> {

            User_Notification notificationPage =
                    new User_Notification();

            borderPane.setCenter(
                    notificationPage.createNotificationPage()
            );
        });

        // ========================================================
        // USER BUTTON
        // ========================================================

        Button user =
                new Button(
                        "👤  " + currentUserName
                );

        user.setPrefHeight(40);

        user.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-text-fill:white;" +
                        "-fx-background-radius:10px;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:0 18 0 18;" +
                        "-fx-cursor:hand;"
        );

        HBox topRight =
                new HBox(
                        10,
                        notification,
                        user
                );

        topRight.setAlignment(
                Pos.CENTER_RIGHT
        );

        HBox topHeader =
                new HBox(
                        heading,
                        topRight
                );

        topHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                heading,
                Priority.ALWAYS
        );

        // ========================================================
        // HERO
        // ========================================================

        Label bannerTitle =
                new Label(
                        "Your fitness journey starts here 💪"
                );

        bannerTitle.setStyle(
                "-fx-font-size:22px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label bannerText =
                new Label(
                        "Complete today's challenge "
                                + "and earn more points!"
                );

        bannerText.setStyle(
                "-fx-font-size:13px;" +
                        "-fx-text-fill:#DDF9EA;"
        );

        Button challengeButton =
                new Button(
                        "Start Today's Challenge"
                );

        challengeButton.setStyle(
                "-fx-background-color:#181C1B;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8px;" +
                        "-fx-padding:9 18 9 18;" +
                        "-fx-cursor:hand;"
        );

        // ========================================================
        // START TODAY'S CHALLENGE
        // ========================================================

        challengeButton.setOnAction(e -> {

            ChallengesPage challenges =
                    new ChallengesPage();

            borderPane.setCenter(
                    challenges.createContent()
            );
        });

        VBox banner =
                new VBox(
                        8,
                        bannerTitle,
                        bannerText,
                        challengeButton
                );

        banner.setPadding(
                new Insets(22)
        );

        banner.setPrefHeight(145);

        banner.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-background-radius:17px;"
        );

        // ========================================================
        // OVERVIEW
        // ========================================================

       /*  Label overview =
                sectionTitle(
                        "Your Overview"
                );

        HBox stats =
                new HBox(20);*/

        // --------------------------------------------------------
        // DYNAMIC LABELS
        // --------------------------------------------------------

      /*   streakValue =
                new Label("Loading...");

        pointsValue =
                new Label("Loading...");

        rankValue =
                new Label("Loading...");

        levelValue =
                new Label("Loading...");

        streakDescription =
                new Label("Loading...");

        pointsDescription =
                new Label("Loading...");

        rankDescription =
                new Label("Loading...");

        levelDescription =
                new Label("Loading...");

        VBox streakCard =
                createDynamicStatCard(
                        "🔥",
                        "Current Streak",
                        streakValue,
                        streakDescription
                );

        VBox pointsCard =
                createDynamicStatCard(
                        "⭐",
                        "Total Points",
                        pointsValue,
                        pointsDescription
                );

        VBox rankCard =
                createDynamicStatCard(
                        "🏆",
                        "Current Rank",
                        rankValue,
                        rankDescription
                );

        VBox levelCard =
                createDynamicStatCard(
                        "⚡",
                        "Level",
                        levelValue,
                        levelDescription
                );

        stats.getChildren().addAll(
                streakCard,
                pointsCard,
                rankCard,
                levelCard
        );*/

        // ========================================================
        // LOAD DYNAMIC DATA
        // ========================================================

        loadDashboardStats();

        // ========================================================
        // ACTIVITIES
        // ========================================================

        Label activities =
                sectionTitle(
                        "Your Activities"
                );

        HBox middleRow =
                new HBox(22);

        middleRow.getChildren().addAll(

                createChallengeCard(
                        borderPane
                ),

                createEventsCard(),

                createClubsCard(
                        borderPane
                )
        );

        // ========================================================
        // MORE
        // ========================================================

        Label more =
                sectionTitle(
                        "More For You"
                );

        HBox bottomRow =
                new HBox(22);

        bottomRow.getChildren().addAll(

                createLeaderboardCard(),

                createAICard()
        );

        com.flexforce.view.components.CarouselSlider carouselSlider = new com.flexforce.view.components.CarouselSlider();

        // ========================================================
        // ADD EVERYTHING
        // ========================================================

        dashboard.getChildren().addAll(

                topHeader,

                carouselSlider,

                banner,
//===========================================================================================================================
              //  overview,

                //stats,

                activities,

                middleRow,

                more,

                bottomRow
        );

        // ========================================================
        // SCROLL
        // ========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        dashboard
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                        "-fx-background:" +
                        BACKGROUND + ";"
        );

        return scrollPane;
    }

    // ============================================================
    // DYNAMIC DASHBOARD STATS
    // ============================================================

    private void loadDashboardStats() {

        if (loggedInUserId == null
                || loggedInUserId.trim().isEmpty()) {

            setDefaultStats();

            return;
        }

        Task<UserLeaderboard> task =
                new Task<UserLeaderboard>() {

                    @Override
                    protected UserLeaderboard call()
                            throws Exception {

                        return leaderboardController
                                .getUserLeaderboard(
                                        loggedInUserId
                                );
                    }
                };

        task.setOnSucceeded(e -> {

            UserLeaderboard data =
                    task.getValue();

            if (data == null) {

                setDefaultStats();

                return;
            }

            // ----------------------------------------------------
            // STREAK
            // ----------------------------------------------------

            streakValue.setText(
                    data.getStreak()
                            + " days"
            );

            if (data.getStreak() > 0) {

                streakDescription.setText(
                        "Keep it up!"
                );

            } else {

                streakDescription.setText(
                        "Start your streak"
                );
            }

            // ----------------------------------------------------
            // POINTS
            // ----------------------------------------------------

            pointsValue.setText(
                    String.format(
                            "%,d",
                            data.getPoints()
                    )
            );

            pointsDescription.setText(
                    "Total earned points"
            );

            // ----------------------------------------------------
            // RANK
            // ----------------------------------------------------

            if (data.getRank() > 0) {

                rankValue.setText(
                        "#" + data.getRank()
                );

                rankDescription.setText(
                        "Global ranking"
                );

            } else {

                rankValue.setText(
                        "N/A"
                );

                rankDescription.setText(
                        "Not ranked yet"
                );
            }

            // ----------------------------------------------------
            // LEVEL
            // ----------------------------------------------------

            levelValue.setText(
                    String.valueOf(
                            data.getLevel()
                    )
            );

            levelDescription.setText(
                    "Current level"
            );
        });

        task.setOnFailed(e -> {

            System.out.println(
                    "Dashboard stats loading failed"
            );

            if (task.getException() != null) {

                task.getException()
                        .printStackTrace();
            }

            setDefaultStats();
        });

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // ============================================================
    // DEFAULT STATS
    // ============================================================

    private void setDefaultStats() {

        if (streakValue != null) {
            streakValue.setText("0 days");
        }

        if (pointsValue != null) {
            pointsValue.setText("0");
        }

        if (rankValue != null) {
            rankValue.setText("N/A");
        }

        if (levelValue != null) {
            levelValue.setText("1");
        }

        if (streakDescription != null) {
            streakDescription.setText(
                    "Start your streak"
            );
        }

        if (pointsDescription != null) {
            pointsDescription.setText(
                    "No points yet"
            );
        }

        if (rankDescription != null) {
            rankDescription.setText(
                    "Not ranked yet"
            );
        }

        if (levelDescription != null) {
            levelDescription.setText(
                    "Starting level"
            );
        }
    }

    // ============================================================
    // SECTION TITLE
    // ============================================================

    private Label sectionTitle(
            String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size:20px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        TEXT_WHITE + ";"
        );

        return label;
    }

    // ============================================================
    // DYNAMIC STAT CARD
    // ============================================================

    private VBox createDynamicStatCard(
            String icon,
            String title,
            Label valueLabel,
            Label descriptionLabel) {

        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size:23px;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#A7B0BC;"
        );

        valueLabel.setStyle(
                "-fx-font-size:22px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        descriptionLabel.setStyle(
                "-fx-font-size:11px;" +
                        "-fx-text-fill:" +
                        GREEN + ";"
        );

        VBox card =
                new VBox(
                        6,
                        iconLabel,
                        titleLabel,
                        valueLabel,
                        descriptionLabel
                );

        card.setPadding(
                new Insets(18)
        );

        card.setPrefWidth(240);

        card.setPrefHeight(130);

        card.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-background-radius:15px;" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:15px;"
        );

        return card;
    }

    // ============================================================
    // CHALLENGE CARD
    // ============================================================

    private VBox createChallengeCard(
            BorderPane borderPane) {

        Label heading =
                new Label(
                        "☑  Today's Challenge"
                );

        heading.setStyle(
                "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label challenge =
                new Label(
                        "10K Steps Challenge"
                );

        challenge.setStyle(
                "-fx-font-size:18px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label description =
                new Label(
                        "Take 10,000 steps today\n"
                                + "and earn 100 points!"
                );

        description.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#C5CDD7;"
        );

        ProgressBar progress =
                new ProgressBar(0.72);

        progress.setPrefWidth(280);

        progress.setStyle(
                "-fx-accent:" +
                        GREEN + ";"
        );

        Label steps =
                new Label(
                        "7,245 / 10,000 steps"
                );

        steps.setStyle(
                "-fx-font-size:11px;" +
                        "-fx-text-fill:" +
                        GREEN + ";"
        );

        Button start =
                new Button(
                        "Start Challenge"
                );

        start.setMaxWidth(
                Double.MAX_VALUE
        );

        start.setStyle(
                "-fx-background-color:" +
                        GREEN + ";" +
                        "-fx-text-fill:#111111;" +
                        "-fx-background-radius:8px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:9px;" +
                        "-fx-cursor:hand;"
        );

        // ========================================================
        // START CHALLENGE CLICK
        // ========================================================

        start.setOnAction(e -> {

            ChallengesPage challenges =
                    new ChallengesPage();

            borderPane.setCenter(
                    challenges.createContent()
            );
        });

        VBox box =
                new VBox(
                        11,
                        heading,
                        challenge,
                        description,
                        progress,
                        steps,
                        start
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(360);

        box.setPrefHeight(285);

        box.setStyle(
                "-fx-background-color:#1C3027;" +
                        "-fx-background-radius:15px;" +
                        "-fx-border-color:#2C6148;" +
                        "-fx-border-radius:15px;"
        );

        return box;
    }

    // ============================================================
    // EVENTS CARD
    // ============================================================

    private VBox createEventsCard() {

        Label heading =
                new Label(
                        "📅  Upcoming Events"
                );

        heading.setStyle(
                "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Button viewAll =
                new Button(
                        "View All →"
                );

        viewAll.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-size:12px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:0;" +
                        "-fx-cursor:hand;"
        );

        viewAll.setOnAction(e -> {

            System.out.println(
                    "View All Events clicked"
            );
        });

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.getChildren().add(
                heading
        );

        HBox.setHgrow(
                heading,
                Priority.ALWAYS
        );

        header.getChildren().add(
                viewAll
        );

        ImageView eventImage =
                createImage(
                        "/assets/images/cycling.png",
                        245,
                        55
                );

        Label event1 =
                new Label(
                        "🏃  Sunday Marathon\n"
                                + "25 May 2026 • 6:00 AM\n"
                                + "📍 Pune, Maharashtra"
                );

        event1.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#C5CDD7;"
        );

        Label registered =
                new Label(
                        "● Registered"
                );

        registered.setStyle(
                "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:" +
                        GREEN + ";"
        );

        Label event2 =
                new Label(
                        "🧘  Yoga in the Park\n"
                                + "28 May 2026 • 7:00 AM"
                );

        event2.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#C5CDD7;"
        );

        Button register =
                new Button(
                        "Register"
                );

        register.setStyle(
                "-fx-background-color:#263442;" +
                        "-fx-text-fill:#62A8FF;" +
                        "-fx-background-radius:7px;" +
                        "-fx-font-weight:bold;"
        );

        VBox box =
                new VBox(9);

        box.getChildren().add(
                header
        );

        if (eventImage != null) {

            box.getChildren().add(
                    eventImage
            );
        }

        box.getChildren().addAll(
                event1,
                registered,
                event2,
                register
        );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(325);

        box.setPrefHeight(335);

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-background-radius:15px;" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:15px;"
        );

        return box;
    }

    // ============================================================
    // MY CLUBS CARD
    // ============================================================

    private VBox createClubsCard(
            BorderPane borderPane) {

        Label heading =
                new Label(
                        "👥  My Clubs"
                );

        heading.setStyle(
                "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Button viewAll =
                new Button(
                        "View All →"
                );

        viewAll.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-text-fill:" +
                        GREEN + ";" +
                        "-fx-font-size:12px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:0;" +
                        "-fx-cursor:hand;"
        );

        // ========================================================
        // VIEW ALL CLUBS
        // ========================================================

        viewAll.setOnAction(e -> {

            ExplorerClubPage explorer =
                    new ExplorerClubPage();

            explorer.setOnClubSelected(club -> {
                borderPane.setCenter(
                        com.flexforce.view.User.Userclubdet.createPage(club, () -> {
                            borderPane.setCenter(explorer.createScrollableContent());
                        })
                );
            });

            borderPane.setCenter(
                    explorer.createScrollableContent()
            );
        });

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.getChildren().add(
                heading
        );

        HBox.setHgrow(
                heading,
                Priority.ALWAYS
        );

        header.getChildren().add(
                viewAll
        );

        ImageView clubImage =
                createImage(
                        "/assets/images/laughter.png",
                        285,
                        50
                );

        Label club1 =
                new Label(
                        "🏃  Pune Runners Club\n"
                                + "1.2K Members"
                );

        club1.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#C5CDD7;"
        );

        Label club2 =
                new Label(
                        "💪  Fit & Strong Community\n"
                                + "980 Members"
                );

        club2.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#C5CDD7;"
        );

        Label club3 =
                new Label(
                        "🚴  Cycling Warriors\n"
                                + "750 Members"
                );

        club3.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:#C5CDD7;"
        );

        Button explore =
                new Button(
                        "Explore More Clubs"
                );

        explore.setMaxWidth(
                Double.MAX_VALUE
        );

        explore.setStyle(
                "-fx-background-color:#263442;" +
                        "-fx-text-fill:#62A8FF;" +
                        "-fx-background-radius:8px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:8px;" +
                        "-fx-cursor:hand;"
        );

        // ========================================================
        // EXPLORE MORE CLUBS CLICK
        // ========================================================

        explore.setOnAction(e -> {

            ExplorerClubPage explorerPage =
                    new ExplorerClubPage();

            explorerPage.setOnClubSelected(club -> {
                borderPane.setCenter(
                        com.flexforce.view.User.Userclubdet.createPage(club, () -> {
                            borderPane.setCenter(explorerPage.createScrollableContent());
                        })
                );
            });

            borderPane.setCenter(
                    explorerPage.createScrollableContent()
            );
        });

        VBox box =
                new VBox(8);

        box.getChildren().add(
                header
        );

        if (clubImage != null) {

            box.getChildren().add(
                    clubImage
            );
        }

        box.getChildren().addAll(
                club1,
                club2,
                club3,
                explore
        );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(330);

        box.setPrefHeight(335);

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-background-radius:15px;" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:15px;"
        );

        return box;
    }

    // ============================================================
    // DYNAMIC LEADERBOARD CARD
    // ============================================================

    private VBox createLeaderboardCard() {

        Label heading =
                new Label(
                        "🏆  Leaderboard"
                );

        heading.setStyle(
                "-fx-font-size:17px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        VBox rankingBox =
                new VBox(10);

        rankingBox.setPrefHeight(130);

        Label loading =
                new Label(
                        "Loading leaderboard..."
                );

        loading.setStyle(
                "-fx-text-fill:#A7B0BC;" +
                        "-fx-font-size:12px;"
        );

        rankingBox.getChildren().add(
                loading
        );

        loadLeaderboardCard(
                rankingBox
        );

        VBox box =
                new VBox(
                        14,
                        heading,
                        rankingBox
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(430);

        box.setPrefHeight(230);

        box.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                        "-fx-background-radius:15px;" +
                        "-fx-border-color:" +
                        CARD_BORDER + ";" +
                        "-fx-border-radius:15px;"
        );

        return box;
    }

    // ============================================================
    // LOAD LEADERBOARD
    // ============================================================

    private void loadLeaderboardCard(
            VBox rankingBox) {

        Task<List<UserLeaderboard>> task =
                new Task<List<UserLeaderboard>>() {

                    @Override
                    protected List<UserLeaderboard> call()
                            throws Exception {

                        return leaderboardController
                                .getAllUserLeaderboards();
                    }
                };

        task.setOnSucceeded(e -> {

            List<UserLeaderboard> data =
                    task.getValue();

            rankingBox.getChildren()
                    .clear();

            if (data == null || data.isEmpty()) {

                Label empty =
                        new Label(
                                "No leaderboard data available"
                        );

                empty.setStyle(
                        "-fx-text-fill:#A7B0BC;" +
                                "-fx-font-size:12px;"
                );

                rankingBox.getChildren().add(
                        empty
                );

                return;
            }

            // ----------------------------------------------------
            // SORT BY POINTS
            // ----------------------------------------------------

            List<UserLeaderboard> sorted =
                    new ArrayList<>(data);

            sorted.sort(
                    Comparator.comparingInt(
                            UserLeaderboard::getPoints
                    ).reversed()
            );

            int limit =
                    Math.min(
                            4,
                            sorted.size()
                    );

            // ----------------------------------------------------
            // DISPLAY TOP 4
            // ----------------------------------------------------

            for (int i = 0; i < limit; i++) {

                UserLeaderboard user =
                        sorted.get(i);

                String medal;

                if (i == 0) {
                    medal = "🥇";
                } else if (i == 1) {
                    medal = "🥈";
                } else if (i == 2) {
                    medal = "🥉";
                } else {
                    medal = "🟢";
                }

                String name =
                        user.getUserName();

                if (name == null
                        || name.trim().isEmpty()) {

                    name = "User";
                }

                if (loggedInUserId != null
                        && loggedInUserId.equals(
                                user.getUserId())) {

                    name =
                            "You (" + name + ")";
                }

                Label row =
                        new Label(
                                medal
                                        + "  "
                                        + name
                                        + "    "
                                        + String.format(
                                                "%,d",
                                                user.getPoints()
                                        )
                                        + " pts"
                        );

                if (loggedInUserId != null
                        && loggedInUserId.equals(
                                user.getUserId())) {

                    row.setStyle(
                            "-fx-font-weight:bold;" +
                                    "-fx-text-fill:" +
                                    GREEN + ";" +
                                    "-fx-font-size:12px;"
                    );

                } else {

                    row.setStyle(
                            "-fx-text-fill:#D8DEE7;" +
                                    "-fx-font-size:12px;"
                    );
                }

                rankingBox.getChildren()
                        .add(row);
            }
        });

        task.setOnFailed(e -> {

            rankingBox.getChildren()
                    .clear();

            Label error =
                    new Label(
                            "Unable to load leaderboard"
                    );

            error.setStyle(
                    "-fx-text-fill:#FF7777;" +
                            "-fx-font-size:12px;"
            );

            rankingBox.getChildren()
                    .add(error);

            if (task.getException() != null) {

                task.getException()
                        .printStackTrace();
            }
        });

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // ============================================================
    // AI CARD
    // ============================================================

    private VBox createAICard() {

        Label heading =
                new Label(
                        "✨  KAIRA AI"
                );

        heading.setStyle(
                "-fx-font-size:17px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label based =
                new Label(
                        "AI-powered fitness assistant that "
                                + "provides personalized workout "
                                + "recommendations, nutrition tips, "
                                + "and progress tracking."
                );

        based.setWrapText(true);

        based.setStyle(
                "-fx-font-size:11px;" +
                        "-fx-text-fill:#A7B0BC;"
        );

        Label recommendation =
                new Label(
                        
                "Your Intelligence Behind Every Fitness Goal."
                );

        recommendation.setStyle(
                "-fx-font-size:21px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#B68CFF;"
        );

        Button viewPlan =
                new Button(
                        "Ask Kaira"
                );

        // ========================================================
        // AI COACH - KEEP AS IT IS
        // ========================================================

        viewPlan.setOnAction(e -> {

            try {

                new AICoach()
                        .start(
                                new Stage()
                        );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        viewPlan.setStyle(
                "-fx-background-color:#7045C8;" +
                        "-fx-text-fill:white;" +
                        "-fx-background-radius:8px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-cursor:hand;"
        );

        VBox box =
                new VBox(
                        11,
                        heading,
                        based,
                        recommendation,
                        viewPlan
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(560);

        box.setPrefHeight(230);

        box.setStyle(
                "-fx-background-color:#282238;" +
                        "-fx-background-radius:15px;" +
                        "-fx-border-color:#463866;" +
                        "-fx-border-radius:15px;"
        );

        return box;
    }

    // ============================================================
    // IMAGE METHOD
    // ============================================================

    private ImageView createImage(
            String imagePath,
            double width,
            double height) {

        try {

            java.net.URL imageURL =
                    getClass().getResource(
                            imagePath
                    );

            if (imageURL == null) {

                System.out.println(
                        "IMAGE NOT FOUND: "
                                + imagePath
                );

                return null;
            }

            Image image =
                    new Image(
                            imageURL.toExternalForm()
                    );

            ImageView imageView =
                    new ImageView(image);

            imageView.setFitWidth(
                    width
            );

            imageView.setFitHeight(
                    height
            );

            imageView.setPreserveRatio(
                    false
            );

            imageView.setSmooth(true);

            return imageView;

        } catch (Exception e) {

            System.out.println(
                    "IMAGE ERROR: "
                            + imagePath
            );

            return null;
        }
    }
}

