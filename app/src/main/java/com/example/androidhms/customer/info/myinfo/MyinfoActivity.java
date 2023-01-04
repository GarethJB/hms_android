package com.example.androidhms.customer.info.myinfo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityMyinfoBinding;

public class MyinfoActivity extends AppCompatActivity {
    ActivityMyinfoBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMyinfoBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.btnBack.setVisibility(View.GONE);
        bind.btnOk.setVisibility(View.GONE);

        changeFragment(new SelectFragment());

        bind.btnUpdate.setOnClickListener(v -> {
            changeFragment(new UpdateFragment());
            bind.btnUpdate.setVisibility(View.GONE);
            bind.btnBack.setVisibility(View.VISIBLE);
            bind.btnOk.setVisibility(View.VISIBLE);
        });

        bind.btnBack.setOnClickListener(v -> {
            changeFragment(new SelectFragment());
            bind.btnUpdate.setVisibility(View.VISIBLE);
            bind.btnBack.setVisibility(View.GONE);
            bind.btnOk.setVisibility(View.GONE);
        });
    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}