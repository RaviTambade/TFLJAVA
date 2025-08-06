<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
.back-img {
	background: url("img/peakpx.jpg");
	height: 100vh;
	width: 100%;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	
	<div class="container-fluid back-img">
		
		<div class="row mt-2">
			
			<div class="col-md-4 offset-md-4">
			
				<div class="card">
				
				<%@include file="components/message.jsp" %>
				
				
					<div class="card-bod px-5">
					
					<div class="container text-center">
					
					
					<img src="img/add-friend.png" style="max-width: 50px;" class="img-fluid mt-1">
					</div>

						<h3 class="text-center my-2">Register Here</h3>
						
						<form action="Register_servlet" method="post">

							<div class="form-group" >
								<label for="exampleInputEmail1">User Name</label> <input name="user_name"
									type="text" class="form-control" id="name"
									placeholder="Enter User Name">
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">User Email</label> <input name="user_email"
									type="email" class="form-control" id="email"
									placeholder="Enter Email">
							</div>

							<div class="form-group">
								<label for="password">User Password</label> <input name="user_password"
									type="password" class="form-control" id="password"
									placeholder="Enter Password">
							</div>

							<div class="form-group">
								<label for="phone">User Phone</label> <input name="user_phone" type="number"
									class="form-control" id="exampleInputEmail1"
									placeholder="Enter Phone no.">
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">User Address</label>
								<textarea name="user_address" style="height: 200px" class="form-control"
									placeholder="Enter Your Address"></textarea>
							</div>

							<div class="container text-center mb-2">
								<button class="btn btn-outline-success">Register</button>
								<button class="btn btn-outline-warning">Reset</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>