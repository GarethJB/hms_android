package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidhms.databinding.ActivityStaffMyPageBinding;
import com.example.androidhms.staff.vo.StaffDTO;
import com.example.androidhms.util.EditDialog;
import com.example.androidhms.util.Util;
import com.example.conn.RetrofitMethod;

public class StaffMyPageActivity extends AppCompatActivity {

    private ActivityStaffMyPageBinding bind;
    private StaffDTO staff = Util.staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffMyPageBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());

        bind.tvName.setText(staff.getName());
        bind.tvStaffId.setText(String.valueOf(staff.getStaff_id()));
        String department = staff.getDepartment_name();
        if (staff.getStaff_level() == 1) department += " 의사";
        else department += " 간호사";
        bind.tvStaffInfo.setText(department);
        bind.tvBirthday.setText(Util.getBirthDay(staff.getSocial_id()));
        bind.tvEmail.setText(staff.getEmail());
        bind.tvPhoneNumber.setText(staff.getPhone_number());
        bind.tvHireDate.setText(String.valueOf(staff.getHire_date()));
        bind.tvIntroduction.setText(staff.getIntroduction());

        bind.btnModifyIntroduction.setOnClickListener(v -> {
            new EditDialog(this, getLayoutInflater(), "자기소개", staff.getIntroduction(), new EditDialog.OnSaveClickListener() {
                @Override
                public void onSaveClick(EditDialog dialog, String content) {
                    new RetrofitMethod().setParams("id", staff.getStaff_id())
                            .setParams("introduction", content)
                            .sendPost("updateStaffIntroduction.ap", new RetrofitMethod.CallBackResult() {
                                @Override
                                public void result(boolean isResult, String data) {
                                    if (isResult && data.equals("1")) {
                                        Toast.makeText(StaffMyPageActivity.this, "자기소개를 저장했습니다.", Toast.LENGTH_SHORT).show();
                                        Util.staff.setIntroduction(content);
                                        bind.tvIntroduction.setText(content);
                                        dialog.dismiss();
                                    } else
                                        Toast.makeText(StaffMyPageActivity.this, "자기소개 저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }).show();
        });

        bind.btnLogout.setOnClickListener(v -> {
            Toast.makeText(StaffMyPageActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            Util.staff = null;
            finish();
        });
    }
}