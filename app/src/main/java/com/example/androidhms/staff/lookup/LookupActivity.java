package com.example.androidhms.staff.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityLookupBinding;
import com.example.androidhms.util.ActivityUtil;
import com.google.android.material.tabs.TabLayout;

public class LookupActivity extends AppCompatActivity {

    private ActivityLookupBinding bind;
    private MedicalRecordFragment recordFragment;
    private PatientInfoFragment infoFragment;
    private ActivityUtil aUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLookupBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        recordFragment = new MedicalRecordFragment();
        infoFragment = new PatientInfoFragment();

        aUtil = new ActivityUtil(this);
        aUtil.addFragment(R.id.fl_container, infoFragment);
        aUtil.addFragment(R.id.fl_container, recordFragment);
        aUtil.hideFragment(recordFragment);
        aUtil.showFragment(infoFragment);

        bind.tlLookup.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        aUtil.showFragment(infoFragment);
                        aUtil.hideFragment(recordFragment);
                        break;
                    case 1:
                        aUtil.showFragment(recordFragment);
                        aUtil.hideFragment(infoFragment);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}