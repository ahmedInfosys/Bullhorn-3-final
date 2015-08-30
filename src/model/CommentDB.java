package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.DBUtil;

 

public class CommentDB {
	
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
	
	public static List<Comment> select_all_by_id(long post_id){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select c from Comment c where c.postId = :ID";
		TypedQuery <Comment> List_of_table = em.createQuery(qString, Comment.class);
		List_of_table.setParameter("ID", post_id);
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

	public static void insert(Comment comment) {
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
		
	public static void update(Comment comment, long value) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			comment.setPostId(value);
			em.merge(comment);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
		
	public static void delete(Comment user) {
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