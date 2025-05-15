<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8d7da;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
            text-align: center;
        }
    </style>
</head>
<body>

<div class="error-container">
    <h2 class="text-danger">⚠️ Oops! An Error Occurred</h2>
    <p class="mt-3">
        <strong>
            <%= request.getAttribute("error") != null ? request.getAttribute("error") : "An unexpected error occurred." %>
        </strong>
    </p>
    <a href="dashboard.jsp" class="btn btn-danger mt-4">Back to Dashboard</a>
</div>

</body>
</html>
