package com.example.androidhms.staff.outpatient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentCustomerMedicalRecordBinding;
import com.example.androidhms.databinding.FragmentMedicalRecordBinding;
import com.example.androidhms.databinding.FragmentStaffMedicalRecordBinding;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class MedicalRecordFragment extends Fragment {

    private FragmentStaffMedicalRecordBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentStaffMedicalRecordBinding.inflate(inflater, container, false);
        Util.setEditTextDate(getContext(), inflater, bind.etFirstDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                String selectedDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                bind.etFirstDate.setText(selectedDate);
                dialog.dismiss();
            }
        });
        Util.setEditTextDate(getContext(), inflater, bind.etSecondDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                String selectedDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                bind.etSecondDate.setText(selectedDate);
                dialog.dismiss();
            }
        });
        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}