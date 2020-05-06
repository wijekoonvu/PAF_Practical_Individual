<%@page import="model.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Doctor Management</title>
		

		
		
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.4.1.min.js"></script>
		<script src="Components/doctor.js"></script>
	</head>
	
	

<body>
	<nav class="navbar navbar-expand-sm bg-danger navbar-dark">
  <ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="#">Doctor Management</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Home</a>
    </li>
  </ul>
</nav>
	<br>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					
					<h1>Add Doctor</h1>
					<form id="formDOC" name="formDOC">
 					
 					First Name:
 					<input id="firstname" name="firstname" type="text" class="form-control form-control-sm"> <br> 
 					
 					Last Name:
 					<input id="lastname" name="lastname" type="text" class="form-control form-control-sm"> <br> 
 					
 					Email:
 					<input id="email" name="email" type="text" class="form-control form-control-sm"> <br> 
 					
 					password
 					<input id="password" name="password" type="text" class="form-control form-control-sm"> <br>
 					
 					phone number
 					<input id="phonenumber" name="phonenumber" type="text" class="form-control form-control-sm"> <br>
 					
 					specalization
 					<input id="specalization" name="specalization" type="text" class="form-control form-control-sm"> <br>
 					
 					
 					charges
 					<input id="charges" name="charges" type="text" class="form-control form-control-sm"> <br>
 					
 					 Doctor type
 					<input id="type" name="type" type="text" class="form-control form-control-sm"> <br>
 					
 					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" style="width:250px">
 					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
					</form>
					<br>
					
					
					<br>
					<br>
				</div>	
			<div class="col-md-9">
					<h3 align="center">Doctor Details</h3>
					<br>
						<div id="divItemsGrid1">
							 <%
							 Doctor DocObj = new Doctor();
							 out.print(DocObj.viewDoctorDetails());
							 %>
							</div>
							<br>
						<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
				</div>
			</div>
		</div>
</body>
</html>