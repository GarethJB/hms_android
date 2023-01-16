package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.databinding.FragmentCustomerStepThreeBinding;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StepThreeFragment extends Fragment {
    private FragmentCustomerStepThreeBinding bind;
    private String setYear;
    private String setMonth;
    private String setDate;
    private int selectedDate;
    private int nowDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bind = FragmentCustomerStepThreeBinding.inflate(inflater, container, false);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(now);
        Log.d("로그", "현재날짜 : " + date);
        nowDate = Integer.parseInt(date);

        bind.calvDate.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            setYear = Integer.toString(year);
            if (month < 10) {
                setMonth = "0" + Integer.toString(month + 1);
            }else {
                setMonth = Integer.toString(month + 1);
            }
            if (dayOfMonth < 10) {
                setDate = "0" + Integer.toString(dayOfMonth);
            }else {
                setDate = Integer.toString(dayOfMonth);
            }

            ReservationSelect.selectedDate = setYear + setMonth + setDate;


            bind.btnSelect.setOnClickListener(v -> {
                selectedDate = Integer.parseInt(setYear + setMonth + setDate);
                if (selectedDate > nowDate && selectedDate <= nowDate + 10000) {
                    StepCnt.cnt = 4;
                    ((ReservationActivity)getActivity()).changeStep();
                }else {
                    Log.d("로그", "예약할 수 없습니다");
                }



            });

        });





        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}