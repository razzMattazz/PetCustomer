/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;
/************************************
    Data Access Object
    Author: Matthew Vaughn
************************************/

import java.sql.*;
import java.util.ArrayList;

public class AccessDB {
    
    //Class variable dbPath defines the path where the database can be found
    String dbPath = "jdbc:ucanaccess://C:/Users/matth/Documents/PetCustomer/db/petDatabase.accdb;columnOrder=DISPLAY";
    
    public String getPath(){
        return dbPath;
    }
    
    /**********
     * Uses a string to emulate the SELECT SQL function.
     * @param sql   an SQL statement represented as a string
     * @return      returns an ArrayList of type String
     **********/
    public ArrayList<String> select(String sql) {
        ArrayList<String> transit = new ArrayList();
        
        try{
            //Step 1: Load Driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver Loading...");
            Connection con = DriverManager.getConnection(getPath());
            System.out.println("Driver Loaded.");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            rs.next();
            int count = data.getColumnCount();
            
            for (int i = 1; i <= count; i++){
                transit.add(rs.getString(i));
                System.out.println(transit.get(i-1));
            }

            con.close();
	}
        
	catch(Exception ex) {
            System.out.println(ex);
	}
        
        return transit;
    }
    
    /**********
     * This is similar to the select(String sql) method in that it emulates
     * a SQL SELECT function, but returns instead only the primary keys (or
     * first column) of a table as an ArrayList of type String.
     * @param sql   an SQL statement represented as a string
     * @return      returns an ArrayList of type String 
     **********/
    public ArrayList<String> selectID(String sql) {
        ArrayList<String> transit = new ArrayList();
        
        try{
            //Step 1: Load Driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver Loading...");
            Connection con = DriverManager.getConnection(getPath());
            System.out.println("Driver Loaded.");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            
            while(rs.next()){
                transit.add(rs.getString(1));
            }

            con.close();
	}
        
	catch(Exception ex) {
            System.out.println(ex);
	}
        
        return transit;
    }
    
    /**********
     * Accepts an SQL statement as a String to emulate an SQL UPDATE function.
     * @param sql   an SQL statement represented as a string
     **********/
    public void update(String sql) {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    /**********
     * Accepts an SQL statement as a String to emulate an SQL INSERT function.
     * @param sql   an SQL statement represented as a string
     **********/
    public void insert(String sql) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
            
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    /**********
     * Accepts an SQL statement as a String to emulate an SQL DELETE function.
     * @param sql   an SQL statement represented as a string
     **********/
    public void delete(String sql) {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int GetCount(String sql){
        int i = 0;
        
        try{
            //Step 1: Load Driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            rs.next();
            i = rs.getInt(1);
            con.close();
	}
        
	catch(Exception ex) {
            System.out.println(ex);
	}
        return i;
    
    }
    
    public void InsertOrder(Order o1){
    
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            String sql = "INSERT INTO orders VALUES('"+o1.getOrderID()+"','"+o1.getOrderCID()+"','"+o1.getOrderPay()+"','"+o1.getOrderFul()+"','"+o1.getOrderTotal()+"')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
            
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    
    }
    
    public void InsertOrderDetail(OrderDetail od1){
                try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            String sql = "INSERT INTO orderdetails VALUES("+od1.getorDetailId()+",'"+od1.getorDetailPId()+"','"+od1.getorDetailQty()+"','"+od1.getorDetailOId()+"',"+od1.getorDetailFulfilled()+")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
            
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    
    }
    
    public void InsertCustomer(Customer c1){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(getPath());
            String sql = "INSERT INTO customers VALUES('"+c1.getID()+"','"+c1.getFN()+"','"+c1.getLN()+"','"+c1.getPass()+"','"+c1.getAddress()+"','"+c1.getEMail()+"','"+c1.getAcctC()+"')";
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
            
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    
    
    }
    /**********
     * Uses the provided sql to select an ID and add 1 to it.
     * @param sql   SELECT statement to return an ID from a table
     * @return 
     **********/
    public int idCounter(String sql) {
        int i = 0;
        
        try{
            //Step 1: Load Driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver Loading...");
            Connection con = DriverManager.getConnection(getPath());
            System.out.println("Driver Loaded.");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            rs.next();
            i = rs.getInt(1);
            i++;
            
            

            con.close();
	}
        
	catch(Exception ex) {
            System.out.println(ex);
	}
        return i;
    }
    public static void main(String[] args){
        
    }
}
