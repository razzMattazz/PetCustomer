<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*" import="java.text.NumberFormat" import="java.util.*"%>
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

            ProductList cart = (ProductList) session.getAttribute("cart");
            if (cart == null) {
                cart = new ProductList();
            }
            
            double total = 0;
            String money;
            int quantity = 0;
            ArrayList<Product> prods = cart.getList();
            ArrayList<Integer> ids = new ArrayList();
            Set<Integer> set = new HashSet<Integer>();
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
                        <fieldset id="signIn_fieldset">
                            <legend><h1>Checkout</h1></legend>
                            <div id="login_returning_customer">
                    
                    <%
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
                    %>
                    
                    <form action="CheckoutServlet" method="get">
                        <%
                            NumberFormat fmt = NumberFormat.getCurrencyInstance();
                            System.out.println(total);
                            money = fmt.format(total);
                            out.println("<strong>Total: </strong>" + money);
                            session.setAttribute("total", total);
                            Customer cust = (Customer) session.getAttribute("user");
                            if (cust == null) {

                                out.println("<br><br><strong>First Name: </strong><input type=\"text\" name=\"fName\"><br>");
                                out.println("<br><strong>Last Name: </strong><input type=\"text\" name=\"lName\"><br>");
                                out.println("<br><strong>Email: </strong><input type=\"text\" name=\"email\" size=\"35\"><br>");
                                out.println("<br><br><strong> Payment </strong><br><br><strong> Card Type: </strong>");
                                out.println("<select name=\"cardType\">"
                                        + "<option value=\"\">Select Card Type</option>"
                                        + "<option value=\"Visa\">Visa</option>"
                                        + "<option value=\"Amex\">Amex</option>"
                                        + "<optio   n value=\"Mastercard\">Mastercard</option>"
                                        + "</select><br>");
                                out.println("<br><strong>Card Number: </strong><input type=\"text\" name=\"cardNumber\"><br>");
                                out.println("<br><br><br><strong>Address: </strong><input type=\"text\" name=\"address\" size=\"35\"><br>");
                                out.println("<br><strong>State: </strong><select name=\"state\">"
                                        + "<option value=\"\">Select State</option>"
                                        + "<option value=\"AL\">Alabama</option>"
                                        + "<option value=\"AK\">Alaska</option>"
                                        + "<option value=\"AZ\">Arizona</option>"
                                        + "<option value=\"AR\">Arkansas</option>"
                                        + "<option value=\"CA\">California</option>"
                                        + "<option value=\"CO\">Colorado</option>"
                                        + "<option value=\"CT\">Connecticut</option>"
                                        + "<option value=\"DE\">Delaware</option>"
                                        + "<option value=\"DC\">District Of Columbia</option>"
                                        + "<option value=\"FL\">Florida</option>"
                                        + "<option value=\"GA\">Georgia</option>"
                                        + "<option value=\"HI\">Hawaii</option>"
                                        + "<option value=\"ID\">Idaho</option>"
                                        + "<option value=\"IL\">Illinois</option>"
                                        + "<option value=\"IN\">Indiana</option>"
                                        + "<option value=\"IA\">Iowa</option>"
                                        + "<option value=\"KS\">Kansas</option>"
                                        + "<option value=\"KY\">Kentucky</option>"
                                        + "<option value=\"LA\">Louisiana</option>"
                                        + "<option value=\"ME\">Maine</option>"
                                        + "<option value=\"MD\">Maryland</option>"
                                        + "<option value=\"MA\">Massachusetts</option>"
                                        + "<option value=\"MI\">Michigan</option>"
                                        + "<option value=\"MN\">Minnesota</option>"
                                        + "<option value=\"MS\">Mississippi</option>"
                                        + "<option value=\"MO\">Missouri</option>"
                                        + "<option value=\"MT\">Montana</option>"
                                        + "<option value=\"NE\">Nebraska</option>"
                                        + "<option value=\"NV\">Nevada</option>"
                                        + "<option value=\"NH\">New Hampshire</option>"
                                        + "<option value=\"NJ\">New Jersey</option>"
                                        + "<option value=\"NM\">New Mexico</option>"
                                        + "<option value=\"NY\">New York</option>"
                                        + "<option value=\"NC\">North Carolina</option>"
                                        + "<option value=\"ND\">North Dakota</option>"
                                        + "<option value=\"OH\">Ohio</option>"
                                        + "<option value=\"OK\">Oklahoma</option>"
                                        + "<option value=\"OR\">Oregon</option>"
                                        + "<option value=\"PA\">Pennsylvania</option>"
                                        + "<option value=\"RI\">Rhode Island</option>"
                                        + "<option value=\"SC\">South Carolina</option>"
                                        + "<option value=\"SD\">South Dakota</option>"
                                        + "<option value=\"TN\">Tennessee</option>"
                                        + "<option value=\"TX\">Texas</option>"
                                        + "<option value=\"UT\">Utah</option>"
                                        + "<option value=\"VT\">Vermont</option>"
                                        + "<option value=\"VA\">Virginia</option>"
                                        + "<option value=\"WA\">Washington</option>"
                                        + "<option value=\"WV\">West Virginia</option>"
                                        + "<option value=\"WI\">Wisconsin</option>"
                                        + "<option value=\"WY\">Wyoming</option>"
                                        + " </select><br>");
                                out.println("<br><strong>ZIP Code: </strong><input type=\"text\" name=\"zip\">");
                            }
                            if(cust != null) {
                                out.println("<br><br>" + cust.getFN() + " " + cust.getLN()); 
                                out.println("<br>" + cust.getEMail());
                                out.println("<br><br><strong> Shipping Address: </strong>");
                                out.println(cust.getAddress());
                                out.println("<br><br><strong> Payment</strong><br>");
                                out.println("<strong>Card Type: </strong><select name=\"cardType\">"
                                        + "<option value=\"\">Select Card Type</option>"
                                        + "<option value=\"Visa\">Visa</option>"
                                        + "<option value=\"Amex\">Amex</option>"
                                        + "<option value=\"Mastercard\">Mastercard</option>"
                                        + "</select><br>");
                                out.println("<br><strong>Card Number: </strong><input type=\"text\" name=\"cardNumber\"><br>");
                                
                                
                            }
                        %>
                        <br><br>
                        <input type="submit" name="order" value="Submit Order">
                    </form>
                            </div>
                        </fieldset>
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