<?php

require 'PHP/validate.php';
if(isset($_POST['distance'])){
	containsSpecChars($errors, $_POST, 'distance');
	isEmpty($errors, $_POST, 'distance');
	if(isset($_POST['page'])){
		$page = $_POST['page'];
	} else {
		$errors['location'] = "Something went wrong.";
	}
	
	
	if(!isset($_POST['lat']) || $_POST['lat'] == ""){
		$errors['location'] = "Something went wrong.";
	}else {
		$lat = $_POST['lat'];
	}
	
	
	if(!isset($_POST['lon']) || $_POST['lon'] == ""){
		$errors['location'] = "Something went wrong.";
	}else {
		$lon = $_POST['lon'];
	}
	
	if ($errors) {
		// redisplay the form
		include 'index.php';
	} else {
	$distance = $_POST['distance'];
	
		header('Location:Results_page.php?distance='.$distance. '&page='.$page.'&lat='.$lat.'&lon='.$lon.'');
		//echo 'form submitted successfully with no errors';
	}
} else {
	$errors['distance'] = "Please set the distance";
	include 'index.php';
}

?>