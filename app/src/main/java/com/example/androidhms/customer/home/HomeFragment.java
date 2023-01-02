package com.example.androidhms.customer.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;


public class HomeFragment extends Fragment {
    RecyclerView rcv_introduce;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rcv_introduce = v.findViewById(R.id.rcv_introduce);

        HomeAdapter adapter = new HomeAdapter(inflater, getContext());

        rcv_introduce.setAdapter(adapter);
        rcv_introduce.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));



        return v;
    }
}