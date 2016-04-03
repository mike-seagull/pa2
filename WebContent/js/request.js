/**
 * 
 */
var create = document.getElementById("create");
var view = document.getElementById("view");
var viewAll = document.getElementById("viewAll");
var remove = document.getElementById("remove");
var title = document.getElementById("title");
var request_form = document.getElementById("request_form");
var lab3_id = document.getElementById("lab3_id");
var lab3_message = document.getElementById("lab3_message");
var message = document.getElementById("message");
var data_container = document.getElementById("data_container");

$('#createCustomer').on('submit', function () {
	var firstname = document.getElementsByName("firstname")[0];
	var lastname = document.getElementsByName("lastname")[0];
	var phnum = document.getElementsByName("phnum")[0];
	var biiladdr = document.getElementsByName("biiladdr")[0];
	var billcity = document.getElementsByName("billcity")[0];
	var billstate = document.getElementsByName("billstate")[0];
	var billzip = document.getElementsByName("billzip")[0];
	
	var customer = {
			"first": firstname.value, 
			"last": lastname.value,
			"phonenumber": phnum.value,
			"address": biiladdr.value,
			"city": billcity.value,
			"state": billstate.value,
			"zip": billzip.value
			};
	
	$.post( "http://localhost:8080/PA2/Customer", customer, function( data ) {
		  console.log(data.customerId);
		  var alert = document.getElementsById("alert");
		  alert.innerHTML = data.customerId;
		  alert.setAttribute("class", "alert alert-success");
	});
	
});

$('#reserveRoom').on('submit', function () {
	var customerId = document.getElementsByName("custId")[0];
	var roomNumber = document.getElementsByName("roomNum")[0];
	
	var payload = {	
					"customerId": customerId.value, 
					"roomNumber": roomNumber.value
					};

	$.post( "http://localhost:8080/PA2/Room", payload, function( data ) {
		  console.log(data.success);
		  var alert = document.getElementsById("alert");
		  if (data.success) {
			alert.innerHTML = "Reservation Succesful";
		  	alert.setAttribute("class", "alert alert-success");
		  } else {
     		alert.innerHTML = "Reservation Unsuccessful";
		  	alert.setAttribute("class", "alert alert-success");
		  }
	});
	
});

$('#customerById').on('submit', function () {
	var customerId = document.getElementsByName("customerId")[0];
	
	var payload = {"customerId": customerId, "roomNumber": roomNumber};

	$.get( "http://localhost:8080/PA2/Customers/"customerId.value, function( data ) {
		  console.log(data.success);
		  var alert = document.getElementsById("alert");
		  if (data.success) {
			alert.innerHTML = "Reservation Succesful";
		  	alert.setAttribute("class", "alert alert-success");
		  } else {
     		alert.innerHTML = "Reservation Unsuccessful";
		  	alert.setAttribute("class", "alert alert-success");
		  }
	});
	
});