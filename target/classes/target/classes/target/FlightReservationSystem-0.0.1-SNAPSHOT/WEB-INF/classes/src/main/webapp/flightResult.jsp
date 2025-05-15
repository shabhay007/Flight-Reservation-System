<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flightBooking.Model.Flight" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flight Results</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/flightResult.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/messages.css">
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/messages.jsp" />
	
	<main class="result-container">
	    <h2>Available Flights</h2>
	
	    <%
		    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		    response.setHeader("Pragma", "no-cache");
		    response.setHeader("Expires", "0");
		    
		    HttpSession sessionObj = request.getSession(false);

		    if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
		        response.sendRedirect("login.jsp");
		        return;
		    }
		    
	        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
	        if (flights == null || flights.isEmpty()) {
	    %>
	        <p class="no-flights">❌ No flights found for the given route.</p>
	    <%
	        } else {
	    %>
	        <table class="flight-table">
	            <thead>
	                <tr>
	                    <th>Flight No</th>
	                    <th>Source</th>
	                    <th>Destination</th>
	                    <th>Departure</th>
	                    <th>Reach Time</th>
	                    <th>Price</th>
	                    <th>Available Seats</th>
	                    <th>Date</th>
	                    <th>Action</th>
	                </tr>
	            </thead>
	            <tbody>
	            <%
	                for (Flight f : flights) {
	            %>
	            <tr>
	                <td><%= f.getFlightNo() %></td>
	                <td><%= f.getSource() %></td>
	                <td><%= f.getDestination() %></td>
	                <td><%= f.getDepartureTime() %></td>
	                <td><%= f.getArrivalTime() %></td>
	                <td>₹<%= f.getPrice() %></td>
	                <td><%= f.getSeats() %></td>
	                <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(f.getFlightDate()) %></td>
	                <td>
	                    <%
	                        if (f.getSeats() > 0) {
	                    %>
	                        <form action="bookFlight" method="post" class="book-form">
	                            <input type="hidden" name="flightId" value="<%= f.getId() %>">
	                            <input type="number" name="numOfSeats" min="1" max="<%= f.getSeats() %>" required placeholder="Seats">
	                            <input type="submit" value="Book">
	                        </form>
	                    <%
	                        } else {
	                    %>
	                        <span class="full">Fully Booked</span>
	                    <%
	                        }
	                    %>
	                </td>
	            </tr>
	            <% } %>
	            </tbody>
	        </table>
	    <%
	        }
	    %>
	</main>
	
	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
