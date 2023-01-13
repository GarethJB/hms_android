package com.example.androidhms.customer.join;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.R;
import com.example.androidhms.customer.common.FragmentControl;
import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.databinding.ActivityJoinBinding;

public class JoinActivity extends AppCompatActivity {
    private ActivityJoinBinding bind;
    private FragmentControl control;
    private ExistJoinFragment existJoinFragment;
    private NewJoinFragment newJoinFragment;
    private CustomerVO customer;
    private int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        control = new FragmentControl(this);



        Intent intent = getIntent();
        result = intent.getIntExtra("result", 0);

        Log.d("로그", "result : " + result+"");

        if (result == 1) {
            newJoinFragment = new NewJoinFragment();
            control.addFragment(R.id.join_container, newJoinFragment);
            control.showFragment(newJoinFragment);
            Log.d("로그", "신규회원");
        }else if(result == 2) {
            customer = (CustomerVO) intent.getSerializableExtra("customer");
            existJoinFragment = new ExistJoinFragment(customer);
            control.addFragment(R.id.join_container, existJoinFragment);
            control.showFragment(existJoinFragment);
            Log.d("로그", "기존회원");
            Log.d("로그", "이름 : " + customer.getName());
        }









    }
}