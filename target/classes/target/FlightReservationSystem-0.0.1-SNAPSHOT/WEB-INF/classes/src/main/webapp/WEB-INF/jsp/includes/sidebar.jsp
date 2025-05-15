<%@ page import="com.flightBooking.Model.User" %>
<%
  User loggedInUser = (User) session.getAttribute("currentUser");
  String role = (loggedInUser != null) ? loggedInUser.getRole() : "";
%>

<aside class="sidebar">
  <nav>
    <ul>
      <% if ("admin".equals(role)) { %>
        <li><a href="<%= request.getContextPath() %>/admin/flights">Manage Flights</a></li>
        <li><a href="<%= request.getContextPath() %>/admin/users">Manage Users</a></li>
        <li><a href="<%= request.getContextPath() %>/admin/bookings">Manage Bookings</a></li>
      <% } else if ("user".equals(role)) { %>
        <li><a href="<%= request.getContextPath() %>/search">Search Flights</a></li>
        <li><a href="<%= request.getContextPath() %>/myBookings">My Bookings</a></li>
      <% } %>
    </ul>
  </nav>
</aside>
