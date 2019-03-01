package com.fdmgroup.model;

public class OrderType {
	private int ID;
	private String type;
	
	public OrderType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderType(int iD, String type) {
		super();
		ID = iD;
		this.type = type;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
