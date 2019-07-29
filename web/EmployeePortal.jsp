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
            Customer c1 = new Customer();
            Product p1 = new Product();
            
        %>
        <%
            Employee emp = (Employee)session.getAttribute("emp");
            if(emp == null) {
                int error = 3;
                session.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("/emplogin_error.jsp");
                rd.forward(request,response);
            }
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
        <div class = "container">
            <h1 align="center"><b>Orders</b></h1><table>
            <%
                o1.GetOrders();
                
                for (int i = 0; i < o1.orlist.orlist.size() ; i++){
                %><%c1.selectC(o1.orlist.orlist.get(i).getOrderCID());
                    
                    out.println("<tr><th>Order ID: " + o1.orlist.orlist.get(i).getOrderID());
                    out.println("</th><th>Customer Name: " + c1.getFN() +" " + c1.getLN() + "<br />Address: " + c1.getAddress());
                    out.println("</th><th>Order Total: $" + o1.orlist.orlist.get(i).getOrderTotal());
                    out.println("</th><th>Shipped Status: " + o1.orlist.orlist.get(i).getOrderFul() + "</th>");
                    
                    for(int q = 0; q < o1.orlist.orlist.get(i).olist.olist.size() ; q++){
                        %><form method=post action="FulServ"> <%
                        
                        out.println("<tr><td>Product ID: "+o1.orlist.orlist.get(i).olist.olist.get(q).getorDetailPId()+"</td>");
                        out.println("<td>Quantity: "+o1.orlist.orlist.get(i).olist.olist.get(q).getorDetailQty()+"</td>");
                        out.println("<td>Fulfilled?: "+o1.orlist.orlist.get(i).olist.olist.get(q).getorDetailFulfilled() + "</td>");
                        %> <td><input type="hidden" id="odid" name="odid" value="<% out.println(o1.orlist.orlist.get(i).olist.olist.get(q).getorDetailId()); %>">
                            <input type="submit" name="submit" value="Ship"/></td></tr></form> <%
                    }
                    %>  <%
                }
            %></table>
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