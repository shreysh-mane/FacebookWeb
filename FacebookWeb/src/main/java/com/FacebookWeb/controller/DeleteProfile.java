package com.FacebookWeb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.service.FacebookServiceInterface;
import com.FacebookWeb.utility.ServiceFactory;

/**
 * Servlet implementation class DeleteProfile
 */
public class DeleteProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession hs=request.getSession(true);
		String uid=(String) hs.getAttribute("userid");
		
		if(uid!=null){
		FacebookServiceInterface fbs=ServiceFactory.createObject();
	
		
		FacebookUser fbu=new FacebookUser();
		fbu.setEmail(uid);
		
		
		
		int i=fbs.deleteProfile(fbu);
		if(i>0) {
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/signin.html");
			rd.forward(request, response);
		}
		}
	}

}
