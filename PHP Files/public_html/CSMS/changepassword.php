<?php

if($_SERVER['REQUEST_METHOD']=='GET')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();

	//Getting values 
	$Username=$_GET['Username'];
	$Password=$_GET['Password'];
    
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
		
		
	//Query
        $sql = "update Customer set cus_pswd='$Password' where cus_name='$Username'";
	    $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                echo "Password Set Successfully";
                $error=false;
                $msg="Success";
                
                array_push($result,array("error"=>$error,"msg"=>$msg));
                print json_encode(array("result"=>$result));    
            }
            else
            {
                echo "Try Again";
                echo mysqli_error($con);
            }
	mysqli_close($con);
}
?>