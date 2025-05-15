package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
		query.setParameter("email", email);
		User user1 = query.uniqueResult();
		
		if(user1 != null) {
			session.close();
			req.setAttribute("error", "Email already registered! Please log in");
            req.getRequestDispatcher("register.jsp").forward(req, res);
            
            return; // to prevent further execution
		}
		
		Transaction tx = session.beginTransaction();
		
		// saving the user into database
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		
		session.save(user);
		
		tx.commit();
		session.close();
		
		System.out.println("User Registered successfully.");
		
		// After successful registration, redirecting to login page
		req.setAttribute("success", "User Registered successfully!");
        req.getRequestDispatcher("login.jsp").forward(req, res);
//		res.sendRedirect("login.jsp");
	}

}
