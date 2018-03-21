package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		System.out.println(session);
		RequestDispatcher dispatcher;
		if (session != null) {
			dispatcher = req.getRequestDispatcher("./bodyPage?page=Home");
			dispatcher.forward(req, resp);
		} else {
			dispatcher = req.getRequestDispatcher("./login.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
