package com.flexforce.view.admin;

import java.io.File;
import java.util.List;

import com.flexforce.controller.CloudinaryImageUploadController;
import com.flexforce.dao.CarouselImageDAO;
import com.flexforce.model.admin.CarouselImage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminCarouselManage {

    private final String BACKGROUND = "#080B09";
    private final String CARD = "#101511";
    private final String GREEN = "#B7FF00";
    private final String WHITE = "#FFFFFF";
    private final String BORDER = "#283129";

    private VBox imageListContainer;

    public VBox createContent(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: " + BACKGROUND + ";");

        Label title = new Label("Manage Carousel Images");
        title.setTextFill(Color.web(WHITE));
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button uploadBtn = new Button("Upload New Image");
        uploadBtn.setStyle("-fx-background-color: " + GREEN + "; -fx-text-fill: black; -fx-font-weight: bold;");

        ProgressIndicator loader = new ProgressIndicator();
        loader.setVisible(false);
        loader.setManaged(false);

        uploadBtn.setOnAction(e -> handleUpload(stage, loader, uploadBtn));

        HBox topBox = new HBox(20, title, uploadBtn, loader);
        topBox.setAlignment(Pos.CENTER_LEFT);

        imageListContainer = new VBox(15);
        
        ScrollPane scrollPane = new ScrollPane(imageListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: " + BACKGROUND + "; -fx-background-color: " + BACKGROUND + "; -fx-border-color: transparent;");

        root.getChildren().addAll(topBox, scrollPane);

        loadImages();

        return root;
    }

    private void handleUpload(Stage stage, ProgressIndicator loader, Button uploadBtn) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.webp")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            uploadBtn.setDisable(true);
            loader.setVisible(true);
            loader.setManaged(true);

            new Thread(() -> {
                CloudinaryImageUploadController uploader = new CloudinaryImageUploadController();
                String url = uploader.imageUpload(selectedFile);

                if (url != null && !url.isEmpty()) {
                    CarouselImage image = new CarouselImage();
                    image.setImageId("CAROUSEL_" + System.currentTimeMillis());
                    image.setImageUrl(url);
                    image.setUploadedAt(java.time.LocalDate.now().toString());

                    CarouselImageDAO dao = new CarouselImageDAO();
                    dao.addCarouselImage(image);

                    javafx.application.Platform.runLater(() -> {
                        loadImages();
                    });
                }
                
                javafx.application.Platform.runLater(() -> {
                    uploadBtn.setDisable(false);
                    loader.setVisible(false);
                    loader.setManaged(false);
                });
            }).start();
        }
    }

    private void loadImages() {
        imageListContainer.getChildren().clear();

        new Thread(() -> {
            CarouselImageDAO dao = new CarouselImageDAO();
            List<CarouselImage> images = dao.getAllImages();

            javafx.application.Platform.runLater(() -> {
                if (images.isEmpty()) {
                    Label noImages = new Label("No carousel images found.");
                    noImages.setTextFill(Color.web(WHITE));
                    imageListContainer.getChildren().add(noImages);
                    return;
                }

                for (CarouselImage img : images) {
                    HBox row = new HBox(20);
                    row.setAlignment(Pos.CENTER_LEFT);
                    row.setPadding(new Insets(15));
                    row.setStyle("-fx-background-color: " + CARD + "; -fx-border-color: " + BORDER + "; -fx-border-radius: 5px; -fx-background-radius: 5px;");

                    ImageView imageView = new ImageView();
                    imageView.setFitWidth(200);
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);
                    
                    // load image safely
                    new Thread(() -> {
                        Image i = new Image(img.getImageUrl(), true);
                        javafx.application.Platform.runLater(() -> imageView.setImage(i));
                    }).start();

                    VBox info = new VBox(5);
                    Label idLabel = new Label("ID: " + img.getImageId());
                    idLabel.setTextFill(Color.web(WHITE));
                    Label dateLabel = new Label("Uploaded: " + img.getUploadedAt());
                    dateLabel.setTextFill(Color.web(WHITE));
                    info.getChildren().addAll(idLabel, dateLabel);

                    Button deleteBtn = new Button("Delete");
                    deleteBtn.setStyle("-fx-background-color: #FF5555; -fx-text-fill: white;");
                    deleteBtn.setOnAction(e -> {
                        new Thread(() -> {
                            dao.deleteImage(img.getImageId());
                            javafx.application.Platform.runLater(this::loadImages);
                        }).start();
                    });

                    row.getChildren().addAll(imageView, info, deleteBtn);
                    imageListContainer.getChildren().add(row);
                }
            });
        }).start();
    }
}
