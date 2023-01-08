package com.example.androidhms.staff.outpatient;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffOutpatientBinding;
import com.example.androidhms.staff.vo.StaffDTO;
import com.example.androidhms.util.ActivityUtil;
import com.example.androidhms.util.Util;
import com.google.android.material.tabs.TabLayout;

public class OutpatientActivity extends AppCompatActivity {

    private ActivityStaffOutpatientBinding bind;
    private MedicalRecordFragment recordFragment;
    private ReceiptFragment receiptFragment;
    private ActivityUtil aUtil;
    private StaffDTO staff = Util.staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffOutpatientBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.toolbar.ivLeft.setOnClickListener(v -> finish());

        recordFragment = new MedicalRecordFragment();
        receiptFragment = new ReceiptFragment();
        aUtil = new ActivityUtil(this);
        aUtil.addFragment(R.id.fl_container, recordFragment);
        aUtil.addFragment(R.id.fl_container, receiptFragment);
        aUtil.hideFragment(recordFragment);
        bind.tlOutpatient.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        aUtil.showFragment(receiptFragment);
                        aUtil.hideFragment(recordFragment);
                        break;
                    case 1:
                        aUtil.showFragment(recordFragment);
                        aUtil.hideFragment(receiptFragment);
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