function updateRating(val,id){
	document.getElementById(id).value = val;
}

function newSearch(form){
	var parkName = form.parkName.value;
	var parkSuburb = form.parkSuburb.value;
	var rating = form.rating.value;
	
	document.getElementById('searchedName').innerHTML = parkName;
	document.getElementById('searchedSuburb').innerHTML = parkSuburb;
	document.getElementById('searchedRating').innerHTML = rating;
	return false;
}
