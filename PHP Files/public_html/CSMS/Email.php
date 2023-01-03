<?php
    $to="harshshah39609@gmail.com";
    $subject="Testing";
    $body="Testing Email For System";
    $from="projectcsms.000webhostapp.com";
    $header="From:$from";

    mail($to,$subject,$body,$header);
    
        echo "Sucessfull";
    
?>