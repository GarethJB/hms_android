package com.example.androidhms.staff.messenger.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.RvMessengerStaffBinding;
import com.example.androidhms.staff.messenger.MessengerStaffFragment;
import com.example.androidhms.staff.vo.StaffVO;

import java.util.ArrayList;

public class MessengerStaffAdapter extends RecyclerView.Adapter<MessengerStaffAdapter.MessengerStaffViewHolder> {

    private MessengerStaffFragment fragment;
    private ArrayList<StaffVO> staffList;

    public MessengerStaffAdapter(MessengerStaffFragment fragment, ArrayList<StaffVO> staffList) {
        this.fragment = fragment;
        this.staffList = staffList;
    }

    @NonNull
    @Override
    public MessengerStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessengerStaffViewHolder(
                fragment.getLayoutInflater().inflate(R.layout.rv_messenger_staff, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessengerStaffViewHolder holder, int position) {
        holder.bind.tvStaffname.setText(staffList.get(position).getName());
        holder.bind.tvStaffdepart.setText(String.valueOf(staffList.get(position).getDepartment_id()));
        holder.bind.rlGetchat.setOnClickListener(fragment.onGetChatClick(position));
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class MessengerStaffViewHolder extends RecyclerView.ViewHolder {

        public RvMessengerStaffBinding bind;

        public MessengerStaffViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = RvMessengerStaffBinding.bind(itemView);

        }
    }
}
