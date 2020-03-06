package com.xxgc.model;

public class User {
	private int userid;
	private String username;
	private String passwd;
	private String userstate;
	private String userimage;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUserstate() {
		return userstate;
	}
	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	@Override
	public String toString() {
		return "User [passwd=" + passwd + ", userid=" + userid + ", userimage="
				+ userimage + ", username=" + username + ", userstate="
				+ userstate + "]";
	}
	
	
	
}
