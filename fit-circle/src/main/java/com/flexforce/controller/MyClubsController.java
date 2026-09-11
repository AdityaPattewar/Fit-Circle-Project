package com.flexforce.controller;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


import com.flexforce.dao.ClubOwnerActivityDAO;
import com.flexforce.dao.UserClubDao;
import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.model.club_owner.ClubOwnerActivity;
import com.flexforce.model.common_for_user_clubowner.UserClub;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;

public class MyClubsController {
    
private UserClubMembershipDAO membershipDAO;
    private UserClubDao clubDao;
    private ClubOwnerActivityDAO activityDAO;

    public MyClubsController() {
        this.membershipDAO = new UserClubMembershipDAO();
        this.clubDao = new UserClubDao();
        this.activityDAO = new ClubOwnerActivityDAO();
    }

    public List<UserClub> getUserJoinedClubs(String userId) {
        List<UserClub> joinedClubs = new ArrayList<>();
        
        List<UserClubMembership> memberships = membershipDAO.getMembershipsByUser(userId);
        
        for (UserClubMembership mem : memberships) {
            if (mem.getClubId() != null && !mem.getClubId().trim().isEmpty()) {
                UserClub club = clubDao.getClubFromOwners(mem.getClubId());
                if (club != null) {
                    joinedClubs.add(club);
                }
            }
        }
        
        return joinedClubs;
    }

    public List<ClubOwnerActivity> getUpcomingActivities(List<UserClub> joinedClubs) {
        List<ClubOwnerActivity> allActivities = new ArrayList<>();
        
        for (UserClub club : joinedClubs) {
            if (club.getClubId() != null) {
                List<ClubOwnerActivity> activities = activityDAO.getActivitiesByClubId(club.getClubId());
                for (ClubOwnerActivity act : activities) {
                    // Assign club name to location field or some unused field if needed? 
                    // Actually, we'll map it in the view by looking up the club again.
                }
                allActivities.addAll(activities);
            }
        }
        
        // Sort by date 
        allActivities.sort((a1, a2) -> {
            String date1 = a1.getDate() != null ? a1.getDate() : "";
            String date2 = a2.getDate() != null ? a2.getDate() : "";
            return date1.compareTo(date2); 
        });
        
        return allActivities;
    }
}

