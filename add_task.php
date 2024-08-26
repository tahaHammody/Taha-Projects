<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['list_id'])) {
        $list_id = $_POST['list_id'];

        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "211941653_322753203";

        $conn = new mysqli($servername, $username, $password, $dbname);

        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $taskTitle = isset($_POST['taskTitle']) ? trim($_POST['taskTitle']) : '';
        $responsibleUser = isset($_POST['responsibleUser']) ? trim($_POST['responsibleUser']) : '';

        // Use prepared statements to avoid SQL injection
        $stmt = $conn->prepare("INSERT INTO tasks (list_id, title, Responsible_User) VALUES (?, ?, ?)");
        $stmt->bind_param("iss", $list_id, $taskTitle, $responsibleUser);
        if ($stmt->execute()) {
            // Return the task_id of the newly inserted task
            $task_id = $stmt->insert_id;
            echo json_encode(array('task_id' => $task_id));
        } else {
            echo "error";
        }

        $stmt->close();
        $conn->close();
    } else {
        echo "error";
    }
} else {
    echo "error";
}
?>
