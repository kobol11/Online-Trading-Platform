package com.fdmgroup.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private int streetNumber;
	private String street;
	private String postalCode;
	private String city;
	private String country;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public Address setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
		return this;
	}
	public String getStreet() {
		return street;
	}
	public Address setStreet(String street) {
		this.street = street;
		return this;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public Address setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	public String getCity() {
		return city;
	}
	public Address setCity(String city) {
		this.city = city;
		return this;
	}
	public String getCountry() {
		return country;
	}
	public Address setCountry(String country) {
		this.country = country;
		return this;
	}
	
}
