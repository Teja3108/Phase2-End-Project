<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>

<%
    // Get the username and quiz ID from the request parameters
    String username = request.getParameter("username");
    String quizIdParam = request.getParameter("quizId");
    int quizId = 0; // Default value if quizIdParam is not a valid integer

// Debug output

    try {
        quizId = Integer.parseInt(quizIdParam);
    } catch (NumberFormatException e) {
        // Handle the case where quizIdParam is not a valid integer
        // You can display an error message or redirect the user to an error page
        response.getWriter().println("Invalid quiz ID");
        return; // Stop further processing of the page
    }

    // Import necessary Java classes for database access
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

    // Retrieve the correct answers for the given quiz ID from the database
    String correctAnswersQuery = "SELECT question_id, correctoption FROM question WHERE quiz_id = ?";
    PreparedStatement stmt = connection.prepareStatement(correctAnswersQuery);
    stmt.setInt(1, quizId);
    ResultSet rs = stmt.executeQuery();

    int totalQuestions = 0;
    int correctAnswers = 0;

    // Iterate through the result set and compare user's answers with correct answers
    while (rs.next()) {
        int questionId = rs.getInt("question_id");
        String correctOption = rs.getString("correctoption");
        String userAnswer = request.getParameter("answerKeys" + questionId);

        if (userAnswer != null && correctOption.equals(userAnswer)) {
            correctAnswers++;
        }
        totalQuestions++;
    }

    // Calculate the score in percentage
    int score = (correctAnswers * 100) / totalQuestions;

    // Retrieve quiz details from the quiz table
    String quizDetailsQuery = "SELECT quiz_title, category FROM quiz WHERE quiz_id = ?";
    stmt = connection.prepareStatement(quizDetailsQuery);
    stmt.setInt(1, quizId);
    rs = stmt.executeQuery();

    String quizTitle = "";
    String category = "";

    if (rs.next()) {
        quizTitle = rs.getString("quiz_title");
        category = rs.getString("category");
    }
    // Insert the score details into the score table
    String insertScoreQuery = "INSERT INTO score (username, quiz_id, score) VALUES (?, ?, ?)";
    PreparedStatement insertStatement = connection.prepareStatement(insertScoreQuery);
    insertStatement.setString(1, username);
    insertStatement.setInt(2, quizId);
    insertStatement.setInt(3, score);
    insertStatement.executeUpdate();

    // Close the insert statement
    insertStatement.close();

    // Close the database connection, statement, and result set
    rs.close();
    stmt.close();
    connection.close();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Score Details</title>
</head>
<body>
    <h1>Score Details</h1>
    <p>Username: <%= username %></p>
    <p>Quiz ID: <%= quizId %></p>
    <p>Quiz Title: <%= quizTitle %></p>
    <p>Category: <%= category %></p>
    <p>Total Questions: <%= totalQuestions %></p>
    <p>Correct Answers: <%= correctAnswers %></p>
    <p>Score: <%= score %>%</p>
    <a href="Allscore.jsp">View All Scores</a>
</body>
</html>