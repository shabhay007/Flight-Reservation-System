<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
    	response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add New User</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/addUser.css">
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-wrapper">
        <h2>Add New User</h2>

        <form action="<%= request.getContextPath() %>/admin/addUser" method="post" class="form-container">
            <div class="form-group">
                <label>Name:</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" required>
            </div>

            <div class="form-group">
                <label>Role:</label>
                <select name="role" required>
                	<option value="" disabled selected>-- Select Role --</option>
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                </select>
            </div>

            <div class="form-actions">
                <input type="submit" value="➕ Add User" class="submit-btn">
                <a href="<%= request.getContextPath() %>/admin/users" class="back-link">← Back to User List</a>
            </div>
        </form>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
