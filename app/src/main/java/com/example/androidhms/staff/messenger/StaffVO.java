package com.example.androidhms.staff.messenger;

public class StaffVO {

    private int staff_id, department_id;
    private String name;

    public StaffVO(int staff_id, int department_id, String name) {
        this.staff_id = staff_id;
        this.department_id = department_id;
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

}
