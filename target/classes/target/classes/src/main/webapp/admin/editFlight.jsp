<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.flightBooking.Model.Flight" %>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
    	response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    Flight flight = (Flight) request.getAttribute("flight");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Flight</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/editFlight.css">
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-wrapper">
        <h2>Edit Flight</h2>

        <form action="<%= request.getContextPath() %>/admin/editFlight" method="post" class="flight-form">
            <input type="hidden" name="id" value="<%= flight.getId() %>">

            <label>Flight Number:
                <input type="text" name="flightNumber" value="<%= flight.getFlightNo() %>" required>
            </label>

            <label>Source:
                <input type="text" name="source" value="<%= flight.getSource() %>" required>
            </label>

            <label>Destination:
                <input type="text" name="destination" value="<%= flight.getDestination() %>" required>
            </label>

            <label>Available Seats:
                <input type="number" name="seats" value="<%= flight.getSeats() %>" required>
            </label>

            <input type="submit" value="Update Flight" class="submit-btn">
        </form>

        <a href="flights" class="back-link">← Back to Flight List</a>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
