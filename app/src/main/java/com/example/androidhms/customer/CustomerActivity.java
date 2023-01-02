package com.example.androidhms.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.reservation.ReservationActivity;
import com.example.androidhms.customer.home.HomeFragment;
import com.example.androidhms.customer.info.InfoFragment;
import com.example.androidhms.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity {
    ActivityHomeBinding bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());


        changeFragment(new HomeFragment());
        BottomNavigationView btm_nav = bind.btmNav;
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.btm_item1){
                    changeFragment(new HomeFragment());
                }else if(item.getItemId() == R.id.btm_item2){
                    Intent intent = new Intent(CustomerActivity.this, ReservationActivity.class);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.btm_item3){
                    changeFragment(new LocationFragment());
                }else if(item.getItemId() == R.id.btm_item4){
                    changeFragment(new InfoFragment());

                }

                return true;
            }
        });



    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

}