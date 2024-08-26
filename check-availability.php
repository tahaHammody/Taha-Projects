<?php

// Database connection parameters
$servername = "localhost";
$username = "root";
$password = ""; // Leave blank if no password is set
$dbname = "211941653_322753203";

// Create a connection to the database
$conn = new mysqli($servername, $username, $password, $dbname);

// Check the connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
if(!empty($_POST["email"])){
    $query = "SELECT * FROM users WHERE email='" . $_POST["email"] . "'";
    $result = $conn->query($query);

    if($result->num_rows > 0){
        echo "<span style='color:red'> Sorry user already exist . </span>";
        echo "<script>$('#submit').prop('disabled',true);</script>";
    }
    else if(!filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)){
        echo "<span style='color:red'> Email is not written correctly . </span>";
        echo "<script>$('#submit').prop('disabled',true);</script>";
    }
    else{
        echo "<span style='color:green'> User available for registration . </span>";
        echo "<script>$('#submit').prop('disabled',false);</script>";
    }
}


?>