package com.example.androidhms.staff.outpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffPrescriptionBinding;
import com.example.androidhms.staff.vo.PrescriptionVO;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;

import java.sql.Timestamp;

public class PrescriptionActivity extends AppCompatActivity {

    private ActivityStaffPrescriptionBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffPrescriptionBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.imgvBefore.setOnClickListener(v -> finish());
        int mrId = getIntent().getIntExtra("medical_record_id", 0);
        if (mrId != 0) {
            new RetrofitMethod().setParams("id", mrId)
                    .sendPost("getprescription.ap", new RetrofitMethod.CallBackResult() {
                        @Override
                        public void result(boolean isResult, String data) {
                            if (isResult && !data.equals("null")) {
                                PrescriptionVO vo = new Gson().fromJson(data, PrescriptionVO.class);
                                String date = Util.dateFormat(Timestamp.valueOf(vo.getTreatment_date()), "yyyy년 MM월 dd일 ")
                                        + vo.getPrescription_record_id() + "호";
                                bind.tvPrescriptionDate.setText(date);
                                bind.tvPatientName.setText(vo.getPatient_name());
                                bind.tvPatientSocialId.setText(vo.getSocial_id() + "-*******");
                                bind.tvStaffName.setText(vo.getStaff_name());
                                bind.tvStaffId.setText(String.valueOf(vo.getStaff_id()));
                                String[] pArr = vo.getPrescription_name().split(", ");
                                if (pArr.length == 3) {
                                    bind.tvPrescription3.setText(pArr[2]);
                                    bind.tvPrescription2.setText(pArr[1]);
                                    bind.tvPrescription1.setText(pArr[0]);
                                } else if (pArr.length == 2) {
                                    bind.tvPrescription2.setText(pArr[1]);
                                    bind.tvPrescription1.setText(pArr[0]);
                                    bind.tvPrescription3.setVisibility(View.GONE);
                                    bind.llPrescription3.setVisibility(View.GONE);
                                } else if (pArr.length == 1) {
                                    bind.tvPrescription1.setText(pArr[0]);
                                    bind.tvPrescription3.setVisibility(View.GONE);
                                    bind.llPrescription3.setVisibility(View.GONE);
                                    bind.tvPrescription2.setVisibility(View.GONE);
                                    bind.llPrescription2.setVisibility(View.GONE);
                                } else {
                                    bind.tvPrescription3.setVisibility(View.GONE);
                                    bind.llPrescription3.setVisibility(View.GONE);
                                    bind.tvPrescription2.setVisibility(View.GONE);
                                    bind.llPrescription2.setVisibility(View.GONE);
                                    bind.tvPrescription1.setVisibility(View.GONE);
                                    bind.llPrescription1.setVisibility(View.GONE);
                                }
                                bind.rlProgress.view.setVisibility(View.GONE);
                                bind.zlPrescription.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(PrescriptionActivity.this, "처방전을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
        }

    }


}