<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home_page.css" />
<style>
.modal-content {
  background-color: #fefefe;
  margin: 5px auto; /* 15% from the top and centered */
  border: 1px solid #888;
  width: 90%; /* Could be more or less, depending on screen size */
  
}

input[type=text], input[type=password], input[type=email], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}
</style>
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

   <div id="contact" class="modal">
 
<form class="modal-content animate" action="updateusercontactinfo" method="POST">
   

    <div class="container">
      <label for="firstname"><b>First Name</b></label>
      <input type="text"  name="firstname" id="firstname" required>

      <label for="lastname"><b>Last Name</b></label>
      <input type="text" name="lastname" id="lastname" required>
      
      <label for="email"><b>Email</b></label>
      <input type="email" name="email" id="email" required>
      
      <label for="streetNumber"><b>Street Number</b></label>
      <input type="text" name="streetNumber" id="streetNumber" required>

      <label for="street"><b>Street</b></label>
      <input type="text" name="street" id="street" required>

      <label for="postalCode"><b>Postal Code</b></label>
      <input type="text" name="postalCode" id="postalCode" required>
      
      <label for="city"><b>City</b></label>
      <input type="text" name="city" id="city" required>
      
      <label for="country"><b>Country</b></label>
      <input type="text" name="country" id="country" required>
      <button type="submit">Submit</button>      
    </div>

  </form>
</div>
</body>
</html>