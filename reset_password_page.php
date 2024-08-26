<!DOCTYPE html>
<html>
<head>
    <title>Reset Password Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div id="login-form">
        <h1>Reset Password Page</h1>
        <?php
        // Database connection parameters
        $servername = "localhost";
        $username = "root";
        $password = ""; // Leave blank if no password is set
        $dbname = "211941653_322753203"; // Replace with your actual database name

        // Create a connection to the database
        $conn = new mysqli($servername, $username, $password, $dbname);

        // Check the connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        // Check if the token is provided in the URL
        if (isset($_GET['token'])) {
            $token = $_GET['token'];

            // Retrieve the token from the database
            $query = "SELECT email, expiration FROM password_reset_tokens WHERE token = '$token'";
            $result = $conn->query($query);

            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();
                $email = $row['email'];
                $expiration = $row['expiration'];

                // Check if the token is not expired
                if (strtotime($expiration) > time()) {
                    // Handle password update form submission
                    if ($_SERVER["REQUEST_METHOD"] === "POST") {
                        $newPassword = $_POST["new_password"];

                        // Update the user's password in the database
                        $hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);
                        $updateQuery = "UPDATE users SET password = '$hashedPassword' WHERE email = '$email'";
                        if ($conn->query($updateQuery) === TRUE) {
                            // Delete the token from the database after password update
                            $deleteQuery = "DELETE FROM password_reset_tokens WHERE token = '$token'";
                            $conn->query($deleteQuery);

                            echo "<p>Password updated successfully.</p>";
                        } else {
                            echo "Error updating password: " . $conn->error;
                        }
                    } else {
                        // Display the password reset form
                        echo '<form action="reset_password_page.php?token=' . $token . '" method="post">';
                        echo '    <input type="hidden" name="email" value="' . $email . '">';
                        echo '    <label for="new_password">New Password:</label>';
                        echo '    <input type="password" id="new_password" name="new_password" required>';
                        echo '    <input type="submit" value="Reset Password">';
                        echo '</form>';
                    }
                } else {
                    echo "<p>Token has expired. Please request a new password reset link.</p>";
                }
            } else {
                echo "<p>Invalid or expired token. Please request a new password reset link.</p>";
            }
        } else {
            echo "<p>Token not provided. Please request a new password reset link.</p>";
        }
        ?>
    </div>
</body>
</html>
