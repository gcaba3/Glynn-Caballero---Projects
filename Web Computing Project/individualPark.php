<!doctype html>
<link rel="stylesheet" type="text/css" href="css/sitestyling.css"/>
<link rel="stylesheet" type="text/css" href="css/samplepagestylings.css"/>
<html>
<head>
<meta charset="utf-8">
<title>Individual Park</title>
<script type="text/javascript" src="scripts/map.js"></script>
<script type="text/javascript" src="scripts/newReviewValidation.js"></script>
</head>

<body>
<div class="header" style="z-index:99">
	<div class="logo">
    <img src="images/logo.png" alt="something" height="100%" width="100%">
    </div>

 	<div class="menu" style="color:white; text-align:right;">
    <?php include('menu.php'); ?>
    </div>
</div>

<div id="container" class="container">
	<div class="body">
    	<div class="mainSection">
        <div class="mapSection" id="mapSection">
        	<div class="map" id='map'>
				<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPFlvBSrIsnn_jjhnUTYa6ZnJlkLI_m7o&callback=initMap"></script>
        	</div>	
        </div>
        <div class="descriptionSection" itemscope itemtype="http://schema.org/Park">
        <?php
		require 'PHP/connections.php';
		require 'PHP/functions.php';
		$parkID = $_GET['id'];
		
		//display the descriptions of the park epending on the id of the park
		try {
			$result = $pdo->query(
			"SELECT *
			FROM parks
			WHERE Idpark = $parkID");
			
			echo "<b > Description </b>";
			foreach($result as $park){
				echo '<p id="parkName" itemprop="Park" itemscope itemtype="http://schema.org/Park">'. $park['Name'] .'</p>';
				echo '<p id="parkAddress" itemprop="address" itemscope itemtype="http://schema.org/address" >'. $park['Street'] . ', '. $park['Suburb'] . '</p>';
				echo '<p> Rating: '.getAverageRating($park['Idpark']).'</p>';
				echo '<p id="lat" name="lat" style=" display:none; visibility:hidden;" itemprop="latitude" itemscope itemtype="http://schema.org/latitude">'.$park['Latitude'].'</p>';
				echo '<p id="lon" name="lon" style="display:none ; visibility:hidden;" itemprop="longtitude" itemscope itemtype="http://schema.org/longtitude">'.$park['Longitude'].'</p>';
				
			}
		} catch (PDOException $e) {
			echo $e->getMessage();
		}
		
		
		?>
        
        
        	
        </div>
        <div class="myReviewSection" style="height:100px;">
        <form action="newReview.php" method="post" onSubmit="return validateReview(this)">
        	<fieldset>
        	<label style="display:inline-block"> Rating: </label>
            <input required type="number" id="rating" name="rating" style="width:30px;" max="5" min="0" step="1" value="0" >
            <span name='reviewerror' id='reviewerror' class="error"></span>
			<?php
			//if the user is a registered member then show the submit button and set the hidden input fields. else show a helful message. 
			if(isset($_SESSION['isUser']) &&  ($_SESSION['isUser'] == TRUE)){
				echo '<input type="submit" style="float:right;">';
				echo '<input type="hidden" name="parkID" value="'.$parkID.'">';
				echo '<input type="hidden" name="date" value="'.date("Y-m-d H:i:s").'">';
			} else {
				echo '<p style="float:right; padding:0px;margin:0px;">Please <a href="Login.php" style="text-decoration:none">login</a> to review</p>';
			}
			?>
            </fieldset>
            <textarea name="review" id="review" placeholder="Write review here..." onKeyPress="turnOffError('reviewerror')" required></textarea>
            
        </form>
        </div>
        <div class="reviewSection">
        <table>
        <?php
		
		require('PHP/connections.php');
		$result = $pdo->query(
		'SELECT *
		FROM reviews
		WHERE Idpark ="'.$parkID.'"
		ORDER BY Dateposted desc');
		
		// if there are no reviews then display a message indicating theres no reviews. else display all the reviews
		if($result->rowCount() == 0){
			echo "No reviews for this park";
		} else {
			foreach($result as $review){
			$member = $pdo->query(
			'SELECT *
			FROM members
			WHERE Idmember ="'.$review['Idmember'].'"');
			foreach($member as $row){
				$memberName = $row['Fullname']; // retrieve the users full name depending on theire member's id
			}
			//display the review
			echo '<tr>';
			echo '<td>';
			echo '<div class="reviewHeader">';
			echo "<b> Posted by: </b>";
			echo '<a name="postedby" >'.$memberName.'</a>';
			echo '<a name="date"> ('.$review['Dateposted'].') </a>';
			echo "<b> Rating: </b>";
			echo '<a name="rating" > '.$review['Rating'].' </a>';
			echo '</div>';
			echo '<div class="reviewBody">';
			echo '<a >'.$review['Review'].'</a>';
			echo '</div>';
			echo '</td>';
			echo '</tr>';
				
			}

		}
		
		?>
        
        </table>
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
