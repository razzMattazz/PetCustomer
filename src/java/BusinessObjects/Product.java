package BusinessObjects;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**************************************
    Product Business Object
    Author: Matthew Vaughn
***************************************/
/*
    doesnt have drop/insert classes. update class only alters invAvailable. 
didnt think anything more would be 
 */

public class Product {
    //ATTRIBUTES--------------------------------------------------
    private String prodID;
    private String prodName;
    private String description;
    private String price;
    private String invAvailable;
    private String invTotal;
    private String image;
    
    //CONSTRUCTORS-------------------------------------------------
    public Product() {
        setID("");
        setName("");
        setDescription("");
        setPrice("");
        setIA("");
        setIT("");
        setImg("");
    }
    
    public Product(String id, String name, String desc, String price, String ia, String it) {
        setID(id);
        setName(name);
        setDescription(desc);
        setPrice(price);
        setIA(ia);
        setIT(it);
    }
    public Product(String id, String name, String desc, String price, String ia, String it, String img) {
        setID(id);
        setName(name);
        setDescription(desc);
        setPrice(price);
        setIA(ia);
        setIT(it);
        setImg(img);
    }
    
    //SETTERS------------------------------------------------------
    public void setID(String newID) {
        prodID = newID;
    }
    public void setName(String newName) {
        prodName = newName;
    }
    public void setDescription(String newDes) {
        description = newDes;
    }
    public void setPrice(String newPrice) {
        price = newPrice;
    }
    public void setIA(String newIA) {
        invAvailable = newIA;
    }
    public void setIT(String newIT) {
        invTotal = newIT;
    }
    public void setImg(String newImg) {
        image = newImg;
    }
    //GETTERS-------------------------------------------------------
    public String getID() {
        return prodID;
    }
    public String getName() {
        return prodName;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }
    public String getIA() {
        return invAvailable;
    }
    public String getIT() {
        return invTotal;
    }
    public String getImg() {
        return image;
    }
    
    /**********
     * Reads a .txt file and returns it as a string
     * @return 
     **********/
    public String readDescr(){
        String descr = "";
        try{
            List<String> lines = Files.readAllLines(Paths.get("C:/Users/matth/Documents/PetCustomer/web/texts/" + getDescription()));
            for(int i = 0; i < lines.size(); i++){
                descr += lines.get(i);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return descr;
    }
    
    //SELECT--------------------------------------------------------
    /**********
         * Uses AccessDB class to call on the database and fill attributes
         * with data where the Product ID in the database corresponds to the
         * id parameter.
         * @param id Product ID
         **********/
    public void selectP(String id) {
        AccessDB a1 = new AccessDB();
        String sql = "SELECT * FROM Products WHERE ProdID = " + id;
        ArrayList<String> reception = a1.select(sql);

        setID(reception.get(0));
        setName(reception.get(1));
        setDescription(reception.get(2));
        setPrice(reception.get(3));
        setIA(reception.get(4));
        setIT(reception.get(5));
        setImg(reception.get(6));
    }
    
    public void updateInvAvailable(String id, String numOrdered) {
        Product uitemp = new Product();
        uitemp.selectP(id);
        int inv = Integer.parseInt(uitemp.getIA());
        int num = Integer.parseInt(numOrdered);
        inv = inv - num;     
        String sql = "UPDATE Products SET InvAvailable = "+ inv +" WHERE ProdID = "+ id;
        AccessDB a1 = new AccessDB();
        a1.update(sql);
    }
    
    /**********
     * Displays all attributes to the console.
     **********/
    public void display() {
        System.out.println(prodID + " " + prodName + " " + description + " " + price + " " + invAvailable + " " + invTotal + " " + image);
    }
    
    public static void main(String[] args){
     
        Product p1 = new Product();
        p1.updateInvAvailable("1", "3");
        p1.display();
    }
}