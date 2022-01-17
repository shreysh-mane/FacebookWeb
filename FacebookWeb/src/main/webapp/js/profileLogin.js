

function setUserid() {
	let email = document.getElementById("signin-Email").value;
	sessionStorage.removeItem("userEmail");
	sessionStorage.setItem("userEmail",email);
}



