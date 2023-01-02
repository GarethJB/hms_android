package com.example.androidhms.customer.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;


public class InfoFragment extends Fragment {
    RecyclerView rcv_reservation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        rcv_reservation = v.findViewById(R.id.rcv_reservation);

        ReservationAdapter adapter = new ReservationAdapter(inflater, getContext());

        rcv_reservation.setAdapter(adapter);
        rcv_reservation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return v;
    }
}