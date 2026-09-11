package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.user.CommunityPost;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

public class CommunityPostDAO {

    private final Firestore db;

    private static final String COLLECTION = "CommunityPosts";

    public CommunityPostDAO() {
        db = FirebaseConfig.getFirebaseConfig();
    }

    // =========================================================
    // ADD POST
    // =========================================================

    public boolean addPost(CommunityPost post) {

        try {

            if (post.getPostId() == null ||
                    post.getPostId().isEmpty()) {

                post.setPostId(UUID.randomUUID().toString());
            }

            db.collection(COLLECTION)
                    .document(post.getPostId())
                    .set(post)
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // GET ALL POSTS
    // =========================================================

    public List<CommunityPost> getAllPosts() {

        List<CommunityPost> posts = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                            .orderBy(
                                    "timestamp",
                                    Query.Direction.DESCENDING)
                            .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                CommunityPost post =
                        document.toObject(CommunityPost.class);

                if (post != null) {

                    if (post.getPostId() == null ||
                            post.getPostId().isEmpty()) {

                        post.setPostId(document.getId());
                    }

                    posts.add(post);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return posts;
    }

    // =========================================================
    // GET SINGLE POST
    // =========================================================

    public CommunityPost getPost(String postId) {

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                            .document(postId)
                            .get()
                            .get();

            if (document.exists()) {

                CommunityPost post =
                        document.toObject(CommunityPost.class);

                if (post != null) {
                    post.setPostId(document.getId());
                }

                return post;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE POST
    // =========================================================

    public boolean updatePost(CommunityPost post) {

        try {

            db.collection(COLLECTION)
                    .document(post.getPostId())
                    .set(post)
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // DELETE POST
    // =========================================================

    public boolean deletePost(String postId) {

        try {

            db.collection(COLLECTION)
                    .document(postId)
                    .delete()
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // LIKE POST
    // =========================================================

    public boolean likePost(String postId) {

        try {

            CommunityPost post = getPost(postId);

            if (post == null) {
                return false;
            }

            post.increaseLikes();

            return updatePost(post);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // REMOVE LIKE
    // =========================================================

    public boolean unlikePost(String postId) {

        try {

            CommunityPost post = getPost(postId);

            if (post == null) {
                return false;
            }

            post.decreaseLikes();

            return updatePost(post);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // ADD COMMENT COUNT
    // =========================================================

    public boolean addComment(String postId) {

        try {

            CommunityPost post = getPost(postId);

            if (post == null) {
                return false;
            }

            post.increaseComments();

            return updatePost(post);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // SHARE POST
    // =========================================================

    public boolean sharePost(String postId) {

        try {

            CommunityPost post = getPost(postId);

            if (post == null) {
                return false;
            }

            post.increaseShares();

            return updatePost(post);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
