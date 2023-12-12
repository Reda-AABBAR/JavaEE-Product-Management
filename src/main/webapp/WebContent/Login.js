let form = document.getElementById('form');

form.addEventListener("submit",(e) => {
	e.preventDefault();
	let userData = {
		'email': document.getElementById('email').value,
		'password': document.getElementById('password').value
	}
	
	let request = new XMLHttpRequest();
	request.open("POST","/testManyToMany/productSystemManagement?page=" + "Login");
	request.setRequestHeader('Content-Type', 'application/json',true);
	request.onreadystatechange = function () {
                if (request.readyState === 4) {
                    if (request.status === 200) {
                        // Successful response, handle the data
                        let responseData = JSON.parse(request.responseText);
                        if(responseData.errors['email'] != null)
                        document.getElementById('emailError').innerHTML = responseData.errors['email'];
                        else
                        document.getElementById('emailError').innerHTML = '';
                        if(responseData.errors['Password'] != null)
                        document.getElementById('passwordError').innerHTML = responseData.errors['Password'];
                        else
                        document.getElementById('passwordError').innerHTML = '';
                        if(responseData.user == null && responseData.errors['Password'] == null && responseData.errors['email'] == null)
                        	document.getElementById('NotFound').innerHTML = 'email or password not correcte!';
                        else{
							const user = responseData.user;
							window.location.href = 'ProductDesply.html?id=' + responseData.user.id;
						}
                        	
                        // You can perform additional actions based on the response
                    } else {
                        // Error occurred during the request
                        console.error('Error:', request.status, request.statusText);
                    }
                }
            };
    request.send(JSON.stringify(userData));
	
})
