package com.example.androidhms.staff.nurse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidhms.databinding.ActivityNurseOutpatientBinding;

public class NurseOutpatientActivity extends AppCompatActivity {

    private ActivityNurseOutpatientBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityNurseOutpatientBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

    }

}