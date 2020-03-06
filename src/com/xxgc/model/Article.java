package com.xxgc.model;

public class Article {
	private int arid;
	private int caid;
	private String arnumber;
	private String artitle;
	private String arimage;
	private String arcontent;
	private String aruser;
	private String artime;
	private String arstate;
	private int click;
	private String caids;
	private int message;
	
	
	
	
	
	
	
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	public String getCaids() {
		return caids;
	}
	public void setCaids(String caids) {
		this.caids = caids;
	}
	public int getArid() {
		return arid;
	}
	public void setArid(int arid) {
		this.arid = arid;
	}
	public int getCaid() {
		return caid;
	}
	public void setCaid(int caid) {
		this.caid = caid;
	}
	public String getArnumber() {
		return arnumber;
	}
	public void setArnumber(String arnumber) {
		this.arnumber = arnumber;
	}
	public String getArtitle() {
		return artitle;
	}
	public void setArtitle(String artitle) {
		this.artitle = artitle;
	}
	public String getArimage() {
		return arimage;
	}
	public void setArimage(String arimage) {
		this.arimage = arimage;
	}
	public String getArcontent() {
		return arcontent;
	}
	public void setArcontent(String arcontent) {
		this.arcontent = arcontent;
	}
	public String getAruser() {
		return aruser;
	}
	public void setAruser(String aruser) {
		this.aruser = aruser;
	}
	public String getArtime() {
		return artime;
	}
	public void setArtime(String artime) {
		this.artime = artime;
	}
	public String getArstate() {
		return arstate;
	}
	public void setArstate(String arstate) {
		this.arstate = arstate;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	@Override
	public String toString() {
		return "Article [arcontent=" + arcontent + ", arid=" + arid
				+ ", arimage=" + arimage + ", arnumber=" + arnumber
				+ ", arstate=" + arstate + ", artime=" + artime + ", artitle="
				+ artitle + ", aruser=" + aruser + ", caid=" + caid
				+ ", caids=" + caids + ", click=" + click + "]";
	}
	
	
	
	
}
