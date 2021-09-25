package com.idemia.Gestion_previsions.shared.dto;

import java.io.Serializable;

public class ActivityDto implements Serializable{

	private static final long serialVersionUID = 2022167436101386991L;

	
	private long id;
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
	
	
	
	
}
