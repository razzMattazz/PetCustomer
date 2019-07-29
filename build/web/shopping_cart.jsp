<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*" import="java.text.NumberFormat" import="javax.servlet.RequestDispatcher" import="java.util.*" %>
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
            ProductList cart = (ProductList)session.getAttribute("cart");
            if (cart==null) {
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
            <div id = "productspage_maincontent">
                <div id="login_div">
                    <fieldset id ="signIn_fieldset">
                              <legend><h1>Shopping Cart </h1></legend>
                        <div id = "login_return_customer">
                <%
                    double total = 0;
                    String money;
                    int quantity = 0;
                    ArrayList<Product> prods = cart.getList();
                    ArrayList<Integer> ids = new ArrayList();
                    Set<Integer> set = new HashSet<Integer>();
                    
                    //Algorithm Author: Matthew Vaughn
                    for (int i = 0; i<prods.size(); i++) {
                        Product prod = prods.get(i);
                        ids.add(Integer.parseInt(prod.getID()));
                    }              
                    for (int e= 0; e<prods.size(); e++) {
                        Product prod = prods.get(e);
                        while(set.add(Integer.parseInt(prod.getID()))) {
                            quantity = 0;
                            for(int a = 0; a <ids.size(); a++) {
                                if(Integer.parseInt(prod.getID()) == ids.get(a)) {
                                    quantity++;
                                }
                            }
                            total = total +(Double.parseDouble(prod.getPrice()) * quantity);
                            out.println("<p>");
                            out.println("<strong>" + prod.getName() + ": </strong>");
                            out.println("$" + prod.getPrice() + " X " +quantity);
                            out.println("<form method=\"get\" action=\"UpdateCartServlet\">");
                                out.println("<input type=\"hidden\" name=\"prodId\" value=\"" + prod.getID() + "\">");
                                out.println("<input type=\"hidden\" name=\"prodIndex\" value=\"" + e + "\">");
                                out.println("<input type=\"submit\" name=\"submit\" value=\"Remove\">");
                            out.println("</form>");
                        out.println("</p><hr>");
                    }
                    }
                    
                    
                    /*for(int i = 0; i < cart.getSize(); i++){
                        Product p = cart.getProd(i);
                        
                        out.println("<p>");
                            out.println("<strong>" + p.getName() + ": </strong>");
                            out.println("$" + p.getPrice());
                            total += Double.parseDouble(p.getPrice());
                            out.println("<form method=\"get\" action=\"UpdateCartServlet\">");
                                out.println("<input type=\"hidden\" name=\"prodId\" value=\"" + p.getID() + "\">");
                                out.println("<input type=\"hidden\" name=\"prodIndex\" value=\"" + i + "\">");
                                out.println("<input type=\"submit\" name=\"submit\" value=\"Remove\">");
                            out.println("</form>");
                        out.println("</p><hr>");
                    }*/
                    NumberFormat fmt = NumberFormat.getCurrencyInstance();
                    money = fmt.format(total);
                    out.println("<strong>Cart Total: </strong>" + money);
                    out.println("<br><br>");
                    if(total != 0) {
                        out.println("<br><br><a href = \"checkout.jsp\"> Proceed to Checkout</a>");
                    }
                    out.println("<br><br>");
                    out.println("<a href = \"products.jsp\"> Back to Shopping</a>"); 
                %>
                <br><br>
               
            </div>
                    </fieldset>
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