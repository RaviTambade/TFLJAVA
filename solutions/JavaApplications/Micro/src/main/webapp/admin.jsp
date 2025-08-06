<%@page import="java.util.Map"%>
<%@page import="com.learn.mycart.helper.Helper"%>
<%@page import="com.learn.mycart.entites.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.learn.mycart.helper.FactoryProvider"%>
<%@page import="com.learn.mycart.dao.CategoryDao"%>
<%@page import="com.learn.mycart.entites.User"%>
<%
User user = (User) session.getAttribute("current-user");
if (user == null) {

	session.setAttribute("message", "You are not logged in !! Login First ");
	response.sendRedirect("login.jsp");
	return;

} else {
	if (user.getUserType().equals("normal")) {
		session.setAttribute("message", "You are not Admin !! Do not access this page");
		response.sendRedirect("login.jsp");
		return;
	}
}
%>

<%
CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
List<Category> list = cdao.getCategories();

//gettng count

 Map<String,Long> m = Helper.getCounts(FactoryProvider.getFactory());

%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">

<title>Admin Panel</title>

<%@include file="components/common_css_js.jsp"%>
<!-- <style type="text/css">
.back-img {
	background: url("img/list.png");
	height: 50vh;
	width: 100%;
	background-repeat: no-repeat;
	background-size: cover;
}

.card:hover {
	background-color: #cfcfd1;
}
</style> -->
</head>


<body>


	<%@include file="components/navbar.jsp"%>
	<div class="container admin">
		<h1 class="text-center mt-4 mb-4">WELCOME TO ADMIN PAGE</h1>

		<div class="container-fluid mt-3"></div>
		<%@include file="components/message.jsp"%>



		<div class="row mt-3">

			<!--first column  -->
			<div class="col-md-4">

				<div class="card">
					<div class="card-body text-center">
						<div class="container ">
							<img style="max-width: 120px;" class="img-fluid rounded-circle"
								alt="" src="img/man.png">
						</div>
						<h1><%=m.get("userCount") %></h1>
						<h1 class="text-uppercase text-muted">Users</h1>
					</div>
				</div>
			</div>

			<!--second column  -->
			<div class="col-md-4">

				<div class="card">
					<div class="card-body text-center">
						<div class="container ">
							<img style="max-width: 120px;" class="img-fluid rounded-circle"
								alt="" src="img/list.png">
						</div>
						<h1><%=list.size() %></h1>
						<h1 class="text-uppercase text-muted">Categories</h1>
					</div>
				</div>

			</div>

			<!--third column  -->
			<div class="col-md-4">

				<div class="card">
					<div class="card-body text-center">
						<div class="container ">
							<img style="max-width: 120px;" class="img-fluid rounded-circle"
								alt="" src="img/delivery-box.png">
						</div>
						<h1><%=m.get("productCount") %></h1>
						<h1 class="text-uppercase text-muted">Products</h1>
					</div>
				</div>

			</div>
		</div>

		<!--Second row  -->

		<div class="row mt-3">

			<!-- second row - first column  -->
			<div class="col-md-6">

				<div class="card" data-toggle="modal"
					data-target="#add-category-model">
					<div class="card-body text-center">
						<div class="container ">
							<img style="max-width: 120px;" class="img-fluid rounded-circle"
								alt="" src="img/mathematics.png">
						</div>
						<p>Click here to add new Category</p>
						<h1 class="text-uppercase text-muted">Add Category</h1>
					</div>
				</div>
			</div>

			<!--second row - second column  -->
			<div class="col-md-6">

				<div class="card" data-toggle="modal"
					data-target="#add-product-modal">
					<div class="card-body text-center">
						<div class="container ">
							<img style="max-width: 120px;" class="img-fluid rounded-circle"
								alt="" src="img/plus.png">
						</div>
						<p class="mt-2">Click here to add new Products</p>
						<h1 class="text-uppercase text-muted">Add Products</h1>
					</div>
				</div>

			</div>

		</div>
	</div>

	<!-- add category model -->


	<!-- Button trigger modal -->
	<!-- <button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#add-category-model">Launch demo modal</button> -->

	<!-- Modal -->
	<div class="modal fade" id="add-category-model" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">Fill Category
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<form action="Product_OperationServlet" method="post">

						<input type="hidden" name="operation" value="addcategory">

						<div class="form-group">
							<input type="text" class="form-control" name="carTitle"
								placeholder="Enter Category Title" required="required" />
						</div>
						<div class="form-group">
							<textarea class="form-control"
								placeholder="Enter Category Description" name="catDescription"
								required="required"></textarea>
						</div>

						<div class="modal-footer container text-center ">
							<button class="btn btn-outline-success">Add Category</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>


					</form>


				</div>

			</div>
		</div>
	</div>



	<!-- end of add category model -->

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<!-- start of Product model -->


	<!-- Button trigger modal -->

	<!-- Modal -->
	<div class="modal fade" id="add-product-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">Product Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<!-- start form -->

					<form action="Product_OperationServlet" method="post"
						enctype="multipart/form-data">

						<input type="hidden" name="operation" value="addproduct">

						<div class="form-group">
							<input type="text" class="form-control"
								placeholder="Enter Title of Product" name="pName"
								required="required" />
						</div>

						<!-- product description -->

						<div class="form-group">
							<textarea class="form-control"
								placeholder="Enter Product Description" name="pDesc"></textarea>
						</div>

						<!-- product price -->

						<div class="form-group">
							<input type="number" class="form-control"
								placeholder="Enter Price of Product" name="pPrice"
								required="required" />
						</div>

						<!-- product Discount -->

						<div class="form-group">
							<input type="number" class="form-control"
								placeholder="Enter Product Discount" name="pDiscount"
								required="required" />
						</div>

						<!-- product Quantity -->

						<div class="form-group">
							<input type="number" class="form-control"
								placeholder="Enter Product Quantity" name="pQuantity"
								required="required" />
						</div>

						<!-- product category -->

						<%-- 	<%
						CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
						List<Category> list = cdao.getCategories();
						%> --%>


						<div class="form-group">
							<select name="catId" class="form-control" id="">

								<%
								for (Category c : list) {
								%>

								<option value="<%=c.getCategoryId()%>"><%=c.getCategoryTitle()%></option>

								<%
								}
								%>
							</select>
						</div>

						<!-- product file -->

						<div class="form-group">
							<label for="pPic">Select Picture of Product</label> <input
								type="file" id="pPic" name="pPic" required="required" />
						</div>

						<!-- submit button -->

						<div class="modal-footer container text-center ">
							<button class="btn btn-outline-success">Add Products</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>


					</form>

					<!-- end form -->

				</div>

			</div>
		</div>
	</div>


	<%@include file="components/comman_model.jsp"%>
	<!-- end of Product model -->

</body>
</html>