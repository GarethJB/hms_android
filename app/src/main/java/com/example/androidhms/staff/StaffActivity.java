package com.example.androidhms.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffBinding;
import com.example.androidhms.staff.lookup.LookupActivity;
import com.example.androidhms.staff.messenger.MessengerActivity;
import com.example.androidhms.staff.outpatient.OutpatientActivity;
import com.example.androidhms.staff.schedule.ScheduleActivity;
import com.example.androidhms.staff.vo.StaffDTO;
import com.example.androidhms.staff.ward.WardActivity;
import com.example.androidhms.util.Util;

public class StaffActivity extends AppCompatActivity {

    private ActivityStaffBinding bind;
    private final StaffDTO staff = Util.staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.tvName.setText(staff.getName());
        bind.clLookup.setOnClickListener(onMenuClick());
        bind.clOutpatient.setOnClickListener(onMenuClick());
        bind.clWard.setOnClickListener(onMenuClick());
        bind.clSchedule.setOnClickListener(onMenuClick());
        bind.clMessanger.setOnClickListener(onMenuClick());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        // 마이페이지
        bind.rlMypage.setOnClickListener(v ->
                startActivity(new Intent(this, StaffMyPageActivity.class)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.staff == null) {
            startActivity(new Intent(this, StaffLoginActivity.class));
            finish();
        }
    }

    private View.OnClickListener onMenuClick() {
        return v -> {
            Intent intent = null;
            if (v.getId() == R.id.cl_lookup) {
                intent = new Intent(StaffActivity.this, LookupActivity.class);
            } else if (v.getId() == R.id.cl_outpatient) {
                intent = new Intent(StaffActivity.this, OutpatientActivity.class);
            } else if (v.getId() == R.id.cl_ward) {
                intent = new Intent(StaffActivity.this, WardActivity.class);
            } else if (v.getId() == R.id.cl_messanger) {
                intent = new Intent(StaffActivity.this, MessengerActivity.class);
            } else if (v.getId() == R.id.cl_schedule) {
                intent = new Intent(StaffActivity.this, ScheduleActivity.class);
            }
            startActivity(intent);
        };
    }
}