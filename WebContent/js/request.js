/**
 * 
 */
var create = document.getElementById("createCustomerButton");
var reserve = document.getElementById("reserveRoomButton");
var customerById = document.getElementById("customerByIdButton");
var customerByName = document.getElementById("customerByNameButton");
var currentCustomers = document.getElementById("currentCustomersButton");
var transactions = document.getElementById("transactionsButton");
var vacancies = document.getElementById("vacanciesButton");
var reservations = document.getElementById("reservationsButton");
/*
{ 	header: ["", ""]
	data: [ 
	        {
	        	colomn: ""
	        	value: ""
	        }
	      ]
}
*/
function createTable(data) {
	var table = "<table>" +
				"<tr>";
				for (var i=0;i<data.headers.length();i++) {
					 table +=  "<th>" + data.headers[i] + "</th>";
				}
			table += "</tr><tr>";
				for (var j=0;j<data.data.length;j++) {
					table += "<td>" + data.data[j].value + "</td>";
				}
			table += "</tr> </table>";
}

create.onclick = function () {
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
		  var alert = document.getElementById("alert");
		  alert.innerHTML = "Success! Customer Id =" + data.customerId;
		  alert.setAttribute("class", "alert alert-success");
		  $("#alert").fadeTo(2000, 500).slideUp(500, function(){
    			$("#success-alert").alert('close');
		  });
	});
	$('a[href="#home"]').tab('show');
};

reserve.onclick = function () {
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
	$('a[href="#menu1"]').tab('show');

};

customerById.onclick = function () {
	var customerId = document.getElementsByName("customerId")[0];
	
	var payload = {"customerId": customerId};

	$.get( "http://localhost:8080/PA2/Customer/"+customerId.value, function( data ) {
		  console.log(data.success);
		  var alert = document.getElementById("alert");
		  if (data.success) {
			alert.innerHTML = "Reservation Succesful";
		  	alert.setAttribute("class", "alert alert-success");
		  } else {
     		alert.innerHTML = "Reservation Unsuccessful";
		  	alert.setAttribute("class", "alert alert-success");
		  }
	});
	$('a[href="#menu2"]').tab('show');
};
customerByName.onclick = function () {
	var name = document.getElementById("customer_name");
	
	$.get( "http://localhost:8080/PA2/Customers/"+name.value, function( data ) {
		  console.log(data.success);
		  var alert = document.getElementById("alert");
		  if (data.success) {
			alert.innerHTML = "Reservation Succesful";
		  	alert.setAttribute("class", "alert alert-success");
		  } else {
     		alert.innerHTML = "Reservation Unsuccessful";
		  	alert.setAttribute("class", "alert alert-success");
		  }
			var table = "<table>" +
			"<tr>";
			table +=  "<th>Id</th><th>First Name</th><th>Last Name</th><th>Phone Number</th>";
			table += "</tr>";
			for (var j=0;j<data.length;j++) {
				table += "<tr><td>" + data[j].id + "</td>";
				table += "<td>" + data[j].first_name + "</td>";
				table += "<td>" + data[j].last_name + "</td>";
				table += "<td>" + data[j].phonenumber + "</td></tr> ";
			}
			table += "</table>";
			document.getElementById("table_data").innerHTML = table;
	});
	$('a[href="#menu2"]').tab('show');
};
currentCustomers.onclick = function() {
	console.log("in currentCustomers");
	$.get( "http://localhost:8080/PA2/Customers/", function( data ) {
 		console.log(data.success);
 		var alert = document.getElementsById("alert");
 		if (data.success) {
			alert.innerHTML = "Successful";
  			alert.setAttribute("class", "alert alert-success");
 		} else {
    		alert.innerHTML = "Unsuccessful";
  			alert.setAttribute("class", "alert alert-success");
 		}
	});
	$('a[href="#menu4"]').tab('show');
};
transactions.onclick = function() {
	$.get( "http://localhost:8080/PA2/Transaction/", function( data ) {
 		console.log(data.success);
 		var alert = document.getElementById("alert");
 		if (data.success) {
			alert.innerHTML = "Successful";
  			alert.setAttribute("class", "alert alert-success");
 		} else {
    		alert.innerHTML = "Unsuccessful";
  			alert.setAttribute("class", "alert alert-success");
 		}
	});
	$('a[href="#menu5"]').tab('show');
};
vacancies.onclick = function() {
	$.get( "http://localhost:8080/PA2/Room/true", function( data ) {
 		console.log(data.success);
 		var alert = document.getElementById("alert");
 		if (data.success) {
			alert.innerHTML = "Successful";
  			alert.setAttribute("class", "alert alert-success");
 		} else {
    		alert.innerHTML = "Unsuccessful";
  			alert.setAttribute("class", "alert alert-success");
 		}
	});
	$('a[href="#menu6"]').tab('show');
};
reservations.onclick = function() {
	$.get( "http://localhost:8080/PA2/Room/false", function( data ) {
 		console.log(data.success);
 		var alert = document.getElementById("alert");
 		if (data.success) {
			alert.innerHTML = "Successful";
  			alert.setAttribute("class", "alert alert-success");
 		} else {
    		alert.innerHTML = "Unsuccessful";
  			alert.setAttribute("class", "alert alert-success");
 		}
	});
	$('a[href="#menu7"]').tab('show');
};
function successAlert(message) {
	  var alert = document.getElementById("alert");
	  alert.innerHTML = "Success! " + message;
	  alert.setAttribute("class", "alert alert-success");
	  $("#alert").fadeTo(2000, 500).slideUp(500, function(){
			$("#alert").alert('close');
	  });
};
function failureAlert(message) {
	  var alert = document.getElementById("alert");
	  alert.innerHTML = "Error! " + message;
	  alert.setAttribute("class", "alert alert-alert-warning");
	  $("#alert").fadeTo(2000, 500).slideUp(500, function(){
			$("#alert").alert('close');
	  });
};