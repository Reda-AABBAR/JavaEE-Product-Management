let page = 'addProduct';
let userString = window.location.search;
if(userString.length === 0)
	window.location.href = 'Login.html';
userString = userString.substring(1);
let userData = userString.split('=');

let user = {};
user[userData[0]] = userData[1];

if(Object.keys(user).length === 0 || user['id'] == 0)
	window.location.href = 'Login.html';

const pagination = (link)=>{
	
	if(link === 'logout')
	window.location.href = 'Login.html';
	else 
	window.location.href = link + '?id=' + user['id'];
}


let req = new XMLHttpRequest();
req.open('GET', '/testManyToMany/productSystemManagement?page=' + page,true);

req.onload = ()=>{
	if(req.status >= 200 && req.status < 300){
		
		types = JSON.parse(req.responseText);
		console.log(types);
		let select = document.getElementById('productType');
		for(type in types){
			console.log(types[type]);
                var optionElement = document.createElement('option');
                optionElement.value = types[type].id;
                optionElement.text = types[type].typeName;
                select.appendChild(optionElement);
                }
            
	}else
	console.log('Error : ' + req.status);
};

req.send();


const form = document.getElementById('addProduct');

form.addEventListener('submit',(e)=>{
	e.preventDefault();
	const product = {
		'idUser': user['id'],
		'name' : document.getElementById('productName').value,
		'price' : document.getElementById('productPrice').value,
		'quantity' : document.getElementById('productQuantity').value,
		'type' : document.getElementById('productType').value
	};
	
	let req = new XMLHttpRequest();
	req.open('POST','/testManyToMany/productSystemManagement?page=' + page,true);
	req.setRequestHeader('Content-Type', 'application/json');
	req.onreadystatechange = ()=>{
		if (req.readyState === 4) {
                    if (req.status === 200) {
                        // Successful response, handle the data
                         // Show the alert
				        document.getElementById('productAddedAlert').style.display = 'block';
				
				        // Hide the alert after 3 seconds
				        setTimeout(function () {
				            document.getElementById('productAddedAlert').style.display = 'none';
				            pagination('myProduct.html');
				        }, 3000);
                        
                    } else {
                        // Error occurred during the request
                        console.error('Error:', req.status, req.statusText);
                    }
                }
        };
        console.log(product)
	req.send(JSON.stringify(product));
});


