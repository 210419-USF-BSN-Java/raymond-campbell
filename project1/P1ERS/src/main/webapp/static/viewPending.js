
document.getElementById("pending-btn").addEventListener("click", requestLogin);
console.log("inside pending.js")
function requestLogin(){
	
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewPending";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let PendingList = xhr.getResponseHeader("PendingList");
			// sessionStorage.setItem("PendingList", PendingList);

			let jsonList = JSON.parse(PendingList);

			let content = document.getElementById("pending-list-table");
			console.log(content);


			console.log(jsonList);
			console.log(jsonList[0])
			console.log(jsonList[0].reimbAmount);
			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimbAmount + "</td><td>" + jsonList[i].reimbDescription + "</td><td>" + jsonList[i].reimbId + "</td>";
			    console.log(request);
				content.insertAdjacentHTML('beforeend', request);
				window.location.href="viewMyPending.html";
			}

			console.log("successfully queried the DB");
			console.log(sessionStorage.getItem("PendingList"));



			window.location.href="viewMyPending.html";
		} 
		else if (xhr.readyState == 4){
			document.getElementById('pending-message').innerHTML='You have no pending reimbursements.';
		}
	}
	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `empId=${empId}`;
	xhr.send(requestBody);
}