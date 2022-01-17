package com.FacebookWeb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.service.FacebookServiceInterface;
import com.FacebookWeb.utility.ServiceFactory;

@MultipartConfig
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String city=request.getParameter("city");
		String age=request.getParameter("age");
	
		Part part=request.getPart("profilePic");
		
//		System.out.println(name);
//		System.out.println(part.getSubmittedFileName());
		
		HttpSession hs=request.getSession(true);
		String uid=(String) hs.getAttribute("userid");
		
		
		if(part.getSize()!=0 && uid!=null){
		try {
		String user_name = uid.split("\\.")[0];	
			
		InputStream is=part.getInputStream();
		byte[] data=new byte[is.available()];
		
		is.read(data);
		
//		String path=request.getRealPath("/")+"zz";
//				"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"img"+File.separator+user_name;		
		
		 String path= "D:\\code\\javaCode1\\FbWeb\\FacebookWeb\\src\\main\\webapp\\img";
		path+="\\"+user_name;
		System.out.println(path);
		
		File fol = new File(path); 
		fol.mkdir();
	
		String image ="profilePic.jpg";
		String file=path+File.separator+image;
	
		File myProfileFile = new File(file);
		System.out.println(myProfileFile.delete());


		FileOutputStream fos=new FileOutputStream(myProfileFile);
		fos.write(data);
		

		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
			FacebookUser fbu=new FacebookUser();
			
			fbu.setAge(age);
			fbu.setEmail(uid);
			fbu.setCity(city);
			fbu.setName(name);
			fbu.setGender(gender);
			fbu.setPhoto("true");
			
			FacebookLogin fbl=new FacebookLogin();
			fbl.setPassword(password);
			fbl.setEmail(uid);
			
			FacebookServiceInterface fbs=ServiceFactory.createObject();
			int i=fbs.updateProfile(fbu,fbl);
			
		}
	}
}
