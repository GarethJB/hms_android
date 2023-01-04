package com.example.androidhms.util;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.androidhms.databinding.DialogCalendarBinding;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

public class CalendarDialog {

    private DialogCalendarBinding bind;
    private Dialog dialog;
    private final Calendar calendar = Calendar.getInstance();

    public CalendarDialog(Context context, LayoutInflater inflater, SetDateClickListener callback) {
        dialog = new Dialog(context);
        bind = DialogCalendarBinding.inflate(inflater);
        dialog.setContentView(bind.getRoot());
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.horizontalMargin = 100;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setDimAmount(0.3f);

        bind.calendar.setOnDateChangedListener((widget, date, selected) ->
                callback.setDateClick(date, CalendarDialog.this));

        // 토요일
        bind.calendar.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                calendar.set(Calendar.YEAR, day.getYear());
                calendar.set(Calendar.MONTH, day.getMonth() - 1);
                calendar.set(Calendar.DATE, day.getDay());
                int weekday = calendar.get(Calendar.DAY_OF_WEEK);
                return weekday == Calendar.SATURDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.BLUE));
            }
        });

        // 일요일
        bind.calendar.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                calendar.set(Calendar.YEAR, day.getYear());
                calendar.set(Calendar.MONTH, day.getMonth() - 1);
                calendar.set(Calendar.DATE, day.getDay());
                int weekday = calendar.get(Calendar.DAY_OF_WEEK);
                return weekday == Calendar.SUNDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.RED));
            }
        });

    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public interface SetDateClickListener {
        void setDateClick(CalendarDay date, CalendarDialog dialog);
    }
}
