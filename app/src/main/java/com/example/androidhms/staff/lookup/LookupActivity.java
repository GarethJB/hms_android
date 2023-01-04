package com.example.androidhms.staff.lookup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.databinding.ActivityStaffLookupBinding;

public class LookupActivity extends AppCompatActivity {

    private ActivityStaffLookupBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffLookupBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        setContentView(bind.getRoot());
    }
}