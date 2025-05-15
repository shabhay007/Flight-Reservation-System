package com.flightBooking.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.flightBooking.Model.Booking;
import com.flightBooking.Model.Booking.BookingStatus;
import com.flightBooking.Model.Flight;
import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/addBooking")
public class AddBookingServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<User> users = session.createQuery("from User", User.class).list();
        List<Flight> flights = session.createQuery("from Flight", Flight.class).list();

        session.close();

        req.setAttribute("users", users);
        req.setAttribute("flights", flights);

        RequestDispatcher rd = req.getRequestDispatcher("/admin/addBooking.jsp");
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String bookingDateStr = req.getParameter("bookingDate");
            int numSeats = Integer.parseInt(req.getParameter("numSeats"));
            BookingStatus status = BookingStatus.valueOf(req.getParameter("status"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            int flightId = Integer.parseInt(req.getParameter("flightId"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bookingDate = sdf.parse(bookingDateStr);

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            User user = session.get(User.class, userId);
            Flight flight = session.get(Flight.class, flightId);

            Booking booking = new Booking();
            booking.setBookingDate(bookingDate);
            booking.setNumSeats(numSeats);
            booking.setStatus(status);
            booking.setUser(user);
            booking.setFlight(flight);

            session.save(booking);
            session.getTransaction().commit();
            session.close();

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("message", "Booking added successfully");

            res.sendRedirect(req.getContextPath() + "/admin/bookings");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/admin/bookings");
        }
    }
}
