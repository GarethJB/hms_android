package com.example.androidhms.reception.search.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityDetailRecordBinding;
import com.example.androidhms.reception.appointment.AppointmentActivity;
import com.example.androidhms.reception.appointment.AppointmentAdapter;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.androidhms.reception.vo.MedicalRecordVO;
import com.example.conn.RetrofitMethod;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;

public class DetailRecordActivity extends AppCompatActivity {

    ActivityDetailRecordBinding bind;
    DatePickerDialog datePickerDialog;
    ArrayList<MedicalReceiptVO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailRecordBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent record_intent = getIntent();


        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new MonthFragment());

        bind.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new MonthFragment());
                }else if(position == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new YearFragment());
                }else if(position == 2){
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        bind.calendar.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(c.YEAR);
            int month = c.get(c.MONTH);
            int day = c.get(c.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(DetailRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = year + "." + month + "." + day ;
                    //java 숫자 왼쪽에 0으로 채우기
                    //스프링에서 데이터를 보내기
                    new RetrofitMethod().setParams("time", year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)).sendPost("appointmentlist.re", (isResult, data) -> {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                         list = gson.fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>() {
                        }.getType());



                    });
                }
            }, year, month, day);
            datePickerDialog.show();
        });
    }



}