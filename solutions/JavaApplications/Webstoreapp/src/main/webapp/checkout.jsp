<%

User user = (User) session.getAttribute("current-user");
if (user == null) {  

	session.setAttribute("message", "You are not logged in !! Login First to Access Checkout Page ");
	response.sendRedirect("login.jsp");
	return;

}



%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Page</title>
<%@include file="components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>

	<div class="container ">
		<div class="row mt-5">
			<div class="col-md-6">
				<!-- card -->
				<div class="card">
					<div class="card-body">
						<h3 class="text-center mb-3">Your selected items</h3>
						<div class="cart-body"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<!-- form details -->

				<div class="card">
					<div class="card-body">
						<h3 class="text-center mb-3">Your Datails for Order</h3>
						<form action="">
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input value="<%=user.getUserEmail() %>"
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter email">
								<small id="emailHelp" class="form-text text-muted">We'll
									never share your email with anyone else.</small>
							</div>
							<div class="form-group">
								<label for="name">Your Name</label> <input value="<%=user.getUserName() %>" type="text"
									class="form-control" id="name" aria-describedby="emailHelp"
									placeholder="Enter Name">
							</div>
							<div class="form-group">
								<label for="name">Your Contact</label> <input value="<%=user.getUserPhone() %>" type="text"
									class="form-control" id="name" aria-describedby="emailHelp"
									placeholder="Enter Contact">
							</div>
							<div class="form-group">
								<label for="exampleFormControlTextarea1">Your Shipping
									Address</label> 
								<textarea class="form-control" id="exampleFormControlTextarea1"
									placeholder="Enter Your Address" rows="3"><%=user.getUserAddress() %></textarea>
							</div>
							<div class="form-check text-left">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">Check me out</label>
							</div>
							<div class="text-right">
								<button type="submit" class="btn btn-success ">Order Now</button>
								<button type="submit" class="btn btn-primary ">Continue Shopping</button>
							</div>
						</form>

					</div>
				</div>

			</div>
		</div>
	</div>


	<%@include file="components/comman_model.jsp"%>
</body>
</html>