package com.example.androidhms.customer.info.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.customer.vo.AdmissionRecordVO;
import com.example.androidhms.customer.vo.MedicalReceiptVO;
import com.example.androidhms.databinding.ActivityCustomerReservationRecordBinding;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ReservationScheduleActivity extends AppCompatActivity {
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
        new RetrofitMethod().setParams("patient_id", patient_id).sendPost("admission_schedule.cu", (isResult, data) -> {
            admission = new Gson().fromJson(data, AdmissionRecordVO.class);
            Log.d("로그", "입원일정: " + admission.getName());
            try {
                bind.tvDepartment.setText(admission.getDepartment_name());
                bind.tvName.setText(admission.getName());
                bind.tvAdmissionDate.setText(admission.getAdmission_date());
                bind.tvDischargeDate.setText(admission.getDischarge_date());
                bind.tvWardNumber.setText(admission.getWard_number() + "호");
                bind.tvBed.setText(admission.getBed() + "번 침대");

            }catch (Exception e) {
                Log.d("로그", "onCreate: " + e);
            }
        });


        //예약일정 조회
        new RetrofitMethod().setParams("patient_id", patient_id).sendPost("receipt_record.cu", (isResult, data) -> {
            receipt = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
            MedicalReservationAdapter adapter_medical = new MedicalReservationAdapter(getLayoutInflater(), ReservationScheduleActivity.this, receipt);

           try {
                bind.rcvMedicalReservation.setAdapter(adapter_medical);
                bind.rcvMedicalReservation.setLayoutManager(new LinearLayoutManager(ReservationScheduleActivity.this, RecyclerView.VERTICAL, false));
           }catch (Exception e) {

           }
        });










    }
}