package com.example.androidhms.staff;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.databinding.FragmentOutpatientLookupBinding;

public class OutpatientLookupFragment extends Fragment {

    private FragmentOutpatientLookupBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentOutpatientLookupBinding.inflate(inflater, container, false);
        //bind.btnSearch.setOnClickListener(onSearchClick());
        return bind.getRoot();
    }

//    private View.OnClickListener onSearchClick() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new RetrofitMethod().setParams("name", bind.etName.getText().toString())
//                        .sendPost("getpatient.ap", new RetrofitMethod.CallBackResult() {
//                            @Override
//                            public void result(boolean isResult, String data) {
//                                if (data.equals("null")) {
//                                    Toast.makeText(getActivity(), "해당 환자가 없습니다.", Toast.LENGTH_SHORT).show();
//                                } else setPatientInfo(new Gson().fromJson(data, PatientVO.class));
//                            }
//                        });
//            }
//        };
//    }

//    private void setPatientInfo(PatientVO vo) {
//
//    }
}