package com.example.androidhms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.databinding.ActivityMainBinding;
import com.example.androidhms.reception.ReceptionActivity;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "로그";
    TextView logo;
    ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());


    logo= findViewById(R.id.logo);

    logo.setOnClickListener(this);

        ApiClient.setBASEURL("http://192.168.0.116/middle/");

        new RetrofitMethod().setParams("a", "1").setParams("b", "2").sendPost("hmstest", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                Log.d(TAG, "result: " + isResult);
                Log.d(TAG, "result: " + data);
            }
        });



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.logo){
            Intent intent = new Intent(MainActivity.this, ReceptionActivity.class);
            startActivity(intent);
        }
    }
}


//텍스트 사이즈
//네비게이션 20dp
//타이틀 24dp
//하위항목 20dp
//내용 16dp

//컬러
//main_color
//second_color
//white
//text_color