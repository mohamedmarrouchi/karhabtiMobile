<?php
require_once('connect.php');
$id=$_GET['id'];


$sql = "SELECT * FROM annonces where user = '".$id."'";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('annonces');
$mydata->addChild('id',$row['id']);
        $mydata->addChild('TITRE',$row['TITRE']);
$mydata->addChild('user',$row['user']);
        $mydata->addChild('region',$row['region']);
        $mydata->addChild('BOITE',$row['BOITE']);
        $mydata->addChild('NOMBRE_DE_CYLINDRES',$row['NOMBRE_DE_CYLINDRES']);
        $mydata->addChild('ENERGIE',$row['ENERGIE']);
        $mydata->addChild('image',$row['image']);

        $mydata->addChild('CYLINDREE',$row['CYLINDREE']);
        $mydata->addChild('PRIX',$row['PRIX']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>
