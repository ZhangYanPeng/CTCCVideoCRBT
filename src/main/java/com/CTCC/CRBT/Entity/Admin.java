package com.CTCC.CRBT.Entity;

import javax.persistence.Column;
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
	private long admin_id;//管理员标识
	
	@Column( unique=true, nullable=false)
	private String admin_name;//管理员账号名
	@Column( unique=false, nullable=false)
	private String admin_pwd;//管理员账号密码
	private String admin_desc;//管理员描述
	private int valid_state;//0：冻结；1：有效
	
	public Admin() {
		super();
	}

	public long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_pwd() {
		return admin_pwd;
	}

	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}

	public String getAdmin_desc() {
		return admin_desc;
	}

	public void setAdmin_desc(String admin_desc) {
		this.admin_desc = admin_desc;
	}

	public int getValid_state() {
		return valid_state;
	}

	public void setValid_state(int valid_state) {
		this.valid_state = valid_state;
	}
	
	
}
