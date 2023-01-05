package com.example.androidhms.staff.outpatient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.databinding.FragmentStaffReceiptBinding;
import com.example.androidhms.staff.outpatient.adapter.ReceiptAdapter;
import com.example.androidhms.staff.vo.MedicalReceiptVO;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.example.androidhms.util.ProgressTimer;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ReceiptFragment extends Fragment {

    private FragmentStaffReceiptBinding bind;
    private StaffVO staff = Util.staff;
    private Timestamp tsDate = new Timestamp(System.currentTimeMillis());
    private ArrayList<MedicalReceiptVO> mrList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentStaffReceiptBinding.inflate(inflater, container, false);
        bind.etDate.setText(Util.getDate(tsDate));
        bind.btnNextday.setOnClickListener(onDayClick(true));
        bind.btnPreday.setOnClickListener(onDayClick(false));
        Util.setEditTextDate(getContext(), inflater, bind.etDate, new CalendarDialog.SetDateClickListener() {
            @Override
            public void setDateClick(CalendarDay date, CalendarDialog dialog) {
                tsDate = Timestamp.valueOf(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " 00:00:00");
                setEtdateText();
                dialog.dismiss();
                getReceipt();
            }
        });
        getReceipt();
        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

    private void setEtdateText() {
        bind.etDate.setText(Util.getDate(tsDate));
    }

    private View.OnClickListener onDayClick(boolean plus) {
        return v -> {
            if (plus) tsDate = Util.timestampOperator(tsDate, Calendar.DAY_OF_MONTH, 1);
            else tsDate = Util.timestampOperator(tsDate, Calendar.DAY_OF_MONTH, -1);
            setEtdateText();
            getReceipt();
        };
    }

    private void getReceipt() {
        ProgressTimer timer = new ProgressTimer(bind.rlProgress, 10000, 1000);
        timer.start();
        new RetrofitMethod().setParams("id", staff.getStaff_id())
                .setParams("time", Util.getDate(tsDate))
                .sendPost("getmedicalreceipt.ap", new RetrofitMethod.CallBackResult() {
                    @Override
                    public void result(boolean isResult, String data) {
                        if (isResult && !data.equals("null")) {
                            mrList = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
                            Util.setRecyclerView(getContext(), bind.rvMedicalRecord, new ReceiptAdapter(ReceiptFragment.this, mrList), true);
                            timer.viewFinish();
                        }
                    }
                });
    }
}