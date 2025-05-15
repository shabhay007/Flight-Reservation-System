<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flightBooking.Model.Booking" %>
<%@ page import="com.flightBooking.Model.Flight" %>
<%@ page import="com.flightBooking.Model.Booking.BookingStatus" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/myBookings.css">
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="my-bookings-container">
        <h2>📋 My Bookings</h2>

        <%
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "0");
	        
	        HttpSession sessionObj = request.getSession(false);

	        if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }
	        
            List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
            if (bookings == null || bookings.isEmpty()) {
        %>
            <p>You have no bookings yet.</p>
        <%
            } else {
        %>
            <table>
                <tr>
                    <th>Booking ID</th>
                    <th>Flight</th>
                    <th>Flight Date</th>
                    <th>Departure</th>
                    <th>Expected Landing</th>
                    <th>Seats</th>
                    <th>Booking Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    for (Booking booking : bookings) {
                        Flight flight = booking.getFlight();
                %>
                <tr>
                    <td><%= booking.getId() %></td>
                    <td><%= flight.getFlightNo() %> (<%= flight.getSource() %> ➜ <%= flight.getDestination() %>)</td>
                    <td><%= sdf.format(flight.getFlightDate()) %></td>
                    <td><%= flight.getDepartureTime() %></td>
                    <td><%= flight.getArrivalTime() %></td>
                    <td><%= booking.getNumSeats() %></td>
                    <td><%= sdf.format(booking.getBookingDate()) %>
                    <td><%= booking.getStatus() %></td>
                    <td>
                        <%
                            if (booking.getStatus() == BookingStatus.BOOKED) {
                        %>
                            <form action="cancelBooking" method="post" onsubmit="return confirm('Are you sure you want to cancel this booking?');">
                                <input type="hidden" name="bookingId" value="<%= booking.getId() %>" />
                                <input type="submit" class="btn-cancel" value="Cancel Booking" />
                            </form>
                        <%
                            } else {
                        %>
                            <span class="cancelled">Cancelled</span>
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        <%
            }
        %>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
