<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

session_start();

// Check if the user is not logged in
if (!isset($_SESSION['user_email'])) {
    // Redirect the user to the login page
    header("Location: login.php");
    exit();
}

var_dump($_POST['listTitle']); // Check the value of listTitle
var_dump($_POST['listUsers']); // Check the value of listUsers

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] === "POST") {
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

    // Fetch the user's email from the session
    $user_email = $_SESSION['user_email'];

    // Get the list data from the form
    $list_title = $_POST['listTitle'];
    $shared_users = $_POST['listUsers'];

    // Insert the list into the lists table
    $query = "INSERT INTO lists (title, owner_email, shared_users) VALUES ('$list_title', '$user_email', '$shared_users')";
    $conn->query($query);

    // Redirect to the home page after adding the list
    header("Location: home.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add List</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div id="add-list-form">
        <h1 id="title-of-add-list">Add New List</h1>
        <form action="add_list.php" method="post">
            <div class="form-group">
                <label for="listTitle">Title</label>
                <input type="text" class="form-control" id="listTitle" name="listTitle" required>
            </div>
            <div class="form-group">
                <label for="listUsers">Users</label>
                <input type="text" class="form-control" id="listUsers" name="listUsers" required>
                <small class="form-text text-muted">Enter emails of users, separated by commas.</small>
            </div>
            <button type="submit" class="btn btn-primary">Create List</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
