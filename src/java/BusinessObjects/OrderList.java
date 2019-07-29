/*
 * OrderList class is used to generate an arraylist to hold OrderDetails inside
 * the Order Class. 
 */
package BusinessObjects;

import java.util.ArrayList;

/**
 *
 * @author Kazu
 */
public class OrderList extends ArrayList{
    
    /**
     * creates ArrayList for OrderDetail 
     */

    public ArrayList<OrderDetail> olist = new ArrayList();

    public void AddOrder(OrderDetail or){
        olist.add(or);
    }
/**
* Display Method for testing
*/
    public void Display(){
        OrderDetail o;
        for (int i = 0 ; i < olist.size() ; i++){
            o = olist.get(i);
            o.Display();
            System.out.println("---------------------------");
        }
    }  
}
