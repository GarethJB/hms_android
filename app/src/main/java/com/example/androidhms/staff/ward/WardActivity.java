package com.example.androidhms.staff.ward;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffWardBinding;
import com.example.androidhms.util.ActivityUtil;
import com.google.android.material.tabs.TabLayout;

public class WardActivity extends AppCompatActivity {

    private ActivityStaffWardBinding bind;
    private MyPatientFragment myPatientFragment;
    private WardFragment wardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffWardBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        setContentView(bind.getRoot());

        myPatientFragment = new MyPatientFragment();
        wardFragment = new WardFragment();
        ActivityUtil aUtil = new ActivityUtil(this);
        aUtil.addFragment(R.id.fl_container, myPatientFragment);
        aUtil.addFragment(R.id.fl_container, wardFragment);
        aUtil.hideFragment(wardFragment);
        bind.tlWard.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        aUtil.showFragment(myPatientFragment);
                        aUtil.hideFragment(wardFragment);
                        break;
                    case 1:
                        aUtil.showFragment(wardFragment);
                        aUtil.hideFragment(myPatientFragment);
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