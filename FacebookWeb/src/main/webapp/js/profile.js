

const profilePageBtn = document.getElementById("profilePage");
const searchBox=document.getElementById("searchBox");

function errorHandler(error) {
  console.log(error);
}

function feedData(){
	
	
}

function viewProfile(){
 
	let sem=sessionStorage.getItem("searchEmail");
	let photo;
  
	let profileName = document.getElementById("profileName");
	let profileCity = document.getElementById("profileCity");
	let profileAge = document.getElementById("profileAge");
	let profileGender = document.getElementById("profileGender");
	let profilePhoto = document.getElementById("profilePhoto");
	
	let userEmail=sessionStorage.getItem("userEmail");
	if (sem=== userEmail) {
		friendBtn.style.display = "none";
	    document.getElementById("update").style.display = "block";
	    document.getElementById("delete").style.display = "block";
	  } else {
		document.getElementById("update").style.display = "none";
		document.getElementById("delete").style.display = "none";
		fetch("AddFriend?method=loadFriend&searchProfile=" + sem)
		.then((response) => response.json())
		.then((json) => {
			if(json.accept==1){
				friendBtn.innerHTML = "removeFriend";
				friendBtn.style.backgroundColor = "#00FFFF";
			}else{
				friendBtn.innerHTML = "addFriend";
				friendBtn.style.backgroundColor = "transparent";
				friendBtn.style.opacity = "1";
			}
		});
		document.getElementById("friend").style.display = "block";
	  }
	
	
	
	fetch("Profiledata?searchProfile=" + sem)
		.then((response) => response.json())
		.then((json) => {	
	  console.log(json);
	 
	  profileName.innerHTML=json.name;
	  profileGender.innerHTML=json.gender;
	  profileCity.innerHTML=json.city;
	  
	 
	  
	  photo=json.photo;
	  
	  let path="images/profile-pic.png";
	  if(photo=="true"){
		  let folName=json.email.split(".")[0];
		  console.log(folName);
		  path="img/"+folName+"/profilePic.jpg";
	  }
	  profilePhoto.src=path;
	  profilePageBtn.src=path;
	  profileAge.innerHTML=json.age;
	  
  }).catch(errorHandler); 
}




function searchUser() {


	if (event.keyCode === 13) {
		
		let em=searchBox.value;
		console.log(em);
		sessionStorage.setItem("searchEmail",em);
		location.href = "./profile.html";
	}
}



function setUserProfile(){
	let em=sessionStorage.getItem("userEmail");
	sessionStorage.setItem("searchEmail",em);
	location.href = "./profile.html";
}

searchBox.addEventListener("keyup", searchUser);
profilePageBtn.addEventListener("click", setUserProfile);
