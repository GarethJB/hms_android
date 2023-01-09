package com.example.androidhms.reception.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivitySearchBinding;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.androidhms.reception.vo.MedicalRecordVO;
import com.example.androidhms.staff.vo.PatientVO;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySearchBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySearchBinding.inflate(getLayoutInflater());
         setContentView(bind.getRoot());
        Intent intent = getIntent();
        bind.btnSearch.setOnClickListener(this);
       /* bind.toolbar.ivLeft.setOnClickListener(this);
        bind.toolbar.llLogo.setOnClickListener(this);*/

        bind.btnSearch.setOnClickListener(v -> {
            searchPatientInfo();
            searchAppointment();
            searchMedicalRecord();
        });
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_search){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new SearchFragment()).commit();
        }else if(v.getId() == R.id.iv_left){
            onBackPressed();
        }else if(v.getId() == R.id.ll_logo){
            onBackPressed();
        }
    }
    //환자 인적사항 조회
    public void searchPatientInfo(){
            new RetrofitMethod().setParams("name", bind.editPatient.getText().toString()).sendPost("patient.re", new RetrofitMethod.CallBackResult() {
                @Override
                public void result(boolean isResult, String data) {
                    ArrayList<PatientVO> patientList=  new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>(){}.getType());
                    if(patientList ==null || patientList.size()==0){
                        Toast.makeText(SearchActivity.this, "환자 정보가 없습니다", Toast.LENGTH_SHORT).show();
                    }else{
                        /*patient_id는 string 값으로 바꿔줘야 한다*/
                        bind.patientId.setText(patientList.get(0).getPatient_id()+"");
                        bind.name.setText(patientList.get(0).getName());
                        bind.patientSocialId.setText(patientList.get(0).getSocial_id());
                        bind.patientPhone.setText(patientList.get(0).getPhone_number());
                        bind.patientGender.setText(patientList.get(0).getGender());
                    }
                }
            });
    }
    //예약내역 조회
    public void searchAppointment(){
        new RetrofitMethod().setParams("name",bind.editPatient.getText().toString()).sendPost("appointment.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                ArrayList<MedicalReceiptVO> appointlist=  new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
                Log.d("로그", "result: " + data);
              if(appointlist == null || appointlist.size()== 0){
                    searchAppointment();
              }else{
                  bind.tvReserveDate.setText(appointlist.get(0).getReserve_date());
                  bind.tvReserveTime.setText(appointlist.get(0).getReserve_time());
                  bind.tvReserveDepartment.setText(appointlist.get(0).getDepartment_name());
                  bind.tvReserveDoctor.setText(appointlist.get(0).getDoctor_name());
                  bind.tvReserveSymptom.setText(appointlist.get(0).getMemo());
              }
            }
        });
    }
    //진료기록 조회
    public void searchMedicalRecord(){
        new RetrofitMethod().setParams("name", bind.editPatient.getText().toString()).sendPost("medical_record.re", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                Log.d("로그", "result: " + data);
                ArrayList<MedicalRecordVO> recordList=  new Gson().fromJson(data, new TypeToken<ArrayList<MedicalRecordVO>>(){}.getType());
                bind.tvRecordDate.setText(recordList.get(0).getRecord_date());
                bind.tvDoctorName.setText(recordList.get(0).getDoctor_name());
                bind.tvTreatmentName.setText(recordList.get(0).getTreatment_name());

            }
        });
    }
}