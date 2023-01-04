package com.example.androidhms.staff.vo;

public class ChatRoomVO {

    String roomTitle, lastChat, lastChatTime;

    public ChatRoomVO(String roomTitle, String lastChat, String lastChatTime) {
        this.roomTitle = roomTitle;
        this.lastChat = lastChat;
        this.lastChatTime = lastChatTime;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getLastChat() {
        return lastChat;
    }

    public String getLastChatTime() {
        return lastChatTime;
    }
}
