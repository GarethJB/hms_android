package com.example.androidhms.customer.info.reservation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.customer.vo.AdmissionRecordVO;
import com.example.androidhms.customer.vo.MedicalReceiptVO;
import com.example.androidhms.databinding.ActivityCustomerReservationRecordBinding;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    private ActivityCustomerReservationRecordBinding bind;
    private ArrayList<MedicalReceiptVO> receipt = new ArrayList<>();
    private AdmissionRecordVO admission;
    private int patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerReservationRecordBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ApiClient.setBASEURL("http://192.168.0.116/hms/");

        Intent intent = getIntent();

        patient_id = intent.getIntExtra("patient_id", 0);

        //입원일정 조회
        new RetrofitMethod().setParams("patient_id", patient_id).sendPost("admissions_schedule.cu", (isResult, data) -> {
            admission = new Gson().fromJson(data, AdmissionRecordVO.class);
            bind.tvName.setText(admission.getName());
        });


        //예약일정 조회
        new RetrofitMethod().setParams("patient_id", patient_id).sendPost("receipt_record.cu", (isResult, data) -> {
            receipt = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
            MedicalReservationAdapter adapter_medical = new MedicalReservationAdapter(getLayoutInflater(), ScheduleActivity.this, receipt);

//            if (receipt.size() > 0) {
//                bind.rcvMedicalReservation.setAdapter(adapter_medical);
//                bind.rcvMedicalReservation.setLayoutManager(new LinearLayoutManager(ScheduleActivity.this, RecyclerView.VERTICAL, false));
//            }else {
//
//            }

        });










    }
}