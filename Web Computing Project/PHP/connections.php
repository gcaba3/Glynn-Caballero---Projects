<?php
$dbuser = 'n9511555';
$dbpassword = 'UIOjkl123!@#';
$pdo = new PDO('mysql:host=localhost;port=3307;dbname=stage2',$dbuser, $dbpassword);
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
//$mysqli = mysqli_connect('localhost','min','qweasd123','puppies');
?>