package com.example.androidhms.customer.reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;


public class StepThreeFragment extends Fragment {
    RecyclerView rcv_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step_three, container, false);

        rcv_date = v.findViewById(R.id.rcv_date);

        StepThreeAdapter adapter = new StepThreeAdapter(inflater, getContext());

        rcv_date.setAdapter(adapter);
        rcv_date.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        return v;
    }
}