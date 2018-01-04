// JavaScript Document
function newSearch(form){
	var parkName = form.parkName.value;
	var parkSuburb = form.parkSuburb.value;
	var rating = form.rating.value;
	
	if (parkName === ""){
		document.getElementById('searchedName').innerHTML = "Any";
	} else {
		document.getElementById('searchedName').innerHTML = parkName;
	}
	document.getElementById('searchedSuburb').innerHTML = parkSuburb;
	document.getElementById('searchedRating').innerHTML = rating;
	return false; // change to true when database is implemented
}

function validateNewSearch(form){
	var regex = /[$&+,:;=?@#|'<>^*%!]/;
	if(regex.test(form.parkName.value)){
		document.getElementById('nameerror').style.visibility = 'visible';
		document.getElementById('nameerror').innerHTML = "Invalid character(s) used";	
		return false;
	} else {
		return newSearch(form);
	}
	
}