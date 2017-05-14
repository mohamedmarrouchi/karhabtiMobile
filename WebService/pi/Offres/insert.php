<?php
require_once('connect.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/pi/Offres/images/');
$Imagecode=$_POST['Image']; // parameter
$img=base64_decode($Imagecode);
$uid=uniqid();
$file = UPLOAD_DIR . $uid . '.jpg';
$success = file_put_contents($file, $img);
$i = $uid.'.jpg';
$nom_offre=$_POST['nom_offre'];
$description=$_POST['description'];
$prixinit=$_POST['prixinit'];
$taux_remise=$_POST['taux_remise'];
$sql = "INSERT INTO offre ( nom_offre, description,photo,ptvente_id_id,etat,membre_id_id,prixinit,taux_remise)
VALUES ( '$nom_offre','$description','$i',2,1,1,$prixinit,$taux_remise)";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>
