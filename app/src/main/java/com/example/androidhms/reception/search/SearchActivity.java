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
import com.example.androidhms.staff.vo.PatientVO;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySearchBinding bind;
    PatientVO vo;
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
            new RetrofitMethod().setParams("name", bind.editPatient.getText().toString()).sendPost("patient.re", new RetrofitMethod.CallBackResult() {

                @Override
                public void result(boolean isResult, String data) {
                   ArrayList<PatientVO> plist=  new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>(){}.getType());
                   if(plist ==null || plist.size()==0){
                       Toast.makeText(SearchActivity.this, "환자 정보가 없습니다", Toast.LENGTH_SHORT).show();
                   }else{
                       Log.d("로그", "result: " + plist.get(0).getName());
                       bind.patientId.setText(plist.get(0).getPatient_id());
                       bind.name.setText(plist.get(0).getName());
                       bind.patientSocialId.setText(plist.get(0).getSocial_id());
                       bind.patientPhone.setText(plist.get(0).getPhone_number());
                       bind.doctorName.setText(plist.get(0).getName());
                   }
                }
            });
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
}