<%@page import="com.UserManage.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/user.js"></script>
	</head>

	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<h1>User Management </h1>
		
			<form id="formUser" name="formUser">
			
 				User Name : 
 				<input id="name" name="name" type="text" 
 				class="form-control form-control-sm"> <br>
			    
			    User Age : 
 				<input id="age" name="age" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				User Address : 
 				<input id="address" name="address" type="text" 
				class="form-control form-control-sm"><br>
 				
 				User Email : 
 				<input id="email" name="email" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary">
 				<input type="hidden" id="hidUserIDSave" 
				name="hidUserIDSave" value="">
				
			</form>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divUserGrid">
 		<%
 		User usrObj = new User(); 
 		 		out.print(usrObj.readUser());
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>
