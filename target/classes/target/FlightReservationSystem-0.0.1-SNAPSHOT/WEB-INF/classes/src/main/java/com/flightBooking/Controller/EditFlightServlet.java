package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/editFlight")
public class EditFlightServlet extends HttpServlet {
	
	

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Flight flight = session.get(Flight.class, id);
        session.close();

        req.setAttribute("flight", flight);
        req.getRequestDispatcher("/admin/editFlight.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String flightNumber = req.getParameter("flightNumber");
        String source = req.getParameter("source");
        String destination = req.getParameter("destination");
        int seats = Integer.parseInt(req.getParameter("seats"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Flight flight = session.get(Flight.class, id);

        if (flight != null) {
            flight.setFlightNo(flightNumber);;
            flight.setSource(source);
            flight.setDestination(destination);
            flight.setSeats(seats);
            
            // updation
            session.update(flight);
        }

        session.getTransaction().commit();
        session.close();
        
        
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("message", "Flight updated successfully!");

        res.sendRedirect(req.getContextPath() + "/admin/flights"); // back to list
    }
}

