package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.databinding.ActivityStaffLoginBinding;

public class StaffLoginActivity extends AppCompatActivity {

    private ActivityStaffLoginBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bind.etId.getText().toString().equals("2")) {
                    startActivity(new Intent(StaffLoginActivity.this, StaffActivity.class));
                }
            }
        });
    }

}