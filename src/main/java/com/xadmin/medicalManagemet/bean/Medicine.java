package com.xadmin.medicalManagemet.bean;

public class Medicine {

	private int id;
	private String MediName;
	private String MediDetails;
	private String ForDisease;
	
	
//	public Medicine(String mediName, String mediDetails, String forDisease) {
//		super();
//		MediName = mediName;
//		MediDetails = mediDetails;
//		ForDisease = forDisease;
//	}
	
	public Medicine(int id, String mediName, String mediDetails, String forDisease) {
//		super();
		this.id = id;
		MediName = mediName;
		MediDetails = mediDetails;
		ForDisease = forDisease;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMediName() {
		return MediName;
	}
	public void setMediName(String mediName) {
		MediName = mediName;
	}
	public String getMediDetails() {
		return MediDetails;
	}
	public void setMediDetails(String mediDetails) {
		MediDetails = mediDetails;
	}
	public String getForDisease() {
		return ForDisease;
	}
	public void setForDisease(String forDisease) {
		ForDisease = forDisease;
	}
	
	
}
