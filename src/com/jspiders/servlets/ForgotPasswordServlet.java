package com.jspiders.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.SecurityQuestion;

public class ForgotPasswordServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regno = req.getParameter("regno");
		StudentDAO dao = ServiceGenrator.genrateDAO();
		List<SecurityQuestion> list = dao.securityQuection(Integer.parseInt(regno));
		if (list.isEmpty()) {
			req.getServletContext().setAttribute("msg", "Entered wrong Regno");
			resp.sendRedirect("./loginpage");
		} else {
			Random random = new Random();
			int index = -1;
			int i;
			int count = 1;
			for (int j = 0; j < 2; j++) {
				if ((i = Math.abs(random.nextInt() % 5)) != index) {
					req.setAttribute("question" + count, list.get(i));
					count++;
				}
			}
			req.getRequestDispatcher("./SecurityCheck.jsp").forward(req, resp);
		}
	}
}
