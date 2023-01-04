package com.example.androidhms.staff.vo;

import com.example.androidhms.util.Util;

import java.io.Serializable;

public class StaffVO implements Serializable {

    private int staff_id, staff_level, department_id;
    private String name, department_name, lastChatCheckTime;

    public StaffVO(int staff_id, int staff_level, int department_id, String name, String department_name) {
        this.staff_id = staff_id;
        this.staff_level = staff_level;
        this.department_id = department_id;
        this.name = name;
        this.department_name = department_name;
        lastChatCheckTime = null;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public int getStaff_level() {
        return staff_level;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getLastChatCheckTime() {
        return lastChatCheckTime;
    }

    public void setLastChatCheckTime() {
        lastChatCheckTime = Util.getChatTimeStamp();
    }
}
