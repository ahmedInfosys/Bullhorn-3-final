package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.DBUtil;

 

public class UserDB {
	
	public static List<Comment> select_all(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select c from Comment c ";
		TypedQuery <Comment> List_of_table = em.createQuery(qString, Comment.class);
		
		List<Comment> list_of_comments;
		try{
			list_of_comments = List_of_table.getResultList();
			if(list_of_comments == null || list_of_comments.isEmpty()){
				list_of_comments = null;
			}
		}finally{
			em.close();
		}
		return list_of_comments;
	}
	
	public static User select_single(String Email, String Password){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select u from User u where u.email = :Email" +
				" and u.zipcode = :Password"  ;
		TypedQuery <User> q = em.createQuery(qString, User.class);
		
		q.setParameter("Email", Email);
		q.setParameter("Password", Password);
		
		User user = null;
		try{
			user = q.getSingleResult();
			
		}catch(NoResultException e){
			System.out.println(e);
		}finally{
			em.clear();
		}
		return user;
	}
	
	public static User select_single_id(long id){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		//String qString = "select u from User u where u.id = :ID";
		//TypedQuery <User> q = em.createQuery(qString, User.class);
		
		//q.setParameter("ID", id);
		User user = new User();
		try{
//			user = q.getSingleResult();
			System.out.println(id + " from user db");
			user = em.find(User.class, id);
			
			System.out.println(" From User DB " + user.getFirstname());
			
		}catch(NoResultException e){
			e.printStackTrace();
		}finally{
			em.clear();
		}
		return user;
	}
	
//	public static User select_filter(long id){
//		EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
//		User user = new User();
//		try {    
//				
//				model.User cust = em.find(model.User.class, id);   
//				
//				user.setId(cust.getId());
//				user.setFirstname(cust.getFirstname());
//				user.setLastname(cust.getLastname());
//				user.setEmail(cust.getEmail());
//				user.setZipcode(cust.getZipcode());
//  
//			} catch (Exception e){    
//				System.out.println(e);   
//			} finally {   
//				em.close(); 	
//			} 
//		return user;
//	}
	

	public static void insert(User comment) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			em.persist(comment);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);	
			trans.rollback();
		} finally {
			em.close();
		}
	}
		
	public static void update(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
		
	public static void delete(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		} 
	}


}