package com.flightBooking.Controller;

import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flightBooking.Model.Booking;
import com.flightBooking.Model.Booking.BookingStatus;
import com.flightBooking.Model.Flight;
import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/paymentSuccess")
public class PaymentSuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        int numSeats = Integer.parseInt(request.getParameter("numSeats"));

        // Get userId from session (assuming user is logged in)
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = (int) httpSession.getAttribute("userId");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Flight flight = session.get(Flight.class, flightId);
            User user = session.get(User.class, userId);

            if (flight == null || user == null) {
                request.setAttribute("error", "Invalid flight or user details.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            if (flight.getSeats() < numSeats) {
                request.setAttribute("error", "Not enough seats available.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            Booking booking = new Booking();
            booking.setFlight(flight);
            booking.setUser(user);
            booking.setNumSeats(numSeats);
            booking.setBookingDate(new Date());
            booking.setStatus(BookingStatus.BOOKED);

            session.save(booking);

            flight.setSeats(flight.getSeats() - numSeats);
            session.update(flight);

            transaction.commit();

            // Redirect to My Bookings page or dashboard
            response.sendRedirect("dashboard.jsp?success=1");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            request.setAttribute("error", "❌ Something went wrong.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
