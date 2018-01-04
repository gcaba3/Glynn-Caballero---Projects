<!doctype html>
<link rel="stylesheet" type="text/css" href="css/registrationstyling.css"/>
<link rel="stylesheet" type="text/css" href="css/sitestyling.css"/>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
<script type="text/javascript" src="scripts/properRegistrationValidation.js"></script>
</head>

<body>
<div class="header">
	<div class="logo">
    <img src="images/logo.png" alt="something" height="100%" width="100%">
    </div>

    <div class="menu" style="color:white; text-align:right;">
    <?php require_once('menu.php') ?>
    </div>
</div>
<div id="container" class="container">
	<div class="body">
    	<div class="innerbody">
        
        	<h1 style="margin:0px; padding:0px;"> Registration </h1>
            
            <form action="RegistrationProcess.php" method="post" onSubmit=" return validateAll(this) ">
            
            <?php 
			require 'PHP/functions.php';
			input_text($errors,'fname','Full Name:', 'Jane Doe', 1);
			input_email($errors,'email','Email Address:','example@email.com',1);
			input_text($errors,'username', 'Username:', 'JaneDoe17',1);
			input_password($errors,'password','Password:',1);
			input_number($errors,'postcode','Postcode:',0,9999,1,0); 
			//input_text($errors,'postcode','Postcode:', '9999', 1); // change back to this one, to test for js and php validation
			input_text($errors,'dob','Date of Birth:','',0);
			?>
            
            <br><input type="submit" value="Confirm">
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
