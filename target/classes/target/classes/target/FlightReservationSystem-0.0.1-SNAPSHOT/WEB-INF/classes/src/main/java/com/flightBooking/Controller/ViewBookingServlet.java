package com.flightBooking.Controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.flightBooking.Model.Booking;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewBooking")
public class ViewBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession httpsSession = req.getSession(false);
        int id = (int) httpsSession.getAttribute("userId");

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Booking> bookings = session.createQuery("FROM Booking WHERE user.id = :userId", Booking.class)
        		.setParameter("userId", id)
        		.getResultList();

        session.close();

        req.setAttribute("bookings", bookings);
        RequestDispatcher rd = req.getRequestDispatcher("myBookings.jsp");
        rd.forward(req, res);
    }
}
