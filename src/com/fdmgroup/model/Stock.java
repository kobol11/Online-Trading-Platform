package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Stock")
@NamedQuery(name = "s.findBySymbol", query="SELECT s FROM Stock s WHERE s.symbol = :stockSymbol")
public class Stock implements IStorable{
	
	public static final String STOCK_ID = "STOCK_ID_SEQ";
	
	@Id
	@SequenceGenerator(name = STOCK_ID, sequenceName = STOCK_ID, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = STOCK_ID)
	@Column(name = "id")
	private int id;
	private String name;
	private String symbol;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToMany(mappedBy="stock")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StockOrder> orders;
	
	@OneToMany(mappedBy="stock")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StockPosition> stockPositions;
	
	public List<StockPosition> getStockPositions() {
		return stockPositions;
	}

	public void setStockPositions(List<StockPosition> stockPositions) {
		this.stockPositions = stockPositions;
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
		this.orders = new ArrayList<>();
		this.stockPositions = new ArrayList<>();
	}

	public String getSymbol() {
		return symbol;
	}

	public Stock setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}
	
	
	public Company getCompany() {
		return company;
	}

	public List<StockOrder> getOrders() {
		return orders;
	}

	public Stock setOrders(List<StockOrder> orders) {
		this.orders = orders;
		return this;
	}

	public Stock setCompany(Company company) {
		this.company = company;
		return this;
	}


	public String getName() {
		return name;
	}


	public Stock setName(String name) {
		this.name = name;
		return this;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	

}
