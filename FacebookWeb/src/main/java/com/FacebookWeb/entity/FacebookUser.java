package com.FacebookWeb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FacebookUser {
	@Id
	private String email;
	private String name;
	private String gender;
	private String city;
	private String photo;
	private String age;
	
	
//	@OneToMany(mappedBy = "fbuser")
//	private List<FacebookPostdata> fbpost;
//	 
//	@Override
//	public String toString() {
//		return "FacebookUser [email=" + email + ", name=" + name + ", gender=" + gender + ", city=" + city + ", photo="
//				+ photo + ", age=" + age + "]";
//	}
//	public List<FacebookPostdata> getFbpost() {
//		return fbpost;
//	}
//	public void setFbpost(List<FacebookPostdata> fbpost) {
//		this.fbpost = fbpost;
//	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
