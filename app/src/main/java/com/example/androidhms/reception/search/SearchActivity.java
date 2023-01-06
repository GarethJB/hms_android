package com.example.androidhms.reception.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivitySearchBinding;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySearchBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySearchBinding.inflate(getLayoutInflater());
         setContentView(bind.getRoot());
        Intent intent = getIntent();
        bind.btnSearch.setOnClickListener(this);
        /*bind.toolbar.ivLeft.setOnClickListener(this);
        bind.toolbar.llLogo.setOnClickListener(this);*/

        bind.btnSearch.setOnClickListener(v -> {
                new RetrofitMethod().setParams("name",bind.editPatient.getText().toString()).sendPost("patient.re", new RetrofitMethod.CallBackResult() {
                    @Override
                    public void result(boolean isResult, String data) {
                        Log.d("로그", "result: " + "data");
                    }
                });
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
        });
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_search){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new SearchFragment()).commit();
        }else if(v.getId() == R.id.iv_left){
            onBackPressed();
        }else if(v.getId() == R.id.ll_logo){
            onBackPressed();
        }
    }
}