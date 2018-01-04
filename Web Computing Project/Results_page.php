<!doctype html>
<link rel="stylesheet" type="text/css" href="css/sitestyling.css"/>
<link rel="stylesheet" type="text/css" href="css/resultsstyling.css"/>
<html>
<head>
<meta charset="utf-8">
<title>Results Page</title>
<script type="text/javascript" src="scripts/resultsMap.js"></script>
<script type="text/javascript" src="scripts/functions.js"></script>
<script type="text/javascript" src="scripts/resultsFunctions.js"></script>
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
	<div class="body" >
    	<div class="mainResultsPageSection" >
        	<div class="miniSearchSection" >
            
            <form id="newSearchForm" onsubmit="return validateNewSearch(this)" action="Results_pageProcess.php" method="post">
            <div class="miniSearchleftColumn"> 
                <?php
				require 'PHP/functions.php';
				input_text($errors, 'name', 'Enter Name:', '7th Brigade Park',0);
				input_number($errors,'rating','Choose minimum rating:',0,5,1,0);
				?>
            </div>
            
            <div class="miniSearchrightColumn" style="padding:0px;margin:0px;">
                <?php
				require 'PHP/connections.php';
				$options = suburbArray();
				input_select('suburb', 'Choose Suburb',$options);  
				?>
                <br><input type="submit" value="New Search" > <br>
                </div>
                </form>
            </div>
            
            <div class="searchedForSection" style="text-decoration:">
            <strong> Showing results for: </strong> <br>
            <?php
			include_once('PHP/search_parameters.php');
			?>
            </div>
            
            <div class="mapSection" id="mapSection">
                <div class="map" id='map'>
                    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPFlvBSrIsnn_jjhnUTYa6ZnJlkLI_m7o&callback=initMap"></script>
                </div>	
        	</div>
            
            
            <div class="resultsSection">
            	<table class="resultsTable">
                <?php
				
				if(isset($_GET['name']) && isset($_GET['suburb']) && isset($_GET['rating']) && isset($_GET['page'])){
					//Displays the results for a specific park search 
					include_once('PHP/searchedForResults.php');
					
				} else if(isset($_GET['distance']) && isset($_GET['page']) && isset($_GET['lat']) && isset($_GET['lon'])){
					//Displays the results for the users current location
					include_once('PHP/locationResults.php');
					
				}else {
					echo '</table>';
					echo "One of the required field isn't set. Please click new search with blank name, 'Any' suburb and 0 minimum rating.";
				}
				?>
            </div>
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
