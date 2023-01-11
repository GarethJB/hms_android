package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.databinding.FragmentCustomerStepThreeBinding;


public class StepThreeFragment extends Fragment {
    private FragmentCustomerStepThreeBinding bind;
    private String setYear;
    private String setMonth;
    private String setDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bind = FragmentCustomerStepThreeBinding.inflate(inflater, container, false);

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

        });

        bind.btnSelect.setOnClickListener(v -> {

            StepCnt.cnt = 4;
            ((ReservationActivity)getActivity()).changeStep();


        });

        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}