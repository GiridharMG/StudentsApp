package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StudentBean;

public class DeleteStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			StudentBean bean = (StudentBean) session.getAttribute("data");
			if (bean.getIsAdmin().equals("Y")) {
				String regno = req.getParameter("regno");
				int regnum = Integer.parseInt(regno);

				StudentDAO dao = ServiceGenrator.genrateDAO();
				dao.deleteStudent(regnum);

				RequestDispatcher dispatcher = req.getRequestDispatcher("./viewallstudents");
				dispatcher.forward(req, resp);
			} else {
				session.setAttribute("msg", "Become admin and come back!!!");
				resp.sendRedirect("./bodyPage?page=Home");
			}
		} else {
			resp.sendRedirect("./loginPage");
		}
	}
}
