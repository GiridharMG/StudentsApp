package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.JdbcImpl;
import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StudentBean;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regno = req.getParameter("regno");
		String password = req.getParameter("password");
		int regnum = Integer.parseInt(regno);


		StudentDAO dao = ServiceGenrator.genrateDAO();
		boolean check = dao.checkAttempt(regnum);
		if (!check) {
			StudentBean data = dao.athenticate(regnum, password);

			if (data != null) {
				String checked = req.getParameter("remember");
				if (checked != null) {
					Cookie cookie = new Cookie("regno", regno);
					resp.addCookie(cookie);
				}
				HttpSession session = req.getSession();
				session.setAttribute("data", data);
				RequestDispatcher dispatcher = req.getRequestDispatcher("./Header.jsp");
				dispatcher.include(req, resp);
				dispatcher = req.getRequestDispatcher("./Home.jsp");
				dispatcher.include(req, resp);
				dispatcher = req.getRequestDispatcher("./Footer.html");
				dispatcher.include(req, resp);
			} else {
				resp.sendRedirect("./loginPage");
			}
		} else {
			getServletContext().setAttribute("msg", "Account locked!!! Ask Admin to unlock account");
			resp.sendRedirect("./loginPage");
		}
	}
}
