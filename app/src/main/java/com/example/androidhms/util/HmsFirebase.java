package com.example.androidhms.util;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidhms.staff.vo.ChatRoomVO;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class HmsFirebase {

    public static final int GET_CHATROOM_SUCCESS = 1;
    public static final int GET_CHAT_SUCCESS = 2;
    public static final int GET_CHATROOM_LIST_SUCCESS = 3;
    public static final int GET_CHAT_MEMBER_SUCCESS = 4;
    public static final int GET_NOT_CHECKED_CHAT_COUNT_SUCCESS = 5;
    private static final String RB_URL = "https://hmsmessenger-3a156-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private static final FirebaseDatabase fbDb = FirebaseDatabase.getInstance(RB_URL);
    private static final DatabaseReference dbRef = fbDb.getReference();
    private final Handler handler;
    private ValueEventListener notCheckedChatCountListener, getChatListener, getChatRoomListener;

    public HmsFirebase(Context context, Handler handler) {
        FirebaseApp.initializeApp(context);
        this.handler = handler;
    }

    /**
     * 아직 읽지않은 채팅 수 불러오기
     */
    public void getNotCheckedChatCount() {
        notCheckedChatCountListener = getNotCheckedChatCountListener();
        dbRef.child("notCheckedChat")
                .child(String.valueOf(Util.staff.getStaff_id()))
                .child("count")
                .addValueEventListener(getNotCheckedChatCountListener());
    }

    private ValueEventListener getNotCheckedChatCountListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                handler.sendMessage(handler.obtainMessage(
                        GET_NOT_CHECKED_CHAT_COUNT_SUCCESS, snapshot.getValue(Integer.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void removeNotCheckedChatCountListener() {
        dbRef.child("notCheckedChat")
                .child(String.valueOf(Util.staff.getStaff_id()))
                .child("count")
                .removeEventListener(notCheckedChatCountListener);
    }

    /**
     * MessengerStaffFragment 채팅방 입장
     */
    public void getChatRoom(ArrayList<StaffChatDTO> staffList) {
        dbRef.child("chatRoom").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 1:1 채팅방의 키값 (staff_id) -- (staff_id)
                String key;
                if (staffList.get(0).getStaff_id() < staffList.get(1).getStaff_id()) {
                    key = staffList.get(0).getStaff_id() + "--" + staffList.get(1).getStaff_id();
                } else key = staffList.get(1).getStaff_id() + "--" + staffList.get(0).getStaff_id();
                dbRef.child("chatRoom").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // 채팅방이 존재하지 않을경우 채팅방을 만들고 함수 재호출
                        if (!snapshot.exists()) {
                            HashMap<String, Object> map = new HashMap<>();
                            HashMap<String, StaffChatDTO> memberMap = new HashMap<>();
                            for (StaffChatDTO dto : staffList) {
                                memberMap.put(String.valueOf(dto.getStaff_id()), dto);
                            }
                            ArrayList<ChatVO> chatList = new ArrayList<>();
                            chatList.add(new ChatVO("0", "chatStart", "chatStart", "1999-01-01 00:00:00"));
                            map.put("member", memberMap);
                            map.put("chat", chatList);
                            map.put("chatRoomTitle", "#" + staffList.get(0).getName() + staffList.get(1).getName());
                            dbRef.child("chatRoom").child(key).setValue(map)
                                    .addOnSuccessListener(unused -> handler.sendMessage(handler.obtainMessage(GET_CHATROOM_SUCCESS, null)));
                        } else {
                            handler.sendMessage(handler.obtainMessage(GET_CHATROOM_SUCCESS, key));
                        }
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

    /**
     * MessengerFragment 채팅방 목록 불러오기
     */
    public void getChatRoom(int id) {
        getChatRoomListener = GetChatRoomListener(id);
        dbRef.child("chatRoom").addValueEventListener(getChatRoomListener);
    }

    private ValueEventListener GetChatRoomListener(int id) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ChatRoomVO> chatRoomList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    for (DataSnapshot member : data.child("member").getChildren()) {
                        if (id == member.child("staff_id").getValue(Long.class)) {
                            Timestamp lastCheckTime;
                            int count = 0;
                            // 한번도 채팅방에 접속하지 않았을경우
                            if (member.child("lastChatCheckTime").getValue(String.class) == null) {
                                lastCheckTime = Timestamp.valueOf("2000-01-01 00:00:00");
                            } else
                                lastCheckTime = Timestamp.valueOf(member.child("lastChatCheckTime").getValue(String.class));
                            for (DataSnapshot chat : data.child("chat").getChildren()) {
                                if (lastCheckTime.compareTo(Timestamp.valueOf(chat.child("time").getValue(String.class))) < 0) {
                                    count++;
                                }
                            }
                            if (data.child("lastChat").child("content").getValue(String.class) != null) {
                                chatRoomList.add(new ChatRoomVO(
                                        data.getKey(),
                                        data.child("chatRoomTitle").getValue(String.class),
                                        data.child("lastChat").child("content").getValue(String.class),
                                        data.child("lastChat").child("time").getValue(String.class),
                                        String.valueOf(count)
                                ));
                            }
                        }
                    }
                }
                if (chatRoomList.size() == 0)
                    handler.sendMessage(handler.obtainMessage(GET_CHATROOM_LIST_SUCCESS, null));
                else
                    handler.sendMessage(handler.obtainMessage(GET_CHATROOM_LIST_SUCCESS, chatRoomList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void removeGetChatRoomListener() {
        dbRef.child("chatRoom").removeEventListener(getChatRoomListener);
    }

    /**
     * 채팅방 입장시 읽지 않은 채팅 갯수 차감
     */
    public void updateNotCheckedChatCount(int id, int count) {
        dbRef.child("notCheckedChat").child(String.valueOf(id)).child("count")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbRef.child("notCheckedChat").child(String.valueOf(id)).child("count")
                                .setValue(snapshot.getValue(Integer.class) - count);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    /**
     * 채팅방 입장/퇴장시 접속 상태를 변경
     */
    public void setOnChat(String key, boolean onChat) {
        dbRef.child("chatRoom").child(key).child("member")
                .child(String.valueOf(Util.staff.getStaff_id()))
                .child("onChat").setValue(onChat);
    }

    /**
     * 채팅방 멤버 불러오기
     */
    public void getChatMember(String key) {
        dbRef.child("chatRoom").child(key).child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<StaffChatDTO> staffList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    staffList.add(data.getValue(StaffChatDTO.class));
                }
                handler.sendMessage(handler.obtainMessage(GET_CHAT_MEMBER_SUCCESS, staffList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * 채팅 내역을 불러옴
     */
    public void getChat(String key) {
        getChatListener = GetChatListener(key);
        dbRef.child("chatRoom").child(key).child("chat").addValueEventListener(getChatListener);
    }

    private ValueEventListener GetChatListener(String key) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ChatVO> chatList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    chatList.add(child.getValue(ChatVO.class));
                }
                handler.sendMessage(handler.obtainMessage(GET_CHAT_SUCCESS, chatList));
                dbRef.child("chatRoom").child(key).child("member")
                        .child(String.valueOf(Util.staff.getStaff_id()))
                        .child("lastChatCheckTime").setValue(Util.getChatTimeStamp());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
    }

    public void removeChatListener(String key) {
        dbRef.child("chatRoom").child(key).child("chat").removeEventListener(getChatListener);
    }

    /**
     * 채팅 전송
     */
    public void sendChat(String key, ChatVO vo, ArrayList<StaffChatDTO> staffList) {
        dbRef.child("chatRoom").child(key).child("chat").push().setValue(vo);
        dbRef.child("chatRoom").child(key).child("lastChat").setValue(vo);
        for (StaffChatDTO dto : staffList) {
            if (dto.getStaff_id() != Util.staff.getStaff_id()) {
                dbRef.child("notCheckedChat").child(String.valueOf(dto.getStaff_id())).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbRef.child("notCheckedChat").child(String.valueOf(dto.getStaff_id()))
                                .child("count").setValue(snapshot.getValue(Integer.class) + 1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dbRef.child("notCheckedChat").child(String.valueOf(dto.getStaff_id()))
                        .child("lastChat").setValue(vo);
            }
        }
    }

}
