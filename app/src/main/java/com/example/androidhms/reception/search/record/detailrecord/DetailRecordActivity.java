package com.example.androidhms.reception.search.record.detailrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityDetailRecordBinding;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.conn.RetrofitMethod;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetailRecordActivity extends AppCompatActivity {

    ActivityDetailRecordBinding bind;
    DatePickerDialog datePickerDialog;
    ArrayList<MedicalReceiptVO> list;
    int position;
    String date;
    String patient_id;
    String fromMonth;
    String fromYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailRecordBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent record_intent = getIntent();
        patient_id = (String) record_intent.getSerializableExtra("id");
        Log.d("로그", "onCreate: " + patient_id + "아이디 받음");

        bind.llCalender.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(c.YEAR);
            int month = c.get(c.MONTH);
            int day = c.get(c.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(DetailRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    date= year + "-" + month + "-" + day ;
                    bind.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId){
                                case R.id.rado1:
                                    try {
                                        fromMonth = CalculationDate(date,0,-6,0);
                                        bind.term.setText(fromMonth+"~"+date);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.detail,new MonthFragment()).commit();

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case R.id.radio2:
                                    try {
                                        fromYear = CalculationDate(date, -1,0,0);
                                        bind.term.setText(fromYear +"~"+ date);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.detail,new YearFragment()).commit();
                                        Toast.makeText(DetailRecordActivity.this, "1년", Toast.LENGTH_SHORT).show();
                                        Log.d("로그", "onCheckedChanged: "+"1");
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
            }, year, month, day);
            datePickerDialog.show();
        });
    }
    
    private static String CalculationDate(String sttDate, int year, int month , int day) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        Date date = sdf.parse(sttDate);
        cal.setTime(date);

        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);

        return sdf.format(cal.getTime());
    }

    private void getTerm(){
        new RetrofitMethod().setParams("id", patient_id).setParams("from",fromMonth).setParams("to",date)
                .sendPost("medical_record.re", new RetrofitMethod.CallBackResult() {
                    @Override
                    public void result(boolean isResult, String data) throws Exception {
                        Log.d("로그", "result: " + "기간구하기");

                    }
                });
    }

}