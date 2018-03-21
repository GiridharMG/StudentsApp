package com.jspiders.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StudentBean;

public class UnlockAccountServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session!=null) {
			StudentBean data = (StudentBean)session.getAttribute("data");
			if(data.getIsAdmin().equals("Y")) {
				int regno = Integer.parseInt(req.getParameter("regno"));
				StudentDAO dao = ServiceGenrator.genrateDAO();
				dao.resetAttempt(regno);
				resp.sendRedirect("./viewallstudents");
			}else {
				resp.sendRedirect("./bodyPage?page=Home");
			}
		}else {
			resp.sendRedirect("./loginpage");
		}
	}
}
