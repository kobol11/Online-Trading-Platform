package com.fdmgroup.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "CashBrokerageAccount")
@DiscriminatorValue("Cash")
public class CashBrokerageAccount extends Account{
	
	public CashBrokerageAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CashBrokerageAccount(PlatformUser user, double cashBalance) {
		super(user, cashBalance);
	}
	public CashBrokerageAccount(PlatformUser user){
		super(user);
	}
}
