<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flightBooking.Model.Booking" %>
<%@ page import="com.flightBooking.Model.User" %>
<%@ page import="com.flightBooking.Model.Flight" %>
<%@ page import="java.text.SimpleDateFormat" %>

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
    <title>Manage Bookings</title>
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
        <h2>Manage Bookings</h2>

        <div class="top-actions">
            <a href="<%= request.getContextPath() %>/admin/addBookingForm" class="add-btn">➕ Add Booking</a>
        </div>

        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Booking Date</th>
                    <th>Seats</th>
                    <th>Status</th>
                    <th>User</th>
                    <th>Flight</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
                    if (bookings != null && !bookings.isEmpty()) {
                        for (Booking booking : bookings) {
                %>
                <tr>
                    <td><%= booking.getId() %></td>
                    <td><%= sdf.format(booking.getBookingDate()) %></td>
                    <td><%= booking.getNumSeats() %></td>
                    <td><%= booking.getStatus() %></td>
                    <td>
                        <%
                            User u = booking.getUser();
                            if (u != null) {
                        %>
                            <%= u.getName() %> (<%= u.getEmail() %>)
                        <%
                            } else {
                        %>
                            [User Missing]
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <%
                            Flight f = booking.getFlight();
                            if (f != null) {
                        %>
                            <%= f.getSource() %> → <%= f.getDestination() %>
                        <%
                            } else {
                        %>
                            [Flight Missing]
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <a href="<%= request.getContextPath() %>/admin/editBooking?id=<%= booking.getId() %>" class="action-link edit">✏️ Edit</a>
                        <a href="<%= request.getContextPath() %>/admin/deleteBooking?id=<%= booking.getId() %>"
                           onclick="return confirm('Are you sure to delete this booking?');"
                           class="action-link delete">🗑️ Delete</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" style="text-align: center;">No bookings available.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
