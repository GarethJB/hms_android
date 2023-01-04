package com.example.androidhms.receptionoffice.reception;
//datepicker


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidhms.receptionoffice.appointment.AppointFragment;
import com.example.androidhms.receptionoffice.home.ReceptionHomeFragment;
import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//datepicker


public class ReceptionActivity extends AppCompatActivity {

    BottomNavigationView btm_nav;
    ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        Intent intent =getIntent();

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
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }


}