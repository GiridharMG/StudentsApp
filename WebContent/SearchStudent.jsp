<%@page import="com.jspiders.dto.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
StudentBean data = (StudentBean)request.getAttribute("data");
%>
<body>
<table>
		<tr>
			<th>Regno</th>
			<th>FirstName</th>
			<th>MiddleName</th>
			<th>LastName</th>
			<th>GuardianFirstName</th>
			<th>GuardianMiddleName</th>
			<th>GuardianLastName</th>
			<th>IsAdmin</th>
		</tr>
		<tr>
			<td><%=data.getRegno() %></td>
			<td><%=data.getFirstName() %></td>
			<td><%=data.getMiddleName() %></td>
			<td><%=data.getLastName() %></td>
			<td><%=data.getgFirstName() %></td>
			<td><%=data.getgMiddleName() %></td>
			<td><%=data.getgLastName() %></td>
			<td><%=data.getIsAdmin() %></td>
		</tr>
	</table>
</body>
</html>