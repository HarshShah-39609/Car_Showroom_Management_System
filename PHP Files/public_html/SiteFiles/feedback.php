 <!doctype html>
  
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Feedback</title>
    </head>
       <body background='finalp.png'>
	<h1><center>FeedBacks</center></h1><br><br>
      <?php
			$con = mysqli_connect("localhost","id16370078_harshshah","Csms#0123456789");
	
        	$db=mysqli_select_db($con,"id16370078_projectcsms");


      //execute the SQL query and return records
      $result = mysqli_query($con,"SELECT * FROM Feedback");
      ?>
      <table border="1" style= "background-color: #fff; color: #000000; margin: 0 auto;" >
      <thead>
        <tr>
          <th>FeedBack_id</th>
          <th>FeedBack</th>
        </tr>
      </thead>
      <tbody>
        <?php
          while( $row = mysqli_fetch_row( $result ) ){
            echo
            "<tr>
              <td>$row[0]</td>
              <td>$row[1]</td>
            </tr>\n";
          }
        ?>
      </tbody>
    </table>
    </body>
    </html>