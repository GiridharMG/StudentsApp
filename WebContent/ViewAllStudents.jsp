<%@page import="com.jspiders.dto.StudentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	List<StudentBean> list = (List) request.getAttribute("list");
%>
<body>
	<table border='1' width='100%'>
		<tr bgcolor=\"green\">
			<td>Reg. No.</td>
			<td>First Name</td>
			<td>Middle Name</td>
			<td>Last Name</td>
			<td>G First Name</td>
			<td>G Middle Name</td>
			<td>G Last Name</td>
			<td>Is Admin</td>
		</tr>
		<%
			for (StudentBean data : list) {
		%>
		<tr>
			<td><%=data.getRegno() %></td>
			<td><%=data.getFirstName() %></td>
			<td><%=data.getMiddleName() %></td>
			<td><%=data.getLastName() %></td>
			<td><%=data.getgFirstName() %></td>
			<td><%=data.getgMiddleName() %></td>
			<td><%=data.getgLastName() %></td>
			<td><%=data.getIsAdmin() %></td>
			<td><a href="./delete?regno=<%=data.getRegno()%>">delete</a></td>
			<td><a href="./edit?regno=<%=data.getRegno()%>">edit</a></td>
			<%if(data.getAttempt()>=3) { %>
			<td><a href="./unlock?regno=<%=data.getRegno()%>">unlock</a></td>
			<%} %>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>