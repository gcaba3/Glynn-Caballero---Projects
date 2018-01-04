<?php
session_start();
//check if the person viewing the website is logged in, if he/she is display the registered users menu and not the regular. else display the regular.
if(isset($_SESSION['isUser']) &&  ($_SESSION['isUser'] == TRUE)){
	require('PHP/connections.php');
	$username = str_replace(' ', '&nbsp;', $_SESSION['userName']);
	$userID = str_replace(' ', '&nbsp;', $_SESSION['userFName']);
	echo '<p>Hello!&nbsp'.$username.' </p>';
	//echo '<p>Hello!&nbsp'.$userID.' </p>';
    echo '<a href="index.php">Search</a>';
    echo '<a href="PHP/Logout.php">Logout</a>';
    echo '<a href="Registration.php">Register</a>';
} else {
	echo '<a href="index.php">Search</a>';
    echo '<a href="Login.php">Login</a>';
    echo '<a href="Registration.php">Register</a>';
}

?>