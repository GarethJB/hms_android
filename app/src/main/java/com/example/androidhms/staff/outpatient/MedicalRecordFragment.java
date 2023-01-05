package com.example.androidhms.staff.outpatient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentStaffMedicalRecordBinding;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class MedicalRecordFragment extends Fragment {

    private FragmentStaffMedicalRecordBinding bind;
    private Timestamp nowDate = new Timestamp(System.currentTimeMillis());
    private Timestamp firstDate = Util.timestampOperator(nowDate, Calendar.MONTH, -3);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentStaffMedicalRecordBinding.inflate(inflater, container, false);
        bind.etFirstDate.setText(Util.getDate(firstDate));
        bind.etSecondDate.setText(Util.getDate(nowDate));
        setDateRange(bind.etFirstDate, nowDate, null);
        setDateRange(bind.etSecondDate, new Timestamp(System.currentTimeMillis()), firstDate);

        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

    // 선택한 날짜에 따라 날짜 선택창의 선택할 수 있는 날짜의 범위 변경
    private void setDateRange(EditText edt, Timestamp maxDate, Timestamp minDate) {
        Util.setEditTextDate(getContext(), getLayoutInflater(), edt, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                Timestamp ts = Timestamp.valueOf(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " 00:00:00");
                String selectedDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                edt.setText(selectedDate);
                if (edt.getId() == R.id.et_first_date) setDateRange(bind.etSecondDate, nowDate, ts);
                else setDateRange(bind.etFirstDate, ts, null);
                dialog.dismiss();
            }
        }, maxDate, minDate);
    }

}