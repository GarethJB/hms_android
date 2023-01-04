package com.example.androidhms.customer.info.timetable;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.ActivityCustomerTimeTableBinding;

public class TimeTableActivity extends AppCompatActivity {
    ActivityCustomerTimeTableBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerTimeTableBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        SelectDepartmentAdapter selectDepartmentAdapter = new SelectDepartmentAdapter(getLayoutInflater(), TimeTableActivity.this);

        bind.rcvSelectDepartment.setAdapter(selectDepartmentAdapter);
        bind.rcvSelectDepartment.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, RecyclerView.HORIZONTAL, false));

        StaffListAdapter staffListAdapter = new StaffListAdapter(getLayoutInflater(), TimeTableActivity.this);

        bind.rcvStaffList.setAdapter(staffListAdapter);
        bind.rcvStaffList.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, RecyclerView.VERTICAL, false));

    }
}