  <!doctype html>
  
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Car Bookings</title>
    </head>
       <body 		background-color:#ff7f50;>
	<h1><center>Booked Car Details</center></h1><br><br>
      <?php
			$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
        	$db=mysqli_select_db($con,"id16370078_projectcsms");


      //execute the SQL query and return records
      
      $result = mysqli_query($con,"SELECT * FROM Book");
      ?>
      <table border="1" style= "background-color: #fff; color: #000000; margin: 0 auto;" >
      <thead>
        <tr>
          <th>Booking_id</th>
          <th>Book_Type</th>
		  <th>Book_Date</th>
          <th>Book_Time</th>
          <th>Book_Status</th>
          <th>Customer_id</th>
          <th>Showroom_id</th>
          <th>CarModel_id</th>
        </tr>
      </thead>
      <tbody>
        <?php
          while( $row = mysqli_fetch_row($result) ){
            echo
            "<tr>
              <td>$row[0]</td>
              <td>$row[1]</td>
              <td>$row[2]</td>
              <td>$row[3]</td>
              <td>$row[4]</td>
              <td>$row[5]</td>
              <td>$row[6]</td>
              <td>$row[7]</td>
            </tr>\n";
          }
        ?>
      </tbody>
    </table>
    <form style="text-align:center;margin-top:100px;" class="form-inline" method="POST" action="UpdateBooking.php">
        <h1><center>Update Booking</center></h1><br>
        <label for="id">Booking Id:</label>
				<input type="text" name="id" Value=""><br>
			<label for="name">Book Status:</label>
				<input type="text" name="status" value=""><br>
				<input type="submit">
    </from>
    </body>
    </html>