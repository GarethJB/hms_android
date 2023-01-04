package com.example.androidhms.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.staff.schedule.ScheduleActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Timestamp;

public class Util {

    public static void setRecyclerView(Context context, RecyclerView rv, RecyclerView.Adapter<?> adapter, boolean vertical) {
        RecyclerView.LayoutManager lm;
        if (vertical) lm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        else lm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(null);
    }

    public static void setEditTextDate(Context context, LayoutInflater inflater, EditText edit, CalendarDialog.SetDateClickListener listener) {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalendarDialog(context, inflater, listener).show();
            }
        });
    }

    public static String getChatTimeStamp() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    public static String getChatTime(String time) {
        return new StringBuilder(time).substring(11, 16);
    }

}
