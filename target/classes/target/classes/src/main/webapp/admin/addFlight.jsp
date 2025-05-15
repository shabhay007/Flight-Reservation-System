<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    <title>Add New Flight</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> <!-- Global styles -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/addFlight.css"> <!-- Page-specific styles -->
</head>
<body>

    <!-- Include common header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-wrapper">
        <h2>Add New Flight</h2>

        <form action="<%= request.getContextPath() %>/addFlight" method="post" class="flight-form">
		    <div class="form-row">
		        <label>Flight Number:
		            <input type="text" name="flightNumber" required>
		        </label>
		        <label>Source:
		            <input type="text" name="source" required>
		        </label>
		    </div>
		
		    <div class="form-row">
		        <label>Destination:
		            <input type="text" name="destination" required>
		        </label>
		        <label>Departure Time:
		            <input type="text" name="departureTime" placeholder="e.g. 10:00 AM" required>
		        </label>
		    </div>
		
		    <div class="form-row">
		        <label>Arrival Time:
		            <input type="text" name="arrivalTime" placeholder="e.g. 12:30 PM" required>
		        </label>
		        <label>Available Seats:
		            <input type="number" name="seats" required>
		        </label>
		    </div>
		
		    <div class="form-row">
		        <label>Price (in ₹):
		            <input type="number" step="0.01" name="price" required>
		        </label>
		        <label>Flight Date:
		            <input type="date" name="flightDate" required>
		        </label>
		    </div>
		
		    <input type="submit" value="Add Flight" class="submit-btn">
		</form>

        <a href="flights" class="back-link">← Back to Flight List</a>
    </main>

    <!-- Include common footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
