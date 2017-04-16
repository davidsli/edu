package com.hp.webedu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

 //���ֱ�Ϊ������ֵı�
@Entity
@Table(name = "t_score", catalog = "eduweb")
public class Score implements java.io.Serializable {

	private String id;
	private User user;
	private Video video;
	private String score;

	public Score() {
	}

	public Score(String id) {
		this.id = id;
	}

	public Score(String id, User user, Video video, String score) {
		this.id = id;
		this.user = user;
		this.video = video;
		this.score = score;
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

	@Column(name = "score", length = 10)
	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
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