package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.DBUtil;
import model.Post;

 

public class PostDB {
	
	
	public static List<Post> select_all(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select p from Post p ";
		
		TypedQuery <Post> List_of_table = em.createQuery(qString, Post.class);
		List<Post> list_of_posts;
		try{
			list_of_posts = List_of_table.getResultList();
			if(list_of_posts == null || list_of_posts.isEmpty()){
				list_of_posts = null;
			}
		}finally{
			em.close();
		}
		return list_of_posts;
	}
	
	public static List<Post> select_all_by_user(long id){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select p from Post p where p.postUserId = :ID";
		
		TypedQuery <Post> List_of_table = em.createQuery(qString, Post.class);
		List_of_table.setParameter("ID", id);
		List<Post> list_of_posts;
		try{
			list_of_posts = List_of_table.getResultList();
			if(list_of_posts == null || list_of_posts.isEmpty()){
				list_of_posts = null;
			}
		}finally{
			em.close();
		}
		return list_of_posts;
	}
	
	public static Post select_single_post(long post_id){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		Post post = new Post();
		try{

			post = em.find(Post.class, post_id);
		}catch(NoResultException e){
			e.printStackTrace();
		}finally{
			em.clear();
		}
		return post;
	}
	
	public static List<Post> Search(String search){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select p from Post p where lower(p.postContent) like :search ";

		TypedQuery <Post> List_of_table = em.createQuery(qString, Post.class);

		List_of_table.setParameter("search","%" + search + "%");
		
		List<Post> list_of_posts;
		try{
			list_of_posts = List_of_table.getResultList();
			if(list_of_posts == null || list_of_posts.isEmpty()){
				list_of_posts = null;
			}
		}finally{
			em.close();
		}
		return list_of_posts;
	}
	
	public static void insert(Post post) {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			em.persist(post);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);	
			trans.rollback();
		} finally {
			em.close();
		}

	}
		
	public static void update(Post post) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			em.merge(post);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
		
	public static void delete(Post post) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try {
			em.remove(em.merge(post));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		} 
	}


}