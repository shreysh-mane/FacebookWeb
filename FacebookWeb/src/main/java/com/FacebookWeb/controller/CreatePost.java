package com.FacebookWeb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.FacebookWeb.entity.FacebookPostdata;
import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.service.FacebookServiceInterface;
import com.FacebookWeb.utility.ServiceFactory;

/**
 * Servlet implementation class CreatePost
 */
@MultipartConfig
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=request.getParameter("postMessage");
		
		Part part=request.getPart("postPics");
		 System.out.println(part.getSubmittedFileName());
		 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");  
	    Date date = new Date();
	    String[] dateTime= formatter.format(date).split(" ");	
	    String[] time=dateTime[1].split(":");
	  
	    String fileName=dateTime[0]+time[0]+time[1]+time[2];
		System.out.println(fileName);
		
		HttpSession hs=request.getSession(true);
		String uid=(String) hs.getAttribute("userid");
		System.out.println(uid);
		
		
		try {
			if(part.getSize()!=0 && uid!=null){
	
		String user_name = uid.split("\\.")[0];	
			
		InputStream is=part.getInputStream();
		byte[] data=new byte[is.available()];
		
		is.read(data);
		
		 String path= "D:\\code\\javaCode1\\FbWeb\\FacebookWeb\\src\\main\\webapp\\img";
		path+="\\"+user_name;
		
		File fol = new File(path); 
		System.out.println(fol.mkdirs());
	
		String image =fileName+".jpg";
		String file=path+File.separator+image;
		System.out.println(file);
		
		File myPostFile = new File(file);
	
		FileOutputStream fos=new FileOutputStream(myPostFile);
		fos.write(data);
		
		
		FacebookPostdata fbpost=new FacebookPostdata();
		fbpost.setCreatedate(dateTime[0]);
		fbpost.setImage(image);
		fbpost.setMessage(message);
		fbpost.setCreatorEmail(uid);
		
//		FacebookUser fbu=new FacebookUser();
//		fbu.setEmail(uid);
//		fbpost.setFbuser(fbu);
		

		
		
		
		System.out.println(dateTime[0]);
		System.out.println(image);
		System.out.println(message);
		System.out.println(uid);
		
		System.out.println("gone to service");
		FacebookServiceInterface fbs=ServiceFactory.createObject();
		int i=fbs.createPost(fbpost);
		if(i>0) {
			System.out.println("post created" );
		}
		
		}
		
	 }catch(Exception e) {
			e.printStackTrace();
		}
	}

}
