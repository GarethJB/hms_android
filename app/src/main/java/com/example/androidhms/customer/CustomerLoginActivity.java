package com.example.androidhms.customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.customer.vo.AccountVO;
import com.example.androidhms.databinding.ActivityCustomerLoginBinding;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;

public class CustomerLoginActivity extends AppCompatActivity {

    private ActivityCustomerLoginBinding bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ApiClient.setBASEURL("http://192.168.0.116/hms/");


        // 일반 로그인
        bind.btnLogin.setOnClickListener(v -> {
            new RetrofitMethod().setParams("email", bind.etEmail.getText().toString())
                    .setParams("pw", bind.etPw.getText().toString())
                    .sendPost("customerlogin.ap", (isResult, data) -> {

                        Log.d("로그인", "결과 : " + isResult);
                        Log.d("로그인", "정보 : " + data);

                        if (data.equals("null")) {
                            Toast.makeText(CustomerLoginActivity.this,
                                    "사번 또는 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            // AccountVO 에 DB정보 주입
                            Intent intent = new Intent();
                            AccountVO account = new Gson().fromJson(data, AccountVO.class);
                            LoginInfo.check_id = account.getPatient_id();
                            intent.putExtra("account", account);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });

        });
    }
}