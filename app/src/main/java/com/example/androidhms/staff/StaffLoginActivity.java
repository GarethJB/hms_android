package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffLoginBinding;

public class StaffLoginActivity extends AppCompatActivity {

    private ActivityStaffLoginBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}