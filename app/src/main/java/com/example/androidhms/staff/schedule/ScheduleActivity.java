package com.example.androidhms.staff.schedule;

import static com.example.androidhms.util.Util.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.databinding.ActivityStaffScheduleBinding;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Timestamp;

public class ScheduleActivity extends AppCompatActivity {

    private ActivityStaffScheduleBinding bind;
    private HmsFirebase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffScheduleBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

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
    protected void onResume() {
        super.onResume();
        fb = Util.setToolbar(this, bind.toolbar.toolbar);
        fb.makeChatRoom(staff.getStaff_id());
    }

    @Override
    protected void onPause() {
        super.onPause();
        fb.removeGetChatRoom();
    }


}