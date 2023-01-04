package com.example.androidhms.receptionoffice;
//datepicker


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityReceptionBinding;
import com.example.androidhms.receptionoffice.appointment.AppointFragment;
import com.example.androidhms.receptionoffice.home.ReceptionHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//datepicker


public class ReceptionActivity extends AppCompatActivity {

    BottomNavigationView btm_nav;
    ActivityReceptionBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityReceptionBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Intent intent =getIntent();

        changeFragment(new ReceptionHomeFragment());

        btm_nav =findViewById(R.id.btm_nav);
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.btm_item1){
                    changeFragment(new ReceptionHomeFragment());
                }else if(item.getItemId() == R.id.btm_item2){
                    changeFragment(new SearchFragment());
                }else if(item.getItemId() == R.id.btm_item3){
                    changeFragment(new AppointFragment());
                }
                return true;
            }
        });

        //임시
        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }


}