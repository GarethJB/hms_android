package com.example.androidhms.customer.info;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.LoginInfo;
import com.example.androidhms.customer.hospital.acceptance.AcceptanceRecordActivity;
import com.example.androidhms.customer.info.medical.MedicalRecordActivity;
import com.example.androidhms.customer.info.reservation.ReservationScheduleActivity;
import com.example.androidhms.customer.vo.CustomerVO;
import com.example.androidhms.customer.vo.MedicalReceiptVO;
import com.example.androidhms.databinding.FragmentCustomerInfoBinding;
import com.example.androidhms.staff.outpatient.PrescriptionActivity;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class InfoFragment extends Fragment {
    private FragmentCustomerInfoBinding bind;
    private CustomerVO customer;
    private ArrayList<MedicalReceiptVO> medicalReceipt = new ArrayList<>();
    private TextView tv_n_number, tv_n_department, tv_n_name, tv_n_time, tv_n_waiting;
    private TextView tv_c_name, tv_c_patient_id, tv_c_social_id, tv_c_gender, tv_c_bloodtype, tv_c_height, tv_c_weight, tv_c_allergy, tv_c_underlying;
    private Button btn_c_back, btn_q_back;
    private Dialog dialog_number;
    private Dialog dialog_card;
    private Dialog dialog_qr;
    private ImageView imgv_qr;
    private String patient_id;
    private int num;
    private int number;


    public InfoFragment(CustomerVO customer) {
        this.customer = customer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerInfoBinding.inflate(inflater, container, false);

        Date curTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String nowTime = format.format(curTime);
        Log.d("로그", "오늘 : " + nowTime);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int tn = 1;
                while (true) {
                    new RetrofitMethod().setParams("patient_id", customer.getPatient_id())
                            .sendPost("number_ticket.cu", (isResult, data) -> {
                                medicalReceipt = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
                                for (int i = 0; i <= medicalReceipt.size()-1; i++) {
                                    if (customer.getPatient_id() == medicalReceipt.get(i).getPatient_id()) {
                                        num = i;
                                    }
                                }
                                if (medicalReceipt.size() != 0) {
                                    Log.d("로그", "일치 " + num);
                                    Log.d("로그", "환자ID : " + LoginInfo.check_id);
                                    bind.tvDepartment.setText(medicalReceipt.get(num).getDepartment_name());
                                    bind.tvName.setText(medicalReceipt.get(num).getName());
                                    bind.tvWaiting.setText(num+"");
                                }else if (medicalReceipt.size() == 0) {
                                    Toast.makeText(getContext(), "접수내역이 없습니다", Toast.LENGTH_SHORT );
                                }
                            });

                    try{
                        Thread.sleep(10000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();











        dialog_number = new Dialog(getActivity());
        dialog_number.setContentView(R.layout.dialog_number_ticket);

        dialog_card = new Dialog(getActivity());
        dialog_card.setContentView(R.layout.dialog_card);

        dialog_qr = new Dialog(getActivity());
        dialog_qr.setContentView(R.layout.dialog_qr);

        tv_n_number = dialog_number.findViewById(R.id.tv_number);
        tv_n_department = dialog_number.findViewById(R.id.tv_department);
        tv_n_name = dialog_number.findViewById(R.id.tv_name);
        tv_n_time = dialog_number.findViewById(R.id.tv_time);
        tv_n_waiting = dialog_number.findViewById(R.id.tv_waiting);

        tv_c_name = dialog_card.findViewById(R.id.tv_name);
        tv_c_patient_id = dialog_card.findViewById(R.id.tv_patient_id);
        tv_c_social_id = dialog_card.findViewById(R.id.tv_social_id);
        tv_c_gender = dialog_card.findViewById(R.id.tv_gender);
        tv_c_bloodtype = dialog_card.findViewById(R.id.tv_bloodtype);
        tv_c_height = dialog_card.findViewById(R.id.tv_height);
        tv_c_weight = dialog_card.findViewById(R.id.tv_weight);
        tv_c_allergy = dialog_card.findViewById(R.id.tv_allergy);
        tv_c_underlying = dialog_card.findViewById(R.id.tv_underlying);
        btn_c_back = dialog_card.findViewById(R.id.btn_back);







        //클릭시 번호표 조회
        bind.btnNumberTicket.setOnClickListener(v1 -> {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String now = formatDate.format(currentTime);
            Log.d("로그", "오늘 : " + now);
            new RetrofitMethod().setParams("patient_id", customer.getPatient_id())
                    .sendPost("number_ticket.cu", (isResult, data) -> {
                        medicalReceipt = new Gson().fromJson(data, new TypeToken<ArrayList<MedicalReceiptVO>>(){}.getType());
                        for (int i = 0; i <= medicalReceipt.size()-1; i++) {
                            if (customer.getPatient_id() == medicalReceipt.get(i).getPatient_id()) {
                                number = i;
                            }
                        }
                        if (medicalReceipt.size() != 0) {
                            Log.d("로그", "일치 " + number);
                            Log.d("로그", "환자ID : " + LoginInfo.check_id);
                            tv_n_number.setText(Integer.toString(medicalReceipt.get(number).getReceipt_id()));
                            tv_n_department.setText(medicalReceipt.get(number).getDepartment_name());
                            tv_n_name.setText(medicalReceipt.get(number).getName());
                            tv_n_time.setText(medicalReceipt.get(number).getTime()+"");
                            tv_n_waiting.setText(Integer.toString(number));
                            dialog_number.show();
                            dialog_number.findViewById(R.id.btn_back).setOnClickListener(v -> {
                                dialog_number.dismiss();
                            });
                        }else if (medicalReceipt.size() == 0) {
                            Toast.makeText(getContext(), "접수내역이 없습니다", Toast.LENGTH_SHORT );
                        }
                    });
        });

        //클릭시 카드전환
        bind.btnCard.setOnClickListener(v1 -> {
            tv_c_name.setText(customer.getName());
            tv_c_patient_id.setText(customer.getPatient_id()+"");
            tv_c_social_id.setText(customer.getSocial_id()+"");
            tv_c_gender.setText(customer.getGender());
            tv_c_bloodtype.setText(customer.getBlood_type());
            tv_c_height.setText(customer.getHeight()+"");
            tv_c_weight.setText(customer.getWeight()+"");
            if (customer.getAllergy() == null) {
                tv_c_allergy.setText("없음");
            }else {
                tv_c_allergy.setText(customer.getAllergy());
            }
            if (customer.getUnderlying_disease() == null) {
                tv_c_underlying.setText("없음");
            }else {
                tv_c_underlying.setText(customer.getUnderlying_disease());
            }
            dialog_card.show();
            btn_c_back.setOnClickListener(v -> {
                dialog_card.dismiss();
            });
        });

        //클릭시 큐알화면 전환
        bind.btnQr.setOnClickListener(v1 -> {
            imgv_qr = dialog_qr.findViewById(R.id.imgv_qr);
            patient_id = Integer.toString(customer.getPatient_id());

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(patient_id, BarcodeFormat.QR_CODE,200,200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imgv_qr.setImageBitmap(bitmap);
            }catch (Exception e){}
            btn_q_back = dialog_qr.findViewById(R.id.btn_back);
            btn_q_back.setOnClickListener(v -> {
                dialog_qr.dismiss();
            });
            dialog_qr.show();
        });

        //기록 조회
        bind.llMedicalRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
            intent.putExtra("patient_id", customer.getPatient_id());
            startActivity(intent);
        });

        //일정 조회
        bind.llReservationRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReservationScheduleActivity.class);
            intent.putExtra("patient_id", customer.getPatient_id());
            startActivity(intent);
        });

        //클릭시 수납조회
        bind.llAcceptanceInquire.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AcceptanceRecordActivity.class);
            startActivity(intent);
        });

        //클릭시 처방전 조회
        bind.llTimetable.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PrescriptionActivity.class);
            intent.putExtra("medical_record_id", 23);
            startActivity(intent);
        });





      



        //로그인시 화면 변경



        return bind.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }


}