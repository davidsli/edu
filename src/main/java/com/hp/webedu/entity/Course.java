package com.hp.webedu.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

//�γ̱�
@Entity
@Table(name = "t_course", catalog = "eduweb")
public class Course implements java.io.Serializable {

	private String id;
	private Subject subject;
	private String courName;
	private String imgUrl;
	private String teacherName;
	private String courIntro;
	private String teacherIntro;
	private Date createTime=new Date();//����ʱ��
	private String school;
	private String top;//�Ƿ��ö�
	private String scrollImg;
	private Boolean state=true;

	public Course() {
	}

	public Course(String id) {
		this.id = id;
	}

	public Course(String id, Subject subject, String courName,
			String imgUrl, String teacherName, String courIntro,
			String teacherIntro, Date createTime, String school, String top,
			String scrollImg, Boolean state) {
		this.id = id;
		this.subject = subject;
		this.courName = courName;
		this.imgUrl = imgUrl;
		this.teacherName = teacherName;
		this.courIntro = courIntro;
		this.teacherIntro = teacherIntro;
		this.createTime = createTime;
		this.school = school;
		this.top = top;
		this.scrollImg = scrollImg;
		this.state = state;
	}

	public Course(String courseName, String courIntro, String teacherName, String teacherIntro, String school,
			Subject subject, String fileName) {
		this.subject = subject;
		this.courName = courseName;
		this.imgUrl = fileName;
		this.teacherName = teacherName;
		this.courIntro = courIntro;
		this.teacherIntro = teacherIntro;
		this.school = school;
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
	
	@Column(name = "cour_name", length = 100)
	public String getCourName() {
		return this.courName;
	}

	public void setCourName(String courName) {
		this.courName = courName;
	}

	@Column(name = "img_url", length = 100)
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "teacher_name", length = 50)
	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Column(name = "cour_intro", length = 400)
	public String getCourIntro() {
		return this.courIntro;
	}

	public void setCourIntro(String courIntro) {
		this.courIntro = courIntro;
	}

	@Column(name = "teacher_intro", length = 400)
	public String getTeacherIntro() {
		return this.teacherIntro;
	}

	public void setTeacherIntro(String teacherIntro) {
		this.teacherIntro = teacherIntro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "school", length = 50)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "top", length = 1)
	public String getTop() {
		return this.top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	@Column(name = "scroll_img", length = 100)
	public String getScrollImg() {
		return this.scrollImg;
	}

	public void setScrollImg(String scrollImg) {
		this.scrollImg = scrollImg;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_id")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}