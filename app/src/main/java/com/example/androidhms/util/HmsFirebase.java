package com.example.androidhms.util;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.androidhms.staff.messenger.ChatVO;
import com.example.androidhms.staff.messenger.StaffVO;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HmsFirebase {

    public static final int GET_CHATROOM_SUCCESS = 1;
    public static final int GET_CHAT_SUCCESS = 2;
    private static final String RB_URL = "https://hmsmessenger-3a156-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private final DatabaseReference dbRef;
    private final Handler handler;

    public HmsFirebase(Context context, Handler handler) {
        FirebaseApp.initializeApp(context);
        FirebaseDatabase fbDb = FirebaseDatabase.getInstance(RB_URL);
        dbRef = fbDb.getReference();
        this.handler = handler;
    }

    public void getChatRoom(ArrayList<StaffVO> staffList) {
        dbRef.child("chatRoom").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = staffList.get(0).getStaff_id() + "0000" + staffList.get(1).getStaff_id();
                dbRef.child("chatRoom").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            HashMap<String, Object> map = new HashMap<>();
                            ArrayList<ChatVO> chatList = new ArrayList<>();
                            chatList.add(new ChatVO("chatStart", "chatStart"));
                            map.put("member", staffList);
                            map.put("chat", chatList);
                            dbRef.child("chatRoom").child(key).setValue(map)
                                    .addOnSuccessListener(unused -> handler.sendMessage(handler.obtainMessage(1, null)));
                        } else handler.sendMessage(handler.obtainMessage(GET_CHATROOM_SUCCESS, key));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void sendChat(String key, ChatVO vo) {
        dbRef.child("chatRoom").child(key).child("chat").push().setValue(vo);
    }

    public void getChat(String key) {
        dbRef.child("chatRoom").child(key).child("chat").addValueEventListener(getChatListener);
    }

    private ValueEventListener getChatListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<ChatVO> chatList = new ArrayList<>();
            for (DataSnapshot child : snapshot.getChildren()) {
                chatList.add(
                        new ChatVO(child.child("name").getValue(String.class),
                                child.child("content").getValue(String.class),
                                child.child("time").getValue(String.class)
                        ));
            }
            handler.sendMessage(handler.obtainMessage(GET_CHAT_SUCCESS, chatList));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


}
