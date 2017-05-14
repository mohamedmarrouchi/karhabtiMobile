<?php
require_once('connect.php');



$sql = "SELECT * FROM offre where membre_id_id=1";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('offre');
        $mydata->addChild('id',$row['id']);
        $mydata->addChild('nom_offre',$row['nom_offre']);
        $mydata->addChild('description',$row['description']);
        $mydata->addChild('photo',$row['photo']);
		$mydata->addChild('ptvente',$row['ptvente_id_id']);
		$mydata->addChild('prixinit',$row['prixinit']);
		$mydata->addChild('taux_remise',$row['taux_remise']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>
