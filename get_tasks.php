<?php
// Check if the list_id is provided in the URL
if (!isset($_GET['list_id'])) {
    // Redirect or send an error response if list_id is not provided
    header("HTTP/1.1 400 Bad Request");
    exit();
}

$list_id = $_GET['list_id'];

// Database connection parameters (Update with your actual credentials)
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

// Fetch tasks from the database for the specific list using list_id
$query = "SELECT * FROM tasks WHERE list_id = $list_id";
$result = $conn->query($query);
$tasks = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $tasks[] = $row;
    }
}

// Close the connection
$conn->close();

// Generate the HTML for the tasks table
ob_start();
foreach ($tasks as $task) {
    echo '<tr class="home-page-list-rows">';
    echo '<td>' . $task['title'] . '</td>';
    echo '<td>' . $task['date'] . '</td>';
    echo '<td>' . $task['responsible_user'] . '</td>';
    echo '<td> <input type="checkbox" class="checks" id="check' . $task['task_id'] . '" value="checked" ' . ($task['done'] ? 'checked' : '') . '><br></td>';
    echo '</tr>';
}
$response = ob_get_clean();

// Send the HTML response
echo $response;
?>
