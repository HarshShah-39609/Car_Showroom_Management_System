  <!doctype html>
  
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Car Details</title>
    </head>
       <body 		background-color:#ff7f50;>
	<h1><center>Car Details</center></h1><br><br>
	<?php
			$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
        	$db=mysqli_select_db($con,"id16370078_projectcsms");


      //execute the SQL query and return records
      $result = mysqli_query($con,"SELECT * FROM Carmodel");
      ?>
      <table border="1" style= "background-color: #fff; color: #000000; margin-top:100px;" >
      <thead>
        <tr>
          <th>CarModel_id</th>
          <th>CarModel_Name</th>
		  <th>CarModel_FuelType</th>
          <th>CarModel_Color</th>          
          <th>CarModel_Price</th>
          <th>CarModel_Warrenty</th>
          <th>CarModel_EngineType</th>
          <th>CarModel_Avarage</th>
          <th>CarModel_Seat</th>
          <th>CarModel_MaxPower</th>
          <th>CarModel_Torque</th>
        </tr>
      </thead>
      <tbody>
        <?php
          while( $row = mysqli_fetch_row( $result ) ){
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
              <td>$row[8]</td>
              <td>$row[9]</td>
              <td>$row[10]</td>
            </tr>\n";
          }
        ?>
       
      </tbody>
    </table>
    
    </body>
    </html>