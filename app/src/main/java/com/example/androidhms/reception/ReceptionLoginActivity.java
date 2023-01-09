package com.example.androidhms.reception;
//datepicker

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityReceptionLoginBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.gson.Gson;

public class ReceptionLoginActivity extends AppCompatActivity {
    ActivityReceptionLoginBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityReceptionLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        ApiClient.setBASEURL("http://192.168.0.14/hms/"); //안드로이드 시작 점에 실시 *경로정확하게 지정*

        bind.btnLogin.setOnClickListener(v -> {
           // Log.d("로그", "onCreate: " + "클릭");
            new RetrofitMethod().setParams("id",bind.editId.getText().toString()).setParams("pw",bind.editPw.getText().toString())
                    .sendPost("login.re", new RetrofitMethod.CallBackResult() {
                        @Override
                        public void result(boolean isResult, String data) {
                          //  Log.d("로그", "result: " +data);
                            if(data.equals("null")){
                                Toast.makeText(ReceptionLoginActivity.this, "사번과 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(ReceptionLoginActivity.this, ReceptionActivity.class);
                                intent.putExtra("staff_name",  new Gson().fromJson(data, StaffVO.class));
                                startActivity(intent);
                            }
                        }
                    });
        });
        //새로고침 버튼 (입력한 정보가 사라지게 )
        bind.refresh.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0,0);
            Intent intent= getIntent();
            startActivity(intent);
            overridePendingTransition(0,0);

        });

        //엔터키 이벤트 : 로그인버튼에 입력하고 엔터키 
   /*     bind.editPw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    //키패트 내리기
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.editPass.getWindowToken(), 0);
                    Log.d("로그", "onKey: " + "action_down");

                    precss();
                    return true;
                }
                //true일 경우 리턴값이 없어진다
                return false;
            }
        });*/


        //뒤로가기 버튼
        bind.toolbar.llLogo.setOnClickListener(v -> {
            onBackPressed();
        });
        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}