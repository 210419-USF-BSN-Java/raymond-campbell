<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
    .footer {
       position: fixed;
       left: 0;
       bottom: 0;
       width: 100%;
       padding: 15px 25px;
       background-color: rgb(172, 162, 162);
       color: white;
       text-align: center;
    }
    </style>
    <title>JSON data to HTML Table using Ajax Jquery getJSOn method</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>

    </head>
<body>

    <div id="myData"></div>
    <script>
        
        $.getJSON(sessionStorage.getItem("PendingList") , function(data) {
        var tbl_body = "";
        var odd_even = false;
        $.each(data, function() {
            var tbl_row = "";
            $.each(this, function(k , v) {
                tbl_row += "<td>"+v+"</td>";
        });
        tbl_body += "<tr class=\""+( odd_even ? "odd" : "even")+"\">"+tbl_row+"</tr>";
        odd_even = !odd_even;               
    });
    $("#target_table_id tbody").html(tbl_body);
}); 
    </script>

    <div class="footer">
		<a href="employeeHome.html">Employee Home Page</a>
	  </div>
</body>
</html>