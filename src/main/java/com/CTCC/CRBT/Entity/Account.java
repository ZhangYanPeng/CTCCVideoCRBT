package com.CTCC.CRBT.Entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="T_ACCOUNT")
public class Account {
	@Id    
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccSeq")
	@SequenceGenerator(name = "AccSeq", initialValue = 1, allocationSize = 1, sequenceName = "ACC_SEQUENCE")
	private long usr_id; //*用户ID
	private String usr_pwd; //用户密码（可能需要）
	private Date reg_date; //注册日期（可能需要）
	private String usr_tel; //*用户号码
	private int usr_status; //*账户状态
	
	@ManyToOne
	private Group group;
	
	@ManyToOne
	private Video default_video;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="owner_account_usr_id") 
	@JsonIgnore
	private Set<Video> owner_videos;
	
	public long getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(long usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_pwd() {
		return usr_pwd;
	}

	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getUsr_tel() {
		return usr_tel;
	}

	public void setUsr_tel(String usr_tel) {
		this.usr_tel = usr_tel;
	}

	public int getUsr_status() {
		return usr_status;
	}

	public void setUsr_status(int usr_status) {
		this.usr_status = usr_status;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Video getDefault_video() {
		return default_video;
	}

	public void setDefault_video(Video default_video) {
		this.default_video = default_video;
	}

	public long getUsrID(){
		return usr_id;
	}

	public void setUsrID(long usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsrPwd(){
		return usr_pwd;
	}

	public void setUsrPwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}
	
	public Date getRegDate(){
		return reg_date;
	}

	public void setRegDate(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getUsrTel(){
		return usr_tel;
	}

	public void setUsrTel(String usr_tel) {
		this.usr_tel = usr_tel;
	}
	
	public int getUsrStatus(){
		return usr_status;
	}

	public void setUsrStatus(int usr_status) {
		this.usr_status = usr_status;
	}

	public Set<Video> getOwner_videos() {
		return owner_videos;
	}

	public void setOwner_videos(Set<Video> owner_videos) {
		this.owner_videos = owner_videos;
	}
	
	
}
