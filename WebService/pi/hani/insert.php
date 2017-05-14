
<?php
require_once('connect.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/codename/pi/images/');
$niveau=$_POST['niveau'];
$image=$_POST['image'];
$question=$_POST['question'];
$reponse=$_POST['reponse'];
$choix1=$_POST['choix1'];
$choix2=$_POST['choix2'];
$choix3=$_POST['choix3'];
$img=base64_decode($image);
$uid=uniqid();
$file = $uid . '.jpg';
$sql = "INSERT INTO mes_tests ( niveau,image,question,reponse,choix1,choix2,choix3)
VALUES ( '$niveau','$file','$question','$reponse','$choix1','$choix2','$choix3')";

if (mysqli_query($conn, $sql)) {
	file_put_contents($file, $img);
    echo "ok";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
