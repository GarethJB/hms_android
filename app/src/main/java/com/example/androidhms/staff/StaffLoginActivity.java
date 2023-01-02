package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidhms.databinding.ActivityStaffLoginBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.conn.ApiClient;
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
        preferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        editor = preferences.edit();
        ApiClient.setBASEURL("http://192.168.0.36/hms/");
        //ApiClient.setBASEURL("http://192.168.0.25/hms/")

        bind.etId.setText(preferences.getString("id", ""));
        bind.etPw.setText(preferences.getString("pw", ""));
        if (preferences.getString("autoLogin", "N").equals("Y")) {
            bind.cbAutologin.setChecked(true);
        }

        bind.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrofitMethod().setParams("id", bind.etId.getText().toString())
                        .setParams("pw", bind.etPw.getText().toString())
                        .sendPost("stafflogin.ap", new RetrofitMethod.CallBackResult() {
                    @Override
                    public void result(boolean isResult, String data) {
                        if (data.equals("null")) {
                            Toast.makeText(StaffLoginActivity.this,
                                    "사번 또는 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (bind.cbAutologin.isChecked()) {
                                editor.putString("id", bind.etId.getText().toString());
                                editor.putString("pw", bind.etPw.getText().toString());
                                editor.putString("autoLogin", "Y");
                            } else {
                                editor.putString("id", "");
                                editor.putString("pw", "");
                                editor.putString("autoLogin", "N");
                            }
                            editor.commit();
                            Intent intent = new Intent(StaffLoginActivity.this, StaffActivity.class);
                            intent.putExtra("staff", new Gson().fromJson(data, StaffVO.class));
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

}