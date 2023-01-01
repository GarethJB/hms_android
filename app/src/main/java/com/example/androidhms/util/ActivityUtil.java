package com.example.androidhms.util;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ActivityUtil {

    private final AppCompatActivity activity;

    public ActivityUtil(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setFragment(int id, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void addFragment(int id, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().add(id, fragment).commit();
    }

    public void showFragment(Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    public void hideFragment(Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

}
