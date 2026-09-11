package com.flexforce.view.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.flexforce.controller.CommunityController;
import com.flexforce.model.user.CommunityPost;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CommunityPage {

    // =========================================================
    // COLORS
    // =========================================================

    String bg = "#101111";
    String card = "#1d1e1d";
    String green = "#c6ff00";
    String white = "#f5f5f5";
    String gray = "#a7aaa6";
    String border = "#303230";

    // =========================================================
    // CONTROLLER
    // =========================================================

    private final CommunityController controller;

    private VBox feedContainer;

    private TextField searchField;

    private List<CommunityPost> allPosts =
            new ArrayList<>();

    private int postsToShow = 5;

    // =========================================================
    // CURRENT USER
    // =========================================================

    /*
     * Tumchya login system sobat connect kartana
     * he actual logged-in user ID/name kara.
     */

    private String currentUserId = "user001";

    private String currentUserName = "Current User";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CommunityPage() {

        controller = new CommunityController();
        
        // Fetch actual logged-in user
        String loggedInId = com.flexforce.controller.AuthControllerlogin.getCurrentUserId();
        if (loggedInId != null && !loggedInId.trim().isEmpty()) {
            currentUserId = loggedInId;
            com.flexforce.dao.UserInfoDAO dao = new com.flexforce.dao.UserInfoDAO();
            com.flexforce.model.user.UserInfo u = dao.getUserInfo(currentUserId);

            if (u != null) {
                currentUserName = u.getName();
            }
        }
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private Runnable onDiscoverMore;

    public void setOnDiscoverMore(Runnable onDiscoverMore) {
        this.onDiscoverMore = onDiscoverMore;
    }

    public VBox createContent() {

        VBox content = new VBox(15);

        content.setPadding(
                new Insets(20, 25, 30, 25)
        );

        content.setPrefWidth(1250);

        content.setMinWidth(1050);

        content.setMaxWidth(1250);

        content.setStyle(
                "-fx-background-color: " + bg + ";"
        );

        HBox main = new HBox(18);

        VBox left = createFeed();

        VBox right = createRightSide();

        left.setPrefWidth(950);

        left.setMinWidth(800);

        right.setPrefWidth(240);

        right.setMinWidth(240);

        main.getChildren().addAll(
                left,
                right
        );

        content.getChildren().add(
                main
        );

        return content;
    }

    // =========================================================
    // FEED
    // =========================================================

    private VBox createFeed() {

        VBox feed = new VBox(15);

        feed.setPrefWidth(950);

        feed.setMinWidth(800);

        // -----------------------------------------------------
        // HEADING
        // -----------------------------------------------------

        VBox heading = new VBox(5);

        Label title = new Label(
                "Community"
        );

        title.setTextFill(
                Color.web(white)
        );

        title.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle = new Label(
                "Connect, share, and stay motivated with the FitCircle community."
        );

        subtitle.setTextFill(
                Color.web(white)
        );

        subtitle.setStyle(
                "-fx-font-size: 16px;"
        );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // -----------------------------------------------------
        // HEADER CARD
        // -----------------------------------------------------

        VBox headerCard = new VBox(12);

        headerCard.setPadding(
                new Insets(15)
        );

        headerCard.setStyle(
                "-fx-background-color: " + card + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        // -----------------------------------------------------
        // TITLE ROW
        // -----------------------------------------------------

        HBox titleRow = new HBox();

        Region titleSpace = new Region();

        HBox.setHgrow(
                titleSpace,
                Priority.ALWAYS
        );

        Button createPost = new Button(
                "+  Create Post"
        );

        createPost.setStyle(
                "-fx-background-color: " + green + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5px;" +
                "-fx-padding: 8px 14px;"
        );

        createPost.setOnAction(
                e -> showCreatePostDialog()
        );

        titleRow.getChildren().addAll(
                heading,
                titleSpace,
                createPost
        );

        // -----------------------------------------------------
        // SEARCH ROW
        // -----------------------------------------------------

        HBox searchRow = new HBox(8);

        searchField = new TextField();

        searchField.setPrefHeight(35);

        searchField.setStyle(
                "-fx-background-color: #111212;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #777;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-font-size: 15px;"
        );

        searchField.setPromptText(
                "Search community..."
        );

        HBox.setHgrow(
                searchField,
                Priority.ALWAYS
        );

        Button searchButton = new Button(
                "Search"
        );

        searchButton.setPrefHeight(35);

        searchButton.setStyle(
                "-fx-background-color: " + green + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5px;"
        );

        searchButton.setOnAction(
                e -> searchPosts()
        );

        Button filter = new Button(
                "☷  Filter"
        );

        filter.setPrefHeight(35);

        filter.setStyle(
                "-fx-background-color: #252625;" +
                "-fx-text-fill: " + white + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-font-size: 16px;"
        );

        filter.setOnAction(
                e -> loadPosts()
        );

        searchField.setOnAction(
                e -> searchPosts()
        );

        searchRow.getChildren().addAll(
                searchField,
                searchButton,
                filter
        );

        headerCard.getChildren().addAll(
                titleRow,
                searchRow
        );

        // -----------------------------------------------------
        // FEED CONTAINER
        // -----------------------------------------------------

        feedContainer = new VBox(15);

        loadPosts();

        // -----------------------------------------------------
        // LOAD MORE
        // -----------------------------------------------------

        Button loadMore = new Button(
                "Load More Posts"
        );

        loadMore.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + green + ";" +
                "-fx-border-color: " + green + ";" +
                "-fx-border-radius: 4px;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 8px 14px;"
        );

        loadMore.setOnAction(
                e -> {

                    postsToShow += 5;

                    displayPosts(
                            allPosts
                    );

                    if (postsToShow >= allPosts.size()) {

                        loadMore.setText(
                                "All Posts Loaded"
                        );

                        loadMore.setDisable(
                                true
                        );
                    }
                }
        );

        HBox loadMoreHolder =
                new HBox(loadMore);

        loadMoreHolder.setAlignment(
                Pos.CENTER
        );

        feed.getChildren().addAll(
                headerCard,
                feedContainer,
                loadMoreHolder
        );

        return feed;
    }

    // =========================================================
    // LOAD POSTS
    // =========================================================

    private void loadPosts() {

        try {

            allPosts =
                    controller.getAllPosts();

            if (allPosts == null) {

                allPosts =
                        new ArrayList<>();
            }

            postsToShow = 5;

            displayPosts(
                    allPosts
            );

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    "Error",
                    "Unable to load community posts."
            );
        }
    }

    // =========================================================
    // DISPLAY POSTS
    // =========================================================

    private void displayPosts(
            List<CommunityPost> posts
    ) {

        feedContainer.getChildren().clear();

        if (posts == null ||
                posts.isEmpty()) {

            Label empty = new Label(
                    "No community posts found."
            );

            empty.setTextFill(
                    Color.web(gray)
            );

            empty.setStyle(
                    "-fx-font-size: 15px;"
            );

            feedContainer.getChildren().add(
                    empty
            );

            return;
        }

        int count =
                Math.min(
                        postsToShow,
                        posts.size()
                );

        for (int i = 0; i < count; i++) {

            CommunityPost post =
                    posts.get(i);

            feedContainer.getChildren().add(
                    createPostCard(post)
            );
        }
    }

    // =========================================================
    // CREATE POST CARD
    // =========================================================

    private VBox createPostCard(
            CommunityPost post
    ) {

        VBox box = new VBox(12);

        box.setPadding(
                new Insets(15)
        );

        box.setStyle(
                "-fx-background-color: " + card + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        // =====================================================
        // USER ROW
        // =====================================================

        HBox userRow =
                new HBox(10);

        String userName =
                post.getUserName();

        if (userName == null ||
                userName.trim().isEmpty()) {

            userName = "User";
        }

        Label avatar =
                new Label(
                        userName
                                .substring(0, 1)
                                .toUpperCase()
                );

        avatar.setPrefSize(
                38,
                38
        );

        avatar.setAlignment(
                Pos.CENTER
        );

        avatar.setTextFill(
                Color.web(white)
        );

        avatar.setStyle(
                "-fx-background-color: #303530;" +
                "-fx-background-radius: 50%;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        VBox userInfo =
                new VBox(2);

        Label name =
                new Label(userName);

        name.setTextFill(
                Color.web(white)
        );

        name.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        String timestamp =
                post.getTimestamp();

        if (timestamp == null) {

            timestamp = "";
        }

        Label time =
                new Label(timestamp);

        time.setTextFill(
                Color.web(gray)
        );

        time.setStyle(
                "-fx-font-size: 9px;"
        );

        userInfo.getChildren().addAll(
                name,
                time
        );

        Region userSpace =
                new Region();

        HBox.setHgrow(
                userSpace,
                Priority.ALWAYS
        );

        Label more =
                new Label("•••");

        more.setTextFill(
                Color.web(gray)
        );

        userRow.getChildren().addAll(
                avatar,
                userInfo,
                userSpace,
                more
        );

        // =====================================================
        // POST TEXT
        // =====================================================

        String text =
                post.getContent();

        if (text == null) {

            text = "";
        }

        Label postText =
                new Label(text);

        postText.setWrapText(true);

        postText.setTextFill(
                Color.web(white)
        );

        postText.setStyle(
                "-fx-font-size: 12px;"
        );

        box.getChildren().addAll(
                userRow,
                postText
        );

        // =====================================================
        // IMAGE
        // =====================================================

        String imagePath =
                post.getImageUrl();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                File imageFile =
                        new File(imagePath);

                if (imageFile.exists()) {

                    Image image =
                            new Image(
                                    imageFile
                                            .toURI()
                                            .toString()
                            );

                    ImageView imageView =
                            new ImageView(image);

                    imageView.setPreserveRatio(
                            true
                    );

                    imageView.setFitWidth(
                            880
                    );

                    imageView.setFitHeight(
                            400
                    );

                    VBox imageBox =
                            new VBox(imageView);

                    imageBox.setAlignment(
                            Pos.CENTER
                    );

                    imageBox.setPadding(
                            new Insets(5)
                    );

                    imageBox.setStyle(
                            "-fx-background-color: #252925;" +
                            "-fx-background-radius: 5px;"
                    );

                    box.getChildren().add(
                            imageBox
                    );
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        // =====================================================
        // STATISTICS
        // =====================================================

        HBox statistics =
                new HBox(15);

        Label likeCount =
                new Label(
                        post.getLikes() +
                        " Likes"
                );

        Label commentCount =
                new Label(
                        post.getComments() +
                        " Comments"
                );

        Label shareCount =
                new Label(
                        post.getShares() +
                        " Shares"
                );

        likeCount.setTextFill(
                Color.web(gray)
        );

        commentCount.setTextFill(
                Color.web(gray)
        );

        shareCount.setTextFill(
                Color.web(gray)
        );

        statistics.getChildren().addAll(
                likeCount,
                commentCount,
                shareCount
        );

        // =====================================================
        // SEPARATOR
        // =====================================================

        Region separator =
                new Region();

        separator.setPrefHeight(1);

        separator.setMaxWidth(
                Double.MAX_VALUE
        );

        separator.setStyle(
                "-fx-background-color: " +
                border + ";"
        );

        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        HBox actions =
                new HBox();

        Button like =
                actionButton(
                        "♡  Like"
                );

        Button comment =
                actionButton(
                        "□  Comment"
                );

        Button share =
                actionButton(
                        "↗  Share"
                );

        Region space1 =
                new Region();

        Region space2 =
                new Region();

        HBox.setHgrow(
                space1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                space2,
                Priority.ALWAYS
        );

        actions.getChildren().addAll(
                like,
                space1,
                comment,
                space2,
                share
        );

        // =====================================================
        // LIKE
        // =====================================================

        like.setOnAction(
                e -> {

                    boolean success =
                            controller.likePost(
                                    post.getPostId()
                            );

                    if (success) {

                        post.increaseLikes();

                        like.setText(
                                "♥  Liked"
                        );

                        like.setTextFill(
                                Color.web(green)
                        );

                        likeCount.setText(
                                post.getLikes() +
                                " Likes"
                        );
                    }
                }
        );

        // =====================================================
        // COMMENT
        // =====================================================

        comment.setOnAction(
                e -> {

                    boolean success =
                            controller.addComment(
                                    post.getPostId()
                            );

                    if (success) {

                        post.increaseComments();

                        comment.setText(
                                "Comment ✓"
                        );

                        commentCount.setText(
                                post.getComments() +
                                " Comments"
                        );
                    }
                }
        );

        // =====================================================
        // SHARE
        // =====================================================

        share.setOnAction(
                e -> {

                    boolean success =
                            controller.sharePost(
                                    post.getPostId()
                            );

                    if (success) {

                        post.increaseShares();

                        share.setText(
                                "Shared ✓"
                        );

                        shareCount.setText(
                                post.getShares() +
                                " Shares"
                        );
                    }
                }
        );

        // =====================================================
        // DELETE
        // =====================================================

        if (post.getUserId() != null &&
                post.getUserId().equals(
                        currentUserId
                )) {

            Button delete =
                    actionButton(
                            "Delete"
                    );

            delete.setTextFill(
                    Color.web("#ff6666")
            );

            delete.setOnAction(
                    e -> {

                        boolean success =
                                controller.deletePost(
                                        post.getPostId()
                                );

                        if (success) {

                            loadPosts();

                        } else {

                            showAlert(
                                    "Error",
                                    "Post could not be deleted."
                            );
                        }
                    }
            );

            actions.getChildren().add(
                    delete
            );
        }

        box.getChildren().addAll(
                statistics,
                separator,
                actions
        );

        return box;
    }

    // =========================================================
    // CREATE POST DIALOG
    // =========================================================

    private void showCreatePostDialog() {

        VBox form =
                new VBox(12);

        form.setPadding(
                new Insets(20)
        );

        form.setStyle(
                "-fx-background-color: " +
                bg + ";"
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Create Community Post"
                );

        title.setTextFill(
                Color.web(white)
        );

        title.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        // =====================================================
        // CONTENT
        // =====================================================

        TextArea content =
                new TextArea();

        content.setPromptText(
                "Write something to the community..."
        );

        content.setPrefRowCount(
                6
        );

        content.setWrapText(
                true
        );

        content.setStyle(
                "-fx-control-inner-background: #111212;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #777;" +
                "-fx-border-color: " + border + ";" +
                "-fx-font-size: 14px;"
        );

        // =====================================================
        // IMAGE PREVIEW
        // =====================================================

        ImageView imagePreview =
                new ImageView();

        imagePreview.setFitWidth(
                420
        );

        imagePreview.setFitHeight(
                220
        );

        imagePreview.setPreserveRatio(
                true
        );

        imagePreview.setVisible(
                false
        );

        Label imageStatus =
                new Label(
                        "No image selected"
                );

        imageStatus.setTextFill(
                Color.web(gray)
        );

        // =====================================================
        // UPLOAD IMAGE BUTTON
        // =====================================================

        Button uploadImage =
                new Button(
                        "📷  Upload Image"
                );

        uploadImage.setStyle(
                "-fx-background-color: #252625;" +
                "-fx-text-fill: " + white + ";" +
                "-fx-border-color: " + green + ";" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-padding: 8px 14px;" +
                "-fx-font-size: 14px;"
        );

        /*
         * Selected image cha actual file path
         * ya variable madhe store hoil.
         */

        final File[] selectedImage =
                new File[1];

        uploadImage.setOnAction(
                e -> {

                    FileChooser chooser =
                            new FileChooser();

                    chooser.setTitle(
                            "Select Community Image"
                    );

                    FileChooser.ExtensionFilter
                            imageFilter =
                            new FileChooser.ExtensionFilter(
                                    "Image Files",
                                    "*.png",
                                    "*.jpg",
                                    "*.jpeg",
                                    "*.gif"
                            );

                    chooser.getExtensionFilters().add(
                            imageFilter
                    );

                    Stage owner =
                            (Stage) uploadImage
                                    .getScene()
                                    .getWindow();

                    File file =
                            chooser.showOpenDialog(
                                    owner
                            );

                    if (file != null) {

                        selectedImage[0] =
                                file;

                        try {

                            Image image =
                                    new Image(
                                            file.toURI()
                                                    .toString()
                                    );

                            imagePreview.setImage(
                                    image
                            );

                            imagePreview.setVisible(
                                    true
                            );

                            imageStatus.setText(
                                    "Image selected: " +
                                    file.getName()
                            );

                            imageStatus.setTextFill(
                                    Color.web(green)
                            );

                        } catch (Exception ex) {

                            ex.printStackTrace();

                            showAlert(
                                    "Image Error",
                                    "Unable to load selected image."
                            );
                        }
                    }
                }
        );

        // =====================================================
        // REMOVE IMAGE BUTTON
        // =====================================================

        Button removeImage =
                new Button(
                        "Remove Image"
                );

        removeImage.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #ff6666;" +
                "-fx-border-color: #ff6666;" +
                "-fx-border-radius: 5px;" +
                "-fx-padding: 7px 12px;"
        );

        removeImage.setOnAction(
                e -> {

                    selectedImage[0] =
                            null;

                    imagePreview.setImage(
                            null
                    );

                    imagePreview.setVisible(
                            false
                    );

                    imageStatus.setText(
                            "No image selected"
                    );

                    imageStatus.setTextFill(
                            Color.web(gray)
                    );
                }
        );

        HBox imageButtons =
                new HBox(10);

        imageButtons.getChildren().addAll(
                uploadImage,
                removeImage
        );

        // =====================================================
        // SAVE BUTTON
        // =====================================================

        Button save =
                new Button(
                        "Create Post"
                );

        save.setStyle(
                "-fx-background-color: " + green + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5px;" +
                "-fx-padding: 9px 18px;"
        );

        // =====================================================
        // CANCEL BUTTON
        // =====================================================

        Button cancel =
                new Button(
                        "Cancel"
                );

        cancel.setStyle(
                "-fx-background-color: #252625;" +
                "-fx-text-fill: " + white + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 5px;" +
                "-fx-padding: 9px 18px;"
        );

        // =====================================================
        // BUTTON ROW
        // =====================================================

        HBox buttonRow =
                new HBox(10);

        buttonRow.setAlignment(
                Pos.CENTER_RIGHT
        );

        buttonRow.getChildren().addAll(
                cancel,
                save
        );

        // =====================================================
        // FORM
        // =====================================================

        form.getChildren().addAll(
                title,
                content,
                imageButtons,
                imageStatus,
                imagePreview,
                buttonRow
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(form);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setStyle(
                "-fx-background: " + bg + ";" +
                "-fx-background-color: " + bg + ";"
        );

        // =====================================================
        // WINDOW
        // =====================================================

        Stage stage =
                new Stage();

        stage.setTitle(
                "Create Community Post"
        );

        javafx.scene.Scene scene =
                new javafx.scene.Scene(
                        scrollPane,
                        560,
                        600
                );

        stage.setScene(
                scene
        );

        // =====================================================
        // SAVE POST
        // =====================================================

        save.setOnAction(
                e -> {

                    String postContent =
                            content.getText();

                    if (postContent == null ||
                            postContent.trim().isEmpty()) {

                        showAlert(
                                "Validation",
                                "Please enter post content."
                        );

                        return;
                    }

                    String imagePath = "";

                    if (selectedImage[0] != null) {

                        imagePath =
                                selectedImage[0]
                                        .getAbsolutePath();
                    }

                    boolean success =
                            controller.createPost(
                                    currentUserId,
                                    currentUserName,
                                    postContent.trim(),
                                    imagePath
                            );

                    if (success) {

                        stage.close();

                        loadPosts();

                        showAlert(
                                "Success",
                                "Community post created successfully."
                        );

                    } else {

                        showAlert(
                                "Error",
                                "Post could not be created."
                        );
                    }
                }
        );

        // =====================================================
        // CANCEL
        // =====================================================

        cancel.setOnAction(
                e -> stage.close()
        );

        stage.show();
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchPosts() {

        String keyword =
                searchField.getText();

        try {

            List<CommunityPost> result =
                    controller.searchPosts(
                            keyword
                    );

            postsToShow = 5;

            displayPosts(
                    result
            );

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    "Search Error",
                    "Unable to search posts."
            );
        }
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private Button actionButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + white + ";" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 5px;"
        );

        return button;
    }

    // =========================================================
    // RIGHT SIDE
    // =========================================================

    private VBox createRightSide() {

        VBox right =
                new VBox(15);

        right.setPrefWidth(
                240
        );

        right.setMinWidth(
                240
        );

        right.getChildren().addAll(
                trendingCommunities(),
                activeMembers(),
                guidelines()
        );

        return right;
    }

    // =========================================================
    // TRENDING COMMUNITIES
    // =========================================================

    private VBox trendingCommunities() {

        VBox box =
                sideCard();

        box.getChildren().add(
                sideTitle(
                        "Trending Communities"
                )
        );

        box.getChildren().add(
                communityItem(
                        "♟",
                        "Pune Runners Club",
                        "1.2K members"
                )
        );

        box.getChildren().add(
                communityItem(
                        "↗",
                        "Fit & Strong",
                        "980 members"
                )
        );

        box.getChildren().add(
                communityItem(
                        "♧",
                        "Cycling Warriors",
                        "750 members"
                )
        );

        Button discover =
                new Button(
                        "Discover More"
                );

        discover.setMaxWidth(
                Double.MAX_VALUE
        );

        discover.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + green + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-font-size: 12px;"
        );

        discover.setOnAction(e -> {
            if (onDiscoverMore != null) {
                onDiscoverMore.run();
            }
        });

        box.getChildren().add(
                discover
        );

        return box;
    }

    // =========================================================
    // COMMUNITY ITEM
    // =========================================================

    private HBox communityItem(
            String iconText,
            String name,
            String members
    ) {

        HBox row =
                new HBox(8);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label(iconText);

        icon.setTextFill(
                Color.web(white)
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: #303530;" +
                "-fx-padding: 6px;" +
                "-fx-font-size: 12px;"
        );

        VBox info =
                new VBox(2);

        Label title =
                new Label(name);

        title.setWrapText(
                true
        );

        title.setTextFill(
                Color.web(white)
        );

        title.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        Label member =
                new Label(members);

        member.setTextFill(
                Color.web(gray)
        );

        member.setStyle(
                "-fx-font-size: 12px;"
        );

        info.getChildren().addAll(
                title,
                member
        );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button view =
                new Button(
                        "View"
                );

        view.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + green + ";" +
                "-fx-font-size: 12px;"
        );

        view.setOnAction(e -> {
            if (onDiscoverMore != null) {
                onDiscoverMore.run();
            }
        });

        row.getChildren().addAll(
                icon,
                info,
                space,
                view
        );

        return row;
    }

    // =========================================================
    // ACTIVE MEMBERS
    // =========================================================

    private VBox activeMembers() {

        VBox box =
                sideCard();

        box.getChildren().add(
                sideTitle(
                        "Active Members"
                )
        );

        HBox members =
                new HBox(8);

        members.getChildren().addAll(
                memberAvatar("A"),
                memberAvatar("P"),
                memberAvatar("R"),
                memberAvatar("S")
        );

        box.getChildren().add(
                members
        );

        return box;
    }

    // =========================================================
    // MEMBER AVATAR
    // =========================================================

    private Label memberAvatar(
            String name
    ) {

        Label avatar =
                new Label(name);

        avatar.setTextFill(
                Color.web(white)
        );

        avatar.setAlignment(
                Pos.CENTER
        );

        avatar.setStyle(
                "-fx-background-color: #303530;" +
                "-fx-background-radius: 50%;" +
                "-fx-padding: 8px 10px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        return avatar;
    }

    // =========================================================
    // GUIDELINES
    // =========================================================

    private VBox guidelines() {

        VBox box =
                sideCard();

        box.getChildren().add(
                sideTitle(
                        "♧ Guidelines"
                )
        );

        box.getChildren().addAll(
                guideline(
                        "✓ Be respectful and supportive"
                ),
                guideline(
                        "✓ Encourage others in their goals"
                ),
                guideline(
                        "✓ Keep discussions fitness related"
                ),
                guideline(
                        "⊗ No spam or self-promotion"
                )
        );

        return box;
    }

    // =========================================================
    // GUIDELINE
    // =========================================================

    private Label guideline(
            String text
    ) {

        Label label =
                new Label(text);

        label.setWrapText(
                true
        );

        label.setTextFill(
                Color.web(white)
        );

        label.setStyle(
                "-fx-font-size: 13px;"
        );

        return label;
    }

    // =========================================================
    // SIDE CARD
    // =========================================================

    private VBox sideCard() {

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(12)
        );

        box.setStyle(
                "-fx-background-color: " + card + ";" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        return box;
    }

    // =========================================================
    // SIDE TITLE
    // =========================================================

    private Label sideTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setWrapText(
                true
        );

        label.setTextFill(
                Color.web(white)
        );

        label.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        return label;
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        com.flexforce.view.components.DialogUtils.applyTheme(alert);

        alert.getDialogPane().lookup(".content.label").setStyle(
            "-fx-text-fill: white;"
        );
        
        alert.showAndWait();
    }
}