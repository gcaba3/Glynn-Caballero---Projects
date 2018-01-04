function turnOffError(errorId){
	document.getElementById(errorId).style.visibility = 'hidden';
}

function checkForNumbers(str){
	var regex = /[0-9]/;
	
	if(regex.test(str)){
		return true;	
	} else {
		return false;	
	}
}

function checkForLetters(str){
	var regex = /[A-Za-z]/;
	
	if(regex.test(str)){
		return true;	
	} else {
		return false;	
	}
}

function checkforSpecialChar(str){
	var regex = /[{}$&+,:;=?@#|~'<>^*%!]/;
	
	if(regex.test(str)){
		return true;	
	} else {
		return false;	
	}
}