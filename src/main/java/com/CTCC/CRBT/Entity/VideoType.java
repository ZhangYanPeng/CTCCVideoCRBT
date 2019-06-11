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
@Table(name = "T_VIDEO_TYPE")
public class VideoType {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VideoTypeSeq")
	@SequenceGenerator(name = "VideoTypeSeq", initialValue = 1, allocationSize = 1, sequenceName = "VIDEO_TYPE_SEQUENCE")
	private long video_type_id; // 分类的id

	@Column(unique = true, nullable = false)
	private String type_name; // 视频的分类，音乐、搞笑等等

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "type_video_type_id")
	@JsonIgnore
	private Set<Video> videos;

	public VideoType(String type) {
		// TODO Auto-generated constructor stub
		type_name = type;
	}

	public long getVideo_type_id() {
		return video_type_id;
	}

	public void setVideo_type_id(long video_type_id) {
		this.video_type_id = video_type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public VideoType() {
		super();
	}
}
