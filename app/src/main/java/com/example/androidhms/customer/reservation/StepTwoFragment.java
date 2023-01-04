package com.example.androidhms.customer.reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;


public class StepTwoFragment extends Fragment {
    RecyclerView rcv_staff;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step_two, container, false);

        rcv_staff = v.findViewById(R.id.rcv_staff);

        StepTwoAdapter adapter = new StepTwoAdapter(inflater, getContext());

        rcv_staff.setAdapter(adapter);
        rcv_staff.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return v;
    }
}