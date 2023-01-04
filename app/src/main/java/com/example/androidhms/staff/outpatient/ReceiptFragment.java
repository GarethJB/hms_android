package com.example.androidhms.staff.outpatient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentReceiptBinding;
import com.example.androidhms.databinding.FragmentStaffReceiptBinding;
import com.example.androidhms.staff.schedule.ScheduleActivity;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class ReceiptFragment extends Fragment {

    private FragmentStaffReceiptBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentStaffReceiptBinding.inflate(inflater, container, false);
        Util.setEditTextDate(getContext(), inflater, bind.etDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                String selectedDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                bind.etDate.setText(selectedDate);
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