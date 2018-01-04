<?php
function containsSpecChars(&$errors, $field_list, $field_name){
	$pattern = "/[{}~$&+,:;=?@#|\\\\\/'<>^*%!]/";
	if (preg_match($pattern, $field_list[$field_name])) {
		$errors[$field_name] = 'Invalid character(s) used';
	}
}

function containsNumbers(&$errors, $field_list, $field_name){
	$pattern = '/[0-9]/';
	if (preg_match($pattern, $field_list[$field_name])) {
		$errors[$field_name] = "Can't use numbers";
	}
}

function containsLetters(&$errors, $field_list, $field_name){
	$pattern = '/[a-zA-Z]/';
	if (preg_match($pattern, $field_list[$field_name])) {
		$errors[$field_name] = "Can't use letters";
	}
}

function isEmpty(&$errors, $field_list, $field_name){
	if (trim($field_list[$field_name]) == '') {
		$errors[$field_name] = "Please fill out field";
	}
}

function validateEmail(&$errors, $field_list, $field_name) {
	$pattern = '/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/';
	if (!preg_match($pattern, $field_list[$field_name]) && !(trim($field_list[$field_name]) == '')) {
		$errors[$field_name] = 'Invalid email format: example@email.com';
	}
}

function validatePassword(&$errors, $field_list, $password, $conf_password){
	if($field_list[$password] != $field_list[$conf_password]){
		$errors[$password] = "Password's don't match. Try again";
	}
}

function validateDate(&$errors, $field_list, $field_name){
	$pattern = '/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/';
	if(!preg_match($pattern, $field_list[$field_name]) && !(trim($field_list[$field_name]) == '')){
		$errors[$field_name] = 'Date is not valid: YYYY-mm-dd';
	}
	
}

function validateUsername(&$errors, $field_list, $field_name){
	if(filter_var($field_list[$field_name], FILTER_SANITIZE_STRIPPED) != $field_list[$field_name]){
		$errors[$field_name] = "Please don't use html tags";
	}
}

?>