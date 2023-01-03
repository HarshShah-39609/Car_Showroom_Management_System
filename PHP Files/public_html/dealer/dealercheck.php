<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();

	//Getting values 
	$login=$_POST['Lid'];
	$password = $_POST['pass'];

	
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
	$db=mysqli_select_db($con,"id16370078_projectcsms");
			
	//Query
	$sql = "select * from Dealer where de_id='$login' and de_pswd='$password'";
	$cmd=mysqli_query($con,$sql);

	//Fetching Record

        while($ans=mysqli_fetch_row($cmd))
        {
	            if($ans[0]==$login && $ans[2]==$password)
	            {
		             header("Location:mainpage1.php");
		            break;
	            }
	            
        }
        if($ans=mysqli_num_rows($cmd)>0)
        {
            echo "Login Failed";
        }
        else
            echo "Failure";
       
// 	mysqli_close($con);
}
?>

