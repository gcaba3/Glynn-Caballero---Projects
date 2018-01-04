<?php
require('PHP/validate.php');
if(isset($_POST['username']) && $_POST['password']){
	//validate the username and password field
	isEmpty($errors,$_POST,'username');
	isEmpty($errors,$_POST,'password');
	
	$username = $_POST['username'];
	$password = $_POST['password'];
	$userID = validateLogin($errors,$username,$password); // validates username and password. and sets it to userID to be used in the startSession.php
	
	if($errors){
		include "Login.php";
	} else {
		include "PHP/startSession.php";
	}
} else {
	$errors['password'] = "Something went wrong";
	include "Login.php";
}

//checks if the input password and username actually exists in the database. returns the result of the query
function validateLogin(&$errors, $username, $password){
	require('PHP/connections.php');
	$stmt = $pdo->prepare(
	'SELECT *
	FROM members
	WHERE Username = :username and
	Password = SHA2(CONCAT(:password, salt),0)');
	$stmt->execute(array("username" => $username, "password" => $password));
	if($stmt->rowCount() == 0){
		$errors['username'] = "Failed login. Either the username or password is incorrect.";
	} else {
		$result = $stmt->fetchAll();
		return $result;
	}
	
}

?>