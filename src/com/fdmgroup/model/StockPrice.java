package com.fdmgroup.model;

public class StockPrice {
	private int ID;
	private Stock stock;
	private double openingPrice;
	private double closingPrice;
	public StockPrice() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StockPrice(int iD, Stock stock, double openingPrice, double closingPrice) {
		super();
		ID = iD;
		this.stock = stock;
		this.openingPrice = openingPrice;
		this.closingPrice = closingPrice;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Stock getStock() {
		return stock;
	}

	public StockPrice setStock(Stock stock) {
		this.stock = stock;
		return this;
	}

	public double getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}

	public double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}
	
	
}
