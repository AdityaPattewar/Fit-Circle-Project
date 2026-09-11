
package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.StaffDAO;
import com.flexforce.model.club_owner.Staff;

public class StaffController {

    private final StaffDAO staffDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public StaffController() {
        this.staffDAO = new StaffDAO();
    }

    // =========================================================
    // GET STAFF
    // =========================================================

    public List<Staff> getStaff(String clubId) {

        try {

            if (clubId == null || clubId.trim().isEmpty()) {

                return staffDAO.getStaff();

            }

            return staffDAO.getStaffByClubId(
                    clubId.trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    // =========================================================
    // ADD STAFF
    // =========================================================

    public boolean addStaff(
            String clubId,
            String name,
            String role,
            String email,
            String phone,
            String status
    ) {

        try {

            // -----------------------------
            // VALIDATION
            // -----------------------------

            if (name == null ||
                    name.trim().isEmpty()) {

                System.out.println(
                        "Staff name is required."
                );

                return false;
            }

            if (role == null ||
                    role.trim().isEmpty()) {

                System.out.println(
                        "Staff role is required."
                );

                return false;
            }

            // -----------------------------
            // CREATE STAFF
            // -----------------------------

            Staff staff = new Staff();

            staff.setClubId(
                    safe(clubId)
            );

            staff.setName(
                    name.trim()
            );

            staff.setRole(
                    role.trim()
            );

            staff.setEmail(
                    safe(email)
            );

            staff.setPhone(
                    safe(phone)
            );

            if (status == null ||
                    status.trim().isEmpty()) {

                staff.setStatus("Active");

            } else {

                staff.setStatus(
                        status.trim()
                );
            }

            // -----------------------------
            // SAVE
            // -----------------------------

            staffDAO.saveStaff(staff);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // UPDATE STAFF
    // =========================================================

    public boolean updateStaff(Staff staff) {

        try {

            if (staff == null) {

                System.out.println(
                        "Staff cannot be null."
                );

                return false;
            }

            if (staff.getStaffId() == null ||
                    staff.getStaffId()
                         .trim()
                         .isEmpty()) {

                System.out.println(
                        "Staff ID is required."
                );

                return false;
            }

            if (staff.getName() == null ||
                    staff.getName()
                         .trim()
                         .isEmpty()) {

                System.out.println(
                        "Staff name is required."
                );

                return false;
            }

            staffDAO.updateStaff(staff);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE STAFF
    // =========================================================

    public boolean deleteStaff(String staffId) {

        try {

            if (staffId == null ||
                    staffId.trim().isEmpty()) {

                System.out.println(
                        "Staff ID is required."
                );

                return false;
            }

            staffDAO.deleteStaff(
                    staffId.trim()
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET STAFF BY ID
    // =========================================================

    public Staff getStaffById(String staffId) {

        try {

            if (staffId == null ||
                    staffId.trim().isEmpty()) {

                return null;
            }

            return staffDAO.getStaff(
                    staffId.trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // GET STAFF BY EMAIL
    // =========================================================

    public Staff getStaffByEmail(String email) {

        try {

            if (email == null ||
                    email.trim().isEmpty()) {

                return null;
            }

            return staffDAO.getStaffByEmail(
                    email.trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // GET STAFF BY ROLE
    // =========================================================

    public List<Staff> getStaffByRole(String role) {

        try {

            if (role == null ||
                    role.trim().isEmpty()) {

                return staffDAO.getStaff();
            }

            return staffDAO.getStaffByRole(
                    role.trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    // =========================================================
    // GET ACTIVE STAFF
    // =========================================================

    public List<Staff> getActiveStaff(String clubId) {

        try {

            if (clubId == null ||
                    clubId.trim().isEmpty()) {

                List<Staff> allStaff =
                        staffDAO.getStaff();

                return allStaff.stream()
                        .filter(staff ->
                                staff != null &&
                                "Active".equalsIgnoreCase(
                                        staff.getStatus()
                                )
                        )
                        .toList();
            }

            return staffDAO.getActiveStaff(
                    clubId.trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    // =========================================================
    // GET STAFF ON LEAVE
    // =========================================================

    public List<Staff> getStaffOnLeave(
            String clubId
    ) {

        try {

            if (clubId == null ||
                    clubId.trim().isEmpty()) {

                List<Staff> allStaff =
                        staffDAO.getStaff();

                return allStaff.stream()
                        .filter(staff ->
                                staff != null &&
                                "On Leave".equalsIgnoreCase(
                                        staff.getStatus()
                                )
                        )
                        .toList();
            }

            return staffDAO.getStaffOnLeave(
                    clubId.trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    // =========================================================
    // TOTAL STAFF
    // =========================================================

    public int getTotalStaff(String clubId) {

        return getStaff(clubId).size();
    }

    // =========================================================
    // TOTAL ACTIVE STAFF
    // =========================================================

    public int getTotalActiveStaff(String clubId) {

        return getActiveStaff(clubId).size();
    }

    // =========================================================
    // TOTAL ON LEAVE
    // =========================================================

    public int getTotalStaffOnLeave(String clubId) {

        return getStaffOnLeave(clubId).size();
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(String value) {

        if (value == null) {
            return "";
        }

        return value.trim();
    }
}

