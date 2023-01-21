package com.example.androidhms.reception.search.record.detailrecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidhms.databinding.FragmentReceptionMonthBinding;
import com.example.androidhms.reception.search.SearchMedicalRecordAdapter;
import com.example.androidhms.reception.vo.MedicalRecordVO;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Month_6Fragment extends Fragment {
    MedicalRecordVO vo;
    ArrayList<MedicalRecordVO> list;
    FragmentReceptionMonthBinding bind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    
        bind = FragmentReceptionMonthBinding.inflate(inflater,container,false);
        //액티비티에서 값 받기

        Bundle bundle =getArguments();
        String id= (String) bundle.getString("id");
        String from= (String) bundle.getString("from");
        String to= (String) bundle.getString("to");
        Log.d("로그", "onCreateView: " + "프래그먼트" + id +from + to);

        new RetrofitMethod().setParams("id", id).setParams("from",from).setParams("to",to)
                .sendPost("medical_record.re", new RetrofitMethod.CallBackResult() {
                    @Override
                    public void result(boolean isResult, String data) throws Exception {
                         list = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalRecordVO>>(){}.getType());
                        if(list == null||list.size()==0) {
                            Toast.makeText(getContext(), "값이 없습니다", Toast.LENGTH_SHORT).show();
                        }else {
                            bind.recvDetailRecord.setAdapter(new SearchMedicalRecordAdapter(inflater, vo, list));
                            bind.recvDetailRecord.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        }
                    }
                });

        return  bind.getRoot();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}