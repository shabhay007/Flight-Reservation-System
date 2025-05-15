package com.flightBooking.Controller;

import java.io.IOException;
import java.text.ParseException;
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


@WebServlet("/admin/editBooking")
public class EditBookingServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Booking booking = session.get(Booking.class, id);
		List<Flight> flights = session.createQuery("FROM Flight", Flight.class).getResultList();
		List<User> users = session.createQuery("FROM User", User.class).getResultList();
		
		session.close();
		
		req.setAttribute("booking", booking);
		req.setAttribute("flights", flights);
		req.setAttribute("users", users);
		
		RequestDispatcher rd = req.getRequestDispatcher("/admin/editBooking.jsp");
		rd.forward(req, res);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			
			int id = Integer.parseInt(req.getParameter("id"));
			String dateStr = req.getParameter("bookingDate");
			int numOfSeats = Integer.parseInt(req.getParameter("numSeats"));
			BookingStatus status = BookingStatus.valueOf(req.getParameter("status"));
			int userId = Integer.parseInt(req.getParameter("userId"));
			int flightId = Integer.parseInt(req.getParameter("flightId"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date bookingDate = sdf.parse(dateStr);
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Booking booking = session.get(Booking.class, id);
			User user = session.get(User.class, userId);
			Flight flight = session.get(Flight.class, flightId);
			
			if(booking != null) {
				booking.setBookingDate(bookingDate);
				booking.setNumSeats(numOfSeats);
				booking.setStatus(status);
				booking.setUser(user);
				booking.setFlight(flight);
				
				session.update(booking);
			}
			
			session.getTransaction().commit();
			session.close();
			
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("message", "Booking updated successfully");
			
			res.sendRedirect(req.getContextPath() + "/admin/bookings");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect(req.getContextPath() + "/admin/bookings");
		}
	}

}
