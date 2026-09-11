package com.flexforce.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.flexforce.dao.CommunityPostDAO;
import com.flexforce.model.user.CommunityPost;

public class CommunityController {

    private final CommunityPostDAO communityPostDAO;

    public CommunityController() {
        communityPostDAO = new CommunityPostDAO();
    }

    // =========================================================
    // LOAD ALL COMMUNITY POSTS
    // =========================================================

    public List<CommunityPost> getAllPosts() {

        return communityPostDAO.getAllPosts();
    }

    // =========================================================
    // GET SINGLE POST
    // =========================================================

    public CommunityPost getPost(String postId) {

        return communityPostDAO.getPost(postId);
    }

    // =========================================================
    // CREATE NEW POST
    // =========================================================

    public boolean createPost(
            String userId,
            String userName,
            String content,
            String imageUrl) {

        try {

            String postId =
                    java.util.UUID.randomUUID().toString();

            String timestamp =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter
                                            .ofPattern(
                                                    "yyyy-MM-dd HH:mm:ss"));

            CommunityPost post =
                    new CommunityPost(
                            postId,
                            userId,
                            userName,
                            content,
                            imageUrl,
                            timestamp,
                            0,
                            0,
                            0
                    );

            return communityPostDAO.addPost(post);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // UPDATE POST
    // =========================================================

    public boolean updatePost(CommunityPost post) {

        return communityPostDAO.updatePost(post);
    }

    // =========================================================
    // DELETE POST
    // =========================================================

    public boolean deletePost(String postId) {

        return communityPostDAO.deletePost(postId);
    }

    // =========================================================
    // LIKE
    // =========================================================

    public boolean likePost(String postId) {

        return communityPostDAO.likePost(postId);
    }

    // =========================================================
    // UNLIKE
    // =========================================================

    public boolean unlikePost(String postId) {

        return communityPostDAO.unlikePost(postId);
    }

    // =========================================================
    // COMMENT
    // =========================================================

    public boolean addComment(String postId) {

        return communityPostDAO.addComment(postId);
    }

    // =========================================================
    // SHARE
    // =========================================================

    public boolean sharePost(String postId) {

        return communityPostDAO.sharePost(postId);
    }

    // =========================================================
    // SEARCH POSTS
    // =========================================================

    public List<CommunityPost> searchPosts(String keyword) {

        List<CommunityPost> allPosts =
                communityPostDAO.getAllPosts();

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            return allPosts;
        }

        String search =
                keyword.toLowerCase().trim();

        allPosts.removeIf(post -> {

            String content =
                    post.getContent() == null
                            ? ""
                            : post.getContent().toLowerCase();

            String userName =
                    post.getUserName() == null
                            ? ""
                            : post.getUserName().toLowerCase();

            return !content.contains(search)
                    && !userName.contains(search);
        });

        return allPosts;
    }
}