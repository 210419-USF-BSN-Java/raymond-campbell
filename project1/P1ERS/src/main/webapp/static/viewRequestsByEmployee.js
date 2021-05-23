document.getElementById("check").addEventListener("click", viewRequest);

function viewRequest(){
	let token = localStorage.getItem("Authorization")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewRequestHistory";
	let employeeID = document.getElementById("employeeID").value;
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            let requestList = xhr.getResponseHeader("requestList");
			let jsonList = JSON.parse(requestList);
			
			let content = document.getElementById("pending-list-table");

			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimbAmount + "</td><td>" + jsonList[i].reimbDescription + "</td><td>" + jsonList[i].reimbId + "</td>";
				content.insertAdjacentHTML('beforeend', request);
			}
		} 
		else if (xhr.readyState == 4){
			alert("Failed to load request information!, please check your login status.");
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.setRequestHeader("employeeID",employeeID);
	xhr.send();
}
