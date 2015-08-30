

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
import model.User;
import model.UserDB;

/**
 * Servlet implementation class user_profile
 */
@WebServlet("/user_profile")
public class user_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public user_profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  FirstName, LastName, Email, zipCode, welcome = " ";
		
		
		String  Password;
		String  invalid = "", create_an_account = "";
		
		
		String  post_date,single_post = "", people_details = "", posts = "", post_field = "";
		String  Page = "";
		long ID = 0, post_id, user_id = 0, post_user_id;
		
		SimpleDateFormat  sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
		
		
		if(request.getParameter("zip") != null){
			
			System.out.println("For Sign Up.");
			FirstName = request.getParameter("fName");
			LastName = request.getParameter( "lName" );
			Email=request.getParameter("email");
			zipCode= request.getParameter("zip");

			
			if(UserDB.ExistsorNot(Email).get(1) != null){
				Page = "/SignUp.jsp";
				String Already_Exists = "The user " + Email + " is already exists, please <a href=\"/Bullhorm/SignIn.jsp\"> sign in.</a>" ;
				request.setAttribute("alreadyin", Already_Exists);
			}else{
				Page = "/Profile.jsp";
				Date now = new Date();

				User user = new User();
				
				user.setFirstname(FirstName);
				user.setLastname(LastName);
				user.setEmail(Email);
				user.setZipcode(zipCode);
				user.setJoinDate(sdf.format(now));
				
				UserDB.insert(user);
				ID = user.getId();
			}
		}else if (request.getParameter("password") != null){
			
			System.out.println("For Sign in.");
			
			
			Email = request.getParameter("email");
			Password = request.getParameter("password");
			
			User single_user = new User();
			
			single_user = UserDB.Isvalid(Email, Password);
			System.out.println(single_user);
			
			if(single_user == null){
				
				System.out.println("Invalid");
				Page = "/SignIn.jsp";
				invalid = "Invalid Email or/and password";
				create_an_account = "<a href=\"/Bullhorm/SignUp.jsp\">Create an account</a>";
				request.setAttribute("invalid_user", invalid);
				//request.setAttribute("Sign_up", create_an_account);
			}
			else{
	        	HttpSession session = request.getSession();
	        	session.setAttribute("UserID", single_user.getId());
	        	Page = "/Profile.jsp";
	        	session = request.getSession(true);
				ID = Long.parseLong(session.getAttribute("UserID").toString());
			}
			
			
		}else if( request.getParameter("user_ID") != null){
				ID = Long.parseLong(request.getParameter("user_ID"));
				Page = "/Profile.jsp";
				
		}else {
				System.out.println("GoTO my profile");
				HttpSession session = request.getSession(true);
				ID = Long.parseLong(session.getAttribute("UserID").toString());
			    Page = "/Profile.jsp";
		}
		 

		
		//Display Welcome user on top of screen.
		HttpSession session = request.getSession(true);
		FirstName = UserDB.select_single_id(Long.parseLong(session.getAttribute("UserID").toString())).getFirstname();
		LastName = UserDB.select_single_id(Long.parseLong(session.getAttribute("UserID").toString())).getLastname();
		welcome += FirstName + " " + LastName + " !";
		
		FirstName = UserDB.select_single_id(ID).getFirstname();
		LastName = UserDB.select_single_id(ID).getLastname();
		Email =  UserDB.select_single_id(ID).getEmail();
		String Join_date =  UserDB.select_single_id(ID).getJoinDate();

		
		
		
		//Display personal details
		people_details += "<p><span class=\"glyphicon glyphicon-user\"></span><b> Name:  </b>"+ FirstName + "  " + LastName + "</p>" ;
    	people_details += "<p><span class=\"glyphicon glyphicon-envelope\"> </span><b> Email Address: </b>"   + Email + "</p>";
    	people_details += "<p><span class=\"glyphicon glyphicon-calendar\"> </span><b> Join Date: </b>"   + Join_date + "</p>";
    	
    	 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
    	 String account =  "";
    	 
    	 //User Posts
    	 if(PostDB.select_all_by_user(ID) != null){
    		 for(Post post:PostDB.select_all_by_user(ID)){
        		 post_date = post.getPostDate();
        		 single_post = post.getPostContent();
        		 FirstName = UserDB.select_single_id(ID).getFirstname();
        		 LastName = UserDB.select_single_id(ID).getLastname();
        		 
        		 post_id = post.getPostId();
        		 post_user_id = post.getPostUserId();
        		 posts+= "<nav class=\"navbar navbar-default col-sm-10\">" +
        				 " <p class=\"navbar-text navbar-default\" ><b>Post: </b>"+ single_post + "</p><br>" 
        				 + "<p class=\"navbar-text navbar-right\"> <a href=\"user_profile?user_ID=" + post_user_id + "\">" + FirstName + " " + LastName + "</a> <br>";
        		 posts+= "<b>"+ post_date + "</b><br><a  href=\"Posts?post_id=" +  post_id + "&Post_User_ID=" + post_user_id + "\">Click here to see comments </a></p></div></nav>";
        		 
        	 }
    	 }
    	 
    	 post_field += "<div class=\"container\">"
    	 		+ " <form role=\"form\" method=\"post\" action=\"put_post\"> "
    	 		+ " <div > "
    	 		+ "<textarea maxlength=\"100\" class=\"form-control\" rows=\"4\" cols=\"200\" name=\"post\" placeholder=\"What's in your mind?\">" +
    			 "</textarea><br> "
    			 + "</div>"
    			 + "<div class=\"form-group col-sm-4 col-sm-offset-8\">"
    			 + "<button type=\"submit\" value = \"submit\" class= \"button btn-primary form-control \"><span class=\"glyphicon glyphicon-pencil\"></span> Post</button>"
			     + "</div>"
    			 + "</form>"
			     + "</div>";
    	 
    	 
    	 
    	 request.setAttribute("welcome", welcome);
    	 request.setAttribute("sign_in_out", Sign_out );
	     request.setAttribute("account", account);
	     request.setAttribute("Details", people_details);
	     if(request.getParameter("user_ID") != null){
	    	 if(Long.parseLong(request.getParameter("user_ID")) != Long.parseLong(session.getAttribute("UserID").toString())){
	    		 //posts = "";
		    	 post_field = "";
	    	 }
//	    	 System.out.println("User ID from session.get Parameter() " +  user_id);
//	    	 System.out.println("User ID from request.get Parameter () " + request.getParameter("user_ID"));
	    	 
	     }
	    
	     request.setAttribute("posts", posts);
	     request.setAttribute("post_field", post_field);
	     getServletContext().getRequestDispatcher(Page).forward(request,response);
    	
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
