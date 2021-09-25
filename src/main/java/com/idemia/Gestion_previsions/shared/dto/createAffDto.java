package com.idemia.Gestion_previsions.shared.dto;

import java.io.Serializable;

public class createAffDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1718732368214068036L;
	private long id;
	private long user_id;
	private long activity_id;
	private String mois;
	private String semaine;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
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
	
	
	
	
}
