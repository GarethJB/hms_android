package com.example.androidhms.staff.outpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityOutpatientBinding;
import com.example.androidhms.util.ActivityUtil;
import com.google.android.material.tabs.TabLayout;

public class OutpatientActivity extends AppCompatActivity {

    private ActivityOutpatientBinding bind;
    private MedicalRecordFragment recordFragment;
    private ReceiptFragment receiptFragment;
    private ActivityUtil aUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityOutpatientBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        recordFragment = new MedicalRecordFragment();
        receiptFragment = new ReceiptFragment();

        aUtil = new ActivityUtil(this);
        aUtil.addFragment(R.id.fl_container, receiptFragment);
        aUtil.addFragment(R.id.fl_container, recordFragment);
        aUtil.hideFragment(recordFragment);
        aUtil.showFragment(receiptFragment);

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