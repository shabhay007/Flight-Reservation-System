<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flightBooking.Model.User" %>
<%@ page import="com.flightBooking.Model.Flight" %>
<%@ page import="com.flightBooking.Model.Booking" %>
<%@ page import="com.flightBooking.Model.Booking.BookingStatus" %>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
    	response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
	
    Booking booking = (Booking) request.getAttribute("booking");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Booking</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/editBooking.css">
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-wrapper">
        <h2>Edit Booking</h2>

        <form action="<%= request.getContextPath() %>/admin/editBooking" method="post" class="form-container">
            <input type="hidden" name="id" value="<%= booking.getId() %>">

            <div class="form-group">
                <label>Booking Date:</label>
                <input type="date" name="bookingDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(booking.getBookingDate()) %>" required>
            </div>

            <div class="form-group">
                <label>Number of Seats:</label>
                <input type="number" name="numSeats" value="<%= booking.getNumSeats() %>" min="1" required>
            </div>

            <div class="form-group">
                <label>Booking Status:</label>
                <select name="status">
                    <%
                        for (BookingStatus status : BookingStatus.values()) {
                            String selected = status == booking.getStatus() ? "selected" : "";
                    %>
                        <option value="<%= status %>" <%= selected %>><%= status %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label>Select User:</label>
                <select name="userId">
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        for (User user : users) {
                            String selected = user.getId() == booking.getUser().getId() ? "selected" : "";
                    %>
                        <option value="<%= user.getId() %>" <%= selected %>><%= user.getName() %> (<%= user.getEmail() %>)</option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label>Select Flight:</label>
                <select name="flightId">
                    <%
                        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
                        for (Flight flight : flights) {
                            String selected = flight.getId() == booking.getFlight().getId() ? "selected" : "";
                    %>
                        <option value="<%= flight.getId() %>" <%= selected %>>
                            <%= flight.getSource() %> → <%= flight.getDestination() %> (₹<%= flight.getPrice() %>)
                        </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-actions">
                <input type="submit" value="💾 Update Booking" class="submit-btn">
                <a href="<%= request.getContextPath() %>/admin/bookings" class="back-link">← Back to Booking List</a>
            </div>
        </form>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
