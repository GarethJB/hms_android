package com.example.androidhms.customer.join;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.databinding.FragmentCustomerPatientRegisterBinding;
import com.example.conn.RetrofitMethod;


public class PatientRegisterFragment extends Fragment {
    private FragmentCustomerPatientRegisterBinding bind;
    private String gender;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerPatientRegisterBinding.inflate(inflater, container, false);


       bind.btnJoin.setOnClickListener(v -> {
            if (bind.rbMale.isChecked()) {
                gender = "M";
            }else if (bind.rbFemale.isChecked()) {
                gender = "F";
            }
            new RetrofitMethod().setParams("social_id", Integer.parseInt(bind.etSocialId.getText().toString()))
                    .setParams("name", bind.etName.getText().toString())
                    .setParams("gender", gender)
                    .setParams("phone_number", bind.etPhone.getText().toString())
                    .sendPost("patient_insert.cu", (isResult, data) -> {
                        Log.d("로그", "환자등록완료 ");
                        ((PatientRegisterActivity)getActivity()).social_id = Integer.parseInt(bind.etSocialId.getText().toString());
                        ((PatientRegisterActivity)getActivity()).name = bind.etName.getText().toString();
                        ((PatientRegisterActivity)getActivity()).change(2);
            });

        });



        return bind.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }





}