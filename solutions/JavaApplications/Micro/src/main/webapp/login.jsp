<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login - MyCart</title>
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
	<div class="container-fluid back-img ">
		<div class="row ">
			<div class="col-md-6 offset-md-3">
				<div class="card mt-3 ">
					<div class="card-header custom-bg text-white text-center">
						<h4>Login Here</h4>

					</div>

					<div class="card-body">
					
					<%@include file="components/message.jsp"%>

						<form action="Login_servlet" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input name="email"
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter email">
								<small id="emailHelp" class="form-text text-muted">We'll
									never share your email with anyone else.</small>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input name="password"
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password">
							</div>
							
							<a href="resgister.jsp" class="text-center d-block mb-2">If not register click here</a>
							
						<div class="conatiner text-center">
							
							<button type="submit" class="btn btn-primary custom-bg border-0">Submit</button>
							<button type="reset" class="btn btn-primary custom-bg border-0">Reset</button>
						</div>
						</form>

					</div>

		
				</div>
			</div>
		</div>
	</div>
</body>
</html>