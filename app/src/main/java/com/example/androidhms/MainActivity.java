package com.example.androidhms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.customer.CustomerActivity;
import com.example.androidhms.databinding.ActivityMainBinding;
import com.example.androidhms.reception.ReceptionLoginActivity;
import com.example.androidhms.staff.StaffLoginActivity;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;


public class MainActivity extends AppCompatActivity {
    String TAG = "로그";
    private ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

//        ApiClient.setBASEURL("http://192.168.0.116/middle/");
 //       ApiClient.setBASEURL("http://192.168.0.14/hms/");


        // 고객홈페이지로 이동
        bind.btnCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
            startActivity(intent);
        });

        // 직원홈페이지로 이동
        bind.btnStaff.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StaffLoginActivity.class);
            startActivity(intent);
        });

        // 원무과홈페이지로 이동
        bind.btnReceptionoffice.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReceptionLoginActivity.class);
            startActivity(intent);
        });



//        new RetrofitMethod().setParams("a", "1").setParams("b", "2").sendPost("hmstest", new RetrofitMethod.CallBackResult() {
//            @Override
//            public void result(boolean isResult, String data) {
//                Log.d(TAG, "result: " + isResult);
//                Log.d(TAG, "result: " + data);
//            }
//        });

    }


}

