

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comment;
import model.CommentDB;
import model.Post;
import model.PostDB;
import model.UserDB;

/**
 * Servlet implementation class Posts
 */
@WebServlet("/Posts")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Posts() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long post_id = 0, ID = 0;
		Post post_object = new Post();
		HttpSession session = request.getSession();
    	
		String post = "", collect_comments = "", FirstName,LastName, postDate,post_content, welcome = "";
		
		session = request.getSession(true);
		
		if(session.getAttribute("Post_User_ID") != request.getParameter("post_id") || request.getParameter("Post_User_ID") != null){
			
			if(request.getParameter("Post_User_ID") != null ||  request.getParameter("post_id") != null){
				ID = Long.parseLong(request.getParameter("Post_User_ID"));
				post_id = Long.parseLong(request.getParameter("post_id"));
				session.setAttribute("Post_User_ID", ID);
				session.setAttribute("post_id", post_id);
				System.out.println(post_id);
			}else{
				System.out.println("Comment please");
				ID = Long.parseLong(session.getAttribute("Post_User_ID").toString());
				post_id = Long.parseLong(session.getAttribute("post_id").toString());
			}
		}
		
		
		long my_id = Long.parseLong(session.getAttribute("UserID").toString());
		
		FirstName = UserDB.select_single_id(my_id).getFirstname();
		LastName = UserDB.select_single_id(my_id).getLastname();
		
		
		postDate = PostDB.select_single_post(post_id).getPostDate();
		
		//System.out.println(postDate);
		post_content = PostDB.select_single_post(post_id).getPostContent();

		
		welcome += FirstName + " " + LastName + " !";
		 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
		
	    FirstName = UserDB.select_single_id(ID).getFirstname();
		LastName = UserDB.select_single_id(ID).getLastname();
			
		//see if the post has comments
		 post+= "<nav class=\"navbar navbar-default col-sm-10\">" +
				 " <p class=\"navbar-text navbar-default\" ><b>Post: </b>"+ post_content + "</p><br>" 
				 + "<p class=\"navbar-text navbar-right\"> <a href=\"/user_profile\">" + FirstName + " " + LastName + "</a> <br>";
		 post+= "<b>"+ postDate + "</b></p></div><br><br><br>";//</nav>";
		 
		
		 
		 if(request.getParameter("comment") != null){
			 
			 	System.out.println("There is comment");
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
				
				String comment_date = sdf.format(now);
				String comment = request.getParameter("comment");

				FirstName = UserDB.select_single_id(my_id).getFirstname();
				LastName = UserDB.select_single_id(my_id).getLastname();
				
				
				
				Comment comment_obj = new Comment();
				comment_obj.setCommentDate(comment_date);
				comment_obj.setContentText(comment);
				comment_obj.setPostId(post_id);
				comment_obj.setUserId(my_id);
				
				CommentDB.insert(comment_obj);
			}
		 
		if(CommentDB.select_all_by_id(post_id) != null){
			String comment,comment_date;
			long comment_user_id;
			
			for(Comment comm:CommentDB.select_all_by_id(post_id)){
				comment_user_id = comm.getUserId();
				comment = comm.getContentText();
				comment_date = comm.getCommentDate();
				
				FirstName = UserDB.select_single_id(comment_user_id).getFirstname();
				LastName = UserDB.select_single_id(comment_user_id).getLastname();
				
				
				collect_comments+= "<nav class=\"navbar navbar-right col-sm-8\">" +
       				 " <p class=\"navbar-text navbar-default\" ><b>Comment: </b>"+ comment + "</p><br>" 
       				 + "<p class=\"navbar-text navbar-right\" <a href=\"/user_profile\">" + FirstName + " " + LastName + "</a> <br>";
				collect_comments+= "<b>"+ comment_date + "</b></p></div></nav>";
			}
			
		}
		
		post += collect_comments;
		post+= "</nav>";
		
		request.setAttribute("welcome", welcome);
		request.setAttribute("sign_in_out", Sign_out);
		request.setAttribute("post_with_comments", post);
		getServletContext().getRequestDispatcher("/posts.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
