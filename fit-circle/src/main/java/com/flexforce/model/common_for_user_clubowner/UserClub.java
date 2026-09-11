package com.flexforce.model.common_for_user_clubowner;

import java.util.ArrayList;
import java.util.List;

public class UserClub {
        private UserClub club;
        private String clubId;
        private String clubName;
        private String description;
        private String image;
        private String ownerId;
        private String status;

        // Firebase ClubOwners fields
        private String clubAddress;
        private String category;
        private String startingPrice;
        private long capacity;
        private List<String> amenities;
        private String phone;
        private String website;

        // =====================================================
        // DEFAULT CONSTRUCTOR
        // =====================================================
        
        public UserClub() {

                amenities = new ArrayList<>();
        }

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public UserClub(
                        String clubId,
                        String clubName,
                        String description,
                        String image,
                        String ownerId,
                        String status) {

                this.clubId = clubId;
                this.clubName = clubName;
                this.description = description;
                this.image = image;
                this.ownerId = ownerId;
                this.status = status;

                amenities = new ArrayList<>();
        }

        // =====================================================
        // CLUB ID
        // =====================================================

        public String getClubId() {

                return clubId;
        }

        public void setClubId(String clubId) {

                this.clubId = clubId;
        }

        // =====================================================
        // CLUB NAME
        // =====================================================

        public String getClubName() {

                return clubName;
        }

        public void setClubName(String clubName) {

                this.clubName = clubName;
        }

        // =====================================================
        // DESCRIPTION
        // =====================================================

        public String getDescription() {

                return description;
        }

        public void setDescription(String description) {

                this.description = description;
        }

        // =====================================================
        // IMAGE
        // =====================================================

        public String getImage() {

                return image;
        }

        public void setImage(String image) {

                this.image = image;
        }

        // =====================================================
        // OWNER ID
        // =====================================================

        public String getOwnerId() {

                return ownerId;
        }

        public void setOwnerId(String ownerId) {

                this.ownerId = ownerId;
        }

        // =====================================================
        // STATUS
        // =====================================================

        public String getStatus() {

                return status;
        }

        public void setStatus(String status) {

                this.status = status;
        }

        // =====================================================
        // CLUB ADDRESS
        // =====================================================

        public String getClubAddress() {

                return clubAddress;
        }

        public void setClubAddress(String clubAddress) {

                this.clubAddress = clubAddress;
        }

        // =====================================================
        // CATEGORY
        // =====================================================

        public String getCategory() {

                return category;
        }

        public void setCategory(String category) {

                this.category = category;
        }

        // =====================================================
        // STARTING PRICE
        // =====================================================

        public String getStartingPrice() {

                return startingPrice;
        }

        public void setStartingPrice(String startingPrice) {

                this.startingPrice = startingPrice;
        }

        // =====================================================
        // CAPACITY
        // =====================================================

        public long getCapacity() {

                return capacity;
        }

        public void setCapacity(long capacity) {

                this.capacity = capacity;
        }

        // =====================================================
        // AMENITIES
        // =====================================================

        public List<String> getAmenities() {

                return amenities;
        }

        public void setAmenities(List<String> amenities) {

                this.amenities = amenities;
        }

        // =====================================================
        // PHONE
        // =====================================================

        public String getPhone() {

                return phone;
        }

        public void setPhone(String phone) {

                this.phone = phone;
        }

        // =====================================================
        // WEBSITE
        // =====================================================

        public String getWebsite() {

                return website;
        }

        public void setWebsite(String website) {

                this.website = website;
        }
}