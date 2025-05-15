<%@ page import="com.flightBooking.Model.Flight" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Flight</title>
</head>
<body>
    <h2>Book a Flight</h2>
    <form action="bookFlight" method="POST">
        <label for="flightId">Select Flight:</label>
        <select name="flightId">
            <%
                List<Flight> flights = (List<Flight>) request.getAttribute("flights");
                for (Flight flight : flights) {
            %>
                <option value="<%= flight.getId() %>">
                    <%= flight.getFlightNo() + " - " + flight.getSource() + " to " + flight.getDestination() %>
                </option>
            <% } %>
        </select><br>

        <label for="numTickets">Number of Tickets:</label>
        <input type="number" name="numTickets" required><br>

        <input type="submit" value="Book Now">
    </form>
</body>
</html>
