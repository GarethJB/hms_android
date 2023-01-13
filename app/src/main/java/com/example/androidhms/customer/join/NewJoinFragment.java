package com.example.androidhms.customer.join;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.common.FragmentControl;
import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.databinding.FragmentNewJoinBinding;
import com.example.conn.RetrofitMethod;


public class NewJoinFragment extends Fragment {
    private FragmentNewJoinBinding bind;
    private CustomerVO customer;
    private String gender;
    private Dialog dialog;
    private EditText et_bloodtype, et_height, et_weight, et_allergy, et_underlying;
    private Button btn_later, btn_register;
    private FragmentControl control;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentNewJoinBinding.inflate(inflater, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_patient_register);

        et_bloodtype = dialog.findViewById(R.id.et_bloodtype);
        et_height = dialog.findViewById(R.id.et_height);
        et_weight = dialog.findViewById(R.id.et_weight);
        et_allergy = dialog.findViewById(R.id.et_allergy);
        et_underlying = dialog.findViewById(R.id.et_underlying);

        btn_later = dialog.findViewById(R.id.btn_later);
        btn_later = dialog.findViewById(R.id.btn_register);

        bind.btnJoin.setOnClickListener(v -> {
            if (bind.rbMale.isChecked()) {
                gender = "M";
            }else if (bind.rbFemale.isChecked()) {
                gender = "F";
            }
            new RetrofitMethod().setParams("social_id", bind.etSocialId.getText().toString())
                    .setParams("name", bind.etName.getText().toString())
                    .setParams("gender", gender)
                    .setParams("phone_number", bind.etPhone.getText().toString())
                    .sendPost("patient_insert.cu", (isResult, data) -> {
                        Log.d("로그", "환자등록완료 ");
                        dialog.show();



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