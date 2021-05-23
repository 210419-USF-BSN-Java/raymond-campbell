
viewPending();
console.log("inside pending.js")
	
function viewPending() {
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ManagePending";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let ManagePendingList = xhr.getResponseHeader("ManagePendingList");
			// sessionStorage.setItem("PendingList", PendingList);

			let jsonList = JSON.parse(ManagePendingList);

			let content = document.getElementById("manage-pending-list-table");
			console.log(content);


			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimbAmount + "</td><td>" + jsonList[i].reimbDescription + "</td><td>" + jsonList[i].reimbId + "</td>";
			    console.log(request);
				content.insertAdjacentHTML('beforeend', request);
			}

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
