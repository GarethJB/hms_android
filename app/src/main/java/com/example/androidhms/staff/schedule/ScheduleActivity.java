package com.example.androidhms.staff.schedule;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffScheduleBinding;
import com.example.androidhms.databinding.DialogCalendarBinding;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Timestamp;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    private ActivityStaffScheduleBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffScheduleBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        Util.setEditTextDate(this, getLayoutInflater(), bind.etDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                Timestamp ts = Timestamp.valueOf(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " 00:00:00");
                bind.etDate.setText(Util.getDate(ts));
                dialog.dismiss();
            }
        });
    }

}