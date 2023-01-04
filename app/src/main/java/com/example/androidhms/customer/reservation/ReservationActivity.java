package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityCustomerReservationBinding;

public class ReservationActivity extends AppCompatActivity {
    ActivityCustomerReservationBinding bind;
    int idx = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerReservationBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.tvStep.setText("STEP 1");
        bind.tvSelect.setText("진료과 선택");
        bind.imgvBefore.setVisibility(View.INVISIBLE);
        changeFragment(new StepOneFragment());

        bind.imgvBefore.setOnClickListener(v -> {
            idx = idx - 1;
            if (idx == 0) idx = 1;
            if (idx == 1) {
                changeFragment(new StepOneFragment());
                bind.tvStep.setText("STEP 1");
                bind.tvSelect.setText("진료과 선택");
                bind.imgvBefore.setVisibility(View.INVISIBLE);
            } else if (idx == 2) {
                changeFragment(new StepTwoFragment());
                bind.tvStep.setText("STEP 2");
                bind.tvSelect.setText("의료진 선택");
                bind.imgvBefore.setVisibility(View.VISIBLE);
            } else if (idx == 3) {
                changeFragment(new StepThreeFragment());
                bind.tvStep.setText("STEP 3");
                bind.tvSelect.setText("날짜 선택");
                bind.imgvBefore.setVisibility(View.VISIBLE);
            } else if (idx == 4) {
                changeFragment(new StepFourFragment());
                bind.tvStep.setText("STEP 4");
                bind.tvSelect.setText("시간 선택");
                bind.imgvBefore.setVisibility(View.VISIBLE);
            }
        });

        bind.imgvNext.setOnClickListener(v -> {
            idx = idx + 1;
            if (idx == 5) idx = 4;
            if (idx == 1) {
                changeFragment(new StepOneFragment());
                bind.tvStep.setText("STEP 1");
                bind.tvSelect.setText("진료과 선택");
            } else if (idx == 2) {
                changeFragment(new StepTwoFragment());
                bind.tvStep.setText("STEP 2");
                bind.tvSelect.setText("의료진 선택");
                bind.imgvBefore.setVisibility(View.VISIBLE);
            } else if (idx == 3) {
                changeFragment(new StepThreeFragment());
                bind.tvStep.setText("STEP 3");
                bind.tvSelect.setText("날짜 선택");
                bind.imgvBefore.setVisibility(View.VISIBLE);
            } else if (idx == 4) {
                changeFragment(new StepFourFragment());
                bind.tvStep.setText("STEP 4");
                bind.tvSelect.setText("시간 선택");
                bind.imgvBefore.setVisibility(View.VISIBLE);
            }
        });

        bind.tvBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }


}