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
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "t_video", catalog = "eduweb")
public class Video implements java.io.Serializable {


	private String id;
	private Course course;
	private String videoDesc;//课程名
	private String videoName;//课程名
	private String timeLong;//时长
	private String chapter;//章节
	private Date uploadTime=new Date();//上传时间
	private String thumb;//缩略图
	private String size;//文件尺寸大小
	private Boolean state=true;//
	
	public Video() {
	}

	public Video(String id) {
		this.id = id;
	}

	public Video(String id, Course course, String videoName,
			String timeLong, String chapter, Date uploadTime, String size,
			Boolean state) {
		this.id = id;
		this.course = course;
		this.videoName = videoName;
		this.timeLong = timeLong;
		this.chapter = chapter;
		this.uploadTime = uploadTime;
		this.size = size;
		this.state = state;
	}

	public Video(Course course,String videoDesc,String videoName, String timeLong, String chapter, String thumb,
			String videoSize) {
		this.videoDesc=videoDesc;
		this.course = course;
		this.videoName = videoName;
		this.timeLong = timeLong;
		this.chapter = chapter;
		this.thumb=thumb;
		this.size = videoSize;
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

	@Column(name = "video_name", length = 100)
	public String getVideoName() {
		return this.videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	@Column(name = "time_long", length = 50)
	public String getTimeLong() {
		return this.timeLong;
	}

	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}

	@Column(name = "chapter", length = 10)
	public String getChapter() {
		return this.chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "upload_time", length = 10)
	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "size", length = 10)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cour_id")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "thumb", length = 100)
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Column(name = "video_desc", length = 50)
	public String getVideoDesc() {
		return videoDesc;
	}

	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}

}