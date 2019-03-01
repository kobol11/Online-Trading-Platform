package com.fdmgroup.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fdmgroup.businessLogic.IDGenerator;


@Entity
//@SequenceGenerator(name = Account.ACCOUNT_ID, sequenceName = Account.ACCOUNT_ID, initialValue = 1, allocationSize = 1)
@Table(name="Account")
@NamedQuery(name = "acc.findByAccNum", query="SELECT acc FROM Account acc WHERE acc.accountNumber = :accNumParam")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Account_Type")
public abstract class Account implements IStorable{
	
	public static final String ACCOUNT_ID = "ACCOUNT_ID_SEQ";
	
	@Id
	@SequenceGenerator(name = Account.ACCOUNT_ID, sequenceName = Account.ACCOUNT_ID, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ACCOUNT_ID)
	@Column(name = "id")
	private int ID;
	
	@Column(name = "accountnumber")
	private long accountNumber;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="User_Id")
	private PlatformUser user;
	
	private double cashBalance;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
		this.cashBalance = 0;
		this.accountNumber = (long) (new Date().getTime() + Math.ceil(Math.random() * 1000));
	}
	public Account(PlatformUser user){
		this();
		this.user = user;
	}

	public Account(PlatformUser user, double cashBalance) {
		super();
		this.accountNumber = new Date().getTime();
		this.user = user;
		this.cashBalance = cashBalance;
	}
	
	public int getID() {
		return ID;
	}

	/*public void setID(int iD) {
		ID = iD;
	}*/

	public long getAccountNumber() {
		return accountNumber;
	}

	public Account setAccountNumber(long accountNumber) {
		this.accountNumber = IDGenerator.getNext();
		return this;
	}

	public PlatformUser getUser() {
		return user;
	}

	public Account setUser(PlatformUser user) {
		this.user = user;
		return this;
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public Account setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
		return this;
	}
	
	

}
