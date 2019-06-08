package com.CTCC.CRBT.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_RATE")
public class Rate {
	@Id    
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RateSeq")    
	@SequenceGenerator(name = "RateSeq", initialValue = 1, allocationSize = 1, sequenceName = "RATE_SEQUENCE")
	private long rate_id;
	@OneToOne
	private Video video;
	private long order_sum;
	private long using_sum;
	
	
	public long getRate_id() {
		return rate_id;
	}
	public void setRate_id(long rate_id) {
		this.rate_id = rate_id;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public long getOrder_sum() {
		return order_sum;
	}
	public void setOrder_sum(long order_sum) {
		this.order_sum = order_sum;
	}
	public long getUsing_sum() {
		return using_sum;
	}
	public void setUsing_sum(long using_sum) {
		this.using_sum = using_sum;
	}
	
	
}
