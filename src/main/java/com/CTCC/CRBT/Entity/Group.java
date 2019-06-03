package com.CTCC.CRBT.Entity;

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
@Table(name="T_GROUP")
public class Group {
	@Id    
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupSeq")    
	@SequenceGenerator(name = "GroupSeq", initialValue = 1, allocationSize = 1, sequenceName = "GROUP_SEQUENCE")
	private long group_id;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="group_group_id") 
	@JsonIgnore
	private Set<Account> accounts;
	
	@ManyToOne
	private Video default_video;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="owner_group_group_id") 
	@JsonIgnore
	private Set<Video> owner_videos;

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Video getDefault_video() {
		return default_video;
	}

	public void setDefault_video(Video default_video) {
		this.default_video = default_video;
	}

	public Set<Video> getOwner_videos() {
		return owner_videos;
	}

	public void setOwner_videos(Set<Video> owner_videos) {
		this.owner_videos = owner_videos;
	}
	
}
