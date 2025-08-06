<%@page import="com.learn.mycart.helper.Helper"%>
<%@page import="com.learn.mycart.entites.Category"%>
<%@page import="com.learn.mycart.dao.CategoryDao"%>

<%@page import="com.learn.mycart.entites.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.learn.mycart.dao.ProductDao"%>
<%@page import="com.learn.mycart.helper.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyCart-Home</title>

<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
.back-img {
	background: url("img/peakpx.jpg");
	height: 100vh;
	width: 100%;
	background-repeat: repeat;
	background-size: cover;
}

.crd-ho:hover {
	background-color: #cfcfd1;
}

a {
	color: #fff;
	text-decoration: none;
}

.me {
	width: 400px;
	margin: 90px auto;
}

.me p, .me h1 {
	letter-spacing: 3px;
	text-align: center;
}

.me p {
	font-weight: 200;
}

.me span {
	font-weight: bold;
}

.social {
	position: fixed;
	top: 450px;
}

.social ul {
	padding: 0px; /* this is used to align the name from left to screen  */
	transform: translate(-270px, 0);
}

.social ul li {
	display: block;
	margin: 5px;
	background: rgba(0, 0, 0, 0.36);
	width: 300px;
	text-align: right;
	padding: 10px;
	border-radius: 0 30px 30px 0;
	transition: all 1s;
}

.social ul li:hover {
	transform: translate(110px, 0);
	background: rgba(255, 255, 255, 0.4);
}

.social ul li:hover a {
	color: #000;
}

.social ul li:hover i {
	color: #fff;
	background: rgba(0, 0, 0, 0.36);
	transform: rotate(360deg);
	transition: all 1s;
}

.social ul li i {
	margin-left: 10px;
	color: #000;
	background: #fff;
	padding: 10px;
	border-radius: 50%;
	width: 20px;
	height: 20px;
	font-size: 20px;
	background: #ffffff;
	transform: rotate(0deg);
}

body {
	background: #FFFFFF;
	color: #000;
	font-family: 'Raleway', sans-serif;
}
</style>
</head>
<body>

	<%@include file="components/navbar.jsp"%>

	<div class="container-fluid back-img">
		<div class="row mt-1 ">
			<%
			String cat = request.getParameter("category");
			ProductDao dao = new ProductDao(FactoryProvider.getFactory());
			List<Product> list = null;
			 /*if(cat==null){

			}else */ if (cat == null || cat.trim().equals("all")) {
				list = dao.getAllProducts();
				/* List<Product> list = dao.getAllProducts(); */
			} else {
				int cid = Integer.parseInt(cat.trim());
				list = dao.getAllProductsById(cid);

			}
			CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
			List<Category> clist = cdao.getCategories();
			%>


			<!--  show category -->

			<div class="col-md-2">
				<div class="list-group mt-4" style="width: 15rem;">
					<a href="index.jsp?category=all"
						class="list-group-item list-group-item-action active "> ALL
						PRODUCTS </a>
					<%
					for (Category c : clist) {
					%>

					<!-- out.println(c.getCategoryTitle() + "<br>"); -->
					<a href="index.jsp?category=<%=c.getCategoryId()%>"
						class="list-group-item list-group-item-action"><%=c.getCategoryTitle()%></a>


					<%
					}
					%>


				</div>
				<nav class="social">
					<ul>
						<li><a style="color: #00BFFF"
							href="https://trailblazer.me/id/ttambe10">Salesfocre <i
								class="fa-brands fa-salesforce"></i></a></li>
						<li><a href="https://github.com/tanmaytambe57">Github <i
								class="fa fa-github"></i></a></li>
						<li><a style="color: #FF4500"
							href="https://www.linkedin.com/in/tanmay-tambe-tt/">Linkedin
								<i class="fa fa-linkedin"></i>
						</a></li>
						<li><a style="color: #FF69B4"
							href="https://www.instagram.com/tanmay_tambe_tt/">Instagram <i
								class="fa-brands fa-instagram"></i>
						</a></li>
						<li><a style="color: #BF40BF"
							href="mailto:tanmaytambe57@example.com">Gmail-ID <i
								class="fa-regular fa-envelope"></i>

						</a></li>
					</ul>
				</nav>
			</div>
			<!-- show Product -->
			<div class="col-md-10">
				<%-- <h1>
				Number Of Products is
				<%=list.size()%></h1>
			<%
			for (Product product : list) {
				out.println(product.getpPhoto() + "<br>");
				out.println(product.getpName() + "<br>");

			}

			%> --%>

				<!-- row -->
				<div class="row mt-4">

					<!-- col12 -->
					<div class="col-md-12">

						<div class="card-columns">
							<!-- traversing products -->
							<%
							for (Product p : list) {
							%>

							<!-- Product card -->

							<div class="card" style="width: 25rem;">
								<div class="container text-center" >
									<img class="card-img-top m-2"
										src="img/products/<%=p.getpPhoto()%>"
										style="max-height: 270px; max-width: 250px; width: auto;"
										alt="Card image cap">
								</div>
								<div class="card-body">
									<h5 class="card-title"><%=p.getpName()%></h5>
									<p class="card-text"><%=Helper.get10Words(p.getpDesc())%></p>
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
								<div class="card-footer text-center">

									<button class="btn custom-bg text-white"
										onclick="add_to_cart(<%=p.getpId()%>,'<%=p.getpName()%>',<%=p.getPriceAfterApplyingDiscount()%>)">Add
										to cart</button>
									<button class="btn btn-outline-success">
										&#8377;
										<%=p.getPriceAfterApplyingDiscount()%>/-<span
											class="text-secondary discount-label"> &#8377; <%=p.getpPrice()%>
											<%=p.getpDiscount()%>% off
										</span>
									</button>
								</div>


							</div>

							<%
							}
							if (list.size() == 0) {
							out.println("<h3>No Item In This Category</h3>");
							}
							%>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="components/comman_model.jsp"%>
</body>
</html>