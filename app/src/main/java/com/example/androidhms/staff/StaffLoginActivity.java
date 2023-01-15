package com.example.androidhms.staff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.databinding.ActivityStaffLoginBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;

public class StaffLoginActivity extends AppCompatActivity {

    private ActivityStaffLoginBinding bind;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        preferences = getSharedPreferences("staffLoginInfo", MODE_PRIVATE);
        editor = preferences.edit();

        bind.etId.setText(preferences.getString("id", ""));
        bind.etPw.setText(preferences.getString("pw", ""));
        if (preferences.getString("autoLogin", "N").equals("Y")) {
            bind.cbAutologin.setChecked(true);
        }

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        bind.btLogin.setOnClickListener(v -> new RetrofitMethod()
                .setParams("id", bind.etId.getText().toString())
                .setParams("pw", bind.etPw.getText().toString())
                .sendPost("staffLogin.ap", (isResult, data) -> {
                    if (data != null && data.equals("null")) {
                        Toast.makeText(StaffLoginActivity.this,
                                "사번 또는 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (bind.cbAutologin.isChecked()) {
                            editor.putString("id", bind.etId.getText().toString());
                            editor.putString("pw", bind.etPw.getText().toString());
                            editor.putString("autoLogin", "Y");
                            editor.putString("staffData", data);
                        } else {
                            editor.putString("id", "");
                            editor.putString("pw", "");
                            editor.putString("autoLogin", "N");
                            editor.putString("staffData", "");
                        }
                        editor.commit();
                        Util.getStaff(StaffLoginActivity.this);
                        new HmsFirebase(StaffLoginActivity.this).sendToken();
                        Intent intent = new Intent(StaffLoginActivity.this, StaffActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }));
    }

}