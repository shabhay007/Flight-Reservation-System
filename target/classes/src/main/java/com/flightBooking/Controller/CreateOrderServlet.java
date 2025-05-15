package com.flightBooking.Controller;

import java.io.IOException;

import org.json.JSONObject;

import com.flightBooking.Util.ConfigUtil;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {

	private static final String RAZORPAY_KEY_ID = ConfigUtil.get("razorpay.key_id");
	private static final String RAZORPAY_KEY_SECRET = ConfigUtil.get("razorpay.key_secret");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	try {
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            int numSeats = Integer.parseInt(request.getParameter("numSeats"));
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            
            if (totalPrice <= 0) {
                throw new ServletException("Invalid price specified.");
            }

            long amountInPaise = Math.round(totalPrice * 100);

            RazorpayClient razorpay = new RazorpayClient(RAZORPAY_KEY_ID, RAZORPAY_KEY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amountInPaise);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "flight_" + flightId + "_seats_" + numSeats);

            Order order = razorpay.orders.create(orderRequest);

            // Pass order details to payment.jsp
            request.setAttribute("orderId", order.get("id"));
            request.setAttribute("amount", totalPrice);
            request.setAttribute("flightId", flightId);
            request.setAttribute("numSeats", numSeats);
            request.getRequestDispatcher("/payment.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
