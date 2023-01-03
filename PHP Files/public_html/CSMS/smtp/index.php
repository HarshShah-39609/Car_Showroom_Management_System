<?php
include('smtp/PHPMailerAutoload.php');

$result = array();

$to = trim($_GET['email']);
$msg='Thanks for contacting us';
$otp = rand(1000,9999);
$purpose = trim($_GET['purpose']);
$returnmsg = ''.$otp;
$subject = 'No Reply';

switch (strtolower($purpose)){
    case 'forgotpwd':
        $subject = 'Verification';
        $msg = $otp.' is your one time password (OTP) for changing password for LUXURY AUTO';
        $returnmsg = ''.$otp;
        break;
    case 'newcar':
        $subject = 'New Car Acknowledgement';
        $msg = 'Your booking of new car is registered suuccessfully.';
        $returnmsg = 'new car booked';
        break;
    case 'testcar':
        $subject = 'Test Car Acknowledgement';
        $msg = 'Your booking of test drive is registered suuccessfully.';
        $returnmsg = 'test drive booked';;
        break;
    case 'service': 
        $subject = 'Service Acknowledgement';
        $msg = 'Your booking of service car is registered suuccessfully.';
        $returnmsg ='Service Booked';
        break;
    case 'cnewcar':
        $subject = 'Cancel Acknowledgement';
        $msg = 'Your booking of new car has been canceled';
        $returnmsg = 'Booking Canceled';
        break;
    case 'ctestcar':
        $subject = 'Cancel Acknowledgement';
        $msg = 'Your booking of test car has been canceled';
        $returnmsg = 'Booking Canceled';
        break;
    case 'cservice':
        $subject = 'Cancel Acknowledgement';
        $msg = 'Your booking of service car has been canceled';
        $returnmsg = 'Booking Canceled';
        break;
    default:
        
}
smtp_mailer($to,$subject,$msg);






function smtp_mailer($to,$subject, $msg){
	$mail = new PHPMailer(); 
	$mail->SMTPDebug  = 0;
	$mail->IsSMTP(); 
	$mail->SMTPAuth = true; 
	$mail->SMTPSecure = 'tls'; 
	$mail->Host = "smtp.gmail.com";
	$mail->Port = 587; 
	$mail->IsHTML(true);
	$mail->CharSet = 'UTF-8';
	$mail->Username = "projectcsms10@gmail.com";
	$mail->Password = "Harshshah20";
	$mail->SetFrom("projectcsms10@gmail.com","LUXURY AUTO");
	$mail->Subject = $subject;
	$mail->Body =$msg;
	$mail->AddAddress($to);
	$mail->SMTPOptions=array('ssl'=>array(
		'verify_peer'=>false,
		'verify_peer_name'=>false,
		'allow_self_signed'=>false
	));
	if(!$mail->Send()){
		//echo $mail->ErrorInfo;
	}else{
	    
        $error=false;
            $msg="Success";
            array_push($GLOBALS['result'],array("error"=>$error,"msg"=>$GLOBALS['returnmsg']));
            print json_encode(array("result"=>$GLOBALS['result']));	    
		//return 'Sent';
	}
}
?>