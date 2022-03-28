<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Using IoC Container</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container mt-4">
		
		<h1>Using IoC Container</h1>
		
		<h3>Add New Course</h3>		
		
		<div class="row">
			
			<div class="col-4">
				
				<c:url var="save" value="/courses"></c:url>
				<form action="${save}" method="post">
					
					<div class="mb-3">
						<label class="form-label">Name</label>
						<input type="text" name="name" placeholder="Enter Course Name" required="required" class="form-control" />
					</div>				
				
					<div class="mb-3">
						<label class="form-label">Duration</label>
						<input type="number" name="duration" placeholder="Enter Course Duration" required="required" class="form-control" />
					</div>				

					<div class="mb-3">
						<label class="form-label">Fees</label>
						<input type="number" name="fees" placeholder="Enter Course Fees" required="required" class="form-control" />
					</div>				

					<div class="mb-3">
						<label class="form-label">Description</label>
						<textarea rows="4" name="description" cols="40" class="form-control"></textarea>
					</div>	
					
					<input type="submit" value="Save Course" class="btn btn-primary" />			
				
				</form>
			
			</div>
		
		</div>
	
	</div>

</body>
</html>