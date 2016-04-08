/**
 * 
 */
var table_data = document.getElementById("table_data");
var home = document.getElementById("home_anc");
var menu1 = document.getElementById("menu1_anc");
var menu2 = document.getElementById("menu2_anc");
var menu3 = document.getElementById("menu3_anc");
var menu4 = document.getElementById("menu4_anc");
var menu5 = document.getElementById("menu5_anc");
var menu6 = document.getElementById("menu6_anc");
var menu7 = document.getElementById("menu7_anc");

var create = document.getElementById("createCustomerButton");
var reserve = document.getElementById("reserveRoomButton");
var customerById = document.getElementById("customerByIdButton");
var customerByName = document.getElementById("customerByNameButton");
var currentCustomers = document.getElementById("currentCustomersButton");
var transactions = document.getElementById("transactionsButton");
var vacancies = document.getElementById("vacanciesButton");
var reservations = document.getElementById("reservationsButton");

function createTable(data) {

	if (data[0]) {
	var table = "<table>" +
				"<tr>";
				var headers = Object.keys(data[0]);
				for (var i=0;i<headers.length;i++) {
					 table +=  "<th>" + headers[i] + "</th>";
				}
			table += "</tr>";
				for (var j=0;j<data.length;j++) {
					table+="<tr>";
					
					for (var key in data[j]) {
						table += "<td>" + data[j][key] + "</td>";
					}
					table+="</tr>";
				}
			table += "</table>";
			console.log(table);
			table_data.innerHTML = table;
	}else {
		failureAlert("List is empty");
	}
};
function clearTable() {
	table_data.innerHTML = "";
};

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
		  successAlert("Customer Id =" + data.customerId)
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
 		createTable(data);
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
	$('a[href="#menu3"]').tab('show');
};
currentCustomers.onclick = function() {
	console.log("in currentCustomers");
	$.get( "http://localhost:8080/PA2/Customers/", function( data ) {
 		createTable(data);
	});
	$('a[href="#menu4"]').tab('show');
};
transactions.onclick = function() {
	$.get( "http://localhost:8080/PA2/Transaction/", function( data ) {
 		createTable(data);
	});
	$('a[href="#menu5"]').tab('show');
};
vacancies.onclick = function() {
	$.get( "http://localhost:8080/PA2/Room/true", function( data ) {
 		createTable(data);
	});
	$('a[href="#menu6"]').tab('show');
};
reservations.onclick = function() {
	$.get( "http://localhost:8080/PA2/Room/false", function( data ) {
 		createTable(data);
	});
	$('a[href="#menu7"]').tab('show');
};
function successAlert(message) {
	  var alert = document.getElementById("alert");
	  alert.innerHTML = "Success! " + message;
	  alert.setAttribute("class", "alert alert-success");
	  $("#alert").fadeTo(7500, 500).slideUp(500, function(){
			$("#alert").alert('close');
	  });
};
function failureAlert(message) {
	  var alert = document.getElementById("alert");
	  alert.innerHTML = "Error! " + message;
	  alert.setAttribute("class", "alert alert-danger");
	  $("#alert").fadeTo(7500, 500).slideUp(500, function(){
			$("#alert").alert('close');
	  });
};

