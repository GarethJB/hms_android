package com.example.androidhms.staff.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityDoctorOutpatientBinding;
import com.example.androidhms.staff.OutpatientLookupFragment;
import com.example.androidhms.util.ActivityUtil;

public class DoctorOutpatientActivity extends AppCompatActivity {

    private ActivityDoctorOutpatientBinding bind;
    private ActivityUtil activityUtil;
    private OutpatientLookupFragment lookupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDoctorOutpatientBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        activityUtil = new ActivityUtil(this);
        lookupFragment = new OutpatientLookupFragment();

        activityUtil.addFragment(R.id.fl_container, lookupFragment);
        activityUtil.showFragment(lookupFragment);
    }
}