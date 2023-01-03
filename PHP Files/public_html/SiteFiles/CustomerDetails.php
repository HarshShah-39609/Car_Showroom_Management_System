  <!doctype html>
  
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Customer Details</title>
    </head>
       <body 		background-color:#ff7f50;>
	<h1><center>Customer Details</center></h1><br><br>
      <?php
			$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
        	$db=mysqli_select_db($con,"id16370078_projectcsms");


      //execute the SQL query and return records
      $result = mysqli_query($con,"SELECT * FROM Customer");
      ?>
      <table border="1" style= "background-color: #fff; color: #000000; margin: 0 auto;" >
      <thead>
        <tr>
          <th>Customer_id</th>
          <th>Customer_Name</th>
		  <th>Customer_Email</th>
          <th>Customer_Password</th>
          <th>Customer_Contact</th>
          <th>Customer_Address</th>
          <th>Customer_Gender</th>
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
            </tr>\n";
          }
        ?>
      </tbody>
    </table>
    </body>
    </html>