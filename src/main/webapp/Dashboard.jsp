<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.Statement" %>
    
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome to the Dashboard, <%= request.getParameter("username") %></h1>
    
    <table>
        <tr>
            <th>Quiz ID</th>
            <th>Quiz Title</th>
            <th>Category</th>
            <th>Start Test</th>
        </tr>
        <% 
            // Import necessary Java classes for database access
    // Database connection details
    String url = "jdbc:mysql://localhost/quizapp";
    String username = "root";
    String password = "tejagoud3108";

    // Establish the database connection
    Connection connection = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
        e.printStackTrace();
    }

            // Define a SQL query to retrieve quiz data from the table
            String query = "SELECT * FROM quiz";
            
            // Create a statement object
            Statement stmt = connection.createStatement();
            
            // Execute the query and get the result set
            ResultSet rs = stmt.executeQuery(query);
            
            // Iterate through the result set and display quiz data
            while (rs.next()) {
                int quizId = rs.getInt("quiz_id");
                String quizTitle = rs.getString("quiz_title");
                String category = rs.getString("category");
        %>
        <tr>
            <td><%= quizId %></td>
            <td><%= quizTitle %></td>
            <td><%= category %></td>
            <td>
                <form action="Test.jsp" method="GET">
                    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
                    <input type="hidden" name="quizId" value="<%= quizId %>">
                    <input type="hidden" name="quizId" value="<%= quizId %>">
                    <input type="submit" value="Start Test">
                </form>
            </td>
        </tr>
        <% 
            }
            
            // Close the database connection, statement, and result set
            rs.close();
            stmt.close();
        %>
    </table>
     <a href="Logout.jsp">Log out</a>
</body>
</html>