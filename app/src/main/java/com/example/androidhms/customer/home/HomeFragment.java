package com.example.androidhms.customer.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.customer.join.JoinActivity;


public class HomeFragment extends Fragment {
    RecyclerView rcv_introduce;
    Button btn_join;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rcv_introduce = v.findViewById(R.id.rcv_introduce);

        btn_join = v.findViewById(R.id.btn_join);
        btn_join.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), JoinActivity.class);
            startActivity(intent);
        });

        HomeAdapter adapter = new HomeAdapter(inflater, getContext());

        rcv_introduce.setAdapter(adapter);
        rcv_introduce.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));



        return v;
    }
}