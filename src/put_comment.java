

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comment;
import model.CommentDB;
import model.UserDB;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/put_comment")
public class put_comment extends HttpServlet {
	static Connection conn;
	static String name, grade, output;
	private static final long serialVersionUID = 1L;
     
	private String page;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public put_comment() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Comment comment = new Comment();
		
		CommentDB  comment_db =  new CommentDB();
		String single_comment,output, comment_date;
		output="";
		Date now  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
		
		single_comment = request.getParameter("comment");
		comment_date = sdf.format(now);
		long ID;
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("UserID") != null){
	
			
			ID = (long) session.getAttribute("UserID");
			if(single_comment != null){
				comment.setCommentDate(comment_date);
				comment.setContentText(single_comment);
				comment.setUserId(ID);
				CommentDB.insert(comment);
			}
			
			String FirstName = "", LastName = "";
			

			for (Comment comm: CommentDB.select_all())
				{
					
						
//					FirstName = UserDB.select_single_id(comm.getUserId()).getFirstname();
//					LastName = UserDB.select_single_id(comm.getUserId()).getLastname();
						
						output+= "<nav class=\"navbar navbar-default \"><div class=\"navbar-text navbar-default\">  <p ><b>Comment: </b>"+ comm.getContentText() + "</p> </div> ";
					    output+= "<div class=\"navbar-text navbar-right\"> <p class=\"navbar-text navbar-right\">"+ comm.getCommentDate() +
					         "</p>" + "<p class=\"navbar-text navbar-right\">" + FirstName + " " + LastName + "</p> </div> </nav>";
				  
					}
				    
			     }

			
		
		
		

		request.setAttribute("comments", output);

        getServletContext().getRequestDispatcher("/Bullhorn.jsp").forward(request,response);
		      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request,response);
	}

}
