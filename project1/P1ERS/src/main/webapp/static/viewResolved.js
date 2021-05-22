
document.getElementById("resolved-btn").addEventListener("click", requestLogin);
console.log("inside resolved.js")
function requestLogin(){
	
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewResolved";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let ResolvedList = xhr.getResponseHeader("ResolvedList");
			sessionStorage.setItem("ResolvedList", ResolvedList);
			console.log("successfully queried the DB");
			console.log(sessionStorage.getItem("ResolvedList"));

			window.location.href="viewMyResolved.html";
		} 
		else if (xhr.readyState == 4){
			document.getElementById('resolved-message').innerHTML='You have no resolved reimbursements.';
		}
	}
	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `empId=${empId}`;
	xhr.send(requestBody);
}