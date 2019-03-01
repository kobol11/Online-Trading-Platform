<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
    <%@ page import="com.fdmgroup.model.*"%>
    <%@page import="java.awt.List"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home_page.css" />
<style>
      
      table { font-family: Courier, monospace; }
      .stocks-container {
        margin-bottom: 1.5em;
        width: 100%;
        max-width: 600px;
      }
      .stocks-container a { text-decoration: none; }
      table {
        border-collapse: collapse;
        width: 100%;
        font-size: 1.1em;
      }
      .stock-symbol {
        width: 12%;
        padding: 2px 4px 2px 0px;
      }
      .stock-price, .stock-change, .stock-change-pct, .stock-mkt-cap {
        width: 22%;
        text-align: right;
        padding: 2px 4px;
      }
      @media (max-width: 576px) {
        table { margin-bottom: 3em; }
        .stock-mkt-cap { display: none; }
        .stock-symbol { width: 16%; }
        .stock-price, .stock-change, .stock-change-pct { width: 28%; }
        td.stock-symbol, td.stock-price, td.stock-change, td.stock-change-pct {
          padding-top: 1em;
          padding-bottom: 1em;
        }
      }
      summary:hover { cursor: pointer; }
      summary::-webkit-details-marker { display: none; }
      button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
 
}

div.welcome, .button{
position: relative;
display: inline-block;


}

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%;
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

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

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 20%;
  height: 20%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Close Button */
.close {
  /* Position it in the top right corner outside of the modal */
  position: absolute;
  right: 25px;
  top: 0; 
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

/* Close button on hover */
.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}

@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}
#orders, #accountinfo {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
#orders td, #orders th, #accountinfo td, #accountinfo th {
  border: 1px solid #ddd;
  padding: 8px;
}

#orders tr:nth-child(even), #accountinfo tr:nth-child(even){background-color: #f2f2f2;}

#orders tr:hover, #accountinfo tr:hover {background-color: #ddd;}

#orders th, #accountinfo th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}

.caption{
 
  color: white;
  font-weight: bold;
}
    </style>
  </head>
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
<li>
<a href="#">Update Profile <span class="toggle">Expand</span><span class="caret"></span></a>
<nav>
<ul>
<li><a  onclick="document.getElementById('username_password').style.display='block'">Username/Password</a></li>
<li><a onclick="document.getElementById('contact').style.display='block'">Contact Info</a></li>
</ul>
</nav>
</li>
<li><a onclick="document.getElementById('orders').style.display='block'">View Trades</a></li>
<li><a onclick="document.getElementById('accountinfo').style.display='block'">Accounts <span class="toggle">Expand</span><span class="caret"></span></a>
  <nav>
    <ul id="accounts" onclick="document.getElementById('accountinfo').style.display='block'" >
    </ul>
  </nav>
</li>
<li><a onclick="document.getElementById('fundaccount').style.display='block'">Fund Account</a></li>
</ul>
</nav>
</header>
<div id="accountDiv" data-accountnumber="${ sessionScope.STPUserAccounts}" data-user="${ sessionScope.STPUser.getFirstname() }"></div>
<div style="background-color: white;">
<div class="welcome" style="color:green;font-weight:bold;font-size:20px;">Welcome ${ sessionScope.STPUser.getFirstname() } ${ sessionScope.STPUser.getLastname() }</div>
<div class="welcome" ><form action="logout" method="GET" class="button" style="margin-left:950px;">
<button type="submit"><b>Log Out</b></button>
</form></div>
</div>

<h2 style="color:brown; position:relative; display:inline-block;" class="trade"> Today is the best day to make money, start trading now!</h2>
<form class="trade" style="position:relative; display:inline-block; margin-left: 495px;">
<div style="color:white; font-weight: bold;background-color:green;">
<a onclick="document.getElementById('order').style.display='block'"><b>Place Trade</b></a>
</div>

</form>
<h2 class="info" id="dashboarderror"> ${ updateUserInfo }</h2>
<br>
<div class="stocks-container" style="width:100%; margin:auto;"></div>

<div id="username_password" class="modal">
 <span onclick="document.getElementById('username_password').style.display='none'" 
class="close" title="Close Modal">&times;</span> 


<form class="modal-content animate" action="updateuser" method="POST">

<div class="imgcontainer">
      <img src="${pageContext.request.contextPath}/resources/images/signon_image.jpg" alt="Avatar" class="avatar">
    </div>    

    <div class="container">
      <label for="username"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" id="username" required title="Only alphanumeric characters allowed">

      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" id="password" pattern=".{6,}"   required title="6 characters minimum">

      <button type="submit">Submit</button>      
    </div>

  </form>
</div>

<div id="contact" class="modal">
 <span onclick="document.getElementById('contact').style.display='none'" 
class="close" title="Close Modal">&times;</span> 
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

<div id="fundaccount" class="modal">
 <span onclick="document.getElementById('fundaccount').style.display='none'" 
class="close" title="Close Modal">&times;</span> 


<form class="modal-content animate" action="fundaccount" method="POST">    

    <div class="container">
      
      <input type="hidden"  name="acctnumber" id="acctnumber" value="${ sessionScope.STPUser.getAccounts().get(0).getAccountNumber() }">

      <label for="fund"><b>Amount</b></label>
      <input type="text" placeholder="Enter Amount" name="fund" id="fund" required>

      <button type="submit">Submit</button>      
    </div>

  </form>
</div>

<div id="order" class="modal">
 <span onclick="document.getElementById('order').style.display='none'" 
class="close" title="Close Modal">&times;</span> 

<form class="modal-content animate" action="order" method="POST">

    <div class="container">
      <label for="username"><b>Stock</b></label>
      <select name="stockType" id="stockType" style="width:100%;">
	    <option value="GOOGL" >Google</option>
		<option value="AAPL">Apple</option>
	  </select>
	  <label for="action"><b>Action</b></label>
      <select name="action" id="action" style="width:100%;">
	    <option value="buy" >Buy</option>
		<option value="sell">Sell</option>
	  </select>
      <label for="unit"><b>Unit</b></label>
      <input type="text" placeholder="Enter desired units of shares" name="unit" id="unit" required>
      
      <label for="price_per_unit"><b>Price Per Unit Share</b></label>
      <input type="text" placeholder="Enter desired price per unit of share" name="price_per_unit" id="price_per_unit" required>
      <button type="submit">Submit</button>      
    </div>

  </form>
</div>

<p class="updated-timestamp"></p>
<div id="accountinfo" class="modal">
<span onclick="document.getElementById('accountinfo').style.display='none'" 
class="close" title="Close Modal">&times;</span>
<div class="container">
<table class="accountinfo modal-content animate">
<caption class="caption">Account Details</caption>
<tr>
  <th>Account Number</th>
  <th>Cash Balance</th>
</tr>
<c:forEach items="${sessionScope.STPUser.getAccounts()}" var="item">
  <tr>
    <td>${item.getAccountNumber() }</td>
    <td>${item.getCashBalance() }</td>
  </tr>
    
</c:forEach>
</table>
</div>

</div>

<div id="orders" class="modal">
<span onclick="document.getElementById('orders').style.display='none'" 
class="close" title="Close Modal">&times;</span> 
<div class="container">
<table class="orders modal-content animate">
  <caption class="caption">Transactions</caption>
  <tr>
    <th>Stock</th>
    <th>Stock Unit</th>
    <th>Transaction Date</th>
    <th>Order Type</th>
    <th>Status</th>
  </tr>
  <c:forEach items="${sessionScope.STPUser.getOrders()}" var="item">
  <tr>
    <td>${item.getStock().getSymbol() }</td>
    <td>${item.getStockUnit()}</td>
    <td>${item.getTransactionDate()}</td>
    <td>${item.getAction()}</td>
    <c:if test='${item.getOrderStatusId() == 1}'>
         <td>Pending</td>
   </c:if>
    <c:if test='${item.getOrderStatusId() == 2}'>
         <td>Completed</td>
   </c:if>
   <c:if test='${item.getOrderStatusId() == 3}'>
         <td>Cancelled</td>
   </c:if>
  </tr>
    
</c:forEach>
</table>
</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/stock.js"></script>
<script>

function accountDetails(elem){
	var displayAcctInfoDiv = document.getElementById("accountinfo");
	var p1 = document.createElement("p");
	var p2 = document.createElement("p");
	var user = accountDiv.getAttribute("data-user");
	p1.innerHTML = "Account No: " + elem.a.innerText;
	p2.innerHTML = "Account Holder: " + user;
	displayAcctInfoDiv.appendChild(p2);
	displayAcctInfoDiv.appendChild(p1);
}

var accountDiv = document.getElementById("accountDiv"), accounts;
var accountList = document.getElementById("accounts");
accounts = accountDiv.getAttribute("data-accountnumber");
console.log(accounts);
var numbers = accounts.trim().split(" ");
for(var i = 0; i < numbers.length; i++){
	var li = document.createElement("li");
	var a = document.createElement("a");
	a.innerHTML = numbers[i];
	//a.onclick = accountDetails(this);
	li.appendChild(a);
	accountList.appendChild(li);
}

function setError(){
	var errorDiv = document.getElementById("dashboarderror");
	errorDiv.innerHTML = "";
}

var body = document.getElementsByTagName("BODY")[0];
body.onmousemove = setError;
</script>
</body>
</html>