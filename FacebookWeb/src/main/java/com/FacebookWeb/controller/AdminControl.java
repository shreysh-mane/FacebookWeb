package com.FacebookWeb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.service.FacebookServiceInterface;
import com.FacebookWeb.utility.ServiceFactory;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminControl
 */
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String methods=request.getParameter("method");
		PrintWriter out=response.getWriter();
		
		String email=request.getParameter("email");
		FacebookLogin fbl=new FacebookLogin();
		fbl.setEmail(email);
		
		if(methods.equals("BlockUser")) {
			System.out.println("block");
			FacebookServiceInterface fbs=ServiceFactory.createObject();
			int i=fbs.blockPerson(fbl);
		}else if(methods.equals("UnblockUser")) {
			System.out.println("Unblock");
			FacebookServiceInterface fbs=ServiceFactory.createObject();
			int i=fbs.removeBlock(fbl);
		}else if(methods.equals("getdata")) {
			FacebookServiceInterface fbs=ServiceFactory.createObject();
			List<FacebookLogin>fbls=fbs.viewAllUser();
			
			for(FacebookLogin fblogin:fbls) {
				System.out.println(fblogin);
			}
			Gson json=new Gson();
			String UsersList=json.toJson(fbls);
			System.out.println(UsersList);
			out.print(UsersList);
		}
		
		
	}

}
