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
 * @author matth
 */
@WebServlet(name = "ProfileEditServlet", urlPatterns = {"/ProfileEditServlet"})
public class ProfileEditServlet extends HttpServlet {

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
        String fName, lName, address, eMail, password;
        HttpSession ses1;
        ses1 = request.getSession();
        //pull Customer from session
        Customer c1 = (Customer) ses1.getAttribute("user");
        c1.display();

        //pull parameters from profile_edit.jsp
        fName = request.getParameter("fName");
        lName = request.getParameter("lName");
        address = request.getParameter("address");
        eMail = request.getParameter("eMail");
        password = request.getParameter("password");

        //call updatecustomer method for each parameter that is different than 
        //the customer object from the session
        try {
            if (!fName.equals(c1.getFN()) && !fName.equals("")) {
                c1.updateC(c1.getID(), fName, "FName");
                c1.setfName(fName);
            }
            if (!lName.equals(c1.getLN()) && !lName.equals("")) {
                c1.updateC(c1.getID(), lName, "LName");
                c1.setlName(lName);
            }
            if (!address.equals(c1.getAddress()) && !address.equals("")) {
                c1.updateC(c1.getID(), address, "Address");
                c1.setAddress(address);
            }
            if (!eMail.equals(c1.getFN()) && !eMail.equals("")) {
                c1.updateC(c1.getID(), eMail, "Email");
                c1.setEMail(eMail);
            }
            if (!password.equals(c1.getPass()) && !password.equals("")) {
                c1.updateC(c1.getID(), password, "Pass");
                c1.setPass(password);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        
        //throw customer object back into session
        ses1.setAttribute("user", c1);
        //forward to customer profile.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/customer_profile.jsp");
        rd.forward(request,response);
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
