package com.example.androidhms.customer.vo;

import java.io.Serializable;

public class MedicalRecordVO implements Serializable {
    private int medical_record_id, staff_id, patient_id;
    private String treatment_name, treatment_date, admission, memo, name, department_name;

    public MedicalRecordVO(int medical_record_id, int staff_id, int patient_id, String treatment_name, String treatment_date, String admission, String memo, String name, String department_name) {
        this.medical_record_id = medical_record_id;
        this.staff_id = staff_id;
        this.patient_id = patient_id;
        this.treatment_name = treatment_name;
        this.treatment_date = treatment_date;
        this.admission = admission;
        this.memo = memo;
        this.name = name;
        this.department_name = department_name;
    }

    public int getMedical_record_id() {
        return medical_record_id;
    }

    public void setMedical_record_id(int medical_record_id) {
        this.medical_record_id = medical_record_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getTreatment_name() {
        return treatment_name;
    }

    public void setTreatment_name(String treatment_name) {
        this.treatment_name = treatment_name;
    }

    public String getTreatment_date() {
        return treatment_date;
    }

    public void setTreatment_date(String treatment_date) {
        this.treatment_date = treatment_date;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
