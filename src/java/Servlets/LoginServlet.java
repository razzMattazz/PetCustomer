package Servlets;

import BusinessObjects.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
            //Step 1: retrieve username and password from login.jsp
            String id, pw;
            Customer c1 = new Customer();
            AccessDB adb = new AccessDB();
            id = request.getParameter("idbox");
            pw = request.getParameter("pwbox");
            System.out.println("Step 1: Login");
            System.out.println("ID: " + id);

            //Step 2: retrieve objects from the session
            //        no objects needed
                
            //provided by the user.
                ArrayList<String> reception = new ArrayList();
                reception = adb.select("SELECT CustID FROM Customers WHERE Email = '" + id +"'");
                if(reception.size() > 0) { c1.selectC(reception.get(0)); }
               
                System.out.println("Step 3: Login");
                c1.display();

            //Step 4: make decisions
            //        no decisions needed to be made

            HttpSession ses1;
            ses1 = request.getSession();
            System.out.println("Step 5: Login");
            System.out.println("Customer added to session / forwarded to customer_profile.jsp");

            //Step 6: use RequestDispatcher to forward the user to customer_profile.jsp and 
            
            if(pw.equals(c1.getPass()) && !pw.equals("")){
            ses1.setAttribute("user", c1);
            
                RequestDispatcher rd = request.getRequestDispatcher("/customer_profile.jsp");
                rd.forward(request, response);
            } else{
                RequestDispatcher rd = request.getRequestDispatcher("/login_error.jsp");
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
