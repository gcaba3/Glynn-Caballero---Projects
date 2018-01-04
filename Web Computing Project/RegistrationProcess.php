<?php

if (isset($_POST['fname'])) {
	require 'PHP/validate.php';
	//server validation of full name field
	isEmpty($errors, $_POST, 'fname');
	containsSpecChars($errors, $_POST, 'fname');
	containsNumbers($errors, $_POST, 'fname');
	
	
	//server validation of email field
	isEmpty($errors, $_POST, 'email');
	validateEmail($errors, $_POST, 'email');
	
	
	//server validation of username field
	isEmpty($errors, $_POST, 'username');
	
	//Server validation of passwords field
	isEmpty($errors, $_POST, 'password');
	validatePassword($errors,$_POST,'password','confpassword');
	
	//Server validation of postcode field
	containsSpecChars($errors, $_POST, 'postcode');
	containsLetters($errors, $_POST, 'postcode');
	
	//Server validation of Date of Birth field
	validateDate($errors, $_POST, 'dob');
	containsLetters($errors, $_POST, 'dob');
	
	//Server validation of Username field
	validateUsername($errors, $_POST, 'username');
	
	if ($errors) {
		// redisplay the form
		include 'Registration.php';
	} else {
		if(uniqueUsername($_POST['username'])){
			makeNewUser($_POST['fname'],$_POST['email'], $_POST['username'],$_POST['confpassword'],$_POST['postcode'],$_POST['dob']);
			header("Location:Login.php");
  			exit();
		} else {
			$errors['username'] = "Username is taken.";
			include 'Registration.php';
		}
		//echo 'form submitted successfully with no errors';
	}
} else {
	include 'Registration.php';
}

//returns true if the the username is already registered in the database
function uniqueUsername($username){
	require('PHP/connections.php');
	$stmt = $pdo->prepare(
	'SELECT *
	FROM members
	WHERE Username = :username');
	$stmt->execute(array("username" => $username));
	
	if($stmt->rowCount() > 0){
		return false;
	} else {
		return true;
	}
}

//runs the query that inserts the new member into the members table
function makeNewUser($fName,$email,$username,$password,$postCode,$DOB){
	//echo $fName." | ". $email." | ".$username." | ".$password . " | " . $postCode. " | " . $DOB;
	
	//if the non-required fields are empty set them as null. so that no sql errors pop up
	if(trim($postCode) == ''){
		$postCode = NULL;
	}
	
	if(trim($DOB) == ''){
		$DOB = NULL;
	}
	
	require('PHP/connections.php');
	try {
		$result = $pdo->query(
		'SELECT *
		FROM members');
		
		$salt = uniqid(); 
		
		//prepare the query
		$stmt = $pdo->prepare('INSERT INTO members (Username, Salt, Password, Email, Fullname, Postcode, Dateofbirth)
	VALUES (:username, :salt, SHA2(CONCAT(:password, salt),0), :email, :fullName,  :postcode, :dob) ');
		
		// run the query
		$stmt->execute(array("username" => $username, "password" => $password, "salt" => $salt, "email" => $email, "fullName" => $fName, "postcode" => $postCode , "dob" => $DOB));
		
	} catch (PDOException $e) {
	echo $e->getMessage();
	}
	
}
?>