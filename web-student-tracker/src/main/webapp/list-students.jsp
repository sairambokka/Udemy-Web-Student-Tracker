<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Tracker App</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>



<body>

<div id="wrapper">
	<div id="header">
		<h1>Foo University</h1>
	</div>
</div>

<div id="container">
	<div id="content">
	
	<input type="button" value="Add Student" class="add-student-button" onclick="window.location.href='add-student-form.jsp'" />
	
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th> Email</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="student" items="${STUDENT_LIST}">
			
			<c:url var="tempLink" value="StudentControllerServlet" >
				<c:param name="command" value="LOAD" ></c:param>
				<c:param name="studentId" value="${student.id}"></c:param>
			</c:url>
			
			<c:url var="deleteLink" value="StudentControllerServlet" >
				<c:param name="command" value="DELETE" ></c:param>
				<c:param name="studentId" value="${student.id}"></c:param>
			</c:url>
			
			<tr>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.email}</td>
				<td><a href="${tempLink}">Update</a>
				 | 
				 <a href="${deleteLink}"
				 onclick="if(!(confirm('Are You sure you want to delete this student ?'))) return false">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		
	</div>
</div>

</body>
</html>