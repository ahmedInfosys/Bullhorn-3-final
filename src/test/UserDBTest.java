package test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.Post;
import model.PostDB;
import model.User;
import model.UserDB;




import org.junit.Test;

public class UserDBTest {

	@Test
//	public void testSelect_single_id() {
//		System.out.println("in test");
//		long id = 5;
//		User user = UserDB.select_single_id(id);
//		
//		System.out.println("test user = " + user.getFirstname());
//	}
//	
//	public void testSelect_all() {
//		System.out.println("in test");
//
//		//assertTrue(UserDB.select_single("Ahmed", "800") != null );
//		System.out.println(PostDB.select_all_by_user(2).);
//	}
	
	
	public void test_insert_post()  throws ServletException, IOException {
//		Post post  = new Post();
//		//PostDB pDB = new PostDB();
//		post.setPostDate("dd/mm/yyyy");
//		post.setPostContent(" dffrf");
//		post.setPostUserId(1);
//		
//		PostDB.insert(post);
		
		//long ID = Long.parseLong();
		
//		String postDate;
//		String email =;
		//System.out.println(UserDB.Isvalid("nadia@hameed.com", "80222").getEmail());
		System.out.println(UserDB.ExistsorNot("nadia@hameed.com").get(1).getEmail());
		
		//assertTrue(PostDB.select_all_by_user(0).isEmpty());
				
	}

}
