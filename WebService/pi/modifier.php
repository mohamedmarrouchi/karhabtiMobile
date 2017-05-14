<?php

require_once('connect.php');
$Imagecode=$_POST['Image']; // parameter
$img=base64_decode($Imagecode);
$uid=uniqid();
$file = $_SERVER['DOCUMENT_ROOT'].'/pi/images/'. $uid . '.jpg';
$success = file_put_contents($file, $img);
$i = $uid.'.jpg';

$TITRE=$_POST['TITRE'];
$region=$_POST['region'];
$id=$_POST['id'];
$BOITE=$_POST['BOITE'];
$NOMBRE_DE_CYLINDRES=$_POST['NOMBRE_DE_CYLINDRES'];
$ENERGIE=$_POST['ENERGIE'];
$PUISSANCE_FISCALE=$_POST['PUISSANCE_FISCALE'];
$CYLINDREE=$_POST['CYLINDREE'];
$PRIX=$_POST['PRIX'];

$sql = "update annonces set TITRE = '".$TITRE."' ,region='".$region."', BOITE='".$BOITE."', NOMBRE_DE_CYLINDRES='".$NOMBRE_DE_CYLINDRES."', ENERGIE='".$ENERGIE."', CYLINDREE='".$CYLINDREE."', PRIX='".$PRIX."',image='".$i."' where id = '".$id."'";


if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>
