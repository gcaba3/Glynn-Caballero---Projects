<?php
require'PHP/connections.php';
require'PHP/validate.php';
require('PHP/functions.php');

if(isset($_POST['review'])){
	session_start();
	echo "hello";
	isEmpty($errors, $_POST, 'review');
	if(!$errors){
		$stmt = $pdo->prepare('INSERT INTO reviews (Idmember, Idpark, Dateposted, Review, Rating)
		VALUES (:memberid, :parkid, :date, :review, :rating)');
		$stmt->execute(array("memberid" => $_SESSION['userID'], "parkid" => $_POST['parkID'] , "date" => $_POST['date'], "review" => filter_var($_POST['review'], FILTER_SANITIZE_STRING), "rating" => $_POST['rating']));
		//echo $_POST['parkID'];
		header('Location: individualPark.php?id='.$_POST['parkID']);
		
	} else {
		//Just redisplays the page. with no errors, cuz i didn't use php to display the html elements. 
		header('Location: individualPark.php?id='.$_POST['parkID']);
	}
} else {
	header('Location: individualPark.php?id='.$_POST['parkID']);
}
?>