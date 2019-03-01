package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="OrderStatus")
@NamedQuery(name = "o.findByStatus", query="SELECT o FROM OrderStatus o WHERE o.status = :status")
public class OrderStatus implements IStorable{
	public static final String ORDER_STATUS_ID = "ORDER_STATUS_ID_SEQ";
	
	@Id
	@SequenceGenerator(name = ORDER_STATUS_ID, sequenceName = ORDER_STATUS_ID, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDER_STATUS_ID)
	@Column(name = "id")
	private int ID;
	
	private String status;
	
	public OrderStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
