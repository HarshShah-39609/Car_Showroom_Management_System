<?php

if($_SERVER['REQUEST_METHOD']=='GET')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();

	//Getting values 
	$feeds=$_GET['feeds'];
	$i=0;
	
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
		
   
   //Query

       $sql = "insert into Feedback values('$i','$feeds')";
	   $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                echo "data inserted successfully";
                $error=false;
                $msg="Success";
                
                array_push($result,array("error"=>$error,"msg"=>$msg));
                print json_encode(array("result"=>$result));    
            }
            else
            {
                echo "Try Again";
            }
       
	mysqli_close($con);
}
?>
