
package com.flexforce.view.User;

import java.util.List;

import com.flexforce.controller.ClubownerActivityController;
import com.flexforce.model.club_owner.ClubOwnerActivity;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChallengesPage {

        // ============================================================
        // FITCIRCLE THEME
        // ============================================================

        private static final String BG_COLOR = "#0B0F0D";

        private static final String CARD_COLOR = "#171B18";

        private static final String GREEN = "#B6FF00";

        private static final String WHITE = "#FFFFFF";

        private static final String SECONDARY_TEXT = "#A8B3A8";

        private static final String BORDER_COLOR = "#2A302C";

        // ============================================================
        // CONTROLLER
        // ============================================================

        private ClubownerActivityController activityController;

        // ============================================================
        // ACTIVITY CONTAINER
        // ============================================================

        private VBox challengeContainer;

        // ============================================================
        // CONSTRUCTOR
        // ============================================================

        public ChallengesPage() {

                activityController = new ClubownerActivityController();
        }

        // ============================================================
        // CREATE CONTENT
        // ============================================================

        public BorderPane createContent() {

                BorderPane mainContainer = new BorderPane();

                mainContainer.setStyle(
                                "-fx-background-color: " +
                                                BG_COLOR + ";");

                // ========================================================
                // MAIN CONTENT
                // ========================================================

                VBox content = new VBox(20);

                content.setPadding(
                                new Insets(
                                                30,
                                                35,
                                                30,
                                                35));

                // ========================================================
                // TITLE
                // ========================================================

                Label title = new Label("Challenges");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // SUBTITLE
                // ========================================================

                Label subtitle = new Label(
                                "Explore fitness activities created by Club Owners.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                SECONDARY_TEXT + ";");

                subtitle.setWrapText(true);

                // ========================================================
                // AVAILABLE ACTIVITIES TITLE
                // ========================================================

                Label activeTitle = new Label(
                                "Available Activities");

                activeTitle.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // ACTIVITY CONTAINER
                // ========================================================

                challengeContainer = new VBox(15);

                challengeContainer.setFillWidth(true);

                // ========================================================
                // ADD CONTENT
                // ========================================================

                content.getChildren().add(title);

                content.getChildren().add(subtitle);

                content.getChildren().add(activeTitle);

                content.getChildren().add(
                                challengeContainer);

                // ========================================================
                // SCROLL PANE
                // ========================================================

                ScrollPane scrollPane = new ScrollPane();

                scrollPane.setContent(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background: " +
                                                BG_COLOR +
                                                ";" +
                                                "-fx-background-color: " +
                                                BG_COLOR +
                                                ";");

                // ========================================================
                // SET CENTER
                // ========================================================

                mainContainer.setCenter(
                                scrollPane);

                // ========================================================
                // LOAD ALL CLUB OWNER ACTIVITIES
                // ========================================================

                loadActivities();

                return mainContainer;
        }

        // ============================================================
        // GET VIEW
        // ============================================================

        public BorderPane getView() {

                return createContent();
        }

        // ============================================================
        // LOAD ALL ACTIVITIES
        // ============================================================

        private void loadActivities() {

                challengeContainer
                                .getChildren()
                                .clear();

                try {

                        System.out.println(
                                        "======================================");

                        System.out.println(
                                        "LOADING ALL CLUB OWNER ACTIVITIES");

                        System.out.println(
                                        "======================================");

                        // ----------------------------------------------------
                        // FETCH ALL ACTIVITIES FROM FIREBASE
                        // ----------------------------------------------------

                        List<ClubOwnerActivity> activities = activityController.getActivities();

                        // ----------------------------------------------------
                        // NULL CHECK
                        // ----------------------------------------------------

                        if (activities == null) {

                                System.out.println(
                                                "Activities list is NULL");

                                showEmptyMessage(
                                                "Unable to load activities.");

                                return;
                        }

                        System.out.println(
                                        "Total Activities Fetched = "
                                                        + activities.size());

                        // ----------------------------------------------------
                        // EMPTY CHECK
                        // ----------------------------------------------------

                        if (activities.isEmpty()) {

                                System.out.println(
                                                "No Club Owner Activities Found");

                                showEmptyMessage(
                                                "No activities available yet.");

                                return;
                        }

                        // ----------------------------------------------------
                        // DISPLAY ALL ACTIVITIES
                        // ----------------------------------------------------

                        for (ClubOwnerActivity activity : activities) {

                                if (activity == null) {
                                        continue;
                                }

                                System.out.println(
                                                "Activity Loaded: " +
                                                                activity.getActivityId() +
                                                                " | " +
                                                                activity.getActivityName());

                                VBox card = createActivityCard(
                                                activity);

                                challengeContainer
                                                .getChildren()
                                                .add(card);
                        }

                        System.out.println(
                                        "All Club Owner Activities Displayed");

                } catch (Exception e) {

                        System.out.println(
                                        "ERROR WHILE LOADING ACTIVITIES");

                        e.printStackTrace();

                        showEmptyMessage(
                                        "Unable to load activities.");
                }
        }

        // ============================================================
        // EMPTY / ERROR MESSAGE
        // ============================================================

        private void showEmptyMessage(
                        String message) {

                Label empty = new Label(message);

                empty.setWrapText(true);

                empty.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: " +
                                                SECONDARY_TEXT + ";");

                challengeContainer
                                .getChildren()
                                .add(empty);
        }

        // ============================================================
        // CREATE ACTIVITY CARD
        // ============================================================

        private VBox createActivityCard(
                        ClubOwnerActivity activity) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(22));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: " +
                                                CARD_COLOR +
                                                ";" +

                                                "-fx-background-radius: 16;" +

                                                "-fx-border-color: " +
                                                BORDER_COLOR +
                                                ";" +

                                                "-fx-border-radius: 16;" +

                                                "-fx-border-width: 1;");

                // ========================================================
                // TOP ROW
                // ========================================================

                HBox topRow = new HBox(10);

                topRow.setAlignment(
                                Pos.CENTER_LEFT);

                // ========================================================
                // ACTIVITY NAME
                // ========================================================

                String activityName = activity.getActivityName();

                if (activityName == null ||
                                activityName.trim().isEmpty()) {

                        activityName = "Unnamed Activity";
                }

                Label name = new Label(activityName);

                name.setWrapText(true);

                name.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                HBox.setHgrow(
                                name,
                                Priority.ALWAYS);

                // ========================================================
                // STATUS
                // ========================================================

                String statusText = activity.getStatus();

                if (statusText == null ||
                                statusText.trim().isEmpty()) {

                        statusText = "Available";
                }

                Label status = new Label(statusText);

                status.setStyle(
                                "-fx-background-color: " +
                                                GREEN +
                                                ";" +

                                                "-fx-text-fill: #000000;" +

                                                "-fx-font-weight: bold;" +

                                                "-fx-padding: 6 12 6 12;" +

                                                "-fx-background-radius: 20;");

                topRow.getChildren().add(name);

                topRow.getChildren().add(status);

                // ========================================================
                // ACTIVITY TYPE
                // ========================================================

                Label type = new Label(
                                "Activity Type: " +
                                                safeValue(
                                                                activity.getActivityType()));

                type.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // DIFFICULTY
                // ========================================================

                Label difficulty = new Label(
                                "Difficulty: " +
                                                safeValue(
                                                                activity.getDifficulty()));

                difficulty.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // DESCRIPTION
                // ========================================================

                String descriptionText = activity.getDescription();

                if (descriptionText == null ||
                                descriptionText.trim().isEmpty()) {

                        descriptionText = "No description available.";
                }

                Label description = new Label(
                                descriptionText);

                description.setWrapText(true);

                description.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                SECONDARY_TEXT + ";");

                // ========================================================
                // DATE
                // ========================================================

                Label date = new Label(
                                "Date: " +
                                                safeValue(
                                                                activity.getDate()));

                date.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // TIME
                // ========================================================

                Label time = new Label(
                                "Time: " +
                                                safeValue(
                                                                activity.getStartTime())
                                                +
                                                " - " +
                                                safeValue(
                                                                activity.getEndTime()));

                time.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // LOCATION
                // ========================================================

                Label location = new Label(
                                "Location: " +
                                                safeValue(
                                                                activity.getLocation()));

                location.setWrapText(true);

                location.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // TRAINER
                // ========================================================

                Label trainer = new Label(
                                "Trainer: " +
                                                safeValue(
                                                                activity.getTrainer()));

                trainer.setWrapText(true);

                trainer.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // MAXIMUM PARTICIPANTS
                // ========================================================

                Label participants = new Label(
                                "Maximum Participants: " +
                                                activity.getMaximumParticipants());

                participants.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " +
                                                WHITE + ";");

                // ========================================================
                // ACTIVITY ID
                // ========================================================

                Label activityId = new Label(
                                "Activity ID: " +
                                                safeValue(
                                                                activity.getActivityId()));

                activityId.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " +
                                                SECONDARY_TEXT + ";");

                // ========================================================
                // ADD EVERYTHING TO CARD
                // ========================================================

                card.getChildren().add(topRow);

                card.getChildren().add(type);

                card.getChildren().add(difficulty);

                card.getChildren().add(description);

                card.getChildren().add(date);

                card.getChildren().add(time);

                card.getChildren().add(location);

                card.getChildren().add(trainer);

                card.getChildren().add(participants);

                card.getChildren().add(activityId);

                return card;
        }

        // ============================================================
        // SAFE STRING VALUE
        // ============================================================

        private String safeValue(
                        String value) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return "N/A";
                }

                return value;
        }
}
