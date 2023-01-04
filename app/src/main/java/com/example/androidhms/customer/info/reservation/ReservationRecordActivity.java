package com.example.androidhms.customer.info.reservation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.ActivityCustomerReservationRecordBinding;

public class ReservationRecordActivity extends AppCompatActivity {
    private ActivityCustomerReservationRecordBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerReservationRecordBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        MedicalReservationAdapter adapter_medical = new MedicalReservationAdapter(getLayoutInflater(), ReservationRecordActivity.this);

        bind.rcvMedicalReservation.setAdapter(adapter_medical);
        bind.rcvMedicalReservation.setLayoutManager(new LinearLayoutManager(ReservationRecordActivity.this, RecyclerView.VERTICAL, false));

    }
}