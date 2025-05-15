package com.flightBooking.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.flightBooking.Model.Booking;
import com.flightBooking.Model.Booking.BookingStatus;
import com.flightBooking.Util.HibernateUtil;
import com.flightBooking.Model.Flight;
import com.flightBooking.Model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/bookFlight")
public class BookFlightServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// fetching form parameters from req object
		int flightId = Integer.parseInt(req.getParameter("flightId"));
		int numberOfTickets = Integer.parseInt(req.getParameter("numOfSeats"));
		
		
		// fetching user from session
		HttpSession httpSession = req.getSession();
		User user = (User) httpSession.getAttribute("currentUser");

        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
		
		
		// Fetching the selected flight from db
//		Configuration cfg = new Configuration().configure();
//		cfg.addAnnotatedClass(Flight.class);
//		cfg.addAnnotatedClass(User.class);
//		cfg.addAnnotatedClass(Booking.class);
//		
//		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
//		SessionFactory sf = cfg.buildSessionFactory(reg);
//		Session session = sf.openSession();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
		
		Flight flight = session.get(Flight.class, flightId);
		
		
		if (flight.getSeats() < numberOfTickets) {
		    req.setAttribute("error", "Not enough available seats.");
		    req.getRequestDispatcher("error.jsp").forward(req, res);
		    return;
		}

		session.close();

		
		// forwarding to confirmation page
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date departureTime = null;
		Date arrivalTime = null;

		try {
		    departureTime = sdf.parse(flight.getDepartureTime());
		    arrivalTime = sdf.parse(flight.getArrivalTime());
		} catch (Exception e) {
		    e.printStackTrace();
		    req.setAttribute("error", "Failed to parse flight times.");
		    req.getRequestDispatcher("error.jsp").forward(req, res);
		    return;
		}

		SimpleDateFormat ffd = new SimpleDateFormat("dd/MM/yyyy");
		String formattedFlightDate = ffd.format(flight.getFlightDate());
		
		req.setAttribute("flight", flight);
		req.setAttribute("user", user);
		req.setAttribute("formattedFlightDate", formattedFlightDate);
		req.setAttribute("numSeats", numberOfTickets);
		req.setAttribute("departureTime", departureTime);
		req.setAttribute("arrivalTime", arrivalTime);
		req.setAttribute("bookingDate", new Date());

		RequestDispatcher rd = req.getRequestDispatcher("bookingSuccess.jsp");
		rd.forward(req, res);
	}

}
