<?php
function connect(){
    $conn = new mysqli("localhost","root","","animal");
    return $conn;
}
function getAnimal(){
    $conn = connect();
    $str = "select * from animals";

    $result = mysqli_query($conn,$str);
    return $result;
}