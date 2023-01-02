package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {

    private ActivityScheduleBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}