<%@page import="com.jspiders.dto.SecurityQuestion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
SecurityQuestion question1 = (SecurityQuestion)request.getAttribute("question1");
SecurityQuestion question2 = (SecurityQuestion)request.getAttribute("question2");
%>
<body>
<fieldset>
	<legend>Reset Password</legend>
	<form action="./securitycheck" method="post">
		<table>
			<tr>
				<td>Regno</td>
				<td><input type="text" name="regno" value="<%=request.getParameter("regno")%>" readonly="readonly">
			</tr>
			<tr>
				<td><input type="text" name="q1" value="<%=question1.getQuestion()%>" readonly="readonly"></td>
				<td><input type="text" name="a1"></td>
			</tr>
			<tr>
				<td><input type="text" name="q2" value="<%=question2.getQuestion()%>" readonly="readonly"></td>
				<td><input type="text" name="a2"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit">
			</tr>
		</table>
	</form>
</fieldset>
</body>
</html>