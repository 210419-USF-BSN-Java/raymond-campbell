<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Employee Login</h1>

	<form action="http://localhost:8080/P1ERS/EmployeeLogin" method="post" class="form-horizontal" enctype="form-data">
	<div class="container">
		<h3>Login</h3>
		<br>
		<label>Username</label>
		<input type="text" id="username" class="form-control">
		<br>
		<label>Password</label>
		<input type="password" id="password" class="form-control">
		<br>
		<button type="submit" class="btn btn-success">Login</button>
	</div>
	<br>
	<div id="message" style=" margin: auto;width: 50%;color:red" > </span>
		<script src="static/employeeToken.js"></script>
	</form>
</body>
</html>