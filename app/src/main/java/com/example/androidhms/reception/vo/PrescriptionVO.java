package com.example.androidhms.reception.vo;

public class PrescriptionVO {
	String pres_date,pres_date_short, treatment_day,treatment_day_short, treatment_name, 
	patient_name,doctor_name ,department_name;

	public String getPres_date() {
		return pres_date;
	}

	public void setPres_date(String pres_date) {
		this.pres_date = pres_date;
	}

	public String getPres_date_short() {
		return pres_date_short;
	}

	public void setPres_date_short(String pres_date_short) {
		this.pres_date_short = pres_date_short;
	}

	public String getTreatment_day() {
		return treatment_day;
	}

	public void setTreatment_day(String treatment_day) {
		this.treatment_day = treatment_day;
	}

	public String getTreatment_day_short() {
		return treatment_day_short;
	}

	public void setTreatment_day_short(String treatment_day_short) {
		this.treatment_day_short = treatment_day_short;
	}

	public String getTreatment_name() {
		return treatment_name;
	}

	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
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
