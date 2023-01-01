package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffBinding;
import com.example.androidhms.staff.nurse.NurseOutPatientActivity;

public class StaffActivity extends AppCompatActivity {

    private ActivityStaffBinding bind;
    private int staffLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        staffLevel = 2;

        bind.rlOutpatient.setOnClickListener(onMenuClick());
        bind.rlWard.setOnClickListener(onMenuClick());
        bind.rlSchedule.setOnClickListener(onMenuClick());
        bind.rlMessanger.setOnClickListener(onMenuClick());
    }

    private View.OnClickListener onMenuClick() {
        return v -> {
            if (staffLevel == 2) {
                Intent intent = null;
                if (v.getId() == R.id.rl_outpatient) {
                    intent = new Intent(StaffActivity.this, NurseOutPatientActivity.class);
                } else if (v.getId() == R.id.rl_ward) {
                    // intent = new Intent(StaffActivity.this, WardActivity.class)
                } else if (v.getId() == R.id.rl_schedule) {
                    // intent = new Intent(StaffActivity.this, ScheduleActivity.class)
                } else if (v.getId() == R.id.rl_messanger) {
                    intent = new Intent(StaffActivity.this, MessengerActivity.class);
                }
                startActivity(intent);
            }
        };
    }
}