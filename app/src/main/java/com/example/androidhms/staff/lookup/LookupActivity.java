package com.example.androidhms.staff.lookup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.databinding.ActivityStaffLookupBinding;
import com.example.androidhms.staff.vo.PatientVO;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LookupActivity extends AppCompatActivity {

    private ActivityStaffLookupBinding bind;
    private PatientVO vo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffLookupBinding.inflate(getLayoutInflater());
      /*  bind.toolbar.ivLeft.setOnClickListener((v) -> finish());*/
        setContentView(bind.getRoot());

        // 환자 검색
        bind.btnSearch.setOnClickListener(onSearchClick());
        // 키보드의 검색 버튼 눌렀을때 앱의 검색 버튼 클릭
        bind.etName.setOnEditorActionListener((v, actionId, event) -> {
            bind.btnSearch.performClick();
            return false;
        });
        // 전화걸기 (다이얼까지만)
        bind.tvPhone.setOnClickListener(onPhoneClick());
        // 메모저장
        bind.btnMemosave.setOnClickListener(onMemoSaveClick());
    }

    private View.OnClickListener onPhoneClick() {
        return v -> {
            String tel = bind.tvPhone.getText().toString();
            if (!tel.equals("")) {
                tel = "tel:" + tel;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(tel)));
            }
        };
    }

    private View.OnClickListener onSearchClick() {
        return v -> {
            Util.keyboardDown(LookupActivity.this);
            new RetrofitMethod().setParams("name", bind.etName.getText().toString())
                    .sendPost("searchpatient.ap", (isResult, data) -> {
                        if (isResult) {
                            ArrayList<PatientVO> patientList =
                                    new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>(){}.getType());
                            if (patientList.isEmpty()) {
                                Toast.makeText(LookupActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                if (patientList.size() == 1) {
                                    vo = patientList.get(0);
                                    bindPatientInfo(vo);
                                }
                            }
                        }
                    });
        };
    }

    private View.OnClickListener onMemoSaveClick() {
        return v -> {
            Util.keyboardDown(LookupActivity.this);
            new RetrofitMethod().setParams("id", vo.getPatient_id())
                    .setParams("memo", bind.etMemo.getText().toString())
                    .sendPost("updatepatientmemo.ap", (isResult, data) -> {
                        if (isResult && data.equals("1")) {
                            Toast.makeText(LookupActivity.this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(LookupActivity.this, "메모저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    });
        };
    }

    private void bindPatientInfo(PatientVO vo) {
        bind.tvName.setText(vo.getName());
        // 밑줄
        SpannableString content = new SpannableString(vo.getPhone_number());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        bind.tvPhone.setText(content);
        bind.tvBirthday.setText(Util.getBirthDay(vo.getSocial_id()));
        bind.tvAge.setText(Util.getAge(vo.getSocial_id()) + "세");
        bind.tvHeight.setText(vo.getHeight() + "cm");
        bind.tvWeight.setText(vo.getWeight() + "kg");
        bind.tvBloodtype.setText(vo.getBlood_type());
        String gender = vo.getGender().equals("M") ? "남" : "여";
        bind.tvGender.setText(gender);
        bind.tvAllergy.setText(vo.getAllergy());
        bind.tvUnderlying.setText(vo.getUnderlying_disease());
        bind.etMemo.setText(vo.getMemo());
    }


}