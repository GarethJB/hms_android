package com.example.androidhms.customer.info;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.customer.vo.MedicalRecordVO;
import com.example.androidhms.databinding.ActivityCustomerDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityCustomerDetailBinding bind;
    private MedicalRecordVO medicalRecord;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerDetailBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        intent = getIntent();
        
        bind.toolbar.tvPage.setText("진료기록 상세");
        
        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        medicalRecord = (MedicalRecordVO) intent.getSerializableExtra("vo");

        bind.tvIdNumber.setText(medicalRecord.getMedical_record_id()+"");
        bind.tvPatientName.setText(medicalRecord.getPatient_name());
        bind.tvTreatmentDate.setText(medicalRecord.getTreatment_date()+"");
        bind.tvDepartmentName.setText(medicalRecord.getDepartment_name());
        bind.tvStaffName.setText(medicalRecord.getStaff_name());
        bind.tvTreatmentName.setText(medicalRecord.getTreatment_name());
        bind.tvAdmission.setText(medicalRecord.getAdmission());
        bind.tvPrescriptionName.setText(medicalRecord.getPrescription_name());

    }
}