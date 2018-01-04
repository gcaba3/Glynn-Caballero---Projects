<?php

function toRadians($degrees){
	$radians = ($degrees * pi()) / 180;
	return $radians;
}

function findDistance($lat1,$lat2,$lon1,$lon2){
	$R = 6371; // metres
	$x = toRadians($lat1);
	$y = toRadians($lat2);
	$q = toRadians(($lat2-$lat1));
	$w = toRadians(($lon2-$lon1));
		
	$a = sin($q/2) * sin($q/2) +
				cos($x) * cos($y) *
				sin($w/2) * sin($w/2);
	$c = 2 * atan2(sqrt($a), sqrt(1-$a));
		
	$d = $R * $c;
	return $d;
}

?>