<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> <!-- Global styles -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css"> <!-- Register page specific styles -->
</head>

<body>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    
    <main class="register-container">
    	<c:if test="${not empty error}">
		    <div class="alert alert-danger" style="color: red;">
		        <strong>Error!</strong> ${error}
		        <button class="close-btn" onclick="this.parentElement.style.display='none';">&times;</button>
		    </div>
		</c:if>
		
        <h1>Register</h1>
        
        <form action="register" method="post">
            <label>Name:</label><br>
            <input type="text" name="name" required><br><br>

            <label>Email:</label><br>
            <input type="email" name="email" required><br><br>

            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>

            <label>Role:</label><br>
            <select name="role" required>
            	<option value="" disabled selected>-- Select Role --</option>
                <option value="user">User</option>
                <option value="admin">Admin</option>
            </select><br><br>

            <input type="submit" value="Register">
        </form>
        
    </main>
    
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
