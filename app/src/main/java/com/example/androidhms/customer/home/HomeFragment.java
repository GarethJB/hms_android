package com.example.androidhms.customer.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidhms.customer.join.JoinActivity;
import com.example.androidhms.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding bind;

    // 고객용 메인페이지, 의료진 소개, 회원가입 화면 연결
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentHomeBinding.inflate(inflater, container, false);

        bind.btnJoin.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), JoinActivity.class);
            startActivity(intent);
        });

        HomeAdapter adapter = new HomeAdapter(inflater, getContext());

        bind.rcvIntroduceStaff.setAdapter(adapter);
        bind.rcvIntroduceStaff.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}