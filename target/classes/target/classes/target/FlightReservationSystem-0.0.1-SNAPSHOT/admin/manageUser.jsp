<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/manageUser.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/messages.css">
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    <jsp:include page="/WEB-INF/jsp/includes/messages.jsp" />

    <main class="main-wrapper">
        <h2>Registered Users</h2>

        <div class="top-actions">
            <a href="<%= request.getContextPath() %>/admin/addUser.jsp" class="add-btn">➕ Add User</a>
        </div>

        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    for (User user : users) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/admin/editUser?id=<%= user.getId() %>" class="action-link edit">✏️ Edit</a>
                        <a href="<%= request.getContextPath() %>/admin/deleteUser?id=<%= user.getId() %>"
                           onclick="return confirm('Are you sure to delete this user?');"
                           class="action-link delete">🗑️ Delete</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
