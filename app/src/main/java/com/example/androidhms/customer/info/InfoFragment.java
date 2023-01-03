package com.example.androidhms.customer.info;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.customer.info.acceptance.AcceptanceRecordActivity;
import com.example.androidhms.customer.info.card.CardActivity;
import com.example.androidhms.customer.info.medical.MedicalRecordActivity;
import com.example.androidhms.customer.info.myinfo.MyinfoActivity;
import com.example.androidhms.customer.info.reservation.ReservationRecordActivity;
import com.example.androidhms.customer.info.timetable.TimeTableActivity;
import com.example.androidhms.databinding.FragmentInfoBinding;


public class InfoFragment extends Fragment {
    FragmentInfoBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentInfoBinding.inflate(inflater, container, false);

        RecyclerView rcv_reservation = bind.rcvReservation;
        Button btn_myinfo = bind.btnMyinfo;
        Button btn_card = bind.btnCard;
        Button btn_qr = bind.btnQr;
        LinearLayout ll_medical = bind.llMedical;
        LinearLayout ll_reservation = bind.llReservation;
        LinearLayout ll_acceptance = bind.llAcceptance;
        LinearLayout ll_time = bind.llTime;

        ReservationAdapter reservationAdapter = new ReservationAdapter(inflater, getContext());

        rcv_reservation.setAdapter(reservationAdapter);
        rcv_reservation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        btn_myinfo.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), MyinfoActivity.class);
            startActivity(intent);
        });

        btn_card.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), CardActivity.class);
            startActivity(intent);
        });

        btn_qr.setOnClickListener(v1 -> {
            Dialog dialog;
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_qr);
            dialog.show();
        });

        ll_medical.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
            startActivity(intent);
        });

        ll_reservation.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReservationRecordActivity.class);
            startActivity(intent);
        });

        ll_acceptance.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AcceptanceRecordActivity.class);
            startActivity(intent);
        });

        ll_time.setOnClickListener(v -> {
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