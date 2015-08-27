

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
			
			Comment comment = new Comment();
			String single_comment,output, comment_date;
			output="";
			Date now  = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
			
			single_comment = request.getParameter("comment");
			comment_date = sdf.format(now);
			
			if(CommentDB.select_all() != null){
				for (Comment comm: CommentDB.select_all())
				{
					
					
					output+= "<nav class=\"navbar navbar-default \"><div class=\"navbar-text navbar-default\">  <p ><b>Comment: </b>"+ comm.getContentText() + "</p> </div> ";
				    output+= "<div class=\"navbar-text navbar-right\"> <p class=\"navbar-text navbar-right\">"+ comm.getCommentDate() +
				         "</p> </div> </nav>";
				  
				          
				    
				  }
			}
			
			
			request.setAttribute("comments", output);

	        getServletContext().getRequestDispatcher("/Bullhorn.jsp").forward(request,response);
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
