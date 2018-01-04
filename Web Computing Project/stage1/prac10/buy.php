<!doctype html>
<?php
require_once('connections.php');
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
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
<title>Pard D</title>
</head>

<body>
<h1> Animal has been sold </h1>
<p> Information: </p>
<?php

$puppyID = $_GET['puppy'];
echo "Puppy ID: " . $puppyID;
$stmt = $pdo->prepare('UPDATE animals SET Sold = 1 WHERE ID = :Id');
$stmt->bindValue(':Id', $puppyID);
$stmt->execute();
?>
<br><br>
<a href="partf.php"> Make new Entry</a>
<a> | </a>
<a href="partd.php"> View Animals </a>
</body>
</html>