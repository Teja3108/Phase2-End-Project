<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h1>User Login</h1>
    <%
        // Check if the user has submitted the form
        if (request.getMethod().equalsIgnoreCase("post")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            // Perform authentication logic
            // ...
            
            // Assuming authentication is successful, store the username in the session
            session.setAttribute("username", username);
            
            // Redirect to the dashboard page
            response.sendRedirect("dashboard.jsp");
        }
    %>
    <form action="Dashboard.jsp" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>
        <br>
        <input type="submit" value="Login">
    </form>
</body>
</html>