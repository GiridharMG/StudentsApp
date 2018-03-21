package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StudentBean;

public class ViewAllStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			StudentBean data = (StudentBean) session.getAttribute("data");
			if (data.getIsAdmin().equals("Y")) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("./Header.jsp");
				dispatcher.include(req, resp);

				StudentDAO dao = ServiceGenrator.genrateDAO();
				List<StudentBean> list = dao.getAllStudentDetails();
				req.setAttribute("list", list);
				dispatcher = req.getRequestDispatcher("./ViewAllStudents.jsp");
				dispatcher.include(req, resp);
				dispatcher = req.getRequestDispatcher("./Footer.html");
				dispatcher.include(req, resp);
			}
		} else {
			resp.sendRedirect("./loginPage");
		}
	}// End of doGet

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}// End of Class
