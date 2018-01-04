<?php

//Returns an array of all the unique suburbs in the parks table
function suburbArray(){
	require 'connections.php';
	$options = array('Any' => 'Any');
	try {
	$result = $pdo->query(
	'SELECT DISTINCT Suburb
	FROM parks
	WHERE Suburb IS NOT NULL
	ORDER BY Suburb asc');
	} catch (PDOException $e) {
	echo $e->getMessage();
	}
	foreach ($result as $park){
		$options[$park['Suburb']] = $park['Suburb'];
	}
	return $options;
}

//returns the average rating of a given park id
function getAverageRating($parkID){
	require 'connections.php';
	$searchStr = "Select * from reviews where Idpark = $parkID";
	$total = 0;
	$averageRating = 0;
	try{
		$result = $pdo->query($searchStr);
		foreach($result as $review){
			$total += $review['Rating'];
		}
		$numReviews = $result->rowCount();
		if($numReviews > 0){
			$averageRating = $total / $numReviews;
		} else {
			$averageRating = 0;
		}
		
	} catch (PDOException $e) {
		echo "Cound't find average rating";
	}
	
	return round($averageRating,2);
	
}	

//label for an input field
function label($name, $label){
	echo '<br><label for="';
	echo $name;
	echo '">';
	echo $label;
	echo '</label><br>';
}

//if the user has already filled out a form. then redisplay what they had already written.
function posted_value($name){
	if(isset($_POST[$name])){ 
		return htmlspecialchars($_POST[$name]);
	} else if (isset($_GET[$name])){
		return htmlspecialchars($_GET[$name]);
	} 
}

//the display the error if there is an error
function errorLabel($errors, $name){
	echo '<br><span id="';
	echo $name;
	echo 'error" class="error" style="visibility:visible">';

	if (isset($errors[$name])){
		echo $errors[$name];
	}
	echo '</span>';

}

function input_text(&$errors, $name, $label, $placeholder, $required) {
	label($name, $label);
	$value = posted_value($name);
	
	if ($required > 0) {
	echo "<input type=\"text\" id=\"$name\" placeholder=\"$placeholder\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\" required/>";		
	} else {
		echo "<input type=\"text\" id=\"$name\" placeholder=\"$placeholder\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\"/>";	
	}
	
	errorLabel($errors, $name);
}

function input_number(&$errors, $name, $label, $min, $max, $step, $required) {
	label($name, $label);
	
	$value = posted_value($name);
	if ($required > 0) {
		echo "<input type=\"number\" id=\"$name\" name=\"$name\" value=\"$value\" min=\"$min\" max=\"$max\" step=\"$step\" placeholder=\"$max\" required/>";
	} else {
		echo "<input type=\"number\" id=\"$name\" name=\"$name\" value=\"$value\" min=\"$min\" max=\"$max\" step=\"$step\" placeholder=\"$max\" />";
	}
	errorLabel($errors, $name);
}

function input_email(&$errors, $name, $label, $placeholder,$required){
	label($name, $label);
	$value = posted_value($name);
	if($required>0){
		echo "<input type=\"email\" id=\"$name\" name=\"$name\" value=\"$value\" placeholder=\"$placeholder\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\" required>";
	} else {
		echo "<input type=\"email\" id=\"$name\" name=\"$name\" value=\"$value\" placeholder=\"$placeholder\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\"> ";
	}
	errorLabel($errors, $name);
}

function input_password(&$errors, $name, $label,$required){
	label($name, $label);
	$value = posted_value($name);
	
	if ($required > 0) {
	echo "<input type=\"password\" id=\"$name\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\" required/>";		
	} else {
		echo "<input type=\"password\" id=\"$name\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\"/>";	
	}
	
	errorLabel($errors, $name);
	conf_password('conf'.$name, 'Confirm '.$label, $required);
}

function input_passwordNoConf(&$errors, $name, $label,$required){
	label($name, $label);
	$value = posted_value($name);
	
	if ($required > 0) {
	echo "<input type=\"password\" id=\"$name\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\" required/>";		
	} else {
		echo "<input type=\"password\" id=\"$name\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$name}error')\"/>";	
	}
	
	errorLabel($errors, $name);
}

function conf_password($name, $label,$required){
	label($name, $label);
	$value = posted_value($name);
	$passwordname = substr($name,4);
	if ($required > 0) {
	echo "<input type=\"password\" id=\"$name\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$passwordname}error')\" required/><br>";		
	} else {
		echo "<input type=\"password\" id=\"$name\" name=\"$name\" value=\"$value\" style=\"text-align:center;\" onKeyPress=\"turnOffError('{$passwordname}error')\"/><br>";	
	}
}

function input_select($name, $label, $values) {
label($name, $label);
echo "<select id=\"$name\" name=\"$name\">";
foreach ($values as $value => $display) {
$selected = ($value==posted_value($name))?'selected="selected"':'';
echo "<option $selected value=\"$value\">$display</option>";
}
echo '</select><br>';
}

function input_date($name,$label,$required){
	label($name, $label);
	if ($required > 0) {
		echo "<input type=Date name=\"$name\" required><br>";		
	} else {
		echo "<input type=Date name=\"$name\"><br>";	
	}
}

?>
