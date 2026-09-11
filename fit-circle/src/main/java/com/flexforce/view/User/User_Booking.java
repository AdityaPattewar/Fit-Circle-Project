package com.flexforce.view.User;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class User_Booking extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:#080808;");


        Label title = new Label("Smart Booking & Schedule");
        title.setStyle("-fx-text-fill:#F5F5F5;-fx-font-size:34px;-fx-font-weight:bold;");

        Label subtitle = new Label("Manage your week with AI-optimized insights.");
        subtitle.setStyle("-fx-text-fill:#BFC3CC;-fx-font-size:18px;");

        VBox titleBox = new VBox(5,title,subtitle);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Button sync = new Button("▣  Sync Calendar");
        sync.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:#3A3A3A;-fx-border-radius:5;-fx-background-radius:5;-fx-padding:10 18;-fx-font-size:14px;");

        BorderPane header = new BorderPane();
        header.setLeft(titleBox);
        header.setRight(sync);

        Label scheduleTitle = new Label("♙  AI Smart Schedule");
        scheduleTitle.setStyle("-fx-text-fill:#F5F5F5;-fx-font-size:23px;-fx-font-weight:bold;");

        Label scheduleSub = new Label("Your personalized fitness plan for this week");
        scheduleSub.setStyle("-fx-text-fill:#AEB1BA;-fx-font-size:14px;");

        VBox scheduleTitleBox = new VBox(5,scheduleTitle,scheduleSub);

        Button optimize = new Button("✦  Optimize");
        optimize.setStyle("-fx-background-color:#087FF5;-fx-text-fill:white;-fx-font-size:15px;-fx-padding:10 18;-fx-background-radius:6;");

        BorderPane scheduleHeader = new BorderPane();
        scheduleHeader.setLeft(scheduleTitleBox);
        scheduleHeader.setRight(optimize);

        VBox monday = dayCard("MON 12","YOGA FLOW","Completed","#103A3B");
        VBox tuesday = dayCard("TUE 13","CYCLING","6:00 PM","#152D4A");
        VBox wednesday = dayCard("WED 14","RECOVERY\nDAY","Recommended","#392C2C");
        VBox thursday = dayCard("THU 15","MARATHON\nPREP","6:30 AM","#123D40");
        VBox friday = dayCard("FRI 16","RUN","5.0 km","#182F45");

        HBox days = new HBox(12,monday,tuesday,wednesday,thursday,friday);
        days.setAlignment(Pos.CENTER_LEFT);

        VBox schedule = new VBox(18,scheduleHeader,days);
        schedule.setStyle("-fx-background-color:#1A1A1A;-fx-border-color:#303030;-fx-border-radius:14;-fx-background-radius:14;-fx-padding:22;");

        Label findTitle = new Label("Find a Class");
        findTitle.setStyle("-fx-text-fill:#F5F5F5;-fx-font-size:23px;-fx-font-weight:bold;");

        TextField search = new TextField("⌕  Activity, Club...");
        search.setPrefWidth(210);
        search.setStyle("-fx-background-color:#111111;-fx-text-fill:#777777;-fx-border-color:#343434;-fx-border-radius:5;-fx-background-radius:5;-fx-padding:10;");

        Button filter = new Button("☰");
        filter.setStyle("-fx-background-color:#111111;-fx-text-fill:white;-fx-border-color:#343434;-fx-border-radius:5;-fx-background-radius:5;-fx-padding:10;");

        HBox searchBox = new HBox(10,search,filter);
        searchBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane findHeader = new BorderPane();
        findHeader.setLeft(findTitle);
        findHeader.setRight(searchBox);

        VBox yoga = classRow("♨","Yoga Flow","Instructor: Sarah J. • ZenFit Studios","18:00 - 19:00","4 seats left • 1.8 km",false);

        VBox hiit = classRow("⚔","HIIT Training","Instructor: Mike T. • FitCore","19:30 - 20:15","12 seats left • 2.4 km",false);

        VBox cycling = classRow("♧","Cycling Group Ride","Instructor: Elena R. • Velocity","06:00 - 07:00 Tomorrow","2 seats left • 3.1 km",true);

        VBox classes = new VBox(14,findHeader,yoga,hiit,cycling);
        classes.setStyle("-fx-background-color:#1A1A1A;-fx-border-color:#303030;-fx-border-radius:14;-fx-background-radius:14;-fx-padding:25;");

        Label suggestionTitle = new Label("⇄  Can't attend your booking?");
        suggestionTitle.setStyle("-fx-text-fill:white;-fx-font-size:16px;-fx-font-weight:bold;");

        Label bookingName = new Label("Yoga Flow - Monday 7:00 PM");
        bookingName.setStyle("-fx-text-fill:#CCCCCC;-fx-font-size:14px;");

        Label ai = new Label("♙  AI Suggests: Wed 6:30 PM (94% Match)");
        ai.setStyle("-fx-text-fill:#00D9E8;-fx-font-size:13px;");

        VBox suggestionText = new VBox(6,bookingName,ai);

        Button better = new Button("Find Better Slot");
        better.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:#333333;-fx-border-radius:5;-fx-background-radius:5;-fx-padding:10 18;");

        BorderPane suggestionBottom = new BorderPane();
        suggestionBottom.setLeft(suggestionText);
        suggestionBottom.setRight(better);

        VBox suggestion = new VBox(8,suggestionTitle,suggestionBottom);
        suggestion.setStyle("-fx-background-color:#1A1A1A;-fx-border-color:#333333;-fx-border-radius:12;-fx-background-radius:12;-fx-padding:20;");

        VBox mainContent = new VBox(24,header,schedule,classes,suggestion);
        mainContent.setStyle("-fx-background-color:#080808;-fx-padding:40 25 35 40;");

        ScrollPane centerScroll = new ScrollPane(mainContent);
        centerScroll.setFitToWidth(true);
        centerScroll.setStyle("-fx-background-color:#080808;-fx-background:#080808;");

        root.setCenter(centerScroll);


        Label insightTitle = new Label("⌁  AI INSIGHT");
        insightTitle.setStyle("-fx-text-fill:white;-fx-font-size:17px;-fx-font-weight:bold;");

        Label insightText = new Label("You have 4 activities scheduled this\nweek. Your current workload is\nmoderate. Consider keeping\nWednesday as a recovery day.");
        insightText.setStyle("-fx-text-fill:#B9BDC7;-fx-font-size:14px;");

        VBox insight = new VBox(15,insightTitle,insightText);
        insight.setStyle("-fx-background-color:#111111;-fx-border-color:#333333;-fx-border-radius:14;-fx-background-radius:14;-fx-padding:22;");

        Label upcomingTitle = new Label("Upcoming Bookings");
        upcomingTitle.setStyle("-fx-text-fill:white;-fx-font-size:17px;-fx-font-weight:bold;");

        Label viewAll = new Label("View All");
        viewAll.setStyle("-fx-text-fill:#087FF5;-fx-font-size:14px;");

        BorderPane upcomingHeader = new BorderPane();
        upcomingHeader.setLeft(upcomingTitle);
        upcomingHeader.setRight(viewAll);

        Label marathon = new Label("Marathon Training\n\nTomorrow • 06:30 AM");
        marathon.setStyle("-fx-text-fill:#DDDDDD;-fx-font-size:14px;");

        Label confirmed = new Label("CONFIRMED");
        confirmed.setStyle("-fx-background-color:#143839;-fx-text-fill:#00D9E8;-fx-padding:10;-fx-background-radius:5;");

        BorderPane marathonBox = new BorderPane();
        marathonBox.setLeft(marathon);
        marathonBox.setRight(confirmed);

        Label core = new Label("Core Crusher\n\nThu 15 • 18:00 PM");
        core.setStyle("-fx-text-fill:#DDDDDD;-fx-font-size:14px;");

        Label wait = new Label("WAITLIST #2");
        wait.setStyle("-fx-background-color:#303030;-fx-text-fill:#D5D5D5;-fx-padding:10;-fx-background-radius:5;");

        BorderPane coreBox = new BorderPane();
        coreBox.setLeft(core);
        coreBox.setRight(wait);

        VBox upcoming = new VBox(15,upcomingHeader,marathonBox,coreBox);
        upcoming.setStyle("-fx-background-color:#1A1A1A;-fx-border-color:#333333;-fx-border-radius:14;-fx-background-radius:14;-fx-padding:20;");

        Label waitTitle = new Label("Yoga Masterclass (Full)");
        waitTitle.setStyle("-fx-text-fill:white;-fx-font-size:16px;-fx-font-weight:bold;");

        Label waitText = new Label("AI will notify you if a spot opens. Position #3.");
        waitText.setStyle("-fx-text-fill:#AEB2BB;-fx-font-size:12px;");

        Button smartWait = new Button("♧  Join Smart Waitlist");
        smartWait.setStyle("-fx-background-color:transparent;-fx-text-fill:#087FF5;-fx-border-color:#363636;-fx-border-radius:5;-fx-padding:10;");

        VBox waitlist = new VBox(13,waitTitle,waitText,smartWait);
        waitlist.setStyle("-fx-background-color:#111111;-fx-border-color:#383838;-fx-border-style:dashed;-fx-border-radius:12;-fx-background-radius:12;-fx-padding:20;");

        Label nearbyTitle = new Label("Nearby Spots                         ◫");
        nearbyTitle.setStyle("-fx-text-fill:white;-fx-font-size:15px;-fx-font-weight:bold;");

        Label studio = new Label("ZenFit Studio                         1.8 km");
        studio.setStyle("-fx-text-fill:#DDDDDD;-fx-font-size:13px;");

        Label gym = new Label("FitCore Gym                            2.4 km");
        gym.setStyle("-fx-text-fill:#DDDDDD;-fx-font-size:13px;");

        VBox nearby = new VBox(10,nearbyTitle,studio,gym);
        nearby.setStyle("-fx-background-color:#1A1A1A;-fx-border-color:#333333;-fx-border-radius:12;-fx-background-radius:12;-fx-padding:15;");

        VBox rightContent = new VBox(22,insight,upcoming,waitlist,nearby);
        rightContent.setStyle("-fx-background-color:#080808;-fx-padding:40 25 25 0;");

        ScrollPane rightScroll = new ScrollPane(rightContent);
        rightScroll.setFitToWidth(true);
        rightScroll.setStyle("-fx-background-color:#080808;-fx-background:#080808;");

        root.setRight(rightScroll);

        Scene scene = new Scene(root,1000,600);

        stage.setTitle("Smart Booking & Schedule");
        stage.setScene(scene);
        stage.show();
    }

    VBox dayCard(String day,String activity,String time,String activityColor) {

        Label dayLabel = new Label(day);
        dayLabel.setStyle("-fx-text-fill:#B8BBC4;-fx-font-size:13px;");

        Label activityLabel = new Label(activity);
        activityLabel.setStyle("-fx-background-color:"+activityColor+";-fx-text-fill:#00DDE8;-fx-font-size:12px;-fx-padding:10;-fx-background-radius:4;");

        Label timeLabel = new Label(time);
        timeLabel.setStyle("-fx-text-fill:#D0D0D0;-fx-font-size:13px;");

        VBox box = new VBox(12,dayLabel,activityLabel,timeLabel);
        box.setStyle("-fx-background-color:#191919;-fx-border-color:#2D2D2D;-fx-border-radius:8;-fx-background-radius:8;-fx-padding:14;");
        box.setPrefWidth(130);
        box.setMinHeight(145);

        return box;
    }

    VBox classRow(String icon,String name,String instructor,String time,String seats,boolean active) {

        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-text-fill:#00D9E8;-fx-font-size:22px;");

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-text-fill:white;-fx-font-size:17px;-fx-font-weight:bold;");

        Label instructorLabel = new Label(instructor);
        instructorLabel.setStyle("-fx-text-fill:#B7BAC3;-fx-font-size:13px;");

        VBox nameBox = new VBox(5,nameLabel,instructorLabel);

        Label timeLabel = new Label(time);
        timeLabel.setStyle("-fx-text-fill:white;-fx-font-size:15px;");

        Label seatsLabel = new Label(seats);
        seatsLabel.setStyle("-fx-text-fill:#B5B8C0;-fx-font-size:13px;");

        VBox timeBox = new VBox(5,timeLabel,seatsLabel);

        Button book = new Button("Book");

        if(active) {
            book.setStyle("-fx-background-color:#087FF5;-fx-text-fill:white;-fx-background-radius:5;-fx-padding:10 18;");
        } else {
            book.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:#3A3A3A;-fx-border-radius:5;-fx-background-radius:5;-fx-padding:10 18;");
        }

        HBox leftSide = new HBox(18,iconLabel,nameBox);
        leftSide.setAlignment(Pos.CENTER_LEFT);

        HBox rightSide = new HBox(20,timeBox,book);
        rightSide.setAlignment(Pos.CENTER_RIGHT);

        BorderPane rowContent = new BorderPane();
        rowContent.setLeft(leftSide);
        rowContent.setRight(rightSide);

        VBox row = new VBox(rowContent);
        row.setStyle("-fx-background-color:#191919;-fx-border-color:#2E2E2E;-fx-border-radius:8;-fx-background-radius:8;-fx-padding:14;");

        return row;
    }
}