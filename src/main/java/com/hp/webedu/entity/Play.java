package com.hp.webedu.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//���ű�
@Entity
@Table(name = "t_play", catalog = "eduweb")
public class Play implements java.io.Serializable {

	private String id;
	private User user;
	private Video video;
	private String ip;
	private Timestamp startTime;
	private Timestamp endTime;
	private String timeLong;
	private Boolean state;

	public Play() {
	}

	public Play(String id) {
		this.id = id;
	}

	public Play(String id, User user, Video video, String ip,
			Timestamp startTime, Timestamp endTime, String timeLong,
			Boolean state) {
		this.id = id;
		this.user = user;
		this.video = video;
		this.ip = ip;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeLong = timeLong;
		this.state = state;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ip", length = 50)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "start_time", length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "time_long", length = 10)
	public String getTimeLong() {
		return this.timeLong;
	}

	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id")
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}