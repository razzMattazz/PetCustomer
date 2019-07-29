/*
 * Business Object for OrderDetail
 * 
 * 
 */
package BusinessObjects;

import java.util.ArrayList;

/**
 *
 * @author Kazu
 */

public class OrderDetail {
	
    private String oDetailId;
    private String oDetailOId;
    private String oDetailPId;
    private String oDetailQty;
    private String oDetailFulfilled;
    public OrderList olist;
    
    public OrderDetail(){}
    
    public OrderDetail( String orDetailId, String orDetailOId, String orDetailPId, String orDetailQty, String orDetailFulfilled ){
		
        oDetailId = orDetailId;
        oDetailOId = orDetailOId;
        oDetailPId = orDetailPId;
        oDetailQty = orDetailQty;
        oDetailFulfilled = orDetailFulfilled;

    }
    
    //Setters
    public void setoDetailId(String orDetailId){oDetailId = orDetailId;}
    public void setoDetailOId(String orDetailOId){oDetailOId = orDetailOId;}
    public void setoDetailPId(String orDetailPId){oDetailPId = orDetailPId;}
    public void setoDetailQty(String orDetailQty){oDetailQty = orDetailQty;}
    public void setoDetailFulfilled(String orDetailFulfilled){oDetailFulfilled = orDetailFulfilled;}

    
    //Getters
    public String getorDetailId(){return oDetailId;}
    public String getorDetailOId(){return oDetailOId;}
    public String getorDetailPId(){return oDetailPId;}
    public String getorDetailQty(){return oDetailQty;}
    public String getorDetailFulfilled(){return oDetailFulfilled;}
	
    // Select Statement for OrderDetail
    public void Select(String oDetailId) {
            
        try{   
            
            AccessDB or1 = new AccessDB();
            String sql = "SELECT * FROM OrderDetails WHERE ID = " + oDetailId;

            ArrayList<String> reception = or1.select(sql);

            setoDetailId(reception.get(0));
            setoDetailOId(reception.get(3));
            setoDetailPId(reception.get(1));
            setoDetailQty(reception.get(2));
            setoDetailFulfilled(reception.get(4));

            }
        
        catch(Exception ex) {System.out.println(ex); }

    }
    
    // Update Statement to set fulfilled status
    public void UpdateFulfillment(){
        try{ 
            
            String sql = "UPDATE OrderDetails SET fulfilled='"+oDetailFulfilled+"' where ID='"+oDetailId+"';";
            AccessDB o2 = new AccessDB();
            o2.update(sql);
            
        }
        catch(Exception ex) {System.out.println(ex); }
    }


    //DISPLAY for debugging purposes
    public void Display() {
            System.out.println("=====BEGINNING DEBUG FROM ORDERDETAIL.JAVA=====");
            System.out.println("OD ID: " + oDetailId);
            System.out.println("OD orderID: " + oDetailOId);
            System.out.println("OD ProductID: " + oDetailPId);
            System.out.println("OD Product Qty: " + oDetailQty);
            System.out.println("OD Fulfillment Status: " + oDetailFulfilled);
            System.out.println("=====END DEBUG FROM ORDERDETAIL.JAVA=====");
    }
    
    public static void main(String[] args){
        OrderDetail od1 = new OrderDetail();
        od1.Select("1");
        od1.Display();
    }
}
