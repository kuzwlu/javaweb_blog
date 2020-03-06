package com.xxgc.model;

public class Message {
	private int meid;
	private int arid;
	private String metitle;
	private int userid;
	private	int managerid;
	private String mecontent;
	private String metime;
	private String mestate;
	private String artitle;
	private String managername;
	private String username;
	
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public String getArtitle() {
		return artitle;
	}
	public void setArtitle(String artitle) {
		this.artitle = artitle;
	}
	public int getMeid() {
		return meid;
	}
	public void setMeid(int meid) {
		this.meid = meid;
	}
	public int getArid() {
		return arid;
	}
	public void setArid(int arid) {
		this.arid = arid;
	}
	public String getMetitle() {
		return metitle;
	}
	public void setMetitle(String metitle) {
		this.metitle = metitle;
	}
	public String getMecontent() {
		return mecontent;
	}
	public void setMecontent(String mecontent) {
		this.mecontent = mecontent;
	}
	public String getMetime() {
		return metime;
	}
	public void setMetime(String metime) {
		this.metime = metime;
	}
	public String getMestate() {
		return mestate;
	}
	public void setMestate(String mestate) {
		this.mestate = mestate;
	}
	@Override
	public String toString() {
		return "Message [arid=" + arid + ", mecontent=" + mecontent + ", meid="
				+ meid + ", mestate=" + mestate + ", metime=" + metime
				+ ", metitle=" + metitle + "]";
	}
	
	
}
