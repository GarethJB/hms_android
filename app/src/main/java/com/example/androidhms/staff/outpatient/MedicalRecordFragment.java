package com.example.androidhms.staff.outpatient;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentStaffMedicalRecordBinding;
import com.example.androidhms.staff.lookup.LookupActivity;
import com.example.androidhms.staff.outpatient.adapter.MedicalRecordAdapter;
import com.example.androidhms.staff.vo.MedicalRecordVO;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.util.CalendarDialog;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class MedicalRecordFragment extends Fragment {

    private FragmentStaffMedicalRecordBinding bind;
    private Timestamp nowDate = new Timestamp(System.currentTimeMillis());
    private Timestamp firstDate = Util.timestampOperator(nowDate, Calendar.MONTH, -1);
    private StaffVO staff = Util.staff;
    private ArrayList<MedicalRecordVO> mrList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentStaffMedicalRecordBinding.inflate(inflater, container, false);

        // 날짜선택
        bind.etFirstDate.setText(Util.getDate(firstDate));
        bind.etSecondDate.setText(Util.getDate(nowDate));
        setDateRange(bind.etFirstDate, nowDate, null);
        setDateRange(bind.etSecondDate, new Timestamp(System.currentTimeMillis()), firstDate);

        // 상세옵션
        if (staff.getStaff_level() == 2) bind.rbMyPatient.setChecked(true);
        else if (staff.getDepartment_id() > 100) bind.rbMyDepartment.setChecked(true);
        else bind.rbAllPatient.setChecked(true);
        bind.rbAllPatient.setChecked(true);
        bind.imgvOption.setOnClickListener(onOptionClick());
        bind.tvOption.setOnClickListener(onOptionClick());

        bind.btnSearch.setOnClickListener(onSearchClick());
        // 키보드의 검색 버튼 눌렀을때 앱의 검색 버튼 클릭
        bind.etName.setOnEditorActionListener((v, actionId, event) -> {
            bind.btnSearch.performClick();
            return false;
        });


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

    private View.OnClickListener onOptionClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bind.llOption.getVisibility() == View.GONE) {
                    bind.llOption.setVisibility(View.VISIBLE);
                    bind.imgvOption.setImageResource(R.drawable.icon_up);
                } else if (bind.llOption.getVisibility() == View.VISIBLE) {
                    bind.llOption.setVisibility(View.GONE);
                    bind.imgvOption.setImageResource(R.drawable.icon_down);
                }

            }
        };
    }

    private View.OnClickListener onSearchClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.rlProgress.view.setVisibility(View.VISIBLE);
                bind.clNotfound.view.setVisibility(View.GONE);
                Util.keyboardDown(requireActivity());
                String option = "";
                if (bind.rbMyDepartment.isChecked()) option = "department";
                else if (bind.rbMyPatient.isChecked()) option = "doctor";
                new RetrofitMethod().setParams("id", staff.getStaff_id())
                        .setParams("patient_name", bind.etName.getText().toString())
                        .setParams("first_date", bind.etFirstDate.getText().toString())
                        .setParams("second_date", bind.etSecondDate.getText().toString())
                        .setParams("option", option)
                        .sendPost("getmedicalrecord.ap", new RetrofitMethod.CallBackResult() {
                            @Override
                            public void result(boolean isResult, String data) {
                                if (isResult && !data.equals("null")) {
                                    mrList = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalRecordVO>>(){}.getType());
                                    Util.setRecyclerView(getContext(), bind.rvMedicalRecord,
                                            new MedicalRecordAdapter(MedicalRecordFragment.this, mrList), true);
                                    if (mrList.isEmpty()) bind.clNotfound.view.setVisibility(View.VISIBLE);
                                    else bind.rvMedicalRecord.post(() ->  bind.rlProgress.view.setVisibility(View.GONE));
                                } else if (!isResult) {
                                    Toast.makeText(requireActivity(), "검색결과를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    bind.clNotfound.view.setVisibility(View.VISIBLE);
                                }
                                bind.rlProgress.view.setVisibility(View.GONE);
                            }
                        });
            }
        };
    }

    public void onMedicalRecordClick(int position) {
        Intent intent = new Intent(getActivity(), PrescriptionActivity.class);
        intent.putExtra("medical_record_id", mrList.get(position).getMedical_record_id());
        startActivity(intent);
    }

}