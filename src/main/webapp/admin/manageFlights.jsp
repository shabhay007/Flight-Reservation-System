<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flightBooking.Model.Flight" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <title>Manage Flights</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> <!-- Global styles -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/admin/manageFlights.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/messages.css">
</head>
<body>
    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    <jsp:include page="/WEB-INF/jsp/includes/messages.jsp" />

    <main class="main-wrapper">
        <h2>Flight Management</h2>
        
        <%
            String msg = (String) session.getAttribute("message");
            session.removeAttribute("message");
        
            if (msg != null) {
        %>
            <p style="color: green; font-weight: bold;"><%= msg %></p>
        <%
            }
        %>
        
        <a href="addFlight.jsp" class="add-btn">➕ Add New Flight</a><br><br>

        <table>
            <tr>
                <th>ID</th>
                <th>Flight Number</th>
                <th>Source</th>
                <th>Destination</th>
                <th>Seats</th>
                <th>Actions</th>
            </tr>

            <%
                List<Flight> flights = (List<Flight>) request.getAttribute("flights");
                for (Flight flight : flights) {
            %>
            <tr>
                <td><%= flight.getId() %></td>
                <td><%= flight.getFlightNo() %></td>
                <td><%= flight.getSource() %></td>
                <td><%= flight.getDestination() %></td>
                <td><%= flight.getSeats() %></td>
                <td>
                    <a href="editFlight?id=<%= flight.getId() %>" class="edit-btn">✏️ Edit</a> |
                    <a href="deleteFlight?id=<%= flight.getId() %>" class="delete-btn" onclick="return confirm('Are you sure you want to delete this flight?');">🗑️ Delete</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
