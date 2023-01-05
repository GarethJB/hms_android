package com.example.androidhms.customer.info;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidhms.R;
import com.example.androidhms.customer.LoginInfo;
import com.example.androidhms.customer.info.acceptance.AcceptanceRecordActivity;
import com.example.androidhms.customer.info.card.CardActivity;
import com.example.androidhms.customer.info.medical.MedicalRecordActivity;
import com.example.androidhms.customer.info.myinfo.MyinfoActivity;
import com.example.androidhms.customer.info.reservation.ReservationRecordActivity;
import com.example.androidhms.customer.info.timetable.TimeTableActivity;
import com.example.androidhms.customer.vo.AccountVO;
import com.example.androidhms.databinding.FragmentCustomerInfoBinding;
import com.example.androidhms.staff.vo.PatientVO;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;


public class InfoFragment extends Fragment {
    private FragmentCustomerInfoBinding bind;
    final String TAG = "로그";
    private PatientVO patient;
    private AccountVO account;

    public InfoFragment(AccountVO account) {
        this.account = account;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerInfoBinding.inflate(inflater, container, false);

        ReservationAdapter reservationAdapter = new ReservationAdapter(inflater, getContext());

        bind.rcvReservation.setAdapter(reservationAdapter);
        bind.rcvReservation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        new RetrofitMethod().setParams("check_id", LoginInfo.check_id).sendPost("patientinfo.ap", (isResult, data) -> {
            patient = new Gson().fromJson(data, PatientVO.class);
            Log.d(TAG, "고객이름 : " + patient.getName());


            //로그인시 화면변경
            bind.btnMyinfo.setText(patient.getName());



            //클릭시 계정정보 조회
            bind.btnMyinfo.setOnClickListener(v1 -> {
                Intent intent = new Intent(getActivity(), MyinfoActivity.class);
                Log.d(TAG, "계정정보 account : " + account.getEmail());
                intent.putExtra("patient", patient);
                intent.putExtra("account", account);
                startActivity(intent);
            });

            //클릭시 카드전환
            bind.btnCard.setOnClickListener(v1 -> {
                Intent intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);
            });

            //클릭시 큐알화면 전환
            bind.btnQr.setOnClickListener(v1 -> {
                Dialog dialog;
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_qr);
                dialog.show();
            });

            //클릭시 진료기록 조회
            bind.llMedicalRecord.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
                startActivity(intent);
            });

            //클릭시 예약기록 조회
            bind.llReservationRecord.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ReservationRecordActivity.class);
                startActivity(intent);
            });

            //클릭시 수납조회
            bind.llAcceptanceInquire.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), AcceptanceRecordActivity.class);
                startActivity(intent);
            });

            //클릭시 의료진 소개
            bind.llTimetable.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), TimeTableActivity.class);
                startActivity(intent);
            });



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