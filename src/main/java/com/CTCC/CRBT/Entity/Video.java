package com.CTCC.CRBT.Entity;

import java.sql.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="T_VIDEO")
public class Video {
	@Id    
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuSeq")    
	@SequenceGenerator(name = "menuSeq", initialValue = 1, allocationSize = 1, sequenceName = "MENU_SEQUENCE")
	private String video_id; //��Ƶid
	private String video_name; //��Ƶ����
	private String video_desc; //��Ƶ����
	@ManyToOne
	private VideoType type; //��Ƶ����
	private String tags; //"不同的标签以；间隔"
	private Date create_date; 
	private double price;
	private String video_path;

	@OneToOne
	private Rate rate;
	
	private int owner_type; //0: 公开 1：个人用户   2：集团用户
	@ManyToOne
	private Account owner_account;
	@ManyToOne
	private Group owner_Group;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="default_video_video_id") 
	@JsonIgnore
	private Set<Group> groups;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="default_video_video_id") 
	@JsonIgnore
	private Set<Account> accounts;
	
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getVideo_name() {
		return video_name;
	}
	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}
	public String getVideo_desc() {
		return video_desc;
	}
	public void setVideo_desc(String video_desc) {
		this.video_desc = video_desc;
	}
	public VideoType getType() {
		return type;
	}
	public void setType(VideoType type) {
		this.type = type;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getVideo_path() {
		return video_path;
	}
	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}
	public Rate getRate() {
		return rate;
	}
	public void setRate(Rate rate) {
		this.rate = rate;
	}
	public int getOwner_type() {
		return owner_type;
	}
	public void setOwner_type(int owner_type) {
		this.owner_type = owner_type;
	}
	public Account getOwner_account() {
		return owner_account;
	}
	public void setOwner_account(Account owner_account) {
		this.owner_account = owner_account;
	}
	public Group getOwner_Group() {
		return owner_Group;
	}
	public void setOwner_Group(Group owner_Group) {
		this.owner_Group = owner_Group;
	}
	public void setGroups(HashSet<Group> groups) {
		this.groups = groups;
	}
	public void setAccounts(HashSet<Account> accounts) {
		this.accounts = accounts;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
