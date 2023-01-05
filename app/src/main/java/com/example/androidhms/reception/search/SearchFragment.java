package com.example.androidhms.reception.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentReceptionSearchBinding;

public class SearchFragment extends Fragment {

    FragmentReceptionSearchBinding bind;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         bind = FragmentReceptionSearchBinding.inflate(inflater,container,false);

        return bind.getRoot() ;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}