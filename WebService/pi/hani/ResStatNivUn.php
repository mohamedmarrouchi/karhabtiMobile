
<?php
require_once('connect.php');
$id=$_GET['id'];
$sql = "select * from resultat where id=$id";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
		$mydata = $json->addChild('resultat');
       $mydata->addChild('reponse_succes',$row['reponse_succes']);
	   $mydata->addChild('reponse_echec',$row['reponse_echec']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>