<!doctype html>
<link rel="stylesheet" type="text/css" href="css/sitestyling.css"/>
<link rel="stylesheet" type="text/css" href="css/searchstyling.css"/>
<html>
<head>
<meta charset="utf-8">
<title>Search Page</title>
<script type="text/javascript" src="scripts/geolocation.js"></script>
<script type="text/javascript" src="scripts/searchValidations.js"></script>
<script type="text/javascript" src="scripts/functions.js"></script>
</head>

<body onLoad="getLocation()">
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
    	<div class="innerbody">
        	<img src="images/logo.png" alt="something" height="100px" width="100%">
            
            <!---Main search form--->
            <form action="indexprocess.php" onSubmit="return validateSearch(this)" method="post">
            <a> Search for a specific park </a><br>
            
            <?php 
			//Main input fields
			require 'PHP/functions.php';
			input_text($errors, 'name', 'Park name', '7th Brigade Park',0);
			
			$suburbs = suburbArray();
			input_select('suburb', 'Choose Suburb',$suburbs); 
			
			input_number($errors,'rating','Choose minimum rating:',0,5,1,0);
			
			//hidden page number to be used in results page
			echo "<input type=\"hidden\" name=\"page\" id=\"page\" value=\"0\">"

			?>
            <br><input type="submit" value="Search">
            </form>
            
            <!--- Search form for the geo-location --->
            <form onSubmit=" return validateLocation(this)" action="geolocationProcess.php" method="post">
            <br>
            Or, Find parks from your current location. 
            <?php
			input_number($errors,'distance','Within (kms):',0,99999,1,1);
			echo "<input type=\"hidden\" name=\"page\" id=\"page\" value=\"0\">";
			//hidden lat and lon fields to be changed by gelocation.js
			echo "<input type=\"hidden\" name=\"lat\" id=\"lat\" value=\"\">";
			echo "<input type=\"hidden\" name=\"lon\" id=\"lon\" value=\"\">";
			?>
           
            
            <br>
            <span class="error" name="locationerror" id="locationerror"></span>
            <br>
            <?php 
			//Stops the p
			if(isset($errors['locationerror'])){
				echo "";
			} else {
				echo '<input type="submit" value="Search" onClick="getLocation();">';
			}
			?>
            
            
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
