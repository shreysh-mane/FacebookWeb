package com.FacebookWeb.dao;

import java.util.List;

import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.entity.FacebookPostdata;
import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.entity.Friends;
import com.FacebookWeb.service.FacebookServiceInterface;

public interface FacebookDAOInterface {

	int createProfileDAO(FacebookUser fb, FacebookLogin fbl);

	int loginProfileDAO(FacebookLogin fbl);

	FacebookUser loadProfile(FacebookUser fbu);

	int updateProfile(FacebookUser fbu, FacebookLogin fbl);

	int createPost(FacebookPostdata fbpost);

	int deleteProfileDAO(FacebookUser fbu);

	Friends loadFriendStatusDAO(Friends tempFriend);

	int addFriendDAO(Friends tempFriend);

	int removeFriendDAO(Friends tempFriend);

	int blockPerson(FacebookLogin fbl);

	int removeBlock(FacebookLogin fbl);

	List<FacebookLogin> viewAllUserDAO();

	

}
