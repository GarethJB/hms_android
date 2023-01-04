package com.example.androidhms.staff.ward;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityNurseWardBinding;
import com.example.androidhms.databinding.ActivityStaffNurseWardBinding;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class NurseWardActivity extends AppCompatActivity {

    private ActivityStaffNurseWardBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffNurseWardBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        Util.setEditTextDate(this, getLayoutInflater(), bind.etDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                String selectedDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                bind.etDate.setText(selectedDate);
                dialog.dismiss();
            }
        });
        setContentView(bind.getRoot());
    }
}