
package com.flexforce.view.club_owner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.flexforce.dao.ClubMemberDAO;
import com.flexforce.model.club_owner.ClubMember;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Member_management {

    // =========================================================
    // FITCIRCLE THEME COLORS
    // =========================================================

    private static final String BACKGROUND = "#0b0f13";
    private static final String CARD = "#151c24";
    private static final String CARD_LIGHT = "#19222c";
    private static final String BORDER = "#29333e";
    private static final String GREEN = "#b8ff00";
    private static final String GREEN_DARK = "#20310f";
    private static final String WHITE = "#f4f7fa";
    private static final String TEXT = "#c4ccd5";
    private static final String MUTED = "#7f8b98";

    // =========================================================
    // DAO
    // =========================================================

    private final ClubMemberDAO memberDAO;

    // =========================================================
    // MEMBER DATA
    // =========================================================

    private final ObservableList<ClubMember> members =
            FXCollections.observableArrayList();

    // =========================================================
    // UI REFERENCES
    // =========================================================

    private VBox rowsContainer;

    private Label totalMembersLabel;
    private Label activeMembersLabel;
    private Label inactiveMembersLabel;
    private Label newMembersLabel;
    private Label showingLabel;

    private TextField searchField;
    private ComboBox<String> statusBox;

    // =========================================================
    // CLUB ID
    // =========================================================

    private String clubId = "";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Member_management() {

        memberDAO = new ClubMemberDAO();
    }

    // =========================================================
    // SET CLUB ID
    // =========================================================

    public void setClubId(String clubId) {

        this.clubId =
                clubId == null ? "" : clubId.trim();
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    public VBox createMemberManagementUI() {

        VBox content = new VBox(22);

        content.setPadding(
                new Insets(25, 28, 30, 28)
        );

        content.setMaxWidth(
                Double.MAX_VALUE
        );

        content.setStyle(
                "-fx-background-color:" + BACKGROUND + ";"
        );

        // =====================================================
        // HEADER
        // =====================================================

        VBox heading = new VBox(5);

        Label title = new Label(
                "Member Management"
        );

        title.setStyle(
                "-fx-text-fill:" + GREEN + ";" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle = new Label(
                "Manage members of your club"
        );

        subtitle.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // =====================================================
        // STATISTICS
        // =====================================================

        HBox statistics = new HBox(16);

        statistics.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox totalCard = createStatCard(
                "TOTAL MEMBERS",
                "0",
                "All registered members"
        );

        VBox activeCard = createStatCard(
                "ACTIVE",
                "0",
                "Currently active"
        );

        VBox inactiveCard = createStatCard(
                "INACTIVE",
                "0",
                "Need attention"
        );

        VBox newCard = createStatCard(
                "NEW THIS MONTH",
                "0",
                "Recently joined"
        );

        totalMembersLabel =
                (Label) totalCard.getChildren().get(1);

        activeMembersLabel =
                (Label) activeCard.getChildren().get(1);

        inactiveMembersLabel =
                (Label) inactiveCard.getChildren().get(1);

        newMembersLabel =
                (Label) newCard.getChildren().get(1);

        statistics.getChildren().addAll(
                totalCard,
                activeCard,
                inactiveCard,
                newCard
        );

        HBox.setHgrow(
                totalCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                inactiveCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                newCard,
                Priority.ALWAYS
        );

        // =====================================================
        // SEARCH + FILTER
        // =====================================================

        HBox tools = new HBox(10);

        tools.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // ADD MEMBER
        // =====================================================

        Button addMember = new Button(
                "+   Add Member"
        );

        addMember.setPrefHeight(40);
        addMember.setPrefWidth(125);

        addMember.setStyle(
                "-fx-background-color:" + GREEN + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );

        addMember.setOnAction(
                e -> showInfo(
                        "Add Member",
                        "Member registration should be done through the membership system."
                )
        );

        // =====================================================
        // SEARCH
        // =====================================================

        searchField = new TextField();

        searchField.setPromptText(
                "⌕   Search member by name, email or ID"
        );

        searchField.setPrefHeight(40);
        searchField.setPrefWidth(300);

        searchField.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;" +
                "-fx-padding:0 12px;"
        );

        // =====================================================
        // STATUS FILTER
        // =====================================================

        statusBox = new ComboBox<>();

        statusBox.getItems().addAll(
                "All Status",
                "Active",
                "Inactive"
        );

        statusBox.setValue(
                "All Status"
        );

        statusBox.setPrefHeight(40);
        statusBox.setPrefWidth(125);

        statusBox.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;"
        );

        // =====================================================
        // FILTER BUTTON
        // =====================================================

        Button filterButton = new Button(
                "☰   Filter"
        );

        filterButton.setPrefHeight(40);
        filterButton.setPrefWidth(90);

        filterButton.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:12px;" +
                "-fx-cursor:hand;"
        );

        filterButton.setOnAction(
                e -> updateMemberRows()
        );

        tools.getChildren().addAll(
                addMember,
                searchField,
                statusBox,
                filterButton
        );

        // =====================================================
        // TABLE CONTAINER
        // =====================================================

        VBox tableContainer = new VBox();

        tableContainer.setMaxWidth(
                Double.MAX_VALUE
        );

        tableContainer.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;"
        );

        VBox table = new VBox();

        table.setMaxWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // TABLE HEADER
        // =====================================================

        GridPane header = createTableHeader();

        table.getChildren().add(
                header
        );

        // =====================================================
        // ROWS
        // =====================================================

        rowsContainer = new VBox();

        rowsContainer.setMaxWidth(
                Double.MAX_VALUE
        );

        table.getChildren().add(
                rowsContainer
        );

        tableContainer.getChildren().add(
                table
        );

        // =====================================================
        // SEARCH LISTENER
        // =====================================================

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateMemberRows()
        );

        // =====================================================
        // STATUS LISTENER
        // =====================================================

        statusBox.valueProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateMemberRows()
        );

        // =====================================================
        // PAGINATION
        // =====================================================

        HBox pagination = new HBox(7);

        pagination.setAlignment(
                Pos.CENTER
        );

        Button previous = createPageButton(
                "Previous"
        );

        Button page1 = createPageButton(
                "1"
        );

        Button next = createPageButton(
                "Next"
        );

        page1.setStyle(
                "-fx-background-color:" + GREEN + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:6px;" +
                "-fx-border-radius:6px;" +
                "-fx-border-color:" + GREEN + ";" +
                "-fx-cursor:hand;"
        );

        pagination.getChildren().addAll(
                previous,
                page1,
                next
        );

        // =====================================================
        // FOOTER
        // =====================================================

        HBox footer = new HBox();

        footer.setAlignment(
                Pos.CENTER_LEFT
        );

        showingLabel = new Label(
                "Showing 0 of 0 members"
        );

        showingLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:11px;"
        );

        footer.getChildren().add(
                showingLabel
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        content.getChildren().addAll(
                heading,
                statistics,
                tools,
                tableContainer,
                pagination,
                footer
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color:" + BACKGROUND + ";" +
                "-fx-background:" + BACKGROUND + ";"
        );

        // =====================================================
        // ROOT
        // =====================================================

        VBox root = new VBox();

        root.setMaxWidth(
                Double.MAX_VALUE
        );

        root.setMaxHeight(
                Double.MAX_VALUE
        );

        root.setStyle(
                "-fx-background-color:" + BACKGROUND + ";"
        );

        root.getChildren().add(
                scrollPane
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        // =====================================================
        // LOAD FIRESTORE DATA
        // =====================================================

        loadMembers();

        return root;
    }

    // =========================================================
    // LOAD MEMBERS
    // =========================================================

    private void loadMembers() {

        ProgressIndicator loading =
                new ProgressIndicator();

        loading.setPrefSize(
                30,
                30
        );

        rowsContainer.getChildren().clear();

        rowsContainer.setAlignment(
                Pos.CENTER
        );

        rowsContainer.setPadding(
                new Insets(30)
        );

        rowsContainer.getChildren().add(
                loading
        );

        Task<List<ClubMember>> task =
                new Task<>() {

                    @Override
                    protected List<ClubMember> call()
                            throws Exception {

                        if (clubId == null ||
                                clubId.trim().isEmpty()) {

                            return memberDAO.getMembers();
                        }

                        return memberDAO.getMembersByClubId(
                                clubId
                        );
                    }
                };

        task.setOnSucceeded(
                e -> {

                    List<ClubMember> result =
                            task.getValue();

                    members.clear();

                    if (result != null) {

                        members.addAll(
                                result
                        );
                    }

                    updateStatistics();

                    updateMemberRows();
                }
        );

        task.setOnFailed(
                e -> {

                    Throwable exception =
                            task.getException();

                    if (exception != null) {
                        exception.printStackTrace();
                    }

                    rowsContainer.getChildren().clear();

                    Label error =
                            new Label(
                                    "Unable to load members."
                            );

                    error.setStyle(
                            "-fx-text-fill:#ff6b6b;" +
                            "-fx-font-size:12px;"
                    );

                    rowsContainer.getChildren().add(
                            error
                    );

                    showInfo(
                            "Database Error",
                            exception == null
                                    ? "Unable to load members."
                                    : exception.getMessage()
                    );
                }
        );

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // =========================================================
    // UPDATE STATISTICS
    // =========================================================

    private void updateStatistics() {

        int total =
                members.size();

        int active = 0;

        int inactive = 0;

        int newMembers = 0;

        LocalDate now =
                LocalDate.now();

        for (ClubMember member : members) {

            if (member == null) {
                continue;
            }

            String status =
                    safe(member.getStatus());

            if ("Active".equalsIgnoreCase(status)) {

                active++;

            } else {

                inactive++;
            }

            // -------------------------------------------------
            // NEW MEMBER THIS MONTH
            // -------------------------------------------------

            String joinDate =
                    safe(member.getJoinDate());

            LocalDate parsedDate =
                    parseDate(joinDate);

            if (parsedDate != null &&
                    parsedDate.getYear() == now.getYear() &&
                    parsedDate.getMonth() == now.getMonth()) {

                newMembers++;
            }
        }

        totalMembersLabel.setText(
                String.valueOf(total)
        );

        activeMembersLabel.setText(
                String.valueOf(active)
        );

        inactiveMembersLabel.setText(
                String.valueOf(inactive)
        );

        newMembersLabel.setText(
                String.valueOf(newMembers)
        );
    }

    // =========================================================
    // PARSE DATE
    // =========================================================

    private LocalDate parseDate(String dateText) {

        if (dateText == null ||
                dateText.trim().isEmpty()) {

            return null;
        }

        String value =
                dateText.trim();

        // yyyy-MM-dd
        try {

            return LocalDate.parse(
                    value,
                    DateTimeFormatter.ISO_LOCAL_DATE
            );

        } catch (DateTimeParseException ignored) {
        }

        // dd MMM yyyy
        try {

            return LocalDate.parse(
                    value,
                    DateTimeFormatter.ofPattern(
                            "dd MMM yyyy"
                    )
            );

        } catch (DateTimeParseException ignored) {
        }

        // dd MMMM yyyy
        try {

            return LocalDate.parse(
                    value,
                    DateTimeFormatter.ofPattern(
                            "dd MMMM yyyy"
                    )
            );

        } catch (DateTimeParseException ignored) {
        }

        return null;
    }

    // =========================================================
    // UPDATE TABLE
    // =========================================================

    private void updateMemberRows() {

        if (rowsContainer == null) {
            return;
        }

        rowsContainer.getChildren().clear();

        rowsContainer.setAlignment(
                Pos.TOP_LEFT
        );

        rowsContainer.setPadding(
                Insets.EMPTY
        );

        String search =
                searchField == null
                        ? ""
                        : safe(searchField.getText())
                                .trim()
                                .toLowerCase();

        String selectedStatus =
                statusBox == null
                        ? "All Status"
                        : statusBox.getValue();

        int displayed = 0;

        for (ClubMember member : members) {

            if (member == null) {
                continue;
            }

            String name =
                    safe(member.getName());

            String email =
                    safe(member.getEmail());

            String id =
                    safe(member.getMemberId());

            String phone =
                    safe(member.getPhone());

            boolean matchesSearch =
                    search.isEmpty()
                            ||
                    name.toLowerCase()
                            .contains(search)
                            ||
                    email.toLowerCase()
                            .contains(search)
                            ||
                    id.toLowerCase()
                            .contains(search)
                            ||
                    phone.toLowerCase()
                            .contains(search);

            String memberStatus =
                    safe(member.getStatus());

            boolean matchesStatus =
                    selectedStatus == null
                            ||
                    selectedStatus.equals(
                            "All Status"
                    )
                            ||
                    memberStatus.equalsIgnoreCase(
                            selectedStatus
                    );

            if (matchesSearch &&
                    matchesStatus) {

                rowsContainer.getChildren().add(
                        createMemberRow(member)
                );

                displayed++;
            }
        }

        showingLabel.setText(
                "Showing "
                        + displayed
                        + " of "
                        + members.size()
                        + " members"
        );

        if (displayed == 0) {

            Label empty =
                    new Label(
                            "No members found."
                    );

            empty.setPadding(
                    new Insets(25)
            );

            empty.setStyle(
                    "-fx-text-fill:" + MUTED + ";" +
                    "-fx-font-size:12px;"
            );

            rowsContainer.getChildren().add(
                    empty
            );
        }
    }

    // =========================================================
    // TABLE HEADER
    // =========================================================

    private GridPane createTableHeader() {

        GridPane grid =
                new GridPane();

        grid.setPadding(
                new Insets(13, 12, 13, 12)
        );

        grid.setStyle(
                "-fx-background-color:#10161c;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:0 0 1 0;"
        );

        addColumn(
                grid,
                "ID",
                0,
                70
        );

        addColumn(
                grid,
                "NAME",
                1,
                180
        );

        addColumn(
                grid,
                "CONTACT",
                2,
                270
        );

        addColumn(
                grid,
                "MEMBERSHIP",
                3,
                130
        );

        addColumn(
                grid,
                "JOIN DATE",
                4,
                140
        );

        addColumn(
                grid,
                "STATUS",
                5,
                120
        );

        addColumn(
                grid,
                "ACTION",
                6,
                150
        );

        return grid;
    }

    // =========================================================
    // MEMBER ROW
    // =========================================================

    private GridPane createMemberRow(
            ClubMember member
    ) {

        GridPane grid =
                new GridPane();

        grid.setPadding(
                new Insets(13, 12, 13, 12)
        );

        grid.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:#202b35;" +
                "-fx-border-width:0 0 1 0;"
        );

        // =====================================================
        // ID
        // =====================================================

        Label id =
                createCell(
                        safe(member.getMemberId()),
                        70
                );

        // =====================================================
        // NAME
        // =====================================================

        Label name =
                createCell(
                        safe(member.getName()),
                        180
                );

        name.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // CONTACT
        // =====================================================

        VBox contactBox =
                new VBox(3);

        contactBox.setPrefWidth(
                270
        );

        Label email =
                new Label(
                        safe(member.getEmail())
                );

        email.setStyle(
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-font-size:11px;"
        );

        Label phone =
                new Label(
                        safe(member.getPhone())
                );

        phone.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:10px;"
        );

        contactBox.getChildren().addAll(
                email,
                phone
        );

        // =====================================================
        // MEMBERSHIP
        // =====================================================

        Label membership =
                createCell(
                        safe(member.getMembership()),
                        130
                );

        // =====================================================
        // JOIN DATE
        // =====================================================

        Label joinDate =
                createCell(
                        safe(member.getJoinDate()),
                        140
                );

        // =====================================================
        // STATUS
        // =====================================================

        String memberStatus =
                safe(member.getStatus());

        Label status =
                new Label(
                        "●  " + memberStatus
                );

        status.setPrefWidth(
                120
        );

        if ("Active".equalsIgnoreCase(
                memberStatus)) {

            status.setStyle(
                    "-fx-text-fill:" + GREEN + ";" +
                    "-fx-font-size:11px;" +
                    "-fx-font-weight:bold;"
            );

        } else {

            status.setStyle(
                    "-fx-text-fill:#89939e;" +
                    "-fx-font-size:11px;"
            );
        }

        // =====================================================
        // ACTIONS
        // =====================================================

        HBox actions =
                new HBox(7);

        actions.setPrefWidth(
                150
        );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        Button view =
                new Button("◉");

        Button edit =
                new Button("✎");
                
        Button track = 
                new Button("◷");

        styleActionButton(view);
        styleActionButton(edit);
        styleActionButton(track);

        actions.getChildren().addAll(
                view,
                edit,
                track
        );

        // =====================================================
        // VIEW BUTTON
        // =====================================================

        view.setOnAction(
                event ->
                        showMemberDetails(member)
        );

        // =====================================================
        // EDIT BUTTON
        // =====================================================

        edit.setOnAction(
                event ->
                        editMember(member)
        );

        // =====================================================
        // TRACK BUTTON
        // =====================================================

        track.setOnAction(
                event ->
                        MemberActivityDialog.display(member)
        );

        // =====================================================
        // ADD COLUMNS
        // =====================================================

        grid.add(
                id,
                0,
                0
        );

        grid.add(
                name,
                1,
                0
        );

        grid.add(
                contactBox,
                2,
                0
        );

        grid.add(
                membership,
                3,
                0
        );

        grid.add(
                joinDate,
                4,
                0
        );

        grid.add(
                status,
                5,
                0
        );

        grid.add(
                actions,
                6,
                0
        );

        // =====================================================
        // HOVER
        // =====================================================

        grid.setOnMouseEntered(
                event ->
                        grid.setStyle(
                                "-fx-background-color:#1b2630;" +
                                "-fx-border-color:" + GREEN + ";" +
                                "-fx-border-width:0 0 1 0;"
                        )
        );

        grid.setOnMouseExited(
                event ->
                        grid.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                "-fx-border-color:#202b35;" +
                                "-fx-border-width:0 0 1 0;"
                        )
        );

        return grid;
    }

    // =========================================================
    // SHOW MEMBER DETAILS
    // =========================================================

    private void showMemberDetails(
            ClubMember member
    ) {

        String message =
                "Member ID: "
                        + safe(member.getMemberId())
                        + "\n\n"
                        + "Name: "
                        + safe(member.getName())
                        + "\n\n"
                        + "Email: "
                        + safe(member.getEmail())
                        + "\n\n"
                        + "Phone: "
                        + safe(member.getPhone())
                        + "\n\n"
                        + "Membership: "
                        + safe(member.getMembership())
                        + "\n\n"
                        + "Join Date: "
                        + safe(member.getJoinDate())
                        + "\n\n"
                        + "Status: "
                        + safe(member.getStatus());

        showInfo(
                "Member Details",
                message
        );
    }

    // =========================================================
    // EDIT MEMBER
    // =========================================================

    private void editMember(
            ClubMember member
    ) {

        showInfo(
                "Edit Member",
                "Edit functionality for "
                        + safe(member.getName())
                        + " can be connected to MemberDAO.updateMember()."
        );
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String heading,
            String number,
            String description
    ) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(17)
        );

        card.setMinHeight(
                105
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;"
        );

        Label headingLabel =
                new Label(heading);

        headingLabel.setStyle(
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        Label numberLabel =
                new Label(number);

        numberLabel.setStyle(
                "-fx-text-fill:" + GREEN + ";" +
                "-fx-font-size:25px;" +
                "-fx-font-weight:bold;"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:10px;"
        );

        card.getChildren().addAll(
                headingLabel,
                numberLabel,
                descriptionLabel
        );

        card.setOnMouseEntered(
                event ->
                        card.setStyle(
                                "-fx-background-color:" + CARD_LIGHT + ";" +
                                "-fx-border-color:" + GREEN + ";" +
                                "-fx-border-radius:9px;" +
                                "-fx-background-radius:9px;"
                        )
        );

        card.setOnMouseExited(
                event ->
                        card.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                "-fx-border-color:" + BORDER + ";" +
                                "-fx-border-radius:9px;" +
                                "-fx-background-radius:9px;"
                        )
        );

        return card;
    }

    // =========================================================
    // TABLE CELL
    // =========================================================

    private Label createCell(
            String text,
            double width
    ) {

        Label label =
                new Label(text);

        label.setPrefWidth(width);
        label.setMinWidth(width);

        label.setStyle(
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-font-size:11px;"
        );

        return label;
    }

    // =========================================================
    // HEADER COLUMN
    // =========================================================

    private void addColumn(
            GridPane grid,
            String text,
            int column,
            double width
    ) {

        Label label =
                new Label(text);

        label.setPrefWidth(width);

        label.setStyle(
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        grid.add(
                label,
                column,
                0
        );
    }

    // =========================================================
    // ACTION BUTTON STYLE
    // =========================================================

    private void styleActionButton(
            Button button
    ) {

        button.setPrefWidth(36);
        button.setPrefHeight(32);

        button.setStyle(
                "-fx-background-color:#1d2731;" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:6px;" +
                "-fx-background-radius:6px;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color:" + GREEN_DARK + ";" +
                                "-fx-text-fill:" + GREEN + ";" +
                                "-fx-border-color:" + GREEN + ";" +
                                "-fx-border-radius:6px;" +
                                "-fx-background-radius:6px;" +
                                "-fx-font-size:13px;" +
                                "-fx-cursor:hand;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color:#1d2731;" +
                                "-fx-text-fill:" + TEXT + ";" +
                                "-fx-border-color:" + BORDER + ";" +
                                "-fx-border-radius:6px;" +
                                "-fx-background-radius:6px;" +
                                "-fx-font-size:13px;" +
                                "-fx-cursor:hand;"
                        )
        );
    }

    // =========================================================
    // PAGE BUTTON
    // =========================================================

    private Button createPageButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(34);

        if (text.equals("Previous") ||
                text.equals("Next")) {

            button.setPrefWidth(78);

        } else {

            button.setPrefWidth(38);
        }

        button.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:6px;" +
                "-fx-background-radius:6px;" +
                "-fx-font-size:11px;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        return value == null
                ? ""
                : value;
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showInfo(
            String title,
            String message
    ) {

        Platform.runLater(
                () -> {

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.INFORMATION
                            );

                    alert.setTitle(title);
                    alert.setHeaderText(null);
                    alert.setContentText(
                            message == null
                                    ? ""
                                    : message
                    );

                    com.flexforce.view.components.DialogUtils.applyTheme(alert);

                    alert.getDialogPane().lookup(".content.label").setStyle(
                      "-fx-text-fill: white;"
                   );
                    alert.showAndWait();
                }
        );
    }
}

