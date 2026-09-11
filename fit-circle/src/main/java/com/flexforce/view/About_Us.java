package com.flexforce.view;


import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class About_Us {


    // =========================================================
    // COLORS
    // =========================================================

    private static final String BG = "#0B0D0F";
    private static final String CARD = "#161B21";
    private static final String CARD_BORDER = "#29313A";
    private static final String WHITE = "#FFFFFF";
    private static final String GRAY = "#AEB5BC";
    private static final String LIME = "#B6FF00";

    // =========================================================
    // CREATE ABOUT US PAGE
    // =========================================================

    public VBox createContent() {

        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(35, 45, 40, 45));

        mainContent.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // PAGE TITLE
        // =====================================================

        Label title = new Label("About FitCircle");
        title.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle = new Label(
                "Fitness • Community • Challenge"
        );

        subtitle.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 15px;"
        );

        VBox heading = new VBox(5);
        heading.getChildren().addAll(title, subtitle);

        // =====================================================
        // PROJECT DESCRIPTION
        // =====================================================

        VBox projectCard = createCard();

        Label projectTitle = createSectionTitle("About FitCircle");

        Label projectDescription = new Label(
                "FitCircle is a fitness and community platform designed "
                + "to help users stay active, connect with fitness clubs, "
                + "participate in challenges and activities, and track "
                + "their fitness journey in one place."
        );

        projectDescription.setWrapText(true);
        projectDescription.setMaxWidth(1000);

        projectDescription.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 15px;" +
                "-fx-line-spacing: 5px;"
        );

        projectCard.getChildren().addAll(
                projectTitle,
                projectDescription
        );

        // =====================================================
        // TEAM FLEXFORCE
        // =====================================================

        VBox teamCard = createCard();

        Label teamTitle = createSectionTitle(
                "Meet Our Team — FlexForce"
        );

        Label teamMessage = new Label(
                "FitCircle is designed and developed by Team FlexForce "
                + "with teamwork, creativity, learning and dedication."
        );

        teamMessage.setWrapText(true);
        teamMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 4px;"
        );

        VBox teamNames = createNamesBox(
                "Aditya Pattewar",
                "Sai Jagdale",
                "Tejaswi Jagdale",
                "Shravani Dumbre"
        );

        teamCard.getChildren().addAll(
                teamTitle,
                teamMessage,
                teamNames
        );

        // =====================================================
        // SPECIAL THANKS - NADEEM DADA
        // =====================================================

      /*   VBox nadeemCard = createCard();

        Label nadeemTitle = createSectionTitle(
                "Special Thanks"
        );

        Label nadeemName = new Label(
                "Nadeem Dada"
        );

        nadeemName.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        Label nadeemMessage = new Label(
                "A very special thanks to Nadeem Dada for continuously "
                + "encouraging us, supporting us throughout our journey, "
                + "and motivating us to keep learning, improving, "
                + "and giving our best."
        );

        nadeemMessage.setWrapText(true);
        nadeemMessage.setMaxWidth(900);

        nadeemMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 5px;"
        );

        nadeemCard.getChildren().addAll(
                nadeemTitle,
                nadeemName,
                nadeemMessage
        );*/

        // =====================================================
        // SPECIAL THANKS - SHASHI SIR / CORE2WEB
        // =====================================================

        VBox shashiCard = createCard();

        Label thanksTitle = createSectionTitle(
                "Special Thanks"
        );

        Label shashiName = new Label(
                "Shashi Sir"
        );

        shashiName.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        Label core2web = new Label(
                "Core2Web"
        );

        core2web.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;"
        );

        Label shashiMessage = new Label(
                "A heartfelt thank you to Shashi Sir and Core2Web "
                + "for the valuable guidance, learning environment, "
                + "support and encouragement throughout the project."
        );

        shashiMessage.setWrapText(true);
        shashiMessage.setMaxWidth(700);

        shashiMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 4px;"
        );

        ImageView shashiImage = createShashiImage();

        HBox shashiContent = new HBox(25);
        shashiContent.setAlignment(Pos.CENTER_LEFT);

        VBox shashiInfo = new VBox(8);
        shashiInfo.setAlignment(Pos.CENTER_LEFT);

        shashiInfo.getChildren().addAll(
                shashiName,
                core2web,
                shashiMessage
        );

        shashiContent.getChildren().addAll(
                shashiImage,
                shashiInfo
        );

        shashiCard.getChildren().addAll(
                thanksTitle,
                shashiContent
        );

        // =====================================================
        // INSTRUCTORS
        // =====================================================

        VBox instructorsCard = createCard();

        Label instructorsTitle = createSectionTitle(
                "Thanks to Our Instructors"
        );

        Label instructorsMessage = new Label(
                "We sincerely thank our instructors for their "
                + "continuous guidance, teaching and support."
        );

        instructorsMessage.setWrapText(true);

        instructorsMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;"
        );

        VBox instructorNames = createNamesBox(
                "Sachin Sir",
                "Pramod Sir",
                "Akshay Sir"
        );

        instructorsCard.getChildren().addAll(
                instructorsTitle,
                instructorsMessage,
                instructorNames
        );

        // =====================================================
        // SUPER MENTORS
        // =====================================================

        VBox superMentorsCard = createCard();

        Label superMentorsTitle = createSectionTitle(
                "Thanks to Our Super Mentors"
        );

        Label superMentorsMessage = new Label(
                "Special thanks to our Super Mentors for their "
                + "valuable guidance, feedback and motivation."
        );

        superMentorsMessage.setWrapText(true);

        superMentorsMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;"
        );

        VBox superMentorNames = createNamesBox(
                "Shiv Sir",
                "Subodh Sir"
        );

        superMentorsCard.getChildren().addAll(
                superMentorsTitle,
                superMentorsMessage,
                superMentorNames
        );

        // =====================================================
        // SPECIAL THANKS - NADEEM DADA
        // =====================================================

        VBox nadeemCard = createCard();

        Label nadeemTitle = createSectionTitle(
                "Special Thanks"
        );

        Label nadeemName = new Label(
                "Nadeem Dada"
        );

        nadeemName.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        Label nadeemMessage = new Label(
                "A very special thanks to Nadeem Dada for continuously "
                + "encouraging us, supporting us throughout our journey, "
                + "and motivating us to keep learning, improving, "
                + "and giving our best."
        );

        nadeemMessage.setWrapText(true);
        nadeemMessage.setMaxWidth(900);

        nadeemMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 5px;"
        );

        nadeemCard.getChildren().addAll(
                nadeemTitle,
                nadeemName,
                nadeemMessage
        );

        // =====================================================
        // MENTORS & TEAM LEADS
        // =====================================================

        VBox mentorsCard = createCard();

        Label mentorsTitle = createSectionTitle(
                "Thanks to Our Mentors & Team Leads"
        );

        Label mentorsMessage = new Label(
                "We are grateful to all our mentors and Team Leads "
                + "for their constant support, guidance, feedback and "
                + "encouragement throughout our learning and project journey."
        );

        mentorsMessage.setWrapText(true);
        mentorsMessage.setMaxWidth(1000);

        mentorsMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 4px;"
        );

        mentorsCard.getChildren().addAll(
                mentorsTitle,
                mentorsMessage
        );

        // =====================================================
        // FINAL THANK YOU
        // =====================================================

        VBox finalCard = new VBox(10);
        finalCard.setAlignment(Pos.CENTER);
        finalCard.setPadding(new Insets(25));

        finalCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + CARD_BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        Label finalTitle = new Label(
                "Thank You"
        );

        finalTitle.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        Label finalMessage = new Label(
                "Thank you to everyone who contributed to our "
                + "learning journey and helped us build FitCircle."
        );

        finalMessage.setWrapText(true);

        finalMessage.setStyle(
                "-fx-text-fill: " + GRAY + ";" +
                "-fx-font-size: 14px;"
        );

        finalCard.getChildren().addAll(
                finalTitle,
                finalMessage
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        mainContent.getChildren().addAll(
                heading,
                projectCard,
                teamCard,
                //nadeemCard,
                shashiCard,
                instructorsCard,
                superMentorsCard,
                 nadeemCard,
                mentorsCard,
                finalCard
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(mainContent);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
        );

        // =====================================================
        // PAGE
        // =====================================================

        VBox page = new VBox(scrollPane);

        page.setFillWidth(true);

        VBox.setVgrow(
                scrollPane,
                javafx.scene.layout.Priority.ALWAYS
        );

        return page;
    }

    // =========================================================
    // CREATE CARD
    // =========================================================

    private VBox createCard() {

        VBox card = new VBox(15);

        card.setPadding(
                new Insets(25)
        );

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + CARD_BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        return card;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private Label createSectionTitle(String text) {

        Label label = new Label(text);

        label.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;"
        );

        return label;
    }

    // =========================================================
    // NAME BOX
    // =========================================================

    private VBox createNamesBox(String... names) {

        VBox box = new VBox(10);

        for (String name : names) {

            Label label = new Label(
                    "•  " + name
            );

            label.setStyle(
                    "-fx-text-fill: " + WHITE + ";" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;"
            );

            box.getChildren().add(label);
        }

        return box;
    }

    // =========================================================
    // SHASHI SIR IMAGE
    // =========================================================

    private ImageView createShashiImage() {

        ImageView imageView = new ImageView();

        try {

            InputStream imageStream = getClass()
                    .getResourceAsStream(
                            "/assets/images/shashi sir image.jpg"
                    );

            if (imageStream != null) {

                Image image = new Image(imageStream);

                imageView.setImage(image);

                imageView.setFitWidth(180);
                imageView.setFitHeight(180);

                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);

                System.out.println(
                        "Shashi Sir image loaded successfully."
                );

            } else {

                System.out.println(
                        "ERROR: Shashi Sir image not found!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR loading Shashi Sir image: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return imageView;
    }
}