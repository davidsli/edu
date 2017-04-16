package com.hp.webedu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//����Ա��
@Entity
@Table(name = "t_admin", catalog = "eduweb")
public class Admin implements java.io.Serializable {

	private String id; 
	private String userName;//�û���
	private String password1;//����
	private String level1;//����2����������Ա��1������ͨ����Ա
	private String school;//ѧУ
	private Boolean state=true;//�Ƿ���Ч��Ĭ����Ч

	public Admin() {
	}

	public Admin(String id) {
		this.id = id;
	}

	public Admin(String id, String userName, String password1, String level1,
			String school, Boolean state) {
		this.id = id;
		this.userName = userName;
		this.password1 = password1;
		this.level1 = level1;
		this.school = school;
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

	@Column(name = "user_name", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password1", length = 50)
	public String getPassword1() {
		return this.password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	@Column(name = "level1", length = 1)
	public String getLevel1() {
		return this.level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	@Column(name = "school", length = 50)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}