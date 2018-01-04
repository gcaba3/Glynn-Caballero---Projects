<?php
//sets the necessary variables
$searchedNamed = $_GET['name'];
$searchedSuburb = $_GET['suburb'];
$searchedRating = $_GET['rating'];
$pageNumber = $_GET['page'];

//initilises the search string query
$searchStr = 
'SELECT *
FROM parks';

//if the user left the name field empty (i.e. any). Then dont set the name as a where for the sql query
if($searchedNamed != 'Any'){
	$searchStr = $searchStr . " WHERE Name = '". $searchedNamed ."'"; 
} 

//checks if theres already a where statement in the query. if there is add the 'and' before the where for the suburb parameter
if($searchedSuburb != 'Any'){
	if($searchedNamed != 'Any'){
		$searchStr = $searchStr . "AND Suburb ='". $searchedSuburb."'";
	} else {
		$searchStr = $searchStr . " WHERE Suburb ='". $searchedSuburb."'";
	} 
}

//echo $searchStr;
//an array for the parks to be used later
$parks = array();
$totalResults  = 0;
$resultsPerPage = 25; // how many results can be shown per page

try {
	$result = $pdo->query($searchStr);
	//store the result in the parks array, if the parks' average rating is >= to the search rating chosen by the user and increase total results by 1
	foreach($result as $park){
		if( getAverageRating($park['Idpark']) >= $searchedRating){
			array_push($parks,$park);
			$totalResults += 1;
		}
	
	}
	//sets the off set and limit for the array, used later
	$offset = $resultsPerPage * $pageNumber; 
	$limit = $offset + $resultsPerPage;
	
	//echo $searchStr;
	
	//since the limit just adds a flat 25, if the total results is less than 25 or the last page is more the limit then it wouldnt make sense
	if($limit >= $totalResults){
		$limit = $totalResults;
	}
	
	//Display the result and current results
	echo 'Results found: ' . $totalResults;
	echo '<br>Showing '. $offset . '-'. $limit.' results.';
} catch (PDOException $e) {
	echo $e->getMessage();
}

//Displays the parks in the array depending on the page
for($i = $offset; $i < $limit; $i++){
	echo '<tr class="parkResult">';
	echo '<td>'; 
	echo '<a name="park" href= individualPark.php?id='. $parks[$i]['Idpark'].'>'. $parks[$i]['Name'] .'</a>'; 
	echo '<p name="parkStreet"> Street: '.$parks[$i]['Street'] . '</p>';
	echo '<p name="parkSuburb"> Suburb: '. $parks[$i]['Suburb'] . '</p>';
	echo '<p> Rating: '.getAverageRating($parks[$i]['Idpark']).'</p>';
	echo '<p class="parkLat">'.$parks[$i]['Latitude'].'</p>';
	echo '<p class="parkLon">'.$parks[$i]['Longitude'].'</p>';
	echo '</td>';
	echo '</tr>';
}

echo '</table>';
//home pages there are depending on the result from the query
$totalPages = ceil($totalResults / $resultsPerPage);

//display the page numbers navigation 
for ($i = 1; $i <= $totalPages; $i++){
	$page = $i - 1;
	echo '<a href="Results_page.php?name='.$searchedNamed.'&suburb='.$searchedSuburb.'&rating='.$searchedRating.'&page='.$page.'" style="text-decoration:none;">'. $i .' </a>';
}				
?>