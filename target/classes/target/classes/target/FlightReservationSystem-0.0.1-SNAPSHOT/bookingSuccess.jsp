<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.flightBooking.Model.Booking" %>
<%@ page import="com.flightBooking.Model.Flight" %>
<%@ page import="com.flightBooking.Model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Booking Confirmation</title>
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bookingSuccess.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/messages.css">
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    <jsp:include page="/WEB-INF/jsp/includes/messages.jsp" />

    <main class="booking-success-container">
        <h2>Booking Details</h2>

        <%
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "0");
	        
	        HttpSession sessionObj = request.getSession(false);

	        if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }

        %>


        <p><strong>Passenger Name:</strong> ${user.name}</p>
		<p><strong>Email:</strong> ${user.email}</p>
		<p><strong>Flight:</strong> ${flight.flightNo} - ${flight.source} to ${flight.destination}</p>
		<p><strong>Flight Date:</strong> ${formattedFlightDate}</p>
		<p><strong>Departure Time:</strong> <fmt:formatDate value="${departureTime}" pattern="hh:mm a" /></p>
		<p><strong>Expected Landing Time:</strong> <fmt:formatDate value="${arrivalTime}" pattern="hh:mm a" /></p>
		<p><strong>Seats Booked:</strong> ${numSeats}</p>
		<p><strong>Total Price:</strong> ₹<fmt:formatNumber value="${numSeats * flight.price}" type="number" maxFractionDigits="2" /></p>
		<p><strong>Booking Date:</strong> <fmt:formatDate value="${bookingDate}" pattern="dd MMM yyyy, hh:mm a" /></p>
		
		
		<!-- Payment button here -->
		<form action="createOrder" method="POST">
		    <input type="hidden" name="flightId" value="${flight.id}" />
		    <input type="hidden" name="numSeats" value="${numSeats}" />
		    <input type="hidden" name="totalPrice" value="${numSeats * flight.price}" />
		    <button class="btn btn-success" type="submit">Proceed to Payment</button>
		</form>


        <a href="dashboard.jsp">🔙 Back to Dashboard</a>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
    
</body>
</html>
