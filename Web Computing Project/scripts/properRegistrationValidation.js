// JavaScript Document
function turnOffError(errorId){
	document.getElementById(errorId).style.visibility = 'hidden';
}

function checkForNumbers(str){
	var regex = /[0-9]/;
	
	if(regex.test(str)){
		return true;	
	} else {
		return false;	
	}
}

function checkForLetters(str){
	var regex = /[a-zA-Z]/;
	
	if(regex.test(str)){
		return true;	
	} else {
		return false;	
	}
}

function checkforSpecialChar(str){
	var regex = /[{}$&+,:;=?@#|~'<>^*%!]/;
	
	if(regex.test(str)){
		return true;	
	} else {
		return false;	
	}
}

function validateName(form){
	var containsNum = checkForNumbers(form.fname.value);
	var containsSpecChars = checkforSpecialChar(form.fname.value);
	
	if(form.fname.value.trim() === ''){
		document.getElementById('fnameerror').innerHTML = "Please enter your full name.";
		document.getElementById('fnameerror').style.visibility = "visible";
		return false;
	}else if(containsNum){
		document.getElementById('fnameerror').innerHTML = "Can't use numbers in the full name.";
		document.getElementById('fnameerror').style.visibility = "visible";
		return false;
	} else if (containsSpecChars){
		document.getElementById('fnameerror').innerHTML = "Can't use special characters in the full name.";
		document.getElementById('fnameerror').style.visibility = "visible";
		return false;
	} else {
		return true;
	}
}

function validateEmail(form){
	var regex = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}/;
	
	if(!regex.test(form.email.value)){
		document.getElementById('emailerror').innerHTML = "Email is not a valid format: example@email.com.";
		document.getElementById('emailerror').style.visibility = "visible";
		return false;
	} else if(form.email.value.trim() === ''){
		document.getElementById('emailerror').innerHTML = "Please fill in email.";
		document.getElementById('emailerror').style.visibility = "visible";
		return false;
	}else {
		return true;
	}
}

function validatePassword(form){
	if(form.password.value.trim() === '' || form.confpassword.value.trim() === ''){
		document.getElementById('passworderror').innerHTML = "Please fill in both the Password and Confirm Password.";
		document.getElementById('passworderror').style.visibility = "visible";
	return false;
	}else if(form.password.value !== form.confpassword.value){
		document.getElementById('passworderror').innerHTML = "Password's don't match. Try again.";
		document.getElementById('passworderror').style.visibility = "visible";
	return false;
	} else {
		return true;
	}
}

function validateUsername(form){
	if(form.username.value.trim() === ''){
		document.getElementById('usernameerror').innerHTML = "Please fill in username.";
		document.getElementById('usernameerror').style.visibility = "visible";
	return false;
	} else {
		return true;
	}
}

function validateDate(form){
	var regex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
  
	if (form.dob.value.trim() === ""){
		form.dob.value = "";
		return true;
	} else if(!regex.test(form.dob.value)){
		document.getElementById('doberror').style.visibility = 'visible';
		document.getElementById('doberror').innerHTML = "Invalid date. Enter date: YYYY-mm-dd.";
		return false;
	}else{
		return true;
	}
}

function validatePostcode(form){
	if(form.postcode.value.trim() !== ""){
		var containsLetters = checkForLetters(form.postcode.value);
		var containsSpecChars = checkforSpecialChar(form.postcode.value);
		if(containsLetters){
			document.getElementById('postcodeerror').style.visibility = 'visible';
			document.getElementById('postcodeerror').innerHTML = "Postcode can't contain letters.";
		return false;
		} else if (containsSpecChars){
			document.getElementById('postcodeerror').style.visibility = 'visible';
			document.getElementById('postcodeerror').innerHTML = "Postcode can't contain special characters";
		return false;
		} else {
			return true;
		}
	}else {
		form.postcode.value = "";
		return true;
	}
	
}

function validateAll($form){
	var validName = validateName($form);
	var validEmail = validateEmail($form);
	var validPassword = validatePassword($form);
	var validUsername = validateUsername($form);
	var validDate = validateDate($form);
	var validPostcode = validatePostcode($form);
	
	if(validName && validEmail && validPassword && validUsername && validDate && validPostcode){
		return true;	
	}else {
		return false;
	}
}



