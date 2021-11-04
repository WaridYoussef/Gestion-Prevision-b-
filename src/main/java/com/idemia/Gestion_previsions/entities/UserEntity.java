package com.idemia.Gestion_previsions.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@Table(name = "user")
public class UserEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3058098686728906793L;

	

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, unique = true)
	private String userId;
	@Column(nullable = false , length = 50)
	private String firstName;
	@Column(nullable = false , length = 50)
	private String lastName;
	@Column(nullable = false , length = 120, unique=true)
	private String email;
	@Column(nullable = true)
	private Boolean admin;
	@Column(nullable = true)
	private String managerId;
	@Column(nullable = false)
	private String encryptedpassword;
	
	
	
	
	
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AffectationEntity> affectations;
	
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedpassword() {
		return encryptedpassword;
	}
	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}
	

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	
	
	
	
	
	
	
}
