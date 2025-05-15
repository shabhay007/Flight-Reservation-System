<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flightBooking.Model.User" %>
<%@ page import="com.flightBooking.Model.Flight" %>
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
%>
	
	
<!DOCTYPE html>
<html>
<head>
    <title>Add Booking</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/addBooking.css">
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-wrapper">
        <h2>Add New Booking</h2>

        <form action="<%= request.getContextPath() %>/admin/addBooking" method="post">
            <div class="form-group">
                <label for="bookingDate">Booking Date:</label>
                <input type="date" id="bookingDate" name="bookingDate" required>
            </div>

            <div class="form-group">
                <label for="numSeats">Number of Seats:</label>
                <input type="number" id="numSeats" name="numSeats" min="1" required>
            </div>

            <div class="form-group">
                <label for="status">Booking Status:</label>
                <select id="status" name="status" required>
                	<option value="" disabled selected>-- Select Status --</option>
                    <%
                        for (BookingStatus status : BookingStatus.values()) {
                    %>
                        <option value="<%= status %>"><%= status %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="userId">Select User:</label>
                <select id="userId" name="userId" required>
                	<option value="" disabled selected>-- Select User --</option>
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        for (User user : users) {
                    %>
                        <option value="<%= user.getId() %>">
                            <%= user.getName() %> (<%= user.getEmail() %>)
                        </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="flightId">Select Flight:</label>
                <select id="flightId" name="flightId" required>
                	<option value="" disabled selected>-- Select Flight --</option>
                    <%
                        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
                        for (Flight flight : flights) {
                    %>
                        <option value="<%= flight.getId() %>">
                            <%= flight.getSource() %> → <%= flight.getDestination() %> (₹<%= flight.getPrice() %>)
                        </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-actions">
                <input type="submit" value="Add Booking" class="add-btn">
            </div>
        </form>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
