package com.example.androidhms.staff.messenger;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityChatBinding;
import com.example.androidhms.databinding.NavChatHeaderBinding;
import com.example.androidhms.staff.StaffBaseActivity;
import com.example.androidhms.staff.messenger.adapter.ChatAdapter;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;
import com.google.android.material.navigation.NavigationView;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatActivity extends StaffBaseActivity {

    private ActivityChatBinding bind;
    private NavChatHeaderBinding nBind;
    private HmsFirebase fb;
    private String key;
    private String title;
    private StaffChatDTO staff;
    private ArrayList<ChatVO> chatList;
    private ArrayList<StaffChatDTO> staffList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fb = new HmsFirebase(this, firebaseHandler());
        staff = Util.getStaffChatDTO(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        key = intent.getStringExtra("key");

        bind.toolbar.imgvMessenger.setImageResource(R.drawable.icon_menu);
        bind.toolbar.imgvMessenger.setOnClickListener(v -> bind.view.openDrawer(GravityCompat.END));

        if (title.contains("#")) {
            String titleView = title.replace("#", "");
            titleView = titleView.replaceAll(staff.getName(), "");
            bind.tvChatroom.setText(titleView);
        } else bind.tvChatroom.setText(title);

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

    private void setNavigationView() {
        nBind = NavChatHeaderBinding.bind(bind.navMember.getHeaderView(0));
        nBind.tvName.setText(staff.getName());
        nBind.tvDepartment.setText(getDepartment(staff));

        Menu menu = bind.navMember.getMenu();
        Menu subMenu = menu.addSubMenu("참여자 (" + staffList.size() + ")");
        for (StaffChatDTO dto : staffList) {
            subMenu.add(dto.getName() + " / " + getDepartment(dto));
        }
    }

    private String getDepartment(StaffChatDTO dto) {
        String department = dto.getDepartment_name();
        if (dto.getStaff_level() == 1) department += " 의사";
        else department += " 간호사";
        return department;
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
                    setNavigationView();
                }
            }
        };
    }

    private View.OnClickListener onSendClick() {
        return v -> {
            if (bind.etContent.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "채팅을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            } else if (chatList.isEmpty() || !Util.getDate(Timestamp.valueOf(chatList.get(chatList.size() - 1).getTime()))
                    .equals(Util.getDate(new Timestamp(System.currentTimeMillis())))) {
                fb.sendDateBeforeSendChat(key, title,
                        new ChatVO(String.valueOf(staff.getStaff_id()), staff.getName(), bind.etContent.getText().toString())
                        , new Timestamp(System.currentTimeMillis()));
                bind.etContent.setText("");
            } else {
                fb.sendChat(key, title, new ChatVO(String.valueOf(staff.getStaff_id()), staff.getName(), bind.etContent.getText().toString()));
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