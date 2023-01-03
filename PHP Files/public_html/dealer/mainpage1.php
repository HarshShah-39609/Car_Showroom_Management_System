<!DOCTYPE html>
<html>
<head>
<style>
    	a:link{
                  background-color: #000080;
                  color: white;
                  padding: 14px 25px;
                 text-align: center;
                 text-decoration: none;
                  display: inline-block;
            }

        a:hover, a:active {
                    background-color: red;
            }
        a:visited{background-color:#7cfc00}

    body
    {
        		background-color:#ff7f50;;
    }
    #main
    {
        height:300px;
        width:1000px;
        background-color:white;
        margin:0px auto;
    }
    #header
    {
        text-align:center;
        height:200px;
        background-color:pink;
        width:1000px;
        border:solid darkblue;
    }
    #leftside
    {
         background-color:#cd853f;
        border:solid black;
    }
    #content
    {
        height:750px;
        width:1000px;
    }
</style>	
</head>
<body background="finalp.png";>
    <div id="main">
			<div id="header">
					<center style="font-size:50px; margin-top:30px;
					background-image:linear-gradient(gray,purpule);"> 
					Car Showroom Management System </center>
			</div>
			<div id="leftside">
					<center style="font-size:26px; margin-top:16px; color=yellow;"> 
					<a href="Cardmodel1.php" target="_self">Cars Details</a>
					<a href="bookedCar1.php" target="_self">Car Bookings</a>
					<a href="ServiceBooking1.php" target="_self">Service Bookings</a>
			</center>
					
			</div>
		    	<div id="rightside">
		        </div>
	</div>
	</body>
</html>