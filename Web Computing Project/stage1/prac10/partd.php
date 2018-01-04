<!doctype html>
<?php

require_once('connections.php');
try {
$result = $pdo->query(
'SELECT PuppyName, BreedName, Description, Price, Picture, ID
FROM animals, breeds 
WHERE animals.BreedID = breeds.Breed AND Sold = 0');
} catch (PDOException $e) {
echo $e->getMessage();
}
?>
<html>
<head>
<meta charset="utf-8">
<title>Part D</title>
</head>

<body>
<table border="solid;">
<tr>
<th> Name </th>
<th> Breed </th>
<th> Description </th>
<th> Price </th>
<th> Picture </th>
<th> Buy </th>
</tr>
<?php
foreach ($result as $puppy) {
	echo "<tr>";
	for ($i = 0; $i < 3; $i++){
		echo "<td>";
		echo $puppy[$i];
		echo "</td>";
	}
	echo "<td>";
	echo "$" . $puppy['Price'];
	echo "</td>";
	
	echo "<td>";
	echo '<img width="100px" src="images/' . $puppy['Picture'] . '">';
	echo "</td>";
	
	echo "<td>";
	echo '<a href="buy.php?puppy='. $puppy['ID'] . '">Buy me</a>';
	echo "</td>";
	
	echo "</tr>";
}
?>

</table>
<a href="partf.php"> Make new Entry</a>
<?php
/*
foreach ($result as $puppy) {
	for ($i = 0; $i < 5; $i++){
		echo $puppy[$i];
		echo "<br>";
	}
	echo "<br>";
	echo "<br>";
}
*/
?>
</body>
</html>

