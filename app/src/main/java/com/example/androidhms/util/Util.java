package com.example.androidhms.util;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    public static String getChatTimeStamp() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    public static String getChatTime(String time) {
        return new StringBuilder(time).substring(11, 16);
    }

}
