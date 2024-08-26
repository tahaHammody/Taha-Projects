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

// Define variables for storing error messages
$firstNameErr = $lastNameErr = $emailErr = $passwordErr = $rePasswordErr = "";
$registrationErr = "";

// Define variables for storing form data
$firstName = $lastName = $email = $password = $repassword = "";

// Process form data upon submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate first name
    if (empty($_POST["first-name"])) {
        $firstNameErr = "First name is required";
    } else {
        $firstName = $_POST["first-name"];
    }

    // Validate last name
    if (empty($_POST["last-name"])) {
        $lastNameErr = "Last name is required";
    } else {
        $lastName = $_POST["last-name"];
    }

    // Validate email
    if (empty($_POST["email"])) {
        $emailErr = "Email is required";
    } else {
        $email = $_POST["email"];
        // Additional email validation logic can be added here
        
        // Sanitize the input
        $email = mysqli_real_escape_string($conn, $email);
    }

    // Validate password
    if (empty($_POST["password"])) {
        $passwordErr = "Password is required";
    } else {
        $password = $_POST["password"];
        // Additional password validation logic can be added here
    }

     // Validate re-entered password
     if (empty($_POST["repassword"])) {
        $rePasswordErr = "Re-enter Password is required";
    } else {
        $repassword = $_POST["repassword"];
        // Additional password validation logic can be added here
    }

    // Check if there are no validation errors
    if (empty($firstNameErr) && empty($lastNameErr) && empty($emailErr) && empty($passwordErr) && empty($rePasswordErr)) {
        // Check if the passwords match
        if ($repassword != $password) {
            $registrationErr = "Passwords do not match";
        } else {
            // Check if the email is already registered
            $query = "SELECT * FROM users WHERE email = '$email'";
            $result = $conn->query($query);

            if ($result->num_rows > 0) {
                // Email already exists, return an error message
                $registrationErr = "Email already exists. Please try another email.";
            } else {
                // Continue with the signup process

                // Hash the password
                $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

                // Insert the user into the database using prepared statements
                $query = "INSERT INTO Users (fname, lname, email, password) VALUES (?, ?, ?, ?)";
                $stmt = $conn->prepare($query);
                $stmt->bind_param("ssss", $firstName, $lastName, $email, $hashedPassword);

                if ($stmt->execute()) {
                    // Registration successful, redirect to login page
                    header("Location: login.php");
                    exit();
                } else {
                    $registrationErr = "Registration failed: " . $conn->error;
                }
            }
        }
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" type="text/css" href="style1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div id="registration-form">
        <h1>Register</h1>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <label for="first-name">First Name:</label>
            <span class="error"><?php echo $firstNameErr; ?></span>
            <input type="text" id="first-name" name="first-name" required>

            <label for="last-name">Last Name:</label>
            <span class="error"><?php echo $lastNameErr; ?></span>
            <input type="text" id="last-name" name="last-name" required>

            <label for="email">Email:</label>
            <span id="check-email" class="error"><?php echo $emailErr; ?></span>
            <input type="email" id="email" name="email" onInput="checkEmail()" required>

            <label for="password">Password:</label>
            <span class="error"><?php echo $passwordErr; ?></span>
            <input type="password" id="password" name="password" required>

            <label for="repassword">Re-enter Password:</label>
            <span class="error"><?php echo $rePasswordErr; ?></span>
            <input type="password" id="repassword" name="repassword" required>

            <input id="submit" type="submit" value="Register">
            <span class="error"><?php echo $registrationErr; ?></span>
        </form>
        <p>Already have an account? <a href="login.php">Login</a></p>
    </div>

    <script>
        function checkEmail() {
            jQuery.ajax({
                url: "check-availability.php",
                data: 'email=' + $("#email").val(),
                type: "POST",
                success: function (data) {
                    $("#check-email").html(data);
                },
                error: function () { }
            });
        }
    </script>
</body>
</html>
