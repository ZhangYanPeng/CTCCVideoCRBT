package com.CTCC.CRBT.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "T_ADMIN")
public class Admin {
	@Id    
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdminSeq")    
	@SequenceGenerator(name = "AdminSeq", initialValue = 1, allocationSize = 1, sequenceName = "ADMIN_SEQUENCE")
	private long id;
	
	private String username;
	private String password;
	
	
	public Admin() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
