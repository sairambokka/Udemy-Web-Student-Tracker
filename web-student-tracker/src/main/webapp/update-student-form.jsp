<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
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
		<h2>Update Student</h2>
		
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
			
			<table>
				<tr>
					<td><label>First Name</label></td>
					<td><input type="text" name="firstName" value="${THE_STUDENT.firstName}" /></td>
				</tr>
				
				<tr>
					<td><label>Last Name</label></td>
					<td><input type="text" name="lastName" value="${THE_STUDENT.lastName}" /></td>
				</tr>
				
				
				<tr>
					<td><label>Email</label></td>
					<td><input type="text" name="email" value="${THE_STUDENT.email}" /></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Update Data" /></td>
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