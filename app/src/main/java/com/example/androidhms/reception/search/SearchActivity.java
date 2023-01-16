package com.example.androidhms.reception.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivitySearchBinding;
import com.example.androidhms.reception.search.appointment.SearchAppointmentAdapter;
import com.example.androidhms.reception.search.prescription.PrescriptionAdapter;
import com.example.androidhms.reception.search.record.detailrecord.DetailRecordActivity;
import com.example.androidhms.reception.search.ward.WardAdapter;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.androidhms.reception.vo.MedicalRecordVO;
import com.example.androidhms.reception.vo.PrescriptionVO;
import com.example.androidhms.reception.vo.WardVO;
import com.example.androidhms.staff.vo.PatientVO;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    //binding 오류시 싱크해주기
    ActivitySearchBinding bind;
    PatientVO vo;
    MedicalReceiptVO vo1;
    MedicalRecordVO vo2;
    PrescriptionVO vo3;
    WardVO vo4;
    String patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent intent = getIntent();
        //환자정보검색
        bind.btnSearch.setOnClickListener(v -> {
            new RetrofitMethod().setParams("name",bind.editPatient.getText().toString()).sendPost("patient.re", new RetrofitMethod.CallBackResult() {
                @Override
                public void result(boolean isResult, String data) {
                    ArrayList<PatientVO> patientList = new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>() {
                    }.getType());
                    if(patientList == null || patientList.size()== 0){
                        Toast.makeText(SearchActivity.this, "환자 정보가 없습니다", Toast.LENGTH_SHORT).show();

                    }else if(patientList.size() == 1){
                        Log.d("로그", "result: " + "클릭이벤트");
                        patient_id =patientList.get(0).getPatient_id()+"";
                        Log.d("로그", "result: " +patient_id);
                        searchPatientInfo();
                        searchAppointment();
                        searchMedicalRecord();
                        searchWard();

                    } else{
                        Log.d("로그", "result: " + "동명이인");
                        bind.llNameList.setVisibility(View.VISIBLE);
                        bind.recvNameList.setAdapter(new PatientNameAdapter(getLayoutInflater(),vo,patientList , SearchActivity.this));
                        bind.recvNameList.setLayoutManager(new LinearLayoutManager(SearchActivity.this,RecyclerView.VERTICAL,false));
                    }
                }
            });
        });
       /* //새로고침
        bind.refresh.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0,0);
            Intent refresh_intent= getIntent();
            startActivity(refresh_intent);
            overridePendingTransition(0,0);
        });*/
        //qr스캐너
        bind.qrScanner.setOnClickListener(v -> {
            Intent scan_intent = new Intent(SearchActivity.this, QrSacnnerActivity.class);
            startActivity(scan_intent);
        });

        //진료기록 상세보기
        bind.detailRecord.setOnClickListener(v -> {
            Intent record_intent = new Intent(SearchActivity.this, DetailRecordActivity.class);
            record_intent.putExtra("id",patient_id);
            startActivity(record_intent);

        });
        //처방기록 상세보기
        bind.detailPres.setOnClickListener(v -> {

        });
        bind.toolbar.ivLeft.setOnClickListener(this);
        bind.toolbar.llLogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_search) {
        } else if (v.getId() == R.id.iv_left) {
            onBackPressed();
        } else if (v.getId() == R.id.ll_logo) {
            onBackPressed();
        }
    }
    //환자 인적사항 조회
    public void searchPatientInfo() {
        new RetrofitMethod().setParams("id",patient_id).sendPost("id.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                Log.d("로그", "result: " + patient_id + "받은 값");
                ArrayList<PatientVO> patientList = new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>() {
                }.getType());
                    bind.tvPatentId.setText(patientList.get(0).getPatient_id()+"");
                    bind.tvName.setText(patientList.get(0).getName());
                    bind.tvGender.setText(patientList.get(0).getGender());
                    bind.tvSocialId.setText(patientList.get(0).getSocial_id());
                    bind.tvPhone.setText(patientList.get(0).getPhone_number());
                  // bind.tvAdmission.setText(patientList.get(0).);
            }
        });
    }
    //예약정보 조회
   public void searchAppointment(){
        new RetrofitMethod().setParams("id",patient_id ).sendPost("appointment.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                ArrayList<MedicalReceiptVO> appointList = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>() {
                }.getType());
                    bind.recvAppointment.setAdapter(new SearchAppointmentAdapter(getLayoutInflater(),vo1,appointList));
                    bind.recvAppointment.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL,false));
            }
        });
    }
    //진료기록 조회
    public void searchMedicalRecord(){
        new RetrofitMethod().setParams("id", patient_id).sendPost("medical_record_id.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                ArrayList<MedicalRecordVO> recordList=  new Gson().fromJson(data, new TypeToken<ArrayList<MedicalRecordVO>>(){}.getType());
                    bind.recvMedicalRecord.setAdapter(new SearchMedicalRecordAdapter(getLayoutInflater(),vo2,recordList));
                    bind.recvMedicalRecord.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL,false));
                    bind.detailRecord.setVisibility(View.VISIBLE);
            }
        });
    }
    //처방기록 조회
    public void searchPrescription(){
        new RetrofitMethod().setParams("id", patient_id).sendPost("prescription.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                Log.d("로그", "result: " + data);
                ArrayList<PrescriptionVO> presList =  new Gson().fromJson(data, new TypeToken<ArrayList<PrescriptionVO>>(){}.getType());
                bind.recvPrescription.setAdapter(new PrescriptionAdapter(getLayoutInflater(),vo3,presList));
                bind.recvPrescription.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL,false));
                bind.detailPres.setVisibility(View.VISIBLE);
            }
        });
    }
    //입원기록 조회
    public void searchWard() {
        new RetrofitMethod().setParams("id", patient_id).sendPost("ward.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                Log.d("로그", "result: " + data);
                ArrayList<WardVO> wardList = new Gson().fromJson(data, new TypeToken<ArrayList<WardVO>>(){
                }.getType());
                bind.recvWard.setAdapter(new WardAdapter(getLayoutInflater(), vo4, wardList));
                bind.recvWard.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false));
                bind.detailWard.setVisibility(View.VISIBLE);
            }
        });
    }
}