package com.example.androidhms.reception;
//datepicker


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityReceptionLoginBinding;
import com.example.conn.ApiClient;

public class ReceptionLoginActivity extends AppCompatActivity {
    ActivityReceptionLoginBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityReceptionLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        ApiClient.setBASEURL("http://192.168.0.14/hms"); //안드로이드 시작 점에 실시

        //로그인처리
        bind.btnLogin.setOnClickListener(v -> {

        });

        bind.toolbar.ivLeft.setOnClickListener(v -> {
           // onBackPressed();
            Intent intent = new Intent(ReceptionLoginActivity.this,ReceptionActivity.class);
            startActivity(intent);
        });
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}