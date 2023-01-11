package com.example.androidhms.customer.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.customer.LoginInfo;
import com.example.androidhms.customer.join.JoinActivity;
import com.example.androidhms.databinding.FragmentCustomerHomeBinding;


public class HomeFragment extends Fragment {
    FragmentCustomerHomeBinding bind;

    // 고객용 메인페이지, 의료진 소개, 회원가입 화면 연결
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerHomeBinding.inflate(inflater, container, false);

        if (LoginInfo.check_id > 0) {
            bind.btnJoin.setText("WELCOME");
            bind.btnJoin.setEnabled(false);
        }

        bind.btnJoin.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), JoinActivity.class);
            startActivity(intent);
        });


        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}