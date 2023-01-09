package com.example.androidhms.reception.vo;


public class MedicalRecordVO {
	private int medical_record_id, staff_id, patient_id;
	private String record_date, treatment_name, admission, meno, patient_name, doctor_name, department_name ;

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

	public String getRecord_date() {
		return record_date;
	}

	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}

	public String getTreatment_name() {
		return treatment_name;
	}

	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
}
