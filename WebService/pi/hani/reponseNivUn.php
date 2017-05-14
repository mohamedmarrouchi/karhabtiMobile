<?php
require_once('connect.php');
$id=$_GET['id'];
$sql = "SELECT 	responseforja FROM mes_tests WHERE id=$id";

if (mysqli_query($conn, $sql)) {
    echo "ok";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);