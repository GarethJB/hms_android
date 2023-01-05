package com.example.androidhms.util;

import android.os.CountDownTimer;
import android.view.View;

public class ProgressTimer extends CountDownTimer {

    private View view;
    private int count = 0;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public ProgressTimer(View view, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.view = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (count == 1) view.setVisibility(View.VISIBLE);
        count++;
    }

    public void viewFinish() {
        count = 2;
    }

    @Override
    public void onFinish() {
        view.setVisibility(View.GONE);
    }

}
