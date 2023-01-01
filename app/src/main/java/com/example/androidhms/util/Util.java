package com.example.androidhms.util;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

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

    public static String getChatTime() {
        StringBuilder sb = new StringBuilder(new Timestamp(System.currentTimeMillis()).toString());
        // 2023-01-02 00:20:10.377
        return sb.substring(5, 16);
    }

}
