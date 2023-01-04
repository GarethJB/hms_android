package com.example.androidhms.staff.ward;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.databinding.ActivityStaffDoctorWardBinding;

public class DoctorWardActivity extends AppCompatActivity {

    private ActivityStaffDoctorWardBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffDoctorWardBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        setContentView(bind.getRoot());
    }
}