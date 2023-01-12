package com.example.androidhms.staff.messenger.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.androidhms.databinding.DialogChatAddMemberBinding;
import com.example.androidhms.staff.messenger.adapter.DialogStaffAdapter;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.example.androidhms.util.Util;

import java.util.ArrayList;

public class AddMemberDialog {

    private final DialogChatAddMemberBinding b;
    private final Dialog dialog;
    private final ArrayList<StaffChatDTO> allStaffList;
    private final ArrayList<StaffChatDTO> memberStaffList;

    public AddMemberDialog(Context context, ArrayList<StaffChatDTO> allStaffList, LayoutInflater inflater, OnDialogBtnClickListener listener) {
        dialog = new Dialog(context);
        b = DialogChatAddMemberBinding.inflate(inflater);
        dialog.setContentView(b.getRoot());
        this.allStaffList = allStaffList;
        memberStaffList = new ArrayList<>();
        setTextMemberList();

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.horizontalMargin = 100;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setDimAmount(0.3f);

        b.imgvExit.setOnClickListener(v -> dialog.dismiss());
        b.btnCreate.setOnClickListener(v -> listener.onCreateClick(this, memberStaffList));

        Util.setRecyclerView(context, b.rvStaffList, new DialogStaffAdapter(allStaffList, this, inflater), true);
    }

    public void onSelectStaff(int position, boolean checked) {
        if (checked) memberStaffList.add(allStaffList.get(position));
        else memberStaffList.remove(allStaffList.get(position));
        setTextMemberList();
    }

    private void setTextMemberList() {
        StringBuilder member = new StringBuilder();
        for (int i = 0; i < memberStaffList.size(); i++) {
            member.append(memberStaffList.get(i).getName());
            if (i != memberStaffList.size() - 1) member.append(" / ");
        }
        b.tvMemberList.setText(member);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void showProgress() {
        b.rlProgress.view.setVisibility(View.VISIBLE);
    }

    public interface OnDialogBtnClickListener {
        void onCreateClick(AddMemberDialog dialog, ArrayList<StaffChatDTO> memberStaffList);
    }
}
