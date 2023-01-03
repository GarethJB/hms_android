package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgv_before, imgv_next;
    TextView tv_step, tv_select;
    Button btn_back;
    int idx = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        imgv_before = findViewById(R.id.imgv_before);
        imgv_next = findViewById(R.id.imgv_next);
        tv_step = findViewById(R.id.tv_step);
        tv_select = findViewById(R.id.tv_select);
        btn_back = findViewById(R.id.btn_back);

        tv_step.setText("STEP 1");
        tv_select.setText("진료과 선택");
        changeFragment(new StepOneFragment());

        imgv_before.setOnClickListener(this);
        imgv_next.setOnClickListener(this);
        btn_back.setOnClickListener(this);




    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgv_before) {
            idx = idx - 1;
            if (idx == 0) idx = 1;
            if (idx == 1) {
                changeFragment(new StepOneFragment());
                tv_step.setText("STEP 1");
                tv_select.setText("진료과 선택");
            } else if (idx == 2) {
                changeFragment(new StepTwoFragment());
                tv_step.setText("STEP 2");
                tv_select.setText("의료진 선택");
            } else if (idx == 3) {
                changeFragment(new StepThreeFragment());
                tv_step.setText("STEP 3");
                tv_select.setText("날짜 선택");
            } else if (idx == 4) {
                changeFragment(new StepFourFragment());
                tv_step.setText("STEP 4");
                tv_select.setText("시간 선택");
            }
        } else if (v.getId() == R.id.imgv_next) {
            idx = idx + 1;
            if (idx == 5) idx = 4;
            if (idx == 1) {
                changeFragment(new StepOneFragment());
                tv_step.setText("STEP 1");
                tv_select.setText("진료과 선택");
            } else if (idx == 2) {
                changeFragment(new StepTwoFragment());
                tv_step.setText("STEP 2");
                tv_select.setText("의료진 선택");
            } else if (idx == 3) {
                changeFragment(new StepThreeFragment());
                tv_step.setText("STEP 3");
                tv_select.setText("날짜 선택");
            } else if (idx == 4) {
                changeFragment(new StepFourFragment());
                tv_step.setText("STEP 4");
                tv_select.setText("시간 선택");
            }
        } else if (v.getId() == R.id.btn_back) {
                onBackPressed();
        }

    }
}