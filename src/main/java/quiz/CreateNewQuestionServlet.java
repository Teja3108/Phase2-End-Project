package quiz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import assign.HibernateUtil;

/**
 * Servlet implementation class CreateNewQuestionServlet
 */
@WebServlet("/addquestion")
public class CreateNewQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 PrintWriter out = response.getWriter();
	        out.println("<html><body>");
	        out.println("<h1>Add Question</h1>");
	        out.println("<form action=\"addquestion\" method=\"POST\">");
	        out.println("Quiz ID: <input type=\"text\" name=\"quiz_id\"><br>");
	        out.println("Question ID: <input type=\"text\" name=\"question_id\"><br>");
	        out.println("Question Description: <input type=\"text\" name=\"questiondesc\"><br>");
	        out.println("Option 1: <input type=\"text\" name=\"option1\"><br>");
	        out.println("Option 2: <input type=\"text\" name=\"option2\"><br>");
	        out.println("Option 3: <input type=\"text\" name=\"option3\"><br>");
	        out.println("Option 4: <input type=\"text\" name=\"option4\"><br>");
	        out.println("Correct Option: <input type=\"text\" name=\"correctoption\"><br>");
	        out.println("<input type=\"submit\" value=\"Add Question\">");
	        out.println("</form>");
	        out.println("</body></html>");
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Retrieve form data
	        String quizId = request.getParameter("quiz_id");
	        String questionId = request.getParameter("question_id");
	        String questionDesc = request.getParameter("questiondesc");
	        String option1 = request.getParameter("option1");
	        String option2 = request.getParameter("option2");
	        String option3 = request.getParameter("option3");
	        String option4 = request.getParameter("option4");
	        String correctOption = request.getParameter("correctoption");

	        // Create a new Question object
	        Question question = new Question();
	        question.setQuizId(Integer.parseInt(quizId));
	        question.setQuestionId(Integer.parseInt(questionId));
	        question.setQuestiondesc(questionDesc);
	        question.setOption1(option1);
	        question.setOption2(option2);
	        question.setOption3(option3);
	        question.setOption4(option4);
	        question.setCorrectOption(correctOption);

	        // Save the question to the database
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        session.save(question);
	        session.getTransaction().commit();
	        session.close();

	        // Redirect to the success page
	        response.sendRedirect("Success1.html");
	    }
	}