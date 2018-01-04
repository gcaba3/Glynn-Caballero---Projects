<?php
session_destroy(); // destroys the session just incase someone's logged in
session_start(); // starts a new session
$_SESSION['isUser'] = TRUE;
$_SESSION['userName'] = $_POST['username'];
$_SESSION['userID'] = $userID[0][0];
$_SESSION['userFName'] = $userID[0][Fullname];
header('Location: index.php');
?>