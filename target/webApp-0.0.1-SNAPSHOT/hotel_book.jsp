<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="google-signin-client_id" content="658810886525-8eq69ms22roa4rlvajqid8ttpn5bom9c.apps.googleusercontent.com">
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    
    <link rel="stylesheet" href="css/pricing.css">
	<link rel="stylesheet" href="css/metisMenu.min.css" />
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<link rel="stylesheet" href="css/sb-admin-2.css" />
	<link rel="stylesheet" href="https://blackrockdigital.github.io/startbootstrap-sb-admin-2/vendor/metisMenu/metisMenu.min.css" />
	<link rel="stylesheet" href="https://blackrockdigital.github.io/startbootstrap-sb-admin-2/vendor/font-awesome/css/font-awesome.min.css" />
	
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Booking</title>
</head>
<body onload="manageDiv()">
<script>

	function manageDiv(){
		var tableEntry = "${tableEntry}";
		var travelStatus = "${travelStatus}";
		if (tableEntry == 'confirmed') {
			alert("Your booking has been confirmed");
			if (travelStatus == 'reminderAdded') {
				alert("Reminder has been added to your calender");
			}	
		} else if (tableEntry == 'notConfirmed') {
// 				alert("There is problem while booking please contact system administrator");
		} else {
			alert("Something went wrong");
		}
	}

	function enableForm(){
		var checkBox = document.getElementById("travelAPI");
	    if (checkBox.checked == true){
	    	$("#travelForm").css("display","block");
	    	$("#boardingLocation").prop('required',true);
	    	$("#boardingPostalCode").prop('required',true);
	    	$("#sourcePostalCode").prop('required',true);
	    	$("#travelDate").prop('required',true);
	    } else {
	    	$("#travelForm").css("display","none");
	    	$("#boardingLocation").prop('required',false);
	    	$("#boardingPostalCode").prop('required',false);
	    	$("#sourcePostalCode").prop('required',false);
	    	$("#travelDate").prop('required',false);
	    }
	}
	
	function onSignIn(googleUser){
		var profile=googleUser.getBasicProfile();
		var options = new gapi.auth2.SigninOptionsBuilder({'scope': 'https://www.googleapis.com/auth/calendar'});
	    googleUser.grant(options).then(
	        function(success){
	          console.log(JSON.stringify({message: "success", value: success}));
	          var access_token=googleUser.Zi.access_token;
	          console.log(access_token);
			$(".g-signin2").css("display","none");
			$(".test123").css("display","block");
			$("#loginDiv").css("display","block");
			$("#profile_pic").attr("src",profile.getImageUrl());
			$("#email").text(profile.getEmail());
			$("#profileName").text(profile.getName());
			
			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv');
			newTextBoxDiv.after().html('<input type="hidden" name="dynamicTextbox" id="dynamicTextbox" value="'+access_token+'" >');
			newTextBoxDiv.appendTo("#travelForm");
	        },
	        function(fail){
	          alert(JSON.stringify({message: "fail", value: fail}));
	        });
	}
	
	$('#myform').submit(function() {
		// DO STUFF...
		alert("test");
		return true; // return false to cancel form action
	});
	
	function signOut(){
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function(){
			alert("You have successfully sign out");
			$(".g-signin2").css("display","block");
			$("#loginDiv").css("display","none");
			$(".test123").css("display","none");
		})
	}
	
	 function gup(url, name) {
         namename = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
         var regexS = "[\\#&]" + name + "=([^&#]*)";
         var regex = new RegExp(regexS);
         var results = regex.exec(url);
         if (results == null)
             return "";
         else
             return results[1];
     }
	 
</script>
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
			<div class="container">
			<!-- Form start -->
			<form action="ProcessingController" method="POST" id="myform">
				<div class="row show-grid" style="margin-top: 50px;">
					<div class="col-md-8">
					<h2 class="w3-center">Hotel Preview</h2>
						<div class="w3-content w3-display-container" style="height: 400px; width: 400px;">
						  <img class="mySlides" src="images/1.jpg" style="width:100%; height: 100%;">
						  <img class="mySlides" src="images/2.jpg" style="width:100%; height: 100%;">
						  <img class="mySlides" src="images/3.jpg" style="width:100%; height: 100%;">
						  <img class="mySlides" src="images/4.jpg" style="width:100%; height: 100%;">
						  <button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
						  <button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
						</div>
						<div class="checkbox">
                            <label>
                                <input type="checkbox" id="travelAPI" name="travelAPI" onclick="enableForm()"> Travel reminder
                            </label>
                        </div>
                        <div id="travelForm" class="input_fields_wrap" style="display: none">
							<div class="col-md-5" style="display: inline; margin:10px; float: left;">
								Boarding Location: <input type="text" class="form-control" name="boardingLocation" id="boardingLocation" placeholder="London"><br>
								Boarding Point Postal Code: <input type="text" class="form-control" name="boardingPostalCode" id="boardingPostalCode" placeholder="D01 V6V6"><br>
								Source Postal Code: <input type="text" class="form-control" name="sourcePostalCode" id="sourcePostalCode" placeholder="D01 Y300"><br>
                            </div>
							<div class="col-md-5" style="display: inline; margin:10px; float: left;">
								Travel Date: <input type="datetime-local" class="form-control" id="travelDate" name="travelDate" ><br>
								Mode Of Travel
								<select name="travel_options" class="form-control" >
								  <option value="DRIVING">DRIVING</option>
								  <option value="WALKING">WALKING</option>
								  <option value="BICYCLING">BICYCLING</option>
								</select><br>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="margin-bottom:  50px;">
					<div id="formData">
						First name: <input type="text" class="form-control" name="firstName" required ><br>
						Last name: <input type="text" class="form-control" name="lastName" required><br>
						E-mail: <input type="email" class="form-control" name="email" required ><br>
						Phone Number: <input type="text" size="10" class="form-control" name="phoneNumber" ><br>
						Mode of Commute: 
							<select name="mode_of_commute" class="form-control" >
							  <option value="bus">Bus</option>
							  <option value="train">Train</option>
							  <option value="air">Air</option>
							</select><br>
						From Date: <input type="date" min="2018-06-29" class="form-control" name="fromDate" ><br>
						To Date: <input type="date" class="form-control" name="toDate" ><br>
						Reason for visit: 
							<select name="reasonForVisit" class="form-control" >
							  <option value="holiday">Holiday with the best of views</option>
							  <option value="business">Business tour</option>
							  <option value="visit">Visit to specific place</option>
							  <option value="not_to_disclose">Not to be disclose</option>
							</select><br>
						<button type="submit" id="saveBooking" name="saveBooking" value="saveBooking" class="btn btn-block btn-outline-primary">Submit</button>
					</div>
					</div>
				</div>
			</form>
			<!-- Form end -->
			</div>
		</div>
	</div>
	<script>
		var slideIndex = 1;
		showDivs(slideIndex);
		
		function plusDivs(n) {
		  showDivs(slideIndex += n);
		}
		
		function showDivs(n) {
		  var i;
		  var x = document.getElementsByClassName("mySlides");
		  if (n > x.length) {slideIndex = 1}    
		  if (n < 1) {slideIndex = x.length}
		  for (i = 0; i < x.length; i++) {
		     x[i].style.display = "none";  
		  }
		  x[slideIndex-1].style.display = "block";  
		}
	</script>
</body>
</html>