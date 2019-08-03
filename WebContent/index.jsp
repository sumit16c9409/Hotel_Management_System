<%-- <%@page import="com.src.bean.loginBean"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    
    <link rel="stylesheet" href="css/pricing.css">
	<link rel="stylesheet" href="css/metisMenu.min.css" />
	<link rel="stylesheet" href="css/font-awesome.min.css" />
<title>Hotel Booking</title>
</head>
<body>
	<!-- Navigation -->
    <nav class="navbar navbar-light bg-light static-top">
      <div class="container">
        <a class="navbar-brand" href="#">Hotel Booking</a>
      </div>
    </nav>

    <!-- Masthead -->
    <header class="masthead text-white text-center">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-xl-9 mx-auto">
            <h1 class="mb-5" style="margin-top:200px!important; color:#37454d;">Find your dream hotel in cheap price</h1>
          </div>
          <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
            <form action="LoginController" method="post">
              <div class="form-row">
                <div class="col-12 col-md-9 mb-2 mb-md-0">
                  <input type="destination" id="destination" name="destination" class="form-control form-control-lg" placeholder="Enter your Destination...">
                </div>
                <div class="col-12 col-md-3">
                  <button type="submit" class="btn btn-block btn-lg btn-primary">Search</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </header>
</body>
</html>