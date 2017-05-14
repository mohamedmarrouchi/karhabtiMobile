<?php
require_once('connect.php');



$sql = "SELECT * FROM mes_tests where niveau = 1";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
		$mydata = $json->addChild('mes_tests');
       $mydata->addChild('id',$row['id']);
	   $mydata->addChild('image',$row['image']);
        $mydata->addChild('question',$row['question']);
        $mydata->addChild('choix1',$row['choix1']);
		$mydata->addChild('choix2',$row['choix2']);
		$mydata->addChild('choix3',$row['choix3']);
		$mydata->addChild('reponse',$row['reponse']);
		$mydata->addChild('niveau',$row['niveau']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>