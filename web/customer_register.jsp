<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*"%>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <!-- metadata goes here -->
        <meta charset="utf-8">
        <meta name="author" content="Marc Turner">
        <meta name="description" content="This website represents the final project for Group 1...yay Group 1">

        <link rel="stylesheet" href="style.css">

        <script src="script.js"></script>

        <title>Register Account</title>
		
        <%Customer c1 = (Customer) session.getAttribute("user");%>
        
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
            
                <!--sidebar image start-->
                
                <!--sidebar image end-->

                <!--YOUR CONTENT HERE START-->
                <div id="register_main_wrapper">
                    <div id="login_div">
                        <fieldset id = "signIn_fieldset">
                            <legend><h1>Create Account:</h1></legend>
                            <div id="customer_register_div">
								<form method="post" action="ProfileCreationServlet">
                                <Label>First Name:</Label><br>
                                <input type="text" name="fname" size="35" selected required>
                                <br>
                                <Label>Last Name:</Label><br>
                                <input type="type" name="lname" size="35" required><br>
                                <Label>Address:</Label><br>
                                <input type="text" name="address" size="35" required></br>
                                <Label>City:</Label><br>
                                <input type="text" name="city"size="35" required><br>
                                <Label>State:</Label><br>
                                <input type="type" name="state" size="35" required><br>
                                <Label>Zipcode:</Label><br>
                                <input type="number" name="zipcode" size ="5" required><br>
                                <Label>Email:</Label><br>
                                <input type="email" name="email" size="35" required><br>
                                <Label>Create Password:</Label><br>
                                <input type="password" name="pass" size="35" required><br>
                                <Label>Verify Password:</Label><br>
                                <input type="password" name="pass2" size="35" required>
                                <br>
                                <%
                                    try{
                                    int error = 0 + (Integer) session.getAttribute("er");
                                    
                                    if(error == 1) {
                                        out.println("<font color=\"red\"><Label>Passwords must match!</Label></font>");
                                    }
                                    }
                                    catch(Exception ex) { System.out.println(ex); }
                                    %>
                                <br>
                                <input type="submit" value="Create Account"><br><br>
								</form>
                            </div>
                            <div id = "New Customer">
                                <h2>Already Have an Account?</h2> 
                                <i>Creating an account is fast, easy, and free.Why not? No, really...why not?
                                <br><br></i> 
                                <button type="button" onclick="window.location.href='login.jsp'">Sign In</button>
                                </div>
                        </fieldset>
                    </div>
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
                <p>©Copyright 2050 by nobody. All rights reversed.</p>
            </div>
        </footer>
        <!--footer content end-->


    </body>
</html>