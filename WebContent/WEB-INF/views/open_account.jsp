<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home_page.css" />
<style>
form {
  border: 3px solid #f1f1f1;
}
input[type=text], input[type=password], input[type=email] {
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
<li><a href="${pageContext.request.contextPath}">Home</a></li>

<li>
                <a href="#">Resources <span class="toggle">Expand</span><span class="caret"></span></a>
<nav>
<ul>
<li><a href="https://www.nasdaq.com/" target="_blank">NASDAQ</a></li>
<li><a href="https://www.nyse.com/index" target="_blank">NYSE</a></li>

</ul>
</nav>
</li>
<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
<li><a href="#">Open Account</a></li>

</ul>
</nav>
</header>
</div>
<h2 class="info"> ${ invalidParam }</h2>
   <form action="openaccount" method="POST">
   <div class="container">
    <div>
      <label for="firstname"><b>First Name</b></label>
    </div>
	<div>
      <input type="text" name="firstname" id="firstname" required>
    </div>
    <br />
	<div>
      <label for="lastname"><b>Last Name</b></label>
    </div>
	<div>
      <input type="text" name="lastname" id="lastname" required>
	</div>
    <br />
    <div>
      <label for="username"><b>Username</b></label>
    </div>
    <div>
      <input type="text" name="username" id="username" required title="Only alphanumeric characters allowed">
    </div>
    <br />
    <div>
      <label for="password"><b>Password</b></label>
    </div>
    <div>
	  <input type="text" name="password" id="password" pattern=".{6,}"   required title="6 characters minimum">
    </div>
	<br />
	<div >
	  <label for="accountType" ><b>Account Type</b></label>
    </div>
	<div >
	  <select name="accountType" id="accountType" style="width:100%;">
	    <option value="Cash Account" >Cash</option>
		<option value="Margin Account">Margin</option>
	  </select>
	</div>
	<br />
	<div >
	  <label for="email"><b>Email</b></label>
	</div>
	<div>
	  <input type="email" name="email" id="email">
	</div>
	<br />
	</div>
	  <button type="submit">Submit</button>
  </form>
</body>
</html>