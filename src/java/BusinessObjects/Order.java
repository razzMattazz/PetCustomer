/*
 * Business Object for Order
 * 
 * 
 */
package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kazu
 */
public class Order {
    
    String dbPath = "jdbc:ucanaccess://C:/Users/matth/Documents/PetCustomer/db/petDatabase.accdb;columnOrder=DISPLAY";
    
	
    private String oId;
    private String oCustId;
    private String oPay;
    private String oTotal;
    private String oFulfilled;
    public OrList orlist;
    public OrderList olist;
    
    public Order(){}
    
    public Order( String orderID, String OrderCID, String orderPay, String orderTotal, String orderFulfilled ){
		
        oId = orderID;
        oCustId = OrderCID;
        oPay = orderPay;
        oTotal = orderTotal;
        oFulfilled = orderFulfilled;

    }
    
    //Setters
    public void setOrderID(String orderID){oId = orderID;}
    public void setOrderCID(String OrderCID){oCustId = OrderCID;}
    public void setOrderPay(String orderPay){oPay = orderPay;}
    public void setOrderTotal(String orderTotal){oTotal = orderTotal;}
    public void setOrderFul(String orderFulfilled){oFulfilled = orderFulfilled;}

    
    //Getters
    public String getOrderID(){return oId;}
    public String getOrderCID(){return oCustId;}
    public String getOrderPay(){return oPay;}
    public String getOrderTotal(){return oTotal;}
    public String getOrderFul(){return oFulfilled;}
	
    // Select Statement for Order
    public void Select(String oId) {
        try{   
            
            AccessDB or1 = new AccessDB();
            String sql = "SELECT * FROM orders WHERE OrderId = " + oId;

            ArrayList<String> reception = or1.select(sql);

            setOrderID(reception.get(0));
            setOrderCID(reception.get(1));
            setOrderPay(reception.get(2));
            setOrderTotal(reception.get(4));
            setOrderFul(reception.get(3));
            
            }
        
        catch(Exception ex) {System.out.println(ex); }

    }
    // Function generates list of order objects for internal site. 
    public void GetOrders(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(dbPath);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM orders WHERE Orderfufilled = 'no';";
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            int i = 0;
            orlist = new OrList();
            while(rs.next()){
                Order o1 = new Order(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(5), rs.getString(4));
                o1.Display();
                o1.GetOrderDetails(); // Fills Order Object with OrderDetail Objects
                
                // Conditional to check if OrderDetail Arraylist is Empty
                if(o1.olist.olist.isEmpty()){ o1.Update(); }
                
                // Conditional to check fulfillment status of Order
                if ("no".equals(o1.getOrderFul().toLowerCase())){ orlist.AddOrder(o1);}
            }
            conn.close();
            }
    
        catch(Exception e){
            System.out.println("Fail @ GetOrders()");
        }        
    }
    
    // Function generates an arraylist of OrderDetails specific to the order ID.
    public void GetOrderDetails(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(dbPath);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM OrderDetails where orderid =" + getOrderID();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            int i = 0;
            olist = new OrderList();
            while(rs.next()){
                OrderDetail o1 = new OrderDetail(rs.getString(1), rs.getString(4),rs.getString(2), rs.getString(3), rs.getString(5));
                // Checks for OrderDetail Fulfillment Status, adds to OrderDetail Array if not fulfilled.
                if("no".equals(o1.getorDetailFulfilled().toLowerCase())){ olist.AddOrder(o1); }
            }
            conn.close();
            }
    
        catch(Exception e){
            System.out.println("Fail @ GetOrderDetails()");
        }
    }
    
    // Pulls list of fulfilled Orders
    public void GetFulfilled(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(dbPath);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM orders WHERE Orderfufilled = 'yes';";
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            int i = 0;
            orlist = new OrList();
            while(rs.next()){
                Order o1 = new Order(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(5), rs.getString(4));
                o1.GetFulfilledOD();
                orlist.AddOrder(o1);
            }
            conn.close();
            }
    
        catch(Exception e){
            System.out.println("Fail @ GetFulfilled()");
        }       
    }
    // Pulls list of fulfilled order details
    public void GetFulfilledOD(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(dbPath);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM OrderDetails WHERE fulfilled = 'yes' AND orderid =" + getOrderID();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            int i = 0;
            olist = new OrderList();
            while(rs.next()){
                OrderDetail o1 = new OrderDetail(rs.getString(1), rs.getString(4),rs.getString(2), rs.getString(3), rs.getString(5));
                olist.AddOrder(o1);
            }
            conn.close();
            }
    
        catch(Exception e){
            System.out.println("Fail @ GetOrderDetails()");
        }
    }
    
    // Updates Fulfillment Status of Order Object
    public void Update(){
        String sql = "Update orders set Orderfufilled = 'yes' WHERE orderID ="+getOrderID()+";";
        System.out.println(sql);
        AccessDB o2 = new AccessDB();
        o2.update(sql);
    }
    
    // Updates fulfillment status of order to unshipped
    public void Unpick(){
        String sql = "Update orders set Orderfufilled = 'no' WHERE orderID ="+getOrderID()+";";
        System.out.println(sql);
        AccessDB o2 = new AccessDB();
        o2.update(sql);
    }
    
    
    /* Unused methods 
    public void Update(){
        String sql = "UPDATE Employee SET EmpID='"+eId+"', FName='"+eFname+"', LName='"+eLname+"', Pass='"+ePass+"', Address='"+eAdd+"', Email='"+eEmail+"' where EmpID='"+eId+"';";
        AccessDB o2 = new AccessDB();
        e1.update(sql);
    }
    
    public void Insert(){
        String sql = "INSERT into Employee VALUES('"+eId+"', '"+eFname+"', '"+eLname+"', '"+ePass+"', '"+eAdd+"', '"+eEmail+"');";
        AccessDB o3 = new AccessDB();
        e2.insert(sql);
    }
    
    public void Delete(String id){
        String sql = "DELETE from employee WHERE EmpID = '"+id+"';";
        AccessDB o4 = new AccessDB();
        e3.delete(sql);
    }
        */
    //DISPLAY for debugging purposes
    public void Display() {
            System.out.println("=====BEGINNING DEBUG FROM ORDER.JAVA=====");
            System.out.println("Order ID: " + oId);
            System.out.println("Order Customer ID: " + oCustId);
            System.out.println("Payment Choice: " + oPay);
            System.out.println("Order Total: " + oTotal);
            System.out.println("Order Fulfilled: " + oFulfilled);
            System.out.println("=====END DEBUG FROM ORDER.JAVA=====");
            
    }
    
    public static void main(String[] args){
        Order o1 = new Order();
        o1.GetOrders();
        System.out.println(o1.olist.olist.size());
        
    }
}
