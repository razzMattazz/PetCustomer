/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

public class ProductList extends ArrayList{
    ArrayList<Product> plist = new ArrayList();
    
    public ProductList(){
        //fillList("SELECT ProdID FROM Products");  ---Currently Unnecessary---
    }
    
    /**********
     * Constructor that accepts a string to help build a restricting query that
     * searches for specific product names.
     * @param s
     **********/
    public ProductList(String s){
        String sql = "SELECT ProdID FROM Products WHERE ProdName LIKE \"*" + s + "*\"";
        fillList(sql);
    }
    
    /**********
     * Adds a Product object to the end of the ArrayList
     * @param prod 
     **********/
    public void addProd(Product prod){
        plist.add(prod);
    }
    
    /**********
     * 
     * @return  the ProductList
     **********/
    public ArrayList getList(){
        return plist;
    }
    
    /**********
     * Deletes a Product object from the ArrayList at index prodIndex.
     * @param prodIndex 
     **********/
    public void deleteProd(String prodIndex) {
        int iProdIndex = 0;
        try {
            iProdIndex = Integer.parseInt(prodIndex);
            plist.remove(iProdIndex);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    
    /**********
     * Returns a Product object from the ArrayList at index i.
     * @param i
     * @return 
     **********/
    public Product getProd(int i){
        return plist.get(i);
    }
    
    /**********
     * Fills the ArrayList with Product objects by first obtaining an ArrayList
     * of strings filled with Product IDs. These IDs are used to create Product
     * objects and add them to the ProductList.
     * @param sql 
     **********/
    public void fillList(String sql){
        AccessDB db = new AccessDB();
        ArrayList<String> ids = db.selectID(sql);
        
        for (int i = 0; i < ids.size(); i++){
            Product p = new Product();
            p.selectP(ids.get(i));
            addProd(p);
        }
        
    }
    
    /**********
     * Returns the number of items in the ArrayList
     * @return 
     **********/
    public int getSize(){
        return plist.size();
    }

    /**********
     * Displays each Product object in the ArrayList by using its display()
     * method to the console.
     **********/
    public void display(){
        Product p;
        for (int i = 0 ; i < plist.size() ; i++){
            p = plist.get(i);
            p.display();
            System.out.println("---------------------------");
        }
    }
}
