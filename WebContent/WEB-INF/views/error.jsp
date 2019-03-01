<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home_page.css" />
</head>
<body>
<header>
<div id="logo" class="menuUp">
<h1>Star Trading Platform</h1>
<div id="navToggle"><a href="#">Menu</a></div>
</div>
<nav>
<ul>
<li><a href="#">Home</a></li>
<li>
                <a href="#">Resources <span class="toggle">Expand</span><span class="caret"></span></a>
<nav>
<ul>
<li><a href="https://www.nasdaq.com/" target="_blank">NASDAQ</a></li>
<li><a href="https://www.nyse.com/index" target="_blank">NYSE</a></li>

</ul>
</nav>
</li>
<li><a href="login">Login</a></li>
<li><a href="open_account">Open Account</a></li>

</ul>
</nav>
</header>
 <div>
    <h2 class="info"> ${ updateUserInfo }</h2>
  </div>
</body>
</html>