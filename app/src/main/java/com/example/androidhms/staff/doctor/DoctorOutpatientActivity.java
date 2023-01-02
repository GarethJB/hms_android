package com.example.androidhms.staff.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.databinding.ActivityDoctorOutpatientBinding;

public class DoctorOutpatientActivity extends AppCompatActivity {

    private ActivityDoctorOutpatientBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDoctorOutpatientBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

    }
}