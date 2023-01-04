package com.example.androidhms.staff.ward;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityDoctorWardBinding;

public class DoctorWardActivity extends AppCompatActivity {

    private ActivityDoctorWardBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDoctorWardBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        setContentView(bind.getRoot());
    }
}