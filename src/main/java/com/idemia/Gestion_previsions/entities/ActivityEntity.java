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
@Table(name = "Activity")
public class ActivityEntity implements Serializable{

	private static final long serialVersionUID = 8251510000648090655L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length = 50)
	private String name;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	@OneToMany(mappedBy = "activityEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AffectationEntity> affectations;

	

	

}
