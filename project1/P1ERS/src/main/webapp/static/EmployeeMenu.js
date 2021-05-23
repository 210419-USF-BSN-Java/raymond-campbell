let token = localStorage.getItem("Authorization");

if(!token){
	window.location.href="http://localhost:8080/P1ERS/index.jsp";
} else {
	
	let userInfo = token.split(":");
	if(userInfo[2] == 2){
		
		let welcome = document.getElementById("welcome");
		welcome.innerText="Welcome to the ERS, " + userInfo[1];

	} else if(userInfo[2] == 1){
		window.location.href="http://localhost:8080/P1ERS/static/employeeHome.html";
	}
}

document.getElementById("submit-request-btn").addEventListener("click", submitRequest);
document.getElementById("pending-btn").addEventListener("click", viewPending);
document.getElementById("resolved-btn").addEventListener("click", viewResolved);
document.getElementById("view-info-btn").addEventListener("click", viewEmployees);
document.getElementById("logout").addEventListener("click", logOut);

function submitRequest(){

    window.location.href="http://localhost:8080/P1ERS/static/SubmitReimbursement.html";
}

function viewPending(){

    window.location.href="http://localhost:8080/P1ERS/static/viewMyPending.html";
}

function viewResolved(){

    window.location.href="http://localhost:8080/P1ERS/static/viewMyResolved.html";
}

function viewEmployees(){

    window.location.href="http://localhost:8080/P1ERS/static/viewMyAccount.html";
}

function logOut(){

    localStorage.removeItem("Authorization");

    window.location.href="http://localhost:8080/P1ERS/index.jsp";

}