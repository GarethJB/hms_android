package com.example.androidhms.customer.info;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidhms.R;
import com.example.androidhms.customer.info.acceptance.AcceptanceRecordActivity;
import com.example.androidhms.customer.info.card.CardActivity;
import com.example.androidhms.customer.info.medical.MedicalRecordActivity;
import com.example.androidhms.customer.info.myinfo.MyinfoActivity;
import com.example.androidhms.customer.info.reservation.ReservationRecordActivity;
import com.example.androidhms.customer.info.timetable.TimeTableActivity;
import com.example.androidhms.databinding.FragmentCustomerInfoBinding;


public class InfoFragment extends Fragment {
    FragmentCustomerInfoBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerInfoBinding.inflate(inflater, container, false);

        ReservationAdapter reservationAdapter = new ReservationAdapter(inflater, getContext());

        bind.rcvReservation.setAdapter(reservationAdapter);
        bind.rcvReservation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        bind.btnMyinfo.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), MyinfoActivity.class);
            startActivity(intent);
        });

        bind.btnCard.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), CardActivity.class);
            startActivity(intent);
        });

        bind.btnQr.setOnClickListener(v1 -> {
            Dialog dialog;
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_qr);
            dialog.show();
        });

        bind.llMedicalRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
            startActivity(intent);
        });

        bind.llReservationRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReservationRecordActivity.class);
            startActivity(intent);
        });

        bind.llAcceptanceInquire.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AcceptanceRecordActivity.class);
            startActivity(intent);
        });

        bind.llTimetable.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TimeTableActivity.class);
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