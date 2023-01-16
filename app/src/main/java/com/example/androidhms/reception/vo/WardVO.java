package com.example.androidhms.reception.vo;

public class WardVO {
	int admission_record_id, ward_id, medical_record_id, bed;
	String admission_date, ward_number, doctor_name, department_name,patient_name, discharge_date;

	public int getAdmission_record_id() {
		return admission_record_id;
	}

	public void setAdmission_record_id(int admission_record_id) {
		this.admission_record_id = admission_record_id;
	}

	public int getWard_id() {
		return ward_id;
	}

	public void setWard_id(int ward_id) {
		this.ward_id = ward_id;
	}

	public int getMedical_record_id() {
		return medical_record_id;
	}

	public void setMedical_record_id(int medical_record_id) {
		this.medical_record_id = medical_record_id;
	}

	public int getBed() {
		return bed;
	}

	public void setBed(int bed) {
		this.bed = bed;
	}

	public String getAdmission_date() {
		return admission_date;
	}

	public void setAdmission_date(String admission_date) {
		this.admission_date = admission_date;
	}

	public String getWard_number() {
		return ward_number;
	}

	public void setWard_number(String ward_number) {
		this.ward_number = ward_number;
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

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getDischarge_date() {
		return discharge_date;
	}

	public void setDischarge_date(String discharge_date) {
		this.discharge_date = discharge_date;
	}
}
