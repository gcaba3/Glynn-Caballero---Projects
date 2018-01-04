// JavaScript Document
function turnOffError(errorId){
	document.getElementById(errorId).style.visibility = 'hidden';
}

//validates the new review
function validateReview(form){
	if (form.review.value.trim() === ""){
		document.getElementById('reviewerror').innerHTML = "Please fill in the review.";
		document.getElementById('reviewerror').style.visibility = "visible";
		return false;	
	} else {
		return true;
	}
}