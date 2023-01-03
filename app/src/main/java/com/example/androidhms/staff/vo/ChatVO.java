package com.example.androidhms.staff.vo;

import com.example.androidhms.util.Util;

public class ChatVO {

    private String name, content, time;

    public ChatVO(String name, String content) {
        this.name = name;
        this.content = content;
        this.time = Util.getChatTimeStamp();
    }

    public ChatVO(String name, String content, String time) {
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

}
