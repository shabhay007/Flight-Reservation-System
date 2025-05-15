<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.flightBooking.Model.User" %>
<%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
    	response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
	
    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/editUser.css">
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-wrapper">
        <h2>Edit User</h2>

        <form action="<%= request.getContextPath() %>/admin/editUser" method="post" class="form-container">
            <input type="hidden" name="id" value="<%= user.getId() %>">

            <div class="form-group">
                <label>Name:</label>
                <input type="text" name="name" value="<%= user.getName() %>" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="<%= user.getEmail() %>" required>
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" value="<%= user.getPassword() %>" required>
            </div>

            <div class="form-group">
                <label>Role:</label>
                <select name="role" required>
                    <option value="user" <%= user.getRole().equals("user") ? "selected" : "" %>>User</option>
                    <option value="admin" <%= user.getRole().equals("admin") ? "selected" : "" %>>Admin</option>
                </select>
            </div>

            <div class="form-actions">
                <input type="submit" value="💾 Update User" class="submit-btn">
                <a href="<%= request.getContextPath() %>/admin/users" class="back-link">← Back to User List</a>
            </div>
        </form>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
