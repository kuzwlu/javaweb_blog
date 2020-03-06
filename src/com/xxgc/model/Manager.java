package com.xxgc.model;

public class Manager {
	private int managerid;
	private String managername;
	private String passwd;
	private String matate;
	private String maimage;
	
	
	
	public String getMaimage() {
		return maimage;
	}
	public void setMaimage(String maimage) {
		this.maimage = maimage;
	}
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMatate() {
		return matate;
	}
	public void setMatate(String matate) {
		this.matate = matate;
	}
	@Override
	public String toString() {
		return "Manager [managerid=" + managerid + ", managername="
				+ managername + ", matate=" + matate + ", passwd=" + passwd
				+ "]";
	}
	
	
}
