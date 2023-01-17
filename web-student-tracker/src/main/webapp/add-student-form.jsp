<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>


<div id="wrapper">
	<div id="header">
		<h1>Foo Bar University</h1>
	</div>
</div>

<div id="container">
	<div id="content">
		<h2>Add Student</h2>
		
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tr>
					<td><label>First Name</label></td>
					<td><input type="text" name="firstName" /></td>
				</tr>
				
				<tr>
					<td><label>Last Name</label></td>
					<td><input type="text" name="lastName" /></td>
				</tr>
				
				
				<tr>
					<td><label>Email</label></td>
					<td><input type="text" name="email" /></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Add Data" /></td>
				</tr>
				
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
		<a href="StudentControllerServlet">Go back to the List</a>
		</p>
	</div>
</div>

</body>
</html>