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

document.getElementById("view-requests-by-emp-btn").addEventListener("click", viewAllByEmp);
document.getElementById("manage-pending-btn").addEventListener("click", viewPending);
document.getElementById("view-resolved-btn").addEventListener("click", viewResolved);
document.getElementById("view-employees-btn").addEventListener("click", viewEmployees);
document.getElementById("logout-btn").addEventListener("click", logOut);

function viewAllByEmp(){

    window.location.href="http://localhost:8080/P1ERS/static/ViewRequestsByEmployee.html";
}

function viewPending(){

    window.location.href="http://localhost:8080/P1ERS/static/viewManagePending.html";
}

function viewResolved(){

    window.location.href="http://localhost:8080/P1ERS/static/ViewResolvedreimbursements.html";
}

function viewEmployees(){

    window.location.href="http://localhost:8080/P1ERS/static/ViewAllEmployees.html";
}

function logOut(){

    localStorage.removeItem("Authorization");

    window.location.href="http://localhost:8080/P1ERS/index.jsp";

}