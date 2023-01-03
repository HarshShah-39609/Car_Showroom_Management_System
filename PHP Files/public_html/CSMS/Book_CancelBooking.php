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
	$model=$_GET['model'];
	$booktype=$_GET['Type'];
	$cus_id12=mysqli_fetch_array(mysqli_query($con,"select cus_id from Customer where cus_name='$Username'"));
	$cm_id1=mysqli_fetch_array(mysqli_query($con,"select cm_id from Carmodel where cm_name='$model'"));
	$i=0;
	$cm_model=$cm_id1[0];
	//Query
       echo $booktype;
	   if($booktype=="Service")
	   {
            $sql="select * from Service where cus_id=$cus_id12[cus_id] AND cm_id=$cm_id1[cm_id]";	   
            $cmd=mysqli_query($con,$sql);
            $ans=mysqli_num_rows($cmd);
            echo $ans;
            if($ans==1)
            {
                $sql = "DELETE FROM Service WHERE cus_id=$cus_id12[cus_id]";
	            $cmd=mysqli_query($con,$sql);
       
                 if($cmd)
                 {
                     //echo "data inserted successfully";
                     $error=false;
                    $msg="Success";
                
                     //array_push($result,array("error"=>$error,"msg"=>$msg));
                    //print json_encode(array("result"=>$result));    
                }
                 else
                 {
                //echo "Try Again";
                
                }
	        }
	        else
	        {
	            $error=true;
	            $msg="Don't Have Any Booking";
	            array_push($result,array("error"=>$error,"msg"=>$msg));
                print json_encode(array("result"=>$result));    
	        }
	        
	   }
	   else
	   {
	       $user=$cus_id12[0];
	    $sql="select * from Book Where (cus_id1=$user AND cm_id=$cm_model)AND book_type='$booktype'";	   
        $cmd=mysqli_query($con,$sql);
	     $ans=mysqli_num_rows($cmd);
	     echo $ans;
    	     if($ans==1)
            { 
	             $sql = "UPDATE Book SET book_status='Cancel' WHERE cus_id1=$cus_id12[cus_id] && cm_id=$cm_id1[cm_id] &&  book_type='$booktype'";
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
	    else
	    {
	            $error=true;
	            $msg="Don't Have Any Booking";
	            array_push($result,array("error"=>$error,"msg"=>$msg));
                print json_encode(array("result"=>$result));
	    }
	   }
	   echo mysqli_error($con);
	mysqli_close($con);
}
?>