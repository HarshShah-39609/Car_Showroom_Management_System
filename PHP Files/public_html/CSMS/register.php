<?php

if($_SERVER['REQUEST_METHOD']=='GET')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();

	//Getting values 
	$Username=$_GET['Username'];
	$Email= $_GET['Email'];
    $Password=$_GET['Password'];
    $Phonenumber=$_GET['Phonenumber'];
    $Address=$_GET['Address'];
    $Gender=$_GET['Gender'];
	$i=0;
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
		
		
	//Query
	$sql = "select cus_name from Customer where cus_name='$Username'";
	$cmd=mysqli_query($con,$sql);
    $ans=mysqli_num_rows($cmd);
    if($ans==1)
    {    
        echo "UserName Taken";  
    }
    else
    {
        $sql = "insert into Customer values('$i','$Username','$Email','$Password','$Phonenumber','$Address','$Gender')";
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
    }
	mysqli_close($con);
}
?>

