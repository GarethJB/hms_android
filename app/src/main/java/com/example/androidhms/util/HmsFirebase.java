package com.example.androidhms.util;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.androidhms.staff.vo.ChatRoomVO;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.google.android.gms.tasks.OnSuccessListener;
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
    public static final int GET_CHATROOM_LIST_SUCCESS = 3;
    public static final int GET_CHAT_MEMBER_SUCCESS = 4;
    public static final int GET_NOT_CHECKED_CHAT_COUNT_SUCCESS = 5;
    public static final int GET_NOTIFICATION_SUCCESS = 6;
    public static final int GET_NOTIFICATION_CHATROOM_SUCCESS = 7;
    private static final String RB_URL = "https://hmsmessenger-3a156-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private static final FirebaseDatabase fbDb = FirebaseDatabase.getInstance(RB_URL);
    private static final DatabaseReference dbRef = fbDb.getReference();
    private final DatabaseReference chatRoom;
    private final DatabaseReference member;
    private final String myId = String.valueOf(Util.staff.getStaff_id());
    private final Handler handler;
    private ValueEventListener notCheckedChatCountListener, getChatListener, getChatRoomListener, getNotificationListener;

    public HmsFirebase(Context context, Handler handler) {
        FirebaseApp.initializeApp(context);
        this.handler = handler;
        chatRoom = dbRef.child("chatRoom");
        member = dbRef.child("member");
    }

    /**
     * 아직 읽지않은 채팅 수 불러오기
     */
    public void getNotCheckedChatCount() {
        notCheckedChatCountListener = getNotCheckedChatCountListener();
        member.child(myId).child("count")
                .addValueEventListener(getNotCheckedChatCountListener());
    }

    private ValueEventListener getNotCheckedChatCountListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (DataSnapshot child : snapshot.getChildren()) {
                    count += child.getValue(Integer.class);
                }
                handler.sendMessage(handler.obtainMessage(
                        GET_NOT_CHECKED_CHAT_COUNT_SUCCESS, count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void removeNotCheckedChatCountListener() {
        member.child(myId).child("count").removeEventListener(notCheckedChatCountListener);
    }

    /**
     * MessengerStaffFragment 채팅방 입장
     */
    public void makeChatRoom(ArrayList<StaffChatDTO> staffList) {
        chatRoom.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 1:1 채팅방의 키값 (staff_id) -- (staff_id)
                String key;
                if (staffList.get(0).getStaff_id() < staffList.get(1).getStaff_id()) {
                    key = staffList.get(0).getStaff_id() + "--" + staffList.get(1).getStaff_id();
                } else key = staffList.get(1).getStaff_id() + "--" + staffList.get(0).getStaff_id();
                chatRoom.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                            map.put("member", memberMap);
                            map.put("chat", chatList);
                            map.put("chatRoomTitle", "#" + staffList.get(0).getName() + staffList.get(1).getName());
                            chatRoom.child(key).setValue(map)
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
    public void getChatRoom() {
        getChatRoomListener = getChatRoomListener();
        member.child(myId).child("lastChat").addValueEventListener(getChatRoomListener);
    }

    private ValueEventListener getChatRoomListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                member.child(myId).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<ChatRoomVO> chatRoomList = new ArrayList<>();
                        if (!snapshot.exists()) {
                            handler.sendMessage(handler.obtainMessage(GET_CHATROOM_LIST_SUCCESS, null));
                        } else {
                            final int[] childrenCount = {1};
                            for (DataSnapshot count : snapshot.getChildren()) {
                                chatRoom.child(count.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot chatRoom) {
                                        if (!chatRoom.child("lastChat").exists()) {
                                            chatRoomList.add(new ChatRoomVO(
                                                    chatRoom.getKey(),
                                                    chatRoom.child("chatRoomTitle").getValue(String.class),
                                                    "", "2000-01-01 00:00:00.000",
                                                    String.valueOf(count.getValue(Integer.class))
                                            ));
                                        } else {
                                            chatRoomList.add(new ChatRoomVO(
                                                    chatRoom.getKey(),
                                                    chatRoom.child("chatRoomTitle").getValue(String.class),
                                                    chatRoom.child("lastChat").child("content").getValue(String.class),
                                                    chatRoom.child("lastChat").child("time").getValue(String.class),
                                                    String.valueOf(count.getValue(Integer.class))
                                            ));
                                        }
                                        if (childrenCount[0]++ == snapshot.getChildrenCount()) {
                                            handler.sendMessage(handler.obtainMessage(GET_CHATROOM_LIST_SUCCESS, chatRoomList));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
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
        };
    }

    public void removeGetChatRoomListener() {
        member.child(myId).child("lastChat").removeEventListener(getChatRoomListener);
    }

    /**
     * 채팅방 입장/퇴장시 접속 상태를 변경
     */
    public void setOnChat(String key, boolean onChat) {
        chatRoom.child(key).child("member")
                .child(myId).child("onChat").setValue(onChat);
    }

    /**
     * 채팅방 멤버 불러오기
     */
    public void getChatMember(String key) {
        chatRoom.child(key).child("member").addListenerForSingleValueEvent(new ValueEventListener() {
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
        chatRoom.child(key).child("chat").addValueEventListener(getChatListener);
    }

    private ValueEventListener GetChatListener(String key) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ChatVO> chatList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    chatList.add(child.getValue(ChatVO.class));
                }
                member.child(myId).child("count").child(key).setValue(0);
                chatRoom.child(key).child("member")
                        .child(myId)
                        .child("lastChatCheckTime").setValue(Util.getChatTimeStamp());
                handler.sendMessage(handler.obtainMessage(GET_CHAT_SUCCESS, chatList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
    }

    public void removeChatListener(String key) {
        chatRoom.child(key).child("chat").removeEventListener(getChatListener);
        chatRoom.child(key).child("member")
                .child(myId)
                .child("lastChatCheckTime").setValue(Util.getChatTimeStamp());
    }

    /**
     * 채팅 전송
     */
    public void sendChat(String key, String title, ChatVO vo) {
        chatRoom.child(key).child("chat").push().setValue(vo);
        chatRoom.child(key).child("lastChat").setValue(vo);
        chatRoom.child(key).child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (!child.getKey().equals(myId) && !child.child("onChat").getValue(Boolean.class)) {
                        member.child(child.getKey()).child("count")
                                .child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            member.child(String.valueOf(child.getKey()))
                                                    .child("count").child(key).setValue(snapshot.getValue(Integer.class) + 1);
                                        } else
                                            member.child(String.valueOf(child.getKey()))
                                                    .child("count").child(key).setValue(1);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        member.child(String.valueOf(child.getKey())).child("lastChatRoom")
                                .setValue(key + "##" + title).addOnSuccessListener(unused -> {
                                    member.child(String.valueOf(child.getKey())).child("lastChat").setValue(vo);
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * 채팅 알림
     */
    public void getNotification() {
        getNotificationListener = getNotificationListener();
        member.child(myId).child("lastChat").addValueEventListener(getNotificationListener);
    }

    private ValueEventListener getNotificationListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    handler.sendMessage(handler.obtainMessage(GET_NOTIFICATION_SUCCESS, snapshot.getValue(ChatVO.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void getNotificationChatroom() {
        member.child(myId).child("lastChatRoom").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    handler.sendMessage(handler.obtainMessage(GET_NOTIFICATION_CHATROOM_SUCCESS, snapshot.getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void removeNotificationListener() {
        member.child(myId).child("lastChat").removeEventListener(getNotificationListener);
    }


}
