<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*"%>
<!DOCTYPE html>
<html lang="en-US">
<head>

<style>
table, td, th {    
    border: 1px solid #ddd;
    text-align: left;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 15px;
}
</style>

	<!-- metadata -->
	<meta charset="utf-8">
	<meta name="author" content="David Schreck">
	
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<title>Orders</title>
	<%! Order o1 = new Order();
        Order o2 = new Order();
        %>
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
                                        <a href = "unpick.jsp">Completed Orders</a>
                                        <form method="get" action="empLogoutServ">
                                            <input type="submit" value="Logout">
                                        </form>
				</span>
			</div>
		</div>
	</header>
<!--header end-->

<!--main start -->
    <main>
        <div class="container">
            <table>
                <h1>Completed Orders</h1>
                   
                <%
                    o2.GetFulfilled();
                    
                    if(o2.orlist.orlist.size() == 0){
                        out.println("<h2>No Completed Orders</h2>");
                    }
                    else{
                        for (int i = 0; i < o2.orlist.orlist.size() ; i++){
                            out.println("<tr>"
                                        + "<th> Order ID: "+o2.orlist.orlist.get(i).getOrderID()+"</th>"
                                        + "<th> Order Fulfilled Status: "+o2.orlist.orlist.get(i).getOrderFul()+" </th>"
                                        + "</tr>");
                            for (int w = 0; w < o2.orlist.orlist.get(i).olist.olist.size() ; w++) {
                                out.println("<form method=post action='UnpickServ'><tr>"
                                            + "<td> Product ID: "+o2.orlist.orlist.get(i).olist.olist.get(w).getorDetailPId()+"</td>"
                                            + "<td><input type='hidden' id='odid' name='odid' value="
                                            + "'" +o2.orlist.orlist.get(i).olist.olist.get(w).getorDetailId()+ "'>"
                                            + "<input type='submit' name='submit' value='Unship'/></form>"); 
                            }
                            int test = o2.orlist.orlist.get(i).olist.olist.size();
                            if (test == 0){
                                out.println("<form method=post action='UnpickServ'><input type='hidden' id='unship' name='unship' value='"+o2.orlist.orlist.get(i).getOrderID()+"'>");
                                out.println("<tr><td><input type='submit' name='submit' value='Unship'/></td></tr></form>");
                            }

                        }
                    }
                %>
                
            </table>
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