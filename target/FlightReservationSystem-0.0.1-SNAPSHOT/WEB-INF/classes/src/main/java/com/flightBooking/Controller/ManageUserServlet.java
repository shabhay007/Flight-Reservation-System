package com.flightBooking.Controller;

import java.io.IOException;
import java.util.List;

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


@WebServlet("/admin/users")
public class ManageUserServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession httpSession = req.getSession(false);
		String role = "admin";
		
		if(httpSession == null || !role.equals(httpSession.getAttribute("userRole"))) {
			res.sendRedirect("login.jsp");
			return;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		// fetching list of user from database
		List<User> users = session.createQuery("FROM User", User.class).getResultList();
		
		session.close();
		
		// sending the data to view
		req.setAttribute("users", users);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/manageUser.jsp");
		rd.forward(req, res);
	}

}
