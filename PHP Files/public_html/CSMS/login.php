<?php

if($_SERVER['REQUEST_METHOD']=='GET')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();

	//Getting values 
	$login=$_GET['login'];
	$password = $_GET['password'];

	
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
			
	//Query
	$sql = "select * from Customer where cus_name='$login' and 	cus_pswd='$password'";
	$cmd=mysqli_query($con,$sql);

	//Fetching Record

        while($ans=mysqli_fetch_row($cmd))
        {
	            if($ans[0]==$login && $ans[4]==$password)
	            {
		            $error=false;
	                $msg="Success";
	                array_push($result,array("error"=>$error,"msg"=>$msg));
		            echo "Login Successful";
		            break;
	            }
	            
        }
        if($ans=mysqli_num_rows($cmd)>0)
        {
            $error=false;
            $msg="Success";
            array_push($result,array("error"=>$error,"msg"=>$msg));
             print json_encode(array("result"=>$result));
           
        }
        else
            echo "Failure";
// 	mysqli_close($con);
}
?>

