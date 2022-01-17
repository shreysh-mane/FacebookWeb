package com.FacebookWeb.service;

import java.util.List;

import com.FacebookWeb.dao.FacebookDAOInterface;
import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.entity.FacebookPostdata;
import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.entity.Friends;
import com.FacebookWeb.utility.DAOFactory;

public class FacebookService implements FacebookServiceInterface {

	private FacebookDAOInterface fd;
	public FacebookService() {
		fd=DAOFactory.createObject();
	}
	
	@Override
	public int createProfileService(FacebookUser fb, FacebookLogin fbl) {
		return fd.createProfileDAO(fb,fbl);
	}

	@Override
	public int loginProfileService(FacebookLogin fbl) {
		// TODO Auto-generated method stub
		return fd.loginProfileDAO(fbl);
	}

	@Override
	public FacebookUser loadProfile(FacebookUser fbu) {
		// TODO Auto-generated method stub
		return fd.loadProfile(fbu);
	}

	@Override
	public int updateProfile(FacebookUser fbu, FacebookLogin fbl) {
		// TODO Auto-generated method stub
		return fd.updateProfile(fbu,fbl);
	}

	@Override
	public int createPost(FacebookPostdata fbpost) {
		
		return fd.createPost(fbpost);
	}

	@Override
	public int deleteProfile(FacebookUser fbu) {
		// TODO Auto-generated method stub
		return fd.deleteProfileDAO(fbu);
	}

	@Override
	public Friends loadFriendStatus(Friends tempFriend) {
		// TODO Auto-generated method stub
		return fd.loadFriendStatusDAO(tempFriend);
	}

	@Override
	public int addFriend(Friends tempFriend) {
		// TODO Auto-generated method stub
		return fd.addFriendDAO(tempFriend);
	}

	@Override
	public int removeFriend(Friends tempFriend) {
		// TODO Auto-generated method stub
		return fd.removeFriendDAO(tempFriend);
	}

	@Override
	public int blockPerson(FacebookLogin fbl) {
		// TODO Auto-generated method stub
		return fd.blockPerson(fbl);
	}

	@Override
	public int removeBlock(FacebookLogin fbl) {
		// TODO Auto-generated method stub
		return fd.removeBlock(fbl);
	}

	@Override
	public List<FacebookLogin> viewAllUser() {
		// TODO Auto-generated method stub
		return fd.viewAllUserDAO();
	}

	
}
