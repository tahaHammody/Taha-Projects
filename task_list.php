<?php

session_start();

if (!isset($_SESSION['user_email'])) {
    header("Location: login.php");
    exit();
}

if (!isset($_GET['list_id'])) {
    header("Location: home.php");
    exit();
}

$list_id = $_GET['list_id'];

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "211941653_322753203";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$query = "SELECT * FROM lists WHERE list_id = $list_id";
$result = $conn->query($query);

if ($result->num_rows > 0) {
    $list = $result->fetch_assoc();
} else {
    header("Location: home.php");
    exit();
}

$query = "SELECT * FROM tasks WHERE list_id = $list_id";
$result = $conn->query($query);
$tasks = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $tasks[] = $row;
    }
}

$shared_users = explode(",", $list['shared_users']);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List - <?php echo $list['title']; ?></title>
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="Expires" content="Sat, 1 Jan 2000 00:00:00 GMT">
    <link rel="stylesheet" type="text/css" href="style3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div id="tasks">
        <button id="home-button">
            <img id="home-img" src="https://icon-library.com/images/home-icon-white/home-icon-white-10.jpg" alt="buttonpng">
            <h1 id="homeText">home</h1>
        </button>
        <h1 id="title-of-task-list"><?php echo $list['title']; ?></h1>
        <form id="add-task-form" action="add_task.php?list_id=<?php echo $list_id; ?>" method="POST">
            <div class="form-group">
                <label for="taskTitle">Title</label>
                <input type="text" class="form-control" id="taskTitle" name="taskTitle" required>
            </div>
            <div class="form-group">
                <label for="responsibleUser">Responsible User</label>
                <select class="form-control" id="responsibleUser" name="responsibleUser">
                    <?php
                    foreach ($shared_users as $shared_user) {
                        echo '<option value="' . $shared_user . '">' . $shared_user . '</option>';
                    }
                    ?>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Task</button>
        </form>
        <div id="deleteConfirmation">
            <p>Are you sure you want to delete this task?</p>
            <button id="confirmDelete">Yes</button>
            <button id="cancelDelete">No</button>
        </div>
        <table id="Tasks">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Adding Date</th>
                    <th>Responsible User</th>
                    <th>Done</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <?php
                foreach ($tasks as $task) {
                    echo '<tr class="home-page-list-rows">';
                    echo '<td>' . $task['title'] . '</td>';
                    echo '<td>' . $task['date'] . '</td>';
                    echo '<td>' . $task['Responsible_User'] . '</td>';
                    echo '<td> <input type="checkbox" class="checks" data-task-id="' . $task['task_id'] . '" ' . ($task['done'] ? 'checked' : '') . '><br></td>';
                    echo '<td> <i class="delete-icon fa fa-trash" data-task-id="' . $task['task_id'] . '"></i></td>';
                    echo '</tr>';
                }
                ?>
            </tbody>
        </table>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
         function navigateToHome() {
            window.location.href = "home.php";
        }
        $(document).ready(function() {
            // Function to update task status using AJAX
            function updateTaskStatus(taskId, doneStatus) {
                $.ajax({
                    url: "update_task_status.php",
                    method: "POST",
                    data: {
                        task_id: taskId,
                        done: doneStatus
                    },
                    success: function(response) {
                        console.log("Task status updated successfully.");
                    },
                    error: function(xhr, status, error) {
                        console.error("Error updating task status:", error);
                    }
                });
            }

            // Function to add a new task using AJAX
            function addTask(title, responsibleUser) {
                var listId = <?php echo $list_id; ?>;
                $.ajax({
                    url: "add_task.php",
                    method: "POST",
                    data: {
                        list_id: listId,
                        taskTitle: title,
                        responsibleUser: responsibleUser
                    },
                    success: function(response) {
                        try {
                            var parsedResponse = JSON.parse(response);
                            if (parsedResponse && parsedResponse.task_id) {
                                var taskId = parsedResponse.task_id;
                                var newRow = '<tr class="home-page-list-rows">';
                                newRow += '<td>' + title + '</td>';
                                newRow += '<td>' + (new Date()).toISOString().slice(0, 10) + '</td>';
                                newRow += '<td>' + responsibleUser + '</td>';
                                newRow += '<td> <input type="checkbox" class="checks" data-task-id="' + taskId + '"></td>';
                                newRow += '<td> <i class="delete-icon fa fa-trash" data-task-id="' + taskId + '"></i></td>';
                                newRow += '</tr>';
                                $('#Tasks tbody').append(newRow);
                                $("#taskTitle").val("");
                            } else {
                                console.error("Error adding the task: Invalid response format.");
                            }
                        } catch (e) {
                            console.error("Error parsing the response: " + e);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("Error adding the task: " + error);
                    }
                });
            }

            // Submit form to add a new task
            $("#add-task-form").submit(function(event) {
                event.preventDefault();
                var title = $("#taskTitle").val();
                var responsibleUser = $("#responsibleUser").val();
                addTask(title, responsibleUser);
                $("#taskTitle").val("");
            });

            // Function to delete a task using AJAX
            function deleteTask(taskId) {
        $.ajax({
            url: "delete_task.php",
            method: "POST",
            data: {
                task_id: taskId
            },
            success: function(response) {
                if (response === "success") {
                    $('[data-task-id="' + taskId + '"]').closest("tr").remove();
                } else {
                    console.error("Error deleting the task.");
                }
            },
            error: function(xhr, status, error) {
                console.error("Error deleting the task:", error);
                console.log(xhr.responseText); // Log the response from the server
            }
        });
    }

            // Show delete confirmation modal
            function showDeleteConfirmation(taskId) {
                $("#deleteConfirmation").fadeIn();
                $("#confirmDelete").data("task-id", taskId);
            }

            // Hide delete confirmation modal
            function hideDeleteConfirmation() {
                $("#deleteConfirmation").fadeOut();
                $("#confirmDelete").data("task-id", "");
            }

            // Handle click on delete icons using event delegation
            $('#Tasks').on('click', '.delete-icon', function() {
                var taskId = $(this).data('task-id');
                showDeleteConfirmation(taskId);
            });

            // Handle click on cancel delete button
            $("#cancelDelete").click(function() {
                hideDeleteConfirmation();
            });

            // Handle click on confirm delete button
            $("#confirmDelete").click(function() {
                var taskId = $(this).data("task-id");
                if (taskId) {
                    deleteTask(taskId);
                }
                hideDeleteConfirmation();
            });
            $("#home-button").click(function() {
                navigateToHome();
            });
        });
    </script>
</body>
</html>
