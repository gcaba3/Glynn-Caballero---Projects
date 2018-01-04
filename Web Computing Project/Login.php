<!doctype html>
<?php 
session_start(); 
session_destroy(); //Destroys the session just in case someone else is logged in
?>
<link rel="stylesheet" type="text/css" href="css/sitestyling.css"/>
<link rel="stylesheet" type="text/css" href="css/registrationstyling.css"/>
<html>
<head>
<meta charset="utf-8">
<title>Login Page</title>
</head>

<body>
<div class="header">
	<div class="logo">
    <img src="images/logo.png" alt="something" height="100%" width="100%">
    </div>

    <div class="menu" style="color:white; text-align:right;">
    <?php include_once('menu.php'); ?>
    </div>
</div>
<div id="container" class="container">
	<div class="body">
    	<div class="innerbody" style="background-color:white;">
        <h1 style="margin:0px; padding:0px;"> Login </h1>
            <!---Main login form--->
            <form action="loginprocess.php" method="post">
            <?php
			require 'PHP/functions.php';
			input_text($errors, 'username', 'Enter Username:', 'JaneDOE27',1);
			input_passwordNoConf($errors, 'password', 'Enter Password:', 1);
			?>
            <br><input type="submit" value="Login">
            </form>
    	</div>
    </div>
</div>
<div id="footer" class="footer">
<div class="footerColumn">  
 <br>
<a href="#"> FAQ </a> <br>
<a href="#"> About us </a> <br>
<a href="#"> Contact us </a>
<p style="color:white;"> Created by Glynn Caballero, n9511555 </p>
</div>
</div>
</body>
</html>
