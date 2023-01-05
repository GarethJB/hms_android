package com.example.androidhms.reception;
//datepicker

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityReceptionLoginBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;

public class ReceptionLoginActivity extends AppCompatActivity {
    ActivityReceptionLoginBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityReceptionLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        ApiClient.setBASEURL("http://192.168.0.14/hms"); //안드로이드 시작 점에 실시

        //로그인처리
     /*   bind.btnLogin1.setOnClickListener(v -> {
            new RetrofitMethod().setParams("id",bind.editId.getText().toString()).setParams("pw",bind.editPw.getText().toString())
                    .sendPost("login.re", new RetrofitMethod.CallBackResult() {
                                @Override
                                public void result(boolean isResult, String data) {
                                    if(data.equals("null")){
                                        Log.d("로그", "result: " + data);
                                        Toast.makeText(ReceptionLoginActivity.this, "사번과 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Intent intent = new Intent(ReceptionLoginActivity.this, ReceptionActivity.class);
                                        intent.putExtra("staff_name", new Gson().fromJson(data, StaffVO.class));
                                        startActivity(intent);
                                    }
                                }
                            }
                    );
        });*/
        
      bind.btnLogin1.setOnClickListener(v -> {
          new RetrofitMethod().setParams("id",bind.editId.getText().toString()).setParams("pw",bind.editPw.getText().toString())
                  .sendPost("login.re", new RetrofitMethod.CallBackResult() {
                      @Override
                      public void result(boolean isResult, String data) {
                          Log.d("로그", "result: " + data);
                          
                      }
                  });
      });
        bind.toolbar.ivLeft.setOnClickListener(v -> {
           onBackPressed();
        });

        bind.toolbar.llLogo.setOnClickListener(v -> {
            onBackPressed();
        });
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}