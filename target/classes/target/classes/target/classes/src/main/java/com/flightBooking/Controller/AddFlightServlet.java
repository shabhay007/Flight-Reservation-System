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

import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addFlight")
public class AddFlightServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String flightNumber = req.getParameter("flightNumber");
		String source = req.getParameter("source");
		String destination = req.getParameter("destination");
		String departureTimeStr = req.getParameter("departureTime");
		String reachTimeStr = req.getParameter("arrivalTime");
		int totalSeats = Integer.parseInt(req.getParameter("seats"));
		double price = Double.parseDouble(req.getParameter("price"));
		String flightDateStr = req.getParameter("flightDate");
		
		
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Date departureTime = null;
//		Date reachTime = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date flightDate = null;
		
		try {
			
//			departureTime = timeFormat.parse(departureTimeStr);
//			reachTime = timeFormat.parse(reachTimeStr);
			flightDate = sdf.parse(flightDateStr);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return;
			
		}
		
		
		Flight flight = new Flight();
		flight.setFlightNo(flightNumber);
		flight.setSource(source);
		flight.setDestination(destination);
		flight.setDepartureTime(departureTimeStr);
		flight.setArrivalTime(reachTimeStr);
		flight.setSeats(totalSeats);
		flight.setPrice(price);
		flight.setFlightDate(flightDate);
		
		
		// db connection for saving the data
//		Configuration cfg = new Configuration().configure().addAnnotatedClass(Flight.class);
//		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
//		SessionFactory sf = cfg.buildSessionFactory(reg);
//		Session session = sf.openSession();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(flight);
		
		tx.commit();
		session.close();
		
		
		// Toaster message for adding new flight
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("message", "Flight added successfully!");
		
		// redirecting 
		res.sendRedirect(req.getContextPath() + "/admin/flights");
	}

}
