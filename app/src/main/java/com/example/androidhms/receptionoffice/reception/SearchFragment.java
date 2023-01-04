package com.example.androidhms.receptionoffice.reception;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.androidhms.R;

public class SearchFragment extends Fragment {

    ImageButton btn_search;
    LinearLayout ll_info;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_reception_search, container, false);
        ll_info = v.findViewById(R.id.ll_info);
        btn_search= v.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ll_info.setVisibility(View.VISIBLE);
            }
        });
        return v;
    }
}