<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    String userName = (String) (sessionObj != null ? sessionObj.getAttribute("userName") : null);
    String userRole = (String) (sessionObj != null ? sessionObj.getAttribute("userRole") : null);

    String currentPath = request.getRequestURI();
    boolean isDashboard = currentPath.endsWith("dashboard.jsp");
%>

<header class="site-header">
  <div class="container">
    <h4>✈️ Flight Reservation System</h4>
  </div>
    
  <div>
    <nav class="nav">
      <% if (userName != null && userRole != null) { %>
        
        <% if (!isDashboard) { %>
          <% if ("admin".equals(userRole)) { %>
          	<a href="<%= request.getContextPath() %>/dashboard.jsp">Dashboard</a>
            <a href="<%= request.getContextPath() %>/admin/flights">Manage Flights</a>
            <a href="<%= request.getContextPath() %>/admin/users">Manage Users</a>
            <a href="<%= request.getContextPath() %>/admin/bookings">Manage Bookings</a>
          <% } else if ("user".equals(userRole)) { %>
          	<a href="<%= request.getContextPath() %>/dashboard.jsp">Dashboard</a>
            <a href="<%= request.getContextPath() %>/search.jsp">Search Flights</a>
            <a href="<%= request.getContextPath() %>/viewBooking">My Bookings</a>
          <% } %>
        <% } %>

        <a href="<%= request.getContextPath() %>/logout">Logout</a>
      <% } else { %>
        <a href="<%= request.getContextPath() %>/register.jsp">Register</a>
        <a href="<%= request.getContextPath() %>/login.jsp">Login</a>
      <% } %>
    </nav>
  </div>
</header>