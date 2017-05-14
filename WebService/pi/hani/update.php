<?php
require_once('connect.php');
$id=$_GET['id'];
$niveau=$_GET['niveau'];
$image=$_GET['image'];
$question=$_GET['question'];
$reponse=$_GET['reponse'];
$choix1=$_GET['choix1'];
$choix2=$_GET['choix2'];
$choix3=$_GET['choix3'];
$sql = "UPDATE `mes_tests` SET `niveau`='$niveau',`image`='$image',`question`='$question',`reponse`='$reponse',`choix1`='$choix1`',`choix2`='$choix2`',`choix3`='$choix3`' WHERE id='$id'";
if (mysqli_query($conn, $sql)) {
    echo "ok";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);