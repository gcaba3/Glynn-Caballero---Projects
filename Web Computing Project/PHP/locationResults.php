<?php
//sets the variables needed
require_once('calculateDistance.php');
$distance = $_GET['distance'];
$pageNumber = $_GET['page'];
$lat = $_GET['lat'];
$lon = $_GET['lon'];

//initialises the searched string
$searchStr = 'select DISTINCT * from parks';
try {
	$result = $pdo->query($searchStr);
} catch (PDOException $e) {
	echo $e->getMessage();
}

//parks array holding parks that meet the requirements
$parks = array();
$totalResults  = 0;

// Find near parks
foreach ($result as $park){
	$lat2 = $park['Latitude'];
	$lon2 = $park['Longitude'];
	$calcDistance = findDistance($lat,$lat2,$lon,$lon2); // find the distance from the user to the park
	
	//add the park if the calculated distnace from the users location and the park is less then distance search parameter of the user.
	if($calcDistance <= $distance){
		array_push($parks,$park);
		$totalResults += 1;
	}
}

$parksPerPage = 25;
$totalPages = ceil($totalResults / $parksPerPage);
$offset = $pageNumber * $parksPerPage;
$limit = $offset + $parksPerPage;

if($limit >= $totalResults){
	$limit = $totalResults;
}

//displays the parks depedning on the offset and limit. depending on the page number
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
//display the results found
echo 'Results found: ' . $totalResults;
echo '<br>Showing '. $offset . '-'. $limit.' results.';
echo '</table>';
//displays page number navigation based on how many results has found
for ($i = 1; $i <= $totalPages; $i++){
	$page = $i - 1;
	echo '<a href="Results_page.php?distance='.$distance.'&page='.$page.'&lat='.$lat.'&lon='.$lon.'" style="text-decoration:none;"">'. $i .' </a>';
}
?>