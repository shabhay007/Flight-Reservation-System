package com.flightBooking.Controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.flightBooking.Model.User;
import com.flightBooking.Model.Flight;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/addBookingForm")
public class AddNewBookingDataFetchingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("FROM User", User.class).getResultList();
        List<Flight> flights = session.createQuery("FROM Flight", Flight.class).getResultList();
        session.close();

        req.setAttribute("users", users);
        req.setAttribute("flights", flights);

        RequestDispatcher rd = req.getRequestDispatcher("/admin/addBooking.jsp");
        rd.forward(req, resp);
    }
}
