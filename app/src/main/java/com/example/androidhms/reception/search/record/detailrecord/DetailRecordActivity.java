package com.example.androidhms.reception.search.record.detailrecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityDetailRecordBinding;
import com.example.androidhms.reception.search.SearchActivity;
import com.example.androidhms.reception.search.SearchMedicalRecordAdapter;
import com.example.androidhms.reception.vo.MedicalReceiptVO;
import com.example.androidhms.reception.vo.MedicalRecordVO;
import com.example.androidhms.staff.vo.PatientVO;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetailRecordActivity extends AppCompatActivity {

    ActivityDetailRecordBinding bind;
    DatePickerDialog datePickerDialog;
    ArrayList<MedicalRecordVO> list;
    MedicalRecordVO vo;
    String date;
    String patient_id;
    String fromMonth;
    String fromYear;
    String from;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailRecordBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent record_intent = getIntent();
        list = (ArrayList<MedicalRecordVO>) record_intent.getSerializableExtra("recordList");

        if(list == null || list.size() == 0){
            bind.detail.setVisibility(View.INVISIBLE);
            bind.llSearchPatient.setVisibility(View.VISIBLE);
        }else{
            bind.detail.setVisibility(View.INVISIBLE);
            bind.llSearchPatient.setVisibility(View.INVISIBLE);
            bind.recvDetailRecord.setAdapter(new SearchMedicalRecordAdapter(getLayoutInflater(),vo,list));
            bind.recvDetailRecord.setLayoutManager(new LinearLayoutManager(DetailRecordActivity.this, RecyclerView.VERTICAL,false));
        }

        bind.toolbar.llLogo.setOnClickListener(v -> {
            onBackPressed();
        });
        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });
        //환자이름으로 검색
        bind.btnClick.setOnClickListener(v -> {
                    new RetrofitMethod().setParams("name", bind.editName.getText().toString()).sendPost("patient.re", new RetrofitMethod.CallBackResult() {
                        @Override
                        public void result(boolean isResult, String data) throws Exception {
                            ArrayList<PatientVO> patientList = new Gson().fromJson(data, new TypeToken<ArrayList<PatientVO>>() {
                            }.getType());
                            if (patientList.size() == 0 || patientList == null) {
                                Toast.makeText(DetailRecordActivity.this, "검색 기록이 없습니다", Toast.LENGTH_SHORT).show();
                            } else if (patientList.size() == 1) {
                                patient_id = patientList.get(0).getPatient_id() + "";
                                new RetrofitMethod().setParams("id", patient_id).sendPost("medical_record_id.re", new RetrofitMethod.CallBackResult() {
                                    @Override
                                    public void result(boolean isResult, String data) {
                                        Log.d("로그", "result: " + "진료기록" + data);
                                        ArrayList<MedicalRecordVO> recordList = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalRecordVO>>() {
                                        }.getType());
                                        if (recordList == null || recordList.size() == 0) {
                                            Toast.makeText(DetailRecordActivity.this, "진료이력이 없습니다", Toast.LENGTH_SHORT).show();

                                        } else {
                                            bind.recvDetailRecord.setAdapter(new SearchMedicalRecordAdapter(getLayoutInflater(), vo, recordList));
                                            bind.recvDetailRecord.setLayoutManager(new LinearLayoutManager(DetailRecordActivity.this, RecyclerView.VERTICAL, false));
                                        }
                                    }
                                });
                            }

                        }
                    });


                    bind.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {

                        }
                    });

                });
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

                                case R.id.radio2:
                                    try {
                                        from = CalculationDate(date, 0,-3,0);
                                        bind.term.setText(fromYear +"~"+ date);
                                        sendInfo(new Month_3Fragment());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case R.id.radio3:
                                    try {
                                        from = CalculationDate(date, 0,-6,0);
                                        bind.term.setText(fromYear +"~"+ date);
                                        sendInfo(new Month_6Fragment());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case R.id.radio4:
                                    try {
                                        from = CalculationDate(date, -1,0,0);
                                        bind.term.setText(fromYear +"~"+ date);
                                        sendInfo(new YearFragment());
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
    private void sendInfo(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("id", patient_id);
        bundle.putString("to", date);
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail,fragment).commit();
    }
    }

