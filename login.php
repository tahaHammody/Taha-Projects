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

// Start a session
session_start();

// Define variables for storing error messages
$emailErr = $passwordErr = $loginErr = "";

// Define variables for storing form data
$email = $password = "";

// Process form data upon submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate email
    if (empty($_POST["email"])) {
        $emailErr = "Email is required";
    } else {
        $email = $_POST["email"];
    }

    // Validate password
    if (empty($_POST["password"])) {
        $passwordErr = "Password is required";
    } else {
        $password = $_POST["password"];
    }

    // Perform login logic
    if (empty($emailErr) && empty($passwordErr)) {
        // Fetch the user's hashed password from the database based on the email
        $query = "SELECT password FROM Users WHERE email = ?";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result) {
            // Check if any rows were returned
            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();
                $storedPasswordHash = $row["password"];

                // Verify the password
                if (password_verify($password, $storedPasswordHash)) {
                    // Store the user's email in the session
                    $_SESSION["user_email"] = $email;

                    // Check if "Remember Me" is checked
                    if (isset($_POST["remember"])) {
                        // Create a cookie to keep the user logged in for 30 days
                        setcookie("user_email", $email, time() + (30 * 24 * 60 * 60), "/");
                    }

                    // Redirect the user to the main page if the credentials are correct
                    header("Location: home.php");
                    exit();
                } else {
                    $loginErr = "Invalid email or password";
                }
            } else {
                $loginErr = "Invalid email or password";
            }
        } else {
            // Handle query error
            $loginErr = "Query error: " . $conn->error;
        }
    }
}
?>

<!DOCTYPE html>
<html>
<head>
  <title>Login Page</title>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <div id="login-form">
    <h1>Login</h1>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>
      <span class="error"><?php echo $emailErr; ?></span>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>
      <span class="error"><?php echo $passwordErr; ?></span>

      <label for="remember">Remember Me</label>
      <input type="checkbox" id="remember" name="remember">

      <input type="submit" id="submit" value="Login">
      <span class="error"><?php echo $loginErr; ?></span>
    </form>
    <p>Don't have an account yet? <a href="signup.php">Signup</a></p>
    <p>Forget Password? <a href="resetpassword.php">Reset Password </a></p>
  </div>
</body>
</html>
