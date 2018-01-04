var map;
  function initMap() {
	var LAT = parseFloat(document.getElementById("lat").innerHTML);
	var LON = parseFloat(document.getElementById("lon").innerHTML);
	var PARKNAME = document.getElementById("parkName").innerHTML;
	var PARKADDRESS = document.getElementById("parkAddress").innerHTML;
	var myLatLng = {lat: LAT, lng: LON};
	
	//Map with a single marker to show the parks location.
	
	map = new google.maps.Map(document.getElementById('map'), {
	  zoom: 15, 
	  center: myLatLng
	  
	});
	
	var marker = new google.maps.Marker({
          position: myLatLng,
          map: map,
          title: 'Lat: ' + String(LAT) + ' Lon: ' + String(LON)
    });	
	  
	  var infowindow = new google.maps.InfoWindow(); /* SINGLE */
	  
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
  
  function createStringContent(parkName, parkAddress, lat, lon){
	  var returnString = "";
	  var parknameString = "<h1>"+String(parkName)+"</h1>";
	  var parkaddresString= "<p> Address: "+String(parkAddress)+"</p>";
	  var parklatandlonString = "<p> Coordinates: "+String(lat)+", "+String(lon)+"</p>";
	  
	  returnString = parknameString + parkaddresString + parklatandlonString;
	  return returnString;
	  }
	  
	  placeMarker( myLatLng, createStringContent(PARKNAME,PARKADDRESS,LAT, LON));
  
  }