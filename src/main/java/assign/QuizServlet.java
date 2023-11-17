 package assign;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import quiz.Quiz;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/quizlist")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  

		
		PrintWriter out = response.getWriter();

		out.println("<html><body>");

 

		// STEP 1: Get a Session (connection) from the Session Factory class

		SessionFactory factory = HibernateUtil.getSessionFactory();

 

		Session session = factory.openSession();		

 

		// STEP 2 execute the HQL commands

		// for now we will only test if the connection is establised with MySQL server.

		List<Quiz> quiz1 = session.createQuery("from Quiz").list();

	    out.println("<table>");
	        out.println("<tr><th>Quiz ID</th><th>Quiz Title</th><th>Category</th></tr>");

		for(Quiz Q: quiz1) {

			out.println("<tr>");
            out.println("<td>" + Q.getquiz_id() + "</td>");
            out.println("<td>" + Q.getquiz_title() + "</td>");
            out.println("<td>" + Q.getcategory() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href=\"logout\">Logout</a>");
        out.println("<a href =\"dashboard\">dashboard</a>");

        session.close();

		out.println("</body></html>");

	}

}
