package com.example.androidhms.util;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ToastChatBinding;
import com.example.androidhms.databinding.ToolbarStaffBinding;
import com.example.androidhms.staff.messenger.ChatActivity;
import com.example.androidhms.staff.messenger.MessengerActivity;
import com.example.androidhms.staff.messenger.MessengerFragment;
import com.example.androidhms.staff.messenger.adapter.ChatRoomAdapter;
import com.example.androidhms.staff.vo.ChatRoomVO;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.example.androidhms.staff.vo.StaffVO;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class Util {

    public static StaffVO staff = null;

    /**
     * 메신저 Activity에서 쓰이는 StaffChatDTO로 변환
     */
    public static StaffChatDTO getStaffChatDTO() {
        return new StaffChatDTO(Util.staff.getStaff_id(),
                Util.staff.getStaff_level(), Util.staff.getDepartment_id(), Util.staff.getName(),
                Util.staff.getDepartment_name());
    }

    /**
     * RecyclerView 설정
     */
    public static void setRecyclerView(Context context, RecyclerView rv, RecyclerView.Adapter<?> adapter, boolean vertical) {
        RecyclerView.LayoutManager lm;
        if (vertical) lm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        else lm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(null);
    }

    public static int getPxFromDp(Activity activity, int dp) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = displayMetrics.density;
        return (int) (dp * density);
    }

    /**
     * MaterialCalendar Dialog 설정
     */
    public static void setEditTextDate(Context context, LayoutInflater inflater, EditText edit, CalendarDialog.SetDateClickListener listener) {
        edit.setOnClickListener(v -> {
            if (edit.getText().toString().equals(""))
                new CalendarDialog(context, inflater, listener).show();
            else
                new CalendarDialog(context, inflater, listener).setDate(edit.getText().toString()).show();
        });
    }

    public static void setEditTextDate(Context context, LayoutInflater inflater, EditText edit,
                                       CalendarDialog.SetDateClickListener listener, Timestamp maxtime, Timestamp mintime) {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maxtime == null && mintime != null) {
                    new CalendarDialog(context, inflater, listener).setDate(edit.getText().toString())
                            .setMinDate(mintime).show();
                } else if (maxtime != null && mintime != null)
                    new CalendarDialog(context, inflater, listener).setDate(edit.getText().toString())
                            .setMaxDate(maxtime).setMinDate(mintime).show();
                else if (maxtime != null) {
                    new CalendarDialog(context, inflater, listener).setDate(edit.getText().toString())
                            .setMaxDate(maxtime).show();
                }
            }
        });
    }

    /**
     * String date를 Timestamp 타입으로 변환<br>
     * ex) Util.getTimestamp("2022-01-01");
     */
    public static Timestamp getTimestamp(String date) {
        return Timestamp.valueOf(date + " 00:00:00");
    }

    /**
     * Timestamp 시간 연산<br>
     * ex) Util.timestampOperator(time, Calendar.YEAR, 1);
     */
    public static Timestamp timestampOperator(Timestamp time, int pattern, int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(pattern, number);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Timestamp 포맷<br>
     * ex) Util.timestampOperator(time, "yyyy-MM-dd HH:mm:ss"); -> 2022-01-01 00:00:00
     */
    public static String dateFormat(Timestamp time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.KOREA);
        return sdf.format(time);
    }

    /**
     * Timestamp에서 연,월,일만 추출<br>
     * 2022-01-01 00:00:00 -> 2022-01-01
     */
    public static String getDate(Timestamp time) {
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.KOREA);
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.KOREA);
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.KOREA);
        return year.format(time) + "-" + month.format(time) + "-" + day.format(time);
    }

    /**
     * 채팅작성시간을 Timestamp(String)로 기록<br>
     * 2022-01-01 00:00:00.000
     */
    public static String getChatTimeStamp() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    /**
     * 채팅시간에서 시,분만 추출<br>
     * 2022-01-01 08:05:00.111 -> 08:05
     */
    public static String getTime(String time) {
        return new StringBuilder(time).substring(11, 16);
    }

    /**
     * 주민번호로부터 생일가져오기<br>
     * 950217 -> 1995-02-17
     */
    public static String getBirthDay(String social_id) {
        StringBuilder sb = new StringBuilder(social_id);
        int year = Integer.parseInt(sb.substring(0, 2));
        if (year < 22) year += 2000;
        else year += 1900;
        String month = sb.substring(2, 4);
        String day = sb.substring(4);
        return year + "-" + month + "-" + day;
    }

    /**
     * 만나이 계산
     */
    public static int getAge(String social_id) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(System.currentTimeMillis()));
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        StringBuilder sb = new StringBuilder(social_id);
        int year = Integer.parseInt(sb.substring(0, 2));
        if (year < 22) year += 2000;
        else year += 1900;
        int month = Integer.parseInt(sb.substring(2, 4));
        int day = Integer.parseInt(sb.substring(4));

        int age = currentYear - year;
        int m = currentMonth - month;
        if (m < 0 || (m == 0 && currentDay < day)) age--;
        return age;
    }

    /**
     * 올라와있는 안드로이드 키보드를 내림
     */
    public static void keyboardDown(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {

        }
    }


}
