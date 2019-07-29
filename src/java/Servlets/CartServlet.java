package Servlets;

import BusinessObjects.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @class Advanced Systems Project
 * @instructor Ron Enz
 * @author Andrew Patrick
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        //retrieve info from form
        int qty = Integer.parseInt(request.getParameter("quantity"));
        
        //retrieve data from the session
        HttpSession ses1;
        ses1 = request.getSession();
        Product prod = (Product)ses1.getAttribute("prod");
        if(prod == null) {
            System.out.println("BEST SELLER");
            prod.selectP(request.getParameter("prodId"));
            prod.display();
        }
        ProductList cart = (ProductList)ses1.getAttribute("cart");
        
        //populate cart with a product of quantity qty
        if (cart == null){
            cart = new ProductList();
        }
        for (int i=0; i<qty; i++){
            cart.addProd(prod);
        }
        
        //send shopping cart to the session
        ses1.setAttribute("cart", cart);
        
        //forward to view the shopping cart
        RequestDispatcher rd = request.getRequestDispatcher("/shopping_cart.jsp");
        rd.forward(request, response);
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
