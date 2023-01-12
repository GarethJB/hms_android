package com.example.androidhms.staff.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.databinding.ActivityStaffScheduleBinding;
import com.example.androidhms.staff.StaffBaseActivity;
import com.example.androidhms.util.dialog.CalendarDialog;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Timestamp;

public class ScheduleActivity extends StaffBaseActivity {

    private ActivityStaffScheduleBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Util.setEditTextDate(this, getLayoutInflater(), bind.etDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                Timestamp ts = Timestamp.valueOf(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " 00:00:00");
                bind.etDate.setText(Util.getDate(ts));
                dialog.dismiss();
            }
        });
    }

    @Override
    protected View getLayoutResource() {
        bind = ActivityStaffScheduleBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }


}