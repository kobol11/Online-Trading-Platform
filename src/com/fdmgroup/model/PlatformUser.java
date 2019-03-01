package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fdmgroup.businessLogic.IDGenerator;

@Entity
@SequenceGenerator(name = PlatformUser.PLATFORMUSER_ID, sequenceName = PlatformUser.PLATFORMUSER_ID, initialValue = 1, allocationSize = 1)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "User_Type")
public abstract class PlatformUser implements IStorable{
	public static final String PLATFORMUSER_ID = "PLATFORMUSER_ID_SEQ";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PLATFORMUSER_ID)
	@Column(name = "id")
	private int ID;
	
	@NotNull
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password", length = 10, unique = true, nullable = false)
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "isadmin")
	private char isAdmin;
	
	@Embedded
	private Address address;
	
	public PlatformUser() {
		super();
		// TODO Auto-generated constructor stub
		//this.ID = IDGenerator.getNext();
		this.isAdmin = 'N'; 
		this.address = new Address();
	}
	
	public PlatformUser(String firstname, String lastname, String username, String password, String email) {
		this(firstname, lastname, username, password);
		this.email = email;
		this.address = new Address();
	}
	
	

	public PlatformUser(String firstname, String lastname, String username, String password) {
		super();
		//this.ID = IDGenerator.getNext();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.isAdmin = 'N';
		this.address = new Address();
	}



	public int getID() {
		return ID;
	}


	public PlatformUser setID() {
		this.ID = IDGenerator.getNext();
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public PlatformUser setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public PlatformUser setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public PlatformUser setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public PlatformUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public PlatformUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public char isAdmin() {
		return isAdmin;
	}

	public PlatformUser setAdmin(char isAdmin) {
		this.isAdmin = isAdmin;
		return this;
	}
	
	public Address getAddress() {
		return address;
	}

	public PlatformUser setAddress(Address address) {
		this.address = address;
		return this;
	}
}
