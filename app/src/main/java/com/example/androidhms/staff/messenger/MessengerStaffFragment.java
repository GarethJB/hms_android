package com.example.androidhms.staff.messenger;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.databinding.FragmentMessengerStaffBinding;
import com.example.androidhms.staff.messenger.adapter.MessengerStaffAdapter;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MessengerStaffFragment extends Fragment {

    private FragmentMessengerStaffBinding bind;
    private ArrayList<StaffVO> staffList;
    private ArrayList<StaffVO> chatMemberList;
    private HmsFirebase fb;
    private StaffVO staff = Util.staff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentMessengerStaffBinding.inflate(inflater, container, false);
        fb = new HmsFirebase(this.getContext(), firebaseHandler());

        bind.tvName.setText(staff.getName());
        new RetrofitMethod().sendPost("getstaff.ap", new RetrofitMethod.CallBackResult() {
            @Override
            public void result(boolean isResult, String data) {
                if (isResult) {
                    staffList = new Gson().fromJson(data, new TypeToken<ArrayList<StaffVO>>(){}.getType());
                    // 자기 자신은 채팅 상대방에서 제외
                    for (int i = 0; i < staffList.size(); i++) {
                        if (staffList.get(i).getStaff_id() == staff.getStaff_id()) {
                            staffList.remove(i);
                            break;
                        }
                    }
                    Util.setRecyclerView(getContext(), bind.rvMessengerStaff,
                            new MessengerStaffAdapter(MessengerStaffFragment.this, staffList), true);
                    bind.rvMessengerStaff.post(() -> {
                        bind.rlProgress.setVisibility(View.GONE);
                    });
                }
            }
        });

        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

    public View.OnClickListener onGetChatClick(int position) {
        return v -> {
            chatMemberList = new ArrayList<>();
            staff.setLastChatCheckTime();
            chatMemberList.add(staff);
            chatMemberList.add(staffList.get(position));
            fb.getChatRoom(chatMemberList);
        };
    }

    private Handler firebaseHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == HmsFirebase.GET_CHATROOM_SUCCESS) {
                    if (msg.obj != null) {
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra("name", chatMemberList.get(1).getName());
                        intent.putExtra("key", msg.obj.toString());
                        startActivity(intent);
                    } else fb.getChatRoom(chatMemberList);
                }
            }
        };
    }
}