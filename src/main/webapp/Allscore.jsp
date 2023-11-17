<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>

<%
    // Database connection details
      String url = "jdbc:mysql://localhost/quizapp";
    String dbUsername = "root";
    String dbPassword = "tejagoud3108";

    // Establish the database connection
    Connection connection = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, dbUsername, dbPassword);
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Query to retrieve data from the score table
    String query = "SELECT * FROM score";

    // Create a statement object
    Statement stmt = connection.createStatement();

    // Execute the query and get the result set
    ResultSet rs = stmt.executeQuery(query);
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Scores</title>
</head>
<body>
    <h1>All Scores</h1>

    <table border="1">
        <tr>
            <th>Username</th>
            <th>Quiz ID</th>
            <th>Score</th>
        </tr>
        <% while (rs.next()) { %>
            <tr>
                <td><%= rs.getString("username") %></td>
                <td><%= rs.getInt("quiz_id") %></td>
                <td><%= rs.getInt("score") %></td>
            </tr>
        <% } %>
    </table>
     <a href="Dashboard.jsp">Go to Dashboard</a>

    <% 

        // Close the database connection, statement, and result set
        rs.close();
        stmt.close();
        connection.close();
    %>
</body>
</html>