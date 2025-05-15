package com.flightBooking.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/searchFlights")
public class SearchFlightServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String source = req.getParameter("source");
		String destination = req.getParameter("destination");
		String dateStr = req.getParameter("date");
		
		Date date = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("error", "Invalid date format.");
            req.getRequestDispatcher("search.jsp").forward(req, res);
            return;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String queryStr = "FROM Flight WHERE source = :source AND destination = :destination AND flightDate = :date";
		
		Query<Flight> query = session.createQuery(queryStr, Flight.class);
		query.setParameter("source", source);
		query.setParameter("destination", destination);
		query.setParameter("date", date);
		
		List<Flight> flights = query.list();
		
		session.close();
		
		req.setAttribute("flights", flights);
		RequestDispatcher rd = req.getRequestDispatcher("flightResult.jsp");
		rd.forward(req, res);
	}

}
