<?php
session_start();

// Check if the user is not logged in
if (!isset($_SESSION['user_email'])) {
    // Redirect the user to the login page
    header("Location: login.php");
    exit();
}

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

// Fetch lists from the database for the logged-in user
$user_email = $_SESSION['user_email'];
$query = "SELECT * FROM lists WHERE owner_email = '$user_email' OR shared_users LIKE '%$user_email%'";
$result = $conn->query($query);
$lists = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $lists[] = $row;
    }
}

// Fetch all users' emails and names from the database
$query = "SELECT email, fname FROM users";
$result = $conn->query($query);
$users_data = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $users_data[$row['email']] = $row['fname'];
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery UI Autocomplete CSS -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script language="JavaScript" type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- jQuery UI Autocomplete JavaScript -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
    <div id="Lists-of-tasks">
        <h1 id="title-of-home-page">Table of lists</h1>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addListModal">Add New List</button>
        <table id="Lists" class="table table-striped">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Create Date</th>
                    <th>Users</th>
                </tr>
            </thead>
            <tbody>
                <?php
                foreach ($lists as $list) {
                    // Convert the emails to first names and display them in the table
                    $users = explode(",", $list['shared_users']);
                    $user_names = array_map(function ($email) use ($users_data) {
                        return isset($users_data[$email]) ? $users_data[$email] : $email;
                    }, $users);

                    echo '<tr class="home-page-list-rows">';
                    echo '<td><a href="task_list.php?list_id=' . $list['list_id'] . '">' . $list['title'] . '</a></td>';
                    echo '<td>' . $list['create_date'] . '</td>';
                    echo '<td>' . implode(", ", $user_names) . '</td>';
                    echo '</tr>';
                }
                ?>
            </tbody>
        </table>
    </div>

    <!-- Add New List Modal -->
    <div class="modal fade" id="addListModal" tabindex="-1" role="dialog" aria-labelledby="addListModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addListModalLabel">Add New List</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
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
                        <!-- Optional: Display selected user's name (remove if not needed) -->
                        <div>
                            <strong>Selected User's Name:</strong>
                            <span id="selectedUserName"></span>
                        </div>
                        <!-- Optional: Hidden input field to store selected email (remove if not needed) -->
                        <input type="hidden" id="selectedUserEmail" name="selectedUserEmail">
                        <button type="submit" class="btn btn-primary">Create List</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            // Initialize the users' data array for autocomplete
            var usersData = <?php echo json_encode($users_data); ?>;

            // Enable autocomplete for listUsers input field
            $("#listUsers").autocomplete({
                source: Object.keys(usersData),
                select: function(event, ui) {
                    // Get the selected email from the autocomplete
                    var selectedEmail = ui.item.value;

                    // Display the selected user's name in a separate div (optional)
                    $("#selectedUserName").text(usersData[selectedEmail]);

                    // Add the selected email to a hidden input field (optional)
                    $("#selectedUserEmail").val(selectedEmail);
                }
            });

            // Clear the form when the modal is hidden
            $("#addListModal").on("hidden.bs.modal", function() {
                $("#listTitle").val("");
                $("#listUsers").val("");
                $("#selectedUserName").text("");
                $("#selectedUserEmail").val("");
            });
        });
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
