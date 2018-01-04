// JavaScript Document
function validateLocation(form){
	var regex = /[$&+,:;=?@#|'<>^*%!]/;
	if (form.distance.value === ""){
		document.getElementById('locationerror').style.visibility = 'visible';
		document.getElementById('locationerror').innerHTML = "Distance can't be blank";
		return false;
	} else if (regex.test(form.distance.value)){
		window.alert("Distance contains invalid characters");
		return false;
	} else if (form.distance.value < 0){
		window.alert("Distance cant be lesser than 0");	
		return false;
	} else {
		getLocation();	
		return false;
		
	}
}

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
		return false; // change to true when database is in place
	} else {
		window.alert("Geolocation is not supported by this browser.");
	}
}

function showPosition(position) {
	//change functionality to parse the lat and lon to the server.
	window.alert("Current Coordinates \nLatitude: " + position.coords.latitude + ", Longitude: " + position.coords.longitude);
}

function showError(error) {
	var msg = "";
	switch(error.code) {
		case error.PERMISSION_DENIED:
			msg = "User denied the request for Geolocation.";
			break;
		case error.POSITION_UNAVAILABLE:
			msg = "Location information is unavailable.";
			break;
		case error.TIMEOUT:
			msg = "The request to get user location timed out.";
			break;
		case error.UNKNOWN_ERROR:
			msg = "An unknown error occurred.";
			break;
	}
	window.alert(msg);
}