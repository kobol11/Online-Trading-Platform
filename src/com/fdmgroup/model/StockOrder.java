package com.fdmgroup.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class StockOrder implements IStorable{
	
public static final String ORDER_ID = "ORDER_ID_SEQ";
	
	@Id
	@SequenceGenerator(name = ORDER_ID, sequenceName = ORDER_ID, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDER_ID)
	@Column(name = "id")
	private int ID;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="User_Id")
	private PlatformUser user;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="stock_id")
	private Stock stock;
	
	@Column(name="orderstatus_id")
	private int orderStatusId;
	
	@Column(name="stockunit")
	private int stockUnit;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "transactiondate")
	private Date transactionDate;
	
	@Column(name="userquotedprice")
	private double userQuotedPrice;
	
	private String action;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getStockUnit() {
		return stockUnit;
	}

	public void setStockUnit(int stockUnit) {
		this.stockUnit = stockUnit;
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatus(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public StockOrder() {
	super();
	// TODO Auto-generated constructor stub
	}

	public StockOrder(int iD, PlatformUser user, Stock stock, int orderStatusId, double userQuotedPrice) {
		super();
		ID = iD;
		this.user = user;
		this.stock = stock;
		this.orderStatusId = orderStatusId;
		this.userQuotedPrice = userQuotedPrice;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public PlatformUser getUser() {
		return user;
	}

	public void setUser(PlatformUser user) {
		this.user = user;
	}

	public Stock getStock() {
		return stock;
	}

	public StockOrder setStock(Stock stock) {
		this.stock = stock;
		return this;
	}

	public double getUserQuotedPrice() {
		return userQuotedPrice;
	}

	public void setUserQuotedPrice(double userQuotedPrice) {
		this.userQuotedPrice = userQuotedPrice;
	}
	
}
