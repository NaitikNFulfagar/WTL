<?php
include("connect.php");

// Check if ID is set and not empty
if(isset($_GET['id']) && !empty($_GET['id'])) {
    // Escape the ID to prevent SQL injection
    $id = mysqli_real_escape_string($con, $_GET['id']);
    
    // Query to delete the item with the given ID
    $sql = "DELETE FROM grocerytb WHERE Id = '$id'";
    
    // Execute the query
    if(mysqli_query($con, $sql)) {
        echo "Record deleted successfully";
    } else {
        echo "Error deleting record: " . mysqli_error($con);
    }
} else {
    echo "ID not provided";
}

// Close the connection
mysqli_close($con);
?>
