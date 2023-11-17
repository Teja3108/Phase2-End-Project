package assign;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 HttpSession session = request.getSession(false);
	        if (session != null && session.getAttribute("username") != null) {
	            // User is logged in, show the dashboard
	            response.setContentType("text/html");
	            response.getWriter().println("<h1>Welcome to the Dashboard</h1>");
	            
	            response.getWriter().println("<p>QUIZ MANAGER</p>");
	            response.getWriter().println("<li><a href=\"createnewquiz\">Create new quiz</a></li>");
	            response.getWriter().println("<li><a href=\"quizlist\">Quiz List</a></li>");

	            response.getWriter().println("<p>QUESTION MANAGER</p>");
	            response.getWriter().println("<li><a href=\"addquestion\"> Add new question </a></li>");
	            response.getWriter().println("<li><a href=\"questionlist\"> Question List </a></li>");

	          } else {
	            // User is not logged in, redirect to the login page
	            response.sendRedirect("login.html");
	        }
	    }
	

}
