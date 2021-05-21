document.getElementById("login-btn").addEventListener("click", requestLogin);

function requestLogin(){
	
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/EmployeeLogin";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let auth = xhr.getResponseHeader("Authorization");
			/*
				session is a storage that lasts for that specific page session(tab)
				here we store our auth token
			*/
			sessionStorage.setItem("token", auth);
			/*
				if the login is successful, redirects to the home page
			*/
			window.location.href="../employeeHome.jsp";
		} 
		else if (xhr.readyState == 4){
			document.getElementById('message').innerHTML='Incorrect credentials!';
		}
	}
	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${user}&password=${pass}`;
	xhr.send(requestBody);
}