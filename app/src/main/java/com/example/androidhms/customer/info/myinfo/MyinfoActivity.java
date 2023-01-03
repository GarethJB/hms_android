package com.example.androidhms.customer.info.myinfo;

import android.os.Bundle;
import android.widget.Button;

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

        Button btn_update = bind.btnUpdate;
        btn_update.setOnClickListener(v -> {
            changeFragment(new UpdateFragment());
        });


        changeFragment(new SelectFragment());





    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}