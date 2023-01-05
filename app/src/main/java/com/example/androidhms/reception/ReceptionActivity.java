package com.example.androidhms.reception;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityReceptionBinding;
import com.example.androidhms.reception.appointment.AppointmentActivity;
import com.example.androidhms.reception.home.NewsActivity;
import com.example.androidhms.reception.search.SearchActivity;
import com.example.conn.ApiClient;

public class ReceptionActivity extends AppCompatActivity implements View.OnClickListener {

        ActivityReceptionBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityReceptionBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ApiClient.setBASEURL("http://192.168.0.14/hms/"); //처음에 지정하기


        Intent intent =getIntent();

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