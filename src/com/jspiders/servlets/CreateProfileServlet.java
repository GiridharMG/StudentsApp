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

import com.jspiders.dao.JdbcImpl;
import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.SecurityQuestion;
import com.jspiders.dto.StudentBean;

public class CreateProfileServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			StudentBean bean = (StudentBean) session.getAttribute("data");
			if (bean.getIsAdmin().equals("Y")) {
				int regno = Integer.parseInt(req.getParameter("regno"));
				String fname = req.getParameter("fname");
				String mname = req.getParameter("mname");
				String lname = req.getParameter("lname");
				String gfname = req.getParameter("gfname");
				String gmname = req.getParameter("gmname");
				String glname = req.getParameter("glname");
				String isadmin = req.getParameter("isadmin");
				String password = req.getParameter("password");

				StudentBean data = new StudentBean();
				data.setRegno(regno);
				data.setFirstName(fname);
				data.setMiddleName(mname);
				data.setLastName(lname);
				data.setgFirstName(gfname);
				data.setgMiddleName(gmname);
				data.setgLastName(glname);
				data.setIsAdmin(isadmin);
				data.setPassword(password);

				StudentDAO dao = ServiceGenrator.genrateDAO();
				dao.createProfile(data);

				RequestDispatcher dispatcher = req.getRequestDispatcher("./bodyPage?page=Home");
				dispatcher.include(req, resp);
			} else {
				session.setAttribute("msg", "Become admin and come back!!!");
				resp.sendRedirect("./bodyPage?page=Home");
			}
		} else {
			resp.sendRedirect("./loginPage");
		}
	}
}
