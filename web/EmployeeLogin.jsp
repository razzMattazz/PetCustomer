<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<!-- metadata -->
	<meta charset="utf-8">
	<meta name="author" content="David Schreck">
	
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<script src="script.js"></script>
        
        <% Employee emp = (Employee) session.getAttribute("emp"); 
        if(emp != null) {
            int error = 1;
            session.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/emplogin_error.jsp");
            rd.forward(request, response);
        }
        
	%>
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