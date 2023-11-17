<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="java.util.HashMap" %>
<%@ page import= "java.sql.PreparedStatement" %>

<%
    String username = request.getParameter("username");
    String quizIdParam = request.getParameter("quizId");
    int quizId = 0;



    try {
        quizId = Integer.parseInt(quizIdParam);
    } catch (NumberFormatException e) {
        response.getWriter().println("Invalid quiz ID");
        return;
    }

    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        String url = "jdbc:mysql://localhost/quizapp";
        String dbUsername = "root";
        String dbPassword = "tejagoud3108";

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, dbUsername, dbPassword);

        String query = "SELECT * FROM question WHERE quiz_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, quizId);
        rs = pstmt.executeQuery();

        HashMap<Integer, String> answerKeys = new HashMap<>();

%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Questions</title>
</head>
<body>
    <h1>Quiz Questions</h1>
    <form action="Score.jsp" method="POST">
<%
        while (rs.next()) {
            int questionId = rs.getInt("question_id");
            String questiondesc = rs.getString("questiondesc");
            String option1 = rs.getString("option1");
            String option2 = rs.getString("option2");
            String option3 = rs.getString("option3");
            String option4 = rs.getString("option4");

            answerKeys.put(questionId, "");

%>
            <p>Question <%= questionId %>: <%= questiondesc %></p> 
            <p>
                <input type="hidden" name="questionId" value="<%= questionId %>">
                <input type="hidden" name="username" value="<%= username %>">
                <input type="hidden" name="quizId" value="<%= quizId %>">
                <input type="radio" name="answerKeys<%= questionId %>" value="option1"> <%= option1 %><br>
                <input type="radio" name="answerKeys<%= questionId %>" value="option2"> <%= option2 %><br>
                <input type="radio" name="answerKeys<%= questionId %>" value="option3"> <%= option3 %><br>
                <input type="radio" name="answerKeys<%= questionId %>" value="option4"> <%= option4 %><br>
            </p>
<%
        }
%>
        <input type="submit" value="End Test">
    </form>
</body>
</html>
<%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
