package com.flightBooking.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		// checking if user exists or not
		Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
		query.setParameter("email", email);
		User user = query.uniqueResult();
		
		session.close();
		
		if (user == null) {
			
            // User not found, send an error message
            req.setAttribute("error", "Email not registered. Please sign up.");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            
        } else if (!user.getPassword().equals(password)) {
        	
            // Incorrect password
            req.setAttribute("error", "Incorrect Login credentials. Please try again.");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            
        } else {
        	
            // Successful login
            HttpSession httpSession = req.getSession();
            
            httpSession.setAttribute("userId", user.getId());
            httpSession.setAttribute("userName", user.getName());
            httpSession.setAttribute("userEmail", user.getEmail());
            httpSession.setAttribute("userRole", user.getRole());
            httpSession.setAttribute("currentUser", user);

            res.sendRedirect("dashboard.jsp");
        }
	}

}
