package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "MarginBrokerageAccount")
@DiscriminatorValue("Margin")
public class MarginBrokerageAccount extends Account{
	@Column(name = "overdraft")
	private double overdraft;
	
	public MarginBrokerageAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarginBrokerageAccount(PlatformUser user, double cashBalance, double overdraft) {
		super(user, cashBalance);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public MarginBrokerageAccount setOverdraft(double overdraft) {
		this.overdraft = overdraft;
		return this;
	}
	
	
}
