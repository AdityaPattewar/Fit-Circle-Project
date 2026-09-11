package com.flexforce.view.club_owner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Event extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f7f8fa;");

        Label title = new Label("Events");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#eee8e8"));

        Label active = new Label("12 Active");
        active.setPadding(new Insets(4, 8, 4, 8));
        active.setStyle("-fx-background-color: #01050e; -fx-text-fill: white; -fx-background-radius: 10; -fx-font-size: 9px; -fx-font-weight: bold;");

        HBox titleBox = new HBox(7, title, active);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Button createEvent = new Button("+  Create Event");
        createEvent.setPrefSize(105, 30);
        createEvent.setStyle("-fx-background-color: #123B9B; -fx-text-fill: white; -fx-font-size: 9px; -fx-font-weight: bold; -fx-background-radius: 6;");

        Label titleSpace = new Label();
        titleSpace.setPrefWidth(100);

        HBox titleRow = new HBox(titleBox, titleSpace, createEvent);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label subtitle = new Label("Manage and schedule club activities, workshops, and community gatherings.");
        subtitle.setFont(Font.font("Arial", 12));
        subtitle.setTextFill(Color.web("#faf8f8"));

        ToggleButton upcoming = new ToggleButton("Upcoming Events");
        ToggleButton past = new ToggleButton("Past Events");
        ToggleButton drafts = new ToggleButton("Drafts");

        ToggleGroup group = new ToggleGroup();

        upcoming.setToggleGroup(group);
        past.setToggleGroup(group);
        drafts.setToggleGroup(group);

        upcoming.setSelected(true);

        HBox tabs = new HBox(upcoming, past, drafts);
        tabs.setSpacing(2);
        tabs.setPadding(new Insets(4));
        tabs.setStyle("-fx-background-color: #05030e; -fx-background-radius: 5;");

        TextField search = new TextField();
        search.setPromptText("⌕  Search events...");
        search.setPrefSize(190, 32);
        search.setStyle("-fx-background-color: black; -fx-border-color: #d8dce2; -fx-border-radius: 6; -fx-background-radius: 6; -fx-font-size: 10px;");

        Button filter = new Button("☰");
        filter.setPrefSize(35, 32);
        filter.setStyle("-fx-background-color: white; -fx-border-color: #d8dce2; -fx-border-radius: 6; -fx-background-radius: 6;");

        HBox searchBox = new HBox(6, search, filter);

        Label toolbarSpace = new Label();
        toolbarSpace.setPrefWidth(50);

        HBox toolbar = new HBox(tabs, toolbarSpace, searchBox);
        toolbar.setAlignment(Pos.CENTER_LEFT);

        // ============================================================
        // EVENT 1
        // ============================================================

        Label date1 = new Label("JUL\n15");
        date1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        date1.setAlignment(Pos.CENTER);
        date1.setPadding(new Insets(4));
        date1.setStyle("-fx-background-color: black; -fx-border-color: #d8dce2; -fx-background-radius: 4; -fx-border-radius: 4;");

        Label type1 = new Label("WORKSHOP");
        type1.setFont(Font.font("Arial", FontWeight.BOLD, 8));
        type1.setTextFill(Color.web("#20B86B"));
        type1.setStyle("-fx-background-color: #e9effb; -fx-padding: 3 5 3 5;");

        Label name1 = new Label("Summer Bootcamp Kickoff");
        name1.setPrefWidth(190);
        name1.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label capacity1 = new Label("♙  45/50");
        capacity1.setFont(Font.font("Arial", 9));

        Label description1 = new Label("Join us for a high-energy outdoor bootcamp to kickstart your summer fitness goals. Led by our...");
        description1.setPrefWidth(190);
        description1.setFont(Font.font("Arial", 9));
        description1.setTextFill(Color.web("#555555"));

        Label time1 = new Label("◷  08:00 AM - 10:00 AM");
        time1.setFont(Font.font("Arial", 9));

        Label location1 = new Label("⌖  City Park (North Entrance)");
        location1.setFont(Font.font("Arial", 9));

        Label line1 = new Label();
        line1.setPrefHeight(1);
        line1.setStyle("-fx-background-color: #dddddd;");

        Label price1 = new Label("Free");
        price1.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Button manage1 = new Button("Manage →");
        manage1.setStyle("-fx-background-color: transparent; -fx-text-fill: #123B9B; -fx-font-size: 9px; -fx-font-weight: bold;");

        Label bottomSpace1 = new Label();
        bottomSpace1.setPrefWidth(70);

        HBox bottom1 = new HBox(price1, bottomSpace1, manage1);
        bottom1.setAlignment(Pos.CENTER_LEFT);

        VBox details1 = new VBox(7, type1, name1, capacity1, description1, time1, location1, line1, bottom1);
        details1.setPrefWidth(190);

        HBox eventContent1 = new HBox(12, details1);

        VBox event1 = new VBox(eventContent1);
        event1.setPadding(new Insets(10));
        event1.setPrefWidth(350);
        event1.setPrefHeight(210);
        event1.setStyle("-fx-background-color: white; -fx-border-color: #d7dce4; -fx-border-radius: 12; -fx-background-radius: 12;");

        // ============================================================
        // EVENT 2
        // ============================================================

        Label date2 = new Label("JUL\n18");
        date2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        date2.setAlignment(Pos.CENTER);
        date2.setPadding(new Insets(4));
        date2.setStyle("-fx-background-color: white; -fx-border-color: #d8dce2; -fx-background-radius: 4; -fx-border-radius: 4;");

        Label type2 = new Label("CLASS");
        type2.setFont(Font.font("Arial", FontWeight.BOLD, 8));
        type2.setTextFill(Color.web("#20B86B"));
        type2.setStyle("-fx-background-color: #e9effb; -fx-padding: 3 5 3 5;");

        Label name2 = new Label("Mindfulness Yoga Session");
        name2.setPrefWidth(250);
        name2.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label capacity2 = new Label("♙  12/20");
        capacity2.setFont(Font.font("Arial", 9));

        Label time2 = new Label("◷  18:30 - 19:45");
        time2.setFont(Font.font("Arial", 9));

        Label location2 = new Label("⌖  Studio A");
        location2.setFont(Font.font("Arial", 9));

        Label line2 = new Label();
        line2.setPrefHeight(1);
        line2.setStyle("-fx-background-color: #dddddd;");

        Label price2 = new Label("$15");
        price2.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Button manage2 = new Button("Manage");
        manage2.setStyle("-fx-background-color: transparent; -fx-text-fill: #123B9B; -fx-font-size: 9px; -fx-font-weight: bold;");

        Label bottomSpace2 = new Label();
        bottomSpace2.setPrefWidth(50);

        HBox bottom2 = new HBox(price2, bottomSpace2, manage2);
        bottom2.setAlignment(Pos.CENTER_LEFT);

        VBox event2 = new VBox(8, date2, type2, name2, capacity2, time2, location2, line2, bottom2);
        event2.setPadding(new Insets(10));
        event2.setPrefWidth(320);
        event2.setPrefHeight(250);
        event2.setStyle("-fx-background-color: white; -fx-border-color: #d7dce4; -fx-border-radius: 12; -fx-background-radius: 12;");

        // ============================================================
        // EVENT 3
        // ============================================================

        Label date3 = new Label("JUL\n22");
        date3.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        date3.setAlignment(Pos.CENTER);
        date3.setPadding(new Insets(4));
        date3.setStyle("-fx-background-color: white; -fx-border-color: #d8dce2; -fx-background-radius: 4; -fx-border-radius: 4;");

        Label full3 = new Label("● FULL");
        full3.setTextFill(Color.RED);
        full3.setFont(Font.font("Arial", FontWeight.BOLD, 8));

        Label dateSpace3 = new Label();
        dateSpace3.setPrefWidth(70);

        HBox dateRow3 = new HBox(date3, dateSpace3, full3);

        Label type3 = new Label("SEMINAR");
        type3.setFont(Font.font("Arial", FontWeight.BOLD, 8));
        type3.setTextFill(Color.web("#20B86B"));
        type3.setStyle("-fx-background-color: #e9effb; -fx-padding: 3 5 3 5;");

        Label name3 = new Label("Fueling for Performance");
        name3.setPrefWidth(250);
        name3.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label capacity3 = new Label("♙  30/30");
        capacity3.setFont(Font.font("Arial", 9));

        Label time3 = new Label("◷  19:00 - 20:30");
        time3.setFont(Font.font("Arial", 9));

        Label location3 = new Label("⌖  Main Lounge");
        location3.setFont(Font.font("Arial", 9));

        Label line3 = new Label();
        line3.setPrefHeight(1);
        line3.setStyle("-fx-background-color: #dddddd;");

        Label price3 = new Label("Free");
        price3.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Button manage3 = new Button("Manage");
        manage3.setStyle("-fx-background-color: transparent; -fx-text-fill: #123B9B; -fx-font-size: 9px; -fx-font-weight: bold;");

        Label bottomSpace3 = new Label();
        bottomSpace3.setPrefWidth(50);

        HBox bottom3 = new HBox(price3, bottomSpace3, manage3);
        bottom3.setAlignment(Pos.CENTER_LEFT);

        VBox event3 = new VBox(8, dateRow3, type3, name3, capacity3, time3, location3, line3, bottom3);
        event3.setPadding(new Insets(10));
        event3.setPrefWidth(300);
        event3.setPrefHeight(210);
        event3.setStyle("-fx-background-color: white; -fx-border-color: #d7dce4; -fx-border-radius: 12; -fx-background-radius: 12;");

        HBox eventRow1 = new HBox(12, event1, event2,event3);
        eventRow1.setAlignment(Pos.CENTER);

       // HBox eventRow2 = new HBox(event3);
       // eventRow2.setAlignment(Pos.TOP_LEFT);


        VBox mainContent = new VBox(15, titleRow, subtitle, toolbar, eventRow1);
        mainContent.setPadding(new Insets(90));
        mainContent.setStyle("-fx-background-color: #111d35;");


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #f7f8fa;");
        root.setCenter(scrollPane);


        Scene scene = new Scene(root, 1550, 800);

        stage.setScene(scene);
        stage.setTitle("Events");
        stage.show();
    }
}

