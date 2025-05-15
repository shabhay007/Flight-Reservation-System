package com.flightBooking.Controller;

import java.io.IOException;

import org.hibernate.Session;

import com.flightBooking.Model.User;
import com.flightBooking.Util.HibernateUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	
	// when we hit servlet using <a>/anchor tag, we need to use get request instead of post request
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = session.get(User.class, id);
		
		session.beginTransaction();
		
		if(user != null) {
			session.delete(user);
		}
		
		session.getTransaction().commit();
		session.close();
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("message", "User deleted successfully");
		
		res.sendRedirect(req.getContextPath() + "/admin/users");
		
	}

}
