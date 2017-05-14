<?php
require_once('connect.php');
$id=$_GET['id'];
$nom_offre=$_GET['nom_offre'];
$description=$_GET['description'];
$prixinit=$_GET['prixinit'];
$taux_remise=$_GET['taux_remise'];
$sql = "update offre set nom_offre='".$nom_offre."', description='".$description."',prixinit='".$prixinit."',taux_remise='".$taux_remise."' where id='".$id."'";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>