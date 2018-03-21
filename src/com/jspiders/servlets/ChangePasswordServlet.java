package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;

public class ChangePasswordServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			int regno = Integer.parseInt(req.getParameter("regno"));
			String oldpass = req.getParameter("oldpass");
			String newpass = req.getParameter("newpass");
			String retypepass = req.getParameter("retypepass");

			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html");

			if (newpass.equals(retypepass)) {
				StudentDAO dao = ServiceGenrator.genrateDAO();
				if (!dao.changePassword(regno, oldpass, newpass)) {
					out.print("<h3>Either Regno or Password incorrect</h3>");
				}
			} else {
				// error response
				out.println("<h3>Password missmatch</h3>");
			}
			req.getRequestDispatcher("./bodyPage?page=Home").include(req, resp);
		} else {
			resp.sendRedirect("./loginPage");
		}
	}
}
