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
<title>Part F</title>
</head>

<body>
<form action="process.php" method="post">
<label for="name"> Name: </label> <input type="text" id="name" name="name"> <br> <br>
<label for="breed"> Breed: </label> <select id="breed" name="breed"> 
<?php
try {
$breedresult = $pdo->query(
'SELECT Breed, BreedName
FROM breeds');
} catch (PDOException $e) {
echo $e->getMessage();
}
foreach ($breedresult as $breed){
	echo '<option value="',$breed['Breed'],'">',$breed['BreedName'],'</option>';

}
?>
</select> <br><br>
<label for="description"> Description: </label> <textarea id="description" name="description"></textarea> <br> <br>
<label for="price"> Price: </label> <input name="price" type="number" required="required" id="price" min="0"><br> 
<br>
<input type="submit">
</form>
<a href="partf.php"> Make new Entry</a>
<a> | </a>
<a href="partd.php"> View Animals </a>
</body>
</html>