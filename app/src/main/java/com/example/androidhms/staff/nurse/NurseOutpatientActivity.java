package com.example.androidhms.staff.nurse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityNurseOutpatientBinding;
import com.example.androidhms.staff.OutpatientLookupFragment;
import com.example.androidhms.util.ActivityUtil;

public class NurseOutpatientActivity extends AppCompatActivity {

    private ActivityNurseOutpatientBinding bind;
    private ActivityUtil activityUtil;
    private OutpatientLookupFragment lookupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityNurseOutpatientBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        activityUtil = new ActivityUtil(this);
        lookupFragment = new OutpatientLookupFragment();

        activityUtil.addFragment(R.id.fl_container, lookupFragment);
        activityUtil.showFragment(lookupFragment);
    }

}