var map;
function initMap() {
	//Set all the required variabls
	var LAT = document.getElementsByClassName("parkLat");
	var LON = document.getElementsByClassName("parkLon");
	var PARK = document.getElementsByName("park");
	var PARKSuburb = document.getElementsByName("parkSuburb");
	var PARKStreet = document.getElementsByName("parkStreet");
	//var LAT = -27.38006149;
	//var LON = 153.0387005;s
	//change the first lat and lon to ints to be used later
	var myLatLng = [{lat: parseFloat(LAT[0].innerHTML), lng: parseFloat(LON[0].innerHTML)}];
	//push the other lat and lon elements into the array to be used in the map
	for( var i = 1; i < LAT.length; i++){
		myLatLng.push({lat: parseFloat(LAT[i].innerHTML), lng: parseFloat(LON[i].innerHTML)})
	}
	
	map = new google.maps.Map(document.getElementById('map'), {
		  zoom:15,
		  center: myLatLng[0]
		});
	
	var infowindow = new google.maps.InfoWindow(); /* SINGLE */
	
	//function that places a new marker based on the lat and lon
	function placeMarker(latAndLon, content) {
    var marker = new google.maps.Marker({
      position : latAndLon,
      map      : map,
    });
    google.maps.event.addListener(marker, 'click', function(){
        infowindow.close(); // Close previously opened infowindow
        infowindow.setContent(content);
        infowindow.open(map, marker);
    });
  }
  
  //returns a string based on the given variables. To be displayed in the content window of the marker
  function createStringContent(park, parkSuburb, parkStreet, lat, lon){
	  var returnString = "";
	  var parknameString = "<h1>"+String(park.innerHTML)+"</h1>";
	  var parksuburbString = "<p>"+String(parkSuburb.innerHTML)+"</p>";
	  var parkstreetString = "<p>"+String(parkStreet.innerHTML)+"</p>";
	  var parklatandlonString = "<p> Coordinates: "+String(lat.innerHTML)+", "+String(lon.innerHTML)+"</p>";
	  var parkurlString = '<a href="'+park.href+'"> Individual Page </a>';
	  
	  returnString = parknameString + parksuburbString + parkstreetString + parklatandlonString  + parkurlString;
	  return returnString;
	  }
	
	for( var j = 0; j < LAT.length; j++){
		placeMarker( myLatLng[j], createStringContent(PARK[j],PARKSuburb[j],PARKStreet[j], LAT[j], LON[j]));
	}
	
	

  }