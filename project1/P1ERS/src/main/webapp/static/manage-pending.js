
document.getElementById("manage-pending-btn").addEventListener("click", requestLogin);
console.log("inside manager pending.js")
function requestLogin(){
	
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ManagePending";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let ManagePendingList = xhr.getResponseHeader("ManagePendingList");
			sessionStorage.setItem("ManagePendingList", ManagePendingList);
			console.log("successfully queried the DB");
			console.log(sessionStorage.getItem("ManagePendingList"));

			window.location.href="viewManagePending.html";
		} 
		else if (xhr.readyState == 4){
			document.getElementById('manage-pending-message').innerHTML='There are no pending reimbursements.';
		}
	}
	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `empId=${empId}`;
	xhr.send(requestBody);
}