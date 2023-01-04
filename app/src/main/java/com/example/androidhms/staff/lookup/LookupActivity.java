package com.example.androidhms.staff.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityLookupBinding;
import com.example.androidhms.util.ActivityUtil;
import com.google.android.material.tabs.TabLayout;

public class LookupActivity extends AppCompatActivity {

    private ActivityLookupBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLookupBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        setContentView(bind.getRoot());
    }
}