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

//�ղر�
@Entity
@Table(name = "t_collection", catalog = "eduweb")
public class Collection implements java.io.Serializable {

	private String id;
	private User user;
	private Course course;
	private Timestamp collTime;
	private Boolean state;

	public Collection() {
	}

	public Collection(String id, User user, Course course,
			Timestamp collTime, Boolean state) {
		this.id = id;
		this.user = user;
		this.course = course;
		this.collTime = collTime;
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

	@Column(name = "coll_time", length = 19)
	public Timestamp getCollTime() {
		return this.collTime;
	}

	public void setCollTime(Timestamp collTime) {
		this.collTime = collTime;
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
	@JoinColumn(name = "cour_id")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}