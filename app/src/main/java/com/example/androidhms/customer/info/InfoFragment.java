package com.example.androidhms.customer.info;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidhms.R;
import com.example.androidhms.customer.LoginInfo;
import com.example.androidhms.customer.hospital.acceptance.AcceptanceRecordActivity;
import com.example.androidhms.customer.info.card.CardActivity;
import com.example.androidhms.customer.info.medical.MedicalRecordActivity;
import com.example.androidhms.customer.info.myinfo.MyinfoActivity;
import com.example.androidhms.customer.info.reservation.ReservationScheduleActivity;
import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.customer.vo.MedicalReceiptVO;
import com.example.androidhms.databinding.FragmentCustomerInfoBinding;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class InfoFragment extends Fragment {
    private FragmentCustomerInfoBinding bind;
    private CustomerVO customer;
    private ArrayList<MedicalReceiptVO> medicalReceipt = new ArrayList<>();
    private TextView tv_number, tv_department, tv_name, tv_time, tv_waiting;
    private Dialog dialog_number;
    private Dialog dialog_qr;
    private int number;

    public InfoFragment(CustomerVO customer) {
        this.customer = customer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerInfoBinding.inflate(inflater, container, false);

        dialog_number = new Dialog(getActivity());
        dialog_number.setContentView(R.layout.dialog_number_ticket);

        dialog_qr = new Dialog(getActivity());
        dialog_qr.setContentView(R.layout.dialog_qr);

        tv_number = dialog_number.findViewById(R.id.tv_number);
        tv_department = dialog_number.findViewById(R.id.tv_department);
        tv_name = dialog_number.findViewById(R.id.tv_name);
        tv_time = dialog_number.findViewById(R.id.tv_time);
        tv_waiting = dialog_number.findViewById(R.id.tv_waiting);

        //예약현황 어댑터
        ReservationAdapter reservationAdapter = new ReservationAdapter(inflater, getContext());
        bind.rcvReservation.setAdapter(reservationAdapter);
        bind.rcvReservation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        //클릭시 번호표 조회
        bind.btnNumberTicket.setOnClickListener(v1 -> {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String now = format.format(currentTime);
            Log.d("로그", "오늘 : " + now);
            new RetrofitMethod().setParams("today", now)
                    .sendPost("number_ticket.cu", (isResult, data) -> {
                        medicalReceipt = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
                        for (int i = 0; i <= medicalReceipt.size()-1; i++) {
                            if (LoginInfo.check_id == medicalReceipt.get(i).getPatient_id()) {
                                number = i;
                            }
                        }
                        if (medicalReceipt.size() != 0) {
                            Log.d("로그", "일치 " + number);
                            Log.d("로그", "환자ID : " + LoginInfo.check_id);
                            tv_number.setText(Integer.toString(number));
                            tv_department.setText(medicalReceipt.get(number).getDepartment_name());
                            tv_name.setText(medicalReceipt.get(number).getName());
                            tv_time.setText(medicalReceipt.get(number).getTime()+"");
                            tv_waiting.setText(Integer.toString(number));
                            dialog_number.show();
                            dialog_number.findViewById(R.id.btn_back).setOnClickListener(v -> {
                                dialog_number.dismiss();
                            });
                        }else if (medicalReceipt.size() == 0) {
                            Toast.makeText(getContext(), "접수내역이 없습니다", Toast.LENGTH_SHORT );
                        }
                    });
        });

        //클릭시 카드전환
        bind.btnCard.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), CardActivity.class);
            intent.putExtra("customer", customer);
            startActivity(intent);
        });

        //클릭시 큐알화면 전환
        bind.btnQr.setOnClickListener(v1 -> {
            dialog_qr.show();
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