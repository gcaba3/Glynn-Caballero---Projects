// JavaScript Document
function getLocation() {
	if (navigator.geolocation) {
		return navigator.geolocation.getCurrentPosition(showPosition, showError);
	} else {
		document.getElementById('locationerror').style.visibility = 'visible';
		document.getElementById('locationerror').innerHTML = "Geolocation is not supported by this browser.";
		return false;
	}
}

function showPosition(position) {
	// set the hidden input elements to the user's current longtitude and latitiude
	var lat,lon;
	lat = position.coords.latitude;
	lon = position.coords.longitude;
	document.getElementById('lat').value = lat;
	document.getElementById('lon').value = lon;
	return true;
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
	document.getElementById('locationerror').style.visibility = 'visible';
	document.getElementById('locationerror').innerHTML = msg;
	return false;
}