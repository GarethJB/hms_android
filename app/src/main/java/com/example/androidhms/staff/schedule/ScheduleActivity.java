package com.example.androidhms.staff.schedule;

import static com.example.androidhms.util.Util.staff;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidhms.databinding.ActivityStaffScheduleBinding;
import com.example.androidhms.staff.StaffBaseActivity;
import com.example.androidhms.staff.schedule.adapter.ScheduleAdapter;
import com.example.androidhms.staff.vo.ScheduleVO;
import com.example.androidhms.util.Util;
import com.example.androidhms.util.dialog.AlertDialog;
import com.example.androidhms.util.dialog.TimeDialog;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends StaffBaseActivity {

    private ActivityStaffScheduleBinding bind;
    private Timestamp tsDate = new Timestamp(System.currentTimeMillis());
    private ArrayList<ScheduleVO> scList;
    private int selectedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind.etDate.setText(Util.getDate(tsDate));
        bind.clNotfound.tvNotfound.setText("저장된 일정이 없습니다.");

        Util.setEditTextDate(this, getLayoutInflater(), bind.etDate, (date, dialog) -> {
            tsDate = Timestamp.valueOf(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " 00:00:00");
            bind.etDate.setText(Util.getDate(tsDate));
            dialog.dismiss();
            getSchedule();
        });

        bind.btnNextday.setOnClickListener(onDayClick(true));
        bind.btnPreday.setOnClickListener(onDayClick(false));

        bind.etTime.setOnClickListener(onTimeClick());
        bind.btnDelete.setOnClickListener(onDeleteClick());
        bind.btnSave.setOnClickListener(onSaveClick());

        getSchedule();

    }

    @Override
    protected View getLayoutResource() {
        bind = ActivityStaffScheduleBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    private void setEtdateText() {
        bind.etDate.setText(Util.getDate(tsDate));
    }

    private View.OnClickListener onDayClick(boolean plus) {
        return v -> {
            if (plus) tsDate = Util.timestampOperator(tsDate, Calendar.DAY_OF_MONTH, 1);
            else tsDate = Util.timestampOperator(tsDate, Calendar.DAY_OF_MONTH, -1);
            setEtdateText();
            getSchedule();
        };
    }

    private void getSchedule() {
        bind.rlProgress.view.setVisibility(View.VISIBLE);
        bind.clNotfound.view.setVisibility(View.GONE);
        clearSchedule();
        new RetrofitMethod().setParams("id", staff.getStaff_id())
                .setParams("staff_level", staff.getStaff_level())
                .setParams("date", Util.getDate(tsDate))
                .sendGet("getSchedule.ap", (isResult, data) -> {
                    if (isResult && data != null) {
                        scList = new Gson().fromJson(data, new TypeToken<ArrayList<ScheduleVO>>() {
                        }.getType());
                        if (scList.isEmpty()) bind.clNotfound.view.setVisibility(View.VISIBLE);
                        Util.setRecyclerView(ScheduleActivity.this, bind.rvSchedule,
                                new ScheduleAdapter(ScheduleActivity.this, scList, staff.getStaff_level()), true);
                    } else {
                        Toast.makeText(this, "스케줄을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                    bind.rlProgress.view.setVisibility(View.GONE);
                });
    }

    private View.OnClickListener onTimeClick() {
        return v ->
                new TimeDialog(ScheduleActivity.this, getLayoutInflater(), (dialog, time) -> {
                    bind.etTime.setText(time);
                    dialog.dismiss();
                }).show();
    }

    public void onScheduleClick(int position) {
        ScheduleVO vo = scList.get(position);
        if (selectedId == vo.getSchedule_id()) {
            selectedId = -1;
            clearSchedule();
        }
        else selectedId = vo.getSchedule_id();
        selectedId = vo.getSchedule_id();
        bind.etTime.setText(vo.getTime());
        bind.etContent.setText(vo.getContent());
    }

    private boolean timeCheck() {
        StringBuilder sb = new StringBuilder(tsDate.toString());
        if (bind.etTime.getText().toString().equals("")) sb.replace(11, 16, "00:00");
        else sb.replace(11, 16, bind.etTime.getText().toString());
        return Timestamp.valueOf(sb.toString()).compareTo(new Timestamp(System.currentTimeMillis())) > 0;
    }

    private void clearSchedule() {
        bind.etContent.setText("");
        bind.etTime.setText("");
    }

    private View.OnClickListener onSaveClick() {
        return v -> {
            if (!timeCheck())
                Toast.makeText(this, "과거의 일정을 등록/수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
            else if (selectedId == -1)
                new RetrofitMethod().setParams("id", staff.getStaff_id())
                        .setParams("staff_level", staff.getStaff_level())
                        .setParams("date", Util.getDate(tsDate) + " " + bind.etTime.getText().toString())
                        .setParams("content", bind.etContent.getText().toString())
                        .sendPost("insertSchedule.ap", (isResult, data) -> {
                            if (isResult && data.equals("1")) {
                                Toast.makeText(this, "새 일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                getSchedule();
                            } else
                                Toast.makeText(this, "일정을 저장하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                        });
            else {
                new RetrofitMethod().setParams("id", selectedId)
                        .setParams("staff_level", staff.getStaff_level())
                        .setParams("date", Util.getDate(tsDate) + " " + bind.etTime.getText().toString())
                        .setParams("content", bind.etContent.getText().toString())
                        .sendPost("updateSchedule.ap", (isResult, data) -> {
                            if (isResult && data.equals("1")) {
                                Toast.makeText(this, "일정이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                                getSchedule();
                            } else
                                Toast.makeText(this, "일정을 수정하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                        });
            }
        };
    }

    private View.OnClickListener onDeleteClick() {
        return v -> {
            if (selectedId != -1) {
                new AlertDialog(ScheduleActivity.this, getLayoutInflater(), "일정 삭제", "정말 삭제하시겠습니까?",
                        new AlertDialog.OnAlertDialogClickListener() {
                            @Override
                            public void setOnClickYes(AlertDialog dialog) {
                                new RetrofitMethod().setParams("id", selectedId)
                                        .setParams("staff_level", staff.getStaff_level())
                                        .sendPost("deleteSchedule.ap", (isResult, data) -> {
                                            if (isResult && data.equals("1")) {
                                                Toast.makeText(ScheduleActivity.this, "일정을 삭제했습니다.", Toast.LENGTH_SHORT).show();
                                                getSchedule();
                                            } else
                                                Toast.makeText(ScheduleActivity.this, "일정을 삭제하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        });
                            }

                            @Override
                            public void setOnClickNo(AlertDialog dialog) {
                                dialog.dismiss();
                            }
                        }).setYesText("삭제").show();
            }
        };
    }


}