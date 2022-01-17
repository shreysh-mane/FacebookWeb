package com.FacebookWeb.utility;

import com.FacebookWeb.dao.FacebookDAO;
import com.FacebookWeb.dao.FacebookDAOInterface;

public class DAOFactory {
	private DAOFactory() {}
	private static FacebookDAOInterface fbd;
	public static FacebookDAOInterface createObject() {
		if(fbd==null) {
			fbd=new  FacebookDAO();
		}
		return fbd;
	}

}
