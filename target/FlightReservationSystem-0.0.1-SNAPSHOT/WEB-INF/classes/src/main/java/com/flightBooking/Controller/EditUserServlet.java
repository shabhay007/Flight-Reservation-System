package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;

import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = session.get(User.class, id);
		session.close();
		
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/editUser.jsp");
		rd.forward(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String userName = req.getParameter("name");
		String userEmail = req.getParameter("email");
		String userPassword = req.getParameter("password");
		String userRole = req.getParameter("role");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = session.get(User.class, id);
		
		if(user != null) {
			user.setName(userName);
			user.setEmail(userEmail);
			user.setPassword(userPassword);
			user.setRole(userRole);
			
			// updating the user
			session.update(user);
		}
		
		session.getTransaction().commit();
		session.close();
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("message", "User updated successfully");
		
		res.sendRedirect(req.getContextPath() + "/admin/users");
	}

}
