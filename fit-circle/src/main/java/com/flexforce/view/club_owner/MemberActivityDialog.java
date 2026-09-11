package com.flexforce.view.club_owner;

import java.util.List;

import com.flexforce.dao.MemberActivityDAO;
import com.flexforce.model.club_owner.ClubMember;
import com.flexforce.model.club_owner.MemberActivity;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MemberActivityDialog {

    private static final String BACKGROUND = "#0b0f13";
    private static final String CARD = "#151c24";
    private static final String BORDER = "#29333e";
    private static final String GREEN = "#b8ff00";
    private static final String WHITE = "#f4f7fa";
    private static final String TEXT = "#c4ccd5";
    private static final String MUTED = "#7f8b98";

    public static void display(ClubMember member) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Member Activity - " + member.getName());
        window.setMinWidth(600);
        window.setMinHeight(400);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + BACKGROUND + ";");

        // Header
        Label titleLabel = new Label("Activity History: " + member.getName());
        titleLabel.setStyle("-fx-text-fill: " + GREEN + "; -fx-font-size: 20px; -fx-font-weight: bold;");

        Label infoLabel = new Label("Member ID: " + member.getMemberId() + " | Membership: " + member.getMembership());
        infoLabel.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 12px;");

        VBox header = new VBox(5, titleLabel, infoLabel);

        // Fetch Activities
        MemberActivityDAO dao = new MemberActivityDAO();
        List<MemberActivity> activities = dao.getActivitiesByMember(member.getMemberId());

        // Table
        VBox tableContainer = new VBox(10);
        tableContainer.setStyle("-fx-background-color: " + CARD + "; -fx-border-color: " + BORDER + "; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 10px;");

        if (activities.isEmpty()) {
            Label noDataLabel = new Label("No activity records found for this member.");
            noDataLabel.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 13px; -fx-padding: 20px;");
            tableContainer.getChildren().add(noDataLabel);
        } else {
            // Table Header
            GridPane headerGrid = new GridPane();
            headerGrid.setHgap(20);
            headerGrid.setPadding(new Insets(10));
            headerGrid.setStyle("-fx-border-color: " + BORDER + "; -fx-border-width: 0 0 1 0;");

            headerGrid.add(createHeaderLabel("Date", 100), 0, 0);
            headerGrid.add(createHeaderLabel("Activity", 150), 1, 0);
            headerGrid.add(createHeaderLabel("Status", 100), 2, 0);

            tableContainer.getChildren().add(headerGrid);

            // Table Rows
            for (MemberActivity activity : activities) {
                GridPane row = new GridPane();
                row.setHgap(20);
                row.setPadding(new Insets(10));
                row.setStyle("-fx-border-color: " + BORDER + "; -fx-border-width: 0 0 1 0;");

                row.add(createCellLabel(activity.getDate() != null ? activity.getDate() : "N/A", 100), 0, 0);
                row.add(createCellLabel(activity.getActivityType() != null ? activity.getActivityType() : "Unknown", 150), 1, 0);
                
                Label statusLabel = createCellLabel(activity.getStatus() != null ? activity.getStatus() : "Unknown", 100);
                if ("Completed".equalsIgnoreCase(activity.getStatus())) {
                    statusLabel.setStyle("-fx-text-fill: " + GREEN + "; -fx-font-size: 12px; -fx-font-weight: bold;");
                }
                row.add(statusLabel, 2, 0);

                tableContainer.getChildren().add(row);
            }
        }

        ScrollPane scrollPane = new ScrollPane(tableContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BACKGROUND + ";");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // Close Button
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: " + GREEN + "; -fx-text-fill: #000000; -fx-font-weight: bold; -fx-padding: 8 20 8 20; -fx-background-radius: 5px;");
        closeButton.setOnAction(e -> window.close());
        HBox buttonBox = new HBox(closeButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        layout.getChildren().addAll(header, scrollPane, buttonBox);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    private static Label createHeaderLabel(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 11px; -fx-font-weight: bold; -fx-text-transform: uppercase;");
        return label;
    }

    private static Label createCellLabel(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setStyle("-fx-text-fill: " + TEXT + "; -fx-font-size: 12px;");
        return label;
    }
}
