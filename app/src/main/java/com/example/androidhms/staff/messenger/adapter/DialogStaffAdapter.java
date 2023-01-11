package com.example.androidhms.staff.messenger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemMessengerCreateGroupBinding;
import com.example.androidhms.staff.messenger.CreateGroupDialog;
import com.example.androidhms.staff.vo.StaffChatDTO;

import java.util.ArrayList;

public class DialogStaffAdapter extends RecyclerView.Adapter<DialogStaffAdapter.DialogStaffViewHolder> {

    private ArrayList<StaffChatDTO> staffList;
    private CreateGroupDialog dialog;
    private LayoutInflater inflater;

    public DialogStaffAdapter(ArrayList<StaffChatDTO> staffList, CreateGroupDialog dialog, LayoutInflater inflater) {
        this.staffList = staffList;
        this.dialog = dialog;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public DialogStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DialogStaffViewHolder(inflater.inflate(R.layout.item_messenger_create_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DialogStaffViewHolder holder, int position) {
        StaffChatDTO vo = staffList.get(position);
        holder.bind.tvStaffname.setText(vo.getName());
        String department = vo.getDepartment_name();
        if (vo.getStaff_level() == 1) department += " 의사";
        else department += " 간호사";
        holder.bind.tvStaffdepart.setText(department);
        holder.bind.cbAdd.setOnCheckedChangeListener((buttonView, isChecked) -> {
            dialog.onSelectStaff(position, isChecked);
        });
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

    public class DialogStaffViewHolder extends RecyclerView.ViewHolder {

        public ItemMessengerCreateGroupBinding bind;

        public DialogStaffViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = ItemMessengerCreateGroupBinding.bind(itemView);
        }
    }
}
