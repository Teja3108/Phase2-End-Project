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

import quiz.Question;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/questionlist")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		response.setContentType("text/html");  

		PrintWriter out = response.getWriter();
	        out.println("<html><body>");

	        // Get a Session (connection) from the Session Factory
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();

	        // Execute the HQL query to retrieve data from the question table
	        

	        List<Question> questions = session.createQuery("from Question").list();

	        // Display the retrieved data as a question list
	      
	        out.println("<h1>Question List</h1>");
	        out.println("<table>");
	        out.println("<tr><th>Quiz ID</th><th>Question ID</th><th>Question Desc</th><th>Option 1</th><th>Option 2</th><th>Option 3</th><th>Option 4</th><th>Correct Option</th></tr>");
	        for (Question question : questions) {
	            out.println("<tr>");
	            out.println("<td>" + question.getQuizId() + "</td>");
	            out.println("<td>" + question.getQuestionId() + "</td>");
	            out.println("<td>" + question.getQuestiondesc() + "</td>");
	            out.println("<td>" + question.getOption1() + "</td>");
	            out.println("<td>" + question.getOption2() + "</td>");
	            out.println("<td>" + question.getOption3() + "</td>");
	            out.println("<td>" + question.getOption4() + "</td>");
	            out.println("<td>" + question.getCorrectOption() + "</td>");
	            out.println("</tr>");
	        }
	        out.println("</table>");
	        out.println("<a href=\"Logout.jsp\">Logout</a>");
	        out.println("<a href =\"dashboard\">dashboard</a>");

	        // Close the session
	        session.close();

	        out.println("</body></html>");
	    }
	}
