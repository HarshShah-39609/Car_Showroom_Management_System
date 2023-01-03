<?php
    if($_SERVER['REQUEST_METHOD']=='POST')
        
        $login=$_POST['Lid'];
        $password=$_POST['pass'];

        if(strcmp($login,"admin")==0)
        {
            
            if(strcmp($password,"admin")==0)
            {
                 header("Location:mainpage.php");
            }
            else
            {
                echo "Password Is Incorrect";
            }
        }
        else
        {
            echo "User Not Registered";
        }
?>