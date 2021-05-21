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

	<form  class="form-horizontal" enctype="form-data">
	<div class="container">
		<h3>Login</h3>
		<br>
		<label>Username</label>
		<input type="text" id="username" name="username" class="form-control">
		<br>
		<label>Password</label>
		<input type="password" id="password" name="password" class="form-control">
		<br>
		<button type="submit" id="login-btn" class="btn btn-success">Login</button>
	</div>
	<br>
	<div id="message" style=" margin: auto;width: 50%;color:red" > </span>
		<script src="employeeToken.js"></script>
	</form>
</body>
</html>