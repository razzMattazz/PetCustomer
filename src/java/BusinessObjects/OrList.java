/*
 *
 * OrList Class generates an arraylist to hold all orders.
 * Used for listing out orders for pick n pack on internal site.
 * 
 */
package BusinessObjects;

import java.util.ArrayList;

/**
 *
 * @author Kazu
 */
public class OrList extends ArrayList{
    
    /**
     * creates ArrayList for OrderList
     */

    public ArrayList<Order> orlist = new ArrayList();

    public void AddOrder(Order or){
        orlist.add(or);
    }
/**
* Display Method for testing
*/
    public void Display(){
        Order o;
        for (int i = 0 ; i < orlist.size() ; i++){
            o = orlist.get(i);
            o.Display();
            System.out.println("---------------------------");
        }
    }
}
