package com.FacebookWeb.utility;

import com.FacebookWeb.service.FacebookService;
import com.FacebookWeb.service.FacebookServiceInterface;

public class ServiceFactory {
	private ServiceFactory() {}
	private static FacebookServiceInterface fbS;
	public static FacebookServiceInterface createObject() {
		// TODO Auto-generated method stub
		if(fbS==null) {
			fbS=new  FacebookService();
		}
		return fbS;
	}

}
