package com.example.androidhms.reception.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.androidhms.databinding.ActivityAppointmentBinding;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    ActivityAppointmentBinding bind;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityAppointmentBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent intent = getIntent();

        //datePicker 연결
        bind.imgvCalendar.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(c.YEAR);
            int month = c.get(c.MONTH);
            int day = c.get(c.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    Log.d("로그", "onDateSet: " + "달력");
                    month = month + 1;
                    String date = year + "  년  " + month + " 월  " + day + "일";
                    bind.tvToday.setText(date);
                    //java 숫자 왼쪽에 0으로 채우기
                    //스프링에서 데이터를 보내기
                    new RetrofitMethod().setParams("time", year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)).sendPost("appointment.re", (isResult, data) -> {
                        Log.d("로그", "onDateSet: " + data);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                        ArrayList<MedicalReceiptVO> list = gson.fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>() {
                        }.getType());
                        if(list == null || list.size() == 0 ){
                            bind.cardvAppointmentList.setVisibility(View.INVISIBLE);
                        }else{
                        bind.cardvAppointmentList.setVisibility(View.VISIBLE);
                        bind.recvAppointmentList.setAdapter(new AppointmentAdapter(getLayoutInflater(),list, AppointmentActivity.this));
                        bind.recvAppointmentList.setLayoutManager(new LinearLayoutManager(AppointmentActivity.this, RecyclerView.VERTICAL, false));
                        }
                    });
                }
            }, year, month, day);
            datePickerDialog.show();
        });
       bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });
        bind.toolbar.llLogo.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}