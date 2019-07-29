<!DOCTYPE html>
<html lang="en-US">
<head>
	<!-- metadata -->
	<meta charset="utf-8">
	<meta name="author" content="David Schreck">
	
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<script src="script.js"></script>
	
	<title>Employee Login</title>
</head>
<body>
<!-- header start -->
	<header>
		<div id = "topbar">
                       <img id = "logo" src="images/petStufflogo.jpg"> 
			<div id = "topbarnav-right">
				<span id = "topnavright-text">
					<a href = "EmployeeLogin.jsp">Log In</a> 
					<a href = "faq.jsp">F.A.Q</a>
					<a href = "EmployeePortal.jsp">Orders</a>
				</span>
			</div>
		</div>
	</header>
<!--header end-->

<!--main start -->
	<main>
            <div class = "main-flex">
                <div id="maincontent">
                    <h1 align="center"><b>Login</b></h1>
                    <form method="post" action="EmpLoginServ">
                        <table align="center">
                            <tr>
                                <td>User ID:</td>
                                <td><input type="text" name="idbox" selected/></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" name="pwbox"/></td>
                            </tr>
                            
                            <tr><p>
                                <% int error = (Integer)session.getAttribute("error");
                                    if(error == 1) { 
                                        out.println("<font color=\"red\">Already logged in. Enter another username and password to log into a different account.</font>");
                                    }
                                    if(error == 2) {
                                        out.println("<font color=\"red\">Incorrect Username/Password</font>");
                                    }
                                    if(error == 3) {
                                        out.println("<font color=\"red\">Must be logged in to access shipping information</font>");
                                    }
                                %>
                                
                            </p></tr>
                            
                            <tr>
                                <td><input type="submit" name="submit" value="Login"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
	</main>
<!--main end-->

<!--footer start-->
	<footer>
		<div id="footer-topline">
			<div id = "footertopline-div">
				<span id = "footertopline-text">Get to work</span>
			</div>
		</div>
	</footer>
<!--footer end-->
	
	
</body>
</html>