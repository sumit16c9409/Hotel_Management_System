<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Location</title>
	<script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    
    <link rel="stylesheet" href="css/pricing.css">
	<link rel="stylesheet" href="css/metisMenu.min.css" />
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.min.js"></script>
	
<style>
	* {
		margin: 0;
		padding: 0;
	}
	#map {
		height: 500px;
		width: 100%;
	}
</style>
</head>
<body>
<!-- Navigation -->
    <nav class="navbar navbar-light bg-light static-top">
      <div class="container">
        <a class="navbar-brand" href="/webApp/index.jsp">Hotel Booking</a>
        <ul class="nav navbar-top-links navbar-right">
			<li class="dropdown">
				<ul class="dropdown-menu dropdown-user">
					<li class="divider"></li>
					<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
					</li>
				</ul>
			</li>
		</ul>
      </div>
    </nav>
    <div id="wrapper">
		<div id="page-wrapper" style="margin:0 0 0 0px; !important">
			<div class="container" style="margin-top: 50px;">
			<div id="map"></div>
		</div>
		</div>
	</div>
<script>
	var latitude = ${latitude};
	var longitude = ${longitude};
	
	function initMap(){
		var location = {lat: latitude, lng: longitude};
		var map = new google.maps.Map(document.getElementById("map"), {
			zoom: 15,
			center: location
		});
		var marker = new google.maps.Marker({
			position: location,
			map: map
		});
	}
	//geocode();
	
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBD-BWW3LS4p8gGr9a4a2jbOdb7Z1rgMe0&callback=initMap"></script>
</body>
</html>
