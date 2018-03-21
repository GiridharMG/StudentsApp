package com.jspiders.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BodyPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			String pageName = req.getParameter("page");
			RequestDispatcher dispatcher = null;
			String url = "";
			if (pageName.equals("Home")) {
				url = "./Home.jsp";
			} else if (pageName.equals("CreateProfile")) {
				url = "./CreateProfile.html";
			} else if (pageName.equals("ChangePassword")) {
				url = "./ChangePassword.html";
			} else if (pageName.equals("SearchStudent")) {
				url = "./SearchStudent.html";
			} else if (pageName.equals("Logout")) {
				url = "./logout";
				req.getRequestDispatcher(url).forward(req, resp);
				return;
			}
			dispatcher = req.getRequestDispatcher("./Header.jsp");
			dispatcher.include(req, resp);

			dispatcher = req.getRequestDispatcher(url);
			dispatcher.include(req, resp);

			dispatcher = req.getRequestDispatcher("./Footer.html");
			dispatcher.include(req, resp);
		} else {
			resp.sendRedirect("./loginPage");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
