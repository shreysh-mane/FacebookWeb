package com.FacebookWeb.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.FacebookWeb.entity.FacebookLogin;
import com.FacebookWeb.entity.FacebookPostdata;
import com.FacebookWeb.entity.FacebookUser;
import com.FacebookWeb.entity.Friends;
import com.FacebookWeb.service.FacebookServiceInterface;

public class FacebookDAO implements FacebookDAOInterface {
	private SessionFactory sf;

	public FacebookDAO() {
		sf = new Configuration().configure().buildSessionFactory();
	}
	@Override
	public int createProfileDAO(FacebookUser fb, FacebookLogin fbl) {
		int i=0;

		try {
			Session session=sf.openSession();
			session.beginTransaction();
			session.save(fb);
			session.save(fbl);
			session.getTransaction().commit();
			session.close();
			i=1;
		}catch(Exception e){
		}
		return i;
	}
	@Override
	public int loginProfileDAO(FacebookLogin fbl) {
		int i = 0;
		try {
		Session s = sf.openSession();
		Query q = s.createQuery("select f.email from com.FacebookWeb.entity.FacebookLogin f where f.email=:em and f.password=:pw");
		q.setParameter("em", fbl.getEmail());
		q.setParameter("pw", fbl.getPassword());
		
		String email = (String) q.getSingleResult();
		s.close();
		if (email != null) {
			i = 1;
		}
		}catch(Exception e) {
			
		}
		return i;
	}
	@Override
	public FacebookUser loadProfile(FacebookUser fbu) {
		FacebookUser fbuData=null;
		try {
			Session s = sf.openSession();
			Query q = s.createQuery("from com.FacebookWeb.entity.FacebookUser f where f.email=:em");
			q.setParameter("em", fbu.getEmail());
			fbuData = (FacebookUser) q.getSingleResult();
			System.out.println(fbuData.getName());
			s.close();
			}catch(Exception e) {
				fbuData=null;
			}
		
		return fbuData;
	}
	@Override
	public int updateProfile(FacebookUser fbu, FacebookLogin fbl) {
		int i = 0;
		try {
		Session s = sf.openSession();
		s.getTransaction().begin();
		Query queryUpdatePassword = s.createQuery("UPDATE com.FacebookWeb.entity.FacebookLogin f SET f.password=:pw where f.email=:em ");
		queryUpdatePassword.setParameter("em", fbl.getEmail());
		queryUpdatePassword.setParameter("pw", fbl.getPassword());
		i = queryUpdatePassword.executeUpdate();
		s.getTransaction().commit();
		s.close();
		
		Session s1=sf.openSession();
		s1.getTransaction().begin();
		Query queryUpdateProfile=s1.createQuery("UPDATE com.FacebookWeb.entity.FacebookUser f SET f.name=:nm, f.age=:ag, f.city=:ci, f.gender=:ge,f.photo=:ph where f.email=:em ");
		queryUpdateProfile.setParameter("nm", fbu.getName());
		queryUpdateProfile.setParameter("ag", fbu.getAge());
		queryUpdateProfile.setParameter("ci", fbu.getCity());
		queryUpdateProfile.setParameter("em", fbu.getEmail());
		queryUpdateProfile.setParameter("ge", fbu.getGender());
		queryUpdateProfile.setParameter("ph", fbu.getPhoto());

		i=queryUpdateProfile.executeUpdate();
		s1.getTransaction().commit();
		s1.close();
		
		
		}
		catch(Exception e) {
			System.out.println(e);
			i=0;
		}
		
		return i;
		
	}
	@Override
	public int createPost(FacebookPostdata fbpost) {
		
		
		  FacebookUser fbuData=null;
		  
		  int i=0; 
		  try { 
			  Session s = sf.openSession(); 
			  EntityTransaction et=s.getTransaction();
			  et.begin();
			  s.save(fbpost);
			  et.commit();
			  i=1;
//			  Query q=s.createQuery("from com.FacebookWeb.entity.FacebookUser f where f.email=:em"); 
//			  q.setParameter("em", fbpost.getFbuser().getEmail());
//			  fbuData=(FacebookUser) q.getSingleResult(); 
//			  System.out.println(fbuData.getName());
//			  s.close();
//			  
//			  fbuData.getFbpost().add(fbpost);
//			  fbpost.setFbuser(fbuData);
//			  Session s1 = sf.openSession(); 
//			  s1.getTransaction().begin(); 
//			  s1.save(fbpost);
//			  s1.getTransaction().commit();
//			  s1.close();
//			  System.out.println("post creted");
//			  
//			  
//			  Session s2 = sf.openSession();
//			  s2.getTransaction().begin(); 
//			  Query queryUpdateProfilePost=s2.createQuery("UPDATE com.FacebookWeb.entity.FacebookUser f SET  where f.fbpost=:fbp "); 
//			  queryUpdateProfilePost.setParameter("fbp",fbuData.getFbpost());
//			  i=queryUpdateProfilePost.executeUpdate(); 
//			  s2.getTransaction().commit();
//			  s2.close();
//			  System.out.println("profile");
		  }catch(Exception e) { 
			  fbuData=null; 
		}
		return i;
	
		 
		
		
		/*
		 * int i=0; try { Session session=sf.openSession(); session.beginTransaction();
		 * session.save(fbpost); session.getTransaction().commit(); session.close();
		 * i=1; }catch(Exception e){ i=0; } return i;
		 */
		
	
	}
	@Override
	public int deleteProfileDAO(FacebookUser fbu) {
		int i = 0;
		try {
		Session s = sf.openSession();
		s.getTransaction().begin();		
		Query queryDeleteLogin= s.createQuery("DELETE FROM com.FacebookWeb.entity.FacebookLogin f where f.email=:em ");
		Query queryDeleteProfile= s.createQuery("DELETE FROM com.FacebookWeb.entity.FacebookUser f where f.email=:em ");
		queryDeleteLogin.setParameter("em", fbu.getEmail());
		queryDeleteProfile.setParameter("em", fbu.getEmail());
		i = queryDeleteLogin.executeUpdate();
		i = queryDeleteProfile.executeUpdate();
		s.getTransaction().commit();
		s.close();
		}
		catch(Exception e) {
			System.out.println(e);
			i=0;
		}
		
		return i;
	}
	@Override
	public Friends loadFriendStatusDAO(Friends tempFriend) {
		Friends friend=null;
		try {
			Session s = sf.openSession();
			Query q = s.createQuery("from com.FacebookWeb.entity.Friends f where ((f.em1=:emm1 and f.em2=:emm2) or (f.em1=:emm2 and f.em2=:emm1)) and f.accept=1");
			q.setParameter("emm1", tempFriend.getEm1());
			q.setParameter("emm2", tempFriend.getEm2());
			friend = (Friends) q.getSingleResult();
			s.close();
		}	catch(Exception e) {
			System.out.println("check friend status error ");
			friend=null;
		}
		return friend;
	}
	@Override
	public int addFriendDAO(Friends tempFriend) {
		int i=0;

		try {
			Session session=sf.openSession();
			session.beginTransaction();
			session.save(tempFriend);
			session.getTransaction().commit();
			session.close();
			i=1;
		}catch(Exception e){
			System.out.println("Error add Friend");
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int removeFriendDAO(Friends tempFriend) {
		int i = 0;
		try {
		Session s = sf.openSession();
		s.getTransaction().begin();		
		Query queryDeleteFriend = s.createQuery("DELETE from com.FacebookWeb.entity.Friends f where ((f.em1=:emm1 and f.em2=:emm2) or (f.em1=:emm2 and f.em2=:emm1))");
		queryDeleteFriend.setParameter("emm1", tempFriend.getEm1());
		queryDeleteFriend.setParameter("emm2", tempFriend.getEm2());
		i = queryDeleteFriend.executeUpdate();
		s.getTransaction().commit();
		s.close();
		}
		catch(Exception e) {
			System.out.println("Error delete profile");
			System.out.println(e);
			i=0;
		}
		
		return i;
	}
	@Override
	public int blockPerson(FacebookLogin fbl) {
		int i=0;
		try {
			Session s = sf.openSession();
			s.getTransaction().begin();		
			Query queryDeleteFriend = s.createQuery("UPDATE com.FacebookWeb.entity.FacebookLogin f SET f.block=1 where f.email=:em");
			queryDeleteFriend.setParameter("em", fbl.getEmail());
			i = queryDeleteFriend.executeUpdate();
			s.getTransaction().commit();
			s.close();
			}
			catch(Exception e) {
				System.out.println("Error blockPerson profile");
				System.out.println(e);
				i=0;
			}
		
		
		return i;
	}
	@Override
	public int removeBlock(FacebookLogin fbl) {
		int i=0;
		try {
			Session s = sf.openSession();
			s.getTransaction().begin();		
			Query queryDeleteFriend = s.createQuery("UPDATE com.FacebookWeb.entity.FacebookLogin f SET f.block=0 where f.email=:em");
			queryDeleteFriend.setParameter("em", fbl.getEmail());
			i = queryDeleteFriend.executeUpdate();
			s.getTransaction().commit();
			s.close();
			}
			catch(Exception e) {
				System.out.println("Error removeBlock profile");
				System.out.println(e);
				i=0;
			}
		return i;
	}
	@Override
	public List<FacebookLogin> viewAllUserDAO() {
		List<FacebookLogin> fbl=null;
		try {
			Session s = sf.openSession();
			s.getTransaction().begin();		
			Query queryALLUsers = s.createQuery("FROM com.FacebookWeb.entity.FacebookLogin f");
			fbl = queryALLUsers.getResultList();
			s.getTransaction().commit();
			s.close();
			}
			catch(Exception e) {
				System.out.println("Error removeBlock profile");
				System.out.println(e);
				fbl=null;
			}
		return fbl;
	}
	

}
