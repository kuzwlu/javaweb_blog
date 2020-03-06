package com.xxgc.model;

public class MessageReplay {
	private int mrid;
	private int meid;
	private String mrcontent;
	private String mrtime;
	private int mruser;
	private int mrmanager;
	private String managername;
	private String username;
	
	
	
	
	public int getMrmanager() {
		return mrmanager;
	}
	public void setMrmanager(int mrmanager) {
		this.mrmanager = mrmanager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public int getMruser() {
		return mruser;
	}
	public void setMruser(int mruser) {
		this.mruser = mruser;
	}
	public int getMrid() {
		return mrid;
	}
	public void setMrid(int mrid) {
		this.mrid = mrid;
	}
	public int getMeid() {
		return meid;
	}
	public void setMeid(int meid) {
		this.meid = meid;
	}
	public String getMrcontent() {
		return mrcontent;
	}
	public void setMrcontent(String mrcontent) {
		this.mrcontent = mrcontent;
	}
	public String getMrtime() {
		return mrtime;
	}
	public void setMrtime(String mrtime) {
		this.mrtime = mrtime;
	}
	@Override
	public String toString() {
		return "MessageReplay [managername=" + managername + ", meid=" + meid
				+ ", mrcontent=" + mrcontent + ", mrid=" + mrid + ", mrtime="
				+ mrtime + ", mruser=" + mruser + "]";
	}
	
	
}
