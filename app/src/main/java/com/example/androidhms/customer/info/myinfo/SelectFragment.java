package com.example.androidhms.customer.info.myinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.databinding.FragmentCustomerMyinfoSelectBinding;

public class SelectFragment extends Fragment {
    private FragmentCustomerMyinfoSelectBinding bind;
    private CustomerVO customer;

    public SelectFragment(CustomerVO customer) {
        this.customer = customer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerMyinfoSelectBinding.inflate(inflater, container, false);

        bind.tvName.setText(customer.getName());
        bind.tvGender.setText(customer.getGender());
        bind.tvEmail.setText(customer.getEmail());
        bind.tvPhone.setText(customer.getPhone_number());
        bind.tvDate.setText(customer.getJoin_date());


        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}