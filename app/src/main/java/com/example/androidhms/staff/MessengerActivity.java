package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityMessengerBinding;
import com.example.androidhms.staff.messenger.MessengerFragment;
import com.example.androidhms.staff.messenger.MessengerStaffFragment;
import com.example.androidhms.util.ActivityUtil;

public class MessengerActivity extends AppCompatActivity {

    private ActivityMessengerBinding bind;
    private MessengerFragment messengerFragment;
    private MessengerStaffFragment messengerStaffFragment;
    private ActivityUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMessengerBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        util = new ActivityUtil(this);
        messengerStaffFragment = new MessengerStaffFragment();

        util.addFragment(bind.flContainer.getId(), messengerStaffFragment);
        util.showFragment(messengerStaffFragment);

        bind.ivMessenger.setOnClickListener(onBnvClick());
        bind.ivMessengerStaff.setOnClickListener(onBnvClick());
    }

    private View.OnClickListener onBnvClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.iv_messenger_staff) {
                    if (messengerFragment != null) util.hideFragment(messengerFragment);
                    util.showFragment(messengerStaffFragment);
                    bind.ivMessengerStaff.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.text_color));
                    bind.ivMessenger.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.gray));
                } else if (v.getId() == R.id.iv_messenger) {
                    if (messengerFragment == null) {
                        messengerFragment = new MessengerFragment();
                        util.addFragment(bind.flContainer.getId(), messengerFragment);
                    }
                    util.hideFragment(messengerStaffFragment);
                    util.showFragment(messengerFragment);
                    bind.ivMessenger.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.text_color));
                    bind.ivMessengerStaff.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.gray));
                }
            }
        };
    }
}