package com.example.androidhms.reception;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityReceptionBinding;
import com.example.androidhms.reception.appointment.AppointmentActivity;
import com.example.androidhms.reception.home.NewsActivity;
import com.example.androidhms.reception.search.SearchActivity;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.conn.ApiClient;

public class ReceptionActivity extends AppCompatActivity implements View.OnClickListener {

        ActivityReceptionBinding bind;
        StaffVO staff_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityReceptionBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        //받아온 사원이름 받아오기
        Intent intent =getIntent();
       staff_name =(StaffVO)intent.getSerializableExtra("staff_name");
        Log.d("로그", "onCreate: " + staff_name.getName());
        bind.tvName.setText(staff_name.getName());
        //로그인하면 상단바에 로그아웃뜨기
        bind.toolbar.tvLogin.setVisibility(View.VISIBLE);
        
        bind.clAppointmentSearch.setOnClickListener(this);
        bind.clPatientSearch.setOnClickListener(this);
        bind.clNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cl_appointment_search){
            changeActivity(new AppointmentActivity());
        }else if(v.getId() == R.id.cl_patient_search){
            changeActivity(new SearchActivity());
        }else if(v.getId() == R.id.cl_news){
            changeActivity(new NewsActivity());
        }
    }

    //intent 메소드
    public void changeActivity(Activity activity){
        Intent intent = new Intent(ReceptionActivity.this,activity.getClass());
        startActivity(intent);
    }
    //프래그먼트 연결
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}