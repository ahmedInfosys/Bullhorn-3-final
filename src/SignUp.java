

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String ID, FirstName, LastName, Email, zipCode;

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
		
		
		 
		request.setAttribute("Welcome ", "Welcome " + FirstName + " " + LastName + "!");
		getServletContext().getRequestDispatcher("/Bullhorn.jsp").forward(request,response);
		output="";
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
