package com.fdmgroup.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Company implements IStorable{
	public static final String COMPANY_ID = "COMPANY_ID_SEQ";
	
	@Id
	@SequenceGenerator(name = COMPANY_ID, sequenceName = COMPANY_ID, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COMPANY_ID)
	private int ID;
	
	private String name;
	@OneToOne(mappedBy="company")
	private Stock stock;
	
	@Embedded
	private Address address;
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public Company setName(String name) {
		this.name = name;
		return this;
	}

	public Stock getStock() {
		return stock;
	}

	public Company setStock(Stock stock) {
		this.stock = stock;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Company setAddress(Address address) {
		this.address = address;
		return this;
	}
	

}
