<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['task_id']) && isset($_POST['done'])) {
    $task_id = intval($_POST['task_id']);
    $done = intval($_POST['done']);

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "211941653_322753203";

    $conn = new mysqli($servername, $username, $password, $dbname);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Use prepared statements to avoid SQL injection
    $stmt = $conn->prepare("UPDATE tasks SET done = ? WHERE task_id = ?");
    $stmt->bind_param("ii", $done, $task_id);
    if ($stmt->execute()) {
        echo "success";
    } else {
        echo "error";
    }

    $stmt->close();
    $conn->close();
} else {
    echo "Invalid request or missing parameters";
}
?>
