package com.example.androidhms.staff.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityMessengerBinding;
import com.example.androidhms.staff.StaffBaseActivity;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;

public class MessengerActivity extends StaffBaseActivity {

    private ActivityMessengerBinding bind;
    private MessengerFragment messengerFragment;
    private MessengerStaffFragment messengerStaffFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        messengerStaffFragment = new MessengerStaffFragment();
        addFragment(bind.flContainer.getId(), messengerStaffFragment);
        showFragment(messengerStaffFragment);

        bind.ivMessenger.setOnClickListener(onBnvClick());
        bind.ivMessengerStaff.setOnClickListener(onBnvClick());

        if (getIntent().getBooleanExtra("toolbar", false)) {
            bind.ivMessenger.performClick();
        }
    }

    @Override
    protected View getLayoutResource() {
        bind = ActivityMessengerBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    private View.OnClickListener onBnvClick() {
        return v -> {
            if (v.getId() == R.id.iv_messenger_staff) {
                if (messengerFragment != null) hideFragment(messengerFragment);
                showFragment(messengerStaffFragment);
                bind.ivMessengerStaff.setColorFilter(
                        ContextCompat.getColor(MessengerActivity.this, R.color.text_color));
                bind.ivMessenger.setColorFilter(
                        ContextCompat.getColor(MessengerActivity.this, R.color.gray));
            } else if (v.getId() == R.id.iv_messenger) {
                if (messengerFragment == null) {
                    messengerFragment = new MessengerFragment();
                    addFragment(bind.flContainer.getId(), messengerFragment);
                }
                hideFragment(messengerStaffFragment);
                showFragment(messengerFragment);
                bind.ivMessenger.setColorFilter(
                        ContextCompat.getColor(MessengerActivity.this, R.color.text_color));
                bind.ivMessengerStaff.setColorFilter(
                        ContextCompat.getColor(MessengerActivity.this, R.color.gray));
            }
        };
    }
}