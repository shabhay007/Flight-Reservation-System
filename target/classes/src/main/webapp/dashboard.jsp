<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String userName = (String) sessionObj.getAttribute("userName");
    String userRole = (String) sessionObj.getAttribute("userRole");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= userRole.equals("admin") ? "Admin" : "User" %> Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dashboard.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <!-- Wrapping main content inside <main> for sticky footer support -->
    <main class="dashboard-container">
    
    	<%
		    String success = request.getParameter("success");
		    if ("1".equals(success)) {
		%>
		    <div class="alert alert-success alert-dismissible fade show" role="alert">
		      ✅ Payment successful! Your booking is confirmed.
		      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		    </div>
		<%
		    }
		%>
    			
	    <h2>Welcome, <%= userName %>!</h2>
	    <p>Your role: <%= userRole %></p>
	
	    <div class="card-grid">
	        <% if ("admin".equalsIgnoreCase(userRole)) { %>
	            <a href="admin/flights" class="dashboard-card gradient-blue">Manage Flights</a>
	            <a href="admin/users" class="dashboard-card gradient-green">Manage Users</a>
	            <a href="admin/bookings" class="dashboard-card gradient-purple">Manage Bookings</a>
	        <% } else if ("user".equalsIgnoreCase(userRole)) { %>
	            <a href="search.jsp" class="dashboard-card gradient-orange">Search Flights</a>
	            <a href="viewBooking" class="dashboard-card gradient-pink">My Bookings</a>
	        <% } %>
	    </div>
	</main>


    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>
