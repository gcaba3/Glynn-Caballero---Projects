<?php
require_once('connections.php');
try {
$result = $pdo->query('SELECT PuppyName, BreedName, Description, Price, Picture '.
'FROM animals, breeds '.
'WHERE animals.BreedID = breeds.Breed AND Sold = 0');
} catch (PDOException $e) {
echo $e->getMessage();
}


?>