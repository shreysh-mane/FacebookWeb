package com.FacebookWeb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Friends {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fid;
	private String em1;
	private String em2;
	private int accept;
	
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public String getEm1() {
		return em1;
	}
	public void setEm1(String em1) {
		this.em1 = em1;
	}
	public String getEm2() {
		return em2;
	}
	public void setEm2(String em2) {
		this.em2 = em2;
	}
	public int getAccept() {
		return accept;
	}
	public void setAccept(int accept) {
		this.accept = accept;
	}
}
