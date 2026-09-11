package com.flexforce.view.User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class User_membership_plan extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        
        
        
        BorderPane root = new BorderPane();

        VBox left = new VBox(12);
        left.setPadding(new Insets(20));
        left.setPrefWidth(190);
        left.setStyle("-fx-background-color: #171c18;");

        Label logo = new Label("FitCircle");
        logo.setStyle(
                "-fx-text-fill: #35e06f;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        Label slogan = new Label("COMPETE.\nGROW.\nTOGETHER.");
        slogan.setStyle(
                "-fx-text-fill: #9ca89f;" +
                "-fx-font-size: 9px;"
        );

        Button dashboard = new Button("▦   Dashboard");
        Button connect = new Button("⚒   Connect");
        Button memberships = new Button("▣   Memberships");
        Button settings = new Button("⚙   Settings");

        dashboard.setMaxWidth(Double.MAX_VALUE);
        connect.setMaxWidth(Double.MAX_VALUE);
        memberships.setMaxWidth(Double.MAX_VALUE);
        settings.setMaxWidth(Double.MAX_VALUE);

        dashboard.setAlignment(Pos.CENTER_LEFT);
        connect.setAlignment(Pos.CENTER_LEFT);
        memberships.setAlignment(Pos.CENTER_LEFT);
        settings.setAlignment(Pos.CENTER_LEFT);

     

        VBox menu = new VBox(5);

        menu.getChildren().addAll(
                dashboard,
                connect,
                memberships,
                settings
        );

        left.getChildren().addAll(
                logo,
                slogan,
                menu
        );

        root.setLeft(left);

        VBox main = new VBox(20);
        main.setPadding(new Insets(25, 30, 25, 30));
        main.setStyle("-fx-background-color: #0b100c;");

        HBox top = new HBox(10);
        top.setAlignment(Pos.CENTER_RIGHT);

        Button notification = new Button("♧");
        Button help = new Button("?");
        Button profile = new Button("◉");

        top.getChildren().addAll(
                notification,
                help,
                profile
        );

        Label title = new Label("Membership Plans");

        title.setStyle(
                "-fx-text-fill: #e6f0e7;" +
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle = new Label(
                "Choose the membership plan that fits your health and wellness goals"
        );

        subtitle.setStyle(
                "-fx-text-fill: #aab5ac;" +
                "-fx-font-size: 14px;"
        );

        VBox heading = new VBox(5);

        heading.getChildren().addAll(
                title,
                subtitle
        );

        HBox cards = new HBox(20);

        cards.setAlignment(Pos.CENTER);

        VBox basic = new VBox(15);
        basic.setPadding(new Insets(20));
        basic.setPrefWidth(230);
        basic.setPrefHeight(320);

        basic.setStyle(
                "-fx-background-color: #101610;" +
                "-fx-border-color: #29332c;" +
                "-fx-border-radius: 6;"
        );

        Label basicTitle = new Label("Basic");

        basicTitle.setStyle(
                "-fx-text-fill: #e5eee7;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Label basicPrice = new Label("₹499 / month");

        basicPrice.setStyle(
                "-fx-text-fill: #dce6de;" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;"
        );

        Label basicText = new Label(
                "✓ Facility access\n\n" +
                "✓ Basic workout tracking\n\n" +
                "✓ Basic progress tracking"
        );

        basicText.setWrapText(true);

        basicText.setStyle(
                "-fx-text-fill: #bdc8bf;" +
                "-fx-font-size: 13px;"
        );

        Button basicButton = new Button("Choose Plan");

        basicButton.setMaxWidth(Double.MAX_VALUE);

        basic.getChildren().addAll(
                basicTitle,
                basicPrice,
                basicText,
                basicButton
        );
        

        VBox standard = new VBox(15);
        standard.setPadding(new Insets(20));
        standard.setPrefWidth(230);
        standard.setPrefHeight(320);

        standard.setStyle(
                "-fx-background-color: #101811;" +
                "-fx-border-color: #29332c;" +
                "-fx-border-radius: 6;"
        );

        Label standardTitle = new Label("Standard");

        standardTitle.setStyle(
                "-fx-text-fill: #228e46;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Label standardPrice = new Label("₹999 / month");

        standardPrice.setStyle(
                "-fx-text-fill: #dce6de;" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;"
        );

        Label standardText = new Label(
                "✓ Everything in Basic\n\n" +
                "✓ Personalized workout plans\n\n" +
                "✓ Progress analytics\n\n" +
                "✓ Trainer support"
        );

        standardText.setWrapText(true);

        standardText.setStyle(
                "-fx-text-fill: #bdc8bf;" +
                "-fx-font-size: 13px;"
        );

        Button standardButton = new Button("Choose Plan");

        standardButton.setMaxWidth(Double.MAX_VALUE);

        standard.getChildren().addAll(
                standardTitle,
                standardPrice,
                standardText,
                standardButton
        );

        VBox premium = new VBox(15);
        premium.setPadding(new Insets(20));
        premium.setPrefWidth(230);
        premium.setPrefHeight(320);

        premium.setStyle(
                "-fx-background-color: #101610;" +
                "-fx-border-color: #29332c;" +
                "-fx-border-radius: 6;"
        );

        Label premiumTitle = new Label("Premium");

        premiumTitle.setStyle(
                "-fx-text-fill: #a9c7ff;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Label premiumPrice = new Label("₹1,499 / month");

        premiumPrice.setStyle(
                "-fx-text-fill: #dce6de;" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;"
        );

        Label premiumText = new Label(
                "✓ Everything in Standard\n\n" +
                "✓ Personal trainer support\n\n" +
                "✓ Advanced analytics\n\n" +
                "✓ Diet guidance\n\n" +
                "✓ Priority support"
        );

        premiumText.setWrapText(true);

        premiumText.setStyle(
                "-fx-text-fill: #bdc8bf;" +
                "-fx-font-size: 13px;"
        );

        Button premiumButton = new Button("Choose Plan");

        premiumButton.setMaxWidth(Double.MAX_VALUE);

        premium.getChildren().addAll(
                premiumTitle,
                premiumPrice,
                premiumText,
                premiumButton
        );

        VBox annual = new VBox(15);
        annual.setPadding(new Insets(20));
        annual.setPrefWidth(230);
        annual.setPrefHeight(320);

        annual.setStyle(
                "-fx-background-color: #101610;" +
                "-fx-border-color: #29332c;" +
                "-fx-border-radius: 6;"
        );

        Label annualTitle = new Label("Annual");

        annualTitle.setStyle(
                "-fx-text-fill: #ffaaa0;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Label annualPrice = new Label("₹9,999 / year");

        annualPrice.setStyle(
                "-fx-text-fill: #dce6de;" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;"
        );

        Label annualText = new Label(
                "☆ Full premium features\n\n" +
                "↗ Better value compared with monthly plans\n\n" +
                "Save ₹7,989 annually"
        );

        annualText.setWrapText(true);

        annualText.setStyle(
                "-fx-text-fill: #bdc8bf;" +
                "-fx-font-size: 13px;"
        );

        Button annualButton = new Button("Choose Plan");

        annualButton.setMaxWidth(Double.MAX_VALUE);

        annual.getChildren().addAll(
                annualTitle,
                annualPrice,
                annualText,
                annualButton
        );

        cards.getChildren().addAll(
                basic,
                standard,
                premium,
                annual
        );

      

        Label already = new Label(
                "Already have a membership?"
        );

        already.setStyle(
                "-fx-text-fill: #b7c2ba;" +
                "-fx-font-size: 13px;"
        );

        Button viewMembership = new Button(
                "◉  View My Membership"
        );

        viewMembership.setPrefWidth(180);

        VBox bottom = new VBox(10);

        bottom.setAlignment(Pos.CENTER);

        bottom.getChildren().addAll(
                already,
                viewMembership
        );

        main.getChildren().addAll(
                top,
                heading,
                cards,
                bottom
        );

        root.setCenter(main);

        Scene scene = new Scene(
                root,
                1280,
                720
        );

        stage.setTitle("FitCircle - Membership Plans");

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(650);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

