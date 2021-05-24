
viewResolved();
console.log("inside resolved.js");
	
function viewResolved() {
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewResolved";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let ResolvedList = xhr.getResponseHeader("ResolvedList");
			// sessionStorage.setItem("PendingList", PendingList);

			let jsonList = JSON.parse(ResolvedList);

			let content = document.getElementById("resolved-list-table");
			console.log(content);


			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimbAmount + "</td><td>" + jsonList[i].reimbDescription + "</td><td>" + jsonList[i].reimbId  + "</td>";
			    console.log(request);
				content.insertAdjacentHTML('beforeend', request);
			}

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
