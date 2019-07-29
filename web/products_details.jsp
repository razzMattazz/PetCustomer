<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<!-- metadata goes here -->
	<meta charset="utf-8">
	<meta name="author" content="Marc Turner">
	<meta name="description" content="This website represents the final project for ">
	
	<style>
            <%@include file = "style.css" %>
        </style>
	
	<script src="script.js"></script>
	
	<title>Petstore.com</title>
        
        <%
            Product p = (Product)session.getAttribute("prod");
        %>
        
</head>
<body>
<!-- header Content here -->
	<header>
		<div id = "topbar">
                    <a href="index.html">
                       <img id = "logo" src="images/petStufflogo.jpg"> 
                    </a>
			<div id = "search-div" class = "topbar-section">
				<div id = "search-text">
                                    <form method="post" action="SearchServlet">
                                        <input type="text" name="srchbox"/>
                                        <input type="submit" name="srchbtn" value="Search"/>
                                    </form>
				</div>
			</div>
			<div id = "topbarnav-right">
				<span id = "topnavright-text">
					<a href = "login.jsp">Log In/Sign Up</a> 
					|
					<a href = "customer_profile.jsp">My Account</a>
					| 
					<a href = "shopping_cart.jsp">Cart</a>
				</span>
			</div>
		</div>
		
		<div id = "secondnavbar">
			<div id = "secondnavbar-main">
				<div id = "tagline">
					<span id="tagline-text">ANYTHING FOR ALL YOUR PETS...as long as they're dogs and cats</span>
				</div>
				<div id = "secondnavbar-right">
					<span id = "secondnavbar-text">
					<a href = "products.jsp">Products</a>
					| 
					<a href = "FAQ.html">Help/FAQ</a> 
					| 
					<a href = "contact.html">Contact</a>
					</span>
				</div>
			</div>
		</div>
	</header>
<!--header content end-->

<!--main body content start -->
	<main>
		<div id = "main_wrapper">

	<!--YOUR CONTENT HERE START-->
				<div id="productDetail_maincontent">
                    <p class = "page_header">Product Detail</p>
                    <div id = "productDetailDiv">
						<div id = "productDetail_image">
							<img src = "images/<%= p.getImg() %>" align = "center">
						</div>
						<div id = "productDetail_content">
							<h1><%= p.getName() %></h1>
							<p><%= p.readDescr() %></p>
							<p id = "detailPrice">$<%= p.getPrice() %></p>
                                                        <p id = "InOutOfStock">
                                                            <%
                                                                if(Integer.parseInt(p.getIA()) > 0){
                                                                    out.println("IN STOCK!!!</br>Inventory Available: " +p.getIA());
                                                                }else{
                                                                    out.println("OUT OF STOCK");
                                                                }
                                                            %>
                                                        </p>
							<form method="get" action="CartServlet">
								Quantity:<br>
								<input type="number" name="quantity" min="1" max="<%=(p.getIA())%>"><br><br>
								<input type="submit" value="add to cart">
							</form>
						</div>
					</div>
                    
				</div>
	<!--YOUR CONTENT HERE END-->
        </div>
        
	</main>
<!--main body content end-->

<!--footer content start-->
	<footer>
		<div id="footer-topline">
			<div id = "footertopline-div">
				<span id = "footertopline-text">CONTACT OUR PRODUCT SPECIALISTS: 1-800-123-1234</span>
			</div>
		</div>
		<div id="copyright">
			<p>©Copyright 2050 by nobody. All rights reversed.</p>
		</div>
	</footer>
<!--footer content end-->
	
	
</body>
</html>