package com.example.androidhms.reception.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.androidhms.MainActivity;
import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityAppointmentBinding;
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    ActivityAppointmentBinding bind;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityAppointmentBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Intent intent =getIntent();


        //datePicker 연결
        bind.imgvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day= calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year +"년   " + month +"월   " + day + "일";
                        bind.tvShowDate.setText(date);
                          Log.d("로그", "onDateSet: " + "달력");
                        bind.cardvAppointmentList.setVisibility(View.VISIBLE);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //어댑터 연결
        bind.recvAppointmentList.setAdapter(new AppointmentAdapter(getLayoutInflater()));
        //액티비티에서 리사이클러 뷰 붙이기 : context : 지금 액티비티
        bind.recvAppointmentList.setLayoutManager(new LinearLayoutManager(AppointmentActivity.this, RecyclerView.VERTICAL, false));


    }
}