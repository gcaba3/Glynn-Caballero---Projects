<!doctype html>
<?php 
require_once('connections.php');
?>
<html>
<head>
<meta charset="utf-8">
<title>Process</title>
</head>

<body>
<h1> Added new entry </h1>
<p> Information:</p>
<?php
if (isset($_POST['name'])) {
	$name = $_POST['name'];
	echo 'Name: ' . $_POST['name'];
	echo '<br><br>';
}

if (isset($_POST['breed'])) {
	$breed = $_POST['breed'];
	echo 'Breed: ' . $_POST['breed'];
	echo '<br><br>';
}

if (isset($_POST['description'])) {
	$description = $_POST['description'];
	echo 'Description: ' . $_POST['description'];
	echo '<br><br>';
}

if (isset($_POST['price'])) {
	$price = $_POST['price'];
	echo 'Price: ' . $_POST['price'];
	echo '<br><br>';
}

$stmt = $pdo->prepare('INSERT INTO animals (PuppyName, BreedID, Description, Price, Picture, Sold)
VALUES (:name, :breed, :description, :price, :picture, :sale) ');
$stmt->execute(array("name" => $name, "breed" => $breed, "description" => $description, "price" => $price, "picture" => $name . ".jpg" , "sale" => 0));

?>
<a href="partf.php"> Make new Entry</a>
<a> | </a>
<a href="partd.php"> View Animals </a>
</body>
</html>