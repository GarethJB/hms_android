package com.example.androidhms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidhms.databinding.ActivityMainBinding;
import com.example.androidhms.databinding.FragmentAppointBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {

    TextView tv_news, tv_valunteer, tv_notice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tv_news= v.findViewById(R.id.tv_news);
        tv_valunteer= v.findViewById(R.id.tv_valunteer);
        tv_notice= v.findViewById(R.id.tv_notice);

        tv_news.setOnClickListener(this);
        tv_valunteer.setOnClickListener(this);
        tv_notice.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_news){

        }else if(v.getId() == R.id.tv_valunteer){

        }else if(v.getId() == R.id.tv_notice ){

        }
    }

    public void conenct(Activity activity){

    }
}