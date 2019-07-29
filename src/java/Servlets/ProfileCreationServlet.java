package Servlets;

import BusinessObjects.AccessDB;
import BusinessObjects.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
@WebServlet(name = "ProfileCreationServlet", urlPatterns = {"/ProfileCreationServlet"})
public class ProfileCreationServlet extends HttpServlet {

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
        String id, fName, lName, address, street, city, state, zipcode, eMail, password, pass2, acctC;
        int count = 0;
        HttpSession ses1;
        ses1 = request.getSession();
        Customer c1 = new Customer();
        
        //COUNTS rows in DB so it can assign the next ID number in line
        AccessDB path = new AccessDB();
        try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver Loading...");
            Connection con = DriverManager.getConnection(path.getPath());
            System.out.println("Driver Loaded.");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT CustID FROM Customers");
            ResultSetMetaData data = rs.getMetaData();
            
            while(rs.next()) {
                count++;
            } 
            count+=1;
            System.out.println(count);
        }
        catch(Exception ex) { System.out.println(ex); }
        
        id = count + "";
        //retrieve info from form
        fName = request.getParameter("fname");
        lName = request.getParameter("lname");
        street = request.getParameter("address");
        city = request.getParameter("city");
        state = request.getParameter("state");
        zipcode = request.getParameter("zipcode");
        eMail = request.getParameter("email");
        password = request.getParameter("pass");
        pass2 = request.getParameter("pass2");
        
        acctC = "Yes";
        address = street+ ", " +city+ ", " +state+ " " +zipcode;
        
        //insert new Customer into database and set attributes. then send 
        //  Customer object to the session
        if(password.equals(pass2) && !password.equals("")) {
        c1.insertC(id, fName, lName, password, address, eMail, acctC);
        c1.selectC(id);
        ses1.setAttribute("user", c1);
        RequestDispatcher rd = request.getRequestDispatcher("/customer_profile.jsp");
        rd.forward(request,response);
        }else {
            int er = 1;
            ses1.setAttribute("er", er);
            RequestDispatcher rd2 = request.getRequestDispatcher("/customer_register.jsp");
            rd2.forward(request, response);
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
