package com.example.androidhms.staff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ToolbarStaffBinding;
import com.example.androidhms.staff.messenger.ChatActivity;
import com.example.androidhms.staff.messenger.MessengerActivity;
import com.example.androidhms.util.HmsFirebase;

public abstract class StaffBaseActivity extends AppCompatActivity {

    private HmsFirebase fb;
    private ToolbarStaffBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        bind = ToolbarStaffBinding.bind(findViewById(R.id.toolbar));
        bind.imgvBefore.setOnClickListener(v -> finish());
        if (!(getActivity() instanceof ChatActivity) && !(getActivity() instanceof MessengerActivity)) {
            bind.imgvMessenger.setOnClickListener(v ->
                    startActivity(new Intent(this, MessengerActivity.class)));
        }
        fb = new HmsFirebase(this, getChatNotificationHandler());
    }

    private Handler getChatNotificationHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == HmsFirebase.GET_NOT_CHECKED_CHAT_COUNT_SUCCESS) {
                    int count = (int) msg.obj;
                    if (count == 0) bind.tvNotCheckedChat.setVisibility(View.INVISIBLE);
                    else {
                        bind.tvNotCheckedChat.setVisibility(View.VISIBLE);
                        bind.tvNotCheckedChat.setText(String.valueOf(count));
                    }
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        fb.getNotCheckedChatCount();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fb.removeNotCheckedChatCountListener();
    }

    protected void addFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(id, fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    protected void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    protected abstract View getLayoutResource();

    protected abstract Activity getActivity();

}
