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
import com.example.androidhms.customer.info.reservation.ReservationScheduleActivity;
import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.databinding.FragmentCustomerInfoBinding;


public class InfoFragment extends Fragment {
    private FragmentCustomerInfoBinding bind;
    private CustomerVO customer;

    public InfoFragment(CustomerVO customer) {
        this.customer = customer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerInfoBinding.inflate(inflater, container, false);

        //예약현황 어댑터
        ReservationAdapter reservationAdapter = new ReservationAdapter(inflater, getContext());
        bind.rcvReservation.setAdapter(reservationAdapter);
        bind.rcvReservation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        //로그인시 화면변경
        bind.btnMyinfo.setText(customer.getName());



        //클릭시 계정정보 조회
        bind.btnMyinfo.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), MyinfoActivity.class);
            intent.putExtra("customer", customer);
            startActivity(intent);
        });

        //클릭시 카드전환
        bind.btnCard.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), CardActivity.class);
            intent.putExtra("customer", customer);
            startActivity(intent);
        });

        //클릭시 큐알화면 전환
        bind.btnQr.setOnClickListener(v1 -> {
            Dialog dialog;
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_qr);
            dialog.show();
        });

        //기록 조회
        bind.llMedicalRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
            intent.putExtra("patient_id", customer.getPatient_id());
            startActivity(intent);
        });

        //일정 조회
        bind.llReservationRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReservationScheduleActivity.class);
            intent.putExtra("patient_id", customer.getPatient_id());
            startActivity(intent);
        });

        //클릭시 수납조회
        bind.llAcceptanceInquire.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AcceptanceRecordActivity.class);
            startActivity(intent);
        });

        //클릭시 의료진 소개
        bind.llTimetable.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MyinfoActivity.class);
            intent.putExtra("customer", customer);
            startActivity(intent);
        });





      



        //로그인시 화면 변경



        return bind.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}