package com.CTCC.CRBT.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="T_CONTENT_PROVIDER")
public class ContentProvider {
	@Id    
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContentProviderSeq")    
	@SequenceGenerator(name = "ContentProviderSeq", initialValue = 1, allocationSize = 1, sequenceName = "CONTENT_PROVIDER_SEQUENCE")
	private long cp_id;//内容提供商标识
	
	@Column( unique=true, nullable=false)
	private String cp_name;//CP账号名
	@Column( unique=false, nullable=false)
	private String cp_pwd;//CP账号密码
	@Column( unique=false, nullable=false)
	private String company;//CP公司名,不唯一
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="owner_cp_cp_id") 
	@JsonIgnore
	private Set<Video> owner_cp_videos;
	
	public long getCp_id() {
		return cp_id;
	}

	public void setCp_id(long cp_id) {
		this.cp_id = cp_id;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public String getCp_pwd() {
		return cp_pwd;
	}

	public void setCp_pwd(String cp_pwd) {
		this.cp_pwd = cp_pwd;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<Video> getOwner_cp_videos() {
		return owner_cp_videos;
	}

	public void setOwner_cp_videos(Set<Video> owner_cp_videos) {
		this.owner_cp_videos = owner_cp_videos;
	}

	public ContentProvider() {
		super();
	}
}
