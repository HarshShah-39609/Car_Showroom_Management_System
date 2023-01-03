<?php

if($_SERVER['REQUEST_METHOD']=='GET')
{
	//variables
	$error=true;
	$msg="Failure";
    $result = array();

	//Getting values 
	$uid=$_GET['uid'];
	$gid = $_GET['gid'];
	$glevel="";

	
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id7387289_color","COLOR");
	
	$db=mysqli_select_db($con,"id7387289_color");
			
	//Query
	$sql = "select glevel from Resume_game where uid='$uid' and gid='$gid' order by glevel";
	$cmd=mysqli_query($con,$sql);
    while($ans=mysqli_fetch_array($cmd))
    {
        $glevel=$ans[0];
        //echo $glevel;

    }
    if($ans=mysqli_num_rows($cmd)>0)
    {
        $row=mysqli_fetch_array($cmd);
        $error=false;
        $msg="Success";
        echo $row['glevel'];
        array_push($result,array("error"=>$error,"msg"=>$msg,"lstatus"=>$glevel));
        print json_encode(array("result"=>$result));
    }
    else
        echo "Failure";
       
	
	mysqli_close($con);
}
?>

