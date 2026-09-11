package com.flexforce.model.admin;

public class CarouselImage {
    
    private String imageId;
    private String imageUrl;
    private String uploadedAt;

    public CarouselImage() {
    }

    public CarouselImage(String imageId, String imageUrl, String uploadedAt) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.uploadedAt = uploadedAt;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
