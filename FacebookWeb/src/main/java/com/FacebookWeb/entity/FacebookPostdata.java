package com.FacebookWeb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FacebookPostdata {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pid1;
	private String Message;
	private String image;
	private String createdate;
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private FacebookUser fbuser;
	
	private String creatorEmail;
	
	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public long getPid1() {
		return pid1;
	}

	public void setPid1(long pid1) {
		this.pid1 = pid1;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	} 
//	public FacebookUser getFbuser() {
//		return fbuser;
//	}
//
//	public void setFbuser(FacebookUser fbuser) {
//		this.fbuser = fbuser;
//	}
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



}
