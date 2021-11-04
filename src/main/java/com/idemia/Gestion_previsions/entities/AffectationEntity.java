package com.idemia.Gestion_previsions.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="affectation")
public class AffectationEntity implements Serializable{

	
	private static final long serialVersionUID = -8424267838649338063L;
	
	@Transient
	private String firstName;
	
	@Transient
	private String LastName;
	
	@Transient
	private String activity_name;
	
	
	@Id
	@GeneratedValue
	private long id;
	@Column(name="activity_id")
	private long activity_id;
	@Column(name = "user_id")
	private long user_id;
	@Column(nullable = false)
	private String mois;
	@Column(nullable = false)
	private String semaine;
	@Column
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userEntity;
	
	
	
	@ManyToOne
	@JoinColumn(name = "activity_id", insertable = false, updatable = false)
	private ActivityEntity activityEntity;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public String getSemaine() {
		return semaine;
	}
	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}
	public String getFirstName() {
		return this.userEntity.getFirstName();
	}
	public String getActivity_name() {
		return this.activityEntity.getName();
	}
	public String getLastName() {
		return this.userEntity.getLastName();
	}
	
	
	
	
	
	
}
