package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/deleteFlight")
public class DeleteFlightServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Flight flight = session.get(Flight.class, id);
		
		if(flight != null) {
			session.delete(flight);
		}
		
		tx.commit();
		session.close();
		
		
		HttpSession httpSession = req.getSession();
        httpSession.setAttribute("message", "Flight Deleted successfully!");
		
		res.sendRedirect(req.getContextPath() + "/admin/flights");
	}

}
