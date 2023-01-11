package com.example.androidhms.staff.messenger.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.RvChatroomBinding;
import com.example.androidhms.staff.messenger.MessengerFragment;
import com.example.androidhms.staff.vo.ChatRoomVO;
import com.example.androidhms.util.Util;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder> {

    private ArrayList<ChatRoomVO> chatRoomList;
    private MessengerFragment fragment;
    private String name;

    public ChatRoomAdapter(MessengerFragment fragment, ArrayList<ChatRoomVO> chatRoomList, String name) {
        this.chatRoomList = chatRoomList;
        this.fragment = fragment;
        this.name = name;
    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatRoomViewHolder(fragment.getLayoutInflater().inflate(R.layout.rv_chatroom, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position) {
        ChatRoomVO vo = chatRoomList.get(position);
        String title = vo.getRoomTitle();
        if (title.contains("#")) {
            String titleRv = title.replace("#", "");
            titleRv = titleRv.replaceAll(name, "");
            holder.bind.tvTitle.setText(titleRv);
        } else holder.bind.tvTitle.setText(title);
        holder.bind.tvLastchat.setText(vo.getLastChat());
        holder.bind.tvTime.setText(Util.getTime(vo.getLastChatTime()));
        if (vo.getCount().equals("0")) holder.bind.tvCount.setVisibility(View.GONE);
        else holder.bind.tvCount.setText(vo.getCount());
        holder.itemView.setOnClickListener(v -> fragment.getChatRoomClick(vo.getKey(), title));
    }

    @Override
    public int getItemCount() {
        return chatRoomList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ChatRoomViewHolder extends RecyclerView.ViewHolder {

        public RvChatroomBinding bind;

        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = RvChatroomBinding.bind(itemView);
        }
    }
}
