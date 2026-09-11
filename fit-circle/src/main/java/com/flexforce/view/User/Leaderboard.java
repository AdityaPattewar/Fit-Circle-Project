

package com.flexforce.view.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.controller.UserLeaderboardController;
import com.flexforce.model.user.UserLeaderboard;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Leaderboard {

    // ============================================================
    // COLORS
    // ============================================================

    private final String bg = "#101111";
    private final String card = "#1d1e1d";
    private final String green = "#c6ff00";
    private final String white = "#f5f5f5";
    private final String gray = "#a7aaa6";
    private final String border = "#303230";

    // ============================================================
    // CONTROLLER
    // ============================================================

    private final UserLeaderboardController leaderboardController;

    // ============================================================
    // CURRENT LOGGED-IN USER
    // ============================================================

    private String loggedInUserId;

    // ============================================================
    // UI REFERENCES
    // ============================================================

    private VBox rows;

    private Label yourRank;
    private Label yourPoints;
    private Label yourPercentile;
    private Label progressText;

    private Button week;
    private Button month;
    private Button allTime;

    private String selectedTimeframe = "This Week";

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public Leaderboard() {

        leaderboardController =
                new UserLeaderboardController();

        // Get Firebase UID of logged-in user
        loggedInUserId =
                AuthControllerlogin.getCurrentUserId();

        System.out.println(
                "Leaderboard Current User ID = "
                        + loggedInUserId
        );
    }

    // ============================================================
    // MAIN CONTENT
    // ============================================================

    public VBox createContent() {

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(25, 25, 30, 25)
        );

        content.setStyle(
                "-fx-background-color: " + bg + ";"
        );

        // ========================================================
        // HEADER
        // ========================================================

        Label title =
                new Label("Leaderboard");

        title.setTextFill(
                Color.web(white)
        );

        title.setStyle(
                "-fx-font-size: 34px;" +
                "-fx-font-weight: bold;"
        );

        Label description =
                new Label(
                        "See how you rank among the FitCircle community."
                );

        description.setTextFill(
                Color.web(white)
        );

        description.setStyle(
                "-fx-font-size: 13px;"
        );

        VBox header =
                new VBox(5);

        header.getChildren().addAll(
                title,
                description
        );

        // ========================================================
        // MAIN AREA
        // ========================================================

        HBox mainArea =
                new HBox(16);

        VBox leftSide =
                createLeftSide();

        VBox ranking =
                createRanking();

        HBox.setHgrow(
                ranking,
                Priority.ALWAYS
        );

        mainArea.getChildren().addAll(
                leftSide,
                ranking
        );

        content.getChildren().addAll(
                header,
                mainArea
        );

        // ========================================================
        // LOAD FIRESTORE DATA
        // ========================================================

        loadLeaderboard(
                selectedTimeframe
        );

        return content;
    }

    // ============================================================
    // LEFT SIDE
    // ============================================================

    private VBox createLeftSide() {

        VBox left =
                new VBox(16);

        left.setPrefWidth(220);

        // ========================================================
        // TIMEFRAME CARD
        // ========================================================

        VBox timeframe =
                new VBox(12);

        timeframe.setPadding(
                new Insets(15)
        );

        timeframe.setStyle(
                "-fx-background-color: " + card + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        Label timeTitle =
                new Label("Timeframe");

        timeTitle.setTextFill(
                Color.web(white)
        );

        timeTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        week =
                timeframeButton("This Week");

        month =
                timeframeButton("This Month");

        allTime =
                timeframeButton("All Time");

        // Default
        week.setStyle(
                activeTimeframeStyle()
        );

        // ========================================================
        // THIS WEEK
        // ========================================================

        week.setOnAction(e -> {

            selectedTimeframe =
                    "This Week";

            week.setStyle(
                    activeTimeframeStyle()
            );

            month.setStyle(
                    normalTimeframeStyle()
            );

            allTime.setStyle(
                    normalTimeframeStyle()
            );

            loadLeaderboard(
                    selectedTimeframe
            );
        });

        // ========================================================
        // THIS MONTH
        // ========================================================

        month.setOnAction(e -> {

            selectedTimeframe =
                    "This Month";

            month.setStyle(
                    activeTimeframeStyle()
            );

            week.setStyle(
                    normalTimeframeStyle()
            );

            allTime.setStyle(
                    normalTimeframeStyle()
            );

            loadLeaderboard(
                    selectedTimeframe
            );
        });

        // ========================================================
        // ALL TIME
        // ========================================================

        allTime.setOnAction(e -> {

            selectedTimeframe =
                    "All Time";

            allTime.setStyle(
                    activeTimeframeStyle()
            );

            week.setStyle(
                    normalTimeframeStyle()
            );

            month.setStyle(
                    normalTimeframeStyle()
            );

            loadLeaderboard(
                    selectedTimeframe
            );
        });

        timeframe.getChildren().addAll(
                timeTitle,
                week,
                month,
                allTime
        );

        // ========================================================
        // YOUR RANK CARD
        // ========================================================

        VBox rankCard =
                new VBox(8);

        rankCard.setPadding(
                new Insets(15)
        );

        rankCard.setStyle(
                "-fx-background-color: #22251b;" +
                "-fx-border-color: " + green + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        Label yourRankTitle =
                new Label("Your Rank");

        yourRankTitle.setTextFill(
                Color.web(white)
        );

        yourRankTitle.setStyle(
                "-fx-font-size: 12px;"
        );

        yourRank =
                new Label("#-");

        yourRank.setTextFill(
                Color.web(green)
        );

        yourRank.setStyle(
                "-fx-font-size: 34px;" +
                "-fx-font-weight: bold;"
        );

        Label pointsText =
                new Label("Points");

        pointsText.setTextFill(
                Color.web(white)
        );

        pointsText.setStyle(
                "-fx-font-size: 10px;"
        );

        yourPoints =
                new Label("0");

        yourPoints.setTextFill(
                Color.web(white)
        );

        yourPoints.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;"
        );

        Label percentileText =
                new Label("Percentile");

        percentileText.setTextFill(
                Color.web(white)
        );

        percentileText.setStyle(
                "-fx-font-size: 10px;"
        );

        yourPercentile =
                new Label("-");

        yourPercentile.setTextFill(
                Color.web(green)
        );

        yourPercentile.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Region space =
                new Region();

        VBox.setVgrow(
                space,
                Priority.ALWAYS
        );

        progressText =
                new Label(
                        "Loading..."
                );

        progressText.setTextFill(
                Color.web(white)
        );

        progressText.setStyle(
                "-fx-font-size: 9px;"
        );

        Region progress =
                new Region();

        progress.setPrefHeight(4);

        progress.setMaxWidth(
                Double.MAX_VALUE
        );

        progress.setStyle(
                "-fx-background-color: " + green + ";" +
                "-fx-background-radius: 4px;"
        );

        rankCard.getChildren().addAll(
                yourRankTitle,
                yourRank,
                pointsText,
                yourPoints,
                percentileText,
                yourPercentile,
                space,
                progressText,
                progress
        );

        left.getChildren().addAll(
                timeframe,
                rankCard
        );

        return left;
    }

    // ============================================================
    // TIMEFRAME BUTTON
    // ============================================================

    private Button timeframeButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setStyle(
                normalTimeframeStyle()
        );

        return button;
    }

    // ============================================================
    // NORMAL STYLE
    // ============================================================

    private String normalTimeframeStyle() {

        return
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + white + ";" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 9px 10px;" +
                "-fx-background-radius: 3px;";
    }

    // ============================================================
    // ACTIVE STYLE
    // ============================================================

    private String activeTimeframeStyle() {

        return
                "-fx-background-color: #30312e;" +
                "-fx-text-fill: " + green + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9px 10px;" +
                "-fx-background-radius: 3px;";
    }

    // ============================================================
    // CREATE RANKING
    // ============================================================

    private VBox createRanking() {

        VBox ranking =
                new VBox();

        ranking.setStyle(
                "-fx-background-color: " + card + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        // ========================================================
        // HEADING
        // ========================================================

        HBox heading =
                new HBox();

        heading.setPadding(
                new Insets(15)
        );

        Label title =
                new Label(
                        "Global Rankings"
                );

        title.setTextFill(
                Color.web(white)
        );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label filter =
                new Label("☰");

        filter.setTextFill(
                Color.web(gray)
        );

        filter.setStyle(
                "-fx-font-size: 15px;"
        );

        heading.getChildren().addAll(
                title,
                space,
                filter
        );

        // ========================================================
        // TABLE HEADER
        // ========================================================

        HBox tableHeader =
                new HBox();

        tableHeader.setPadding(
                new Insets(10, 15, 10, 15)
        );

        tableHeader.setStyle(
                "-fx-background-color: #202120;"
        );

        Label rank =
                columnLabel(
                        "Rank",
                        55
                );

        Label athlete =
                columnLabel(
                        "Athlete",
                        160
                );

        Label points =
                columnLabel(
                        "Points",
                        100
                );

        Label streak =
                columnLabel(
                        "Streak",
                        80
                );

        Label level =
                columnLabel(
                        "Level",
                        60
                );

        tableHeader.getChildren().addAll(
                rank,
                athlete,
                points,
                streak,
                level
        );

        // ========================================================
        // DYNAMIC ROWS
        // ========================================================

        rows =
                new VBox();

        Label loading =
                new Label(
                        "Loading leaderboard..."
                );

        loading.setTextFill(
                Color.web(gray)
        );

        loading.setPadding(
                new Insets(20)
        );

        rows.getChildren().add(
                loading
        );

        // ========================================================
        // LOAD MORE
        // ========================================================

        Button loadMore =
                new Button(
                        "Load More Rankings  ↓"
                );

        loadMore.setMaxWidth(
                Double.MAX_VALUE
        );

        loadMore.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + green + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px;"
        );

        loadMore.setOnAction(e -> {

            loadMore.setText(
                    "Rankings Refreshed"
            );

            loadLeaderboard(
                    selectedTimeframe
            );
        });

        ranking.getChildren().addAll(
                heading,
                tableHeader,
                rows,
                loadMore
        );

        return ranking;
    }

    // ============================================================
    // LOAD DATA FROM FIRESTORE
    // ============================================================

    private void loadLeaderboard(
            String timeframe
    ) {

        if (rows == null) {
            return;
        }

        rows.getChildren().clear();

        Label loading =
                new Label(
                        "Loading "
                                + timeframe
                                + "..."
                );

        loading.setTextFill(
                Color.web(gray)
        );

        loading.setPadding(
                new Insets(20)
        );

        rows.getChildren().add(
                loading
        );

        Task<List<UserLeaderboard>> task =
                new Task<>() {

                    @Override
                    protected List<UserLeaderboard> call() {

                        return leaderboardController
                                .getAllUserLeaderboards();
                    }
                };

        task.setOnSucceeded(e -> {

            List<UserLeaderboard> allData =
                    task.getValue();

            updateLeaderboard(
                    allData,
                    timeframe
            );
        });

        task.setOnFailed(e -> {

            Throwable error =
                    task.getException();

            if (error != null) {
                error.printStackTrace();
            }

            Platform.runLater(() -> {

                rows.getChildren().clear();

                Label errorLabel =
                        new Label(
                                "Unable to load leaderboard."
                        );

                errorLabel.setTextFill(
                        Color.web("#ff5555")
                );

                errorLabel.setPadding(
                        new Insets(20)
                );

                rows.getChildren().add(
                        errorLabel
                );
            });
        });

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // ============================================================
    // UPDATE LEADERBOARD UI
    // ============================================================

    private void updateLeaderboard(
            List<UserLeaderboard> allData,
            String timeframe
    ) {

        Platform.runLater(() -> {

            rows.getChildren().clear();

            List<UserLeaderboard> filtered =
                    new ArrayList<>();

            // ====================================================
            // FILTER TIMEFRAME
            // ====================================================

            if (allData != null) {

                for (UserLeaderboard data : allData) {

                    if (data == null) {
                        continue;
                    }

                    String dataTimeframe =
                            data.getTimeframe();

                    if (dataTimeframe == null) {
                        continue;
                    }

                    if (dataTimeframe.equalsIgnoreCase(
                            timeframe
                    )) {

                        filtered.add(data);
                    }
                }
            }

            // ====================================================
            // SORT BY POINTS
            // ====================================================

            filtered.sort(
                    Comparator.comparingInt(
                            UserLeaderboard::getPoints
                    ).reversed()
            );

            // ====================================================
            // NO DATA
            // ====================================================

            if (filtered.isEmpty()) {

                Label noData =
                        new Label(
                                "No leaderboard data found for "
                                        + timeframe
                        );

                noData.setTextFill(
                        Color.web(gray)
                );

                noData.setPadding(
                        new Insets(20)
                );

                rows.getChildren().add(
                        noData
                );

                updateYourRankCard(
                        filtered
                );

                return;
            }

            // ====================================================
            // CREATE ROWS
            // ====================================================

            int dynamicRank = 1;

            for (UserLeaderboard data :
                    filtered) {

                boolean currentUser =
                        isCurrentUser(data);

                HBox row =
                        rankingRow(
                                String.valueOf(
                                        dynamicRank
                                ),

                                safeName(
                                        data.getUserName()
                                ),

                                formatPoints(
                                        data.getPoints()
                                ),

                                data.getStreak()
                                        + "d",

                                "Lv "
                                        + data.getLevel(),

                                currentUser
                        );

                rows.getChildren().add(
                        row
                );

                dynamicRank++;
            }

            // ====================================================
            // YOUR RANK CARD
            // ====================================================

            updateYourRankCard(
                    filtered
            );
        });
    }

    // ============================================================
    // CHECK CURRENT USER
    // ============================================================

    private boolean isCurrentUser(
            UserLeaderboard data
    ) {

        if (loggedInUserId == null ||
                data == null ||
                data.getUserId() == null) {

            return false;
        }

        return loggedInUserId.equals(
                data.getUserId()
        );
    }

    // ============================================================
    // YOUR RANK CARD
    // ============================================================

    private void updateYourRankCard(
            List<UserLeaderboard> data
    ) {

        if (data == null ||
                data.isEmpty()) {

            yourRank.setText("#-");
            yourPoints.setText("0");
            yourPercentile.setText("-");
            progressText.setText(
                    "No data available"
            );

            return;
        }

        // ========================================================
        // FIND LOGGED-IN USER
        // ========================================================

        UserLeaderboard current =
                null;

        if (loggedInUserId != null) {

            for (UserLeaderboard item :
                    data) {

                if (loggedInUserId.equals(
                        item.getUserId()
                )) {

                    current = item;
                    break;
                }
            }
        }

        // ========================================================
        // USER FOUND
        // ========================================================

        if (current != null) {

            int position = 1;

            for (UserLeaderboard item :
                    data) {

                if (item == current) {
                    break;
                }

                position++;
            }

            // ----------------------------------------------------
            // YOUR RANK
            // ----------------------------------------------------

            yourRank.setText(
                    "#" + position
            );

            // ----------------------------------------------------
            // YOUR POINTS
            // ----------------------------------------------------

            yourPoints.setText(
                    formatPoints(
                            current.getPoints()
                    )
            );

            // ----------------------------------------------------
            // PERCENTILE
            // ----------------------------------------------------

            int total =
                    data.size();

            double percentile =
                    ((double)
                            (total - position + 1)
                            / total)
                            * 100;

            int topPercent =
                    Math.max(
                            1,
                            (int) Math.ceil(
                                    100 - percentile
                            )
                    );

            yourPercentile.setText(
                    "Top "
                            + topPercent
                            + "%"
            );

            // ----------------------------------------------------
            // PROGRESS
            // ----------------------------------------------------

            if (position > 1) {

                UserLeaderboard above =
                        data.get(
                                position - 2
                        );

                int difference =
                        above.getPoints()
                                - current.getPoints();

                progressText.setText(
                        difference
                                + " pts to Top "
                                + (position - 1)
                                + "   ↗ On Track"
                );

            } else {

                progressText.setText(
                        "You are #1!   ↗ Excellent"
                );
            }

        } else {

            // ====================================================
            // USER NOT FOUND
            // ====================================================

            yourRank.setText("#-");
            yourPoints.setText("0");
            yourPercentile.setText("-");

            if (loggedInUserId == null) {

                progressText.setText(
                        "Please login first"
                );

            } else {

                progressText.setText(
                        "User not found in leaderboard"
                );
            }
        }
    }

    // ============================================================
    // RANKING ROW
    // ============================================================

    private HBox rankingRow(
            String rankNumber,
            String name,
            String points,
            String streak,
            String level,
            boolean currentUser
    ) {

        HBox row =
                new HBox();

        row.setPadding(
                new Insets(
                        12,
                        15,
                        12,
                        15
                )
        );

        // ========================================================
        // CURRENT USER STYLE
        // ========================================================

        if (currentUser) {

            row.setStyle(
                    "-fx-background-color: #282b1d;" +
                    "-fx-border-color: " + green + ";" +
                    "-fx-border-width: 0 0 0 3px;"
            );

        } else {

            row.setStyle(
                    "-fx-border-color: #292b29;" +
                    "-fx-border-width: 0 0 1px 0;"
            );
        }

        Label rank =
                columnLabel(
                        rankNumber,
                        55
                );

        Label athlete =
                columnLabel(
                        name
                                + (
                                currentUser
                                        ? "  (You)"
                                        : ""
                        ),
                        160
                );

        Label pointLabel =
                columnLabel(
                        points,
                        100
                );

        Label streakLabel =
                columnLabel(
                        "◉ " + streak,
                        80
                );

        Label levelLabel =
                columnLabel(
                        level,
                        60
                );

        // ========================================================
        // CURRENT USER GREEN
        // ========================================================

        if (currentUser) {

            rank.setTextFill(
                    Color.web(green)
            );

            athlete.setTextFill(
                    Color.web(green)
            );

            pointLabel.setTextFill(
                    Color.web(green)
            );

            streakLabel.setTextFill(
                    Color.web(green)
            );

            levelLabel.setTextFill(
                    Color.web(green)
            );
        }

        row.getChildren().addAll(
                rank,
                athlete,
                pointLabel,
                streakLabel,
                levelLabel
        );

        return row;
    }

    // ============================================================
    // COLUMN LABEL
    // ============================================================

    private Label columnLabel(
            String text,
            double width
    ) {

        Label label =
                new Label(text);

        label.setPrefWidth(
                width
        );

        label.setTextFill(
                Color.web(white)
        );

        label.setStyle(
                "-fx-font-size: 11px;"
        );

        return label;
    }

    // ============================================================
    // FORMAT POINTS
    // ============================================================

    private String formatPoints(
            int points
    ) {

        return String.format(
                "%,d",
                points
        );
    }

    // ============================================================
    // SAFE USER NAME
    // ============================================================

    private String safeName(
            String name
    ) {

        if (name == null ||
                name.trim().isEmpty()) {

            return "Unknown User";
        }

        return name;
    }
}

