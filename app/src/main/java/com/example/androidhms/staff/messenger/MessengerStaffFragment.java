package com.example.androidhms.staff.messenger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentMessengerStaffBinding;
import com.example.androidhms.staff.messenger.adapter.MessengerStaffAdapter;
import com.example.androidhms.staff.vo.StaffChatDTO;
import com.example.androidhms.util.HmsFirebase;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MessengerStaffFragment extends Fragment {

    private FragmentMessengerStaffBinding bind;
    private ArrayList<StaffChatDTO> staffList;
    private ArrayList<StaffChatDTO> chatMemberList;
    private HmsFirebase fb;
    private final StaffChatDTO staff = Util.getStaffChatDTO();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentMessengerStaffBinding.inflate(inflater, container, false);
        fb = new HmsFirebase(this.getContext(), firebaseHandler());

        bind.tvName.setText(staff.getName());
        new RetrofitMethod().sendGet("getStaff.ap", (isResult, data) -> {
            if (isResult) {
                staffList = new Gson().fromJson(data, new TypeToken<ArrayList<StaffChatDTO>>() {
                }.getType());
                // 자기 자신은 채팅 상대방에서 제외
                for (int i = 0; i < staffList.size(); i++) {
                    if (staffList.get(i).getStaff_id() == staff.getStaff_id()) {
                        staffList.remove(i);
                        break;
                    }
                }
                setSpinner();
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
            fb.makeChatRoom(chatMemberList);
        };
    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.department, android.R.layout.simple_spinner_dropdown_item);
        bind.spDepartment.setAdapter(adapter);
        bind.spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bind.rlProgress.view.setVisibility(View.VISIBLE);
                String selected = (String) bind.spDepartment.getSelectedItem();
                if (position != 0) {
                    ArrayList<StaffChatDTO> tempList = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tempList = (ArrayList<StaffChatDTO>) staffList.stream()
                                .filter(d -> {
                                    if (selected.equals("의사")) return d.getStaff_level() == 1;
                                    else if (selected.equals("간호사")) return d.getStaff_level() == 2;
                                    else return d.getDepartment_name().equals(selected);
                                })
                                .collect(Collectors.toList());
                    }
                    Util.setRecyclerView(getContext(), bind.rvMessengerStaff,
                            new MessengerStaffAdapter(MessengerStaffFragment.this, tempList), true);
                } else {
                    Util.setRecyclerView(getContext(), bind.rvMessengerStaff,
                            new MessengerStaffAdapter(MessengerStaffFragment.this, staffList), true);
                }
                bind.rlProgress.view.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Handler firebaseHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == HmsFirebase.GET_CHATROOM_SUCCESS) {
                    if (msg.obj != null) {
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra("title", chatMemberList.get(1).getName());
                        intent.putExtra("key", msg.obj.toString());
                        startActivity(intent);
                    } else fb.makeChatRoom(chatMemberList);
                }
            }
        };
    }
}