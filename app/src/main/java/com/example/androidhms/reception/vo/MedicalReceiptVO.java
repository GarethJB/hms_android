package com.example.androidhms.reception.vo;

import java.sql.Date;



public class MedicalReceiptVO {
	private int patient_id, staff_id;
	private String memo, time;

	public MedicalReceiptVO(int patient_id, int staff_id, String time, String memo) {
		this.patient_id = patient_id;
		this.staff_id = staff_id;
		this.time = time;
		this.memo = memo;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
