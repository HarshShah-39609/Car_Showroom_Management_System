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
	$service=$_GET['serpaytype'];
	$ser_des=$_GET['serdescri'];
    $model1=$_GET['carmodel'];
	$cus_email1=mysqli_fetch_array(mysqli_query($con,"select cus_email from Customer where cus_name='$Username'"));
	if($service=="Free")
	{
	    $amount=0;
	}
	else
	{
	    if($set_des=='Full')
	    {
	        $amount="3000";
	    }
	    else
	    {
	        $amount="1500";
	    }
	}
	$cus_id=mysqli_fetch_array(mysqli_query($con,"select cus_id from Customer where cus_name='$Username'"));
	$shrw_id=mysqli_fetch_array(mysqli_query($con,"select shrw_id from showroom"));
	$cm_id=mysqli_fetch_array(mysqli_query($con,"select cm_id from Carmodel where cm_name='$model1'"));
	$date=date('d-m-y h:i:s');
	$i=0;
	
	//Query
	
	    $cus_id1=$cus_id[0];
         $cm_id1=$cm_id[0];
        $sql = "insert into Service values('$i','$service','$ser_des','$amount','$cus_id1','$cm_id1')";
	   $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                echo "data inserted successfully";
                $error=false;
                $msg="Success";
                $email = $cus_email1[0];

                array_push($result,array("error"=>$error,"msg"=>$msg,"email"=>$email));
                print json_encode(array("result"=>$result));    
            }
            else
            {
                echo "Try Again";
                echo mysqli_error($con);
            }
           
           
           
    /* $ser_id=mysqli_fetch_array(mysqli_query($con,"select ser_id from Customer where ser_list='$service'"));
        $date = date('d-m-y h:i:s', strtotime('+15 days'));
            	
        $sql = "insert into service_bill values('$i','$date','$amount','','$cus_id[cus_id]','$shrw_id[shrw_id]','$cm_id[cm_id]','$ser_id[ser_id]','')";
	   $cmd=mysqli_query($con,$sql);
       
            if($cmd)
            {
                echo "data inserted successfully";
                $error=false;
                $msg="Success";
                 $email = $cus_email1[0];
                array_push($result,array("error"=>$error,"msg"=>$msg));
                print json_encode(array("result"=>$result));    
            }
            else
            {
                echo "Try Again";
                echo mysqli_error($con);
            }
       */
	mysqli_close($con);
}
?>