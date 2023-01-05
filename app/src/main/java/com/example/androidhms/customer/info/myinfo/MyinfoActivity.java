package com.example.androidhms.customer.info.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.vo.AccountVO;
import com.example.androidhms.databinding.ActivityCustomerMyinfoBinding;
import com.example.androidhms.staff.vo.PatientVO;

public class MyinfoActivity extends AppCompatActivity {
    private ActivityCustomerMyinfoBinding bind;
    private PatientVO patient;
    private AccountVO account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerMyinfoBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        // vo 받아오는 처리
        Intent intent = getIntent();
        patient = (PatientVO) intent.getSerializableExtra("patient");
        account = (AccountVO) intent.getSerializableExtra("account");


        bind.btnBack.setVisibility(View.GONE);
        bind.btnOk.setVisibility(View.GONE);

        changeFragment(new SelectFragment(patient, account));

        bind.btnUpdate.setOnClickListener(v -> {
            changeFragment(new UpdateFragment(patient, account));
            bind.btnUpdate.setVisibility(View.GONE);
            bind.btnBack.setVisibility(View.VISIBLE);
            bind.btnOk.setVisibility(View.VISIBLE);
        });

        bind.btnBack.setOnClickListener(v -> {
            changeFragment(new SelectFragment(patient, account));
            bind.btnUpdate.setVisibility(View.VISIBLE);
            bind.btnBack.setVisibility(View.GONE);
            bind.btnOk.setVisibility(View.GONE);
        });
    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}