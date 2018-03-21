package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.JdbcImpl;
import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StudentBean;

public class SearchStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			String regno = req.getParameter("regno");
			int regnum = Integer.parseInt(regno);

			StudentDAO dao = ServiceGenrator.genrateDAO();
			StudentBean data = dao.getStudentDetails(regnum);

			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			req.getRequestDispatcher("./Header.jsp").forward(req, resp);
			if (data != null) {
				req.setAttribute("data", data);
				req.getRequestDispatcher("./SearchStudent.jsp").forward(req, resp);
			} else {
				out.print("<h1>Regno is not exist</h1>");
			}
			req.getRequestDispatcher("./Footer.html").forward(req, resp);
		} else {
			resp.sendRedirect("./loginPage");
		}
	}
}
