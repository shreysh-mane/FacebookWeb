package com.FacebookWeb.service;

import java.util.List;

import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.entity.FacebookPostdata;
import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.entity.Friends;

public interface FacebookServiceInterface {

	int createProfileService(FacebookUser fb, FacebookLogin fbl);

	int loginProfileService(FacebookLogin fbl);

	FacebookUser loadProfile(FacebookUser fbu);

	int updateProfile(FacebookUser fbu, FacebookLogin fbl);

	int createPost(FacebookPostdata fbpost);

	int deleteProfile(FacebookUser fbu);

	Friends loadFriendStatus(Friends tempFriend);

	int addFriend(Friends tempFriend);

	int removeFriend(Friends tempFriend);

	int blockPerson(FacebookLogin fbl);

	int removeBlock(FacebookLogin fbl);

	List<FacebookLogin> viewAllUser();



	

	

}
