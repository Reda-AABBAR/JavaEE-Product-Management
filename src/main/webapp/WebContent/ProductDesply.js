let userString = window.location.search;
if(userString.length === 0)
	window.location.href = 'Login.html';
userString = userString.substring(1);
let userData = userString.split('=');

let user = {};
user[userData[0]] = userData[1];

if(Object.keys(user).length === 0 || user['id'] == 0)
	window.location.href = 'Login.html';

let request = new XMLHttpRequest();

request.open('GET','/testManyToMany/productSystemManagement?page=' + 'ProductDesplay' + '&id=' + user['id'],true);

request.onload = ()=>{
	if(request.status >= 200 && request.status < 300){
		
		response = JSON.parse(request.responseText);
		console.log(response);
		let div = document.getElementById('contain');
		for(let creation in response){
			div.innerHTML += "<div class='col-12 product-container'> <div class='product-card'> <div class='product-name'>"+response[creation].product.name +"</div> <div>Price: $"+ response[creation].price +"</div> <div>Quantity: "+ response[creation].quantity +"</div> <div>Type: "+response[creation].product.Type +"</div></div></div>";
		}
	}else
	console.log('Request failed with status ' + request.status);
}

request.send();

const pagination = (link)=>{
	
	if(link === 'logout')
	window.location.href = 'Login.html';
	else 
	window.location.href = link + '?id=' + user['id'];
}