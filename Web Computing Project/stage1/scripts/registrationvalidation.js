// JavaScript Document
function validateName(form){
	var containsNum = checkForNumbers(form.fname.value);
	var containsSpecChars = checkforSpecialChar(form.fname.value);
	
	if (containsNum){
		document.getElementById('invalidName').style.visibility = 'visible';
		document.getElementById('invalidName').innerHTML = "A full name can't contain numbers";
		return false;
	} else if (containsSpecChars){
		document.getElementById('invalidName').style.visibility = 'visible';
		document.getElementById('invalidName').innerHTML = "A full name can't contain special characters";
		return false;
	} else if ( form.fname.value === ""){
		document.getElementById('invalidName').style.visibility = 'visible';
		document.getElementById('invalidName').innerHTML = "Full name can't be blank";
		return false;
	}
	else {
		return true;	
	}
}

function validatePostcode(form){
	var containsLetters = checkForLetters(form.postcode.value);
	var containsSpecChars = checkforSpecialChar(form.postcode.value);
	
	if(containsLetters){
		document.getElementById('invalidPostcode').style.visibility = 'visible';
		document.getElementById('invalidPostcode').innerHTML = "Postcode can't contain letters";
		return false;
	} else if (containsSpecChars){
		document.getElementById('invalidName').style.visibility = 'visible';
		document.getElementById('invalidName').innerHTML = "A full name can't contain special characters";
		return false;
	} else {
		return true;	
	}
}

function validateUsername(form){
	if ( form.username.value === ""){
		document.getElementById('invalidUsername').style.visibility = 'visible';
		document.getElementById('invalidUsername').innerHTML = "Username can't be blank";
		return false;
	} else {
		return true;	
	}
}

function validatePassword(form){
	var password = form.password.value;
	var confirmpassword = form.confpassword.value;
	if (password !== confirmpassword){
		document.getElementById('invalidPassword').style.visibility = 'visible';
		document.getElementById('invalidPassword').innerHTML = "Password's don't match";
		return false;
	} else if ( password === ""){
		document.getElementById('invalidPassword').style.visibility = 'visible';
		document.getElementById('invalidPassword').innerHTML = "Password can't be blank";
		return false;
	} else {
		return true;	
	}
}

function validateAll(form){
	var validName = validateName(form);
	var validPostcode = validatePostcode(form);
	var validUsername = validateUsername(form);
	var validPassword = validatePassword(form);
	
	if (validName && validPostcode && validUsername && validPassword ){
		return true;
	} else {
		return false;	
	}
}
