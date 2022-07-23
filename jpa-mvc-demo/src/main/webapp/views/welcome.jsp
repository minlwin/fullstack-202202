<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB JPA</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
		<h1>Hello JPA From Web MVC</h1>
		
		<div class="mt-4">
		
			<table class="table table-striped">
				
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Burmese</th>
						<th>Type</th>
					</tr>
				</thead>
				
				<tbody>
				
					<c:forEach var="d" items="${ divisions }">
						
						<tr>
							<td>${ d.id }</td>
							<td>${ d.name }</td>
							<td>${ d.burmeseName }</td>
							<td>${ d.type }</td>
						</tr>
					
					</c:forEach>
					
				</tbody>
			
			</table>
		
		</div>
		
	</div>


</body>
</html>