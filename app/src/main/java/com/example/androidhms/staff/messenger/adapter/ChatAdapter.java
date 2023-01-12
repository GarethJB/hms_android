package com.example.androidhms.staff.messenger.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemMessengerSystemBinding;
import com.example.androidhms.databinding.RvChatBinding;
import com.example.androidhms.databinding.RvMychatBinding;
import com.example.androidhms.staff.messenger.ChatActivity;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.util.Util;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final ChatActivity activity;
    private final ArrayList<ChatVO> chatList;
    private final String myId;

    public ChatAdapter(ChatActivity activity, ArrayList<ChatVO> chatList, String myId) {
        this.activity = activity;
        this.chatList = chatList;
        this.myId = myId;
    }

    // viewtype 0 : 다른사람의 채팅 / 1 : 나의 채팅 / 2 : 시스템 메시지
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new ChatViewHolder(activity.getLayoutInflater().inflate(R.layout.rv_chat, parent, false));
        else if (viewType == 1)
            return new ChatViewHolder(activity.getLayoutInflater().inflate(R.layout.rv_mychat, parent, false));
        else
            return new ChatViewHolder(activity.getLayoutInflater().inflate(R.layout.item_messenger_system, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatVO vo = chatList.get(position);
        String time = Util.getTime(vo.getTime());
        // 다른사람의 채팅
        if (holder.getItemViewType() == 0 || holder.getItemViewType() == 1) {
            if (position != 0 && vo.getName().equals(chatList.get(position - 1).getName())) {
                holder.tvName.setVisibility(View.GONE);
            } else {
                holder.tvName.setVisibility(View.VISIBLE);
                holder.tvName.setText(vo.getName());
            }
            holder.tvContent.setText(vo.getContent());
            holder.tvTime.setText(time);

        }
//        // 나의 채팅
//        else if (holder.getItemViewType() == 1) {
//            if (position != 0 && vo.getName().equals(chatList.get(position - 1).getName())) {
//                holder.tvName.setVisibility(View.GONE);
//            } else {
//                holder.myBind.tvName.setVisibility(View.VISIBLE);
//                holder.myBind.tvName.setText(vo.getName());
//            }
//            holder.myBind.tvContent.setText(vo.getContent());
//            holder.myBind.tvTime.setText(time);
        else if (holder.getItemViewType() == 2) {
            holder.tvContent.setText(vo.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getId().equals("0")) return 2;
        else if (chatList.get(position).getId().equals(myId)) return 1;
        else return 0;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvTime, tvContent;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);


        }
    }

}
