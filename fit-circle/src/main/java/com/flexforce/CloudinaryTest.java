package com.flexforce;

import java.io.File;

import com.flexforce.controller.CloudinaryImageUploadController;

public class CloudinaryTest {

    public static void main(String[] args) {

        File file = new File("test.jpg");

        CloudinaryImageUploadController controller =
                new CloudinaryImageUploadController();

        String url = controller.imageUpload(file);

        System.out.println("Uploaded Image URL: " + url);
    }
}