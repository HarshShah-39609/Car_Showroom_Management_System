<html>
<head>
	<title>Register page</title>
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
		}
	
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
#regiter1{
	 align:center
	 text-align:left;
	font-size:18px;
}
</style>
</head>
<body style="font-size:24px;">

<form id=regiter1 class="form-inline" method="POST" action="addcardetails.php"> 
		<div style="float:left; align:left;">
			<label for="Name">CarModelName:</label><br>
				<input type="text" name="carmodel" Value=""><br>
			<label for="Email">CarFuelType:</label><br>
				<input type="text" name="fueltype" value=""><br>
			<label for="text">CarColor:</label><br>
				<input type="text" name="color" value=""><br>
			<label for="text">CarPrice:</label><br>
				<input type="text" name="price" value=""><br>
			<label for="ContactNumber">CarWarrenty:</label><br>
				<input type="text" name="warrenty" value=""><br>
			<label for="Address">CarEngineType:</label><br>
				<input type="Text" name="engine" value=""><br>
			<label for="Gender">CarAvarage:</label><br>
				<input type="Text" name="avarage" value=""><br>
			<label for="Gender">CarSeatingCapaCity:</label><br>
				<input type="Text" name="seat" value=""><br>
			<label for="Gender">CarMaxPower:</label><br>
				<input type="Text" name="maxpower" value=""><br>
			<label for="Gender">CarTorque:</label><br>
				<input type="Text" name="torque" value=""><br><br>
			
		<input type="submit">
</form>
</body>
</html>