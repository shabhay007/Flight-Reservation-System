package com.flightBooking.Controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/flights")
public class ManageFlightServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// getSession(false) when you only want to work with an existing session 
		// and want to avoid creating a new one accidentally
		HttpSession session = req.getSession(false);
		
		String role = "admin";
		
		if(session == null || !role.equals(session.getAttribute("userRole"))) {
			res.sendRedirect("../login.jsp");
			return;
		}
		
		
		// fetching the data from the database
		Session dbSession = HibernateUtil.getSessionFactory().openSession();
		
		// storing the data
		List<Flight> flights = dbSession.createQuery("FROM Flight", Flight.class).getResultList();
		
		dbSession.close();
		
		req.setAttribute("flights", flights);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/manageFlights.jsp");
		rd.forward(req, res);
	}

}
