package com.example.androidhms.reception.search.record;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityDetailAppointmentBinding;
import com.example.androidhms.databinding.ActivityDetailMediRecordBinding;
import com.google.android.material.tabs.TabLayout;

public class DetailMediRecordActivity extends AppCompatActivity {

    ActivityDetailMediRecordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMediRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent record_intent= getIntent();

        binding.tabLayout.addTab(new TabLayout.Tab().setText("6개월"));
        binding.tabLayout.addTab(new TabLayout.Tab().setText("1년"));

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new MonthFragment()).commit();
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Log.d("로그", "onTabSelected: " + "탭클릭");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new MonthFragment()).commit();
                }else if(position == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new YearFragment()).commit();
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