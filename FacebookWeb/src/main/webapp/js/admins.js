
function block(email) {
	if(event.target.innerHTML=="BlockUser"){
		event.target.innerHTML="UnblockUser";
		fetch("AdminControl?method="+"BlockUser"+"&email="+email)
	}else{
		event.target.innerHTML="BlockUser";
		fetch("AdminControl?method="+"UnblockUser"+"&email="+email)
	}
	console.log(email);
  
}
function errorHandler(){
	console.log("error");
}
function getUsers() {
	 var ul = document.getElementById("dynamic-list");
	fetch("AdminControl?method="+"getdata")
	.then((response) => response.json())
	.then((json) => {	
		for(var i = 0; i < json.length; i++) {
		    
			var obj = json[i];
		 
		    let status="BlockUser";
		    if(obj.block==1){
		    	status="UnblockUser";
		    }
		    let li = `<li><h2>${obj.email} </h2><button id="blockBtn" onclick="block('${obj.email}')">${status}</button></li>`;
		    ul.innerHTML += li;
		    
		}
	})
	.catch(errorHandler); 
    
  }


