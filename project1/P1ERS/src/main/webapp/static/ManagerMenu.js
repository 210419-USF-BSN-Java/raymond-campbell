let token = localStorage.getItem("token");

if(!token){
	window.location.href="http://localhost:8080/ERS/";
} else {
	
	let userInfo = token.split(":");
	if(userInfo[2] == 2){
		
		let welcome = document.getElementById("welcome");
		welcome.innerText="Welcome to the ERS, " + userInfo[1];

	} else if(userInfo[2] == 1){
		window.location.href="http://localhost:8080/ERS/employee.html";
	}
}

document.getElementById("viewAllById").addEventListener("click", viewAllById);
document.getElementById("viewPending").addEventListener("click", viewPending);
document.getElementById("viewResolved").addEventListener("click", viewResolved);
document.getElementById("viewEmployees").addEventListener("click", viewEmployees);
document.getElementById("logout").addEventListener("click", logOut);

function viewAllById(){

    window.location.href="http://localhost:8080/ERS/managerMenu/viewAllRequestById.html";
}

function viewPending(){

    window.location.href="http://localhost:8080/ERS/managerMenu/viewAllPending.html";
}

function viewResolved(){

    window.location.href="http://localhost:8080/ERS/managerMenu/viewAllResolved.html";
}

function viewEmployees(){

    window.location.href="http://localhost:8080/ERS/managerMenu/viewAllEmployee.html";
}

function logOut(){

    localStorage.removeItem("token");

    window.location.href="http://localhost:8080/ERS/index.html";

}