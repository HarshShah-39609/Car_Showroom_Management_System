<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();
           
	//Getting values 
	$Username=$_POST['name'];
	$i=$_POST['dealerid'];
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
		
		
	//Query
	$sql = "select * from Dealer where de_name=$Username";
	$cmd=mysqli_query($con,$sql);
   
    if($ans="mysqli_num_rows($cmd)"==0);
        
        // $sql = "insert into Dealer values('$id','Yash','syash2581@gmail.com','0123456','9876543210','Nad','Male')";
	  
         $sql = "DELETE FROM Dealer WHERE de_id=$i";
	     $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                header("Location:Dealerdetails.php");
                $error=false;
                $msg="Success";
            }
            else
            {
                echo "Try Again";
            }
	  
	mysqli_close($con);
}
?>