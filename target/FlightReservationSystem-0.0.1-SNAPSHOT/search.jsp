<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Flights</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/search.css">
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="search-container">
        <h2>Search Flights</h2>
        <form action="searchFlights" method="get">
            <label>Source:</label>
            <input type="text" name="source" required><br><br>

            <label>Destination:</label>
            <input type="text" name="destination" required><br><br>

            <label>Date:</label>
            <input type="date" name="date" required><br><br>

            <input type="submit" value="Search Flights">
        </form>
    </main>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

</body>
</html>
