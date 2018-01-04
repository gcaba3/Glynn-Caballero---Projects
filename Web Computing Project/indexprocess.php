<?php

require 'PHP/validate.php';
containsSpecChars($errors, $_POST, 'name');
if ($errors) {
	// redisplay the form
	include 'index.php';
} else {
	//checks if the entered values for the name is empty or just spaces. if they are change the get paramters to Any to be used in the results page search functions.
	if(trim($_POST['name']) == ''){
		$name = 'Any';
	} else {
		$name = $_POST['name'];
	}
	
	$suburb = $_POST['suburb'];
	
	//checks if the rating is empty or just spaces, changes the rating to 0 if they are to be used in the get parameters of the results page
	if(trim($_POST['rating']) == ''){
		$rating = 0;
	} else {
		$rating = $_POST['rating'];
	}
	
	//sets the page number for the resutls page
	if(isset($_POST['page'])){
		$page = $_POST['page'];
	}
	
	//open results page with the get parameters set
	header('Location:Results_page.php?name='.$name.'&suburb='.$suburb.'&rating='.$rating.'&page='.$page.'');
	//echo 'form submitted successfully with no errors';
}
?>