<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="google-signin-client_id" content="658810886525-8eq69ms22roa4rlvajqid8ttpn5bom9c.apps.googleusercontent.com">
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    
    <link rel="stylesheet" href="css/pricing.css">
	<link rel="stylesheet" href="https://blackrockdigital.github.io/startbootstrap-sb-admin-2/vendor/metisMenu/metisMenu.min.css" />
	<link rel="stylesheet" href="https://blackrockdigital.github.io/startbootstrap-sb-admin-2/vendor/font-awesome/css/font-awesome.min.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.min.js"></script>
	<script>
		var latitude;
		var longitude;
		
		function geocode(location) {
			axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
				params:{
					address:location,
					key:'AIzaSyCP-GsydZLMAnksvjIpmQbanaWEcBOdm-Q'
				}
			})
			.then(function(response){
				latitude = response.data.results[0].geometry.location.lat;
				longitude = response.data.results[0].geometry.location.lng;
				console.log(response);
				var param = latitude+","+longitude;
				var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv');
				newTextBoxDiv.after().html('<input type="text" name="locationParam" id="locationParam" value="'+param+'" >');
				newTextBoxDiv.appendTo("#myForm");
				document.getElementById("myForm").submit();
			})
		}
		
		function onSignIn(googleUser){
			var profile=googleUser.getBasicProfile();
			var access_token=googleUser.getAuthResponse();
			$(".g-signin2").css("display","none");
			$(".test123").css("display","block");
			$("#loginDiv").css("display","block");
			$("#profile_pic").attr("src",profile.getImageUrl());
			$("#email").text(profile.getEmail());
			$("#profileName").text(profile.getName());
		}
		
		function signOut(){
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function(){
				alert("You have successfully sign out");
				$(".g-signin2").css("display","block");
				$("#loginDiv").css("display","none");
				$(".test123").css("display","none");
			})
		}
		
	</script>
<title>Hotel Booking</title>
</head>
<body>
<!-- Navigation -->
    <nav class="navbar navbar-light bg-light static-top">
      <div class="container">
        <a class="navbar-brand" href="/webApp/index.jsp">Hotel Booking</a>
        <div align="left" class="g-signin2" data-onsuccess="onSignIn"></div>
        <ul id="loginDiv" style="display: none" name="loginDiv" class="nav navbar-top-links navbar-right">
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
				<p id="profileName" class="alert alert-danger"></p>	
				</a>
				<ul class="dropdown-menu dropdown-user">
					<img class="img-circle" id="profile_pic" width="42" height="42"></img>
					<li><i class="fa fa-user fa-fw"></i><p id="email"></p> Email</li>
					<li class="divider"></li>
					<li>
						<i class="fa fa-sign-out fa-fw"></i>
						<button type="button" onclick="signOut()" class="btn btn-primary btn-xs">Logout</button>
					</li>
				</ul>
			</li>
		</ul>
      </div>
    </nav>
    <div id="wrapper">
	<div id="page-wrapper" style="margin:0 0 0 0px; !important">
	<c:if test="${not empty message}">
	<div class="container">
		<c:if test="${empty message['id']}">
		  <div class="col-xl-9 mx-auto">
		     <h1 class="mb-5" style="margin-top:200px!important; color:#37454d; text-align:center">We don't have any hotel in the city specified by you</h1>
		  </div>
		</c:if>
		<form action="LoginController" method="POST" id="myForm">
		<div class="card-deck mb-3 text-center" style="margin-top: 50px;">
			<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModalFoody" style="margin:auto">Best Restaurants in the city</button>
			  <div class="modal" id="myModalFoody">
				<div class="modal-dialog">
				  <div class="modal-content" style="text-align: left !important">
				   <div class="modal-header">
					  <h4 class="modal-title">
					  Foody
					  </h4> 
					  <button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<c:if test="${not empty foody['id']}">
						<div class="modal-body">
						<c:forEach items="${foody['id']}" var="messageItem" varStatus="loopFoody">
						   <div class="row">
							   <div class="fa col-lg-4">
							   	<img src="${foody['image_url'][loopFoody.index]}" width="100%" height="100%">
							   </div>
							   <div class="fa col-lg-8">
							   		<h6>${foody['title'][loopFoody.index]}</h6>
							   		<h6>${foody['city_name'][loopFoody.index]} , ${foody['country_name'][loopFoody.index]}</h6>
									<h6>${foody['description'][loopFoody.index]}</h6>
									<h6>${foody['title'][loopFoody.index]}</h6>
							   </div>
						   </div>
						   <hr>
						   </c:forEach>
						   <hr>
						</div>
					</c:if>
					<c:if test="${empty foody['id']}">
					  <div class="modal-body">
						  <div class="row">
							   <div class="fa col-lg-12">
							   <center><h4>Foody don't provide service in the city specified by you</h4></center>
							   </div>
						  </div>
					  </div>
					</c:if>
					<div class="modal-footer">
					  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				 </div>
				</div>
			 </div>
		</div>
		  <div class="card-deck mb-3 text-center" style="margin-top: 50px;" id="mainForm">
		  <c:forEach items="${message['id']}" var="messageItem" varStatus="loop">
			<div class="card mb-4 box-shadow" id="mainForm"> 
			  <div class="card-body">
				<img src="images/${message['id'][loop.index]}.jpg" width="100%" height="100%">
			  </div>
			  <div class="card-footer">
				<button type="submit" id="bookNow" name="bookNow" style="display: none" value="bookThis" class="btn btn-block btn-outline-primary test123">Book Now</button>
				<button type="button" id="addressForMap" name="addressForMap" onclick="geocode('${message['name'][loop.index]}, ${message['address_line'][loop.index]}, ${message['city'][loop.index]}')" class="btn btn-block btn-outline-primary">View Map</button>
<!-- 				<button type="submit" id="addressForMap" name="addressForMap" class="btn btn-block btn-outline-primary">View Map</button> -->
				<!-- Button to Open the Modal -->
				<button type="button" class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#myModal${message['id'][loop.index]}">View Details</button>
				  <!-- Modal code -->
				  <div class="modal" id="myModal${message['id'][loop.index]}">
					<div class="modal-dialog">
					  <div class="modal-content" style="text-align: left !important">
					   <div class="modal-header">
						  <h4 class="modal-title">
						  ${message['name'][loop.index]}
						  </h4> 
						  <button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
						  <p>${message['address_line'][loop.index]}, ${message['city'][loop.index]}</p>
						  <c:forEach begin="1" end="${message['rating'][loop.index]}">
						  		<p class="fa fa-star"></p>
						  </c:forEach>
						  <br><br>
						  <div class="row">
							  <div class="fa col-lg-4">
								<p class="fa fa-coffee"> Cafe </p><br>
								<p class="fa fa-renren"> parking </p><br>
							  </div>
							  <div class="fa col-lg-4">
								<p class="fa fa-user"> Restaurant </p><br>
								<p class="fa fa-user"> room service </p><br>
							  </div>
							  <div class="fa col-lg-4">
								<p class="fa fa-glass"> Bar </p><br>
								<p class="fa fa-inbox"> AC </p><br>
							  </div>
							  <div class="fa col-lg-4">
								<p class="fa fa-taxi"> cab service </p><br>
								<p class="fa fa-flag-o"> pool </p><br>					  
							  </div>
							  <div class="fa col-lg-4">
								<p class="fa fa-rss"> Wi-Fi </p><br>
								<p class="fa fa-gears"> gym </p><br>
							  </div>
						  </div>
						  <h4>Booking amount - £ ${message['price'][loop.index]}</h4>
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					 </div>
					</div>
				 </div>
			  </div>
			</div>
			</c:forEach>
			</div>
		</form>
  		</div>
  		</c:if>
	</div>
    </div>
</body>
</html>