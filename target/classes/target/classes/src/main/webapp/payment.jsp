<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.flightBooking.Util.ConfigUtil" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complete Payment</title>
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	HttpSession sessionObj = request.getSession(false);
	
	if (sessionObj == null || sessionObj.getAttribute("userEmail") == null) {
	    response.sendRedirect("login.jsp");
	    return;
	}


    String orderId = (String) request.getAttribute("orderId");
    double amount = (double) request.getAttribute("amount"); // In rupees
    int flightId = (int) request.getAttribute("flightId");
    int numSeats = (int) request.getAttribute("numSeats");
    long amountInPaise = Math.round(amount * 100);
%>

<script>
    var options = {
        "key": "<%= ConfigUtil.get("razorpay.key_id") %>", // Razorpay Key ID
        "amount": "<%= amountInPaise %>", // amount in paise
        "currency": "INR",
        "name": "Flight Booking",
        "description": "Booking Payment",
        "order_id": "<%= orderId %>",
        "handler": function (response){
            // alert("Payment successful!");

            // Redirect to payment success page with flightId and numSeats
            window.location.href = "paymentSuccess?flightId=<%= flightId %>&numSeats=<%= numSeats %>";
        },
        "prefill": {
            "email": "", // You can pre-fill user's email if available
            "contact": "", // Same for contact
        },
        "theme": {
            "color": "#3399cc"
        }
    };

    var rzp1 = new Razorpay(options);
    rzp1.open();
</script>

</body>
</html>
