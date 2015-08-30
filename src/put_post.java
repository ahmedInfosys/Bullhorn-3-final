

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class put_post
 */
@WebServlet("/put_post")
public class put_post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public put_post() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Post post = new Post();

		String single_post,output, post_date;
		String FirstName = "", LastName = "", welcome = " ";
		output="";
		Date now  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
		
		single_post = request.getParameter("post");
		post_date = sdf.format(now);
	
		
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("UserID") != null){

			long ID = Long.parseLong(session.getAttribute("UserID").toString());

			System.out.println("Welcome !");

			if(single_post != null){
				
				post.setPostDate(post_date);
				post.setPostContent(single_post);
				post.setPostUserId(ID);
				PostDB.insert(post);
			}
			
		}

        getServletContext().getRequestDispatcher("/user_profile").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
