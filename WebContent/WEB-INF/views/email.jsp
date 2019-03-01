<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://smtpjs.com/v3/smtp.js"></script>
<title>Insert title here</title>
</head>
<body>
<button onclick="sendEmail()">Click Me</button>

<script>
function sendEmail(){
	Email.send({
	    Host : "smtp.gmail.com",
	    Username : "fridaygame2019@gmail.com",
	    Password : "fridaygame123!@#",
	    To : ['bolarinwa.komolafe@fdmgroup.com', 'kobol11@yahoo.com', 'komolafe.bolarinwa@gmail.com'],
	    From : "fridaygame2019@gmail.com",
	    Subject : "Friday Game",
	    Body : "Another Friday Game Email Test 123..."
	    }).then(
	      function(message){
	    	  alert(message);
	      } 
	    );
}

function send(){
	var emailTo = "kobol11@yahoo.com";
	var emailCC = "komolafe.bolarinwa@gmail.com";
	var emailSub = "Friday Game";
	var emailBody = "Hello testing";
	window.open("mailto:"+emailTo+'?cc='+emailCC+'&subject='+emailSub+'&body='+emailBody);
	alert("Email sent!");
}

</script>
</body>

</html>