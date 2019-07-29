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
                            <legend><h1>Edit Account:</h1></legend>
                            <div id="customer_register_div">
                                <form method="post" action="ProfileEditServlet">
                              <%
                                  out.println("</br><strong>First Name: </strong><input type=\"text\" name=\"fName\" value=\"" + c1.getFN() + "\"></br>");
                                  out.println("<strong>Last Name: </strong><input type=\"text\" name=\"lName\" value=\"" + c1.getLN() + "\"</br></br>");
                                  out.println("<strong>Address: </strong><input type=\"text\" name=\"address\" size=\"55\" value=\"" + c1.getAddress() + "\"</br></br>");
                                  out.println("<strong>E-Mail: </strong><input type=\"email\" name=\"eMail\" size=\"55\" value=\"" + c1.getEMail() + "\"</br></br>");
                                  out.println("<strong>Password</strong><input type=\"text\" name=\"password\" size=\"55\" value=\"" + c1.getPass()+ "\"</br></br>");
                              %> 
                              <br>
                            <input type="submit" value ="Submit Changes"> 
                            </form>
                              
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