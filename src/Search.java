

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import model.PostDB;
import model.UserDB;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post_date, single_post, FirstName, LastName;
		long post_id, post_user_id;
		
		HttpSession session = request.getSession(true);
		long ID = Long.parseLong(session.getAttribute("UserID").toString());
		String posts = "", welcome = "";
		
		FirstName = UserDB.select_single_id(ID).getFirstname();
		LastName = UserDB.select_single_id(ID).getLastname();
		welcome += FirstName + " " + LastName + " !";
		
		 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
		
		 if(request.getParameter("search") != null){
			 String input = request.getParameter("search").toLowerCase();
			 
			 if(PostDB.Search(input) != null){
				 for(Post post:PostDB.Search(input)){
		    		 post_date = post.getPostDate();
		    		 single_post = post.getPostContent();
		    		 FirstName = UserDB.select_single_id(post.getPostUserId()).getFirstname();
		    		 LastName = UserDB.select_single_id(post.getPostUserId()).getLastname();
		    		 
		    		 post_id = post.getPostId();
		    		 post_user_id = post.getPostUserId();
		    		 posts+= "<nav class=\"navbar navbar-default col-sm-10\">" +
		    				 " <p class=\"navbar-text navbar-default\" ><b>Post: </b>"+ single_post + "</p><br>" 
		    				 + "<p class=\"navbar-text navbar-right\"> <a href=\"user_profile?user_ID=" + post_user_id + "\">" + FirstName + " " + LastName + "</a> <br>";
		    		 posts+= "<b>"+ post_date + "</b><br><a  href=\"Posts?post_id=" +  post_id + "&Post_User_ID=" + post_user_id + "\">Click here to see comments </a></p></div></nav>";
		    		 
		    	 }
			 }
			 
		 }
		 
		 
		 
   	 
   	 	request.setAttribute("welcome",welcome);
   	    request.setAttribute("sign_in_out", Sign_out);
   	 	request.setAttribute("filtered_posts", posts);
		getServletContext().getRequestDispatcher("/Search.jsp").forward(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
