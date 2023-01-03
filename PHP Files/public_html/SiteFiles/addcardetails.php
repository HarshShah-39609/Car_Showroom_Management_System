<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();
                 
	//Getting values 
    $cm_name=$_POST['carmodel'];
	$cm_fueltype= $_POST['fueltype'];
    $cm_color=$_POST['color'];
    $cm_price=$_POST['price'];
    $cm_warrenty=$_POST['warrenty'];
    $cm_enginetype=$_POST['engine'];
    $cm_avarage=$_POST['avarage'];
    $cm_seat=$_POST['seat'];
    $cm_maxpower=$_POST['maxpower'];
    $cm_torque=$_POST['torque'];
	$i=0;
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
		
		
	//Query
	$sql = "select * from Carmodel where cm_name='Alto'";
	$cmd=mysqli_query($con,$sql);
   
    if($ans=mysqli_num_rows($cmd)==0);
    									

    // $sql = "insert into Carmodel values('$i','Alto','Petrol','Red','2.40Lakhs','1 Year','In-line','15.7Km','5','46.36hp','62nm_3000rpm')";
	  
	  $sql = "insert into Carmodel values('$i','$cm_name','$cm_fueltype','$cm_color','$cm_price','$cm_warrenty','$cm_enginetype','$cm_avarage','$cm_seat','$cm_maxpower','$cm_torque')";
	   $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                header("Location:Cardmodel.php");
                $error=false;
                $msg="Success";
            }
            else
            {
                echo "Try Again";
                echo mysqli_error($con);
            }
       
    echo mysqli_error($con);
	mysqli_close($con);
}
?>