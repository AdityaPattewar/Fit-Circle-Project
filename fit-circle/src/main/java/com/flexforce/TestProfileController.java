package com.flexforce;

import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.controller.UserProfileController;
import com.flexforce.model.user.User;
import com.flexforce.dao.UserInfoDAO;
import com.flexforce.model.user.UserInfo;
import com.flexforce.config.FirebaseConfig;
import java.util.List;

public class TestProfileController {
    public static void main(String[] args) {
        try {
            System.out.println("Initializing Firebase...");
            FirebaseConfig.getFirebaseConfig();
            
            // Set up a mock currentUserId that matches the one we know works
            AuthControllerlogin auth = new AuthControllerlogin();
            
            // "PSkuuMt924XG7JJGpLtQAT36XlI3" belongs to Kunal
            String mockUserId = "PSkuuMt924XG7JJGpLtQAT36XlI3";
            System.out.println("Using mock user ID: " + mockUserId);
            
            // In AuthControllerlogin, currentUserId is private static. Let's see if we can set it.
            // Wait, we can't set private static easily.
            // Let's just create UserProfileController and use setCurrentUserId
            
            UserProfileController controller = new UserProfileController();
            controller.setCurrentUserId(mockUserId);
            
            System.out.println("Calling getCurrentUser...");
            User user = controller.getCurrentUser();
            
            if (user == null) {
                System.out.println("getCurrentUser returned NULL!");
            } else {
                System.out.println("getCurrentUser returned User: " + user.getFullName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone: " + user.getPhone());
                System.out.println("JoiningDate: " + user.getJoiningDate());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
