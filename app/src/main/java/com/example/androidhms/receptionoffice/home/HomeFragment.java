package com.example.androidhms.receptionoffice.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements View.OnClickListener {
    FragmentHomeBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentHomeBinding.inflate(inflater, container, false);

        bind.tvNews.setOnClickListener(this);
        bind.tvVolunteer.setOnClickListener(this);
        bind.tvNotice.setOnClickListener(this);

        return bind.getRoot();    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_news){
            connect(new NewsActivity());
        }else if(v.getId() == R.id.tv_volunteer){
            connect(new VolunteerActivity());
        }else if(v.getId() == R.id.tv_notice ){
            connect(new NoticeActivity());
        }
    }
    //fragment에서는 Activity호출하지 못하므로 부모 Activity를 이용하여 호출
    /*Intent intent = new Intent(getActivity(), NextActivity.class);
    startActivity(intent);*/
    public void connect(Activity activity){
        Intent intent= new Intent(getActivity(), activity.getClass());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}
