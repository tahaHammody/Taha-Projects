<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div id="login-form">
        <h1>Reset Password</h1>
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

        // Define variable to store error message
        $emailErr = "";

        // Check if the form is submitted
        if ($_SERVER["REQUEST_METHOD"] === "POST") {
            // Validate email
            if (empty($_POST["email"])) {
                $emailErr = "Email is required";
            } else {
                $email = $_POST["email"];
                // Additional email validation logic can be added here
            }

            // Check if there are no validation errors
            if (empty($emailErr)) {
                // Generate a new token for password reset
                $token = bin2hex(random_bytes(32));

                // Calculate the expiration date (e.g., 1 hour from now)
                $expiration = date('Y-m-d H:i:s', strtotime('+1 hour'));

                // Insert the token into the database
                $query = "INSERT INTO password_reset_tokens (email, token, expiration) VALUES ('$email', '$token', '$expiration')";
                if ($conn->query($query) === TRUE) {
                    // Simulate sending an email with the reset link (in a real scenario, you would send an email with the link)
                    $resetLink = "http://localhost/HW2/reset_password_page.php?token=" . $token;

                    // Display a message to the user with the reset link (replace with your message)
                    echo "Password reset link: <a href='$resetLink'>$resetLink</a>";
                    echo "<p>Please click the link above to reset your password.</p>";
                } else {
                    echo "Error creating token: " . $conn->error;
                }
            }
        }
        ?>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email address" required>
            <span class="error"><?php echo $emailErr; ?></span>

            <input type="submit" id="submit" value="Reset">
            <span class="error"></span>
        </form>
    </div>
</body>
</html>
