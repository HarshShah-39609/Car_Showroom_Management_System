<html>
<head>
	<title>Login Page Of Showroom</title>
	<style>
 p {font-size:30px;
	color:red;
	text-align:left;}
a{
    font-size:20px;
}
#link1{
    padding=20px;
    font-size:18px;
    margin-left:300px;
    margin-top:50px;
    margin-bottom:50px;
    
}
body{background-image:url('13.jpg');
		background-size:cover;
		background-repeat:no-repeat;
		background-color:#ff7f50;
		text-align:center;
		margin-top:200px;}
	
.login input[type=text]{
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid #3b5998;
	border-radius: 2px;
	color: #000;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
}

.login input[type=password]{
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid #3b5998;
	border-radius: 2px;
	color: #000;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
	margin-top: 10px;
}

.login input[type=button]{
	width: 260px;
	height: 35px;
	background: #fff;
	border: 1px solid #3b5998;
	cursor: pointer;
	border-radius: 2px;
	color: #a18d6c;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 20px;
	margin-top:100px;
}

.login input[type=button]:hover{
	opacity: 0.8;
}

.login input[type=button]:active{
	opacity: 0.6;
}

.login input[type=text]:focus{
	outline: none;
	border: 1px solid #000000;
}

.login input[type=password]:focus{
	outline: none;
	border: 1px solid #000000;
}
</style>
</head>
<body style="font-size:20px;">
    <div id="header">
					<center style="font-size:50px; margin-top:30px;
					background-image:linear-gradient(gray,purpule);"> 
					Car Showroom Management System </center><br><br>
			</div>

<form class="form-inline" method="POST" action="SiteFiles/logincheck.php">
		<label for="login Id">Login Id:</label>
			<input type="text" name="Lid" Value=""><br>
		<label for="Password">Password:</label>
			<input type="Password" name="pass" value=""><br>
        	<a id=link1 href="Cardetails.html" target="_self">Forgot Password</a><br>
    	<input type="submit"><br><br>
    	<a style="" href="dealer/dealerlogin.php" target="_self">Dealer Login</a><br>
</form>
</body>
</html>

