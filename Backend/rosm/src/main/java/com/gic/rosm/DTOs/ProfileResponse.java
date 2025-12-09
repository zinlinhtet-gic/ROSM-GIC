package com.gic.rosm.DTOs;

public class ProfileResponse {
    private String staff_name;
    private String staff_role;
    private Long staff_id;

    public ProfileResponse(String staff_name, String staff_role, Long staff_id) {
        this.staff_name = staff_name;
        this.staff_role = staff_role;
        this.staff_id = staff_id;
    }

    public Long getStaff_id() {
        return staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getStaff_role() {
        return staff_role;
    }

    public void setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public void setStaff_role(String staff_role) {
        this.staff_role = staff_role;
    }
}
