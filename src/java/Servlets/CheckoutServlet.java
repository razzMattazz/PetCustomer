/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BusinessObjects.AccessDB;
import BusinessObjects.Customer;
import BusinessObjects.Order;
import BusinessObjects.OrderDetail;
import BusinessObjects.Product;
import BusinessObjects.ProductList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author matthew vaughn
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String odid, cid, oid, firstName, lastName, email, address, state, zip, fullAddress, cardType;
            double total = 0;
            Customer c1;
            Order o1;
            OrderDetail od1;
            AccessDB adb = new AccessDB();
            
            HttpSession session;
            session = request.getSession();
            Customer cust = (Customer) session.getAttribute("user");
            ProductList cart = (ProductList) session.getAttribute("cart");
            
            if(cust != null){
                c1 = cust;
                cardType = request.getParameter("cardType");
            }

            else{
                // Generate new Customer ID for Guest
                int custId = adb.GetCount("SELECT COUNT(*) FROM Customers") + 1;
                cid = Integer.toString(custId);
                
                // GET values from checkout.jsp to create new Customer
                firstName = request.getParameter("fName");
                lastName = request.getParameter("lName");
                email = request.getParameter("email");
                address = request.getParameter("address");
                state = request.getParameter("state");
                zip = request.getParameter("zip");
                cardType = request.getParameter("cardType");
                fullAddress = address + ", " + state + " " + zip;
                c1 = new Customer(cid, firstName, lastName, "", fullAddress, email, "no");
                adb.InsertCustomer(c1);
            }
            
            // Create new Order 
            int orderId = adb.GetCount("SELECT orderid FROM orders ORDER BY orderid DESC") + 1;
            oid = Integer.toString(orderId);
            o1 = new Order(oid, c1.getID(), cardType, "0", "no");
            adb.InsertOrder(o1);

            // Build 2 ArrayLists to determine quantity of products
            ArrayList<Product> cartList = cart.getList();
            ArrayList<Integer> quantityCheck = new ArrayList();
            Set<Integer> set = new HashSet<Integer>();
            for(Product p1: cartList){
                quantityCheck.add(Integer.parseInt(p1.getID()));
            }
            out.println("<html>");
            int quantity = 0;
            // Create new OrderDetails per Product with quantity

            for (int e = 0; e < cartList.size(); e++) {
                Product prod = cartList.get(e);
                while (set.add(Integer.parseInt(prod.getID()))) {
                    quantity = 0;
                    for (int a = 0; a < quantityCheck.size(); a++) {
                        if (Integer.parseInt(prod.getID()) == quantityCheck.get(a)) {
                            quantity++;
                        }
                    }
                    total = total + (Double.parseDouble(prod.getPrice()) * quantity);
                    int orderDetail = adb.GetCount("SELECT id FROM orderDetails ORDER BY id DESC") + 1;
                    odid = Integer.toString(orderDetail);
                    String qString = Integer.toString(quantity);
                    od1 = new OrderDetail(odid, o1.getOrderID(), prod.getID(), qString, "'no'");
                    prod.updateInvAvailable(prod.getID(), qString);
                    adb.InsertOrderDetail(od1);
                }
            }
            String totalString = Double.toString(total);
            o1.setOrderTotal(totalString);
            adb.update("UPDATE orders SET total='"+o1.getOrderTotal()+"' WHERE orderid='"+o1.getOrderID()+"'");
            cart = null;
            session.setAttribute("cart", cart);
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");
            rd.forward(request, response);
            
        }
                
            
            
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}