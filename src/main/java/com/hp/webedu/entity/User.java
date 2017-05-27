package com.hp.webedu.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "eduweb")
public class User implements java.io.Serializable {

	private String id;
	private String nickName;
	private String realName;
	private String email;
	private String phoneNumber;
	private Boolean sex;
	private String qqNumber;
	private Boolean phoneState;
	private Boolean emailState;
	private String userPassword;
	private String headImage;
	private String personalIntro;
	private Boolean state;

	public User() {
	}

	public User(String id) {
		this.id = id;
	}

	public User(String id, String nickName, String realName, String email,
			String phoneNumber, Boolean sex, String qqNumber,
			Boolean phoneState, Boolean emailState, String userPassword,
			String headImage, String personalIntro, Boolean state) {
		this.id = id;
		this.nickName = nickName;
		this.realName = realName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.qqNumber = qqNumber;
		this.phoneState = phoneState;
		this.emailState = emailState;
		this.userPassword = userPassword;
		this.headImage = headImage;
		this.personalIntro = personalIntro;
		this.state = state;
	}

	public User(String email, String userPassword) {
		this.email=email;
		this.userPassword=userPassword;
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

	@Column(name = "nick_name", length = 50)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "real_name", length = 50)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone_number", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "sex")
	public Boolean getSex() {
		return this.sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@Column(name = "qq_number", length = 50)
	public String getQqNumber() {
		return this.qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	@Column(name = "phone_state")
	public Boolean getPhoneState() {
		return this.phoneState;
	}

	public void setPhoneState(Boolean phoneState) {
		this.phoneState = phoneState;
	}

	@Column(name = "email_state")
	public Boolean getEmailState() {
		return this.emailState;
	}

	public void setEmailState(Boolean emailState) {
		this.emailState = emailState;
	}

	@Column(name = "user_password", length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "head_image", length = 100)
	public String getHeadImage() {
		return this.headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@Column(name = "personal_intro", length = 200)
	public String getPersonalIntro() {
		return this.personalIntro;
	}

	public void setPersonalIntro(String personalIntro) {
		this.personalIntro = personalIntro;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}