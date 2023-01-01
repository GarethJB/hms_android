package com.example.androidhms.staff.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityChatBinding;
import com.example.androidhms.staff.messenger.adapter.ChatRoomAdapter;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding bind;
    private HmsFirebase fb;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityChatBinding.inflate(getLayoutInflater());
        fb = new HmsFirebase(this, firebaseHandler());
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        key = intent.getStringExtra("key");

        bind.tvChatroom.setText(name);
        bind.btSend.setOnClickListener(onSendClick());
        fb.getChat(key);
        setContentView(bind.getRoot());
    }

    private Handler firebaseHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == HmsFirebase.GET_CHAT_SUCCESS) {
                    ArrayList<ChatVO> chatList = (ArrayList<ChatVO>) msg.obj;
                    Util.setRecyclerView(ChatActivity.this, bind.rvChat,
                            new ChatRoomAdapter(ChatActivity.this, chatList, "간호사"), true);
                }
            }
        };
    }

    private View.OnClickListener onSendClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.sendChat(key, new ChatVO("간호사", bind.etContent.getText().toString()));
            }
        };
    }
}