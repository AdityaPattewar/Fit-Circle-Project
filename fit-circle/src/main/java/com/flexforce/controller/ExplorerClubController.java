package com.flexforce.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.flexforce.dao.UserClubDao;
import com.flexforce.model.common_for_user_clubowner.UserClub;

public class ExplorerClubController {

    private UserClubDao clubDao;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ExplorerClubController() {

        clubDao =
                new UserClubDao();
    }

    // =====================================================
    // GET ALL CLUBS
    // =====================================================

    public List<UserClub> getAllClubs() {

        return clubDao.getClubs();
    }

    // =====================================================
    // SEARCH CLUBS
    // =====================================================

    public List<UserClub> searchClubs(
            String searchText) {

        List<UserClub> allClubs =
                clubDao.getClubs();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            return allClubs;
        }

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        List<UserClub> filteredClubs =
                new ArrayList<>();

        for (UserClub club :
                allClubs) {

            // =================================================
            // CLUB NAME
            // =================================================

            String name =
                    club.getClubName() == null
                            ? ""
                            : club.getClubName()
                                  .toLowerCase();

            // =================================================
            // ADDRESS
            // =================================================

            String address =
                    club.getClubAddress() == null
                            ? ""
                            : club.getClubAddress()
                                  .toLowerCase();

            // =================================================
            // CATEGORY
            // =================================================

            String category =
                    club.getCategory() == null
                            ? ""
                            : club.getCategory()
                                  .toLowerCase();

            // =================================================
            // DESCRIPTION
            // =================================================

            String description =
                    club.getDescription() == null
                            ? ""
                            : club.getDescription()
                                  .toLowerCase();

            // =================================================
            // PHONE
            // =================================================

            String phone =
                    club.getPhone() == null
                            ? ""
                            : club.getPhone()
                                  .toLowerCase();

            // =================================================
            // SEARCH
            // =================================================

            if (name.contains(search)
                    || address.contains(search)
                    || category.contains(search)
                    || description.contains(search)
                    || phone.contains(search)) {

                filteredClubs.add(club);
            }
        }

        return filteredClubs;
    }

    // =====================================================
    // SORT CLUBS BY NAME
    // =====================================================

    public List<UserClub> sortClubsByName(
            List<UserClub> clubs) {

        if (clubs == null) {

            return new ArrayList<>();
        }

        clubs.sort(
                Comparator.comparing(
                        club -> {

                            if (club == null ||
                                    club.getClubName() == null) {

                                return "";
                            }

                            return club
                                    .getClubName()
                                    .toLowerCase();
                        }
                )
        );

        return clubs;
    }
}