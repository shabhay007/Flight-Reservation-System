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


@WebServlet("/admin/bookings")
public class ManageBookingServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
//		List<Booking> bookings = session.createQuery("From Booking", Booking.class).getResultList();
		
		List<Booking> bookings = session.createQuery(
			    "SELECT b FROM Booking b JOIN FETCH b.user JOIN FETCH b.flight", Booking.class
			).getResultList();

		session.close();
		
		req.setAttribute("bookings", bookings);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/manageBookings.jsp");
		rd.forward(req, res);
	}

}
