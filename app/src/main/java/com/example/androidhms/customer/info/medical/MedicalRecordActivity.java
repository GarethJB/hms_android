package com.example.androidhms.customer.info.medical;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.ActivityCustomerMedicalBinding;

public class MedicalRecordActivity extends AppCompatActivity {
    ActivityCustomerMedicalBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerMedicalBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        MedicalRecordAdapter medicalRecordAdapter = new MedicalRecordAdapter(getLayoutInflater(), MedicalRecordActivity.this);
        bind.rcvMedicalRecord.setAdapter(medicalRecordAdapter);
        bind.rcvMedicalRecord.setLayoutManager(new LinearLayoutManager(MedicalRecordActivity.this, RecyclerView.VERTICAL, false));

        AdmissionRecordAdapter admissionRecordAdapter = new AdmissionRecordAdapter(getLayoutInflater(), MedicalRecordActivity.this);
        bind.rcvAdmissionRecord.setAdapter(admissionRecordAdapter);
        bind.rcvAdmissionRecord.setLayoutManager(new LinearLayoutManager(MedicalRecordActivity.this, RecyclerView.VERTICAL, false));

    }
}