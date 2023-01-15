package com.example.androidhms.staff.vo;

public class ScheduleVO {

    private int schedule_id, staff_id;
    private String content, time;
    private boolean complete;

    public ScheduleVO(int schedule_id, int staff_id, String content, String complete, String time) {
        this.schedule_id = schedule_id;
        this.staff_id = staff_id;
        this.content = content;
        this.time = time;
        this.complete = complete.equals("Y");
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public boolean isComplete() {
        return complete;
    }
}
