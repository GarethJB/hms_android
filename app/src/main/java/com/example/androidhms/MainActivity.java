package com.example.androidhms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.conn.ApiClient;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiClient.setBASEURL("http://192.168.0.116/??/");



    }
}