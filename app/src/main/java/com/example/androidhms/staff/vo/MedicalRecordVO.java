package com.example.androidhms.staff.vo;

public class MedicalRecordVO {

    int medical_record_id;
    String patient_name, staff_name, treatment_date, treatment_name, admission;

    public MedicalRecordVO(int medical_record_id, String patient_name, String staff_name, String treatment_date, String treatment_name, String admission) {
        this.medical_record_id = medical_record_id;
        this.patient_name = patient_name;
        this.staff_name = staff_name;
        this.treatment_date = treatment_date;
        this.treatment_name = treatment_name;
        this.admission = admission;
    }

    public int getMedical_record_id() {
        return medical_record_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getTreatment_date() {
        return treatment_date;
    }

    public String getTreatment_name() {
        return treatment_name;
    }

    public String getAdmission() {
        return admission;
    }
}
