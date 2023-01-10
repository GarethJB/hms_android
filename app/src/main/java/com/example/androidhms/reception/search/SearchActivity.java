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
import com.example.androidhms.reception.search.record.SearchMedicalRecordAdapter;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.androidhms.reception.vo.MedicalRecordVO;
import com.example.androidhms.staff.vo.PatientVO;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    //binding 오류시 싱크해주기
    ActivitySearchBinding bind;
    MedicalReceiptVO vo1;
    MedicalRecordVO vo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent intent = getIntent();
        //환자정보검색
        bind.btnSearch.setOnClickListener(v -> {
            searchPatientInfo();
            searchAppointment();
            searchMedicalRecord();
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
        //예약상세 보기
        bind.btnDetailAppointment.setOnClickListener(v -> {
            Intent appoint_intent = getIntent();
            startActivity(appoint_intent);
        });
        //진료기록 상세보기
        bind.btnDetailRecord.setOnClickListener(v -> {
            Intent record_intent =getIntent();
            startActivity(record_intent);
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
        new RetrofitMethod().setParams("name", bind.editPatient.getText().toString()).sendPost("patient.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                Log.d("로그", "result: " + data);
                ArrayList<PatientVO> patientList = new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>() {
                }.getType());
                if (patientList == null || patientList.size() == 0) {
                    Toast.makeText(SearchActivity.this, "환자 정보가 없습니다", Toast.LENGTH_SHORT).show();
                } else {
                    bind.tvName.setText(patientList.get(0).getName());
                    bind.tvGender.setText(patientList.get(0).getGender());
                    bind.tvPatentId.setText(patientList.get(0).getPatient_id() + "");
                    bind.tvSocialId.setText(patientList.get(0).getSocial_id());
                    bind.tvPhone.setText(patientList.get(0).getPhone_number());
                    bind.tvBloodType.setText(patientList.get(0).getBlood_type());
                    bind.tvHeight.setText(patientList.get(0).getHeight() + "");
                    bind.tvWeight.setText(patientList.get(0).getWeight() + "");
                }
            }
        });
    }
    //환자 예약현황 조회
    public void searchAppointment(){
        new RetrofitMethod().setParams("name", bind.editPatient.getText().toString() ).sendPost("appointment.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                ArrayList<MedicalReceiptVO> appointList = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>() {
                }.getType());
                if(appointList == null|| appointList.size() == 0){
                    bind.recvAppointment.setVisibility(View.INVISIBLE);
                }else {
                    bind.recvAppointment.setAdapter(new SearchAppointmentAdapter(getLayoutInflater(),vo1,appointList));
                    bind.recvAppointment.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL,false));
                    bind.btnDetailAppointment.setVisibility(View.VISIBLE);
                }
            }
        });
    }

        //진료기록 조회
    public void searchMedicalRecord(){
        new RetrofitMethod().setParams("name", bind.editPatient.getText().toString()).sendPost("medical_record.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                ArrayList<MedicalRecordVO> recordList=  new Gson().fromJson(data, new TypeToken<ArrayList<MedicalRecordVO>>(){}.getType());
                if(recordList == null || recordList.size()== 0){
                    bind.recvMedicalRecord.setVisibility(View.INVISIBLE);
                }else {
                    bind.recvMedicalRecord.setAdapter(new SearchMedicalRecordAdapter(getLayoutInflater(),vo2,recordList));
                    bind.recvMedicalRecord.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL,false));
                    bind.btnDetailRecord.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}