
document.getElementById("reimb-subm").addEventListener("click", requestLogin);
function requestLogin(e){
    e.preventDefault();
    let empId = localStorage.getItem("Authorization");
	let description = document.getElementById("description").value;
	let amount = document.getElementById("amount").value;
	let type = document.getElementById("type").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ReimbursementSubmit";
	xhr.open("POST", url);
    
	xhr.onreadystatechange = function(){
        console.log("inside js function");
		if(xhr.readyState == 4 && xhr.status == 200){
            console.log("Request was submitted");
			/*
				if the login is successful, redirects to the home page
			*/
			window.location.href="http://localhost:8080/P1ERS/static/employeeHome.html";
		} 
		else if (xhr.readyState == 4){                
            console.log("Request was denied");            
			document.getElementById('message').innerHTML='Submission Failed';
		}
	}
	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `description=${description}&amount=${amount}&type=${type}&empId=${empId}`;
	console.log(requestBody);
	xhr.send(requestBody);
    console.log(description + amount + type);

}