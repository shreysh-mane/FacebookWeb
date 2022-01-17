package com.FacebookWeb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.service.FacebookServiceInterface;
import com.FacebookWeb.utility.ServiceFactory;

/**
 * Servlet implementation class Signin
 */
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("signin");
		FacebookLogin fbl=new FacebookLogin();
		fbl.setEmail(request.getParameter("email"));
		fbl.setPassword(request.getParameter("password"));
		
		FacebookServiceInterface fs=ServiceFactory.createObject();
		int loginStatus=fs.loginProfileService(fbl);
		
		if(loginStatus>0) {
			
			HttpSession hs=request.getSession(true);
			hs.setAttribute("userid", request.getParameter("email"));
			
			if ( request.getParameter("email").equals("shreysh@gmail.com")) {			
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/admins.html");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/feed.html");
				rd.forward(request, response);
			}
						
		}
		
		else {

			RequestDispatcher rd=getServletContext().getRequestDispatcher("/signin.html");
			rd.include(request, response);
		}
		
		
		
		
		
	}

}
