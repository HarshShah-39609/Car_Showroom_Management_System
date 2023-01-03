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
	$glevel=$_GET['glevel'];
	$time = $_GET['time'];
	$score = $_GET['score'];
	
	$user="$uid";
	$name="You";

	
	//Connection with database and selection of database
	$con = mysqli_connect("localhost","id7387289_color","COLOR");
	
	$db=mysqli_select_db($con,"id7387289_color");
			
	//Query
	$sql = "select * from Resume_game where uid='$uid' and gid='$gid' and glevel='$glevel'";
	$cmd=mysqli_query($con,$sql);


        if($ans=mysqli_num_rows($cmd)>0)
        {
            //echo "record updated";
            $sql="update Resume_game set lstatus='1',time='$time',score='$score' where uid='$uid' and gid='$gid' and glevel='$glevel'";
            //echo $sql;
            $cmd=mysqli_query($con,$sql);
            if($cmd)
            {
                
            $error=false;
            $msg="Success";
          
            }
        }
        else
        {
        
            $sql="insert into Resume_game values('$uid','$gid','$glevel','1','$time','$score')";
            $cmd=mysqli_query($con,$sql);
            
            $error=false;
            $msg="Success";
        
        }
         $sql="select * from Global_score where glevel='$glevel' and gid='$gid'";
            //echo $sql;
            
            $cmd=mysqli_query($con,$sql);
            $ans=mysqli_fetch_array($cmd);
           
            if(mysqli_num_rows($cmd)>0)
            {
             //echo $ans['6'];  
                if($score>=$ans['score'])
                {
                    
                    $sql2="select uname from Player_info where uid='$uid'";
                    $cmd2=mysqli_query($con,$sql2);
                    $ans2=mysqli_fetch_array($cmd2);
                    $uname=$ans2[0];
                
                
                    $sql="update Global_score set score='$score',uid='$uid',uname='$uname' where glevel='$glevel' and gid='$gid'";
                    $cmd=mysqli_query($con,$sql);
               
                }
                else
                {
                    $score=$ans['score'];
                    $user=$ans['uid'];
                    $name=$ans['uname'];
                }
            }
            else
            {
                $uid=$_GET['uid'];
                $sql="select uname from Player_info where uid='$uid'";
                $cmd=mysqli_query($con,$sql);
                $ans=mysqli_fetch_array($cmd);
                $uname=$ans[0];
                //echo $uname."Hello world".mysqli_num_rows($cmd);
                
                $sql="insert into Global_score values('$uid','$gid','$glevel','$uname','','$time','$score')";
                $cmd=mysqli_query($con,$sql);
               
            }
           
            
            array_push($result,array("error"=>$error,"msg"=>$msg,"user"=>$user,"hsname"=>$name,"score"=>$score));
             print json_encode(array("result"=>$result));
	
	mysqli_close($con);
}
?>

