<?php

require 'PHP/validate.php';
containsSpecChars($errors, $_POST, 'name');
if ($errors) {
	// redisplay the form
	include 'Results_page.php';
} else {
	//set the name value to Any if its blanks or just spaces
	if(trim($_POST['name']) == ''){
		$name = 'Any';
	} else {
		$name = $_POST['name'];
	}
	
	$suburb = $_POST['suburb'];
	
	//set the ratings to 0 just incase its blank
	if(trim($_POST['rating']) == ''){
		$rating = 0;
	} else {
		$rating = $_POST['rating'];
	}
	
	//if the page is already set use it again. else reset it to 0
	if(isset($_GET['page'])){
		$page = $_GET['page'];
	} else {
		$page = 0;
	}
	//open the page again with new values
	header('Location:Results_page.php?name='.$name.'&suburb='.$suburb.'&rating='.$rating.'&page='.$page.'');
	//echo 'form submitted successfully with no errors';
}
?>