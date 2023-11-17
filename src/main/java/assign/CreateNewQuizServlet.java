package assign;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import quiz.Quiz;

/**
 * Servlet implementation class CreateNewQuizServlet
 */
@WebServlet("/createnewquiz")
public class CreateNewQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    out.println("<html><body>");
	    out.println("<form method='post' action='createnewquiz'>");
	    out.println("Quiz ID: <input type='text' name='quizId'><br>");
	    out.println("Quiz Title: <input type='text' name='quizTitle'><br>");
	    out.println("Category: <input type='text' name='category'><br>");
	    out.println("<input type='submit' value='Add Quiz'>");
	    out.println("</form>");
	    out.println("</body></html>");
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data from request parameters
        String quizId = request.getParameter("quizId");
        String quizTitle = request.getParameter("quizTitle");
        String category = request.getParameter("category");

        // Create a new Quiz object
        Quiz quiz = new Quiz();
        quiz.setquiz_id(Integer.parseInt(quizId));
        quiz.setquiz_title(quizTitle);
        quiz.setcategory(category);

        // Save the Quiz object to the database
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(quiz);
        session.getTransaction().commit();
        session.close();

        // Redirect to the dashboard or any other page
        
        response.sendRedirect("Success.html");

	}

}
