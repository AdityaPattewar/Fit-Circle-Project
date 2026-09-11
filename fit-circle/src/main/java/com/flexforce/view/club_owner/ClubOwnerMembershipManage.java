package com.flexforce.view.club_owner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ClubOwnerMembershipManage extends Application {

    // =========================================================
    // COLORS
    // =========================================================

    private final String BG = "#0d0f11";
    private final String CARD = "#111416";
    private final String INPUT = "#303236";
    private final String BORDER = "#454a38";
    private final String TEXT = "#eeeeee";
    private final String MUTED = "#c9c9b5";
    private final String GREEN = "#baff00";
    private final String RED = "#ffaaa7";

    // =========================================================
    // VARIABLES
    // =========================================================

    private VBox root;
    private FlowPane cardsContainer;
    private TextField searchField;

    private final ObservableList<MembershipPlan> plans =
            FXCollections.observableArrayList();

    // =========================================================
    // START
    // =========================================================

    @Override
    public void start(Stage stage) {

        createSamplePlans();

        root = new VBox();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // TOP BAR
        HBox topBar = createTopBar();

        // MAIN SCROLL CONTENT
        ScrollPane scrollPane = createScrollPane();

        root.getChildren().addAll(
                topBar,
                scrollPane
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        Scene scene =
                new Scene(root, 1200, 700);

        stage.setTitle(
                "Membership Management"
        );

        stage.setScene(scene);
        stage.show();
    }

    // =========================================================
    // SAMPLE PLANS
    // =========================================================

    private void createSamplePlans() {

        plans.add(
                new MembershipPlan(
                        "Monthly Membership",
                        999,
                        "month",
                        "Basic access to gym equipment.",
                        List.of(
                                "Gym access",
                                "Locker use"
                        ),
                        true,
                        false
                )
        );

        plans.add(
                new MembershipPlan(
                        "Quarterly Membership",
                        2499,
                        "3 months",
                        "Great for consistent gym-goers.",
                        List.of(
                                "Gym access",
                                "Locker use",
                                "1 Trainer session"
                        ),
                        true,
                        false
                )
        );

        plans.add(
                new MembershipPlan(
                        "Yearly Membership",
                        7999,
                        "year",
                        "Best value for long-term fitness.",
                        List.of(
                                "All-access",
                                "Pool",
                                "5 Trainer sessions",
                                "Sauna"
                        ),
                        true,
                        true
                )
        );
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    private HBox createTopBar() {

        HBox topBar = new HBox();

        topBar.setPrefHeight(75);
        topBar.setMinHeight(75);

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(0, 35, 0, 35)
        );

        topBar.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        // TITLE
        Label title =
                new Label(
                        "Membership Management"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // SEARCH
        searchField = new TextField();

        searchField.setPromptText(
                "⌕  Search membership..."
        );

        searchField.setPrefWidth(300);
        searchField.setPrefHeight(40);

        searchField.setStyle(
                "-fx-background-color: #303236;" +
                "-fx-background-radius: 22;" +
                "-fx-border-color: #454a38;" +
                "-fx-border-radius: 22;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #aaa997;" +
                "-fx-font-size: 14px;"
        );

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    refreshCards(newValue);
                }
        );

        // NOTIFICATION
        Label notification =
                new Label("♧");

        notification.setFont(
                Font.font(25)
        );

        notification.setTextFill(
                Color.web(MUTED)
        );

        // HELP
        Label help =
                new Label("?");

        help.setPrefSize(30, 30);

        help.setAlignment(
                Pos.CENTER
        );

        help.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        help.setTextFill(
                Color.web(MUTED)
        );

        help.setStyle(
                "-fx-border-color: #777;" +
                "-fx-border-radius: 50;"
        );

        // PROFILE
        Label profile =
                new Label("●");

        profile.setFont(
                Font.font(27)
        );

        profile.setTextFill(
                Color.web("#6885a8")
        );

        HBox right =
                new HBox(
                        25,
                        searchField,
                        notification,
                        help,
                        profile
                );

        right.setAlignment(
                Pos.CENTER
        );

        topBar.getChildren().addAll(
                title,
                spacer,
                right
        );

        return topBar;
    }

    // =========================================================
    // SCROLL PANE
    // =========================================================

    private ScrollPane createScrollPane() {

        VBox content =
                new VBox(25);

        content.setPadding(
                new Insets(30, 40, 40, 40)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        content.setFillWidth(true);

        // =====================================================
        // HEADING ROW
        // =====================================================

        HBox headingRow =
                new HBox();

        headingRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox heading =
                new VBox(6);

        Label pageTitle =
                new Label(
                        "Membership Plans"
                );

        pageTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        38
                )
        );

        pageTitle.setTextFill(
                Color.web(TEXT)
        );

        Label description =
                new Label(
                        "Create and manage membership plans, pricing, and benefits. " +
                        "Ensure your offerings align with facility capacity and business targets."
                );

        description.setWrapText(true);

        description.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        description.setTextFill(
                Color.web(MUTED)
        );

        heading.getChildren().addAll(
                pageTitle,
                description
        );

        Region headingSpacer =
                new Region();

        HBox.setHgrow(
                headingSpacer,
                Priority.ALWAYS
        );

        // ADD BUTTON
        Button addButton =
                new Button(
                        "+  Add Membership Plan"
                );

        addButton.setPrefWidth(290);
        addButton.setPrefHeight(48);

        addButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        addButton.setTextFill(
                Color.web("#4b5400")
        );

        addButton.setStyle(
                "-fx-background-color: " + GREEN + ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        addButton.setOnAction(
                e -> showMembershipForm(null)
        );

        headingRow.getChildren().addAll(
                heading,
                headingSpacer,
                addButton
        );

        // =====================================================
        // SEPARATOR
        // =====================================================

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color: #3b3d3f;"
        );

        // =====================================================
        // CARDS CONTAINER
        // =====================================================

        cardsContainer =
                new FlowPane();

        cardsContainer.setHgap(25);
        cardsContainer.setVgap(25);

        cardsContainer.setPadding(
                new Insets(5)
        );

        cardsContainer.setAlignment(
                Pos.TOP_LEFT
        );

        refreshCards("");

        // =====================================================
        // ADD CONTENT
        // =====================================================

        content.getChildren().addAll(
                headingRow,
                separator,
                cardsContainer
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                content
        );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setFitToHeight(
                false
        );

        // ONLY VERTICAL SCROLL
        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.ALWAYS
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.ALWAYS
        );

        scrollPane.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-background: " + BG + ";" +
                "-fx-border-color: transparent;"
        );

        // Content width
        content.prefWidthProperty().bind(
                scrollPane.widthProperty().subtract(15)
        );

        return scrollPane;
    }

    // =========================================================
    // REFRESH CARDS
    // =========================================================

    private void refreshCards(
            String search
    ) {

        if (cardsContainer == null) {
            return;
        }

        cardsContainer
                .getChildren()
                .clear();

        String keyword =
                search == null
                        ? ""
                        : search.toLowerCase();

        for (MembershipPlan plan : plans) {

            if (
                    plan.name
                            .toLowerCase()
                            .contains(keyword)
            ) {

                cardsContainer
                        .getChildren()
                        .add(
                                createMembershipCard(plan)
                        );
            }
        }
    }

    // =========================================================
    // MEMBERSHIP CARD
    // =========================================================

    private VBox createMembershipCard(
            MembershipPlan plan
    ) {

        VBox card =
                new VBox();

        card.setPrefWidth(300);
        card.setMinWidth(300);
        card.setMaxWidth(300);

        card.setPrefHeight(400);

        card.setPadding(
                new Insets(25)
        );

        card.setSpacing(15);

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-border-width: 1;"
        );

        // =====================================================
        // NAME
        // =====================================================

        HBox nameRow =
                new HBox();

        nameRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label name =
                new Label(plan.name);

        name.setWrapText(true);

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        name.setTextFill(
                Color.web(TEXT)
        );

        Region nameSpacer =
                new Region();

        HBox.setHgrow(
                nameSpacer,
                Priority.ALWAYS
        );

        Label status =
                new Label(
                        "● " +
                        (plan.active
                                ? "Active"
                                : "Inactive")
                );

        status.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        status.setTextFill(
                Color.web(
                        plan.active
                                ? "#3ef0db"
                                : "#ff7777"
                )
        );

        status.setStyle(
                "-fx-background-color: #092d2a;" +
                "-fx-background-radius: 15;" +
                "-fx-padding: 5 9;"
        );

        nameRow.getChildren().addAll(
                name,
                nameSpacer,
                status
        );

        // =====================================================
        // BEST VALUE
        // =====================================================

        if (plan.bestValue) {

            Label bestValue =
                    new Label("BEST VALUE");

            bestValue.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            10
                    )
            );

            bestValue.setTextFill(
                    Color.web(GREEN)
            );

            bestValue.setStyle(
                    "-fx-border-color: " + GREEN + ";" +
                    "-fx-border-radius: 4;" +
                    "-fx-padding: 4 7;"
            );

            nameRow.getChildren().remove(
                    status
            );

            nameRow.getChildren().add(
                    bestValue
            );
        }

        // =====================================================
        // PRICE
        // =====================================================

        HBox priceRow =
                new HBox(5);

        priceRow.setAlignment(
                Pos.BASELINE_LEFT
        );

        Label price =
                new Label(
                        "₹" +
                        String.format(
                                "%.0f",
                                plan.price
                        )
                );

        price.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        price.setTextFill(
                Color.web(GREEN)
        );

        Label duration =
                new Label(
                        "/ " +
                        plan.duration
                );

        duration.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        duration.setTextFill(
                Color.web(MUTED)
        );

        priceRow.getChildren().addAll(
                price,
                duration
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label desc =
                new Label(
                        plan.description
                );

        desc.setWrapText(true);

        desc.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        desc.setTextFill(
                Color.web(MUTED)
        );

        // =====================================================
        // SEPARATOR
        // =====================================================

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color: #393b3d;"
        );

        // =====================================================
        // BENEFITS
        // =====================================================

        VBox benefits =
                new VBox(9);

        for (
                String benefit :
                plan.benefits
        ) {

            Label benefitLabel =
                    new Label(
                            "✓  " +
                            benefit
                    );

            benefitLabel.setFont(
                    Font.font(
                            "Arial",
                            15
                    )
            );

            benefitLabel.setTextFill(
                    Color.web(TEXT)
            );

            benefits.getChildren().add(
                    benefitLabel
            );
        }

        // =====================================================
        // SPACER
        // =====================================================

        Region cardSpacer =
                new Region();

        VBox.setVgrow(
                cardSpacer,
                Priority.ALWAYS
        );

        // =====================================================
        // BOTTOM SEPARATOR
        // =====================================================

        Separator bottomSeparator =
                new Separator();

        bottomSeparator.setStyle(
                "-fx-background-color: #393b3d;"
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons =
                new HBox(12);

        // EDIT
        Button edit =
                new Button("✎  Edit");

        edit.setPrefWidth(140);
        edit.setPrefHeight(42);

        edit.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        edit.setTextFill(
                Color.web(TEXT)
        );

        edit.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #646943;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );

        edit.setOnAction(
                e -> showMembershipForm(plan)
        );

        // DELETE
        Button delete =
                new Button("▣  Delete");

        delete.setPrefWidth(140);
        delete.setPrefHeight(42);

        delete.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        delete.setTextFill(
                Color.web("#7e1717")
        );

        delete.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );

        delete.setOnAction(
                e -> showDeleteDialog(plan)
        );

        buttons.getChildren().addAll(
                edit,
                delete
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        card.getChildren().addAll(
                nameRow,
                priceRow,
                desc,
                separator,
                benefits,
                cardSpacer,
                bottomSeparator,
                buttons
        );

        return card;
    }

    // =========================================================
    // ADD / EDIT FORM
    // =========================================================

    private void showMembershipForm(
            MembershipPlan editPlan
    ) {

        Stage dialog =
                new Stage();

        dialog.initModality(
                Modality.APPLICATION_MODAL
        );

        dialog.setTitle(
                editPlan == null
                        ? "Add Membership Plan"
                        : "Edit Membership Plan"
        );

        VBox main =
                new VBox();

        main.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox();

        header.setPadding(
                new Insets(22, 25, 22, 25)
        );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        editPlan == null
                                ? "Add Membership Plan"
                                : "Edit Membership Plan"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Region headerSpacer =
                new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        Button close =
                new Button("×");

        close.setFont(
                Font.font(25)
        );

        close.setTextFill(
                Color.web(MUTED)
        );

        close.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;"
        );

        close.setOnAction(
                e -> dialog.close()
        );

        header.getChildren().addAll(
                title,
                headerSpacer,
                close
        );

        Separator headerSeparator =
                new Separator();

        // =====================================================
        // FORM
        // =====================================================

        VBox form =
                new VBox(15);

        form.setPadding(
                new Insets(25)
        );

        // PLAN NAME
        Label planLabel =
                formLabel("PLAN NAME");

        TextField planName =
                new TextField();

        planName.setPromptText(
                "e.g. Elite Performance Pro"
        );

        styleInput(planName);

        // PRICE + DURATION
        HBox priceDuration =
                new HBox(15);

        VBox priceBox =
                new VBox(7);

        Label priceLabel =
                formLabel("PRICE");

        TextField priceField =
                new TextField();

        priceField.setPromptText(
                "0.00"
        );

        styleInput(priceField);

        priceBox.getChildren().addAll(
                priceLabel,
                priceField
        );

        VBox durationBox =
                new VBox(7);

        Label durationLabel =
                formLabel("DURATION");

        ComboBox<String> duration =
                new ComboBox<>();

        duration.getItems().addAll(
                "Monthly",
                "Quarterly",
                "Yearly"
        );

        duration.setValue(
                "Monthly"
        );

        duration.setMaxWidth(
                Double.MAX_VALUE
        );

        styleCombo(duration);

        durationBox.getChildren().addAll(
                durationLabel,
                duration
        );

        HBox.setHgrow(
                priceBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                durationBox,
                Priority.ALWAYS
        );

        priceDuration.getChildren().addAll(
                priceBox,
                durationBox
        );

        // DESCRIPTION
        Label descriptionLabel =
                formLabel("DESCRIPTION");

        TextArea description =
                new TextArea();

        description.setPromptText(
                "Briefly describe this membership plan..."
        );

        description.setPrefRowCount(3);

        description.setWrapText(true);

        styleTextArea(description);

        // BENEFITS
        Label benefitsLabel =
                formLabel(
                        "INCLUDED BENEFITS"
                );

        VBox benefitBox =
                new VBox(10);

        benefitBox.setPadding(
                new Insets(15)
        );

        benefitBox.setStyle(
                "-fx-background-color: #1a1d1f;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #303331;" +
                "-fx-border-radius: 8;"
        );

        VBox benefitList =
                new VBox(8);

        TextField benefitInput =
                new TextField();

        benefitInput.setPromptText(
                "Add another benefit..."
        );

        styleInput(
                benefitInput
        );

        Button addBenefit =
                new Button("+");

        addBenefit.setPrefWidth(45);
        addBenefit.setPrefHeight(45);

        addBenefit.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        addBenefit.setTextFill(
                Color.web(GREEN)
        );

        addBenefit.setStyle(
                "-fx-background-color: #303330;" +
                "-fx-background-radius: 20;" +
                "-fx-cursor: hand;"
        );

        HBox benefitRow =
                new HBox(
                        10,
                        benefitInput,
                        addBenefit
                );

        HBox.setHgrow(
                benefitInput,
                Priority.ALWAYS
        );

        Runnable addBenefitAction =
                () -> {

                    String text =
                            benefitInput
                                    .getText()
                                    .trim();

                    if (!text.isEmpty()) {

                        Label benefit =
                                new Label(
                                        "✓  " +
                                        text
                                );

                        benefit.setFont(
                                Font.font(
                                        "Arial",
                                        14
                                )
                        );

                        benefit.setTextFill(
                                Color.web(TEXT)
                        );

                        benefitList
                                .getChildren()
                                .add(
                                        benefit
                                );

                        benefitInput.clear();
                    }
                };

        addBenefit.setOnAction(
                e -> addBenefitAction.run()
        );

        benefitInput.setOnAction(
                e -> addBenefitAction.run()
        );

        benefitBox.getChildren().addAll(
                benefitList,
                benefitRow
        );

        // STATUS
        Separator statusSeparator =
                new Separator();

        HBox statusRow =
                new HBox();

        VBox statusText =
                new VBox(3);

        Label statusTitle =
                new Label(
                        "Plan Status"
                );

        statusTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        statusTitle.setTextFill(
                Color.web(TEXT)
        );

        Label statusDescription =
                new Label(
                        "Active plans are visible to members immediately."
                );

        statusDescription.setTextFill(
                Color.web(MUTED)
        );

        statusText.getChildren().addAll(
                statusTitle,
                statusDescription
        );

        Region statusSpacer =
                new Region();

        HBox.setHgrow(
                statusSpacer,
                Priority.ALWAYS
        );

        CheckBox active =
                new CheckBox("Active");

        active.setSelected(true);

        active.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        active.setTextFill(
                Color.web(GREEN)
        );

        statusRow.getChildren().addAll(
                statusText,
                statusSpacer,
                active
        );

        // =====================================================
        // LOAD EDIT DATA
        // =====================================================

        if (editPlan != null) {

            planName.setText(
                    editPlan.name
            );

            priceField.setText(
                    String.valueOf(
                            editPlan.price
                    )
            );

            if (
                    editPlan.duration
                            .equals("3 months")
            ) {

                duration.setValue(
                        "Quarterly"
                );

            } else if (
                    editPlan.duration
                            .equals("year")
            ) {

                duration.setValue(
                        "Yearly"
                );

            } else {

                duration.setValue(
                        "Monthly"
                );
            }

            description.setText(
                    editPlan.description
            );

            active.setSelected(
                    editPlan.active
            );

            for (
                    String benefitText :
                    editPlan.benefits
            ) {

                Label benefit =
                        new Label(
                                "✓  " +
                                benefitText
                        );

                benefit.setFont(
                        Font.font(
                                "Arial",
                                14
                        )
                );

                benefit.setTextFill(
                        Color.web(TEXT)
                );

                benefitList
                        .getChildren()
                        .add(
                                benefit
                        );
            }
        }

        form.getChildren().addAll(
                planLabel,
                planName,
                priceDuration,
                descriptionLabel,
                description,
                benefitsLabel,
                benefitBox,
                statusSeparator,
                statusRow
        );

        // =====================================================
        // FOOTER
        // =====================================================

        HBox footer =
                new HBox(12);

        footer.setPadding(
                new Insets(
                        15,
                        25,
                        20,
                        25
                )
        );

        footer.setAlignment(
                Pos.CENTER_RIGHT
        );

        footer.setStyle(
                "-fx-background-color: #171a1c;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1 0 0 0;"
        );

        // CANCEL
        Button cancel =
                new Button("Cancel");

        cancel.setPrefWidth(120);
        cancel.setPrefHeight(45);

        cancel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        cancel.setTextFill(
                Color.web(TEXT)
        );

        cancel.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #777;" +
                "-fx-border-radius: 6;" +
                "-fx-cursor: hand;"
        );

        cancel.setOnAction(
                e -> dialog.close()
        );

        // SAVE
        Button save =
                new Button(
                        editPlan == null
                                ? "▣  Save Plan"
                                : "▣  Update Plan"
                );

        save.setPrefWidth(170);
        save.setPrefHeight(45);

        save.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        save.setTextFill(
                Color.web("#4b5400")
        );

        save.setStyle(
                "-fx-background-color: " + GREEN + ";" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        save.setOnAction(
                e -> {

                    String name =
                            planName
                                    .getText()
                                    .trim();

                    String priceText =
                            priceField
                                    .getText()
                                    .trim();

                    String descText =
                            description
                                    .getText()
                                    .trim();

                    if (
                            name.isEmpty()
                                    ||
                            priceText.isEmpty()
                    ) {

                        showAlert(
                                "Please enter plan name and price."
                        );

                        return;
                    }

                    double price;

                    try {

                        price =
                                Double.parseDouble(
                                        priceText
                                );

                    } catch (
                            NumberFormatException ex
                    ) {

                        showAlert(
                                "Please enter a valid price."
                        );

                        return;
                    }

                    // BENEFITS
                    List<String> benefits =
                            new ArrayList<>();

                    for (
                            javafx.scene.Node node :
                            benefitList
                                    .getChildren()
                    ) {

                        if (
                                node instanceof Label
                        ) {

                            String text =
                                    ((Label) node)
                                            .getText();

                            text =
                                    text
                                            .replace(
                                                    "✓  ",
                                                    ""
                                            )
                                            .trim();

                            benefits.add(
                                    text
                            );
                        }
                    }

                    if (
                            benefits.isEmpty()
                    ) {

                        benefits.add(
                                "Gym access"
                        );
                    }

                    // DURATION
                    String durationValue;

                    switch (
                            duration.getValue()
                    ) {

                        case "Quarterly":

                            durationValue =
                                    "3 months";

                            break;

                        case "Yearly":

                            durationValue =
                                    "year";

                            break;

                        default:

                            durationValue =
                                    "month";
                    }

                    // =================================================
                    // ADD
                    // =================================================

                    if (editPlan == null) {

                        plans.add(
                                new MembershipPlan(
                                        name,
                                        price,
                                        durationValue,
                                        descText,
                                        benefits,
                                        active.isSelected(),
                                        false
                                )
                        );

                    }

                    // =================================================
                    // EDIT
                    // =================================================

                    else {

                        editPlan.name =
                                name;

                        editPlan.price =
                                price;

                        editPlan.duration =
                                durationValue;

                        editPlan.description =
                                descText;

                        editPlan.benefits =
                                benefits;

                        editPlan.active =
                                active.isSelected();
                    }

                    refreshCards(
                            searchField == null
                                    ? ""
                                    : searchField
                                            .getText()
                    );

                    dialog.close();
                }
        );

        footer.getChildren().addAll(
                cancel,
                save
        );

        // =====================================================
        // FORM SCROLL
        // =====================================================

        ScrollPane formScroll =
                new ScrollPane();

        formScroll.setContent(
                form
        );

        formScroll.setFitToWidth(
                true
        );

        formScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        formScroll.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-background: " + BG + ";"
        );

        VBox.setVgrow(
                formScroll,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                header,
                headerSeparator,
                formScroll,
                footer
        );

        Scene scene =
                new Scene(
                        main,
                        760,
                        780
                );

        dialog.setScene(scene);

        dialog.showAndWait();
    }

    // =========================================================
    // DELETE DIALOG
    // =========================================================

    private void showDeleteDialog(
            MembershipPlan plan
    ) {

        Stage dialog =
                new Stage();

        dialog.initModality(
                Modality.APPLICATION_MODAL
        );

        dialog.setTitle(
                "Delete Membership Plan"
        );

        VBox box =
                new VBox(18);

        box.setPadding(
                new Insets(28)
        );

        box.setPrefWidth(500);

        box.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: #3b3e40;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        // =====================================================
        // TITLE
        // =====================================================

        HBox titleRow =
                new HBox(15);

        Label warning =
                new Label("⚠");

        warning.setPrefSize(
                52,
                52
        );

        warning.setAlignment(
                Pos.CENTER
        );

        warning.setFont(
                Font.font(25)
        );

        warning.setTextFill(
                Color.web("#ffaaa7")
        );

        warning.setStyle(
                "-fx-background-color: #3b1015;" +
                "-fx-background-radius: 50;"
        );

        VBox titleBox =
                new VBox(5);

        Label title =
                new Label(
                        "Delete Membership Plan"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label message =
                new Label(
                        "Are you sure you want to delete this membership " +
                        "plan? This action cannot be undone."
                );

        message.setWrapText(true);

        message.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        message.setTextFill(
                Color.web(MUTED)
        );

        titleBox.getChildren().addAll(
                title,
                message
        );

        titleRow.getChildren().addAll(
                warning,
                titleBox
        );

        // =====================================================
        // TARGET PLAN
        // =====================================================

        HBox target =
                new HBox();

        target.setPadding(
                new Insets(16)
        );

        target.setStyle(
                "-fx-background-color: #191c1e;" +
                "-fx-border-color: #303331;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        VBox targetLeft =
                new VBox(5);

        Label targetLabel =
                new Label("TARGET PLAN");

        targetLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        targetLabel.setTextFill(
                Color.web(MUTED)
        );

        Label targetName =
                new Label(plan.name);

        targetName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        targetName.setTextFill(
                Color.web(TEXT)
        );

        targetLeft.getChildren().addAll(
                targetLabel,
                targetName
        );

        Region targetSpacer =
                new Region();

        HBox.setHgrow(
                targetSpacer,
                Priority.ALWAYS
        );

        target.getChildren().addAll(
                targetLeft,
                targetSpacer
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons =
                new HBox(12);

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button cancel =
                new Button("Cancel");

        cancel.setPrefWidth(90);
        cancel.setPrefHeight(42);

        cancel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        cancel.setTextFill(
                Color.web(TEXT)
        );

        cancel.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #777;" +
                "-fx-border-radius: 5;" +
                "-fx-cursor: hand;"
        );

        cancel.setOnAction(
                e -> dialog.close()
        );

        Button delete =
                new Button(
                        "▣  Delete Plan"
                );

        delete.setPrefWidth(140);
        delete.setPrefHeight(42);

        delete.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        delete.setTextFill(
                Color.web("#7e1717")
        );

        delete.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );

        delete.setOnAction(
                e -> {

                    plans.remove(
                            plan
                    );

                    refreshCards(
                            searchField == null
                                    ? ""
                                    : searchField
                                            .getText()
                    );

                    dialog.close();
                }
        );

        buttons.getChildren().addAll(
                cancel,
                delete
        );

        box.getChildren().addAll(
                titleRow,
                target,
                buttons
        );

        Scene scene =
                new Scene(
                        box,
                        550,
                        300
                );

        dialog.setScene(scene);

        dialog.showAndWait();
    }

    // =========================================================
    // FORM LABEL
    // =========================================================

    private Label formLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(
                Color.web(MUTED)
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleInput(
            TextField field
    ) {

        field.setPrefHeight(48);

        field.setStyle(
                "-fx-background-color: " + INPUT + ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #777967;" +
                "-fx-font-size: 15px;"
        );
    }

    // =========================================================
    // TEXT AREA STYLE
    // =========================================================

    private void styleTextArea(
            TextArea area
    ) {

        area.setStyle(
                "-fx-control-inner-background: " + INPUT + ";" +
                "-fx-background-color: " + INPUT + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #777967;" +
                "-fx-font-size: 14px;"
        );
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleCombo(
            ComboBox<String> combo
    ) {

        combo.setPrefHeight(48);

        combo.setStyle(
                "-fx-background-color: " + INPUT + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 15px;"
        );
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setTitle(
                "Validation"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        com.flexforce.view.components.DialogUtils.applyTheme(alert);
        alert.showAndWait();
    }

    // =========================================================
    // MEMBERSHIP PLAN CLASS
    // =========================================================

    public static class MembershipPlan {

        String name;
        double price;
        String duration;
        String description;
        List<String> benefits;
        boolean active;
        boolean bestValue;

        public MembershipPlan(
                String name,
                double price,
                String duration,
                String description,
                List<String> benefits,
                boolean active,
                boolean bestValue
        ) {

            this.name =
                    name;

            this.price =
                    price;

            this.duration =
                    duration;

            this.description =
                    description;

            this.benefits =
                    new ArrayList<>(
                            benefits
                    );

            this.active =
                    active;

            this.bestValue =
                    bestValue;
        }
    }

}
