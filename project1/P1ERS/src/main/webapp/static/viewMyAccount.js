
document.getElementById("view-info-btn").addEventListener("click", requestLogin);
console.log("inside account.js")
function requestLogin(){
	
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewAccount";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let UserAccount = xhr.getResponseHeader("UserAccount");
			sessionStorage.setItem("UserAccount", UserAccount);
			console.log("successfully queried the DB");
			console.log(sessionStorage.getItem("AccountList"));

			window.location.href="viewMyAccount.html";
		} 
		else if (xhr.readyState == 4){
			document.getElementById('account-message').innerHTML='Something went wrong.';
		}
	}
	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `empId=${empId}`;
	xhr.send(requestBody);
}