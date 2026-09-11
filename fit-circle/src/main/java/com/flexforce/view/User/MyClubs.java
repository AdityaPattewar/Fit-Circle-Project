package com.flexforce.view.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.flexforce.dao.UserClubDao;
import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.model.club_owner.ClubOwnerActivity;
import com.flexforce.model.common_for_user_clubowner.UserClub;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;
import com.flexforce.view.ExplorerClubPage;
import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.controller.MyClubsController;
import com.flexforce.controller.UserClubController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyClubs extends Application {

        String bg = "#0f1010";
        String card = "#1a1c1b";
        String green = "#c6ff00";
        String white = "#f5f5f5";
        String gray = "#999e9a";
        String border = "#292c2a";

        private java.util.function.Consumer<UserClub> onClubSelected;
        private Runnable onBrowseMore;

        public void setOnClubSelected(java.util.function.Consumer<UserClub> onClubSelected) {
                this.onClubSelected = onClubSelected;
        }

        public void setOnBrowseMore(Runnable onBrowseMore) {
                this.onBrowseMore = onBrowseMore;
        }

        @Override
        public void start(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + bg + ";");

                VBox content = createContent();

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
                                "-fx-background: " + bg +
                                                "; -fx-background-color: " + bg + ";");

                root.setCenter(scrollPane);

                Scene scene = new Scene(root, 1050, 600);

                stage.setTitle("FitCircle - My Clubs");
                stage.setScene(scene);
                stage.show();
        }

        VBox createContent() {

                VBox content = new VBox(20);

                content.setPadding(
                                new Insets(20, 25, 50, 25));

                Label title = new Label("My Clubs");
                title.setTextFill(Color.web(white));
                title.setStyle("-fx-font-size: 34px;-fx-font-weight: bold;");

                Label description = new Label("Your fitness communities and clubs in one place.");
                description.setTextFill(Color.web(white));
                description.setStyle("-fx-font-size: 13px;");

                HBox heading = new HBox(title);
                heading.setAlignment(Pos.CENTER_LEFT);

                Label joined = new Label("Joined Clubs");
                joined.setTextFill(Color.web(white));
                joined.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");

                Button browse = new Button("Browse More →");
                browse.setStyle("-fx-background-color: transparent; -fx-text-fill: " + green
                                + "; -fx-font-size: 11px; -fx-font-weight: bold; -fx-cursor: hand;");
                browse.setOnAction(e -> {
                        if (onBrowseMore != null) {
                                onBrowseMore.run();
                        } else {
                                try {
                                        new ExplorerClubPage().start(new Stage());
                                } catch (Exception ex) {
                                        ex.printStackTrace();
                                }
                        }
                });

                Region headingSpace = new Region();
                HBox.setHgrow(headingSpace, Priority.ALWAYS);

                HBox joinedHeader = new HBox(joined, headingSpace, browse);
                joinedHeader.setAlignment(Pos.CENTER_LEFT);

                HBox clubs = new HBox(16);

                MyClubsController controller = new MyClubsController();
                String userId = AuthControllerlogin.getCurrentUserId();
                List<UserClub> userClubs = controller.getUserJoinedClubs(userId);

                if (userClubs.isEmpty()) {
                        Label noClubs = new Label("You haven't joined any clubs yet.");
                        noClubs.setTextFill(Color.web(gray));
                        clubs.getChildren().add(noClubs);
                } else {
                        for (UserClub club : userClubs) {
                                clubs.getChildren().add(clubCard(club));
                        }
                }

                Label activities = new Label("Upcoming Club Activities");
                activities.setTextFill(Color.web(white));
                activities.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");

                Separator line = new Separator();
                line.setStyle("-fx-background-color: " + border + ";");

                VBox activityList = new VBox(10);

                List<ClubOwnerActivity> upcoming = controller.getUpcomingActivities(userClubs);

                if (upcoming.isEmpty()) {
                        Label noActivities = new Label("No upcoming activities.");
                        noActivities.setTextFill(Color.web(gray));
                        activityList.getChildren().add(noActivities);
                } else {
                        for (ClubOwnerActivity act : upcoming) {
                                String clubName = "Your Club";
                                for (UserClub c : userClubs) {
                                        if (c.getClubId() != null && c.getClubId().equals(act.getClubId())) {
                                                clubName = c.getClubName();
                                                break;
                                        }
                                }
                                activityList.getChildren().add(activityCard(act, clubName));
                        }
                }

                content.getChildren().addAll(heading, description, joinedHeader, clubs, activities, line, activityList);
                return content;
        }

        VBox clubCard(UserClub club) {
                String name = club.getClubName() != null ? club.getClubName() : "Unnamed Club";
                String category = club.getCategory() != null ? club.getCategory() : "General";
                String members = club.getCapacity() > 0 ? club.getCapacity() + " Capacity" : "N/A";
                String description = club.getDescription() != null ? club.getDescription()
                                : "No description available.";

                VBox cardBox = new VBox(10);

                cardBox.setPrefWidth(250);
                cardBox.setMinHeight(315);

                cardBox.setPadding(
                                new Insets(12));

                cardBox.setStyle(
                                "-fx-background-color: " + card + ";" +
                                                "-fx-border-color: " + border + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                Label image = new Label(
                                "FITNESS");

                image.setPrefHeight(90);
                image.setMaxWidth(
                                Double.MAX_VALUE);

                image.setAlignment(
                                Pos.CENTER);

                image.setTextFill(
                                Color.web(green));

                image.setStyle(
                                "-fx-background-color: #252825;" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;");

                Label tag = new Label(
                                category);

                tag.setTextFill(
                                Color.BLACK);

                tag.setStyle(
                                "-fx-background-color: " + green + ";" +
                                                "-fx-padding: 3px 7px;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;");

                Label clubName = new Label(
                                name);

                clubName.setWrapText(true);

                clubName.setTextFill(
                                Color.web(white));

                clubName.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;");

                Label memberLabel = new Label(
                                "♙  " + members);

                memberLabel.setTextFill(
                                Color.web(green));

                memberLabel.setStyle(
                                "-fx-font-size: 10px;");

                Label info = new Label(
                                description);

                info.setWrapText(true);

                info.setTextFill(
                                Color.web("#d0d0d0"));

                info.setStyle(
                                "-fx-font-size: 11px;");

                VBox.setVgrow(
                                info,
                                Priority.ALWAYS);

                Button view = new Button(
                                "VIEW CLUB");

                view.setMaxWidth(
                                Double.MAX_VALUE);

                view.setStyle(
                                "-fx-background-color: " + green + ";" +
                                                "-fx-text-fill: black;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 3px;" +
                                                "-fx-padding: 7px;" +
                                                "-fx-cursor: hand;");

                view.setOnAction(e -> {
                        if (onClubSelected != null) {
                                onClubSelected.accept(club);
                        } else {
                                Userclubdet.openClubDetails(club);
                        }
                });

                cardBox.getChildren().addAll(
                                image,
                                tag,
                                clubName,
                                memberLabel,
                                info,
                                view);

                return cardBox;
        }

        HBox activityCard(ClubOwnerActivity activity, String clubName) {
                String actDate = activity.getDate();
                String month = "MTH";
                String date = "??";
                if (actDate != null && actDate.length() >= 5) {
                        try {
                                String[] parts = actDate.split("-");
                                if (parts.length >= 3) {
                                        date = parts[2];
                                        month = parts[1];
                                } else {
                                        date = actDate.substring(0, Math.min(2, actDate.length()));
                                }
                        } catch (Exception e) {
                        }
                }

                String title = activity.getActivityName() != null ? activity.getActivityName() : "Activity";
                String club = clubName;
                String time = activity.getStartTime() != null ? activity.getStartTime() : "TBD";
                String location = activity.getLocation() != null ? activity.getLocation() : "TBD";

                HBox box = new HBox(12);

                box.setPadding(
                                new Insets(12));

                box.setAlignment(
                                Pos.CENTER_LEFT);

                box.setStyle(
                                "-fx-background-color: " + card + ";" +
                                                "-fx-border-color: " + border + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                VBox dateBox = new VBox(1);

                dateBox.setAlignment(
                                Pos.CENTER);

                dateBox.setPrefWidth(45);

                dateBox.setPadding(
                                new Insets(7));

                dateBox.setStyle(
                                "-fx-background-color: #303330;" +
                                                "-fx-background-radius: 4px;");

                Label monthLabel = new Label(
                                month);

                monthLabel.setTextFill(
                                Color.web(green));

                monthLabel.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-font-weight: bold;");

                Label dateLabel = new Label(
                                date);

                dateLabel.setTextFill(
                                Color.web(white));

                dateLabel.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;");

                dateBox.getChildren().addAll(
                                monthLabel,
                                dateLabel);

                Label titleLabel = new Label(
                                title);

                titleLabel.setTextFill(
                                Color.web(white));

                titleLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                Label details = new Label(
                                "♙ " + club +
                                                "   ◷ " + time +
                                                "   ⌖ " + location);

                details.setTextFill(
                                Color.web("#b8b8b8"));

                details.setStyle(
                                "-fx-font-size: 9px;");

                VBox information = new VBox(
                                5,
                                titleLabel,
                                details);

                Region space = new Region();

                HBox.setHgrow(
                                space,
                                Priority.ALWAYS);

                Button view = new Button(
                                "VIEW EVENT");

                view.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " + green + ";" +
                                                "-fx-border-color: " + green + ";" +
                                                "-fx-border-radius: 3px;" +
                                                "-fx-background-radius: 3px;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 6px 15px;" +
                                                "-fx-cursor: hand;");

                view.setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Event Details");
                        alert.setHeaderText(title + " at " + club);
                        alert.setContentText("Date: " + actDate + "\n" +
                                        "Time: " + time + "\n" +
                                        "Location: " + location + "\n\n" +
                                        "Description: " + (activity.getDescription() != null ? activity.getDescription()
                                                        : "No details available."));
                        com.flexforce.view.components.DialogUtils.applyTheme(alert);
                        alert.showAndWait();
                });

                box.getChildren().addAll(
                                dateBox,
                                information,
                                space,
                                view);

                return box;
        }
}