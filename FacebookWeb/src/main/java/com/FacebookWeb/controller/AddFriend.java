package com.FacebookWeb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FacebookWeb.entity.Friends;
import com.FacebookWeb.service.FacebookServiceInterface;
import com.FacebookWeb.utility.ServiceFactory;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddFriend
 */
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(true);
		String uid=(String) hs.getAttribute("userid");
		
//		method=loadFriend  methodType="removeFrined";  methodType="addFriend";
		String method=request.getParameter("method");
		String searchedProfile=request.getParameter("searchProfile");
		System.out.println(uid);
		System.out.println(searchedProfile);
		System.out.println(method);
		
		Friends tempFriend=new Friends();
		tempFriend.setEm1(uid);
		tempFriend.setEm2(searchedProfile);
		
		PrintWriter out=response.getWriter();
		FacebookServiceInterface fbs=ServiceFactory.createObject();
		
		if(method.equalsIgnoreCase("loadFriend")) {
			Friends friend=fbs.loadFriendStatus(tempFriend);
			if(friend==null) {
				friend=new Friends();

			}
			Gson json=new Gson();
			String friendJson=json.toJson(friend);
			
			out.print(friendJson);
			
		}else if(method.equalsIgnoreCase("removeFrined")) {
			
			int i=fbs.removeFriend(tempFriend);
			
			
		}else if(method.equalsIgnoreCase("addFriend")) {
			tempFriend.setAccept(1);
			int i=fbs.addFriend(tempFriend);
			
		}
			
		
	}
}
