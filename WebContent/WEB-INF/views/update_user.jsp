<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open Account</title>
<link rel="stylesheet" type="text/css" href="css/home_page.css" />
</head>
<body>
<header>
<div id="logo" class="menuUp">
<h1>Star Trading Platform</h1>
<div id="navToggle"><a href="#">Menu</a></div>
</div>
<nav>
<ul>
<li><a href="index.jsp">Home</a></li>
<li><a href="#">About STP</a></li>
<li>
                <a href="#">Resources <span class="toggle">Expand</span><span class="caret"></span></a>
<nav>
<ul>
<li><a href="https://www.nasdaq.com/" target="_blank">NASDAQ</a></li>
<li><a href="https://www.nyse.com/index" target="_blank">NYSE</a></li>

</ul>
</nav>
</li>
<li><a href="#">Contact Us</a></li>
</ul>
</nav>
</header>

<h2 class="info"> ${ updateUserInfo }</h2>

   <form action="updateuser" method="POST">
    <div class="grid">
      <label for="firstname">First Name:</label>
    </div>
	<div class="grid">
      <input type="text" name="firstname" id="firstname" >
    </div>
    <br />
	<div class="grid">
      <label for="lastname">Last Name:</label>
    </div>
	<div class="grid">
      <input type="text" name="lastname" id="lastname" >
	</div>
    <br />
    <div class="grid">
      <label for="username">Username:</label>
    </div>
    <div class="grid">
      <input type="text" name="username" id="username" >
    </div>
    <br />
    <div class="grid">
      <label for="password">Password:</label>
    </div>
    <div class="grid">
	  <input type="text" name="password" id="password">
    </div>
	<br />
	
	<div class="grid">
	  <label for="email">Email:</label>
	</div>
	<div class="grid">
	  <input type="email" name="email" id="email">
	</div>
	<br />
	
	<div class="grid">
	  <label for="streetNumber">Street Number:</label>
	</div >
	<div class="grid">
	  <input type="text" name="streetNumber" id="streetNumber">
	</div>
	<br />
	
	<div class="grid">
	  <label for="street">Street:</label>
	</div>
	<div class="grid">
	  <input type="text" name="street" id="street">
	</div>
	<br />
	
	<div class="grid">
	  <label for="postalCode">Postal Code:</label>
	</div>
	<div class="grid">
	  <input type="text" name="postalCode" id="postalCode">
	</div>
	<br />
	
	<div class="grid">
	  <label for="city">City:</label>
	</div>
	<div class="grid">
	  <input type="text" name="city" id="city">
	</div>
	<br />
	
	<div class="grid">
	  <label for="country">Country:</label>
	</div>
	<div class="grid">
	  <input type="text" name="country" id="country">
	</div>
	<br />
	
	  <input type="submit" value="Send">
  </form>
</body>
</html>