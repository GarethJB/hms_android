package com.example.androidhms.customer.common;

import com.example.conn.RetrofitMethod;
import com.google.firebase.messaging.FirebaseMessaging;


public class CustomerFirebase {

    public void sendToken(int patient_id, String device_id) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            new RetrofitMethod().setParams("patient_id", patient_id)
                    .setParams("device_id", device_id)
                    .sendPost("device_update.cu", (isResult, data) -> {

                    });
        });
    }
}
