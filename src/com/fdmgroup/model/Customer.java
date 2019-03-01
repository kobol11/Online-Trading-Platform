package com.fdmgroup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity(name = "Customer")
@DiscriminatorValue("Customer")
public class Customer extends PlatformUser implements ITradeable, Serializable{
	@Column(name = "sin")
	private int SIN;
	
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Account> accounts;
	
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StockOrder> orders;
	
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StockPosition> stockPositions;
	
	public List<StockPosition> getStockPositions() {
		return stockPositions;
	}

	public void setStockPositions(List<StockPosition> stockPositions) {
		this.stockPositions = stockPositions;
	}

	public Customer(){
		super();
		this.accounts = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.stockPositions = new ArrayList<>();
	}
	
	public List<StockOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<StockOrder> orders) {
		this.orders = orders;
	}

	public Customer(String firstname, String lastname, String username, String password, int SIN) {
		super(firstname, lastname, username, password);
		// TODO Auto-generated constructor stub
		this.SIN = SIN;
		this.accounts = new ArrayList<>();
		this.orders = new ArrayList<>();
	}

	public Customer(String firstname, String lastname, String username, String password, String email, int SIN) {
		super(firstname, lastname, username, password, email);
		// TODO Auto-generated constructor stub
		this.SIN = SIN;
		this.accounts = new ArrayList<>();
		this.orders = new ArrayList<>();
	}

	public int getSIN() {
		return SIN;
	}

	public Customer setSIN(int sIN) {
		SIN = sIN;
		return this;
	}
	
	
	public String getFullName(){
		return this.getClass().getName();
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
