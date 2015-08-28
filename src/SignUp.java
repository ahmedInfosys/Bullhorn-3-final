

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet 
{
	static Connection conn;
	static String name, grade, output, ID;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() 
    {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String  FirstName, LastName, Email, zipCode, welcome = "Welcome ";

		FirstName = request.getParameter("fName");
		
		LastName = request.getParameter( "lName" );
	
		Email=request.getParameter("email");
		zipCode= request.getParameter("zip");
		
		User user = new User();
		
		user.setFirstname(FirstName);
		user.setLastname(LastName);
		user.setEmail(Email);
		user.setZipcode(zipCode);
		
		UserDB.insert(user);
		HttpSession session = request.getSession();
    	
		long ID = user.getId();
		session.setAttribute("UserID", ID);
        welcome += UserDB.select_single_id(ID).getFirstname() + " " + UserDB.select_single_id(ID).getLastname() + "!";
		
		request.setAttribute("welcome", welcome);
		output="";
		
		
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
				long userID = Long.parseLong("" + session.getAttribute("UserID") + "");
				if(single_comment != null){
					
					comment.setCommentDate(comment_date);
					comment.setContentText(single_comment);
					comment.setUserId(userID);
					CommentDB.insert(comment);
				}
		
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

	        getServletContext().getRequestDispatcher("/Bullhorn.jsp").forward(request,response);
			      
		}
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
