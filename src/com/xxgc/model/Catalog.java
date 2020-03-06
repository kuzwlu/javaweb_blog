package com.xxgc.model;

public class Catalog {
	private int caid;
	private String caname;
	private String canumber;
	private String castate;
	public int getCaid() {
		return caid;
	}
	public void setCaid(int caid) {
		this.caid = caid;
	}
	public String getCaname() {
		return caname;
	}
	public void setCaname(String caname) {
		this.caname = caname;
	}
	public String getCanumber() {
		return canumber;
	}
	public void setCanumber(String canumber) {
		this.canumber = canumber;
	}
	public String getCastate() {
		return castate;
	}
	public void setCastate(String castate) {
		this.castate = castate;
	}
	@Override
	public String toString() {
		return "Catalog [caid=" + caid + ", caname=" + caname + ", canumber="
				+ canumber + ", castate=" + castate + "]";
	}
	
}
