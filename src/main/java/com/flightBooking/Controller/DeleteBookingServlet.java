package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;

import com.flightBooking.Model.Booking;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/deleteBooking")
public class DeleteBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Booking booking = session.get(Booking.class, id);
        if (booking != null) {
            session.delete(booking);
        }

        session.getTransaction().commit();
        session.close();

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("message", "Booking deleted successfully");

        res.sendRedirect(req.getContextPath() + "/admin/bookings");
    }
}
