// JavaScript Document
function validateName(form){
	var regex = /[$&+,:;=?@#|'<>^*%!]/;
	if (regex.test(form.name.value)){
		document.getElementById('nameerror').style.visibility = 'visible';
		document.getElementById('nameerror').innerHTML = "Invalid character(s) used.";
		return false;
	} else {
		return  true;	
	}
}

function validateRating(form){
	var regex = /[^0-5]/;
	if(form.rating.value === ""){
		document.getElementById('error').style.visibility = 'visible';
		document.getElementById('error').innerHTML = "Rating can't be blank";
		return false;
	} else if (form.rating.value > 5){
		document.getElementById('error').style.visibility = 'visible';
		document.getElementById('error').innerHTML = "Rating can't be greater than 5";
		return false;
	} else if (form.rating.value < 0){
		document.getElementById('error').style.visibility = 'visible';
		document.getElementById('error').innerHTML = "Rating can't be lesser than 0";
		return false;
	} else if (regex.test(form.rating.value)){
		document.getElementById('error').style.visibility = 'visible';
		document.getElementById('error').innerHTML = "Rating must be an integer inbetween 0-5";
		return false;
	} else {
		return true;	
	}
}

function validateSearch(form){
	var validRating = validateRating(form);
	var validName = validateName(form);
	if (validRating && validName){
		return true;
	} else {
		return false;
	}
}