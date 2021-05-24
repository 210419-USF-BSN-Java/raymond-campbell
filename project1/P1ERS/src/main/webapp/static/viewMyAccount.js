
viewAccount();
console.log("Inside view my account.js")

function viewAccount(){
	
	let empId = localStorage.getItem("Authorization");
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/P1ERS/ViewAccount";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let UserAccount = xhr.getResponseHeader("UserAccount");

			let jsonList = JSON.parse(UserAccount);

			let content = document.getElementById("personal-account");

			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].userId + "</td><td>" + jsonList[i].username + "</td><td>" + jsonList[i].firstName + "</td><td>" + jsonList[i].lastName + "</td><td>" + jsonList[i].email + "</td>";
			    console.log(request);
				content.insertAdjacentHTML('beforeend', request);
			}
		} 
		else if (xhr.readyState == 4){
			document.getElementById('account-message').innerHTML='Something went wrong.';
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `empId=${empId}`;
	xhr.send(requestBody);

}