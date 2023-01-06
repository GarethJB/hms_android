package com.example.androidhms.staff.vo;

import java.io.Serializable;

public class StaffVO implements Serializable {

    private int staff_id, staff_level, department_id;
    private String name, department_name, introduction;

    public StaffVO(int staff_id, int staff_level, int department_id, String name, String department_name, String introduction) {
        this.staff_id = staff_id;
        this.staff_level = staff_level;
        this.department_id = department_id;
        this.name = name;
        this.department_name = department_name;
        this.introduction = introduction;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getStaff_level() {
        return staff_level;
    }

    public void setStaff_level(int staff_level) {
        this.staff_level = staff_level;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
