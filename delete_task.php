<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['task_id'])) {
    $task_id = intval($_POST['task_id']);

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "211941653_322753203";

    $conn = new mysqli($servername, $username, $password, $dbname);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Use prepared statements to avoid SQL injection
    $stmt = $conn->prepare("DELETE FROM tasks WHERE task_id = ?");
    $stmt->bind_param("i", $task_id);
    if ($stmt->execute()) {
        echo "success"; // Send a success message back to the client
    } else {
        echo "Error deleting task: " . $conn->error;
    }

    $stmt->close();
    $conn->close();
} else {
    echo "Invalid request or missing task_id parameter";
}
?>