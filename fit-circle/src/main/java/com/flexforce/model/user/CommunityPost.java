package com.flexforce.model.user;

public class CommunityPost {

    private String postId;
    private String userId;
    private String userName;
    private String content;
    private String imageUrl;
    private String timestamp;

    private int likes;
    private int comments;
    private int shares;

    // Required for Firestore
    public CommunityPost() {
    }

    public CommunityPost(
            String postId,
            String userId,
            String userName,
            String content,
            String imageUrl,
            String timestamp,
            int likes,
            int comments,
            int shares) {

        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }

    // ---------------------------------------------------------
    // GETTERS
    // ---------------------------------------------------------

    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public int getShares() {
        return shares;
    }

    // ---------------------------------------------------------
    // SETTERS
    // ---------------------------------------------------------

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    // ---------------------------------------------------------
    // HELPER METHODS
    // ---------------------------------------------------------

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    public void increaseComments() {
        this.comments++;
    }

    public void increaseShares() {
        this.shares++;
    }
}
