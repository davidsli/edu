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

//�γ̱�
@Entity
@Table(name = "t_subject", catalog = "eduweb")
public class Subject implements java.io.Serializable {

	private String id;
	private String subName;
	private Boolean state=true;

	
	public Subject(String subName) {
		this.subName = subName;
	}

	public Subject() {
	}

	public Subject(String id, String subName, Boolean state) {
		this.id = id;
		this.subName = subName;
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

	@Column(name = "sub_name", length = 100)
	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}


}