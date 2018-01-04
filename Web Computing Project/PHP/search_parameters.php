<?php
//displays the users searched for parameters (displays what they searched for)
if(isset($_GET['name']) && isset($_GET['suburb']) && isset($_GET['rating'])){
	echo '<b> Name: </b> <a id="searchedName">'.$_GET['name'].'</a>';
	echo '<b> Suburb: </b> <a id="searchedSuburb">'.$_GET['suburb'].'</a>';
	echo '<b> Rating: </b> <a id="searchedRating">'.$_GET['rating'].'</a>'; 
} elseif (isset($_GET['distance'])){
	echo '<b> Distance: </b> <a id="searchedName">'.$_GET['distance'].'km</a>';
}
?>