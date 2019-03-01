package com.fdmgroup.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "com.fdmgroup.model.Admin")

@DiscriminatorValue("Admin")
public class Admin extends PlatformUser{
	
	public Admin(){
		super();
	}

	public Admin(String firstname, String lastname, String username, String password, String email) {
		super(firstname, lastname, username, password, email);
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
		// TODO Auto-generated constructor stub
	}

}
