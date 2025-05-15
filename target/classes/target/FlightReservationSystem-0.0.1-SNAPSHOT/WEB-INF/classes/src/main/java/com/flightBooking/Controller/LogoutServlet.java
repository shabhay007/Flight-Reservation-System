package com.flightBooking.Controller;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("userName");
		
		session.invalidate(); // it will invalidate the data
		
		
//		HttpSession session = req.getSession(false); // don't create a new session
//        if (session != null) {
//            session.invalidate(); // destroys the session
//        }

        res.sendRedirect("login.jsp"); // back to login
	}

}
