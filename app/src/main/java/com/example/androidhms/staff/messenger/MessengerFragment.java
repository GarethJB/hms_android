package com.example.androidhms.staff.messenger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentMessengerBinding;
import com.example.androidhms.databinding.FragmentMessengerStaffBinding;

public class MessengerFragment extends Fragment {

    private FragmentMessengerBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentMessengerBinding.inflate(inflater, container, false);
        return bind.getRoot();
    }
}