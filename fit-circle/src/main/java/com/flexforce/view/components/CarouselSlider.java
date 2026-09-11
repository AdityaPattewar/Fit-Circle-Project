package com.flexforce.view.components;

import com.flexforce.dao.CarouselImageDAO;
import com.flexforce.model.admin.CarouselImage;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.List;

public class CarouselSlider extends StackPane {

    private List<CarouselImage> images;
    private int currentIndex = 0;
    private ImageView imageView;
    private Timeline timeline;

    public CarouselSlider() {
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: transparent; -fx-background-radius: 10px;");
        // Maintain a standard banner aspect ratio. Approx 1200x400 max, but we can set constraints
        this.setMaxHeight(400); 

        imageView = new ImageView();
        imageView.setFitWidth(1000);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 5);");

        this.getChildren().add(imageView);

        loadImages();
    }

    private void loadImages() {
        // Load in background
        new Thread(() -> {
            CarouselImageDAO dao = new CarouselImageDAO();
            List<CarouselImage> fetchedImages = dao.getAllImages();
            
            javafx.application.Platform.runLater(() -> {
                this.images = fetchedImages;
                if (images != null && !images.isEmpty()) {
                    startCarousel();
                } else {
                    // Fallback or empty state
                    System.out.println("No carousel images found.");
                }
            });
        }).start();
    }

    private void startCarousel() {
        showImage(currentIndex);

        if (images.size() > 1) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
                currentIndex = (currentIndex + 1) % images.size();
                transitionToNextImage();
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    private void transitionToNextImage() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), imageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            showImage(currentIndex);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), imageView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private void showImage(int index) {
        String url = images.get(index).getImageUrl();
        if (url != null && !url.isEmpty()) {
            Image image = new Image(url, true);
            imageView.setImage(image);
        }
    }
}
