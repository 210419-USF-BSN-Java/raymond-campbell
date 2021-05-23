
viewEmployees();
console.log("inside pending.js")
	
function viewEmployees() {
	let empId = localStorage.getItem("Authorization");
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewAllEmployees";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let EmployeeList = xhr.getResponseHeader("EmployeeList");
			// sessionStorage.setItem("PendingList", PendingList);

			let jsonList = JSON.parse(EmployeeList);

			let content = document.getElementById("employee-list-table");
			console.log(content);


			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].userId + "</td><td>" + jsonList[i].firstName + "</td><td>" + jsonList[i].lastName + "</td><td>" + jsonList[i].email + "</td>";
			    console.log(request);
				content.insertAdjacentHTML('beforeend', request);
			}

		} 
		else if (xhr.readyState == 4){
			document.getElementById('pending-message').innerHTML='You have no employees.';
		}
	}

	
	/*
		Allows us to send form data as a single block in the body rather than as query params in the URL
	*/
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `empId=${empId}`;
	xhr.send(requestBody);
}
