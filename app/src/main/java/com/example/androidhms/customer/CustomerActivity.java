package com.example.androidhms.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.home.HomeFragment;
import com.example.androidhms.customer.info.InfoFragment;
import com.example.androidhms.customer.location.LocationFragment;
import com.example.androidhms.customer.reservation.ReservationActivity;
import com.example.androidhms.customer.ui.login.LoginActivity;
import com.example.androidhms.databinding.ActivityCustomerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity {
    ActivityCustomerBinding bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());


        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        bind.toolbar.tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(CustomerActivity.this, LoginActivity.class);
            startActivity(intent);
        });


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
                    return false;
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