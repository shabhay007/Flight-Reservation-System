package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;

import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("name");
		String userEmail = req.getParameter("email");
		String userPassword = req.getParameter("password");
		String userRole = req.getParameter("role");
		
		User user = new User();
		user.setName(userName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		user.setRole(userRole);
		
		// saving the user
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("message", "User added successfully");
		
		res.sendRedirect(req.getContextPath() + "/admin/users");
	}

}
