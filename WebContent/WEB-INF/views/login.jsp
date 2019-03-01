<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home_page.css" />

	
	<style>
	  /* Bordered form */
form {
  border: 3px solid #f1f1f1;
}

/* Full-width inputs */
input[type=text], input[type=password] {
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

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}

/* Extra style for the cancel button (red) */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the avatar image inside this container */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

/* Avatar image */
img.avatar {
  width: 100px;
  border-radius: 50%;
  height: 100px;
}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* The "Forgot password" text */
span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }
  .cancelbtn {
    width: 100%;
  }
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
<li><a href="${pageContext.request.contextPath}/index">Home</a></li>

<li>
                <a href="#">Resources <span class="toggle">Expand</span><span class="caret"></span></a>
<nav>
<ul>
<li><a href="https://www.nasdaq.com/" target="_blank">NASDAQ</a></li>
<li><a href="https://www.nyse.com/index" target="_blank">NYSE</a></li>

</ul>
</nav>
</li>
<li><a href="#">Login</a></li>
<li><a href="${pageContext.request.contextPath}/open_account">Open Account</a></li>

</ul>
</nav>
</header>
  <h3 class="info"> ${ logoutInfo } </h3>
    <form action="login" method="POST">
    
    
	  <h1 class="welcome">Login to start making money!</h1>
	  
	  <div class="imgcontainer">
        <img src="${pageContext.request.contextPath}/resources/images/signon_image.jpg" alt="Avatar" class="avatar">
      </div>
	<div class="container">
	  <div>
	    <label for="username"><b>Username</b> </label>
	  </div>
	  <div>
        <input type="text" name="username" id="username" required>
	  </div>
	  <br>
	  <div>
        <label for="user-pw"><b>Password</b> </label>
	  </div>
	  <div>
	    <input type="password" name="user-pw" id="user-pw" required minlength="5">
	  </div>
	  <br />
	  <button type="submit">Login</button>
	  </div>
	</form>
  </body>
</html>