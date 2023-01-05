package com.example.androidhms.customer.info.timetable;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.ActivityCustomerTimeTableBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity {
    private ActivityCustomerTimeTableBinding bind;
    private ArrayList<StaffVO> staff;
    private final String TAG = "로그";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerTimeTableBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        //의료진 조회
        int department_id = 1;
        new RetrofitMethod().setParams("department_id", department_id)
                .sendPost("stafflist.ap", (isResult, data) -> {
                    Log.d(TAG, "의료진 : " + data);
                    for (int i = 0; i <= staff.size(); i++) {
                        staff.add(new Gson().fromJson(data, StaffVO.class));
                    }
                    //staff = (ArrayList<StaffVO>) new Gson().fromJson(data, StaffVO.class);

                });


        //진료과 선택 화면
        SelectDepartmentAdapter selectDepartmentAdapter = new SelectDepartmentAdapter(getLayoutInflater(), TimeTableActivity.this);
        bind.rcvSelectDepartment.setAdapter(selectDepartmentAdapter);
        bind.rcvSelectDepartment.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, RecyclerView.HORIZONTAL, false));
        
        //의료진 화면
        StaffListAdapter staffListAdapter = new StaffListAdapter(getLayoutInflater(), TimeTableActivity.this, staff);
        bind.rcvStaffList.setAdapter(staffListAdapter);
        bind.rcvStaffList.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, RecyclerView.VERTICAL, false));

    }
}