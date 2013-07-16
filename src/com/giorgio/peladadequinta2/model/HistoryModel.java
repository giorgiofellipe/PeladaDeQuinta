package com.giorgio.peladadequinta2.model;

import java.util.Date;

public class HistoryModel {
	Date matchdate;
	String firstteam;
	String secondteam;
	
	public HistoryModel(Date matchdate, String firstteam, String secondteam) {
		this.matchdate = matchdate;
		this.firstteam = firstteam;
		this.secondteam = secondteam;
	}
	
	public Date getMatchDate() {
		return this.matchdate;
	}
	
	public void setMatchDate(Date matchdate) {
		this.matchdate = matchdate;
	}
	
	public String getFirstTeam() {
		return this.firstteam;
	}
	
	public void setFirstTeam(String firstteam) {
		this.firstteam = firstteam;
	}
	
	public String getSecondTeam() {
		return this.secondteam;
	}
	
	public void setSecondTeam(String secondteam) {
		this.secondteam = secondteam;
	}
}
