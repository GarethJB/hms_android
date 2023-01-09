package com.example.androidhms.staff.messenger;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.androidhms.databinding.ActivityChatBinding;
import com.example.androidhms.staff.StaffBaseActivity;
import com.example.androidhms.staff.messenger.adapter.ChatAdapter;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;

import java.util.ArrayList;

public class ChatActivity extends StaffBaseActivity {

    private ActivityChatBinding bind;
    private HmsFirebase fb;
    private String key;
    private final StaffChatDTO staff = Util.getStaffChatDTO();
    private ArrayList<ChatVO> chatList;
    private ArrayList<StaffChatDTO> staffList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fb = new HmsFirebase(this, firebaseHandler());
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        key = intent.getStringExtra("key");

        bind.tvChatroom.setText(name);
        bind.btSend.setOnClickListener(onSendClick());
        bind.etContent.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) new Handler().postDelayed(
                    () -> bind.rvChat.scrollToPosition(chatList.size() - 1), 200);
        });
        bind.etContent.setOnClickListener(v ->
                new Handler().postDelayed(() ->
                        bind.rvChat.scrollToPosition(chatList.size() - 1), 200));
        fb.getChat(key);
        fb.getChatMember(key);
        setContentView(bind.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        fb.setOnChat(key, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fb.setOnChat(key, false);
    }

    @Override
    protected View getLayoutResource() {
        bind = ActivityChatBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    private Handler firebaseHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == HmsFirebase.GET_CHAT_SUCCESS) {
                    chatList = (ArrayList<ChatVO>) msg.obj;
                    Util.setRecyclerView(ChatActivity.this, bind.rvChat,
                            new ChatAdapter(ChatActivity.this, chatList, String.valueOf(staff.getStaff_id())), true);
                    bind.rvChat.scrollToPosition(chatList.size() - 1);
                } else if (msg.what == HmsFirebase.GET_CHAT_MEMBER_SUCCESS) {
                    staffList = (ArrayList<StaffChatDTO>) msg.obj;
                }
            }
        };
    }

    private View.OnClickListener onSendClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staff.setLastChatCheckTime();
                fb.sendChat(key, new ChatVO(String.valueOf(staff.getStaff_id()), staff.getName(), bind.etContent.getText().toString()), staffList);
                bind.etContent.setText("");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fb.removeChatListener(key);
    }
}