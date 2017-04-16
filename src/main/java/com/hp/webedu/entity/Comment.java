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

/**
 * TComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_comment", catalog = "eduweb")
public class Comment implements java.io.Serializable {

	// Fields

	private String id;
	private User user;
	private Video video;
	private String content;
	private Timestamp comTime;//�����¼�
	private Boolean state;

	public Comment() {
	}

	public Comment(String id) {
		this.id = id;
	}

	public Comment(String id, User user, Video video, String content,
			Timestamp comTime, Boolean state) {
		this.id = id;
		this.user = user;
		this.video = video;
		this.content = content;
		this.comTime = comTime;
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

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "com_time", length = 19)
	public Timestamp getComTime() {
		return this.comTime;
	}

	public void setComTime(Timestamp comTime) {
		this.comTime = comTime;
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