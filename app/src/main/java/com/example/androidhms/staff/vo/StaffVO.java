package com.example.androidhms.staff.vo;

import java.io.Serializable;

public class StaffVO implements Serializable {

    private int staff_id, department_id, staff_level;
    private String name;

    public StaffVO(int staff_id, int department_id, int staff_level, String name) {
        this.staff_id = staff_id;
        this.department_id = department_id;
        this.staff_level = staff_level;
        this.name = name;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getName() {
        return name;
    }

    public int getStaff_level() {
        return staff_level;
    }

}
