package com.example.androidhms.customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.home.HomeFragment;
import com.example.androidhms.customer.info.InfoFragment;
import com.example.androidhms.customer.location.LocationFragment;
import com.example.androidhms.customer.reservation.ReservationActivity;
import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.databinding.ActivityCustomerBinding;
import com.example.conn.ApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity {
    private ActivityCustomerBinding bind;
    private CustomerVO customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ApiClient.setBASEURL("http://192.168.0.116/hms/");


        //뒤로가기
        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

        //로그아웃
        bind.toolbar.ivLogout.setOnClickListener(v -> {
            LoginInfo.check_id = 0;
            onBackPressed();
        });

        //로그인 액티비티 띄우기
        bind.toolbar.tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(CustomerActivity.this, CustomerLoginActivity.class);
            activityResultLauncher.launch(intent);
        });

        //로그인시 화면변경
        checkLogin();



        //바텀네비게이션
        changeFragment(new HomeFragment());
        BottomNavigationView btm_nav = bind.btmNav;
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.btm_item1){
                    //홈 프래그먼트 전환
                    changeFragment(new HomeFragment());
                } else if(item.getItemId() == R.id.btm_item2){
                    if (LoginInfo.check_id > 0) {
                        //예약 프래그먼트 전환
                        Intent intent = new Intent(CustomerActivity.this, ReservationActivity.class);
                        startActivity(intent);
                        Log.d("로그", "예약 프래그먼트 전환");
                    } else {
                        Toast.makeText(CustomerActivity.this,
                                "로그인시 이용 가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                } else if(item.getItemId() == R.id.btm_item3){
                    //위치 프래그먼트 전환
                    changeFragment(new LocationFragment());
                } else if(item.getItemId() == R.id.btm_item4){
                    //인포 프래그먼트 전환
                    if (LoginInfo.check_id > 0) {
                        Log.d("로그", "인포 프래그먼트 account : " + customer.getEmail());
                        changeFragment(new InfoFragment(customer));
                    } else {
                        Toast.makeText(CustomerActivity.this,
                                "로그인시 이용 가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });

    }

    //프래그먼트 전환 메소드
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

    //로그인 액티비티 전환 메소드
    ActivityResultLauncher<Intent> activityResultLauncher
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK ) {
                Intent intent = result.getData();
                customer = (CustomerVO) intent.getSerializableExtra("customer");
                LoginInfo.check_id = customer.getPatient_id();
                checkLogin();
                changeFragment(new HomeFragment());
            }
        }
    });

    //로그인 확인
    public void checkLogin(){
        if (LoginInfo.check_id > 0) {
            bind.toolbar.tvLogin.setVisibility(View.GONE);
            bind.toolbar.ivLeft.setVisibility(View.GONE);
            bind.toolbar.ivLogout.setVisibility(View.VISIBLE);
            bind.toolbar.tvAccount.setVisibility(View.VISIBLE);
            bind.toolbar.tvAccount.setText(customer.getName() + "님");
        }
    }
}