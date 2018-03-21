<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
String regno = "";

Cookie[] cookies = request.getCookies();
if(cookies!=null) {
	for (Cookie cookie : cookies) {
		if(cookie.getName().equals("regno"))
			regno = cookie.getValue();
	}
}
String msg = (String)application.getAttribute("msg");
application.removeAttribute("msg");
%>
<body>
<%if(msg!=null){ %>
<h4><%=msg %></h4>
<%} %>
	<fieldset>
		<legend>Login Page</legend>
		<form action="./login" method="post">
			<table>
				<tr>
					<td>Regno:</td>
					<td><input type="text" name="regno" value="<%=regno%>"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>Remember Me</td>
					<td><input type="checkbox" name="remember" value="checked">
				</tr>
				<tr>
					<td><input type="submit"></td>
					<td><input type="reset"></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<a href="./ForgotPassword.jsp">Forgot Password</a>
</body>
</html>


