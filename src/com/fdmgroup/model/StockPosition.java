package com.fdmgroup.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class StockPosition implements IStorable{
	public static final String STOCKPOSITION_ID = "STOCKPOSITION_ID_SEQ";
	
	@Id
	@SequenceGenerator(name = STOCKPOSITION_ID, sequenceName = STOCKPOSITION_ID, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = STOCKPOSITION_ID)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="User_Id")
	private PlatformUser user;
	
	@ManyToOne
	@JoinColumn(name="stock_id")
	private Stock stock;
	
	private int stockUnit;
	
	public StockPosition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getStockUnit() {
		return stockUnit;
	}

	public void setStockUnit(int stockUnit) {
		this.stockUnit = stockUnit;
	}
	
	
	
}
