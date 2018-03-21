<%@page import="com.jspiders.dto.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%StudentBean data = (StudentBean)request.getAttribute("data"); %>
<body>
<form action="./update" method="post">
		<fieldset>
			<legend>Create Profile</legend>
			<table>
				<tr>
					<td>Regno</td>
					<td><input type="text" name="regno" value="<%=data.getRegno() %>"></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="fname" value="<%=data.getFirstName()%>"></td>
				</tr>
				<tr>
					<td>Middle Name</td>
					<td><input type="text" name="mname" value="<%=data.getMiddleName()%>"></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lname" value="<%=data.getLastName()%>"></td>
				</tr>
				<tr>
					<td>Guardian First Name</td>
					<td><input type="text" name="gfname" value="<%=data.getgFirstName()%>"></td>
				</tr>
				<tr>
					<td>Guardian Middle Name</td>
					<td><input type="text" name="gmname" value="<%=data.getgMiddleName()%>"></td>
				</tr>
				<tr>
					<td>Guardian Last Name</td>
					<td><input type="text" name="glname" value="<%=data.getgLastName()%>"></td>
				</tr>
				<tr>
					<td>IsAdmin</td>
					<td><select name="isadmin">
							<option value="">---Select---</option>
							<option value="Y">Yes</option>
							<option value="N">No</option>
					</select></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" value="<%=data.getPassword()%>"></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
					<td><input type="reset"></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>