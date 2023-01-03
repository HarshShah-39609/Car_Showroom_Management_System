<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();
               
	//Getting values 
	$name=$_POST['shrw_name'];
	$address=$_POST['shrw_address'];
    $Area=$_POST['shrw_areaname'];
    $Pincode=$_POST['shrw_pincode'];
    $Contact=$_POST['shrw_contact'];
    $dealer=$_POST['de_idsh'];
    $i=0;
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
		
		
	//Query
	$sql = "select * from showroom where shrw_name=$name";
	$cmd=mysqli_query($con,$sql);
   
    if($ans="mysqli_num_rows($cmd)"==0);
        
        // $sql = "insert into Dealer values('$id','Yash','syash2581@gmail.com','0123456','9876543210','Nad','Male')";
	 // shrw_id	shrw_contact	shrw_name	shrw_address	shrw_pincode	shrw_areaname	de_idsh
         $sql = "insert into showroom values('$i','$Contact','$name','$address','$Pincode','$Area','$dealer')";
	     $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                header("Location:ShowroomDetails.php");
                $error=false;
                $msg="Success";
            }
            else
            {
                echo "Try Again";
                echo mysqli_error($con);
            }
	  
	mysqli_close($con);
}
?>