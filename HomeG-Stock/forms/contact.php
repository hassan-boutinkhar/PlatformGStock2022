
<?php
//get data from form  
$name = $_POST['name'];
$email= $_POST['email'];
$message= $_POST['message'];
$to = "hassanboutinkhar15@gmail.com";
$subject = $_POST['subject'];
$txt ="Name = ". $name . "\r\n  Email = " . $email . "\r\n Message =" . $message;
$headers = "From: hassanboutinkhar15@gmail.com";
if($email!=NULL){
    mail($to,$subject,$txt,$headers);
}
//redirect
?>
