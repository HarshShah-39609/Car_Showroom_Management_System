  <!doctype html>
  
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Dealer Details</title>
      <style>
       	a:link{
                  background-color: #000080;
                  color: white;
                  padding: 14px 25px;
                 text-align: center;
                 text-decoration: none;
                  display: inline-block;
             }

        a:hover, a:active {
                    background-color: red;
            }
        a:visited{background-color:#7cfc00}
        </style>
    </head>
       <body 		background-color:#ff7f50;> 
	<h1><center>Dealer Details</center></h1><br><br>
      <?php
			$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
        	$db=mysqli_select_db($con,"id16370078_projectcsms");


      //execute the SQL query and return records
      $result = mysqli_query($con,"SELECT * FROM Dealer");
      ?>
      <a style="margin-left:400px;" href="adddealer.php" target="_self">Add Dealer</a>
      <table border="1" style= "background-color: #fff; color: #000000; margin: 0 auto; margin-top:50px;" >
      <thead>
        <tr>
          <th>Dealer_id</th>
          <th>Dealer_Name</th>
		  <th>Dealer_Password</th>
          <th>Dealer_Contact</th>
          <th>Dealer_Address</th>
          <th>Dealer_Email</th>
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
              
            </tr>\n";
          }
        ?>
      </tbody>
    </table>
    <form style="text-align:center;margin-top:100px;" class="form-inline" method="POST" action="deletedealer.php">
        <h1><center>Delete Dealer</center></h1><br>
        <label for="id">Dealer Id:</label>
				<input type="text" name="dealerid" Value=""><br>
			<label for="name">Dealer Name:</label>
				<input type="text" name="name" value=""><br>
				<input type="submit">
    </from>
    </body>
    </html>