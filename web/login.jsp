<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<!-- metadata goes here -->
	<meta charset="utf-8">
	<meta name="author" content="Marc Turner">
	<meta name="description" content="This website represents the final project for Group 1...yay Group 1">
	
	<link rel="stylesheet" href="style.css">
	
	<script src="script.js"></script>
	
	<title>Pet Stuff-Customer Login</title>
        <%Customer c1 = (Customer) session.getAttribute("user");
            if (c1 != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/customer_profile.jsp");
                rd.forward(request, response);
            }
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
					<form name="search" action="SearchServlet" onsubmit="return validateSearchForm()" method="post">
						<input type="text" name="srchbox" required>
						<input type="submit" name="srchbtn" value="Search">
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
		<!--YOUR CONTENT HERE START-->
		<div id="main_wrapper">
				<div id="login_div">
					<fieldset id = "signIn_fieldset">
						<legend><h1>Sign In or Register:</h1></legend>
						<div id = login_returning_customer>
							<form name = "login" action="LoginServlet" onsubmit="return validateLoginForm()" method="post">
								<h2>I'm a Returning Customer</h2>
								User ID:<br>
								<input type="email" name="idbox" size="35" selected rquired>
								<br><br>
								Password:<br>
								<input type="password" name="pwbox" size="35" required>
								<br><br>
								<input type="submit" value="Login"><br><br>
							</form>
                                                    <a href="EmployeeLogin.jsp"> Login as an employee</a>
						</div>
						<div id = "New Customer">
							<h2>I'm a New Customer</h2>
							<i>Creating an account is fast, easy, and free.<br> Why not? No, really...why not?</i> 
                                                	<br><br><button type="button" onclick="window.location.href='customer_register.jsp'">Create Account</button>
						</div>
					</fieldset>
				</div>
				<div id = "indexTop_bannerDiv">
					<img src = "images/indextopbanner.jpg">
				</div>
		</div>
		<!--YOUR CONTENT HERE END-->
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
			<p>�Copyright 2050 by nobody. All rights reversed.</p>
		</div>
	</footer>
<!--footer content end-->	
</body>
</html>