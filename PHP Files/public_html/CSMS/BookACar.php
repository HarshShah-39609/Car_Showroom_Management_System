<?php

if($_SERVER['REQUEST_METHOD']=='GET')
{
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	$db=mysqli_select_db($con,"id16370078_projectcsms");
	
	//variables
	$error=true;
	$msg="Failure";
    $result = array();
    
	//Getting values 
	$Username=$_GET['Username'];
	$booktype=$_GET['booktype'];
	$model=$_GET['Carmodel'];
	$date=date('d-m-y h:i:s');
	$time=date('Y-m-d H:i:s',time());
	$bookstus='Booked';
	$cus_id1=mysqli_fetch_array(mysqli_query($con,"select cus_id from Customer where cus_name='$Username'"));
    $cus_email1=mysqli_fetch_array(mysqli_query($con,"select cus_email from Customer where cus_name='$Username'"));
	$shrw_id1=mysqli_fetch_array(mysqli_query($con,"select shrw_id from showroom"));
	$cm_id1=mysqli_fetch_array(mysqli_query($con,"select cm_id from Carmodel where cm_name='$model'"));
	$i=0;
	echo $model;
		
	//Query
	$sql = "select * from Customer where cus_name=$Username";
	$cmd=mysqli_query($con,$sql);
   
    if($cus_id1==0)
    {
        echo "User Is Not Exist";
    }
    
    
    if($ans='mysqli_num_rows($cmd)'==0);
            // $sql = "insert into Customer values('$id','Yash','syash2581@gmail.com','0123456','9876543210','Nad','Male')";
	 $sql = "insert into Book values('$i','$booktype','$date','$time','$bookstus','$cus_id1[cus_id]','$shrw_id1[shrw_id]','$cm_id1[cm_id]')";
	   $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                //echo "data inserted successfully";
                $error=false;
                $msg="Success";
                $email = $cus_email1[0];
                
                array_push($result,array("error"=>$error,"msg"=>$msg,"email"=>$email));
                print json_encode(array("result"=>$result));    
            }
            else
            {
                //echo "Try Again";
                echo mysqli_error($con);
            }
       
	mysqli_close($con);
}
?>
