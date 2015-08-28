

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comment;
import model.CommentDB;
import model.User;
import model.UserDB;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDB process_user = new UserDB ();
		String Email, Password;
		String invalid = "", welcome = "Welcome ", create_an_account = "";
		String Page;
		Email = request.getParameter("email");
		Password = request.getParameter("password");
		
		User single_user = new User();
		
		single_user = process_user.select_single(Email, Password);
		System.out.println(single_user);
		
		if(single_user == null){
			
			System.out.println("Invalid");
			Page = "/SignIn.jsp";
			invalid = "Invalid Email or/and password";
			create_an_account = "<a href=\"/Bullhorm/SignUp.jsp\">Create an account</a>";
			request.setAttribute("invalid_user", invalid);
			request.setAttribute("Sign_up", create_an_account);
		}
		else{
        	HttpSession session = request.getSession();
        	session.setAttribute("UserID", single_user.getId());
        	
        	
			System.out.println("Welcome !");
			Page = "/Bullhorn.jsp";
			welcome += single_user.getFirstname() + " " + single_user.getLastname();
			
			request.setAttribute("welcome", welcome);
			request.setAttribute("sign_in_out", "Sign out");
			request.setAttribute("account", "");
			Comment comment = new Comment();
			
			CommentDB  comment_db =  new CommentDB();
			String single_comment,output, comment_date;
			output="";
			Date now  = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
			
			single_comment = request.getParameter("comment");
			comment_date = sdf.format(now);

			
			if(session.getAttribute("UserID") != null){
				String Sign_out =  "<div class=\"col-sm-2 col-sm-offset-10\">" +
	            " <form action= \"SignIn.jsp\" class=\"form-signin\">"
						+ "<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign out</button>" 
						+ "</form> </div>";
				String account = "";
				request.setAttribute("sign_in_out", Sign_out);
				request.setAttribute("account",account);
				System.out.println(session.getAttribute("UserID"));
				long ID = Long.parseLong("" + session.getAttribute("UserID") + "");
				if(single_comment != null){
					
					comment.setCommentDate(comment_date);
					comment.setContentText(single_comment);
					comment.setUserId(ID);
					CommentDB.insert(comment);
				}
				
				
				
				String FirstName = "", LastName = "";
				

				for (Comment comm: CommentDB.select_all())
					{

						FirstName = UserDB.select_single_id(comm.getUserId()).getFirstname();
					     LastName = UserDB.select_single_id(comm.getUserId()).getLastname();


							
							output+= "<nav class=\"navbar navbar-default \"><div class=\"navbar-text navbar-default\">  <p ><b>Comment: </b>"+ comm.getContentText() + "</p> </div> ";
						    output+= "<div class=\"navbar-text navbar-right\"> <p class=\"navbar-text navbar-right\">"+ comm.getCommentDate() +
						         "</p>" + "<p class=\"navbar-text navbar-right\">" + FirstName + " " + LastName + "</p> </div> </nav>";
					  
						}
					    
				     }else{
				    	 String Sign_out =  "<div class=\"col-sm-2 col-sm-offset-10\">" +
				    	            " <form action= \"SignIn.jsp\" class=\"form-signin\">"
				    						+ "<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>" 
				    						+ "</form> </div>";
				    	 String account =  "<div class=\"col-sm-2 col-sm-offset-10\">" +
				    	            " <form action= \"SignUpjsp\" style=\"background-color:#CC66FF\"class=\"form-signin\">"
				    						+ "<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>" 
				    						+ "</form> </div>";
				    	 request.setAttribute("sign_in_out", Sign_out );
					     request.setAttribute("account", "Create an account");
				     }

			request.setAttribute("comments", output);
		}

		

        getServletContext().getRequestDispatcher(Page).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
