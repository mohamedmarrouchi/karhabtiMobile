<?php
require_once('connect.php');
$login=$_GET['username'];
$password=$_GET['password'];

$sql = mysqli_query($conn,"SELECT * FROM pidev WHERE username='".$login. "' and password = '".$password."'");



if ($result=mysql_query($conn,$sql))
  {
  // Return the number of rows in result set
  $count=mysqli_num_rows($result);
	if($count==0) {
		echo "Invalid Username or Password!";
	} else {
		echo "You are successfully authenticated!";
	}
  // Free result set
  mysqli_free_result($result);
  }
mysqli_close($conn);
?>