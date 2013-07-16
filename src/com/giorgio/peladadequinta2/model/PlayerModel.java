package com.giorgio.peladadequinta2.model;

public class PlayerModel {
	int id;
	String name;
	int quality;
	boolean status;
	boolean isGoalkeeper;
	
	public PlayerModel(int id, String name, int quality, boolean status, boolean isGoalkeeper) {
		this.id = id;
		this.name = name;
		this.quality = quality;
		this.status = status;
		this.isGoalkeeper = isGoalkeeper;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getQuality() {
		return this.quality;
	}
	
	public void setQuality(int quality) {
		this.quality = quality;
	}

	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	

	public boolean getIsGoalkeeper() {
		return this.isGoalkeeper;
	}
	
	public void setIsGoalkeeper(boolean isGoalkeeper) {
		this.isGoalkeeper = isGoalkeeper;
	}
}
