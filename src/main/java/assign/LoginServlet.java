package assign;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        // Hard-coded correct values
	        String validUsername = "admin";
	        String validPassword = "admin123";

	        if (username.equals(validUsername) && password.equals(validPassword)) {
	            // Successful login
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            response.sendRedirect("dashboard");
	        } else {
	            // Incorrect login
	            response.sendRedirect("error.html");
	        }
	}

}
