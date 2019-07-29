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
        String s = (String)session.getAttribute("search");
        ProductList plist = new ProductList(s);
        ProductList cart = (ProductList)session.getAttribute("cart");
        if (cart == null){
            cart = new ProductList();
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
                                    <form method="post" action="SearchServlet">
                                        <input type="text" name="srchbox" value="<%= s %>"/>
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
            <div id = "productspage_maincontent">
                <p class = "page_header">Products</p>
                <%
                    if (plist.getSize() == 0){
                        out.println("<p>No results found.</p>");
                    }else{
                        for(int i = 0; i < plist.getSize(); i++){
                            Product p = plist.getProd(i);

                            out.println("<div class = \"productdiv_small\">");
                                out.println("<a><img src = images/" + p.getImg() + "></a>");
                                out.println("<span><strong>" + p.getName() + ":</strong></span>");
                                out.println("<p class = \"productprice\">" + p.getPrice() + "</p>");
                                out.println("<form method=\"get\" action=\"DetailServlet\">");
                                    out.println("<input type=\"hidden\" name=\"prodId\" value=\"" + p.getID() + "\">");
                                    out.println("<input type=\"submit\" name=\"submit\" value=\"Detail\">");
                                out.println("</form>");
                            out.println("</div>");
                        }
                    }
                %>
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