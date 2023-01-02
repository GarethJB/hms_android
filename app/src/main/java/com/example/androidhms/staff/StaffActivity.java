package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffBinding;
import com.example.androidhms.staff.doctor.DoctorOutpatientActivity;
import com.example.androidhms.staff.doctor.DoctorWardActivity;
import com.example.androidhms.staff.lookup.LookupActivity;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.staff.nurse.NurseWardActivity;

public class StaffActivity extends AppCompatActivity {

    private ActivityStaffBinding bind;
    private StaffVO staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent intent = getIntent();
        staff = (StaffVO) intent.getSerializableExtra("staff");

        bind.tvName.setText(staff.getName());
        bind.rlLookup.setOnClickListener(onMenuClick());
        bind.rlOutpatient.setOnClickListener(onMenuClick());
        bind.rlWard.setOnClickListener(onMenuClick());
        bind.rlSchedule.setOnClickListener(onMenuClick());
        bind.rlMessanger.setOnClickListener(onMenuClick());
    }

    private View.OnClickListener onMenuClick() {
        return v -> {
            Intent intent = null;
            if (staff.getStaff_level() == 1) {
                if (v.getId() == R.id.rl_lookup) {
                    intent = new Intent(StaffActivity.this, LookupActivity.class);
                } else if (v.getId() == R.id.rl_outpatient) {
                    intent = new Intent(StaffActivity.this, DoctorOutpatientActivity.class);
                } else if (v.getId() == R.id.rl_ward) {
                    intent = new Intent(StaffActivity.this, DoctorWardActivity.class);
                } else if (v.getId() == R.id.rl_messanger) {
                    intent = new Intent(StaffActivity.this, MessengerActivity.class);
                } else if (v.getId() == R.id.rl_schedule) {
                    intent = new Intent(StaffActivity.this, ScheduleActivity.class);
                }
            } else if (staff.getStaff_level() == 2) {
                if (v.getId() == R.id.rl_lookup) {
                    intent = new Intent(StaffActivity.this, LookupActivity.class);
                } else if (v.getId() == R.id.rl_outpatient) {
                    intent = new Intent(StaffActivity.this, NurseWardActivity.class);
                } else if (v.getId() == R.id.rl_ward) {
                    // intent = new Intent(StaffActivity.this, ScheduleActivity.class)
                } else if (v.getId() == R.id.rl_messanger) {
                    intent = new Intent(StaffActivity.this, MessengerActivity.class);
                } else if (v.getId() == R.id.rl_schedule) {
                    intent = new Intent(StaffActivity.this, ScheduleActivity.class);
                }
            }
            intent.putExtra("staff", staff);
            startActivity(intent);
        };
    }
}