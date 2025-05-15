<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> <!-- Global styles -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>

<body>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    
    <main class="login-container">
    
	    <%-- will show success message if registration is successful --%>
	    <c:if test="${not empty success}">
	        <div class="alert alert-success" style="color: green;">
	            <strong>Success!</strong> ${success}
	            <button class="close-btn" onclick="this.parentElement.style.display='none';">&times;</button>
	        </div>
	    </c:if>
    
    
    	<%-- will show message if credentials fails --%>
	    <c:if test="${not empty error}">
		    <div class="alert alert-danger" style="color: red;">
		        <strong>Error!</strong> ${error}
		        <button class="close-btn" onclick="this.parentElement.style.display='none';">&times;</button>
		    </div>
		</c:if>

    
        <h1>Login</h1>
        
        <form action="login" method="post">
            <label>Email:</label><br>
            <input type="email" name="email" required><br><br>

            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>

            <input type="submit" value="Login">
        </form>
        <br>
        <p>Don't have an account? <a href="register.jsp">Sign up</a></p>
    </main>
    
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
