/*
      JavaScript 6th Edition
      Chapter 6
      Hands-on Project 6-3

      Author: Cody Rauber
      Date:   10/4/20

 */
 
 "use strict";
 
 var formValidity = true;
 
 function advanceSsn() {
	 var ssnFields = document.getElementsByClassName("ssn");
	 var currentField = document.activeElement;
	 if (currentField.value.length === currentField.maxLength) {
		 if (currentField === ssnFields[0]) {
			 ssnFields[1].focus();
		 }
		 if (currentField === ssnFields[1]) {
			 ssnFields[2].focus();
		 }
		 if (currentField === ssnFields[2]) {
			 document.getElementById("submitBtn").focus();
	 }
 }
}

 // Validate required fields
 function validateRequired() {
	 var inputElements = document.querySelectorAll("#contactinfo input");
	 var errorDiv = document.getElementById("errorText");
	 var elementCount = inputElements.length;
	 var requiredValidity = true;
	 var currentElement;
	 try {
		 for (var i = 0; i < elementCount; i++) {
			 // validate all input elements in fieldset
			 currentElement = inputElements[i];
			 if (currentElement.value === "") {
				 currentElement.style.background = "rgb(255,233,233)";
				 requiredValidity = false;
			 } else {
				 currentElement.style.background = "white";
			 }
		 }
		 
		 if (requiredValidity === false) {
			 throw "Please complete all fields.";
		 }
		 
		 errorDiv.style.display = "none";
		 errorDiv.innerHTML = "";
		 
	 }
	 
	 catch (msg) {
		 errorDiv.style.display = "block";
		 errorDiv.innerHTML = msg;
		 formValidity = false;
	 }
 }
 
 // create event listeners
 function createEventListeners() {
	 var form = document.getElementsByTagName("form")[0];
	 if (form.addEventListener) {
		 form.addEventListener("submit", validateForm, false);
	 } else if (form.attachEvent) {
		 form.attachEvent("onsubmit", validateForm);
	 }
	 var ssnFields = document.getElementsByClassName("ssn");
	 for (var i = 0; i < ssnFields.length; i++) {
		 if (ssnFields[i].addEventListener) {
			 ssnFields[i].addEventListener("input", advanceSsn, false);
		 } else if (ssnFields[i].attachEvent) {
			 ssnFields[i].attachEvent("oninput", advanceSsn);
		 }
	 }
 }
 
 // validate form
 function validateForm(evt) {
	 if (evt.preventDefault) {
		 evt.preventDefault(); // prevent form from submitting
	 } else {
		 evt.returnValue = false; // prevent form from submitting in IE8
	 }
	 formValidity = true; // reset value for revalidation
	 validateRequired();
	 validateNumbers();
	 if (formValidity === true) {
		 document.getElementsByTagName("form")[0].submit();
	 }
 }
 
 // run setup functions when page finished loading
 if (window.addEventListener) {
	 window.addEventListener("load", createEventListeners, false);
 } else if (window.attachEvent) {
	 window.attachEvent("onload", createEventListeners);
 }
 
 // validate numer fields for older browsers
 function validateNumbers() {
	 var numberInputs = document.querySelectorAll("#contactinfo input[type=number]");
	 var elementCount = numberInputs.length;
	 var numErrorDiv = document.getElementById("numErrorText");
	 var currentElement;
	 try {
		 for (var i = 0; i < elementCount; i++) {
			 // validate all input elements of tpe number in fieldset
			 currentElement = numberInputs[i];
			 if (isNaN(currentElement.value) || (currentElement.value === "")) {
				 currentElement.style.background = "rgb(255,233,233)";
				 numbersValiditiy = false;
			 } else {
				 currentElement.style.background = "white";
			 }
		 }
		 
		 if (numbersValiditiy === false) {
			 throw "Zip and Social Security values must be numbers.";
		 }
		 
		 numErrorDiv.style.display = "none";
		 numErrorDiv.innerHTML = "";
		 
	 }
	 catch(msg) {
		 numErrorDiv.style.display = "block";
		 numErrorDiv.innerHTML = msg;
		 formValidity = false;
	 }
 }
 
 