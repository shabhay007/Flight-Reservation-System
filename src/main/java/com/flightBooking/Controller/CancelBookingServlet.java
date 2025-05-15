package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flightBooking.Model.Booking;
import com.flightBooking.Model.Booking.BookingStatus;
import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int bookingId = Integer.parseInt(req.getParameter("bookingId"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			
			Booking booking = session.get(Booking.class, bookingId);
			
			if(booking != null && booking.getStatus() != BookingStatus.CANCELLED) {
				booking.setStatus(BookingStatus.CANCELLED);
				
				// restoring available seats after cancellation
				Flight flight = booking.getFlight();
				int restoredSeats = flight.getSeats() + booking.getNumSeats();
				flight.setSeats(restoredSeats);
				
				session.update(flight);
				session.update(booking);
				
				tx.commit();
				
				req.setAttribute("message", "Booking Cancelled Successfully");
			}
			else {
				req.setAttribute("message", "Booking not found or already cancelled");
			}
			
		} 
		catch (Exception e) {
			
			if(tx != null) tx.rollback();
			
			e.printStackTrace();
			req.setAttribute("error", "Something went wrong during cancellation.");
			
		}
		finally {
			session.close();
		}
		
		res.sendRedirect("viewBooking");
	}

}
